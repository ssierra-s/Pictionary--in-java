/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Cliente extends Thread {

    /**
     * Socket que se utiliza para comunicarse con el cliente.
     */
    private final Socket socket;
    /**
     * Stream con el que se envían objetos al servidor.
     */
    private ObjectOutputStream objectOutputStream;
    /**
     * Stream con el que se reciben objetos del servidor.
     */
    private ObjectInputStream objectInputStream;
    /**
     * Servidor al que pertenece este hilo.
     */
    private final Servidor server;
    /**
     * Identificador ínico del cliente con el que este hilo se comunica.
     */
    private String identificador;
    private String puntaje = "0";

    private Timer timer = new Timer();
    private TimerTask tarea;

    private boolean aux = false;
    private boolean seleccionando = false;
    /**
     * Variable booleana que almacena verdadero cuando este hilo esta escuchando
     * lo que el cliente que atiende esta diciendo.
     */
    private boolean escuchando;

    /**
     * Método constructor de la clase hilo cliente.
     *
     * @param socket
     * @param server
     */
    public Cliente(Socket socket, Servidor server) {
        this.server = server;
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.err.println("Error en la inicialización del ObjectOutputStream y el ObjectInputStream");
        }
    }

    /**
     * Método encargado de cerrar el socket con el que se esta comunicando.
     */
    public void desconnectar() {
        try {
            socket.close();
            escuchando = false;
        } catch (IOException ex) {
            System.err.println("Error al cerrar el socket de comunicación con el cliente.");
        }
    }

    /**
     * Sobreescritura del método de Thread, es acá en donde se monta el ciclo
     * infinito.
     */
    public void run() {
        try {
            escuchar();
        } catch (Exception ex) {
            System.err.println("Error al llamar al método readLine del hilo del cliente.");
        }
        desconnectar();
    }

    /**
     * Método que constantemente esta escuchando todo lo que es enviado por el
     * cliente que se comunica con él.
     */
    public void escuchar() {
        escuchando = true;
        while (escuchando) {
            try {
                Object aux = objectInputStream.readObject();
                if (aux instanceof LinkedList) {
                    ejecutar((LinkedList<String>) aux);
                }
            } catch (Exception e) {
                System.err.println("Error al leer lo enviado por el cliente.");
            }
        }
    }

    /**
     * Método que realiza determinadas acciones dependiendo de lo que el socket
     * haya recibido y lo que este le envie el método, en él se manejan una
     * serie de códigos.
     *
     * @param lista
     */
    public void ejecutar(LinkedList<String> lista) {
        // 0 - El primer elemento de la lista es siempre el tipo
        String tipo = lista.get(0);
        switch (tipo) {
            case "SOLICITUD_CONEXION":
                // 1 - Identificador propio del nuevo usuario
                confirmarConexion(lista.get(1));
                break;
            case "SOLICITUD_DESCONEXION":
                // 1 - Identificador propio del nuevo usuario
                confirmarDesConexion();
                break;
            case "INTERRUPTOR":
                // 1 - Identificador propio del nuevo usuario
                quitarPanel();
                server.definirPalabra(lista.get(2));
                server.setTiempo(100);
                server.getSistema().setRondaActiva(true);
                interruptorTiempo();
                seleccionando = false;
                break;
            case "MENSAJE":
                // 1      - Cliente emisor
                // 2      - Mensaje
                if (server.getSistema().comprobrar(lista.get(1), lista.get(2))) {
                    if (!server.getClientes().get(server.getSistema().getTurno()).getIdentificador().equals(lista.get(1))) {
                        sumarPuntaje(lista.get(1));
                    }
                } else {
                    server.clientes
                            .stream()
                            .forEach(cliente -> cliente.enviarMensaje(lista));
                }
                finalizarRonda();
                break;
            case "COLOR":
                // 1      - Cliente emisor
                // 2      - Mensaje
                server.clientes
                        .stream()
                        .forEach(cliente -> cliente.enviarMensaje(lista));
                break;
            case "GROSOR":
                // 1      - Cliente emisor
                // 2      - Mensaje
                server.clientes
                        .stream()
                        .forEach(cliente -> cliente.enviarMensaje(lista));
                break;
            case "COORDENADAS":
                // 1      - Cliente emisor
                // 2      - Mensaje
                server.clientes
                        .stream()
                        .forEach(cliente -> cliente.enviarMensaje(lista));
                break;
            default:
                break;
        }
    }

    /**
     * MÃ©todo para enviar un mensaje al cliente atraves del socket.
     *
     * @param lista
     */
    private void enviarMensaje(LinkedList<String> lista) {
        try {
            objectOutputStream.writeObject(lista);
        } catch (Exception e) {
            System.err.println("Error al enviar el objeto al cliente.");
        }
    }

    /**
     * Una vez conectado un nuevo cliente, este mÃ©todo notifica a todos los
     * clientes conectados que hay un nuevo cliente para que lo agreguen a sus
     * contactos.
     *
     * @param identificador
     */
    private void confirmarConexion(String identificador) {
        if (server.getSistema().isRondaActiva() == false) {
            Servidor.correlativo++;
            this.identificador = Servidor.correlativo + " - " + identificador;
            LinkedList<String> lista = new LinkedList<>();
            lista.add("CONEXION_ACEPTADA");
            lista.add(this.identificador);
            lista.addAll(server.getUsuariosConectados());
            enviarMensaje(lista);
            server.agregarLog("\nNuevo cliente: " + this.identificador);
            //enviar a todos los clientes el nombre del nuevo usuario conectado excepto a él mismo
            LinkedList<String> auxLista = new LinkedList<>();
            auxLista.add("NUEVO_USUARIO_CONECTADO");
            auxLista.add(this.identificador);
            server.clientes
                    .stream()
                    .forEach(cliente -> cliente.enviarMensaje(auxLista));
            server.clientes.add(this);
            actualizarTabla();
            if (Servidor.correlativo >= 2) {
                iniciarRonda();
            }
        } else {
            System.out.print("entra");
            inicioEspera();
        }
    }

    /**
     * MÃ©todo que retorna el identificador Ãºnico del cliente dentro del chat.
     *
     * @return
     */
    public String getIdentificador() {
        return identificador;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String aux) {
        puntaje = aux;
    }

    /**
     * MÃ©todo que se invoca cuando el usuarioi al que atienede este hilo decide
     * desconectarse, si eso pasa, se tiene que informar al resto de los
     * usuarios que ya no pueden enviarle mensajes y que deben quitarlo de su
     * lista de contactos.
     */
    private void confirmarDesConexion() {
        LinkedList<String> auxLista = new LinkedList<>();
        auxLista.add("USUARIO_DESCONECTADO");
        auxLista.add(this.identificador);
        server.agregarLog("\nEl cliente \"" + this.identificador + "\" se ha desconectado.");
        this.desconnectar();
        for (int i = 0; i < server.clientes.size(); i++) {
            if (server.clientes.get(i).equals(this)) {
                server.clientes.remove(i);
                break;
            }
        }
        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(auxLista));
    }

    public void inicioEspera() {
        LinkedList<String> lista = new LinkedList<>();
        lista.add("USUARIO_DESCONECTADO");

        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(lista));
    }

    private void iniciarRonda() {
        seleccionando = true;
        server.getSistema().iniciarRonda();
        LinkedList<String> lista = new LinkedList<>();
        if (server.getSistema().getRonda() != 3) {
            lista.add("INICIO_RONDA");
            lista.add(server.clientes.get(server.getSistema().getTurno()).getIdentificador());
            lista.add(server.getSistema().getPalabra());
            lista.add(server.getSistema().getPalabra2());
            server.clientes
                    .stream()
                    .forEach(h -> h.enviarMensaje(lista));
            aux = false;
            tarea.cancel();
        } else {
            finalizarRonda();
        }
    }

    private void interruptorTiempo() {
        if (aux == false) {
            LinkedList<String> listaAux = new LinkedList<>();
            listaAux.add("AYUDA");
            listaAux.add(server.clientes.get(server.getSistema().getTurno()).getIdentificador());
            listaAux.add(server.getSistema().getPalabraElegida());
            server.clientes
                    .stream()
                    .forEach(h -> h.enviarMensaje(listaAux));
            
            temporizador();
            aux = true;
        }
    }

    private void quitarPanel() {
        LinkedList<String> lista = new LinkedList<>();
        lista.add("QUITAR");
        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(lista));
    }

    public void finalizarRonda() {
        if (server.getSistema().getRonda() == 3) {

            LinkedList<String> listaAux = new LinkedList<>();
            listaAux.add("FIN_PARTIDA");
            listaAux.add(server.getSistema().ganador());
            server.clientes
                    .stream()
                    .forEach(h -> h.enviarMensaje(listaAux));
            tarea.cancel();
        } else {
            if (!server.getSistema().isRondaActiva()) {
                server.setTiempo(3);

                LinkedList<String> lista = new LinkedList<>();

                lista.add("FINALIZAR_RONDA");
                server.clientes
                        .stream()
                        .forEach(h -> h.enviarMensaje(lista));
                System.out.print("entra");
            }
        }
    }

    private void sumarPuntaje(String cliente) {
        LinkedList<String> lista = new LinkedList<>();
        lista.add("SUMAR_PUNTAJE");
        lista.add(cliente);
        for (int i = 0; i < server.getClientes().size(); i++) {
            if (cliente.equals(server.getClientes().get(i).getIdentificador())) {
                server.getClientes().get(i).setPuntaje(String.valueOf(Integer.parseInt(server.getClientes().get(i).getPuntaje()) + server.getTiempo()));
            }
            actualizarTabla();
        }
        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(lista));
    }

    private void actualizarTabla() {
        LinkedList<String> listaClientes = new LinkedList<>();
        listaClientes.add("TABLA");
        for (int i = 0; i < server.getClientes().size(); i++) {
            listaClientes.add(server.getClientes().get(i).getIdentificador());
            listaClientes.add(server.getClientes().get(i).getPuntaje());

        }
        server.clientes
                .stream()
                .forEach(h -> h.enviarMensaje(listaClientes));
    }

    private void temporizador() {
        tarea = new TimerTask() {
            @Override
            public void run() {
                LinkedList<String> lista = new LinkedList<>();
                lista.add("TIEMPO");
                lista.add(server.clientes.get(server.getSistema().getTurno()).getIdentificador());
                lista.add(server.getSistema().getPalabraElegida());
                server.setTiempo(server.getTiempo() - 1);
                lista.add(String.valueOf(server.getTiempo()));
                if (server.getTiempo() < 0) {
                    if (server.getSistema().isRondaActiva()) {
                        //timer.cancel();
                        server.getSistema().setRondaActiva(false);
                        finalizarRonda();
                    } else {
                        //timer.cancel();
                        if (seleccionando == false) {
                            iniciarRonda();
                        }
                        server.setTiempo(0);
                    }
                    System.out.println("se acabo el tiempo");
                } else {
                    server.clientes
                            .stream()
                            .forEach(h -> h.enviarMensaje(lista));
                }

            }
        };
        timer.schedule(tarea, 0, 1000);
    }
}
