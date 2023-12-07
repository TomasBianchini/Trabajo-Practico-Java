package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasaje;
import logic.CtrlPasaje;

/**
 * Servlet implementation class FinalizarPasaje
 */
@WebServlet("/FinalizarPasaje")
public class FinalizarPasaje extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinalizarPasaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		int idPasaje = Integer.parseInt(request.getParameter("id"));
		CtrlPasaje cp = new CtrlPasaje();
		Pasaje pas = new Pasaje();
		pas.setIdPasaje(idPasaje);
		Pasaje pasaje = null;
		switch (accion) {
		case "mostrar": {
			try {
				pasaje = cp.getById(pas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("Pasaje", pasaje);
			request.getRequestDispatcher("finalizarPasaje.jsp").forward(request, response);
			break;
		}
		case "finalizarPasaje": {
			try {
				pas.setEstado("Finalizado");
				cp.cambiarEstado(pas);
				pasaje = cp.getById(pas);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("Pasaje", pasaje);
			request.getRequestDispatcher("finalizarPasaje.jsp").forward(request, response);

			break;
		}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
