/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controlador.middleware.ControladorUsuariosWS;
import controlador.middleware.ControladorUsuariosWSService;
import controlador.middleware.DataCliente;
import controlador.middleware.DataEspecificacionProducto;
import controlador.middleware.DataOrdenCompra;
import controlador.middleware.DataProveedor;
import java.util.List;

/**
 *
 * @author rodro
 */
public class ProxyUsuario {

    private Integer idUsuariosControlador;

    private static ProxyUsuario instance = null;
    private final ControladorUsuariosWS controlador;

    public ProxyUsuario() {

        idUsuariosControlador = null;
        ControladorUsuariosWSService servicio = new ControladorUsuariosWSService();
        controlador = servicio.getControladorUsuariosWSPort();
        idUsuariosControlador = controlador.getId();
    }

    public static ProxyUsuario getInstance() {

        if (ProxyUsuario.instance == null) {

            ProxyUsuario.instance = new ProxyUsuario();

        }
        return ProxyUsuario.instance;
    }

    public void ingresarDatosCliente(DataCliente cliente) {
        controlador.ingresarDatosCliente(cliente, idUsuariosControlador);
    }

    public void ingresarDatosProveedor(DataProveedor proveedor) {
        
        controlador.ingresarDatosProveedor(proveedor, idUsuariosControlador);
    }

    public Boolean validarDatosUsuario() {
        
        return controlador.validarDatosUsuario( idUsuariosControlador);

    }

    public void guardarUsuario() {
        controlador.guardarUsuario( idUsuariosControlador);
    }

    public List<DataCliente> listarClientes() {
        return  controlador.listarClientes( idUsuariosControlador).getItem();
       
    }

    public void elegirCliente(String nickname) {
        controlador.elegirCliente(nickname, idUsuariosControlador);
    }

    public DataCliente mostrarDatosCliente() {
        return controlador.mostrarDatosCliente( idUsuariosControlador);
    }

    public List<DataOrdenCompra> listarOrdenesCliente() {
        return  controlador.listarOrdenesCliente( idUsuariosControlador).getItem();

    }

    public List<DataProveedor> listarProveedores() {
        return controlador.listarProveedores( idUsuariosControlador).getItem();

    }

    public void elegirProveedor(String nickname) {
          controlador.elegirProveedor( nickname,idUsuariosControlador);
    }

    public DataProveedor mostrarDatosProveedor() {
        return  controlador.mostrarDatosProveedor( idUsuariosControlador);
    }

    public String getErrors() {
        
        return  controlador.getErrors( idUsuariosControlador);

    }

    public Boolean login(String nickname, String hashPassword) {
        return  controlador.login( nickname,hashPassword,idUsuariosControlador);
    }

    public Boolean esProveedor(String nickname) {
        
        return  controlador.esProveedor( nickname,idUsuariosControlador);
    }

    public Boolean esCliente(String nickname) {
        return  controlador.esCliente( nickname,idUsuariosControlador);
    }

    public List<DataEspecificacionProducto> listarProductosProveedor() {
        return  controlador.listarProductosProveedor( idUsuariosControlador).getItem();
    }

    public void genDatosPrueba() {
         controlador.genDatosPrueba();
    }
}
