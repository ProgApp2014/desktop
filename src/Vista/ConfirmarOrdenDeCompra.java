/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author rodro
 */
public class ConfirmarOrdenDeCompra extends JInternalFrame{
    private final JPanel contenedor;

    public ConfirmarOrdenDeCompra() {
        setBounds(50, 50, 700, 400);
        setVisible(true);
        setLayout(null);
        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setSize(800, 600);
        contenedor.setLocation(10, 0);
        add(contenedor);
    }
    
}
