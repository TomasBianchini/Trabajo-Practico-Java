package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Avion;
import logic.CtrlAvion;

/**
 * Servlet implementation class AvionServlet
 */
@WebServlet("/AvionServlet")
public class AvionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AvionServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CtrlAvion ca = new CtrlAvion();
		LinkedList<Avion> la = ca.getAll();
		request.setAttribute("listaAviones", la);
		String accion = request.getParameter("accion");

		if (accion != null) {
			switch (accion) {
			/*
			 * case "editar": { Avion a = new Avion(); int idAvion =
			 * Integer.parseInt(request.getParameter("idAvion")); a.setIdAvion(idAvion);
			 * CtrlAvion av = new CtrlAvion(); Avion avi = av.getById(a);
			 * request.setAttribute("Avion", avi);
			 * request.getRequestDispatcher("WEB-INF/EditarAvion.jsp").forward(request,
			 * response); break; }
			 */
			case "eliminar": {
				int idAvion = Integer.parseInt(request.getParameter("idAvion"));
				Avion avi = new Avion();
				avi.setIdAvion(idAvion);
				new CtrlAvion().delete(avi);
				request.getRequestDispatcher("WEB-INF/ui-avion/ListarAvion.jsp").forward(request, response);
				break;
			}
			case "AgregarAvion": {
				request.getRequestDispatcher("WEB-INF/ui-avion/AgregarAvion.jsp").forward(request, response);
				break;
			}
			default:
				request.getRequestDispatcher("WEB-INF/ui-avion/ListarAvion.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/ui-avion/ListarAvion.jsp").forward(request, response);
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
			case "insertarAvion": {
				String marca = request.getParameter("marca");
				String modelo = request.getParameter("modelo");
				String anio = request.getParameter("anio");
				Avion avion = new Avion();
				avion.setMarca(marca);
				avion.setModelo(modelo);
				avion.setAnio(anio);
				new CtrlAvion().add(avion);
				break;
			}
			/*
			 * case "editarAvion": { int idAvion =
			 * Integer.parseInt(request.getParameter("idAvion")); int cantAsientos =
			 * Integer.parseInt(request.getParameter("cantidadAsientos")); Avion a = new
			 * Avion(); a.setIdAvion(idAvion); a.setCantAsientos(cantAsientos); new
			 * CtrlAvion().edit(a); break; }
			 */

			}
			doGet(request, response);

		}
	}
}
