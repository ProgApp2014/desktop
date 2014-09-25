/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Controlador.DataTypes.DataCategoria;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author rodro
 */
public class TreeParser {

    private ArrayList<NodoCategoria> categoriasAgregadas;

    private Comparator<NodoCategoria> comp = new Comparator<NodoCategoria>() {

        @Override
        public int compare(NodoCategoria o1, NodoCategoria o2) {

            return o1.nombre.compareTo(o2.nombre);

        }
    };

    public TreeParser() {

        categoriasAgregadas = new ArrayList();
    }

    public ArrayList<NodoCategoria> buildTree(List<DataCategoria> l) {
        l.forEach((categoria) -> {

            DataCategoria auxDeBusqueda = categoria;
            NodoCategoria auxHuerfana = null;
            Boolean padresAgregados = false;

            while (!padresAgregados) {

                if (!auxDeBusqueda.tienePadre()) {

                    NodoCategoria nodoRaiz = new NodoCategoria(auxDeBusqueda.getNombre());
                    if (auxHuerfana != null) {
                        nodoRaiz.addHijo(auxHuerfana);
                    }

                    if (!categoriasAgregadas.contains(nodoRaiz)) {

                        categoriasAgregadas.add(nodoRaiz);

                    }
                    auxHuerfana = null;
                    padresAgregados = true;

                } else {
                    NodoCategoria encontrado = getPadre(auxDeBusqueda.getPadre().getNombre());

                    if (encontrado != null) {
                        padresAgregados = true;
                        NodoCategoria newNodo = new NodoCategoria(auxDeBusqueda.getNombre());
                        if (encontrado.find(auxDeBusqueda.getNombre()) == null) {
                            encontrado.addHijo(newNodo);
                        }

                    } else {

                        NodoCategoria padre = new NodoCategoria(auxDeBusqueda.getPadre().getNombre());
                        padre.addHijo(new NodoCategoria(auxDeBusqueda.getNombre()));
                        if (auxDeBusqueda.getPadre().tienePadre()) {
                            auxDeBusqueda = auxDeBusqueda.getPadre().getPadre();
                            auxHuerfana = padre;
                        } else {
                            categoriasAgregadas.add(padre);
                            padresAgregados = true;
                        }
                    }
                }
            }

        });

        categoriasAgregadas.sort(comp);

        return categoriasAgregadas;
    }

  

    public NodoCategoria getPadre(String padre) {
        Boolean found = false;
        NodoCategoria encontrado = null;
        for (Iterator<NodoCategoria> it = (Iterator<NodoCategoria>) categoriasAgregadas.iterator(); it.hasNext() && !found;) {
            NodoCategoria current = it.next();

            encontrado = current.find(padre);

            found = encontrado != null;
        }
        return encontrado;
    }

    public class NodoCategoria {

        public ArrayList<NodoCategoria> hijos;
        public String nombre;

        public NodoCategoria(String nombre) {
            this.hijos = new ArrayList<NodoCategoria>();
            this.nombre = nombre;

        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + Objects.hashCode(this.nombre);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NodoCategoria other = (NodoCategoria) obj;
            if (!Objects.equals(this.nombre, other.nombre)) {
                return false;
            }
            return true;
        }

        public void addHijo(NodoCategoria h) {

            this.hijos.add(h);
            hijos.sort(comp);
        }

        public NodoCategoria find(String nombre) {
            NodoCategoria encontrado = null;
            if (this.nombre.equals(nombre)) {
                return this;

            } else {
                for (Iterator<NodoCategoria> it = hijos.iterator(); it.hasNext() && encontrado == null;) {
                    NodoCategoria current = it.next();
                    encontrado = current.find(nombre);
                }
            }
            return encontrado;
        }

        public String toString() {
            return this.nombre;
        }
    }
}
