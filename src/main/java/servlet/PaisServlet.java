package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pais;
import entities.Usuario;
import logic.CtrlPais;

/**
 * Servlet implementation class PaisServlet
 */
@WebServlet("/PaisServlet")
public class PaisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaisServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlPais cp = new CtrlPais();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "eliminar": {
					Pais pa = new Pais();
					try {
						int id = verificarId(request);
						pa.setIdPais(id);
						cp.delete(pa);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;

				}
				case "redirecAgregarPais": {
					request.getRequestDispatcher("WEB-INF/ui-pais/AgregarPais.jsp").forward(request, response);
					break;
				}
				case "redirecEditar": {
					Pais p = new Pais();
					try {
						int id = verificarId(request);
						p.setIdPais(id);
						Pais pa = cp.getById(p);
						request.setAttribute("Pais", pa);
						request.getRequestDispatcher("WEB-INF/ui-pais/EditarPais.jsp").forward(request, response);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}

				}
				}
			}
		}
		try {
			LinkedList<Pais> paises = cp.getAll();
			request.setAttribute("listaPaises", paises);
		} catch (Exception e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("WEB-INF/ui-pais/ListarPaises.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlPais cp = new CtrlPais();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			if (accion != null) {
				switch (accion) {
				case "insertar": {
					Pais pa = verificarInput(request);
					try {
						cp.add(pa);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "editarPais": {
					try {
						int idPais = verificarId(request);
						Pais pa = verificarInput(request);
						pa.setIdPais(idPais);
						cp.edit(pa);
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

	private Pais verificarInput(HttpServletRequest request) {
		Pais p = null;
		String nombre = request.getParameter("nombre");
		if (!nombre.isEmpty()) {
			p = new Pais();
			p.setNombre(nombre);
		}
		return p;
	}

	private int verificarId(HttpServletRequest request) throws Exception {
		String idInput = request.getParameter("idPais");
		int id;
		try {
			id = Integer.parseInt(idInput);
		} catch (NumberFormatException e) {
			throw e;
		}
		return id;
	}
}
