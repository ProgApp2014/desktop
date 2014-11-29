/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import clases.ProxyOrden;
import clases.Utils;
import controlador.middleware.DataEspecificacionProducto;
import controlador.middleware.DataOrdenCompra;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author darius
 */
public class VerInformacionOrden extends JInternalFrame {

    private final JPanel contenedor;
    private final JList<String> ordenList;
    private final JLabel nroRef;
    private final JLabel fechaVenta;
    private final JLabel precioTotal;
    private final JLabel cliente;
    private final JLabel productos;
    private final JTextField precioTotalText;
    private final JTextField nroRefText;
    private final JTextField clienteText;
    private JList<String> productosList;

    private final JButton cancelarBtn;

    private JTable listarClientes;
    private JScrollPane scrollPaneTableDetail;
    private final JTextField fechaVentaText;
    private final JButton borrarBtn;
    private boolean modoEdicion;
    private final JLabel estAct;
    private final JTextField estActText;
    private final JLabel estados;
    private JScrollPane scrollPaneEstados;

    public VerInformacionOrden(boolean modoEdicion) {

        this.modoEdicion = modoEdicion;
        setBounds(50, 50, 1000, 600);
        setVisible(true);
        setLayout(null);
        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setSize(700, 400);
        contenedor.setLocation(10, 0);
        add(contenedor);

        JLabel elegirUsuarioLabel = new JLabel("Elegir Orden De Compra:");
        elegirUsuarioLabel.setVisible(true);
        elegirUsuarioLabel.setBounds(0, 10, 150, 20);
        contenedor.add(elegirUsuarioLabel);

        ordenList = new JList<String>();
        fillOrdenList();

        ordenList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ordenList.setBounds(0, 50, 200, 300);
        scrollPaneTableDetail = new JScrollPane();
        scrollPaneEstados = new JScrollPane();
        ordenList.addListSelectionListener(new ListSelectionListener() {
            private Object[][] rowData;
            private int index;
            private JTable listarEstados;

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting() || ordenList.getSelectedValue() == null) {
                    return;
                }

                Integer ordenId = Integer.parseInt(ordenList.getSelectedValue().split("-")[0].trim());
                DataOrdenCompra aux = null;
                List<DataOrdenCompra> ordenes = (modoEdicion)?ProxyOrden.getInstance().listarOrdenesAPreparar():ProxyOrden.getInstance().listarOrdenes();
                Iterator it = ordenes.iterator();
                while (it.hasNext()) {
                    aux = (DataOrdenCompra) it.next();
                    if (aux.getNroOrden().equals(ordenId)) {
                        break;
                    }
                }
                ProxyOrden.getInstance().elegirOrden(aux.getNroOrden());
                nroRefText.setText(String.valueOf(aux.getNroOrden()));
                fechaVentaText.setText(Utils.formatDate(aux.getFecha()));
                precioTotalText.setText(String.valueOf(aux.getPrecioTotal()));
                clienteText.setText(aux.getClienteCompraProducto().get(0).getCliente().getNickname());
                estActText.setText(aux.getEstadoActualTexto());
                DefaultListModel<String> tes2 = new DefaultListModel<String>();
                List<DataEspecificacionProducto> productosLst = ProxyOrden.getInstance().listarProductosEnOrden();
                HashMap<String, DatosProducto> mp = new HashMap();
                productosLst.stream().forEach((producto) -> {

                    if (!mp.containsKey(producto.getNroReferencia())) {
                        mp.put(producto.getNroReferencia(), new DatosProducto(producto));
                    } else {

                        mp.get(producto.getNroReferencia()).incrementar();
                    }
                });
                
                index = 0;
                rowData = new Object[1][aux.getEstados().size()];
                Object[] row =  new Object[aux.getEstados().size()];
                String[] columEstados = new String[aux.getEstados().size()];
                aux.getEstados().forEach((est)->{
                    row[index] = Utils.formatDate(est.getFecha());
                    columEstados[index] = est.getNombre();
                    index++;
                });
                rowData[0] = row;
                scrollPaneEstados.removeAll();
                listarEstados = new JTable(rowData, columEstados);
                listarEstados.setPreferredScrollableViewportSize(new Dimension(300, 50));
                listarEstados.setFillsViewportHeight(true);
                scrollPaneEstados = new JScrollPane(listarEstados);//setViewportView(listarClientes);
                scrollPaneEstados.setBounds(370, 250, 300, 60);
                contenedor.add(scrollPaneEstados);
                
                rowData = new Object[mp.values().size()][4];
                index = 0;
                mp.values().forEach((dp) -> {
                    Object[] obj = {dp.getProducto().getNroReferencia(), dp.getProducto().getNombre(), "$" + dp.getProducto().getPrecio(), dp.getCantidad()};
                    rowData[index] = obj;
                    index++;
                });
                scrollPaneTableDetail.removeAll();
                String[] columnNames = {"NroReferencia", "Nombre", "Precio", "Cantidad"};
                listarClientes = new JTable(rowData, columnNames);

                listarClientes.setPreferredScrollableViewportSize(new Dimension(440, 100));
                listarClientes.setFillsViewportHeight(true);
                scrollPaneTableDetail = new JScrollPane(listarClientes);//setViewportView(listarClientes);
                scrollPaneTableDetail.setBounds(230, 310, 440, 120);
                contenedor.add(scrollPaneTableDetail);
                contenedor.validate();
                contenedor.repaint();
                repaint();
            }
        });

        contenedor.add(ordenList);

        nroRef = new JLabel("Numero de referencia");
        nroRef.setVisible(true);
        nroRef.setBounds(220, 60, 150, 10);
        contenedor.add(nroRef);

        nroRefText = new JTextField();
        nroRefText.setBounds(370, 50, 300, 30);
        contenedor.add(nroRefText);

        fechaVenta = new JLabel("Fecha de venta");
        fechaVenta.setVisible(true);
        fechaVenta.setBounds(220, 100, 150, 10);
        contenedor.add(fechaVenta);

        fechaVentaText = new JTextField();

        fechaVentaText.setBounds(370, 90, 300, 30);
        contenedor.add(fechaVentaText);

        precioTotal = new JLabel("Precio total");
        precioTotal.setVisible(true);
        precioTotal.setBounds(220, 140, 150, 10);
        contenedor.add(precioTotal);

        precioTotalText = new JTextField();
        precioTotalText.setBounds(370, 130, 300, 30);
        contenedor.add(precioTotalText);

        cliente = new JLabel("Cliente");
        cliente.setVisible(true);
        cliente.setBounds(220, 180, 150, 10);
        contenedor.add(cliente);

        clienteText = new JTextField();
        clienteText.setBounds(370, 170, 300, 30);
        contenedor.add(clienteText);
        
        estAct = new JLabel("Estado Actual:");
        estAct.setVisible(true);
        estAct.setBounds(220, 220, 150, 10);
        contenedor.add(estAct);
        
        estActText = new JTextField();
        estActText.setBounds(370, 210, 300, 30);
        contenedor.add(estActText);
        
        estados = new JLabel("Estados");
        estados.setVisible(true);
        estados.setBounds(220, 260, 150, 10);
        contenedor.add(estados);
        
        productos = new JLabel("Productos");
        productos.setVisible(true);
        productos.setBounds(220, 300, 150, 10);
        contenedor.add(productos);

        cancelarBtn = new JButton("Cerrar");

        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar(e);
            }
        });
        borrarBtn = new JButton("Cancelar Orden ");
        if (modoEdicion) {
            borrarBtn.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    borrarOrden(e);
                }
            });
            borrarBtn.setBounds(250, 430, 200, 40);
            add(borrarBtn);
        }
        cancelarBtn.setBounds(470, 430, 100, 40);
        add(cancelarBtn);
    }

    private void guardarCategoria(ActionEvent evt) {
        Integer nroOrden = Integer.valueOf(nroRefText.getText());

        ProxyOrden.getInstance().elegirOrden(nroOrden);
        ProxyOrden.getInstance().borrarOrdenCompra();
        setVisible(false);
        nroRefText.setText("");
    }

    private void cancelar(ActionEvent evt) {
        setVisible(false);
        nroRefText.setText("");
        fechaVentaText.setText("");
        precioTotalText.setText("");
        clienteText.setText("");
    }

    private void borrarOrden(ActionEvent evt) {
        Integer nroOrden = Integer.valueOf(nroRefText.getText());
        if (JOptionPane.showConfirmDialog(this, "Esta seguro que desea cancelar la oden de compra? \nEste paso no se puede deshacer", "", JOptionPane.WARNING_MESSAGE) == 0) {
           ProxyOrden.getInstance().agregarEstadoOrdenCancelada(nroOrden);
        }
    }

    private void fillOrdenList() {

        DefaultListModel tes = new DefaultListModel();
        List<DataOrdenCompra> ordenes = (modoEdicion)?ProxyOrden.getInstance().listarOrdenesAPreparar():ProxyOrden.getInstance().listarOrdenes();
        ordenes.stream().forEach((orden) -> {
            tes.addElement(orden.getNroOrden() + " - " + Utils.formatDate(orden.getFecha()));
        });

        ordenList.setModel(tes);
        ordenList.revalidate();
    }

    class DatosProducto {

        private int cantidad;
        private DataEspecificacionProducto p;

        private DatosProducto() {

        }

        public DatosProducto(DataEspecificacionProducto producto) {

            this.p = producto;
            this.cantidad = 1;

        }

        private void incrementar() {
            this.cantidad++;
        }

        private DataEspecificacionProducto getProducto() {
            return p;
        }

        private int getCantidad() {
            return cantidad;
        }

    }
}
