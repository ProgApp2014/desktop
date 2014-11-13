/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
 
 
import controlador.middleware.DataCategoria;
import java.util.List;

/**
 *
 * @author rodro
 */
public class indexControladora {

    private List<DataCategoria> l;
    private String error;
    private List<TreeParser.NodoCategoria> arbol;

   

    public indexControladora() {

        try {
            TreeParser tp = new TreeParser();
            l = ProxyProducto.getInstance().listarCategorias();
            tp.buildTree(l);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

 
     public List<TreeParser.NodoCategoria> getArbol() {
        return arbol;
    }

    public void setArbol(List<TreeParser.NodoCategoria> arbol) {
        this.arbol = arbol;
    }
}
