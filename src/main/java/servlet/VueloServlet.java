package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Aeropuerto;
import entities.Avion;
import entities.Usuario;
import entities.Vuelo;
import logic.CtrlAeropuerto;
import logic.CtrlVuelo;

/**
 * Servlet implementation class VueloServlet
 */
@WebServlet("/VueloServlet")
public class VueloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VueloServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlVuelo cv = new CtrlVuelo();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		boolean reenviar = true;
		if (usuario == null) {
			response.sendRedirect("index.html");
		} else {
			if (accion != null) {
				switch (accion) {
				case "eliminar": {
					if (usuario.getTipo().equals("admin")) {
						try {
							int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
							Vuelo vue = new Vuelo();
							vue.setIdvuelo(idvuelo);

							cv.delete(vue);
						} catch (Exception e) {
							String message = "error :" + e.getMessage();
							request.setAttribute("message", message);
						}
					} else {
						request.getRequestDispatcher("VueloServlet").forward(request, response);
						reenviar = false;
					}
					break;
				}
				case "redirecAgregarVuelo": {
					if (usuario.getTipo().equals("admin")) {
						CtrlAeropuerto ca = new CtrlAeropuerto();
						try {
							LinkedList<Aeropuerto> aeropuertos = ca.getAll();
							request.setAttribute("listaAeropuertos", aeropuertos);
						} catch (Exception e) {
							String message = "error :" + e.getMessage();
							request.setAttribute("message", message);

						}
						request.getRequestDispatcher("WEB-INF/ui-vuelo/AgregarVuelo.jsp").forward(request, response);
						reenviar = false;
					} else {
						request.getRequestDispatcher("VueloServlet").forward(request, response);
						reenviar = false;
					}
					break;
				}
				case "redirecEditar": {
					if (usuario.getTipo().equals("admin")) {
						try {
							int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
							Vuelo vue = new Vuelo();
							vue.setIdvuelo(idvuelo);
							Vuelo v = new Vuelo();
							v = cv.getById(vue);
							request.setAttribute("Vuelo", v);
							request.getRequestDispatcher("WEB-INF/ui-vuelo/EditarVuelo.jsp").forward(request, response);
							reenviar = false;
						} catch (Exception e) {
							String message = "error :" + e.getMessage();
							request.setAttribute("message", message);
						}

					} else {
						request.getRequestDispatcher("index.htlm").forward(request, response);
						reenviar = false;
					}
					break;
				}
				case "filtrar": {
					String origen = request.getParameter("origen");
					String destino = request.getParameter("destino");
					LinkedList<Vuelo> vuelos = new LinkedList<>();
					Vuelo v = new Vuelo();
					v.setAeropuertoOrigen(new Aeropuerto());
					v.setAeropuertoDestino(new Aeropuerto());
					v.getAeropuertoOrigen().setNombre(origen);
					v.getAeropuertoDestino().setNombre(destino);
					try {
						vuelos = cv.getByOrigenYDestino(v);
						request.setAttribute("listaVuelos", vuelos);
					} catch (Exception e) {
						String message = "error :" + e.getMessage();
						request.setAttribute("message", message);

					}
					request.getRequestDispatcher("Vuelos.jsp").forward(request, response);
					reenviar = false;
					break;
				}
				}
			}
			if (reenviar) {
				try {
					LinkedList<Vuelo> vuelos = cv.getAll();
					request.setAttribute("listaVuelos", vuelos);
				} catch (Exception e) {
					String message = "error :" + e.getMessage();
					request.setAttribute("message", message);
				}
				request.getRequestDispatcher("Vuelos.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlVuelo cv = new CtrlVuelo();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			switch (accion) {
			case "insertar": {
				CtrlAeropuerto ca = new CtrlAeropuerto();

				try {

					int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
					LocalDateTime fechaHoraSalida = LocalDateTime.parse(request.getParameter("fechaHoraSalida"));
					LocalDateTime fechaHoraLlegada = LocalDateTime.parse(request.getParameter("fechaHoraLlegada"));
					String nombreAeropuertoOrigen = request.getParameter("nombreAeropuertoOrigen");
					String nombreAeropuertoDestino = request.getParameter("nombreAeropuertoDestino");

					int idAvion = Integer.parseInt(request.getParameter("idAvion"));
					double precioGeneral = Double.parseDouble(request.getParameter("precioGeneral"));
					double precioPrimeraclase = Double.parseDouble(request.getParameter("precioPrimeraClase"));
					Vuelo vue = new Vuelo();
					CtrlAeropuerto cAero = new CtrlAeropuerto();
					Aeropuerto aeroOrigen = new Aeropuerto();
					Aeropuerto aeroDestino = new Aeropuerto();
					aeroOrigen.setNombre(nombreAeropuertoOrigen);
					aeroOrigen = cAero.getByNombre(aeroOrigen);
					aeroDestino.setNombre(nombreAeropuertoDestino);
					aeroDestino = cAero.getByNombre(aeroDestino);

					vue.setIdvuelo(idvuelo);
					vue.setAeropuertoOrigen(aeroOrigen);
					vue.setAeropuertoDestino(aeroDestino);
					vue.setFechaHoraLlegada(fechaHoraLlegada);
					vue.setFechaHoraSalida(fechaHoraSalida);
					vue.setAvion(new Avion());
					vue.getAvion().setIdAvion(idAvion);
					vue.setPrecioGeneral(precioGeneral);
					vue.setPrecioPrimeraClase(precioPrimeraclase);
					cv.add(vue);
					LinkedList<Aeropuerto> aeropuertos = ca.getAll();
					request.setAttribute("listaAeropuertos", aeropuertos);
					request.setAttribute("message", "Vuelo agregado correctamente");
				} catch (Exception e) {
					String message = "error : " + e.getMessage();
					request.setAttribute("message", message);
				}
				request.getRequestDispatcher("WEB-INF/ui-vuelo/AgregarVuelo.jsp").forward(request, response);
				break;
			}
			case "editarVuelo": {
				try {
					int idvuelo = Integer.parseInt(request.getParameter("idVuelo"));
					LocalDateTime fechaHoraSalida = LocalDateTime.parse(request.getParameter("fechaHoraSalida"));
					LocalDateTime fechaHoraLlegada = LocalDateTime.parse(request.getParameter("fechaHoraLlegada"));
					int idAvion = Integer.parseInt(request.getParameter("idAvion"));
					double precioGeneral = Double.parseDouble(request.getParameter("precioGeneral"));
					double precioPrimeraclase = Double.parseDouble(request.getParameter("precioPrimeraClase"));
					Vuelo vue = new Vuelo();
					vue.setAvion(new Avion());
					vue.setIdvuelo(idvuelo);
					vue.setFechaHoraSalida(fechaHoraSalida);
					vue.setFechaHoraLlegada(fechaHoraLlegada);
					vue.getAvion().setIdAvion(idAvion);
					vue.setPrecioGeneral(precioGeneral);
					vue.setPrecioPrimeraClase(precioPrimeraclase);

					cv.edit(vue);
					request.setAttribute("message", "Vuelo editado correctamente");
				} catch (Exception e) {
					String message = "error :" + e.getMessage();
					request.setAttribute("message", message);
				}
				doGet(request, response);
				break;
			}
			default:
				doGet(request, response);

			}
		}

	}

}
