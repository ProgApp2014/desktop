package Vista;

import clases.ImagesProxy;
import clases.ProxyUsuario;
import clases.Utils;
import controlador.middleware.DataCliente;
import controlador.middleware.DataProveedor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

class RegistrarUsuarioForm extends JInternalFrame {

//
    private final Formulario form;
//
    private final SelectorImgUsuario sim;
    private boolean esProveedor;

    public RegistrarUsuarioForm() {

        setBounds(50, 50, 800, 500);
        setVisible(true);
        setLayout(new SpringLayout());
        setTitle("Registrar Usuario");

        form = new Formulario();
        form.addField("Proveedor", "checkbox");
        form.addField("Nickname", "text");
        form.addField("Password", "text");
        form.addField("Email", "text");
        form.addField("Fecha nac", "Date");
        form.addField("Apellido", "text");
        form.addField("Nombre", "text");
        form.addField("Nombre Compania", "text");
        form.addField("Link Sitio", "text");
        esProveedor = false;
        form.toggleVisibility("Nombre Compania", esProveedor);
        form.toggleVisibility("Link Sitio", esProveedor);
        ((JTextField) form.getComponentByName("Nombre Compania")).setVisible(esProveedor);
        ((JTextField) form.getComponentByName("Link Sitio")).setVisible(esProveedor);

        ((JCheckBox) form.getComponentByName("Proveedor")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cambiarTipoFormulario(evt);
            }

        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(200, 200);
        buttonsPanel.setVisible(true);
        JButton salvar = new JButton("Guardar");
        salvar.setSize(80, 30);
        salvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario(e);
            }
        });
        JButton cancelar = new JButton("Cancelar");
        cancelar.setSize(80, 30);
        cancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar(e);
            }
        });
        buttonsPanel.add(salvar);
        buttonsPanel.add(cancelar);

        JPanel offsetleft = new JPanel();
        offsetleft.setLayout(new BorderLayout());
        offsetleft.setSize(400, 800);
        offsetleft.setVisible(true);
        offsetleft.setLocation(0, 10);
        offsetleft.add(buttonsPanel, BorderLayout.SOUTH);
        offsetleft.add(form, BorderLayout.CENTER);

        sim = new SelectorImgUsuario();
        sim.setLocation(700, 10);
        sim.setSize(400, 400);

        add(offsetleft);
        add(sim);

        SpringUtilities.makeGrid(this.getContentPane(), 1, 2, 0, 0, 6, 6);

    }

    private void cleanForm() {

        ((JTextField) form.getComponentByName("Nickname")).setText("");
        ((JTextField) form.getComponentByName("Password")).setText("");
        ((JTextField) form.getComponentByName("Email")).setText("");
        ((DateChosserPanel) form.getComponentByName("Fecha nac")).setDate(null);
        ((JTextField) form.getComponentByName("Apellido")).setText("");
        ((JTextField) form.getComponentByName("Nombre")).setText("");
        ((JTextField) form.getComponentByName("Nombre Compania")).setText("");
        ((JTextField) form.getComponentByName("Link Sitio")).setText("");
        sim.clean();
    }

    private void guardarUsuario(ActionEvent evt) {
        String nickname = ((JTextField) form.getComponentByName("Nickname")).getText();
        String password = Utils.md5(((JTextField) form.getComponentByName("Password")).getText());
        String email = ((JTextField) form.getComponentByName("Email")).getText();
        Date fnacDate = ((DateChosserPanel) form.getComponentByName("Fecha nac")).getDate();
        GregorianCalendar fnac = (GregorianCalendar) GregorianCalendar.getInstance();
        fnac.setTime(fnacDate);

        String apellido = ((JTextField) form.getComponentByName("Apellido")).getText();
        String nombre = ((JTextField) form.getComponentByName("Nombre")).getText();
        String nombreCompania = ((JTextField) form.getComponentByName("Nombre Compania")).getText();
        String linkSitio = ((JTextField) form.getComponentByName("Link Sitio")).getText();
        String imagen = sim.getSelectedIMG();
        if (nickname == null || nickname.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Nickname es requerido", "Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password == null || password.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Password es requerido", "Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email == null || email.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Email es requerido", "Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (imagen != null && !imagen.isEmpty()) {
 
            FileInputStream in = null;
            try {
                ImagesProxy ih = new ImagesProxy();
                in = new FileInputStream(imagen);
                imagen = ih.saveImage(in, imagen);
            } catch (FileNotFoundException ex) {
                System.out.println("0 "+ex.getMessage()+" "+ex.getStackTrace());
                Logger.getLogger(RegistrarUsuarioForm.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(RegistrarUsuarioForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!esProveedor) {
            DataCliente cliente = new DataCliente();
            cliente.setNickname(nickname);
            cliente.setPassword(password);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setFechaNacimiento(Utils.getXMLGregorianCalendar(fnac));
            cliente.setNombre(nombre);

            cliente.setImagen(imagen);
            ProxyUsuario.getInstance().ingresarDatosCliente(cliente);
        } else {
            DataProveedor proveedor = new DataProveedor();

            proveedor.setNickname(nickname);
            proveedor.setPassword(password);
            proveedor.setApellido(apellido);
            proveedor.setEmail(email);
            proveedor.setFechaNacimiento(Utils.getXMLGregorianCalendar(fnac));
            proveedor.setNombre(nombre);
            proveedor.setImagen(imagen);
            proveedor.setLinkSitio(linkSitio);
            proveedor.setNombreCompania(nombreCompania);
            ProxyUsuario.getInstance().ingresarDatosProveedor(proveedor);
        }
        if (ProxyUsuario.getInstance().validarDatosUsuario()) {
            JOptionPane.showMessageDialog(this, ProxyUsuario.getInstance().getErrors(), "Validacion", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(this, "Su Usuario se creo correctamente", "Validacion", JOptionPane.DEFAULT_OPTION);
            ProxyUsuario.getInstance().guardarUsuario();
            cleanForm();

        };

    }

    private void cancelar(ActionEvent evt) {
        setVisible(false);

    }

    private void cambiarTipoFormulario(ActionEvent evt) {

        esProveedor = ((JCheckBox) form.getComponentByName("Proveedor")).isSelected();

        form.toggleVisibility("Nombre Compania", esProveedor);
        form.toggleVisibility("Link Sitio", esProveedor);

    }

}
