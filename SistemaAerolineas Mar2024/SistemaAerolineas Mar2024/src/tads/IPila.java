/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;
import tads.Nodo;
/**
 *
 * @author alumnoFI
 */
public interface IPila<T extends Comparable<T>> {
    //pre: El elemento a agregar es un entero
    //post: agrega el elemento al principio de la pila
    public void apilar(T n);
    
    //post: se borra el primer elemento de la pila
    public void desapilar();
    
    //post retorna el primer nodo de la pila 
    public Nodo<T> cima();
    
    //post: se vacia la pila
    public void vaciar();
    
    
    //post: Retorna un boolean indicado si la pila es vacía
    public boolean esVacia();
    
}
