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
import logic.CtrlAeropuerto;

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
		if (accion != null) {
			switch (accion) {
			case "editar": {
				Aeropuerto a = new Aeropuerto();
				int idAeropuerto = Integer.parseInt(request.getParameter("idAeropuerto"));
				a.setIdAeropuerto(idAeropuerto);
				Aeropuerto aero = ca.getById(a);
				request.setAttribute("Aeropuerto", aero);
				request.getRequestDispatcher("WEB-INF/ui-aeropuerto/EditarAeropuerto.jsp").forward(request, response);
				break;
			}
			case "eliminar": {
				int idAeropuerto = Integer.parseInt(request.getParameter("idAeropuerto"));
				Aeropuerto ae = new Aeropuerto();
				ae.setIdAeropuerto(idAeropuerto);
				new CtrlAeropuerto().delete(ae);
				break;
			}
			case "AgregarAeropuerto": {
				request.getRequestDispatcher("WEB-INF/ui-aeropuerto/AgregarAeropuerto.jsp").forward(request, response);
				break;
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
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				String nombre = request.getParameter("nombre");
				String desc = request.getParameter("descripcion");
				String codPostal = request.getParameter("codPostal");
				Aeropuerto ae = new Aeropuerto();
				ae.setNombre(nombre);
				ae.setDescAeropuerto(desc);
				ae.setCiudad(new Ciudad());
				ae.getCiudad().setCodPostal(codPostal);
				ca.add(ae);
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
				ca.edit(a);
				break;
			}
			}
		} else {
		}
		doGet(request, response);
	}

}
