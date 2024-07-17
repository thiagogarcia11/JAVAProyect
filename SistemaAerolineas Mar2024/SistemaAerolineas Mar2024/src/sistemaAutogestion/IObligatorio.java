package sistemaAutogestion;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    //PRE: Las listas estan vacias.
    //POST: se inicializan las listas, se crea el sistema de gestion y se retorna ok
    public Retorno crearSistemaDeGestion();;
    //PRE: No puede haber una aerolinea creada con el mismo nombre.
    //     nombre debe ser único, no puede ser vacío y cantMaxAviones > 0.
    //     Si falla alguno de los datos, se retorna error
    //POST: Se crea una nueva Aerolinea exitosamente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    public Retorno crearAerolinea(String nombre, String pais, int cantMaxAviones); 
    //PRE: Se recibe un nombre con el cual se eliminar esa aerolinea.
    //     La lista de aviones de dicha aerolinea debe estar vacía.
    //     nombre debe ser un string no vacío.
    //POST: Se elimina la Aerolinea exitosamente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    public  Retorno eliminarAerolinea(String nombre);; 
    //PRE: Se recibe por parametro un nombre, capacidad del avion y nombre de la aerolinea
    //     El codigo de avion no puede existir en la aerolinea
    //     La capacidad maxima debe ser mayor o igual a 9 y debe ser multiplo de 3
    //     La aerolinea debe existir en la lista
    //     Al registrar el avion no se debe superar la capacidad maxima establecida por la aerolinea
    //POST: Se registra un avion con exito en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    public Retorno registrarAvion(String codigo, int capacidadMax, String nomAerolinea);; 
        //PRE: Se recibe por parametro un nombre de aerolinea y un codigo de avion
    //     La aerolinea debe existir para poder eliminar el avion
    //     El codigo de avion debe existir dentro de la aerolinea
    //     El avion no puede tener pasajes vendidos para poder eliminarlo
    //POST:Se elimina el avion de la aerolinea correspondiente en caso de cumplirse todas las condiciones, de caso contrario
    //      se devuelve un error correspondiente al fallo.
    public Retorno eliminarAvion(String nomAerolinea, String codAvion); 
//    PRE: Pasaporte debe ser un código alfanumérico único de 7 caracteres.
//	  La edad debe ser mayor o igual a 0.
//     POST: Se registra un nuevo cliente, con pasaporte único.
//	   Si falla alguno de los datos, se retorna error.
    public Retorno registrarCliente(String pasaporte, String nombre, int edad);
    //pre:      post:
    
      /*
    **************** GESTIÓN DE VUELOS Y PASAJES **************************************
    */
    
//    PRE: Vuelo debe salir en un único horario.
//	  Cada categoría de pasaje debe ser >= 3 y múltiplo de 3.
//	  Si la suma de ambas categorías no cubra el total de pasajes disponibles, se completa el vuelo con pasajes de categoría ecnoómica.
//	  La suma de ambas categorías no debe superar la cantidad máxima permitida.
//	  El código de vuelo debe ser único.
//	  Debe existir en el sistema la aerolinea ingresada.
//	  Debe existir el codigo de avion en esa aerolinea.
//	  No pueden existir más de un vuelo creado para ese avión en esa misma fecha.
//     POST: Se crea un nuevo vuelo en el sistema, dentro de una aerolinea y avión específicos.
//	   Si falla alguno de los datos, se retorna error.
    public Retorno crearVuelo(String codigoVuelo, String aerolinea, String codAvion, String paisDestino, int dia, int mes, int año, int cantPasajesEcon, int cantPasajesPClase);
    
//    PRE: Debe existir disponibilidad para la categoría de pasaje elegida.
//	  El pasaporte del cliente debe existir dentro del sistema.
//	  El código de vuelo debe existir en el sistema.
//     POST: En caso de existir disponibilidad de pasajes, se emite el pasaje para el cliente ingresado.
//	   En caso de no existir disponibilidad de pasajes, la emision queda en estado pendiente.
//	   Si falla alguno de los datos, se retorna error.
    public Retorno comprarPasaje(String pasaporteCliente, String codigoVuelo, int categoríaPasaje);
//    PRE: Debe existir clientes en la lista de espera.
//	  El pasaporte del cliente debe existir dentro del sistema.
// 	  El código de vuelo debe existir en el sistema.
//	  El cliente debe tener una compra para dicho vuelo.
//     POST: Se realizó la devolución del pasaje al cliente ingresado.
//	   Si falla alguno de los datos, se retorna error.
    public Retorno devolverPasaje(String pasaporteCliente, String codigoVuelo);

     /*
    **************** REPORTES Y CONSULTAS **************************************
    */
    //PRE: Se solicita listar las Aerolineas alfabeticamente con formato: “Aerolíneas Argentinas-Argentina-2|\nAmerican Airlains-Estados Unidos-5|\nGol-Brasil-7|”
    //POST: Se listan las aerolineas exitosamente
    public Retorno listarAerolineas();
    //PRE: Se solicita listar los Aviones de la lista de Aerolineas con el nombre otorgado en formato : “AA345-12|\n AA211-9|\nGF322-15|”
    //     Debe existir una aerolinea con el nombre dado, en caso contrario se muestra un error
    //POST:Se lista la lista de Aerolineas exitosamente en caso de existir un aerolinea con el nombre dado, en caso contrario se muestra un error
    public Retorno listarAvionesDeAerolinea(String nombre);
//    PRE: Debe haber al menos un cliente registrado en el sistema para que se pueda listar.
//	   Los datos de los clientes deben estar correctamente almacenados en el formato esperado.
//     POST: La lista de clientes debe devolverse en el orden inverso al de su registro, es decir, el último registrado debe mostrarse primero.
    public Retorno listarClientes();
//     PRE: Debe haber al menos un vuelo registrado en el sistema para que se pueda listar.
//	  Los datos de los vuelos deben estar correctamente almacenados en el formato esperado.
//     POST: La lista de vuelos debe devolverse con los detalles especificados.
    public Retorno listarVuelos();
//    PRE: El cliente con el pasaporte proporcionado debe existir en el sistema.
//    	  Los registros de compras y devoluciones de pasajes del cliente deben estar correctamente almacenados.
//    	  Pasaporte debe ser una cadena no nula y válida que identifique a un cliente.
//     POST: La lista de vuelos debe devolverse con los detalles especificados: código de vuelo y el estado ("CPR" para comprado y "DEV" para devuelto).
//	   Los últimos pasajes comprados deben mostrarse al inicio del reporte.
    public Retorno vuelosDeCliente(String pasaporte);
    //pre:      post: 
    public Retorno pasajesDevueltos(String nombreAerolinea);
    //pre:      post: 
    public Retorno vistaDeVuelo(String codigoVuelo);
    
    
    
}
