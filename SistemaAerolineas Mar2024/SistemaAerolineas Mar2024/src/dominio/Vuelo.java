/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.Lista;
import tads.Cola;


/**
 *
 * @author Usuario
 */
public class Vuelo implements Comparable<Vuelo> {
    String codVuelo;
    // hay q traer objetos
    Aerolinea aerolinea;
    Avion avion;
    String paisDestino;
    int dia;
    int mes;
    int año;
    int cantPasajesEcon;
    int cantPasajesPClase;
    int cantMaxPasajes;
    public Lista<Pasaje> listaPasajes;
    public Lista<Pasaje> listaPasajesVueloEconomica;
    public Lista<Pasaje> listaPasajesVueloPClase;
    public Cola<Pasaje>  colaPasajesEnEsperaEconomica;
    public Cola<Pasaje>  colaPasajesEnEsperaPClase;


    public Vuelo(String codVuelo, Aerolinea aerolinea, Avion avion, String paisDestino, int dia, int mes, int año, int cantPasajesEcon, int cantPasajesPClase) {
        this.codVuelo = codVuelo;
        this.aerolinea = aerolinea;
        this.avion = avion;
        this.paisDestino = paisDestino;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.cantPasajesEcon = cantPasajesEcon;
        this.cantPasajesPClase = cantPasajesPClase;
        this.listaPasajes = new Lista<>();
        this.listaPasajesVueloEconomica = new Lista<>();
        this.listaPasajesVueloPClase = new Lista<>();
        this.colaPasajesEnEsperaEconomica = new Cola<>();
        this.colaPasajesEnEsperaPClase = new Cola<>();

    }
    
    public int getCantMaxPasajes() {
        return cantMaxPasajes;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setCantMaxPasajes(int cantMaxPasajes) {
        this.cantMaxPasajes = cantMaxPasajes;
    }

    public Vuelo(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    public Lista<Pasaje> getListaPasajes() {
        return listaPasajes;
    }

    public void setListaPasajes(Lista<Pasaje> listaPasajes) {
        this.listaPasajes = listaPasajes;
    }

    public int getTotalPasajes() {
        return totalPasajes;
    }

    public void setTotalPasajes(int totalPasajes) {
        this.totalPasajes = totalPasajes;
    }

    public Lista<Pasaje> getListaPasajesVueloEconomica() {
        return listaPasajesVueloEconomica;
    }

    public void setListaPasajesVueloEconomica(Lista<Pasaje> listaPasajesVueloEconomica) {
        this.listaPasajesVueloEconomica = listaPasajesVueloEconomica;
    }

    public Lista<Pasaje> getListaPasajesVueloPClase() {
        return listaPasajesVueloPClase;
    }

    public void setListaPasajesVueloPClase(Lista<Pasaje> listaPasajesVueloPClase) {
        this.listaPasajesVueloPClase = listaPasajesVueloPClase;
    }

    public Cola<Pasaje> getColaPasajesEnEsperaEconomica() {
        return colaPasajesEnEsperaEconomica;
    }

    public void setColaPasajesEnEsperaEconomica(Cola<Pasaje> colaPasajesEnEsperaEconomica) {
        this.colaPasajesEnEsperaEconomica = colaPasajesEnEsperaEconomica;
    }

    public Cola<Pasaje> getColaPasajesEnEsperaPClase() {
        return colaPasajesEnEsperaPClase;
    }

    public void setColaPasajesEnEsperaPClase(Cola<Pasaje> colaPasajesEnEsperaPClase) {
        this.colaPasajesEnEsperaPClase = colaPasajesEnEsperaPClase;
    }
    


    public String getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    
    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getCantPasajesEcon() {
        return cantPasajesEcon;
    }

    public void setCantPasajesEcon(int cantPasajesEcon) {
        this.cantPasajesEcon = cantPasajesEcon;
    }

    public int getCantPasajesPClase() {
        return cantPasajesPClase;
    }

    public void setCantPasajesPClase(int cantPasajesPClase) {
        this.cantPasajesPClase = cantPasajesPClase;
    }
    
    @Override
    public int compareTo(Vuelo otroVuelo) {
        return this.codVuelo.compareTo(otroVuelo.codVuelo);
    }
    
    int totalPasajes = this.cantMaxPasajes - (cantPasajesPClase + cantPasajesEcon);
    @Override
    public String toString() {
        return codVuelo + '-' + aerolinea.getNombre() + '-' + avion.getCodigo() + "-" + cantPasajesEcon + "-" + cantPasajesPClase + "-" + totalPasajes ;
    }
}
