package sistemaAutogestion;

import dominio.Aerolinea;
import dominio.Avion;
import dominio.Vuelo;
import dominio.Cliente;
import dominio.Pasaje;
import tads.Cola;
import tads.Nodo;
import tads.Pila;


import tads.Lista;

public class Sistema implements IObligatorio {
    private Lista<Aerolinea> listaAerolinea = new Lista<>();
    private Lista<Cliente> listaCliente = new Lista<>();
    private Lista<Vuelo> listaVuelos = new Lista<>();
    private Lista<Avion> listaAviones = new Lista<>();
    private Lista<Pasaje> listaPasajes = new Lista<>();


    
    //PRE: Las listas estan vacias.
    //POST: se inicializan las listas, se crea el sistema de gestion y se retorna ok
    @Override
    public Retorno crearSistemaDeGestion() {
           listaAerolinea = new Lista<>();
           listaCliente = new Lista<>();
           listaVuelos = new Lista<>();
           listaPasajes = new Lista<>();
           listaAviones = new Lista<>();

           return Retorno.ok();
    }

    //PRE: No puede haber una aerolinea creada con el mismo nombre.
    //     nombre debe ser único, no puede ser vacío y cantMaxAviones > 0.
    //     Si falla alguno de los datos, se retorna error
    //POST: Se crea una nueva Aerolinea exitosamente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    //      Se agrega Ordenado
    @Override
    public Retorno crearAerolinea(String nombre, String pais, int cantMaxAviones) {
        Aerolinea newA = new Aerolinea(nombre, pais, cantMaxAviones);
        if(nombre.trim().equals("")|| cantMaxAviones <= 0){
            return Retorno.error2();
        } else {
            if(listaAerolinea.cantidadElementos() > 0){
            if (listaAerolinea.existeElemento(newA)) {
                return Retorno.error1();
            }
        }
        listaAerolinea.agregarOrdenado(newA);
        return Retorno.ok();
        }
    }
    

    //PRE: Se recibe un nombre con el cual se eliminar esa aerolinea.
    //     La lista de aviones de dicha aerolinea debe estar vacía.
    //     nombre debe ser un string no vacío.
    //POST: Se elimina la Aerolinea exitosamente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    @Override
    public Retorno eliminarAerolinea(String nombre) {
        Aerolinea newA = new Aerolinea(nombre);
        if (!listaAerolinea.existeElemento(newA)) {
            return Retorno.error1();
        } else {
            Aerolinea aerolinea = listaAerolinea.obtenerElemento(newA).getDato(); // Obtener el objeto Aerolinea
            if (aerolinea == null) {
                return Retorno.error1();
            } else {
                Lista<Avion> aviones = aerolinea.getListaAvion();
                if (aviones == null || aviones.esVacia()) { // Verificar si la lista de aviones es nula o vacía
                    listaAerolinea.borrarElemento(newA.getNombre()); // Eliminar la aerolínea de la lista
                    return Retorno.ok();
                } else {
                    return Retorno.error2(); // Si la aerolínea tiene aviones, retornar error
                }
            }
        }
    }
    
    
    
    //PRE: Se recibe por parametro un nombre, capacidad del avion y nombre de la aerolinea
    //     El codigo de avion no puede existir en la aerolinea
    //     La capacidad maxima debe ser mayor o igual a 9 y debe ser multiplo de 3
    //     La aerolinea debe existir en la lista
    //     Al registrar el avion no se debe superar la capacidad maxima establecida por la aerolinea
    //POST: Se registra un avion con exito en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    //      Se agrega ordenado para mostrar por ordenacion a la hora de Listar los Aviones de una Aerolinea

    @Override
    public Retorno registrarAvion(String codigo, int capacidadMax, String nomAerolinea) {
        Aerolinea aerolinea = new Aerolinea(nomAerolinea);
        Avion newAv = new Avion(codigo, capacidadMax, aerolinea);

        // Obtener la aerolínea de la lista
        Nodo<Aerolinea> nodoAerolinea = listaAerolinea.obtenerElemento(aerolinea);
        if (nodoAerolinea != null) {
            aerolinea = nodoAerolinea.getDato();
        } else {
            // Si la aerolínea no existe en la lista, retornar un error
            return Retorno.error3();
        }
        if (capacidadMax < 9 || capacidadMax % 3 != 0) {
            return Retorno.error2();
        }

        Lista<Avion> aviones = aerolinea.getListaAvion();
        if(aviones == null){
            aviones = new Lista<>();
            aerolinea.setListaAvion(aviones);
            aviones.agregarOrdenado(newAv);
            listaAviones.agregarOrdenado(newAv);
            return Retorno.ok();
        }
        
        if (!aviones.existeElemento(newAv)) {

        if (aviones.cantidadElementos() >= aerolinea.getCantidadMaxAviones()) {
            return Retorno.error4();
        }
            aviones.agregarOrdenado(newAv);
            listaAviones.agregarOrdenado(newAv);
            return Retorno.ok();
        }else{
            return Retorno.error1();
        }
    }

    //PRE: Se recibe por parametro un nombre de aerolinea y un codigo de avion
    //     La aerolinea debe existir para poder eliminar el avion
    //     El codigo de avion debe existir dentro de la aerolinea
    //     El avion no puede tener pasajes vendidos para poder eliminarlo
    //POST:Se elimina el avion de la aerolinea correspondiente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
@Override
    public Retorno eliminarAvion(String nomAerolinea, String codAvion) {
        Aerolinea aerolinea = new Aerolinea(nomAerolinea);
        Nodo<Aerolinea> nodoAerolinea = listaAerolinea.obtenerElemento(aerolinea);
        if (nodoAerolinea == null) {
            return Retorno.error1(); // Aerolínea no encontrada
        }
        Lista<Avion> aviones = nodoAerolinea.getDato().getListaAvion();
        if (aviones == null || aviones.cantidadElementos() == 0) {
            return Retorno.error2(); // No hay aviones en la aerolínea
        }

        Nodo<Avion> nodoAvion = aviones.getInicio();
        while (nodoAvion != null) {
            Avion avion = nodoAvion.getDato();
            // Se busca el avión por código de avión
            if (avion.getCodigo().equals(codAvion)) {
                boolean tienePasajesVendidos = false;

                // Verificar todos los vuelos del avión
                Nodo<Vuelo> nodoVuelo = listaVuelos.getInicio();
                while (nodoVuelo != null) {
                    Vuelo vuelo = nodoVuelo.getDato();
                    if (vuelo.getAvion().equals(avion)) {
                        if (vuelo.getListaPasajes() != null && !vuelo.getListaPasajes().esVacia()) {
                            tienePasajesVendidos = true;
                            break;
                        }
                    }
                    nodoVuelo = nodoVuelo.getSiguiente();
                }

                if (tienePasajesVendidos) {
                    return Retorno.error3(); // El avión tiene pasajes vendidos
                } else {
                    // No tiene pasajes vendidos, eliminar el avión
                    aviones.borrarElemento(codAvion);
                    return Retorno.ok();
                }
            }
            nodoAvion = nodoAvion.getSiguiente();
        }
        return Retorno.error2(); // Avión no encontrado en la aerolínea
    }
    
//PRE: Se solicita listar las Aerolineas alfabeticamente con formato: “Aerolíneas Argentinas-Argentina-2|\nAmerican Airlains-Estados Unidos-5|\nGol-Brasil-7|”
//POST: Se listan las aerolineas exitosamente
    @Override
    public Retorno listarAerolineas() {
        String listaAerolineas = listaAerolinea.listar();
        return Retorno.ok(listaAerolineas);
    }
    
//PRE: Se solicita listar los Aviones de la lista de Aerolineas con el nombre otorgado en formato : “AA345-12|\n AA211-9|\nGF322-15|”
//     Debe existir una aerolinea con el nombre dado, en caso contrario se muestra un error
//POST:Se lista la lista de Aerolineas exitosamente en caso de existir un aerolinea con el nombre dado, en caso contrario se muestra un error
    @Override
    public Retorno listarAvionesDeAerolinea(String nombre) {
        Aerolinea aero = new Aerolinea(nombre);

        if (listaAerolinea.existeElemento(aero)) {
             Aerolinea aerolineaExistente = listaAerolinea.obtenerElemento(aero).getDato();
             String aviones = aerolineaExistente.getListaAvion().listar();
            return Retorno.ok(aviones);
        }
        return Retorno.error1();
    }
     
//     PRE: Pasaporte debe ser un código alfanumérico único de 7 caracteres.
//	  La edad debe ser mayor o igual a 0.
//     POST: Se registra un nuevo cliente, con pasaporte único.
//	   Si falla alguno de los datos, se retorna error.
//      Se agrega al inicio para el listado
    @Override
    public Retorno registrarCliente(String pasaporte, String nombre, int edad) {
        Cliente cli = new Cliente(pasaporte, nombre, edad);
        if (edad <= 0){
            return Retorno.error1();
        }
        if (pasaporte.length() != 7){
            return Retorno.error2();
        }
        if(listaCliente.existeElemento(cli)){
            return Retorno.error3();
        }
        
        listaCliente.agregarInicio(cli);
        return Retorno.ok();
    }

    //PRE: Vuelo debe salir en un único horario.
    //	  Cada categoría de pasaje debe ser >= 3 y múltiplo de 3.
    //	  Si la suma de ambas categorías no cubra el total de pasajes disponibles, se completa el vuelo con pasajes de categoría ecnoómica.
    //	  La suma de ambas categorías no debe superar la cantidad máxima permitida.
    //	  El código de vuelo debe ser único.
    //	  Debe existir en el sistema la aerolinea ingresada.
    //	  Debe existir el codigo de avion en esa aerolinea.
    //	  No pueden existir más de un vuelo creado para ese avión en esa misma fecha.
    //     POST: Se crea un nuevo vuelo en el sistema, dentro de una aerolinea y avión específicos.
    //	   Si falla alguno de los datos, se retorna error.
    //     Se agrega ordenado para las listas
@Override
public Retorno crearVuelo(String codigoVuelo, String aerolinea, String codAvion, String paisDestino, int dia, int mes, int año, int cantPasajesEcon, int cantPasajesPClase) {
    // Obtener la instancia existente de Aerolinea
    Aerolinea aero = new Aerolinea(aerolinea);
    if (!listaAerolinea.existeElemento(aero)) {
        return Retorno.error2();
    }
    if (listaAerolinea.obtenerElemento(aero) == null) {
        return Retorno.error3();
    }

    Aerolinea aerolineaEncontrada = listaAerolinea.obtenerElemento(aero).getDato();

    Avion avi = new Avion(codAvion);
    if (listaAviones.obtenerElemento(avi) == null) {
        return Retorno.error3();
    }
    Avion avionEncontrado = listaAviones.obtenerElemento(avi).getDato();

    Vuelo vue = new Vuelo(codigoVuelo, aerolineaEncontrada, avionEncontrado, paisDestino, dia, mes, año, cantPasajesEcon, cantPasajesPClase);
    


    if (listaVuelos.existeElemento(vue)) {
        return Retorno.error1();
    }

    if (aerolineaEncontrada.getListaAvion() == null || aerolineaEncontrada.getListaAvion().esVacia()) {
        return Retorno.error3();
    }

    // Verificar si el avión está registrado en la aerolínea
    if (!aerolineaEncontrada.getListaAvion().existeElemento(avionEncontrado)) {
        return Retorno.error3();
    }

    if (cantPasajesEcon % 3 != 0 || cantPasajesPClase % 3 != 0) {
        return Retorno.error5();
    }

    int totalPasajes = cantPasajesEcon + cantPasajesPClase;
    int capacidadMax = avionEncontrado.getCapacidadMax();

    if (capacidadMax < totalPasajes) {
        return Retorno.error6();
    }

    vue.setCantPasajesEcon(cantPasajesEcon);
    vue.setCantPasajesPClase(cantPasajesPClase);
    vue.setCantMaxPasajes(totalPasajes);
    
    if(tieneFecha(avionEncontrado.getCodigo(), año, mes, dia)){
        return Retorno.error4();
    }
    
    listaVuelos.agregarOrdenado(vue);
    return Retorno.ok();
}

//     PRE: Debe existir disponibilidad para la categoría de pasaje elegida.
//	  El pasaporte del cliente debe existir dentro del sistema.
//	  El código de vuelo debe existir en el sistema.
//     POST: En caso de existir disponibilidad de pasajes, se emite el pasaje para el cliente ingresado.
//	   En caso de no existir disponibilidad de pasajes, la emision queda en estado pendiente.
//	   Si falla alguno de los datos, se retorna error.
//          Se agrega al inicio para el listado y se encola para las listas de espera cuando se excede la capacidad
@Override
public Retorno comprarPasaje(String pasaporteCliente, String codigoVuelo, int categoriaPasaje) {
    Cliente cliente = new Cliente(pasaporteCliente);
    if(listaCliente.obtenerElemento(cliente) == null){
        return Retorno.error1();
    }
    Cliente clienteEncontrado = listaCliente.obtenerElemento(cliente).getDato();
    Vuelo vuelo = new Vuelo(codigoVuelo);
    if(listaVuelos.obtenerElemento(vuelo) == null){
        return Retorno.error2();
    }
    Vuelo vueloEncontrado = listaVuelos.obtenerElemento(vuelo).getDato();
    Pasaje pasaje = new Pasaje(clienteEncontrado, vueloEncontrado, categoriaPasaje);

    if (listaCliente.esVacia()) {
        return Retorno.error1(); // No hay clientes
    }
    if (listaVuelos.esVacia()) {
        return Retorno.error2(); // No hay vuelos
    }

    if (!listaCliente.existeElemento(cliente)) {
        return Retorno.error1(); // Cliente no encontrado
    }
    if (!listaVuelos.existeElemento(vuelo)) {
        return Retorno.error2(); // Vuelo no encontrado
    }


    Lista<Pasaje> pasajesCliente = clienteEncontrado.getListaPasajesCliente();

    if (categoriaPasaje == 1) { // Clase Económica
        Lista<Pasaje> listaPasajesEconomica = vueloEncontrado.getListaPasajesVueloEconomica();
        Cola<Pasaje> colaPasajesEconomica = vueloEncontrado.getColaPasajesEnEsperaEconomica();
        int capacidadEcon = vueloEncontrado.getCantMaxPasajes() - vueloEncontrado.getCantPasajesPClase();
        int listaEcon = vueloEncontrado.getListaPasajesVueloEconomica().cantidadElementos() + 1;
        if (capacidadEcon <= listaEcon) {
            colaPasajesEconomica.encolar(pasaje);
            return Retorno.ok();
        } else {
            listaPasajesEconomica.agregarInicio(pasaje);
            vueloEncontrado.getListaPasajes().agregarInicio(pasaje);
            if(pasajesCliente == null){
                pasajesCliente = new Lista<>();
                clienteEncontrado.setListaPasajesCliente(pasajesCliente);
            }
            pasajesCliente.agregarInicio(pasaje);
            listaPasajes.agregarInicio(pasaje);
            pasaje.setStatus("CPR");
            return Retorno.ok();
        }
    } else if (categoriaPasaje == 2) { // Primera Clase
        Lista<Pasaje> listaPasajesPClase = vueloEncontrado.getListaPasajesVueloPClase();
        Cola<Pasaje> colaPasajesPClase = vueloEncontrado.getColaPasajesEnEsperaPClase();
        int capacidadPClase = vueloEncontrado.getCantMaxPasajes() - vueloEncontrado.getCantPasajesEcon();
        int listaEcon = vueloEncontrado.getListaPasajesVueloPClase().cantidadElementos() + 1;
        if (capacidadPClase <= listaEcon) {
            colaPasajesPClase.encolar(pasaje);
            return Retorno.ok();
        } else {
            listaPasajesPClase.agregarInicio(pasaje);
            vueloEncontrado.getListaPasajes().agregarInicio(pasaje);
            if (pasajesCliente == null) {
                pasajesCliente = new Lista<>();
                clienteEncontrado.setListaPasajesCliente(pasajesCliente);
            }
            pasajesCliente.agregarInicio(pasaje);
            listaPasajes.agregarInicio(pasaje);
            pasaje.setStatus("CPR");
            return Retorno.ok();
        }
    }
    return Retorno.noImplementada();
}


//    PRE: Debe existir clientes en la lista de espera.
//	  El pasaporte del cliente debe existir dentro del sistema.
// 	  El código de vuelo debe existir en el sistema.
//	  El cliente debe tener una compra para dicho vuelo.
//     POST: Se realizó la devolución del pasaje al cliente ingresado.
//	   Si falla alguno de los datos, se retorna error.
//         Se devuelve el pasaje y se agrega al Final a la lista de pasajes devuelto de aerolinea para el listado y al inicio al resto de las listas
    @Override
    public Retorno devolverPasaje(String pasaporteCliente, String codigoVuelo) {
        Cliente cliente = new Cliente(pasaporteCliente);
        Vuelo vuelo = new Vuelo(codigoVuelo);
        Pasaje pasaje = new Pasaje(cliente, vuelo);

        if (!listaCliente.existeElemento(cliente)) {
            return Retorno.error1();
        }

        if (!listaVuelos.existeElemento(vuelo)) {
            return Retorno.error2();
        }
        Vuelo vueloEncontrado1 = listaVuelos.obtenerElemento(vuelo).getDato();

        Avion avion = new Avion(vueloEncontrado1.getAvion().getCodigo());
        Avion avionEncontrado = listaAviones.obtenerElemento(avion).getDato();
        Aerolinea aerolinea = new Aerolinea(avionEncontrado.getAerolinea().getNombre());
        Nodo<Vuelo> nodoVuelo = listaVuelos.obtenerElemento(vuelo);
        Vuelo vueloEncontrado = nodoVuelo.getDato();
        Nodo<Aerolinea> nodoAerolinea = listaAerolinea.obtenerElemento(aerolinea);
        Aerolinea aerolineaEncontrada = nodoAerolinea.getDato();
        Lista<Pasaje> pasajesDevAerolinea = aerolineaEncontrada.getListaPasajesDevueltos();
        Cliente clienteEncontrado = listaCliente.obtenerElemento(cliente).getDato();
        Lista<Pasaje> pasajesCliente = clienteEncontrado.getListaPasajesCliente();

        boolean encontradoPasajeEcon = vueloEncontrado.getListaPasajesVueloEconomica().existeElemento(pasaje);
        boolean encontradoPasajePClase = vueloEncontrado.getListaPasajesVueloPClase().existeElemento(pasaje);

        Cola<Pasaje> colaEcon = vueloEncontrado.getColaPasajesEnEsperaEconomica();
        Cola<Pasaje> colaPClase = vueloEncontrado.getColaPasajesEnEsperaPClase();

        if (encontradoPasajeEcon) {
            pasajesDevAerolinea.agregarFinal(pasaje);
            vueloEncontrado.getListaPasajesVueloEconomica().borrarElemento(codigoVuelo);
            vueloEncontrado.getListaPasajes().borrarElemento(codigoVuelo);
            pasaje.setStatus("DEV");
            pasajesCliente.agregarInicio(pasaje);
            listaPasajes.agregarInicio(pasaje);
            if (colaEcon != null && !colaEcon.esVacia()) {
                colaEcon.desencolar();
                Pasaje pasajeEconEspera = colaEcon.getInicio().getDato();
                vueloEncontrado.getListaPasajes().agregarInicio(pasajeEconEspera);
                vueloEncontrado.getListaPasajesVueloEconomica().agregarInicio(pasajeEconEspera);
                pasajesCliente.agregarInicio(pasaje);
                listaPasajes.agregarInicio(pasaje);
            }
            return Retorno.ok();

        } else if (encontradoPasajePClase) {
            pasajesDevAerolinea.agregarFinal(pasaje);
            vueloEncontrado.getListaPasajesVueloPClase().borrarElemento(codigoVuelo);
            vueloEncontrado.getListaPasajes().borrarElemento(codigoVuelo);
            pasaje.setStatus("DEV");
            pasajesCliente.agregarInicio(pasaje);
            listaPasajes.agregarInicio(pasaje);
            if (colaPClase != null && !colaEcon.esVacia()) {
                colaEcon.desencolar();
                Pasaje pasajeEconEspera = colaEcon.getInicio().getDato();
                vueloEncontrado.getListaPasajes().agregarInicio(pasajeEconEspera);
                vueloEncontrado.getListaPasajesVueloPClase().agregarInicio(pasajeEconEspera);
                pasajesCliente.agregarInicio(pasaje);
                listaPasajes.agregarInicio(pasaje);
            }
            return Retorno.ok();
        } else {
            return Retorno.error3();
        }
    }

// PRE: Debe haber al menos un cliente registrado en el sistema para que se pueda listar.
//	   Los datos de los clientes deben estar correctamente almacenados en el formato esperado.
//     POST: La lista de clientes debe devolverse en el orden inverso al de su registro, es decir, el último registrado debe mostrarse primero.
    @Override
    public Retorno listarClientes() {
        String listarCliente = listaCliente.listarRec();
        return Retorno.ok(listarCliente);
    }
// PRE: Debe haber al menos un vuelo registrado en el sistema para que se pueda listar.
//	  Los datos de los vuelos deben estar correctamente almacenados en el formato esperado.
//     POST: La lista de vuelos debe devolverse con los detalles especificados.
    @Override
    public Retorno listarVuelos() {
        String listarVuelos = listaVuelos.listar();
        return Retorno.ok(listarVuelos);
    }
    
//    PRE: El cliente con el pasaporte proporcionado debe existir en el sistema.
//    	  Los registros de compras y devoluciones de pasajes del cliente deben estar correctamente almacenados.
//    	  Pasaporte debe ser una cadena no nula y válida que identifique a un cliente.
//     POST: La lista de vuelos debe devolverse con los detalles especificados: código de vuelo y el estado ("CPR" para comprado y "DEV" para devuelto).
//	   Los últimos pasajes comprados deben mostrarse al inicio del reporte.
//	   El resultado debe estar en el formato especificado: “CódigoVuelo-Estado|”.
@Override
public Retorno vuelosDeCliente(String pasaporte) {
    Cliente cliente = new Cliente(pasaporte);
    if (!listaCliente.existeElemento(cliente)) {
        return Retorno.error1(); // Cliente no encontrado
    }
    
    Cliente clienteEncontrado = listaCliente.obtenerElemento(cliente).getDato();
    Lista<Pasaje> pasajesCliente = clienteEncontrado.getListaPasajesCliente();
    
    if (pasajesCliente == null || pasajesCliente.esVacia()) {
        return Retorno.error2(); // Lista de pasajes del cliente está vacía
    }
    
    String listarPasajes = pasajesCliente.listarRecInv(); // Listar los pasajes del cliente
    return Retorno.ok(listarPasajes);
}

//PRE: La aerolínea con el nombre proporcionado debe existir en el sistema.
//	  Los registros de pasajes devueltos deben estar correctamente almacenados
//	  nombreAerolinea debe ser una cadena no nula y válida que identifique a una aerolínea.
//     POST: La lista de pasajes devueltos debe devolverse con los detalles especificados: pasaporte del cliente y el código de vuelo.
//	   El resultado debe estar en el formato especificado: “Pasaporte- CódigoVuelo|”.
//	   Si falla alguno de los datos, se retorna error.
@Override
public Retorno pasajesDevueltos(String nombreAerolinea) {
    Aerolinea aerolinea = new Aerolinea(nombreAerolinea);
    
    if (!listaAerolinea.existeElemento(aerolinea)) {
        return Retorno.error1();
    }

    Aerolinea aerolineaEncontrada = listaAerolinea.obtenerElemento(aerolinea).getDato();
    Lista<Pasaje> pasajesDevAerolinea = aerolineaEncontrada.getListaPasajesDevueltos();
    if(pasajesDevAerolinea == null || pasajesDevAerolinea.esVacia()){
        return Retorno.error2();
    }
    String listarPasajesDev = "";
    Nodo<Pasaje> nodoActual = pasajesDevAerolinea.getInicio();

    while (nodoActual != null) {
        Pasaje pasaje = nodoActual.getDato();
        if (pasaje != null) {
            listarPasajesDev += pasaje.toString(1) + "|";
            if (nodoActual.getSiguiente() != null && nodoActual.getSiguiente().getDato() != null) {
            listarPasajesDev +=   "\n";
        }
        }
        nodoActual = nodoActual.getSiguiente();
    }
    return Retorno.ok(listarPasajesDev);
}
// PRE: El vuelo con el código proporcionado debe existir en el sistema.
//	  Los registros de ventas de pasajes deben estar correctamente almacenados.
//	  codigoVuelo debe ser una cadena no nula y válida que identifique un vuelo.
//     POST: El resultado debe estar en el formato especificado, con filas de 3 asientos cada uno y separaciones claras entre las categorías.
//	   Si falla alguno de los datos, se retorna error.
@Override
    public Retorno vistaDeVuelo(String codigoVuelo) {
        Vuelo vuelo = new Vuelo(codigoVuelo);

        if (!listaVuelos.existeElemento(vuelo)) {
            return Retorno.error2(); // Vuelo no encontrado
        }

        Vuelo vueloEncontrado = listaVuelos.obtenerElemento(vuelo).getDato();

        int capacidadMax = vueloEncontrado.getAvion().getCapacidadMax();
        int filas = capacidadMax / 3;
        String[][] matrizAsientos = new String[filas][3];

        // Inicializa la matriz con "XXXXXXXXX"
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {
                matrizAsientos[i][j] = "XXXXXXX";
            }
        }

        // Llena la matriz con los asientos vendidos
        Lista<Pasaje> pasajes = vueloEncontrado.getListaPasajes();
        
        Nodo<Pasaje> nodoPasaje = pasajes.getInicio();
        int posicionPrimera = 0;
        int posicionEconomica = vueloEncontrado.getCantPasajesPClase();
        
        while (nodoPasaje != null) {
            Pasaje pasaje = nodoPasaje.getDato();
            int fila;
            int columna;
            if (pasaje.getCategoriaPasaje() == 2) { // Primera clase
                fila = posicionPrimera / 3;
                columna = posicionPrimera % 3;
                posicionPrimera++;
            } else { // Clase económica
                fila = posicionEconomica / 3;
                columna = posicionEconomica % 3;
                posicionEconomica++;
            }

            matrizAsientos[fila][columna] = pasaje.getCliente().getPasaporte();
            nodoPasaje = nodoPasaje.getSiguiente();
        }

        // Construye la cadena de retorno
        StringBuilder resultado = new StringBuilder();
        resultado.append("*******************************\n");
        resultado.append("     *      PRIMERA      *      \n");
        resultado.append("*******************************\n");
        for (int i = 0; i < filas; i++) {
            if (i == vueloEncontrado.getCantPasajesPClase() / 3) {
                resultado.append("*******************************\n");
                resultado.append("     *     ECONÓMICA     *\n");
                resultado.append("*******************************\n");
            }
            for (int j = 0; j < 3; j++) {
                resultado.append("* ").append(matrizAsientos[i][j]).append(" ");
            }
            resultado.append("*\n");
        }
        resultado.append("*******************************\n");
        return Retorno.ok(resultado.toString());
    }
    
    
    // METODOS AUXILIARES
    public boolean tieneFecha(String codAvion, int año, int mes, int dia) {
        Nodo<Vuelo> actual = listaVuelos.getInicio();
        while (actual != null) {
            Vuelo vuelo = actual.getDato();
            if (vuelo.getAvion().getCodigo() == codAvion
                    && vuelo.getAño() == año
                    && vuelo.getMes() == mes
                    && vuelo.getDia() == dia) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
}
