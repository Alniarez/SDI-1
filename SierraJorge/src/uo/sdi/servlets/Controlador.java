package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.AceptarPlazaAction;
import uo.sdi.acciones.CancelarViajeAction;
import uo.sdi.acciones.CerrarSesionAction;
import uo.sdi.acciones.CrearCuenta;
import uo.sdi.acciones.ExitoAction;
import uo.sdi.acciones.ListarViajesAction;
import uo.sdi.acciones.ModificarPerfilAction;
import uo.sdi.acciones.MostrarViajeAction;
import uo.sdi.acciones.RechazarPlazaAction;
import uo.sdi.acciones.SolicitarPlazaAction;
import uo.sdi.acciones.ValidarseAction;
import uo.sdi.acciones.VerPerfilAction;
import uo.sdi.acciones.VerificarCrearViajeAction;
import uo.sdi.acciones.VerificarModificarViajeAction;
import alb.util.log.Log;

public class Controlador extends javax.servlet.http.HttpServlet {
    
    static private final boolean VER_TRAZA = true;

    private static final long serialVersionUID = 1L;

    // <rol, <opcion, objeto Accion>>
    private Map<String, Map<String, Accion>> mapaDeAcciones;
    // <rol, <opcion, <resultado, JSP>>>
    private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion;

    public void init() throws ServletException {
	crearMapaAcciones();
	crearMapaDeJSP();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException {

	String opcion, resultado, jspSiguiente;
	Accion accion;
	String rolAntes, rolDespues;

	try {
	    opcion = req.getServletPath().replace("/", "");

	    rolAntes = obtenerRolDeSesion(req);

	    accion = buscarAccionParaOpcion(rolAntes, opcion);

	    resultado = accion.execute(req, res);

	    rolDespues = obtenerRolDeSesion(req);

	    jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);

	    req.setAttribute("jspSiguiente", jspSiguiente);

	} catch (Exception e) {

	    req.getSession().invalidate();

	    Log.error("Se ha producido alguna excepción no manejada [%s]", e);
	    
	    if (VER_TRAZA)
		e.printStackTrace();

	    String errorMessage = "Ups. Algo ha salido mal. "
		    + "Continúe con normalidad a partir de ahora.";
	    req.setAttribute("errorMessage", errorMessage);

	    jspSiguiente = "/home.jsp";
	}

	RequestDispatcher dispatcher = getServletContext()
		.getRequestDispatcher(jspSiguiente);

	dispatcher.forward(req, res);
    }

    private String obtenerRolDeSesion(HttpServletRequest req) {
	HttpSession sesion = req.getSession();
	if (sesion.getAttribute("user") == null)
	    return "PUBLICO";
	else
	    return "REGISTRADO";
    }

    // Obtiene un objeto accion en función de la opción
    // enviada desde el navegador
    private Accion buscarAccionParaOpcion(String rol, String opcion) {

	Accion accion = mapaDeAcciones.get(rol).get(opcion);
	Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
		opcion, rol);
	return accion;
    }

    // Obtiene la p�gina JSP a la que habr� que entregar el
    // control el funci�n de la opci�n enviada desde el navegador
    // y el resultado de la ejecuci�n de la acci�n asociada
    private String buscarJSPSegun(String rol, String opcion, String resultado) {

	String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
		.get(resultado);
	Log.debug(
		"Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",
		jspSiguiente, resultado, opcion, rol);
	return jspSiguiente;
    }

    private void crearMapaAcciones() {

	mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

	Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
	// Acciones publicas
	mapaPublico.put("listarViajes", new ListarViajesAction());
	mapaPublico.put("mostrarViaje", new MostrarViajeAction());
	mapaPublico.put("validarse", new ValidarseAction());
	mapaPublico.put("crearCuenta", new CrearCuenta());
	mapaDeAcciones.put("PUBLICO", mapaPublico);

	Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
	// Acciones de registrados
	mapaRegistrado.put("listarViajes", new ListarViajesAction());
	mapaRegistrado.put("mostrarViaje", new MostrarViajeAction());
	mapaRegistrado.put("mostrarPerfil", new VerPerfilAction());
	mapaRegistrado.put("modificarPerfil", new ModificarPerfilAction());
	mapaRegistrado.put("cerrarSesion", new CerrarSesionAction());
	mapaRegistrado.put("registrarViaje", new ExitoAction());
	mapaRegistrado.put("verificarCrearViaje", new VerificarCrearViajeAction());
	mapaRegistrado.put("modificarViaje", new ExitoAction());
	mapaRegistrado.put("verificarModificarViaje", new VerificarModificarViajeAction());
	mapaRegistrado.put("cancelarViaje", new CancelarViajeAction());
	mapaRegistrado.put("solicitarPlaza", new SolicitarPlazaAction());
	
	mapaRegistrado.put("aceparPasajero", new AceptarPlazaAction());
	mapaRegistrado.put("rechazarPasajero", new RechazarPlazaAction());
	mapaDeAcciones.put("REGISTRADO", mapaRegistrado);
    }

    private void crearMapaDeJSP() {

	mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

	// Crear mapas auxiliares vacíos
	Map<String, Map<String, String>> opcionResJSP = new HashMap<String, Map<String, String>>();
	Map<String, String> resJSP = new HashMap<String, String>();

	// PUBLICO
	{
	    // Validarse
	    resJSP.put("FRACASO", "/home.jsp");
	    opcionResJSP.put("validarse", resJSP);

	    // Listar viajes
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/listaViajes.jsp");
	    opcionResJSP.put("listarViajes", resJSP);

	    // Mostrar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/vistaViaje.jsp");
	    opcionResJSP.put("mostrarViaje", resJSP);

	    // Cerrar sesión
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("cerrarSesion", resJSP);

	    // Crear cuenta
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/home.jsp");
	    resJSP.put("FRACASO", "/home.jsp");
	    opcionResJSP.put("crearCuenta", resJSP);
	   
	}

	mapaDeNavegacion.put("PUBLICO", opcionResJSP);

	opcionResJSP = new HashMap<String, Map<String, String>>();
	resJSP = new HashMap<String, String>();

	// REGISTRADO
	{

	    // Validarse
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("validarse", resJSP);

	    // Listar viajes
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/listaViajes.jsp");
	    opcionResJSP.put("listarViajes", resJSP);

	    // Mostrar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/vistaViaje.jsp");
	    opcionResJSP.put("mostrarViaje", resJSP);

	    // Mostrar Perfil
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/profile.jsp");
	    opcionResJSP.put("mostrarPerfil", resJSP);

	    // Modificar Perfil
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/profile.jsp");
	    resJSP.put("FRACASO", "/profile.jsp");
	    opcionResJSP.put("modificarPerfil", resJSP);
	    
	    // Registrar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/promotorViaje.jsp");
	    opcionResJSP.put("registrarViaje", resJSP);
	    
	    //Verificar Registrar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/promotorViaje.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("verificarCrearViaje", resJSP);
	    
	    // Modificar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("EXITO", "/promotorViajeModificar.jsp");
	    opcionResJSP.put("modificarViaje", resJSP);
	    
	    // Verificar Modificar viaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/promotorViajeModificar.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("verificarModificarViaje", resJSP);
	    
	    // cancelarViaje
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/home.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("cancelarViaje", resJSP);
	    
	    // Solicitar plaza
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/home.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("solicitarPlaza", resJSP);
	    
	    //Aceptar plaza
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/home.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("aceparPasajero", resJSP);
	    
	    
	    //Aceptar plaza
	    resJSP = new HashMap<String, String>();
	    resJSP.put("FRACASO", "/home.jsp");
	    resJSP.put("EXITO", "/home.jsp");
	    opcionResJSP.put("rechazarPasajero", resJSP);
	 
	}

	mapaDeNavegacion.put("REGISTRADO", opcionResJSP);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException {
	doGet(req, res);
    }

}