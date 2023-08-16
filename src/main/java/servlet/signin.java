package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasajero;
import logic.CtrlLogin;


/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pasajero pas = new Pasajero(); 
		String email =request.getParameter("email"); 
		String password = request.getParameter("password"); 
		pas.setEmail(email);
		pas.setContraseña(password);
		
		CtrlLogin ctrl = new CtrlLogin(); 
		pas = ctrl.validate(pas); 
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.getSession().setAttribute("pasajero", pas);
		
		request.getRequestDispatcher("WEB-INF/MenuPrincipal.jsp").forward(request, response);
	}

}
