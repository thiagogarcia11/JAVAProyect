/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author Usuario
 */
public interface IListaSimple<T extends Comparable<T>> {
    public void agregarInicio(T dato);
    public void agregarFinal(T dato);
    public boolean existeElemento(T dato);
    public Nodo<T> obtenerElemento(T dato);
    public void vaciar();
    public boolean esVacia();
    public int cantidadElementos();
    public void borrarElemento(String dato);
    public void borrarInicio();
    public void borrarFinal();
    public void mostrar();
    public void agregarOrdenado(T x);
    public int cantidad();
    public String listar();
    public String listarRec();
    public String listarRecInv();
}
