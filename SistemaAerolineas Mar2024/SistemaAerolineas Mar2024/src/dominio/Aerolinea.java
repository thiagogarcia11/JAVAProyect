/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.util.Objects;
import tads.Lista;
import tads.Nodo;
import tads.Pila;

/**
 *
 * @author Usuario
 */
public class Aerolinea implements Comparable<Aerolinea> {
    String nombre;
    String pais;
    int cantidadMaxAviones;
    public Lista<Avion> listaAviones;
    public Lista<Pasaje> listaPasajesDevueltos;



    public Aerolinea(String nombre) {
        this.nombre = nombre;
    }

    public Aerolinea(String nombre, String pais, int cantidadMaxAviones) {
        this.nombre = nombre;
        this.pais = pais;
        this.cantidadMaxAviones = cantidadMaxAviones;
        this.listaAviones = new Lista<>();
        this.listaPasajesDevueltos = new Lista<>();
    }

    public Lista<Pasaje> getListaPasajesDevueltos() {
        return listaPasajesDevueltos;
    }

    public void setListaPasajesDevueltos(Lista<Pasaje> listaPasajesDevueltos) {
        this.listaPasajesDevueltos = listaPasajesDevueltos;
    }
    
    

    public Lista<Avion> getListaAviones() {
        return listaAviones;
    }

    public void setListaAviones(Lista<Avion> listaAviones) {
        this.listaAviones = listaAviones;
    }


    public Lista<Avion> getListaAvion() {
        return listaAviones;
    }

    public void setListaAvion(Lista<Avion> listaAvion) {
        this.listaAviones = listaAvion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCantidadMaxAviones() {
        return cantidadMaxAviones;
    }

    public void setCantidadMaxAviones(int cantidadMaxAviones) {
        this.cantidadMaxAviones = cantidadMaxAviones;
    }
    
    @Override
    public String toString() {
        return nombre + '-' + pais + '-' + cantidadMaxAviones;
    }

    @Override
    public int compareTo(Aerolinea o) {
        return this.nombre.compareTo(o.nombre);
    }
}
