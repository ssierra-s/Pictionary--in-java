/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Pictionary extends Thread {

    final String host = "127.0.0.1";
    final int puerto = 7000;

    private String id;
    private int puntaje;

    private String mensaje;

    private boolean turno;

    private boolean escuchando;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Sistema sistema;

    public Pictionary(String jugador, Sistema sistema) {

        this.id = jugador;
        this.sistema = sistema;
        this.escuchando = true;
        this.start();
    }

    /**
     * Método run del hilo de comunicación del lado del cliente.
     */
    public void run() {
        try {
            socket = new Socket(host, puerto);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Conexion exitosa!!!!");
            this.enviarSolicitudConexion();
            this.escuchar();
        } catch (UnknownHostException ex) {
            System.exit(0);
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    /*
     * Método que escucha constantemente lo que el servidor dice.
     */
    public void escuchar() {
        try {
            while (escuchando) {
                Object aux = in.readObject();
                if (aux != null) {
                    if (aux instanceof LinkedList) {
                        //Si se recibe una LinkedList entonces se procesa
                        ejecutar((LinkedList<String>) aux);
                    } else {
                        System.err.println("Se recibió un Objeto desconocido a través del socket");
                    }
                } else {
                    System.err.println("Se recibió un null a través del socket");
                }
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    /**
     * Método que ejecuta una serie de instruccines dependiendo del
     * enviarMensaje que el cliente reciba del servidor.
     *
     * @param lista
     */
    private void ejecutar(LinkedList<String> lista) {
        // 0 - El primer elemento de la lista es siempre el tipo
        String tipo = lista.get(0);
        switch (tipo) {
            case "CONEXION_ACEPTADA":
                // 1      - Identificador propio del nuevo usuario
                // 2 .. n - Identificadores de los clientes conectados actualmente
                this.id = lista.get(1);
                sistema.getModelo().getVentanaP().setTitle(this.id);
                /*
                
                 *inserte codigo para identificar a todos los jugadores en la tabla de jugadores
                
                 */
                break;
            case "NUEVO_USUARIO_CONECTADO":
                // 1      - Identificador propio del cliente que se acaba de conectar
                //ventana.addContacto(lista.get(1));
                break;
            case "USUARIO_DESCONECTADO":
                // 1      - Identificador propio del cliente que se acaba de conectar
                //ventana.eliminarContacto(lista.get(1));

                break;
            case "MENSAJE":
                // 1      - Cliente emisor
                // 2      - Mensaje
                sistema.mensajeEntrante(lista.get(1), lista.get(2));
                break;
            case "COLOR":
                sistema.colorEntrante(Integer.parseInt(lista.get(1)), Integer.parseInt(lista.get(2)), Integer.parseInt(lista.get(3)));
                break;
            case "FIN_PARTIDA":
                sistema.nuevaPalabra("", "");
                sistema.getModelo().ventanaFinPartida(lista.get(1));
                turno = false;
                sistema.getModelo().controles(false);
                isChat(false);
                sistema.tiempo("");
                break;
            case "GROSOR":
                sistema.grosorEntrante(Integer.parseInt(lista.get(1)));
                break;
            case "AYUDA":
               //
                break;
            case "INICIO_RONDA":
                sistema.getModelo().limpiarCanvas();
                if (lista.get(1).equals(id)) {
                    turno = true;
                    sistema.getModelo().ventanaPalabra();
                    sistema.nuevaPalabra(lista.get(2), lista.get(3));
                    sistema.getModelo().controles(true);
                    isChat(false);
                } else {
                    turno = false;
                    sistema.getModelo().controles(false);
                    sistema.getModelo().ventanaCambio();
                    isChat(true);
                }

                break;
            case "COORDENADAS":
                // 1      - Cliente emisor
                // 2      - posición en x
                // 3     - posición en y

                sistema.coordenadaEntrante(Integer.parseInt(lista.get(2)), Integer.parseInt(lista.get(3)));
                break;
            case "SUMAR_PUNTAJE":
                //1 - cliente
                //2 - puntaje a sumar
                if (this.id.equals(lista.get(1))) {
                    isChat(false);
                }
                break;
            case "QUITAR":
                //1 - cliente
                //2 - puntaje a sumar
                sistema.getModelo().apagarPanel();
                break;
            case "FINALIZAR_RONDA":
                sistema.nuevaPalabra("", "");
                sistema.getModelo().ventanaFinRonda();
                turno = false;
                sistema.getModelo().controles(false);
                isChat(false);
                sistema.tiempo("");
                break;
            case "TABLA":
                LinkedList<String> tabla = lista;
                sistema.recibirTabla(tabla);
                break;
            case "TIEMPO":
                if (!lista.get(1).equals(id)) {
                sistema.getModelo().ayudas(lista.get(2), Integer.parseInt(lista.get(3)));
                }
                sistema.tiempo(lista.get(3));
                break;
            default:
                break;
        }
    }

    /**
     * Método que envia un determinado enviarMensaje hacia el servidor.
     *
     * @param mensaje
     */
    public void enviarMensaje(String mensaje) {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("MENSAJE");
        //cliente emisor
        lista.add(this.getIdentificador());
        //mensaje que se desea transmitir
        lista.add(mensaje);
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    public void Interruptor(String palabra) {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("INTERRUPTOR");
        //cliente emisor
        lista.add(this.getIdentificador());
        lista.add(palabra);
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    public void enviarCoor(int x, int y) {
        if (turno == true) {
            LinkedList<String> lista = new LinkedList<>();
            //tipo
            lista.add("COORDENADAS");
            //cliente emisor
            lista.add(this.getIdentificador());
            //posición x
            lista.add(String.valueOf(x));
            //posición y
            lista.add(String.valueOf(y));
            try {
                out.writeObject(lista);
            } catch (IOException ex) {
                System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
            }
        }

    }

    public void enviarColor(int r, int g, int b) {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("COLOR");
        lista.add(String.valueOf(r));
        lista.add(String.valueOf(g));
        lista.add(String.valueOf(b));
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    void enviarGrosor(int grosor) {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("GROSOR");
        lista.add(String.valueOf(grosor));
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    private void enviarSolicitudConexion() {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("SOLICITUD_CONEXION");
        //cliente solicitante
        lista.add(this.id);
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    public void confirmarDesconexion() {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("SOLICITUD_DESCONEXION");
        //cliente solicitante
        lista.add(this.id);
        try {
            out.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    private void isChat(boolean estado) {
        sistema.isChat(estado);
    }

    public boolean isTurno() {
        return turno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdentificador() {
        return this.id;
    }

}
