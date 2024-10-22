/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Servidor extends Thread {

    private ServerSocket servidor;

    LinkedList<Cliente> clientes;

    private Socket cliente;
    static int correlativo;
    private int puerto = 7000;

    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;

    private int tiempo = 100;
    
    private Sistema sistema;

    public Servidor(int puerto, Sistema sistema) {
        this.correlativo = 0;
        this.puerto = puerto;

        this.sistema = sistema;

        clientes = new LinkedList<>();
        this.start();
    }
    
    public void run() {
        try {
            servidor = new ServerSocket(puerto);
            sistema.addServidorIniciado();

            while (true) {
                Cliente h;
                Socket socket;
                socket = servidor.accept();
                System.out.println("Nueva conexion entrante: " + socket);
                h = new Cliente(socket, this);
                h.start();
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public LinkedList<String> getUsuariosConectados() {
        LinkedList<String> usuariosConectados = new LinkedList<>();
        clientes.stream().forEach(c -> usuariosConectados.add(c.getIdentificador()));
        return usuariosConectados;
    }
    
    public LinkedList<Cliente> getClientes() {
        return clientes;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    public void definirPalabra(String palabra)
    {
        sistema.setPalabraElegida(palabra);
    }
    /**
     * Método que agrega una linea al log de la interfaz gráfica del servidor.
     *
     * @param texto
     */
    void agregarLog(String texto) {
        sistema.agregarLog(texto);
    }
}
