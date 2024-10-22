/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author aguil
 */
public class VistaPartida extends javax.swing.JFrame {

    private final Modelo modelo;
    private ControladorPartida control;

    /**
     * Creates new form Vista
     *
     * @param m
     */
    public VistaPartida(Modelo m) {
        modelo = m;
        initComponents();
        getEventos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFrente = new javax.swing.JPanel();
        tituloPanel = new javax.swing.JLabel();
        botonSPanel = new javax.swing.JButton();
        boton1Panel = new javax.swing.JButton();
        boton2Panel = new javax.swing.JButton();
        labelGanador = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        palabra = new javax.swing.JLabel();
        botonEnviar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextPane();
        canvas = new java.awt.Canvas();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        controles = new javax.swing.JPanel();
        cNegro = new javax.swing.JPanel();
        cCafeC = new javax.swing.JPanel();
        cGris = new javax.swing.JPanel();
        cCafeO = new javax.swing.JPanel();
        cFucsia = new javax.swing.JPanel();
        cAzulC = new javax.swing.JPanel();
        cRojo = new javax.swing.JPanel();
        cNaranja = new javax.swing.JPanel();
        cAmarillo = new javax.swing.JPanel();
        cRosado = new javax.swing.JPanel();
        cAzulO = new javax.swing.JPanel();
        cVerdeO = new javax.swing.JPanel();
        cVerdeC = new javax.swing.JPanel();
        cMorado = new javax.swing.JPanel();
        bBorrador = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        etiquetaFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 576));
        getContentPane().setLayout(null);

        panelFrente.setBackground(new java.awt.Color(51, 51, 51));
        panelFrente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 5));
        panelFrente.setLayout(null);

        tituloPanel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        tituloPanel.setForeground(new java.awt.Color(255, 255, 255));
        tituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPanel.setText("Elige una palabra");
        panelFrente.add(tituloPanel);
        tituloPanel.setBounds(10, 10, 300, 26);

        botonSPanel.setBackground(new java.awt.Color(204, 204, 204));
        botonSPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/x2.png"))); // NOI18N
        botonSPanel.setBorderPainted(false);
        botonSPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonSPanel.setOpaque(false);
        panelFrente.add(botonSPanel);
        botonSPanel.setBounds(280, 70, 30, 30);

        boton1Panel.setBackground(new java.awt.Color(204, 204, 204));
        boton1Panel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1PanelActionPerformed(evt);
            }
        });
        panelFrente.add(boton1Panel);
        boton1Panel.setBounds(170, 60, 120, 30);

        boton2Panel.setBackground(new java.awt.Color(204, 204, 204));
        panelFrente.add(boton2Panel);
        boton2Panel.setBounds(30, 60, 120, 30);

        labelGanador.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        panelFrente.add(labelGanador);
        labelGanador.setBounds(31, 20, 260, 40);

        getContentPane().add(panelFrente);
        panelFrente.setBounds(320, 200, 320, 110);

        labelTiempo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(labelTiempo);
        labelTiempo.setBounds(50, 10, 108, 37);

        palabra.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        palabra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(palabra);
        palabra.setBounds(270, 10, 410, 40);

        botonEnviar.setBackground(new java.awt.Color(255, 255, 255));
        botonEnviar.setText("►");
        getContentPane().add(botonEnviar);
        botonEnviar.setBounds(930, 510, 50, 30);

        texto.setBackground(new java.awt.Color(51, 51, 51));
        texto.setForeground(new java.awt.Color(255, 255, 255));
        texto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(texto);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(760, 510, 160, 30);

        canvas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(canvas);
        canvas.setBounds(220, 50, 520, 400);

        jScrollPane1.setAutoscrolls(true);

        chat.setEditable(false);
        chat.setBackground(new java.awt.Color(51, 51, 51));
        chat.setColumns(20);
        chat.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        chat.setForeground(new java.awt.Color(255, 255, 255));
        chat.setLineWrap(true);
        chat.setRows(5);
        chat.setAutoscrolls(false);
        jScrollPane1.setViewportView(chat);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(760, 20, 226, 464);

        tabla.setBackground(new java.awt.Color(51, 51, 51));
        tabla.setForeground(new java.awt.Color(255, 255, 255));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Puntos"
            }
        ));
        tabla.setEnabled(false);
        tabla.setGridColor(new java.awt.Color(153, 153, 153));
        tabla.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tabla.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(tabla);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 50, 165, 402);

        controles.setBackground(new java.awt.Color(51, 51, 51));
        controles.setLayout(null);

        cNegro.setBackground(new java.awt.Color(0, 0, 0));
        cNegro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cNegro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cNegroMouseClicked(evt);
            }
        });
        controles.add(cNegro);
        cNegro.setBounds(0, 0, 20, 20);

        cCafeC.setBackground(new java.awt.Color(185, 122, 87));
        cCafeC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cCafeC);
        cCafeC.setBounds(20, 20, 20, 20);

        cGris.setBackground(new java.awt.Color(204, 204, 204));
        cGris.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cGris);
        cGris.setBounds(0, 20, 20, 20);

        cCafeO.setBackground(new java.awt.Color(102, 0, 0));
        cCafeO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cCafeO);
        cCafeO.setBounds(20, 0, 20, 20);

        cFucsia.setBackground(new java.awt.Color(204, 0, 204));
        cFucsia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cFucsia);
        cFucsia.setBounds(120, 20, 20, 20);

        cAzulC.setBackground(new java.awt.Color(0, 204, 204));
        cAzulC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cAzulC);
        cAzulC.setBounds(40, 20, 20, 20);

        cRojo.setBackground(new java.awt.Color(255, 0, 0));
        cRojo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cRojo);
        cRojo.setBounds(60, 0, 20, 20);

        cNaranja.setBackground(new java.awt.Color(255, 102, 0));
        cNaranja.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cNaranja);
        cNaranja.setBounds(80, 20, 20, 20);

        cAmarillo.setBackground(new java.awt.Color(255, 255, 51));
        cAmarillo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cAmarillo);
        cAmarillo.setBounds(80, 0, 20, 20);

        cRosado.setBackground(new java.awt.Color(255, 174, 201));
        cRosado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cRosado);
        cRosado.setBounds(60, 20, 20, 20);

        cAzulO.setBackground(new java.awt.Color(0, 51, 255));
        cAzulO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cAzulO);
        cAzulO.setBounds(40, 0, 20, 20);

        cVerdeO.setBackground(new java.awt.Color(0, 102, 0));
        cVerdeO.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cVerdeO);
        cVerdeO.setBounds(100, 0, 20, 20);

        cVerdeC.setBackground(new java.awt.Color(181, 230, 29));
        cVerdeC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cVerdeC);
        cVerdeC.setBounds(100, 20, 20, 20);

        cMorado.setBackground(new java.awt.Color(51, 0, 153));
        cMorado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controles.add(cMorado);
        cMorado.setBounds(120, 0, 20, 20);

        bBorrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/borrador.png"))); // NOI18N
        bBorrador.setBorderPainted(false);
        bBorrador.setContentAreaFilled(false);
        bBorrador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bBorrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorradorActionPerformed(evt);
            }
        });
        controles.add(bBorrador);
        bBorrador.setBounds(160, 5, 30, 30);

        jSlider1.setBackground(new java.awt.Color(51, 51, 51));
        jSlider1.setForeground(new java.awt.Color(255, 255, 255));
        jSlider1.setMajorTickSpacing(4);
        jSlider1.setMaximum(15);
        jSlider1.setMinimum(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setValue(1);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        controles.add(jSlider1);
        jSlider1.setBounds(210, 0, 180, 40);

        getContentPane().add(controles);
        controles.setBounds(220, 460, 390, 40);

        etiquetaFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/Fondo.jpg"))); // NOI18N
        etiquetaFondo.setAlignmentY(0.0F);
        getContentPane().add(etiquetaFondo);
        etiquetaFondo.setBounds(0, 0, 1024, 576);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoKeyPressed

    private void bBorradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bBorradorActionPerformed

    private void cNegroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cNegroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cNegroMouseClicked

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider1StateChanged

    private void boton1PanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1PanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton1PanelActionPerformed

    public Canvas getCanvas() {
        return canvas;
    }

    public JButton getBoton1Panel() {
        return boton1Panel;
    }

    public JButton getBoton2Panel() {
        return boton2Panel;
    }

    public JLabel getTituloPanel() {
        return tituloPanel;
    }

    public JPanel getPanelFrente() {
        return panelFrente;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    
    public JTextArea getChat() {
        return chat;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    public JLabel getPalabra() {
        return palabra;
    }

    public void setPalabra(JLabel palabra) {
        this.palabra = palabra;
    }

    public JLabel getLabelTiempo() {
        return labelTiempo;
    }

    public void setLabelTiempo(JLabel labelTiempo) {
        this.labelTiempo = labelTiempo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JButton getBotonEnviar() {
        return botonEnviar;
    }

    public void setBotonEnviar(JButton botonEnviar) {
        this.botonEnviar = botonEnviar;
    }

    public JTextPane getTexto() {
        return texto;
    }

    public void setTexto(JTextPane texto) {
        this.texto = texto;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public ControladorPartida getControl() {
        if (control == null) {
            control = new ControladorPartida(this);
        }
        return control;
    }

    public JPanel getControles() {
        return controles;
    }

    public JButton getbBorrador() {
        return bBorrador;
    }

    public JPanel getcAmarillo() {
        return cAmarillo;
    }

    public JPanel getcAzulC() {
        return cAzulC;
    }

    public JPanel getcAzulO() {
        return cAzulO;
    }

    public JPanel getcCafeC() {
        return cCafeC;
    }

    public JPanel getcCafeO() {
        return cCafeO;
    }

    public JPanel getcFucsia() {
        return cFucsia;
    }

    public JPanel getcGris() {
        return cGris;
    }

    public JPanel getcMorado() {
        return cMorado;
    }

    public JPanel getcNaranja() {
        return cNaranja;
    }

    public JPanel getcNegro() {
        return cNegro;
    }

    public JPanel getcRojo() {
        return cRojo;
    }

    public JPanel getcRosado() {
        return cRosado;
    }

    public JPanel getcVerdeC() {
        return cVerdeC;
    }

    public JPanel getcVerdeO() {
        return cVerdeO;
    }

    public JSlider getjSlider1() {
        return jSlider1;
    }

    public JButton getBotonSPanel() {
        return botonSPanel;
    }

    public JLabel getLabelGanador() {
        return labelGanador;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBorrador;
    private javax.swing.JButton boton1Panel;
    private javax.swing.JButton boton2Panel;
    private javax.swing.JButton botonEnviar;
    private javax.swing.JButton botonSPanel;
    private javax.swing.JPanel cAmarillo;
    private javax.swing.JPanel cAzulC;
    private javax.swing.JPanel cAzulO;
    private javax.swing.JPanel cCafeC;
    private javax.swing.JPanel cCafeO;
    private javax.swing.JPanel cFucsia;
    private javax.swing.JPanel cGris;
    private javax.swing.JPanel cMorado;
    private javax.swing.JPanel cNaranja;
    private javax.swing.JPanel cNegro;
    private javax.swing.JPanel cRojo;
    private javax.swing.JPanel cRosado;
    private javax.swing.JPanel cVerdeC;
    private javax.swing.JPanel cVerdeO;
    private java.awt.Canvas canvas;
    private javax.swing.JTextArea chat;
    private javax.swing.JPanel controles;
    private javax.swing.JLabel etiquetaFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JLabel labelGanador;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JLabel palabra;
    private javax.swing.JPanel panelFrente;
    private javax.swing.JTable tabla;
    private javax.swing.JTextPane texto;
    private javax.swing.JLabel tituloPanel;
    // End of variables declaration//GEN-END:variables

    private void getEventos() {
        //canvas.addMouseMotionListener(getControl());
        //canvas.addMouseListener(getControl());
        //controles.setVisible(false);
        botonEnviar.addActionListener(getControl());
        canvas.addMouseListener(getControl());
        canvas.addMouseMotionListener(getControl());
        texto.addKeyListener(getControl());
        cAmarillo.addMouseListener(getControl());
        cAzulC.addMouseListener(getControl());
        cAzulO.addMouseListener(getControl());
        cCafeC.addMouseListener(getControl());
        cCafeO.addMouseListener(getControl());
        cFucsia.addMouseListener(getControl());
        cGris.addMouseListener(getControl());
        cMorado.addMouseListener(getControl());
        cNaranja.addMouseListener(getControl());
        cNegro.addMouseListener(getControl());
        cRojo.addMouseListener(getControl());
        cRosado.addMouseListener(getControl());
        cVerdeC.addMouseListener(getControl());
        cVerdeO.addMouseListener(getControl());
        jSlider1.addChangeListener(getControl());
        bBorrador.addActionListener(getControl());
        boton1Panel.addActionListener(getControl());
        boton2Panel.addActionListener(getControl());
        botonSPanel.addActionListener(getControl());
    }

}
