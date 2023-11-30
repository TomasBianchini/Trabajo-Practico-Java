package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ciudad;
import entities.Pais;
import entities.Usuario;
import logic.CtrlCiudad;
import logic.CtrlPais;

/**
 * Servlet implementation class CiudadServlet
 */
@WebServlet("/CiudadServlet")
public class CiudadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CiudadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlCiudad cc = new CtrlCiudad();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "eliminar": {

					try {
						Ciudad ciu = verificarInput(request);
						cc.delete(ciu);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;

				}
				case "redirecEditar": {
					try {
						Ciudad p = verificarInput(request);
						Ciudad ciu = cc.getById(p);
						request.setAttribute("Ciudad", ciu);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "redirecAgregarCiudad": {
					CtrlPais cp = new CtrlPais();
					try {
						LinkedList<Pais> paises = cp.getAll();
						request.setAttribute("listaPaises", paises);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					request.getRequestDispatcher("WEB-INF/ui-ciudad/AgregarCiudad.jsp").forward(request, response);
					break;
				}
				}
			}
		}
		try {
			LinkedList<Ciudad> ciudades = cc.getAll();
			request.setAttribute("listaCiudades", ciudades);
		} catch (Exception e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("WEB-INF/ui-ciudad/ListarCiudad.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlCiudad cc = new CtrlCiudad();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "insertar": {
					try {
						Ciudad ciu = verificarInput(request);
						cc.add(ciu);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;
				}
				case "editarCiudad": {
					try {
						Ciudad ciu = verificarInput(request);
						cc.edit(ciu);
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

	private Ciudad verificarInput(HttpServletRequest request) throws Exception {
		Ciudad c = null;
		String codPostal = request.getParameter("codPostal");
		String nombre = request.getParameter("nombre");
		String nombrePais = request.getParameter("pais");

		if (!codPostal.isEmpty()) {
			if (nombre != null && nombrePais != null) {
				if (!nombre.isEmpty() && !nombrePais.isEmpty()) {
					c = new Ciudad();
					CtrlPais cp = new CtrlPais();
					Pais pais = new Pais();
					pais.setNombre(nombrePais);
					pais = cp.getByNombre(pais);
					c.setNombre(nombre);
					c.setCodPostal(codPostal);
					c.setPais(pais);
				}
			} else {
				c = new Ciudad();
				c.setCodPostal(codPostal);
			}
		}
		return c;
	}
}
