/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author alumnoFI
 */
public interface ICola<T extends Comparable<T>> {
    public void encolar(T dato);
    public void desencolar();
    public Nodo<T> frente();
    public boolean esVacia();
    public boolean esLlena();
    public boolean existeElemento(T dato);
}
