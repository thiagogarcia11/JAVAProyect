/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;
import tads.Nodo;

/**
 *
 * @author Usuario
 */
public class Lista<T extends Comparable<T>> implements IListaSimple<T> {
    Nodo<T> inicio;
    int cantidad = 0;
    private T dato;
    private Nodo siguiente;

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Lista() {
        this.inicio = null;
    }
    
    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        nuevo.setSiguiente(inicio);
        cantidad++;
        inicio = nuevo;
    }

    @Override
    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (esVacia()) {
            inicio = nuevo;
            cantidad++;
            return;
        }
        Nodo<T> actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
        cantidad++;
    }
    
        @Override
    public void agregarOrdenado(T x) {
     if (esVacia() || x.compareTo(inicio.getDato()) <0){
         agregarInicio(x);
         cantidad++;
     } 
     else {
       Nodo<T> actual=inicio; 
       //en la primer pregunta del while,cuando es true, nos aseguramos de que tenemos dos nodos al menos
       while (actual.getSiguiente()!= null && actual.getSiguiente().getDato().compareTo(x)<0) {
           actual=actual.getSiguiente();
       }
       Nodo<T> nuevo=new Nodo<T>(x);
       nuevo.setSiguiente(actual.getSiguiente());
       actual.setSiguiente(nuevo);
       cantidad++;

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
    public Nodo<T> obtenerElemento(T dato) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> actual = inicio;
            Nodo<T> resultado = null;
            while (actual != null && resultado == null) {
                if (actual.getDato().compareTo(dato) == 0) {
                    resultado = actual;
                }
                actual = actual.getSiguiente();
            }
            return resultado;
        }
    }


    @Override
    public void vaciar() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public int cantidadElementos() {
        int contador = 0;
        Nodo<T> actual = inicio;
        while (actual != null) {
            contador = contador + 1;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    @Override
    public void borrarElemento(String dato) {
        if (!esVacia()) {
        Nodo<T> actual = inicio;
        if (inicio.getDato().equals(dato)) {
            inicio = inicio.getSiguiente();
            return;
        }
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return; 
            }
            actual = actual.getSiguiente();
        }
    }
    }
    
    @Override
    public void borrarInicio() {
            if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }
    }

    @Override
    public void borrarFinal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrar() {
        Nodo<T> actual = inicio;
        while (actual != null) {
            System.out.println(" - " + actual.getDato().toString());
            actual = actual.getSiguiente();
        }
    }
    
    @Override
    public int cantidad(){
        int contador = 0;
        Nodo<T> actual = inicio;
        while(actual != null){
            contador++;
            actual.getSiguiente();
        }
        return contador;
    }
    
    @Override
    public String listar() {
        String retornoStr = "";
        Nodo<T> actual = this.inicio;
        while (actual != null) {
            retornoStr = retornoStr + actual.getDato().toString() + "|";
            if(actual.getSiguiente()!= null && actual.getSiguiente().getDato() != null){
                retornoStr = retornoStr + "\n";
            }
            actual = actual.getSiguiente();
        }
        return retornoStr;
    }
    
    
        @Override
    public String listarRec() {
        return listarRecursivo(this.inicio);
    }

    private String listarRecursivo(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        }
    
        String retornoStr = nodo.getDato().toString() + "|";
    
        if (nodo.getSiguiente() != null && nodo.getSiguiente().getDato() != null) {
            retornoStr += "\n";
        }
    
        return retornoStr + listarRecursivo(nodo.getSiguiente());
    } 
    
@Override
public String listarRecInv() {
    return listarRecursivoInv(this.inicio).trim(); // Elimina el último salto de línea adicional
}

private String listarRecursivoInv(Nodo<T> nodo) {
    if (nodo == null) {
        return "";
    }

    // Llama recursivamente al siguiente nodo primero para invertir la lista
    String retornoStr = listarRecursivoInv(nodo.getSiguiente());

    // Añade el dato del nodo actual al final del string retornado
    retornoStr += nodo.getDato().toString() + "|\n";

    return retornoStr;
}
}
