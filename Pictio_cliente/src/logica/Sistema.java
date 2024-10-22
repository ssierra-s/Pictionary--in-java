/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.util.LinkedList;
import presentacion.Modelo;

/**
 *
 * @author User
 */
public class Sistema {
   
    private Modelo modelo;
    private Pictionary c;
    

    public Sistema(Modelo m) {
        modelo = m;
    }
    
    public void iniciar(){
        iniciarCliente();
    }
    
    public void enviarMensaje(String mensaje){
        c.enviarMensaje(mensaje);
    }
    
    public void mensajeEntrante(String id, String mensaje){
        this.getModelo().mensajeEntrante(id, mensaje);
    }
    
    public void enviarCoordenadas(int x, int y){
        c.enviarCoor(x,y);
    }
    public void enviarColor(int r,int g, int b)
    {
        c.enviarColor(r, g, b);
    }
    public void enviarGrosor(int grosor) {
      c.enviarGrosor(grosor);
    }

    public void coordenadaEntrante(int x, int y){
        modelo.dibujar (x,y);
    }
    
    public void colorEntrante(int r, int g, int b)
    {
        modelo.setColor(new Color(r,g,b));
    }
    
    public void grosorEntrante(int grosor)
    {
        modelo.setGrosor(grosor);
    }
    
    public void isChat(boolean estado){
        modelo.isChat(estado);
    }
    
    public void nuevaPalabra(String palabra1, String palabra2){
       
        getModelo().agregarPalabras(palabra1, palabra2);
    }
    
    public void newCliente(String jugador) {
        c = new Pictionary(jugador, this);
    }
    
    private void iniciarCliente() {
        modelo.cliente();
    }
    
    public Modelo getModelo() {
        if (modelo == null) {
            modelo = new Modelo();
        }
        return modelo;
    }
    
    public boolean turno()
    {
        return c.isTurno();
    }

    public void recibirTabla(LinkedList<String> tabla) {
        modelo.recibirTabla(tabla);
    }

    public void tiempo(String tempo) {
       modelo.tiempo(tempo);
    }
    
    public void desconectar(){
        c.confirmarDesconexion();
    }

    public void interruptor(String palabra) {
       c.Interruptor(palabra);
    }

    
}
