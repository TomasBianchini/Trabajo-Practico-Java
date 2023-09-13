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
				request.getRequestDispatcher("WEB-INF/AgregarAsiento.jsp").forward(request, response);
				break;
			}
			case "eliminar": {
				int idavion = Integer.parseInt(request.getParameter("idAvion"));
				int fila = Integer.parseInt(request.getParameter("fila"));
				int numero = Integer.parseInt(request.getParameter("numero"));
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				new CtrlAsiento().delete(asi);
				break;
			}
			default:
				request.getRequestDispatcher("WEB-INF/ListarAsiento.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/ListarAsiento.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				int idavion = Integer.parseInt(request.getParameter("inputIdAvion"));
				int fila = Integer.parseInt(request.getParameter("inputFila"));
				int numero = Integer.parseInt(request.getParameter("inputNumero"));
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				new CtrlAsiento().add(asi);
				break;
			}
			case "eliminar": {
				int idavion = Integer.parseInt(request.getParameter("inputIdAvion"));
				int fila = Integer.parseInt(request.getParameter("inputFila"));
				int numero = Integer.parseInt(request.getParameter("inputNumero"));
				Asiento asi = new Asiento();
				asi.setFila(fila);
				asi.setNumero(numero);
				asi.setAvion(new Avion());
				asi.getAvion().setIdAvion(idavion);
				new CtrlAsiento().delete(asi);
				break;
			}
			}
		} else {
			request.getRequestDispatcher("WEB-INF/ListarAeropuerto.jsp").forward(request, response);
		}
		doGet(request, response);
	}
}