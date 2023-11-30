package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import entities.Vuelo;
import logic.CtrlLogin;
import logic.CtrlVuelo;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signin() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usu = new Usuario();
		String email = request.getParameter("email");
		String contrasenia = request.getParameter("contrasenia");
		usu.setEmail(email);
		usu.setContrasenia(contrasenia);

		CtrlLogin ctrl = new CtrlLogin();
		try {
			usu = ctrl.validate(usu);
			CtrlVuelo cv = new CtrlVuelo();
			LinkedList<Vuelo> vuelos = cv.getAll();
			request.setAttribute("listaVuelos", vuelos);
		} catch (Exception e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}

		if (usu != null) {
			request.getSession().setAttribute("usuario", usu);
			request.getRequestDispatcher("Vuelos.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.html").forward(request, response);
		}

	}

}
