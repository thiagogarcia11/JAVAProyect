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
public class Avion implements Comparable<Avion> {
    String codigo;
    Aerolinea aerolinea;
    int capacidadMax;
    private Lista<Vuelo> listaVuelos;
    Vuelo vuelo;

    public Avion(String codigo) {
        this.codigo = codigo;
    }
    
    public Avion(String codigo, int capacidadMax, Aerolinea aerolinea) {
        this.codigo = codigo;
        this.aerolinea = aerolinea;
        this.capacidadMax = capacidadMax;
        this.listaVuelos = new Lista<>();
    }


    public Lista<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    public void setListaVuelos(Lista<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    @Override
    public String toString() {
        return codigo + "-" +capacidadMax;
    }
    
    @Override
    public int compareTo(Avion otroAvion) {
        return this.codigo.compareTo(otroAvion.codigo);
    }
}
