package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Aeropuerto;
import entities.Ciudad;
import entities.Usuario;
import logic.CtrlAeropuerto;
import logic.CtrlCiudad;

/**
 * Servlet implementation class AeropuertoServlet
 */
@WebServlet("/AeropuertoServlet")
public class AeropuertoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AeropuertoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAeropuerto ca = new CtrlAeropuerto();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		boolean reenviar = true;
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			response.sendRedirect("index.html");
		} else {
			if (accion != null) {
				switch (accion) {
				case "redirecEditar": {
					Aeropuerto a = new Aeropuerto();
					try {
						int idAeropuerto = verificarId(request);
						a.setIdAeropuerto(idAeropuerto);
						Aeropuerto aero = ca.getById(a);
						request.setAttribute("Aeropuerto", aero);
						request.getRequestDispatcher("WEB-INF/ui-aeropuerto/EditarAeropuerto.jsp").forward(request,
								response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}

					break;
				}
				case "eliminar": {
					try {
						int idAeropuerto = verificarId(request);
						Aeropuerto ae = new Aeropuerto();
						ae.setIdAeropuerto(idAeropuerto);
						new CtrlAeropuerto().delete(ae);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "redirecAgregarAeropuerto": {
					CtrlCiudad cc = new CtrlCiudad();
					try {
						LinkedList<Ciudad> ciudades = cc.getAll();
						request.setAttribute("listaCiudades", ciudades);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					request.getRequestDispatcher("WEB-INF/ui-aeropuerto/AgregarAeropuerto.jsp").forward(request,
							response);
					reenviar = false;
					break;
				}
				}
			}
			try {
				LinkedList<Aeropuerto> aeropuertos = ca.getAll();
				request.setAttribute("listaAeropuertos", aeropuertos);

			} catch (Exception e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
			}
			if (reenviar) {
				request.getRequestDispatcher("WEB-INF/ui-aeropuerto/ListarAeropuerto.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAeropuerto ca = new CtrlAeropuerto();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			response.sendRedirect("index.html");
		} else {
			switch (accion) {
			case "insertar": {
				CtrlCiudad cc = new CtrlCiudad();
				try {
					Aeropuerto ae = verificarInput(request);
					ca.add(ae);
					request.setAttribute("message", "Aeropuerto agregado correctamente");
					LinkedList<Ciudad> ciudades = cc.getAll();
					request.setAttribute("listaCiudades", ciudades);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);
				}
				request.getRequestDispatcher("WEB-INF/ui-aeropuerto/AgregarAeropuerto.jsp").forward(request, response);
				break;
			}
			case "editarAeropuerto": {
				try {
					Aeropuerto a = verificarInputEditar(request);
					ca.edit(a);
				} catch (Exception e) {
					String message = e.getMessage();
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

	private Aeropuerto verificarInput(HttpServletRequest request) throws Exception {
		String nombre = request.getParameter("nombre");
		String desc = request.getParameter("descripcion");
		String nombreCiudad = request.getParameter("nombreCiudad");
		CtrlCiudad cc = new CtrlCiudad();
		Ciudad ciu = new Ciudad();

		Aeropuerto aero = null;

		if (!nombre.isEmpty() && !desc.isEmpty() && !nombreCiudad.isEmpty()) {
			aero = new Aeropuerto();
			ciu.setNombre(nombreCiudad);
			ciu = cc.getByNombre(ciu);
			aero.setNombre(nombre);
			aero.setDescAeropuerto(desc);
			aero.setCiudad(ciu);
		}
		return aero;
	}

	private int verificarId(HttpServletRequest request) throws Exception {
		String idInput = request.getParameter("idAeropuerto");
		int id;
		try {
			id = Integer.parseInt(idInput);
		} catch (NumberFormatException e) {
			throw e;
		}
		return id;
	}

	private Aeropuerto verificarInputEditar(HttpServletRequest request) {
		String nombre = request.getParameter("nombre");
		String descAeropuerto = request.getParameter("descAeropuerto");

		Aeropuerto aero = null;

		if (!nombre.isEmpty() && !descAeropuerto.isEmpty()) {
			aero = new Aeropuerto();
			aero.setNombre(nombre);
			aero.setDescAeropuerto(descAeropuerto);
		}
		return aero;
	}
}
