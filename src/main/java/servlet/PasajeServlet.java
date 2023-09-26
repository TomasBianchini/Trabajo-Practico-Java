package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Asiento;
import entities.Avion;
import entities.Pasaje;
import entities.Pasajero;
import entities.Vuelo;
import logic.CtrlPasaje;
import logic.CtrlVuelo;

/**
 * Servlet implementation class PasajeServlet
 */
@WebServlet("/PasajeServlet")
public class PasajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasajeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlVuelo cv = new CtrlVuelo();

		switch (accion) {
		case "compraPasaje": {
			int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
			Vuelo vue = new Vuelo();
			vue.setIdvuelo(idvuelo);
			Vuelo v = new Vuelo();
			v = cv.getById(vue);
			request.setAttribute("Vuelo", v);
			HashMap<String, Asiento> asientosDisponibles = cv.getAsientosDisponibles(v);
			System.out.println(asientosDisponibles);
			request.setAttribute("asientosDisponibles", asientosDisponibles);
			break;
		}
		case "compra": {
			int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
			Vuelo vue = new Vuelo();
			vue.setIdvuelo(idvuelo);

			String dniPasajero = request.getParameter("dniPasajero");
			Pasajero pas = new Pasajero();
			pas.setDni(dniPasajero);
			int idAvion = Integer.parseInt(request.getParameter("idavion"));
			String fila = request.getParameter("fila");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			Asiento asiento = new Asiento();
			asiento.setAvion(new Avion());
			asiento.setFila(fila);
			asiento.setNumero(numero);
			asiento.setTipo(tipo);
			asiento.getAvion().setIdAvion(idAvion);

			CtrlPasaje cpas = new CtrlPasaje();
			Pasaje pasaje = new Pasaje();
			pasaje.setVuelo(vue);
			pasaje.setPasajero(pas);
			pasaje.setAsiento(asiento);

			cpas.add(pasaje);
			request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
			break;
		}

		}

		request.getRequestDispatcher("WEB-INF/ui-pasaje/ComprarPasaje.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
