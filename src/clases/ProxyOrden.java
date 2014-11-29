/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controlador.middleware.ControladorOrdenesWS;
import controlador.middleware.ControladorOrdenesWSService;
import controlador.middleware.DataCategoria;
import controlador.middleware.DataCliente;
import controlador.middleware.DataEspecificacionProducto;
import controlador.middleware.DataOrdenCompra;
import controlador.middleware.DataOrdenCompraArray;
import java.util.List;

/**
 *
 * @author rodro
 */
public class ProxyOrden {

    private Integer idOrdenesControlador;
    private final ControladorOrdenesWS controlador;

    public ProxyOrden() {

        idOrdenesControlador = null;
        ControladorOrdenesWSService servicio = new ControladorOrdenesWSService();
        controlador = servicio.getControladorOrdenesWSPort();
        idOrdenesControlador = controlador.getId();
    }

    private static ProxyOrden instance = null;

    public static ProxyOrden getInstance() {

        if (ProxyOrden.instance == null) {

            ProxyOrden.instance = new ProxyOrden();

        }
        return ProxyOrden.instance;
    }

    public List<DataCliente> listarClientes() {

        return controlador.listarClientes(idOrdenesControlador).getItem();

    }

    public void elegirCliente(String nickname) {
        controlador.elegirCliente(nickname, idOrdenesControlador);
    }

    public List<DataCategoria> listarCategorias() {

        return controlador.listarCategorias(idOrdenesControlador).getItem();

    }

    public void elegirCategoria(String categoria) {

        controlador.elegirCategoria(categoria, idOrdenesControlador);
    }

    public List<DataEspecificacionProducto> listarEspecificacionProductos() {
        return controlador.listarEspecificacionProductos(idOrdenesControlador).getItem();

    }

    public void elegirEspecificacionProducto(String nroRef) {
        controlador.elegirEspecificacionProducto(nroRef, idOrdenesControlador);
    }

    public List<DataEspecificacionProducto> listarProductosEnOrden() {
        return controlador.listarProductosEnOrden(idOrdenesControlador).getItem();

    }

    public void elegirProducto(Integer id) {

        controlador.elegirProducto(id, idOrdenesControlador);
    }

    public void elegirCantidadProducto(Integer cantidad) {
        controlador.elegirCantidadProducto(cantidad, idOrdenesControlador);
    }

    public void generarItemOrden() {

        controlador.generarItemOrden(idOrdenesControlador);
    }

    public List<DataEspecificacionProducto> listarProductosCategoria() {
        return controlador.listarProductosCategoria(idOrdenesControlador).getItem();
    }

    public void guardarOrden(DataOrdenCompra dataOrden) {

        controlador.guardarOrden(dataOrden, idOrdenesControlador);
    }

    public List<DataOrdenCompra> listarOrdenes() {
        return controlador.listarOrdenes(idOrdenesControlador).getItem();

    }

    public void elegirOrden(Integer nroOrden) {
        controlador.elegirOrden(nroOrden, idOrdenesControlador);
    }

    public void borrarOrdenCompra() {

        controlador.borrarOrdenCompra(idOrdenesControlador);
    }

    public DataOrdenCompra mostrarDatosOrden() {

        return controlador.mostrarDatosOrden(idOrdenesControlador);
    }

    public void removerEspecificacionProducto(String ref) {

        controlador.removerEspecificacionProducto(ref, idOrdenesControlador);
    }

    public void agregarEstadoOrdenRecibida(Integer nroOrden) {
        controlador.agregarEstadoOrdenRecibida(nroOrden, idOrdenesControlador);
    }

    public void agregarEstadoOrdenCancelada(Integer nroOrden) {
        controlador.agregarEstadoOrdenCancelada(nroOrden, idOrdenesControlador);
    }

    public void agregarEstadoOrdenConfirmada(Integer nroOrden) {
        controlador.agregarEstadoOrdenConfirmada(nroOrden, idOrdenesControlador);
    }

    public void agregarEstadoOrdenPreparada(Integer nroOrden) {
        controlador.agregarEstadoOrdenPreparada(nroOrden, idOrdenesControlador);
    }

    public List<DataOrdenCompra> listarOrdenesAPreparar() {

        return controlador.listarOrdenesAPreparar(idOrdenesControlador).getItem();

    }

}
