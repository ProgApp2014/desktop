/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;
 
import controlador.middleware.DataEspecificacionProducto;
import java.util.List;

/**
 *
 * @author rodro
 */
public class Main {
    
    public static void main(String args[]) {
//        TreeParser c = new TreeParser();
//        recorrer(c.buildTree(ProxyProducto.getInstance().listarCategorias()));
        
//        ProxyProducto.getInstance().elegirCategoria("Apple");
//        List<DataEspecificacionProducto> productos = ProxyProducto.getInstance().listarProductosCategoria();
//        
//        String prods = "";
//        for (DataEspecificacionProducto p : productos) {
//            prods += "<div class=\"col-lg-4\"><img src=\\\"http://lorempixel.com/140/140/technics/\\\">";
//            prods += "<h2>" + p.getNombre() + "</h2>";
//            prods += "<p>" + p.getNombre() + "</p>";
//            prods += "<p><a class=\"btn btn-default\" href=\"#\" role=\"button\">View details &raquo;</a></p></div>";
//        }
//            
//        System.out.println(prods);
        
        System.out.println(ProxyUsuario.getInstance().listarClientes());
        ProxyUsuario.getInstance().elegirCliente("Dan");
        System.out.println(ProxyUsuario.getInstance().listarOrdenesCliente().get(0).getNroOrden());
    
        
    }
      
    public static String recorrer(List<TreeParser.NodoCategoria> l){
        String arbol = "";
        for (TreeParser.NodoCategoria current : l) {
            if(current.hijos!=null && !current.hijos.isEmpty()){
                arbol += "<li><label class=\"tree-toggler nav-header\">" + current.nombre + "</label>";
                arbol += "<ul class=\"nav-list tree none\">";
                arbol += recorrer(current.hijos);
                arbol += "</ul>";
            }else{
                arbol += "<li><a href=\"#\">" + current.nombre + "</a></li>";
            }
        }
        System.out.println(arbol);
        return arbol;
    }
}
