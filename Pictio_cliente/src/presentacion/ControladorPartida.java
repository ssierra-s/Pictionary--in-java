/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author pc
 */
public class ControladorPartida implements MouseMotionListener, MouseListener, ActionListener, KeyListener, ChangeListener {

    private Modelo modelo;

    public ControladorPartida(VistaPartida ventana) {
        modelo = ventana.getModelo();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       // modelo.dibujar(e.getX(), e.getY());
        modelo.enviarCoordenadas1(e.getX(), e.getY());
        //modelo.dibujar(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(modelo.getVentanaP().getcNegro())){
            modelo.setColor(Color.BLACK);
            modelo.enviarColor(0, 0, 0);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcGris())){
            modelo.setColor(new Color(204,204,204));
            modelo.enviarColor(204, 204, 204);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcAzulC())){
            modelo.setColor(new Color(0,204,204));
            modelo.enviarColor(0, 204, 204);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcCafeC())){
            modelo.setColor(new Color(185,122,87));
            modelo.enviarColor(185,122,87);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcRojo())){
            modelo.setColor(new Color(255,0,0));
            modelo.enviarColor(255,0,0);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcCafeO())){
            modelo.setColor(new Color(102,0,0));
            modelo.enviarColor(102,0,0);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcRosado())){
            modelo.setColor(new Color(255,174,201));
            modelo.enviarColor(255,174,201);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcAmarillo())){
            modelo.setColor(new Color(255,255,51));
            modelo.enviarColor(255,255,51);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcAzulO())){
            modelo.setColor(new Color(0,51,255));
            modelo.enviarColor(0,51,255);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcNaranja())){
            modelo.setColor(new Color(255,102,0));
            modelo.enviarColor(255,102,0);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcVerdeO())){
            modelo.setColor(new Color(0,102,0));
            modelo.enviarColor(0,102,0);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcVerdeC())){
            modelo.setColor(new Color(181,230,29));
            modelo.enviarColor(181,230,29);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcMorado())){
            modelo.setColor(new Color(51,0,153));
            modelo.enviarColor(51,0,153);
            return;
        }
        if(e.getSource().equals(modelo.getVentanaP().getcFucsia())){
            modelo.setColor(new Color(204,0,204));
            modelo.enviarColor(204,0,204);
            return;
        }
        
        
                
    }

    @Override
    public void mousePressed(MouseEvent e) {
         //modelo.dibujar(e.getX(), e.getY());
         modelo.enviarCoordenadas1(e.getX(), e.getY());
         //modelo.dibujar(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //modelo.dibujar(10000, 10000);
        modelo.enviarCoordenadas1(10000, 10000);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource().equals(modelo.getVentanaP().getBotonEnviar()))
        {
            modelo.enviarMensaje();
            return;
        }
        if (e.getSource().equals(modelo.getVentanaP().getbBorrador()))
        {
            modelo.setColor(new Color(255,255,255));
            modelo.enviarColor(255,255,255);
            return;
        }
        if (e.getSource().equals(modelo.getVentanaP().getBoton1Panel()))
        {
            modelo.apagarPanel();
            modelo.palabraElegida(modelo.getVentanaP().getBoton1Panel().getText());
            modelo.getVentanaP().getPalabra().setText(modelo.getVentanaP().getBoton1Panel().getText());
            return;
        }
        if (e.getSource().equals(modelo.getVentanaP().getBoton2Panel()))
        {
            modelo.apagarPanel();
            modelo.palabraElegida(modelo.getVentanaP().getBoton2Panel().getText());
            modelo.getVentanaP().getPalabra().setText(modelo.getVentanaP().getBoton2Panel().getText());
            return;
        }
        if (e.getSource().equals(modelo.getVentanaP().getBotonSPanel()))
        {
            modelo.desconectar();
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
     
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            e.consume();
            modelo.enviarMensaje();
        }
       
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      
    }

    @Override
    public void stateChanged(ChangeEvent e) {
       modelo.cambiarGrosor();
       modelo.enviarGrosor(modelo.getGrosor());
    }
}
