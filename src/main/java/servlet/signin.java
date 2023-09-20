package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasajero;
import logic.CtrlLogin;

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
		Pasajero pas = new Pasajero();
		String email = request.getParameter("email");
		String contrasenia = request.getParameter("contrasenia");
		pas.setEmail(email);
		pas.setContrasenia(contrasenia);

		CtrlLogin ctrl = new CtrlLogin();

		request.setAttribute("email", email);
		request.setAttribute("contrasenia", contrasenia);

		pas = ctrl.validate(pas);

		if (pas != null) {
			request.getSession().setAttribute("pasajero", pas);
			request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
		} else {
			// request.setAttribute("titulo", "No se pudo iniciar sesión");
			// request.setAttribute("mensage", "Usuario o contraseña incorrectos");
			// request.setAttribute("pagina", "Login");
			request.getRequestDispatcher("index.html").forward(request, response);
		}

	}

}
