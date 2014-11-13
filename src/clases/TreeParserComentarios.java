/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
 
 
import controlador.middleware.DataCliente;
import controlador.middleware.DataComentario;
import controlador.middleware.DataEspecificacionProducto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rodro
 */
public class TreeParserComentarios {

    private ArrayList<NodoComentario> comentariosAgregados;

    private Comparator<NodoComentario> comp = new Comparator<NodoComentario>() {

        @Override
        public int compare(NodoComentario o1, NodoComentario o2) {

            return o1.id.compareTo(o2.id);

        }
    };

    public TreeParserComentarios() {

        comentariosAgregados = new ArrayList();
    }

    public ArrayList<NodoComentario> buildTree(List<DataComentario> l) {
        l.forEach((categoria) -> {

            DataComentario auxDeBusqueda = categoria;
            NodoComentario auxHuerfana = null;
            Boolean padresAgregados = false;

            while (!padresAgregados) {

                if (auxDeBusqueda.getPadre() == null) {

                    NodoComentario nodoRaiz = new NodoComentario(auxDeBusqueda.getId(),auxDeBusqueda.getCliente(), auxDeBusqueda.getEspecificacionProducto(), auxDeBusqueda.getComentario());
                    if (auxHuerfana != null) {
                        nodoRaiz.addHijo(auxHuerfana);
                    }

                    if (!comentariosAgregados.contains(nodoRaiz)) {

                        comentariosAgregados.add(nodoRaiz);

                    }
                    auxHuerfana = null;
                    padresAgregados = true;

                } else {
                    NodoComentario encontrado = getPadre(auxDeBusqueda.getPadre().getId());

                    if (encontrado != null) {
                        padresAgregados = true;
                        NodoComentario newNodo = new NodoComentario(auxDeBusqueda.getId(), auxDeBusqueda.getCliente(), auxDeBusqueda.getEspecificacionProducto(), auxDeBusqueda.getComentario());
                        if (encontrado.find(auxDeBusqueda.getId()) == null) {
                            encontrado.addHijo(newNodo);
                        }

                    } else {
                        DataComentario auxBusqPadre = auxDeBusqueda.getPadre();
                        NodoComentario padre = new NodoComentario(auxBusqPadre.getId(),auxBusqPadre.getCliente(),auxBusqPadre.getEspecificacionProducto(),auxBusqPadre.getComentario());
                        padre.addHijo(new NodoComentario(auxDeBusqueda.getId(),auxDeBusqueda.getCliente(),auxDeBusqueda.getEspecificacionProducto(),auxDeBusqueda.getComentario()));
                        if (auxDeBusqueda.getPadre().getPadre() != null) {
                            auxDeBusqueda = auxDeBusqueda.getPadre().getPadre();
                            auxHuerfana = padre;
                        } else {
                            comentariosAgregados.add(padre);
                            padresAgregados = true;
                        }
                    }
                }
            }

        });

        comentariosAgregados.sort(comp);

        return comentariosAgregados;
    }

  

    public NodoComentario getPadre(Integer padre) {
        Boolean found = false;
        NodoComentario encontrado = null;
        for (Iterator<NodoComentario> it = (Iterator<NodoComentario>) comentariosAgregados.iterator(); it.hasNext() && !found;) {
            NodoComentario current = it.next();

            encontrado = current.find(padre);

            found = encontrado != null;
        }
        return encontrado;
    }

    public class NodoComentario {

        public ArrayList<NodoComentario> hijos;
        public Integer id;
        public DataCliente cliente;
        public DataEspecificacionProducto especificacionProducto;
        public String comentario;

        public NodoComentario(Integer id, DataCliente cliente, DataEspecificacionProducto especificacionProducto, String comentario) {
            this.hijos = new ArrayList<NodoComentario>();
            this.id = id;
            this.cliente = cliente;
            this.especificacionProducto = especificacionProducto;
            this.comentario = comentario;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + Objects.hashCode(this.id);
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
            final NodoComentario other = (NodoComentario) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }

        public void addHijo(NodoComentario h) {

            this.hijos.add(h);
            hijos.sort(comp);
        }

        public NodoComentario find(Integer id) {
            NodoComentario encontrado = null;
            if (this.id.equals(id)) {
                return this;

            } else {
                for (Iterator<NodoComentario> it = hijos.iterator(); it.hasNext() && encontrado == null;) {
                    NodoComentario current = it.next();
                    encontrado = current.find(id);
                }
            }
            return encontrado;
        }

    }
}
