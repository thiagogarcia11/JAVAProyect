/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.Lista;

/**
 *
 * @author Usuario
 */
public class Cliente implements Comparable<Cliente>{
    String pasaporte;
    String nombre;
    int edad;
    private Lista<Vuelo> listaVuelo;
    private Lista<Pasaje> listaPasajesCliente;



    public Cliente(String pasaporte) {
        this.pasaporte = pasaporte;
        this.listaVuelo = new Lista<>();
    }

    public Cliente(String pasaporte, String nombre, int edad, Lista<Pasaje> listaPasajesCliente) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.edad = edad;
        this.listaPasajesCliente = listaPasajesCliente;
        this.listaVuelo = new Lista<>();

    }
    

    public Cliente(String pasaporte, String nombre, int edad) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.edad = edad;
        this.listaVuelo = new Lista<>();
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public Lista<Pasaje> getListaPasajesCliente() {
        return listaPasajesCliente;
    }

    public void setListaPasajesCliente(Lista<Pasaje> listaPasajesCliente) {
        this.listaPasajesCliente = listaPasajesCliente;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Lista<Vuelo> getListaVuelo() {
        return listaVuelo;
    }

    public void setListaVuelo(Lista<Vuelo> listaVuelo) {
        this.listaVuelo = listaVuelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
        @Override
    public String toString() {
        return pasaporte + '-' + nombre + '-' + edad;
    }
    
    @Override
    public int compareTo(Cliente otroCliente) {
    return this.pasaporte.compareTo(otroCliente.pasaporte);
    }

}
