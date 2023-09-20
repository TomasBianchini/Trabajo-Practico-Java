package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasajero;
import logic.CtrlPasajero;

/**
 * Servlet implementation class PasajeroServlet
 */
@WebServlet("/PasajeroServlet")
public class PasajeroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasajeroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlPasajero cp = new CtrlPasajero();
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "editar": {
				Pasajero p = new Pasajero();
				String dniPasajero = request.getParameter("dniPasajero");
				p.setDni(dniPasajero);
				Pasajero pa = cp.getByDni(p);
				request.setAttribute("Pasajero", pa);
				request.getRequestDispatcher("WEB-INF/ui-pasajero/EditarPasajero.jsp").forward(request, response);
				break;
			}
			case "eliminar": {
				String dni = request.getParameter("dniPasajero");
				Pasajero pas = new Pasajero();
				pas.setDni(dni);
				cp.delete(pas);
				break;
			}
			case "AgregarPasajero": {
				request.getRequestDispatcher("WEB-INF/ui-pasajero/AgregarPasajero.jsp").forward(request, response);
				break;
			}
			}
		}
		LinkedList<Pasajero> lp = cp.getAll();
		request.setAttribute("listaPasajero", lp);
		request.getRequestDispatcher("WEB-INF/ui-pasajero/ListarPasajero.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlPasajero cp = new CtrlPasajero();
		if (accion != null) {
			switch (accion) {
			case "insertarPasajero": {
				String dni = request.getParameter("dni");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String contrasenia = request.getParameter("contrasenia");
				Pasajero pasajero = new Pasajero();
				pasajero.setApellido(apellido);
				pasajero.setDni(dni);
				pasajero.setNombre(nombre);
				pasajero.setEmail(email);
				pasajero.setContrasenia(contrasenia);
				cp.add(pasajero);
				break;
			}
			case "editarPasajero": {
				String dniPasajero = request.getParameter("dniPasajero");
				String apellido = request.getParameter("apellido");
				String nombre = request.getParameter("nombre");
				String email = request.getParameter("email");
				String contrasenia = request.getParameter("contrasenia");
				Pasajero pas = new Pasajero();
				pas.setDni(dniPasajero);
				pas.setApellido(apellido);
				pas.setEmail(email);
				pas.setNombre(nombre);
				pas.setContrasenia(contrasenia);
				cp.edit(pas);
				break;
			}
			}
		}
		doGet(request, response);
	}

}
