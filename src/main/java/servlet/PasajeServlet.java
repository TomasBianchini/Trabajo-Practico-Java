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
import entities.Usuario;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlVuelo cv = new CtrlVuelo();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		boolean reenviar = true;
		if (usuario == null) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "redirecCompraPasaje": {
					try {
						int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
						Vuelo vue = new Vuelo();
						vue.setIdvuelo(idvuelo);
						Vuelo v = new Vuelo();
						v = cv.getById(vue);
						request.setAttribute("Vuelo", v);
						HashMap<String, Asiento> asientosDisponibles = cv.getAsientosDisponibles(v);
						request.setAttribute("asientosDisponibles", asientosDisponibles);
						request.getRequestDispatcher("WEB-INF/ui-pasaje/ComprarPasaje.jsp").forward(request, response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "compra": {
					try {
						int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
						Vuelo vue = new Vuelo();
						vue.setIdvuelo(idvuelo);

						int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
						Usuario usu = new Usuario();
						usu.setIdUsuario(idUsuario);
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
						pasaje.setUsuario(usu);
						pasaje.setAsiento(asiento);
						Pasaje p = cpas.add(pasaje);
						if (p == null) {
							String message = "error : No se pudo realizar la compra";
							request.setAttribute("message", message);
						} else {
							String message = "Compra realizada con exito";
							request.setAttribute("message", message);
						}
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					request.getRequestDispatcher("VueloServlet").forward(request, response);
					reenviar = false;
					break;
				}
				case "cancelarPasaje": {
					try {
						Pasaje pas = new Pasaje();
						CtrlPasaje cpas = new CtrlPasaje();
						int id = Integer.parseInt(request.getParameter("idPasaje"));
						pas.setIdPasaje(id);
						pas = cpas.getById(pas);
						cpas.cancelarPasaje(pas);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					} catch (Error e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					request.getRequestDispatcher("VueloServlet").forward(request, response);
					reenviar = false;
					break;
				}
				default:
					request.getRequestDispatcher("VueloServlet").forward(request, response);
					reenviar = false;
				}

			}
			if (reenviar)
				request.getRequestDispatcher("VueloServlet").forward(request, response);
		}

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
