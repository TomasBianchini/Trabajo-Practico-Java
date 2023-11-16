package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasaje;
import entities.Usuario;
import logic.CtrlPasaje;
import logic.CtrlUsuario;

/**
 * Servlet implementation class PasajeroServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlUsuario cu = new CtrlUsuario();
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "editar": {
				Usuario us = new Usuario();
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				us.setIdUsuario(idUsuario);
				Usuario usu = cu.getById(us);
				request.setAttribute("Usuario", usu);
				request.getRequestDispatcher("WEB-INF/ui-usuario/EditarUsuario.jsp").forward(request, response);
				break;
			}
			case "eliminar": {
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				Usuario usu = new Usuario();
				usu.setIdUsuario(idUsuario);

				try {
					cu.delete(usu);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			case "AgregarUsuario": {
				request.getRequestDispatcher("WEB-INF/ui-usuario/AgregarUsuario.jsp").forward(request, response);
				break;
			}
			case "misPasajes":
				Usuario us = new Usuario();
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				us.setIdUsuario(idUsuario);
				CtrlPasaje cpasaje = new CtrlPasaje();
				LinkedList<Pasaje> pasajes = cpasaje.getByIdUsuario(us);
				request.setAttribute("listaPasajes", pasajes);
				request.getRequestDispatcher("WEB-INF/ui-usuario/ListaPasajes.jsp").forward(request, response);
				break;
			}
		}
		LinkedList<Usuario> lu = cu.getAll();
		request.setAttribute("listaUsuario", lu);
		request.getRequestDispatcher("WEB-INF/ui-usuario/ListarUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlUsuario cu = new CtrlUsuario();
		if (accion != null) {
			switch (accion) {
			case "insertarUsuario": {
				String tipo = request.getParameter("tipo");
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
				usuario.setTipo(tipo);
				usuario.setFechaNacimiento(fechaNacimiento);

				try {
					cu.add(usuario);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			case "editarUsuario": {
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				String tipo = request.getParameter("tipo");
				String nroDocumento = request.getParameter("nroDocumento");
				String tipoDocumento = request.getParameter("tipoDocumento");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String contrasenia = request.getParameter("contrasenia");
				LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
				Usuario usu = new Usuario();
				usu.setIdUsuario(idUsuario);
				usu.setApellido(apellido);
				usu.setNroDocumento(nroDocumento);
				usu.setTipoDocumento(tipoDocumento);
				usu.setNombre(nombre);
				usu.setEmail(email);
				usu.setContrasenia(contrasenia);
				usu.setTipo(tipo);
				usu.setFechaNacimiento(fechaNacimiento);

				try {
					cu.edit(usu);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			}
		}
		doGet(request, response);
	}

}
