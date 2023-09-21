package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Asiento;
import entities.Avion;
import logic.CtrlAsiento;
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
		CtrlAsiento cas = new CtrlAsiento();
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "editar": {
				Avion a = new Avion();
				int idAvion = Integer.parseInt(request.getParameter("idAvion"));
				a.setIdAvion(idAvion);
				Avion avi = ca.getById(a);
				request.setAttribute("Avion", avi);
				request.getRequestDispatcher("WEB-INF/EditarAvion.jsp").forward(request, response);
				break;
			}

			case "eliminar": {
				int idAvion = Integer.parseInt(request.getParameter("idAvion"));
				Avion avi = new Avion();
				avi.setIdAvion(idAvion);
				ca.delete(avi);
				break;
			}
			case "AgregarAvion": {
				request.getRequestDispatcher("WEB-INF/ui-avion/AgregarAvion.jsp").forward(request, response);
				break;
			}
			case "listarAsientos": {
				Avion a = new Avion();
				int idAvion = Integer.parseInt(request.getParameter("idAvion"));
				a.setIdAvion(idAvion);
				Avion avi = ca.getById(a);
				request.setAttribute("avion", avi);
				request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
				break;
			}
			case "AgregarAsiento": {
				Avion a = new Avion();
				int idAvion = Integer.parseInt(request.getParameter("idAvion"));
				a.setIdAvion(idAvion);
				Avion avi = ca.getById(a);
				request.setAttribute("avion", avi);
				request.getRequestDispatcher("WEB-INF/ui-asiento/AgregarAsiento.jsp").forward(request, response);
				break;
			}
			case "eliminarAsiento": {
				int idavion = Integer.parseInt(request.getParameter("idAvion"));
				String fila = request.getParameter("fila");
				String numero = request.getParameter("numero");
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				cas.delete(asi);
				Avion a = new Avion();
				a.setIdAvion(idavion);
				Avion avi = ca.getById(a);
				request.setAttribute("avion", avi);
				request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
				break;
			}
			}
		}
		LinkedList<Avion> la = ca.getAll();
		request.setAttribute("listaAviones", la);
		request.getRequestDispatcher("WEB-INF/ui-avion/ListarAvion.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAvion ca = new CtrlAvion();
		CtrlAsiento cas = new CtrlAsiento();
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
				ca.add(avion);
				break;
			}
			case "insertarAsiento": {
				int idavion = Integer.parseInt(request.getParameter("IdAvion"));
				String fila = request.getParameter("inputFila");
				String numero = request.getParameter("inputNumero");
				String tipo = request.getParameter("tipo");
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setTipo(tipo);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				cas.add(asi);
				Avion a = new Avion();
				a.setIdAvion(idavion);
				Avion avi = ca.getById(a);
				request.setAttribute("avion", avi);
				request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
				break;
			}

			}
			doGet(request, response);

		}
	}
}
