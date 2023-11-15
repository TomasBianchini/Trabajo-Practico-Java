package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pais;
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
		if (accion != null) {
			switch (accion) {
			case "eliminar": {
				int idPais = Integer.parseInt(request.getParameter("idPais"));
				Pais pa = new Pais();
				pa.setIdPais(idPais);
				try {
					cp.delete(pa);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;

			}
			case "AgregarPais": {
				request.getRequestDispatcher("WEB-INF/ui-pais/AgregarPais.jsp").forward(request, response);
				break;
			}
			case "editar": {
				Pais p = new Pais();
				int idPais = Integer.parseInt(request.getParameter("idPais"));
				p.setIdPais(idPais);
				Pais pa = cp.getById(p);
				request.setAttribute("Pais", pa);
				request.getRequestDispatcher("WEB-INF/ui-pais/EditarPais.jsp").forward(request, response);
			}
			}
		}
		LinkedList<Pais> paises = cp.getAll();
		request.setAttribute("listaPaises", paises);
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
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				String nombre = request.getParameter("nombre");
				Pais pa = new Pais();
				pa.setNombre(nombre);

				try {
					cp.add(pa);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;

			}
			case "editarPais": {
				int idPais = Integer.parseInt(request.getParameter("idPais"));
				String nombre = request.getParameter("nombre");
				Pais pa = new Pais();
				pa.setIdPais(idPais);
				pa.setNombre(nombre);

				try {
					cp.edit(pa);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			}
			doGet(request, response);
		}
	}
}
