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
		LinkedList<Pais> paises = cp.getAll();
		request.setAttribute("listaPaises", paises);
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "eliminar": {
				int idPais = Integer.parseInt(request.getParameter("idPais"));
				Pais pa = new Pais();
				pa.setIdPais(idPais);
				new CtrlPais().delete(pa);
				request.getRequestDispatcher("WEB-INF/ui-pais/ListarPaises.jsp").forward(request, response);
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
				CtrlPais cpais = new CtrlPais();
				Pais pa = cpais.getById(p);
				request.setAttribute("Pais", pa);
				request.getRequestDispatcher("WEB-INF/ui-pais/EditarPais.jsp").forward(request, response);
			}

			default:
				request.getRequestDispatcher("WEB-INF/ui-pais/ListarPaises.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/ui-pais/ListarPaises.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				String nombre = request.getParameter("nombre");
				Pais pa = new Pais();
				pa.setNombre(nombre);
				new CtrlPais().add(pa);
				break;
			}
			case "editarPais": {
				int idPais = Integer.parseInt(request.getParameter("idPais"));
				String nombre = request.getParameter("nombre");
				Pais pa = new Pais();
				pa.setIdPais(idPais);
				pa.setNombre(nombre);
				new CtrlPais().edit(pa);
				break;
			}
			}
			doGet(request, response);
		}
	}
}
