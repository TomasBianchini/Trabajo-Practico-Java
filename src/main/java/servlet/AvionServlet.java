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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlAvion ca = new CtrlAvion();
		LinkedList<Avion> la = ca.getAll();
		request.setAttribute("listaAviones", la);
		String accion = request.getParameter("accion");
		
		if(accion!=null)
		{
			switch (accion){
				case "modificar":
				{
					//Implementar
				}
				case "eliminar":
				{
					int idAvion = Integer.parseInt(request.getParameter("idAvion"));
					Avion avi = new Avion();
					avi.setIdAvion(idAvion );
					new CtrlAvion().delete(avi);
					request.getRequestDispatcher("WEB-INF/ListarAvion.jsp").forward(request, response);
					break;
				}
				case "AgregarAvion": 
				{
					request.getRequestDispatcher("WEB-INF/AgregarAvion.jsp").forward(request, response);
					break;
				}	
			default:
				request.getRequestDispatcher("WEB-INF/ListarAvion.jsp").forward(request, response);
			}
		}
		else {
			//this.accionDefault(request,response);
			request.getRequestDispatcher("WEB-INF/ListarAvion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion){
				case "insertarAvion":{
					int cantAsientos = Integer.parseInt(request.getParameter("cantAsientos"));

					Avion avion = new Avion();
					avion.setCantAsientos(cantAsientos);
					new CtrlAvion ().add(avion);
					
					break;
				}
				case "modificar":
				{
					//Implementar
				}
				case "eliminar":
				{
					int idAvion = Integer.parseInt(request.getParameter("idAvion"));
					Avion avi = new Avion();
					avi.setIdAvion(idAvion);
					
					new CtrlAvion().delete(avi);
					request.getRequestDispatcher("WEB-INF/ListarAvion.jsp").forward(request, response);
					break;
				}
				
		}
		doGet(request, response);

		}
	}
}


