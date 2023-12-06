package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.CtrlUsuario;

/**
 * Servlet implementation class singup
 */
@WebServlet("/singup")
public class singup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public singup() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlUsuario cu = new CtrlUsuario();
		String nroDocumento = request.getParameter("nroDocumento");
		String tipoDocumento = request.getParameter("tipoDocumento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String contrasenia = request.getParameter("contrasenia");
		LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
		Usuario usuario = new Usuario();
		usuario.setApellido(apellido);
		usuario.setNroDocumento(nroDocumento);
		usuario.setTipoDocumento(tipoDocumento);
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContrasenia(contrasenia);
		usuario.setTipo("user");
		usuario.setFechaNacimiento(fechaNacimiento);
		try {
			cu.add(usuario);
		} catch (Exception e) {
			String message = "error :" + e.getMessage();
			request.setAttribute("message", message);
		}
		response.sendRedirect("index.html");
	}

}
