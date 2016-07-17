package uo.sdi.tests;

import java.util.Date;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/*
 * Todos los test estan pensados para funcionar sobre los datos originales.
 * Si se ejecutan multiples veces o sobre una base de datos con otros datos los
 * resultados pueden no ser los esperados.
 */
public class SierraJorgetest {
    
    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8280/SierraJorge");
    }
    
    @Test
    public void testRegistrarseSinExito() {
	beginAt("/noModalLoginRegister.jsp");

        setTextField("nombreUsuarioRegister", "Jorge");
        setTextField("claveUsuarioLogin", new Date().toString());
        
        assertButtonPresent("loginBut");
        submit("loginBut");
        assertTextInElement("errorMessage", "incorrecta"); 
    }
    
    @Test
    public void testValidarseSinExito() {
	beginAt("/noModalLoginRegister.jsp");

        setTextField("nombreUsuarioLogin", "Jorge");
        setTextField("nombreRegister", "Jorge");
        setTextField("apellidoRegister", "Sierra");
        setTextField("emailRegister", "Jorge@correo.es");
        setTextField("claveRegister1", "pass");
        setTextField("claveRegister2", "ssap");
        
        submit("registernBut");
        assertTextInElement("errorMessage", "coinciden"); 
    }
    
    @Test
    public void testRegistrarViajeConExito() {
	beginAt("/noModalLoginRegister.jsp");

        setTextField("nombreUsuarioLogin", "user1");
        setTextField("claveUsuarioLogin", "user1");
        
        submit("loginBut");
        
        assertTextInElement("successMessage", "Bienvenido");
        
        assertLinkPresent("registrarViaje");
        clickLink("registrarViaje");
        
        assertTitleEquals("ShareMyTrip :: Registrar nuevo viaje");
                
        setTextField("direccionOrigen", "direccionOrigen");
        setTextField("ciudadOrigen", "ciudadOrigen");
        setTextField("estadoOrigen", "estadoOrigen");
        setTextField("paisOrigen", "paisOrigen");
        setTextField("zipOrigen", "zipOrigen");
        
        setTextField("direccionDestino", "direccionDestino");
        setTextField("ciudadDestino", "ciudadDestino");
        setTextField("estadoDestino", "estadoDestino");
        setTextField("paisDestino", "paisDestino");
        setTextField("zipDestino", "zipDestino");
        
        setTextField("salida", "2222-01-01");
        setTextField("salidaH", "11:11");
        
        setTextField("llegada", "2223-01-01");
        setTextField("llegadaH", "11:11");
        
        setTextField("cierre", "2221-01-01");
        
        setTextField("plazas", "2");
        setTextField("coste", "2");
        
        submit("vv");
        
        assertTitleEquals("ShareMyTrip :: Inicio");
        
    }
    
    /*
     * Este metodo falla si se ejecuta más de una vez.
     * No se puede solicitar plaza si ya la has solicitado con anterioridad.
     */
    @Test
    public void testSolicitarViaje() {
	setScriptingEnabled(false);
	
	beginAt("/noModalLoginRegister.jsp");

        setTextField("nombreUsuarioLogin", "user5");
        setTextField("claveUsuarioLogin", "user5");
        
        submit("loginBut");
        
        assertTitleEquals("ShareMyTrip :: Inicio");
        
        
        assertLinkPresent("listarViajes");
        clickLink("listarViajes");
        
        
        assertTitleEquals("ShareMyTrip :: Próximos viajes");
        
        assertLinkPresent("203");
        clickLink("203");
        
        assertTitleEquals("ShareMyTrip :: Datos del viaje 203");
        
        assertLinkPresent("solicitarPlaza");
        clickLink("solicitarPlaza");
        
        assertTitleEquals("ShareMyTrip :: Inicio");
        
        
    }
    
    
    
    

}
