package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Pais;
import entities.Pasajero;
import logic.CtrlPais;
import logic.CtrlPasajero;


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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlPais cp = new CtrlPais(); 
		LinkedList<Pais> paises= cp.getAll(); 
		request.setAttribute("listaPaises", paises);
		request.getRequestDispatcher("WEB-INF/ListarPaises.jsp").forward(request, response);

		String accion = request.getParameter("accion");
		
		if(accion!=null)
		{
			switch (accion){

				case "eliminar":
				{
					int idPais = Integer.parseInt(request.getParameter("idPais"));
					Pais pa = new Pais();
					pa.setIdPais(idPais);
					
					new CtrlPais().delete(pa);
					request.getRequestDispatcher("WEB-INF/ListarPaises.jsp").forward(request, response);
					break;
				}
				case "AgregarPais": 
				{
					request.getRequestDispatcher("WEB-INF/AgregarPais.jsp").forward(request, response);
					break;
				}
			
				
			default:
				request.getRequestDispatcher("WEB-INF/ListarPaises.jsp").forward(request, response);
			}
		}
		else {
			//this.accionDefault(request,response);
			request.getRequestDispatcher("WEB-INF/ListarPaises.jsp").forward(request, response);
		}
	
		//request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);
	}
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		if(accion!=null)
		{
			switch (accion){
				case "insertarPais":{
	
					String nombre =  request.getParameter("nombre");

					Pais pa = new Pais();

					pa.setNombre(nombre);
						
					new CtrlPais().add(pa);
					//request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
					break;
				}

				case "eliminar":
				{
					int idPais = Integer.parseInt(request.getParameter("idPais"));
					Pais pa = new Pais();
					pa.setIdPais(idPais);
					
					new CtrlPais().delete(pa);
					request.getRequestDispatcher("/ListarPaises.jsp").forward(request, response);
					break;
				}
			
				
			default:
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}

}
