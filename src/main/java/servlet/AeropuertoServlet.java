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
		// TODO Auto-generated constructor stub
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
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "editar": {
					Aeropuerto a = new Aeropuerto();
					int idAeropuerto = Integer.parseInt(request.getParameter("idAeropuerto"));
					a.setIdAeropuerto(idAeropuerto);
					Aeropuerto aero = ca.getById(a);
					request.setAttribute("Aeropuerto", aero);
					request.getRequestDispatcher("WEB-INF/ui-aeropuerto/EditarAeropuerto.jsp").forward(request,
							response);
					break;
				}
				case "eliminar": {
					int idAeropuerto = Integer.parseInt(request.getParameter("idAeropuerto"));
					Aeropuerto ae = new Aeropuerto();
					ae.setIdAeropuerto(idAeropuerto);

					try {
						new CtrlAeropuerto().delete(ae);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;
				}
				case "AgregarAeropuerto": {
					CtrlCiudad cc = new CtrlCiudad();
					LinkedList<Ciudad> ciudades = cc.getAll();
					request.setAttribute("listaCiudades", ciudades);
					request.getRequestDispatcher("WEB-INF/ui-aeropuerto/AgregarAeropuerto.jsp").forward(request,
							response);
					break;
				}
				}
			}
		}
		LinkedList<Aeropuerto> aeropuertos = ca.getAll();
		request.setAttribute("listaAeropuertos", aeropuertos);
		request.getRequestDispatcher("WEB-INF/ui-aeropuerto/ListarAeropuerto.jsp").forward(request, response);
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
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "insertar": {
					String nombre = request.getParameter("nombre");
					String desc = request.getParameter("descripcion");
					String nombreCiudad = request.getParameter("nombreCiudad");
					Aeropuerto ae = new Aeropuerto();
					CtrlCiudad cc = new CtrlCiudad();
					Ciudad ciu = new Ciudad();
					ciu.setNombre(nombreCiudad);
					ciu = cc.getByNombre(ciu);
					ae.setNombre(nombre);
					ae.setDescAeropuerto(desc);
					ae.setCiudad(ciu);

					try {
						ca.add(ae);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;
				}
				case "editarAeropuerto": {
					int idAeropuerto = Integer.parseInt(request.getParameter("idAeropuerto"));
					String descAeropuerto = request.getParameter("descAeropuerto");
					String nombre = request.getParameter("nombre");
					Aeropuerto a = new Aeropuerto();
					a.setIdAeropuerto(idAeropuerto);
					a.setNombre(nombre);
					a.setDescAeropuerto(descAeropuerto);

					try {
						ca.edit(a);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;
				}
				}
			}
		}
		doGet(request, response);
	}

}
