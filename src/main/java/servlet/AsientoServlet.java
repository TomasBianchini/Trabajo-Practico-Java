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

@WebServlet("/AsientoServlet")
public class AsientoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AsientoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAsiento ca = new CtrlAsiento();
		LinkedList<Asiento> asientos = ca.getAll();
		request.setAttribute("listaAsientos", asientos);
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "AgregarAsiento": {
				request.getRequestDispatcher("WEB-INF/ui-asiento/AgregarAsiento.jsp").forward(request, response);
				break;
			}
			case "eliminar": {
				int idavion = Integer.parseInt(request.getParameter("idAvion"));
				String fila = request.getParameter("fila");
				String numero = request.getParameter("numero");
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				new CtrlAsiento().delete(asi);
				request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
				break;
			}
			default:
				request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				int idavion = Integer.parseInt(request.getParameter("inputIdAvion"));
				String fila = request.getParameter("inputFila");
				String numero = request.getParameter("inputNumero");
				String tipo = request.getParameter("tipo");
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setTipo(tipo);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				new CtrlAsiento().add(asi);
				break;
			}
			}
		}
		doGet(request, response);
	}
}