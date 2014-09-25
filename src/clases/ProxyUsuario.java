/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.clases;

import Controlador.Clases.Fabrica;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProveedor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rodro
 */
public class ProxyUsuario {

    private final Integer idUsuariosControlador;

    private static ProxyUsuario instance = null;

    public ProxyUsuario() {

        idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();

    }

    public static ProxyUsuario getInstance() {

        if (ProxyUsuario.instance == null) {

            ProxyUsuario.instance = new ProxyUsuario();

        }
        return ProxyUsuario.instance;
    }

    public void ingresarDatosCliente(DataCliente cliente) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).ingresarDatosCliente(cliente);
    }

    public void ingresarDatosProveedor(DataProveedor proveedor) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).ingresarDatosProveedor(proveedor);
    }

    public Boolean validarDatosUsuario() {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).validarDatosUsuario();

    }

    public void guardarUsuario() {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).guardarUsuario();
    }

    public List<DataCliente> listarClientes() {
        List<DataCliente> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarClientes();

//        List<DataClienteWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toCliente((DataCliente) it.next()));
//        }
        return l;
    }

    public void elegirCliente(String nickname) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).elegirCliente(nickname);

    }

    public DataCliente mostrarDatosCliente() {
        DataCliente l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).mostrarDatosCliente();
        return l;

    }

    public List<DataOrdenCompra> listarOrdenesCliente() {
        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarOrdenesCliente();
//        List<DataOrdenCompraWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toOrden((DataOrdenCompra) it.next()));
//        }
        return l;

    }

    public List<DataProveedor> listarProveedores() {
        List<DataProveedor> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarProveedores();

//        List<DataProveedorWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toProveedor((DataProveedor) it.next()));
//        }
        return l;

    }

    public void elegirProveedor(String nickname) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).elegirProveedor(nickname);
    }

    public DataProveedor mostrarDatosProveedor() {
        DataProveedor l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).mostrarDatosProveedor();

        return l;

    }

    public String getErrors() {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).getErrors();

    }
}
