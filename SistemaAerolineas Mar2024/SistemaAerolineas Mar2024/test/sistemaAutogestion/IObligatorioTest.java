/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import dominio.Pasaje;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {
    
    private Sistema miSistema;
    
    public IObligatorioTest() {
    }
    
    @Before
    public void setUp() {
       miSistema = new Sistema();
    }

    @Test
    public void testCrearSistemaDeGestionOK() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
// JUEGOS DE PRUEBA CREADOS POR LOS ALUMNOS
    
    
        // Punto 1.2 - Crear Aerolínea
    @Test
    public void testCrearAerolineaOK() {
        // Creación exitosa de aerolíneas válidas
        Retorno resultado = miSistema.crearAerolinea("Air France", "Francia", 15);
        assertEquals(Retorno.ok().resultado, resultado.resultado);

        resultado = miSistema.crearAerolinea("British Airways", "Reino Unido", 25);
        assertEquals(Retorno.ok().resultado, resultado.resultado);

        // Caso borde: Creación de aerolínea con cantidad de aviones máxima (999)
        resultado = miSistema.crearAerolinea("Lufthansa", "Alemania", 999);
        assertEquals(Retorno.ok().resultado, resultado.resultado);
    }

    @Test
    public void testCrearAerolineaERROR1() {
        // Creación de aerolínea con nombre y país ya existentes
        Retorno resultado = miSistema.crearAerolinea("Air France", "Francia", 15);
        assertEquals(Retorno.ok().resultado, resultado.resultado);

        resultado = miSistema.crearAerolinea("British Airways", "Reino Unido", 25);
        assertEquals(Retorno.ok().resultado, resultado.resultado);

        resultado = miSistema.crearAerolinea("Air France", "Francia", 15);
        assertEquals(Retorno.error1().resultado, resultado.resultado);
    }

    @Test
    public void testCrearAerolineaERROR2() {
        // Creación de aerolínea con cantidad de aviones inválida
        Retorno resultado = miSistema.crearAerolinea("Qatar Airways", "Qatar", 0);
        assertEquals(Retorno.error2().resultado, resultado.resultado);

        resultado = miSistema.crearAerolinea("Delta Air Lines", "Estados Unidos", -5);
        assertEquals(Retorno.error2().resultado, resultado.resultado);
    }
    
    // Punto 1.3 - Eliminar Aerolínea
    @Test
    public void testEliminarAerolineaOK() {
        // Eliminación exitosa de aerolínea existente
        miSistema.crearAerolinea("Air France", "Francia", 15);
        Retorno resultado = miSistema.eliminarAerolinea("Air France");
        assertEquals(Retorno.ok().resultado, resultado.resultado);
    }

    @Test
    public void testEliminarAerolineaERROR1() {
        // Intento de eliminar aerolínea inexistente
        Retorno resultado = miSistema.eliminarAerolinea("Lufthansa");
        assertEquals(Retorno.error1().resultado, resultado.resultado);
    }

    @Test
    public void testEliminarAerolineaERROR2() {
        // Intento de eliminar aerolínea con Aviones registrados
        miSistema.crearAerolinea("KLM", "Países Bajos", 20);
        miSistema.registrarAvion("KL567", 15, "KLM");
        Retorno resultado = miSistema.eliminarAerolinea("KLM");
        assertEquals(Retorno.error2().resultado, resultado.resultado);
    }

    // Punto 1.4 - Registrar Avión
    @Test
    public void testRegistrarAvionOK() {
        // Registro de avión sin problemas
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 30);
        Retorno resultado = miSistema.registrarAvion("EK123", 21, "Emirates");
        assertEquals(Retorno.ok().resultado, resultado.resultado);
        resultado = miSistema.registrarAvion("EF123", 21, "Emirates");
        assertEquals(Retorno.ok().resultado, resultado.resultado);
        resultado = miSistema.registrarAvion("EH123", 21, "Emirates");
        assertEquals(Retorno.ok().resultado, resultado.resultado);
    }

    @Test
    public void testRegistrarAvionERROR1() {
        // Registro de avión con codigo ya existente
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 30);
        Retorno retorno = miSistema.crearAerolinea("Emirate", "Emiratos Árabes Unidos", 10);
        assertEquals(Retorno.ok().resultado, retorno.resultado);
        retorno = miSistema.crearAerolinea("Delta Air Lines", "Estados Unidos", 30);
        assertEquals(Retorno.ok().resultado, retorno.resultado);
        retorno = miSistema.registrarAvion("BB123", 9, "Emirates");
        assertEquals(Retorno.ok().resultado, retorno.resultado);
        retorno = miSistema.registrarAvion("BB123", 18, "Emirates");
        assertEquals(Retorno.error1().resultado, retorno.resultado);
    }

    @Test
    public void testRegistrarAvionERROR2() {
        // Intento de registrar avión con capacidad < 9 pasajeros o no múltiplo de 3
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 30);
        Retorno resultado = miSistema.registrarAvion("EK123", 8, "Emirates");
        assertEquals(Retorno.error2().resultado, resultado.resultado);
    }

    @Test
    public void testRegistrarAvionERROR3() {
        // Intento de registrar avión en aerolínea inexistente
        Retorno resultado = miSistema.registrarAvion("AA345", 12, "Porter Airlines");
        assertEquals(Retorno.error3().resultado, resultado.resultado);
    }

    @Test
    public void testRegistrarAvionERROR4() {
        // Intento de registrar avión superando la cantidad máxima soportada por la aerolínea
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 9);
        miSistema.registrarAvion("LowCost", 30, "Emirates");
        miSistema.registrarAvion("Qatar Airways", 30, "Emirates");
        miSistema.registrarAvion("Iberia", 30, "Emirates");
        miSistema.registrarAvion("Air France", 30, "Emirates");
        miSistema.registrarAvion("British Airway", 30, "Emirates");
        miSistema.registrarAvion("Lufthansa", 30, "Emirates");
        miSistema.registrarAvion("Delta Air Lines", 30, "Emirates");
        miSistema.registrarAvion("Aerolineas Uruguayas", 30, "Emirates");
        miSistema.registrarAvion("American Airlines", 30, "Emirates");
        Retorno r = miSistema.registrarAvion("Hainan Airlines", 30, "Emirates");
        assertEquals(Retorno.error4().resultado, r.resultado);
    }

    // Punto 1.5 - Eliminar Avión
    @Test
    public void testEliminarAvionOK() {
        // Registro de avión y eliminación sin problemas
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 30);
        miSistema.registrarAvion("EK123", 21, "Emirates");
        Retorno resultado = miSistema.eliminarAvion("Emirates", "EK123");
        assertEquals(Retorno.ok().resultado, resultado.resultado);
    }

    @Test
    public void testEliminarAvionERROR1() {
        // Intento de eliminación de avión de una aerolínea que no existe
        Retorno resultado = miSistema.eliminarAvion("Aerolineas Uruguayas", "UR345");
        assertEquals(Retorno.error1().resultado, resultado.resultado);
    }

    @Test
    public void testEliminarAvionERROR2() {
        // Intento de eliminación de un avión que no existe en la aerolínea especificada
        miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 30);
        miSistema.registrarAvion("EF123", 20, "Emirates");        
        Retorno resultado = miSistema.eliminarAvion("Emirates", "EK123");
        assertEquals(Retorno.error2().resultado, resultado.resultado);
    }

    @Test
    public void testEliminarAvionERROR3() {
        // Intento de eliminación de un avión con PASAJES VENDIDOS
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2341", "Aerolineas Argentinas", "FLY300", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);        miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        miSistema.comprarPasaje("MF34111", "AA1111", 1);
        miSistema.comprarPasaje("MF34111", "AA1111", 2);

        Retorno resultado = miSistema.eliminarAvion("Aerolineas Argentinas", "FLY221");
        assertEquals(Retorno.error3().resultado, resultado.resultado);
    }
    
    @Test
    public void testListarAerolineasOK() {
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 20);
        assertEquals(Retorno.ok().resultado, r.resultado);


        r = miSistema.listarAerolineas();
        assertEquals("Aerolineas Argentinas-Argentina-10|\nEmirates-Emiratos Árabes Unidos-20|", r.valorString);
    }
    
    @Test
    public void testListarAvionesDeAerolineaOK() {
        Retorno r = miSistema.crearAerolinea("Emirates", "Emiratos Árabes Unidos", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("BB123",12, "Emirates");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FF123", 21, "Emirates");
        assertEquals(Retorno.ok().resultado, r.resultado);
        
        r = miSistema.listarAvionesDeAerolinea("Emirates");
        assertEquals("BB123-12|\nFF123-21|" , r.valorString);
    }

    @Test
    public void testListarAvionesDeAerolineaError1() {
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearAerolinea("Iberia", "EspaÃ±a", 20);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearAerolinea("Delta Air Lines", "Estados Unidos", 30);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.registrarAvion("AA345",12, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("IB563",21, "Iberia");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("AA311", 21, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        
        r = miSistema.listarAvionesDeAerolinea("Pepe");
        assertEquals(Retorno.error1().resultado , r.resultado);
    }

    
    @Test
public void testCrearClienteOK() {
    Retorno r = miSistema.registrarCliente("AB12345", "Ana Blanco", 20);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("CD67890", "Carlos Diaz", 1);  // Edad positiva más baja
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("EF24680", "Elena Fernandez", 120);  // Edad alta aceptada razonablemente
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("GH13579", "Gabriel Hernandez", 50);  // Edad media
    assertEquals(Retorno.ok().resultado, r.resultado);
}

@Test
public void testCrearClienteError1() {
    Retorno r = miSistema.registrarCliente("IJ97531", "Isabel Jimenez", -1);  // Edad negativa
    assertEquals(Retorno.error1().resultado, r.resultado);
    
    r = miSistema.registrarCliente("KL86420", "Karen Lopez", -100);  // Edad negativa alta
    assertEquals(Retorno.error1().resultado, r.resultado);
}

@Test
public void testCrearClienteError2() {
    Retorno r = miSistema.registrarCliente("MN", "Miguel Navarro", 25);  // Pasaporte demasiado corto
    assertEquals(Retorno.error2().resultado, r.resultado);
    
    r = miSistema.registrarCliente("OPQRSTUVWXYZ", "Olga Perez", 30);  // Pasaporte demasiado largo
    assertEquals(Retorno.error2().resultado, r.resultado);
    
    r = miSistema.registrarCliente("", "Pablo Quintana", 40);  // Pasaporte vacío
    assertEquals(Retorno.error2().resultado, r.resultado);
    
    r = miSistema.registrarCliente("RS1234", "Rosa Sanchez", 35);  // Pasaporte un carácter menos
    assertEquals(Retorno.error2().resultado, r.resultado);
    
    r = miSistema.registrarCliente("TU123456", "Tomas Urbina", 50);  // Pasaporte un carácter más
    assertEquals(Retorno.error2().resultado, r.resultado);
}

@Test
public void testCrearClienteError3() {
    Retorno r = miSistema.registrarCliente("WX12345", "Walter Xavier", 28);  // Primer registro con pasaporte
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("WX12345", "William Xavier", 29);  // Duplicado de pasaporte
    assertEquals(Retorno.error3().resultado, r.resultado);
    
    r = miSistema.registrarCliente("YZ54321", "Yolanda Zamora", 45);  // Otro registro con pasaporte diferente
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("YZ54321", "Yvonne Zamora", 46);  // Duplicado de pasaporte
    assertEquals(Retorno.error3().resultado, r.resultado);
    
    r = miSistema.registrarCliente("AA11111", "Alicia Anderson", 35);  // Nuevo primer registro
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarCliente("AA11111", "Amanda Anderson", 36);  // Duplicado de pasaporte
    assertEquals(Retorno.error3().resultado, r.resultado);
}


@Test
public void testCrearVueloOK() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Brasil", 16, 6, 2024, 6, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.crearVuelo("VUE003", "Aerolineas Argentinas", "FLY221", "Chile", 17, 6, 2024, 6, 6); 
    assertEquals(Retorno.ok().resultado, r.resultado);
}

@Test
public void testCrearVueloError1() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Brasil", 16, 6, 2024, 6, 3);
    assertEquals(Retorno.error1().resultado, r.resultado);
}


@Test
public void testCrearVueloError2() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Emirates", "FLY221", "Uruguay", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.error2().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Qatar", "FLY221", "Brasil", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.error2().resultado, r.resultado);
}


@Test
public void testCrearVueloError3() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
     r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
         r = miSistema.registrarAvion("FLY222", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE006", "Aerolineas Argentinas", "AVInoExiste", "Paraguay", 21, 6, 2024, 3, 6);  // Código de avión inexistente en aerolínea existente
    assertEquals(Retorno.error3().resultado, r.resultado);
}

// A TERMINAR

@Test
public void testCrearVueloError4() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Brasil", 16, 6, 2024, 6, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.crearVuelo("VUE003", "Aerolineas Argentinas", "FLY221", "Chile", 16, 6, 2024, 6, 6); 
    assertEquals(Retorno.error4().resultado, r.resultado);
}


@Test
public void testCrearVueloError5() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE009", "Aerolineas Argentinas", "FLY221", "Colombia", 23, 6, 2024, 4, 6);
    assertEquals(Retorno.error5().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE010", "Aerolineas Argentinas", "FLY221", "Mexico", 24, 6, 2024, 2, 8);
    assertEquals(Retorno.error5().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE011", "Aerolineas Argentinas", "FLY221", "Panama", 25, 6, 2024, 8, 2);
    assertEquals(Retorno.error5().resultado, r.resultado);
}

@Test
public void testCrearVueloError6() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 12, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE011", "Aerolineas Argentinas", "FLY221", "Panama", 25, 6, 2024, 300, 300);
    assertEquals(Retorno.error6().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE009", "Aerolineas Argentinas", "FLY221", "Colombia", 23, 6, 2024, 9, 6);
    assertEquals(Retorno.error6().resultado, r.resultado);
}

@Test
public void testComprarPasajeOK() {
     Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AB12345", "Ana Blanco", 19);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Brasil", 16, 6, 2024, 6, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.comprarPasaje("AB12345", "VUE001", 1);
    assertEquals(Retorno.ok().resultado, r.resultado);

    // Cliente y vuelo preexistentes, categoría primera clase con disponibilidad
    r = miSistema.comprarPasaje("JB12345", "VUE002", 2);
    assertEquals(Retorno.ok().resultado, r.resultado);
}


@Test
public void testComprarPasajeError1() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AB12345", "Ana Blanco", 19);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Cliente no existente
    r = miSistema.comprarPasaje("XX00000", "VUE001", 1);
    assertEquals(Retorno.error1().resultado, r.resultado);

    // Cliente no existente, otra categoría
    r = miSistema.comprarPasaje("YY11111", "VUE002", 2);
    assertEquals(Retorno.error1().resultado, r.resultado);
}


@Test
public void testComprarPasajeError2() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Vuelo no existente
    r = miSistema.comprarPasaje("JB12345", "VUE999", 1);
    assertEquals(Retorno.error2().resultado, r.resultado);

    // Vuelo no existente, otra categoría
    r = miSistema.comprarPasaje("JB12345", "VUE998", 2);
    assertEquals(Retorno.error2().resultado, r.resultado);
}

@Test
public void testDevolverPasajeOK() {
    Retorno r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AP12345", "Ana Paula", 31);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("BR12345", "Belen Rodriguez", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("YC12245", "Yazmin Carla", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
    // Cliente y vuelo preexistentes, pasaje comprado previamente
    r = miSistema.comprarPasaje("JB12345", "VUE001", 1);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Vuelo no existente, otra categoría
    r = miSistema.comprarPasaje("BR12345", "VUE001", 2);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.devolverPasaje("BR12345", "VUE001");  // Devolución exitosa
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.devolverPasaje("JB12345", "VUE001");  // Devolución exitosa y reasignación de pasaje al primero en lista de espera
    assertEquals(Retorno.ok().resultado, r.resultado);
}

@Test
public void testDevolverPasajeError1() {
     Retorno r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AP12345", "Ana Paula", 31);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("BR12345", "Belen Rodriguez", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("YC12245", "Yazmin Carla", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
    // Cliente y vuelo preexistentes, pasaje comprado previamente
    r = miSistema.comprarPasaje("JB12345", "VUE001", 1);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Vuelo no existente, otra categoría
    r = miSistema.comprarPasaje("BR12345", "VUE001", 2);
    assertEquals(Retorno.ok().resultado, r.resultado);
   // Cliente existente
    r = miSistema.devolverPasaje("BR12345", "VUE001"); // Devolucion exitosa
    assertEquals(Retorno.ok().resultado, r.resultado);

    // Cliente no existente
    r = miSistema.devolverPasaje("YY11111", "VUE002");
    assertEquals(Retorno.error1().resultado, r.resultado);
}


@Test
public void testDevolverPasajeError2() {
    Retorno r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AP12345", "Ana Paula", 31);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("BR12345", "Belen Rodriguez", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("YC12245", "Yazmin Carla", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Cliente y vuelo preexistentes, pasaje comprado previamente
    r = miSistema.comprarPasaje("JB12345", "VUE001", 1);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Vuelo no existente, otra categoría
    r = miSistema.comprarPasaje("BR12345", "VUE001", 2);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Vuelo existente
    r = miSistema.devolverPasaje("JB12345", "VUE001");
    assertEquals(Retorno.ok().resultado, r.resultado);

    // Vuelo no existente
    r = miSistema.devolverPasaje("BR12345", "VUE998");
    assertEquals(Retorno.error2().resultado, r.resultado);
}


@Test
public void testDevolverPasajeError3() {
     Retorno r = miSistema.registrarCliente("JB12345", "Juan Carlos", 1);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("AP12345", "Ana Paula", 31);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("BR12345", "Belen Rodriguez", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("YC12245", "Yazmin Carla", 61);  // Edad mínima aceptada
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);
    // Cliente y vuelo preexistentes, pasaje comprado previamente

    // Cliente y vuelo preexistentes, pero sin compra previa para ese vuelo
    r = miSistema.devolverPasaje("JB12345", "VUE001");
    assertEquals(Retorno.error3().resultado, r.resultado);

    // Cliente y vuelo preexistentes, pero sin compra previa para otro vuelo
    r = miSistema.devolverPasaje("BR12345", "VUE001");
    assertEquals(Retorno.error3().resultado, r.resultado);
}


@Test
public void testListarClientesOK() {
    Retorno r = miSistema.registrarCliente("AB12345", "Juan Perez", 30);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("CD67890", "Maria Gomez", 25);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarCliente("EF24680", "Carlos Diaz", 40);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.listarClientes();
    assertEquals(Retorno.ok().resultado, r.resultado);
    assertEquals("EF24680-Carlos Diaz-40|\nCD67890-Maria Gomez-25|\nAB12345-Juan Perez-30|", r.valorString);
}
// ESOS NUMEROS NI IDEA
@Test
    public void testListarVuelosOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2222", "Aerolineas Argentinas", "FLY221", "Uruguay", 2, 10, 2024, 6, 9);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA3333", "Aerolineas Argentinas", "FLY221", "Uruguay", 5, 9, 2024, 3, 12);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarVuelos();
        System.out.println(r.valorString);
        assertEquals("AA1111-Aerolineas Argentinas-FLY221-0-0-15|\nAA2222-Aerolineas Argentinas-FLY221-0-0-15|\nAA3333-Aerolineas Argentinas-FLY221-0-0-15|", r.valorString);

        //Compra de pasajes
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA2222", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarVuelos();
        System.out.println(r.valorString);
        assertEquals("AA1111-Aerolineas Argentinas-FLY221-1-0-14|\nAA2222-Aerolineas Argentinas-FLY221-0-1-14|\nAA3333-Aerolineas Argentinas-FLY221-0-0-15|", r.valorString);
    }
@Test
public void testVuelosDeClienteOK() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Dubai", 1, 1, 2024, 9, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);
    miSistema.registrarCliente("AB12345", "Juan Perez", 30);


    r = miSistema.comprarPasaje("AB12345", "VUE002", 1);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.comprarPasaje("AB12345", "VUE002", 2);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.devolverPasaje("AB12345", "VUE002");
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.vuelosDeCliente("AB12345");
    assertEquals(Retorno.ok().resultado, r.resultado);
    assertEquals("VUE002-CPR|\nVUE002-CPR|\nVUE002-DEV|", r.valorString);
}


@Test
public void testVuelosDeClienteError1() {
    Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
    assertEquals(Retorno.ok().resultado, r.resultado);
    r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Dubai", 1, 1, 2024, 9, 3);
    assertEquals(Retorno.ok().resultado, r.resultado);

    r = miSistema.devolverPasaje("AW12345", "VUE002");
    assertEquals(Retorno.error1().resultado, r.resultado);
    r = miSistema.vuelosDeCliente("AB12345");
    assertEquals(Retorno.error1().resultado, r.resultado);
}

@Test
public void testPasajesDevueltosOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA9999", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Devolución de pasaje
        r = miSistema.devolverPasaje("MF34111", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        
        r = miSistema.pasajesDevueltos("Aerolineas Argentinas");
        assertEquals("MF34111-AA9999|\nVM32132-AA1111|\nCB34555-AA1111|", r.valorString);
}


@Test
public void testPasajesDevueltosError1() {
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de clientes
        r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA9999", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA9999", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Devolución de pasaje
        r = miSistema.devolverPasaje("MF34111", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("VM32132", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.pasajesDevueltos("AeroNoExiste");
        assertEquals(Retorno.error1().resultado, r.resultado);
}
    

@Test
    public void testVistaDeVueloOK() {
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("VUE001", "Aerolineas Argentinas", "FLY221", "Argentina", 15, 6, 2024, 3, 6);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.crearVuelo("VUE002", "Aerolineas Argentinas", "FLY221", "Brasil", 16, 6, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.crearVuelo("VUE003", "Aerolineas Argentinas", "FLY221", "Chile", 17, 6, 2024, 6, 6);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.registrarCliente("BR12345", "Belen Rodriguez", 61);  // Edad mínima aceptada
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("YC12245", "Yazmin Carla", 61);  // Edad mínima aceptada
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.comprarPasaje("BR12345", "VUE001", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        // Vuelo no existente, otra categoría
        r = miSistema.comprarPasaje("YC12245", "VUE001", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.vistaDeVuelo("VUE001");
        assertEquals(Retorno.ok().resultado, r.resultado);
        System.out.println(r.valorString);
    }
    
/*    
    JUEGOS DE PRUEBA BRINDADOS POR LA UNIVERSIDAD
    @Test
    public void testRegistrarClienteOK() {
         Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("AM11111", "Alfonso Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
    

    //Gestión de vuelos (No se incluyen en estas pruebas ERROR_1, ERRROR_2, ERROR_3, ERROR_4, ERROR_5, ERROR_6)
    @Test
    public void testCrearVueloOK() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2341", "Aerolineas Argentinas", "FLY300", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
 @Test
    public void testComprarPasajeOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
    @Test
    public void testDevolverPasajeOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
//        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
//        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
//        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
//        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
//        //Devolución de pasaje 
        r = miSistema.devolverPasaje("MF34111", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    

    @Test
    public void testListarVuelosDeClientesOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2222", "Aerolineas Argentinas", "FLY221", "Uruguay", 11, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA3333", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Compra de pasajes (hay disponible)
                r = miSistema.comprarPasaje("MF34111", "AA3333", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
                r = miSistema.comprarPasaje("MF34111", "AA2222", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje (se deberían otorgar a los dos clientes que están esperando)
        r = miSistema.devolverPasaje("MF34111", "AA2222");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.vuelosDeCliente("MF34111");
        assertEquals("AA3333-CPR|\nAA2222-CPR|\nAA1111-CPR|\nAA2222-DEV|", r.valorString);
    }


    @Test
    public void testListarClienteOK() {

        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("AM11111", "Alfonso Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarClientes();
        assertEquals("CB34555-Camila Barcos-54|\nAM11111-Alfonso Miguez-34|\nMF34111-Martina Fernandez-1|", r.valorString);    
    }

    @Test
    public void testPasajesDevueltosOK() {

        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA9999", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Devolución de pasaje
        r = miSistema.devolverPasaje("MF34111", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.pasajesDevueltos("Aerolineas Argentinas");
        assertEquals("MF34111-AA9999|\nVM32132-AA1111|\nCB34555-AA1111|", r.valorString);
    }



    */

    
}
