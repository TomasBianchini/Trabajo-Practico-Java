package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlUsuario cu = new CtrlUsuario();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		boolean reenviar = true;
		if (usuario == null) {
			response.sendRedirect("index.html");
		} else {
			if (accion != null) {
				switch (accion) {
				case "redirecEditar": {
					try {
						Usuario us = new Usuario();
						int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
						us.setIdUsuario(idUsuario);
						Usuario usu = cu.getById(us);
						request.setAttribute("Usuario", usu);
						request.getRequestDispatcher("WEB-INF/ui-usuario/EditarUsuario.jsp").forward(request, response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "eliminar": {
					if (usuario.getTipo().equals("admin")) {
						try {
							int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
							Usuario usu = new Usuario();
							usu.setIdUsuario(idUsuario);

							cu.delete(usu);

						} catch (Exception e) {
							String message = e.getMessage();
							request.setAttribute("message", message);

						}
					} else {
						request.getRequestDispatcher("VueloServlet").forward(request, response);
						reenviar = false;
					}
					break;
				}
				case "redirecAgregarUsuario": {
					if (usuario.getTipo().equals("admin")) {
						request.getRequestDispatcher("WEB-INF/ui-usuario/AgregarUsuario.jsp").forward(request,
								response);
						reenviar = false;
					} else {
						request.getRequestDispatcher("VueloServlet").forward(request, response);
						reenviar = false;
					}
					break;
				}
				case "misPasajes": {
					Usuario us = new Usuario();
					try {
						int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
						us.setIdUsuario(idUsuario);
						CtrlPasaje cpasaje = new CtrlPasaje();
						LinkedList<Pasaje> pasajes = cpasaje.getByIdUsuario(us);
						request.setAttribute("listaPasajes", pasajes);
						request.getRequestDispatcher("WEB-INF/ui-usuario/ListaPasajes.jsp").forward(request, response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "cerrarSesion": {
				}
					HttpSession sesion = request.getSession();
					sesion.invalidate();
					response.sendRedirect("index.html");
					reenviar = false;
					break;
				}

			}
			if (usuario.getTipo().equals("admin")) {
				try {
					LinkedList<Usuario> lu = cu.getAll();
					request.setAttribute("listaUsuario", lu);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				if (reenviar)
					request.getRequestDispatcher("WEB-INF/ui-usuario/ListarUsuario.jsp").forward(request, response);
			} else {
				if (reenviar)
					request.getRequestDispatcher("VueloServlet").forward(request, response);
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
		CtrlUsuario cu = new CtrlUsuario();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null) {
			response.sendRedirect("index.html");
		} else {
			switch (accion) {
			case "insertarUsuario": {
				if (user.getTipo().equals("admin")) {
					try {
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

						cu.add(usuario);
						request.setAttribute("message", "Usuario agregado correctamente");
						request.getRequestDispatcher("WEB-INF/ui-usuario/AgregarUsuario.jsp").forward(request,
								response);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
				} else {
					request.getRequestDispatcher("VueloServlet").forward(request, response);
				}
				break;

			}
			case "editarUsuario": {
				try {
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

					cu.edit(usu);

					doGet(request, response);

				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			default:
				doGet(request, response);
			}

		}

	}

}
