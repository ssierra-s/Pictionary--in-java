/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class Controlador implements ActionListener {

    private Modelo modelo;

    public Controlador(VistaInicio v) {
        modelo = v.getModelo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(modelo.getVentanaI().getBotonJugar()))
       {
          
           modelo.partida();
       }
    }
}
