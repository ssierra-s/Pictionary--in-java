/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedList;
import logica.Sistema;

/**
 *
 * @author User
 */
public class Modelo {

    private VistaInicio ventanaI;
    private VistaPartida ventanaP;
    private Sistema sistema;
    private Canvas lienzo;
    private Path2D shape;
    private String palabraElejida;
    //private final BufferedImage dobleBuffer;
    //private Graphics g;
    //private Graphics2D draw;
    private int grosor;
    private Color color;
    private int mx = 0, my = 0;

    public Modelo() {
        sistema = getSistema();
        ventanaI = getVentanaI();
        ventanaP = getVentanaP();
        lienzo = ventanaP.getCanvas();
        lienzo.setBackground(Color.white);
        this.grosor = 1;
        this.color = Color.black;
        this.shape = new Path2D.Float();
    }

    // ------ FUNCIONALIDADES DE MI APP
    public void iniciar() {
        sistema.iniciar();

    }

    public void cliente() {
        getVentanaI().setSize(340, 300);
        getVentanaI().setVisible(true);
        getVentanaI().setResizable(false);
        getVentanaI().setLocationRelativeTo(null);
        getVentanaI().getBotonJugar().setText("Unirse");
        getVentanaI().getEtiquetaMenu().setText("Adivina la palabra dibujada!");
    }

    public void partida() {
        String aux = getVentanaI().getTextNombre().getText();
        getSistema().newCliente(aux);
        getVentanaI().setVisible(false);
        getVentanaP().setSize(1024, 600);

        getVentanaP().setLocationRelativeTo(null);
        getVentanaP().setResizable(false);

        getVentanaP().getBoton1Panel().setEnabled(false);
        getVentanaP().getBoton2Panel().setEnabled(false);
        getVentanaP().setTitle(aux);
        getVentanaP().setVisible(true);
        ventanaCambio();
        controles(false);
    }

    public void dibujar(int x, int y) {
        Graphics g = lienzo.getGraphics();
        // Crea el graphics 2D
        Graphics2D draw = (Graphics2D) g.create();
        // Establece el color
        draw.setColor(color);
        // Establece el grosor
        draw.setStroke(new BasicStroke(grosor));
        // Y dibuja a mano alzada
        if (x == 10000 || y == 10000) {
            mx = 0;
            my = 0;
        } else if (mx == 0 || my == 0) {
            mx = x;
            my = y;
        } else {
            draw.draw(new Line2D.Double(mx, my, x, y));
            mx = x;
            my = y;
        }
    }

    public void limpiarCanvas() {
        this.getVentanaP().getCanvas().repaint(0, 0, this.getVentanaP().getCanvas().getWidth(), this.getVentanaP().getCanvas().getHeight());
    }

    public void enviarCoordenadas1(int x, int y) {
        sistema.enviarCoordenadas(x, y);
    }

    public void enviarMensaje() {
        String mensaje = getVentanaP().getTexto().getText();
        if (!"".equals(mensaje)) {
            getSistema().enviarMensaje(mensaje);
            getVentanaP().getTexto().setText("");
        }
    }

    public void enviarColor(int r, int g, int b) {
        sistema.enviarColor(r, g, b);
    }

    public void enviarGrosor(int grosor) {
        sistema.enviarGrosor(grosor);
    }

    public void isChat(boolean estado) {
        getVentanaP().getTexto().setEditable(estado);
    }

    public void mensajeEntrante(String id, String mensaje) {
        this.getVentanaP().getChat().setText(this.getVentanaP().getChat().getText() + "\n" + id + ": " + mensaje);
    }

    public void agregarPalabras(String palabra1, String palabra2) {
        this.getVentanaP().getPalabra().setText("");
        this.ventanaP.getBoton1Panel().setEnabled(true);
        this.ventanaP.getBoton2Panel().setEnabled(true);
        this.getVentanaP().getBoton1Panel().setText(palabra1);
        this.getVentanaP().getBoton2Panel().setText(palabra2);
    }

    public VistaInicio getVentanaI() {
        if (ventanaI == null) {

            ventanaI = new VistaInicio(this);
        }
        return ventanaI;
    }

    public Sistema getSistema() {
        if (sistema == null) {
            sistema = new Sistema(this);
        }
        return sistema;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getGrosor() {
        return grosor;
    }

    public Color getColor() {
        return color;
    }

    public VistaPartida getVentanaP() {
        if (ventanaP == null) {
            ventanaP = new VistaPartida(this);
        }
        return ventanaP;
    }

    public void controles(boolean estado) {
        ventanaP.getControles().setVisible(estado);
    }

    public void ventanaPalabra() {
        this.ventanaP.getBoton1Panel().setVisible(true);
        this.ventanaP.getBotonSPanel().setVisible(false);
        this.ventanaP.getBoton2Panel().setVisible(true);
        this.ventanaP.getTituloPanel().setBounds(10, 10, 300, 26);
        this.ventanaP.getTituloPanel().setText("Elije una palabra");
    }

    public void ventanaCambio() {
        this.ventanaP.getBoton1Panel().setVisible(false);
        this.ventanaP.getBoton2Panel().setVisible(false);
        this.ventanaP.getTituloPanel().setBounds(10, 40, 300, 26);
        this.ventanaP.getBotonSPanel().setVisible(false);
        this.ventanaP.getTituloPanel().setText("Espere!");
    }

    public void ventanaFinRonda() {
        this.ventanaP.getBoton1Panel().setVisible(false);
        this.ventanaP.getBotonSPanel().setVisible(false);
        this.ventanaP.getBoton2Panel().setVisible(false);
        this.ventanaP.getTituloPanel().setBounds(10, 40, 300, 26);
        this.ventanaP.getPanelFrente().setVisible(true);
        this.ventanaP.getTituloPanel().setText("La ronda termino!");
        this.getVentanaP().getPalabra().setText(palabraElejida);
    }

    public void ventanaFinPartida(String ganador) {
        this.ventanaP.getBoton1Panel().setVisible(false);
        this.ventanaP.getBoton2Panel().setVisible(false);
        this.ventanaP.getBotonSPanel().setVisible(true);
        this.ventanaP.getTituloPanel().setBounds(10, 10, 300, 26);
        this.ventanaP.getPanelFrente().setVisible(true);
        this.ventanaP.getTituloPanel().setText("El ganador fue: " + ganador);
    }

    public void etiqueta(String palabra) {
        int caracteres = palabra.length();
        String guion = "_ ";
        String aux = "";

        for (int i = 0; i < caracteres; i++) {
            aux = aux + guion;
        }
        this.getVentanaP().getPalabra().setText(aux);
    }

    public void ayudas(String palabra, int tiempo) {
        palabraElejida = palabra;
        int caracteres = palabra.length();
        String guion = "_ ";
        String aux = "";
        char[] caracteresLis = palabra.toCharArray();
        if (tiempo > 3) {
            if (caracteres > 5) {
                aux = "";
                if (tiempo < 80) {
                    for (int i = 0; i < caracteres; i++) {
                        if (i == 0) {
                            aux = aux + String.valueOf(caracteresLis[i]);
                        } else {
                            aux = aux + guion;
                        }

                    }
                }
                if (tiempo < 55) {
                    aux = "";
                    for (int i = 0; i < caracteres; i++) {
                        switch (i) {
                            case 0:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            case 2:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            default:
                                aux = aux + guion;
                                break;
                        }

                    }
                }
                if (tiempo < 30) {
                    aux = "";
                    for (int i = 0; i < caracteres; i++) {
                        switch (i) {
                            case 0:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            case 2:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            case 4:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            default:
                                aux = aux + guion;
                                break;
                        }

                    }
                }
                if (tiempo > 80) {
                    aux = "";
                    for (int i = 0; i < caracteres; i++) {
                        aux = aux + guion;
                    }
                }

                this.getVentanaP().getPalabra().setText(aux);
            }
            if (caracteres <= 5) {
                aux = "";
                if (tiempo < 75) {
                    for (int i = 0; i < caracteres; i++) {
                        if (i == 0) {
                            aux = aux + String.valueOf(caracteresLis[i]);
                        } else {
                            aux = aux + guion;
                        }

                    }
                }
                if (tiempo < 45) {
                    aux = "";
                    for (int i = 0; i < caracteres; i++) {
                        switch (i) {
                            case 0:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            case 2:
                                aux = aux + String.valueOf(caracteresLis[i] + " ");
                                break;
                            default:
                                aux = aux + guion;
                                break;
                        }

                    }
                }
                if (tiempo > 75) {
                    aux = "";
                    for (int i = 0; i < caracteres; i++) {
                        aux = aux + guion;
                    }
                }

                this.getVentanaP().getPalabra().setText(aux);
            }
        }
    }

    public void desconectar() {
        sistema.desconectar();
    }

    public void cambiarGrosor() {
        grosor = ventanaP.getjSlider1().getValue();
    }

    public void apagarPanel() {

        this.ventanaP.getPanelFrente().setVisible(false);
    }

    public void palabraElegida(String palabra) {
        sistema.interruptor(palabra);
    }

    public void recibirTabla(LinkedList<String> tabla) {
        String matris[][] = new String[tabla.size() - 1][2];
        for (int i = 1; i < tabla.size(); i = i + 2) {
            matris[i - 1][0] = tabla.get(i);
            matris[i - 1][1] = tabla.get(i + 1);
            System.out.println(tabla.get(i));
            System.out.println(tabla.get(i + 1));
        }
        ventanaP.getTabla().setModel(new javax.swing.table.DefaultTableModel(matris, new String[]{"Nombre", "Puntos"}));
    }

    public void tiempo(String tempo) {
        ventanaP.getLabelTiempo().setText(tempo);

    }
}
