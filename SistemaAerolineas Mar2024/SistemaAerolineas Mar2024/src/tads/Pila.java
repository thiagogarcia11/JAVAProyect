/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;
import tads.Nodo;
/**
 *
 * @author alumnoFI
 */
public class Pila<T extends Comparable<T>>implements IPila<T> {

    private Nodo<T> inicio;
    private int cantidad;
    private int tope;

    public Pila(int cantMax) {
        this.tope = cantMax;
        inicio = null;
        cantidad = 0;
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

    @Override
    public void apilar(T n) {
        if (!esVacia()) {

            Nodo<T> nuevo = new Nodo(n);

            if (esVacia()) {
                inicio = nuevo;

            } else {
                nuevo.setSiguiente(getInicio());
                inicio = nuevo;
            }

            cantidad++;

        }
    }

    @Override
    public void desapilar() {
        if (!esVacia()) {
            Nodo<T> borrar = inicio;
            if (cantidad == 1) {
                vaciar();
            } else {
                inicio = inicio.getSiguiente();
                borrar.setSiguiente(null); // desengancho el nodo a eliminar  
                cantidad--;

            }
        }
    }

    @Override
    public Nodo cima() {
       return getInicio();        
    }

    @Override
    public void vaciar() {
       cantidad=0;
       inicio=null;
    }

    @Override
    public boolean esVacia() {
       return cantidad==0;        
    }
    
}

