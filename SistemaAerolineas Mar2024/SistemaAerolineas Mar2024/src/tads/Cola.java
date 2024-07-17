/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;
import tads.Lista;

/**
 *
 * @author alumnoFI
 */
public class Cola<T extends Comparable<T>> implements ICola<T> {

    private Nodo<T> inicio;
    private int cantidad;
    private int tope;

    public Cola() {
        this.inicio = null;
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public Cola(int cantMax) {
        this.tope = cantMax;
        this.cantidad = 0;
        this.inicio = null;
    }

    @Override
    public void encolar(T dato) {//agregar al final
        if (!esLlena()) {
            Nodo<T> pactual = this.inicio;
            Nodo<T> nuevo = new Nodo<T>(dato);
            if (esVacia()) {
                inicio = nuevo;
                cantidad++;
            } else {
                while (pactual.getSiguiente() != null) {
                    pactual = pactual.getSiguiente();
                }
                pactual.setSiguiente(nuevo);
                cantidad++;
            }
        }
    }
    
        @Override
    public boolean existeElemento(T dato) {
        boolean existe = false;
        if (!esVacia()) {
            Nodo<T> actual = inicio;

            while (actual != null && !existe) {
                if (actual.getDato().compareTo(dato)==0) {
                    existe = true;
                } else {
                    actual = actual.getSiguiente();
                }
            }
        }
        return existe;
    }


    @Override
    public void desencolar() {
        if (!esVacia()) {
            this.inicio = this.inicio.getSiguiente();
            cantidad--;
        }
    }

    @Override
    public Nodo<T> frente() {
        return inicio;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public boolean esLlena() {
        return cantidad == tope;
    }

//    @Override
//    public void eliminarRepetidos(Cola<T> c) {
//        Lista<Integer> aux = new Lista<Integer>((Integer) 10);
//        aux.setInicio(c.frente());
//        while (!c.esVacia()) {
//            if (!aux.estaElemento((Integer) c.frente().getDato())) {
//                aux.agregarFinal((Integer) c.frente().getDato());
//            }
//            c.desencolar();
//        }
//        Nodo<Integer> actual = aux.getInicio();
//        while (actual != null) {
//            Integer dato = actual.getDato();
//            c.encolar((T) dato);
//            actual=actual.getSiguiente();
//        }
//    }

}