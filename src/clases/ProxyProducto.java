/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Controlador.Clases.Fabrica;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rodro
 */
public class ProxyProducto {

    private final Integer idProductosControlador;

    private static ProxyProducto instance = null;

    public static ProxyProducto getInstance() {

        if (ProxyProducto.instance == null) {

            ProxyProducto.instance = new ProxyProducto();

        }
        return ProxyProducto.instance;
    }

    private ProxyProducto() {

        idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();

    }

    public List<DataProveedor> listarProveedores() {

        List<DataProveedor> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarProveedores();
//        List<DataProveedorWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toProveedor((DataProveedor) it.next()));
//        }
        return l;
    }

    public void elegirProveedor(String nickname) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirProveedor(nickname);
    }

    public void ingresarDatosProductos(DataEspecificacionProducto espProducto) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosProductos(espProducto);
    }

    public Boolean elegirTipoProducto() {

        return null;
    }

    public void agregarMultiplesProductosAutogenerados(Integer cantidad) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarMultiplesProductosAutogenerados(cantidad);
    }

    public void ingresarDatosUnidad(DataProducto producto) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosUnidad(producto);
    }

    public List<DataCategoria> listarCategorias() {
        List<DataCategoria> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarCategorias();
//        List<DataCategoriaWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toCategoria((DataCategoria) it.next()));
//        }
        return l;
    }

    public void elegirCategoria(String categoria) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirCategoria(categoria);
    }

    public List<DataEspecificacionProducto> listarProductosCategoria() {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarProductosCategoria();
//        List<DataEspecificacionProductoWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toEspecificacionProducto((DataEspecificacionProducto) it.next()));
//        }
        return l;
    }

    public DataEspecificacionProducto mostrarDatosProducto(String numRef) {
        DataEspecificacionProducto l = Fabrica.getInstance().getControladorProductos(idProductosControlador).mostrarDatosProducto(numRef);
        return l;
    }

    public Boolean controlarErrores() {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).controlarErrores();
    }

    public void guardarProducto() {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarProducto();
    }

    public DataCategoria elegirCategoriaPadre(String categoria) {
        DataCategoria l = Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirCategoriaPadre(categoria);
        return l;
    }

    public void ingresarDatosCategoria(DataCategoria categoria) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosCategoria(categoria);
    }

    public void asociarCategoriaPadre(DataCategoria padre) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).asociarCategoriaPadre(padre);
    }

    public void guardarCategoria() {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarCategoria();
    }

    public void elegirEspProducto(String numRef) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirEspProducto(numRef);
    }

    public DataProducto mostrarInformacionProducto() {
        DataProducto l = Fabrica.getInstance().getControladorProductos(idProductosControlador).mostrarInformacionProducto();
        return l;
    }

    public void modificarDatosEspecificacionProducto(DataEspecificacionProducto espProducto) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).modificarDatosEspecificacionProducto(espProducto);
    }

    public void agregarImagen(String rutaImagen) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarImagen(rutaImagen);
    }

    public void agregarCategoria(DataCategoria categoria) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarCategoria(categoria);
    }

    public Boolean validarInfo() {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).validarInfo();
    }

    public void ingresarEspecificacion(String clave, String desc) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarEspecificacion(clave, desc);
    }

    public void agregarCategoriaAEspecificacion(String categoria) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarCategoriaAEspecificacion(categoria);
    }

    public List<String> listarImagenesAModificar() {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).listarImagenesAModificar();
    }

    public void borrarImagen(String rutaImagen) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).borrarImagen(rutaImagen);
    }

    public List<DataCategoria> listarCategoriasAModificar() {
        List<DataCategoria> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarCategoriasAModificar();
//        List<DataCategoriaWS> ret = new ArrayList();
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ret.add(ConvertidorFrontEnd.toCategoria((DataCategoria) it.next()));
//        }
        return l;
    }

    public void borrarCategoriaAEspecificacion(String categoria) {
    }

    public void guardarEspProductoModificado() {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarEspProductoModificado();
    }

    public Boolean categoryAlreadyExist(String categoria) {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).categoryAlreadyExist(categoria);
    }
}
