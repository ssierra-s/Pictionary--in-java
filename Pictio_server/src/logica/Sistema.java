/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class Sistema {

    private final int DEFAULT_PORT = 7000;
    private final Servidor servidor;
    private ArrayList<String> palabras = new ArrayList<String>(Arrays.asList("Perro", "Gato", "Loro", "Dolor", "Hambre", "Café", "Libro", "Carpintero", "Helado", "Marinero", "Aplastar", "Tiroteo", "Televisor", "Computador", "Celular", "Cuaderno", "Camara", "Tortuga", "Zapato"));
    private ArrayList<String> palabrasAux = new ArrayList<String>();

    private String palabra1;
    private String palabra2;
    private String palabraElegida;
    private int turno = -1;
    private boolean rondaActiva = false;
    private int cantAcierto = 0;
    private boolean cambio = false;
    private int ronda = 0;

    public Sistema() {
        servidor = new Servidor(getPuerto(), this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema();
            }
        });
    }

    public void iniciarRonda() {
        if (rondaActiva == false) {
            if (ronda < 3) {
                if (cambio == false) {
                    int aux = (int) (Math.random() * palabras.size());
                    palabra1 = palabras.get(aux);
                    palabrasAux.add(palabra1);
                    palabras.remove(aux);
                    aux = (int) (Math.random() * palabras.size());
                    palabra2 = palabras.get(aux);
                    palabrasAux.add(palabra2);
                    palabras.remove(aux);
                    if (palabras.size() < 2) {
                        cambio = true;
                    }
                } else {
                    int aux = (int) (Math.random() * palabrasAux.size());
                    palabra1 = palabrasAux.get(aux);
                    palabras.add(palabra1);
                    palabrasAux.remove(aux);
                    aux = (int) (Math.random() * palabrasAux.size());
                    palabra2 = palabrasAux.get(aux);
                    palabras.add(palabra2);
                    palabrasAux.remove(aux);
                    if (palabrasAux.size() < 2) {
                        cambio = false;
                    }
                }
                cantAcierto = 0;
                if (servidor.clientes.size() - 1 == turno) {
                    turno = -1;
                    ronda++;
                }
                    turno++;
            } else {
                servidor.clientes.getFirst().finalizarRonda();
            }
        }
    }
    
    public String ganador(){
        String ganador = "";
        int puntajeAlto = 0;
        for(int i = 0; i < servidor.getClientes().size(); i++){
            if(puntajeAlto < Integer.parseInt(servidor.getClientes().get(i).getPuntaje())){
                ganador = servidor.getClientes().get(i).getIdentificador();
                puntajeAlto=Integer.parseInt(servidor.getClientes().get(i).getPuntaje());
            }
        }
        return ganador;
    }

    public boolean comprobrar(String cliente, String palabra) {
        if (this.palabraElegida.toLowerCase().equals(palabra.toLowerCase())) {
            cantAcierto++;
            if (cantAcierto == servidor.clientes.size() - 1) {
                rondaActiva = false;
            }
            return true;
        }
        return false;
    }

    public int getTurno() {
        return turno;
    }

    public String getPalabraElegida() {
        return palabraElegida;
    }

    public void setPalabraElegida(String palabraElegida) {
        this.palabraElegida = palabraElegida;
    }

    public int getRonda() {
        return ronda;
    }

    
    
    public int getCantAcierto() {
        return cantAcierto;
    }

    public void setCantAcierto(int cantAcierto) {
        this.cantAcierto = cantAcierto;
    }

    public String getPalabra() {
        return palabra1;
    }

    public String getPalabra2() {
        return palabra2;
    }

    private int getPuerto() {
        return this.DEFAULT_PORT;
    }

    /*
     * Metodo que agrega una lí­nea de texto al log.
     *
     * @param texto
     */
    public void agregarLog(String texto) {
        System.out.println(texto);
    }

    /**
     * Método que agrega un mensaje de confirmación al log cuando el servidor
     * esté corriendo correctamente.
     */
    void addServidorIniciado() {
        System.out.print("Inicializando el servidor... [Ok].");
    }

    public void setRondaActiva(boolean rondaActiva) {
        this.rondaActiva = rondaActiva;
    }

    public boolean isRondaActiva() {
        return rondaActiva;
    }
}
