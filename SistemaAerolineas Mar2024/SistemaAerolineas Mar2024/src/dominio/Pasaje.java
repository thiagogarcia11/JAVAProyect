/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Usuario
 */
public class Pasaje implements Comparable<Pasaje> {
    Cliente cliente;
    Vuelo vuelo;
    int categoriaPasaje;
    String status;



    public Pasaje(Cliente cliente, Vuelo vuelo, int categoriaPasaje) {
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.categoriaPasaje = categoriaPasaje;
    }

    public Pasaje(Cliente cliente, Vuelo vuelo) {
        this.cliente = cliente;
        this.vuelo = vuelo;
    }

    public String toString(int listado) {
        if(listado != 1){
            return toString();
        }else{
            return cliente.getPasaporte() + "-"+ vuelo.getCodVuelo();
        }
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    

    public int getCategoriaPasaje() {
        return categoriaPasaje;
    }

    public void setCategoriaPasaje(int categoriaPasaje) {
        this.categoriaPasaje = categoriaPasaje;
    }

    @Override
    public String toString() {
        return vuelo.getCodVuelo() + "-" + status;
    }
    
    @Override
    public int compareTo(Pasaje otroPasaje) {
    return this.cliente.getPasaporte().compareTo(otroPasaje.cliente.getPasaporte());
    }
}
