package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vuelo;
import logic.CtrlVuelo;

/**
 * Servlet implementation class PasajeServlet
 */
@WebServlet("/PasajeServlet")
public class PasajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasajeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CtrlPasaje cp = new CtrlPasaje();
		CtrlVuelo cv = new CtrlVuelo();
		int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
		Vuelo vue = new Vuelo();
		vue.setIdvuelo(idvuelo);
		Vuelo v = new Vuelo();
		v = cv.getById(vue);
		request.setAttribute("Vuelo", v);
		request.getRequestDispatcher("WEB-INF/ui-pasaje/ComprarPasaje.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
