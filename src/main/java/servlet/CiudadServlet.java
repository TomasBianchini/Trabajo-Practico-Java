package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ciudad;
import entities.Pais;
import logic.CtrlCiudad;

/**
 * Servlet implementation class CiudadServlet
 */
@WebServlet("/CiudadServlet")
public class CiudadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CiudadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlCiudad cc = new CtrlCiudad(); 
		LinkedList<Ciudad> ciudadades= cc.getAll(); 
		request.setAttribute("listaCiudades", ciudadades);
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion){

				case "eliminar":
				{
					String codPostal = request.getParameter("codPostal");
					Ciudad ciu = new Ciudad();
					ciu.setCodPostal(codPostal);	
					new CtrlCiudad().delete(ciu);
					request.getRequestDispatcher("WEB-INF/ListarCiudad.jsp").forward(request, response);
					break;
				}
				case "AgregarCiudad": 
				{
					request.getRequestDispatcher("WEB-INF/AgregarCiudad.jsp").forward(request, response);
					break;
				}			
			default:
				request.getRequestDispatcher("WEB-INF/ListarCiudad.jsp").forward(request, response);
			}
		}
		else {			
			request.getRequestDispatcher("WEB-INF/ListarCiudad.jsp").forward(request, response);
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
				case "insertar":{
					String codPostal =  request.getParameter("codPostal");
					String nombre =  request.getParameter("nombre");
					int idPais =  Integer.parseInt(request.getParameter("idPais"));
					Ciudad ciu = new Ciudad();
					ciu.setNombre(nombre);
					ciu.setCodPostal(codPostal); 
					ciu.setPais(new Pais()); 
					ciu.getPais().setIdPais(idPais);
						
					new CtrlCiudad().add(ciu);
					//request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
					break;
				}	

				case "eliminar":
				{
					String codPostal = request.getParameter("codPostal");
					Ciudad ciu = new Ciudad();
					ciu.setCodPostal(codPostal);				
					new CtrlCiudad().delete(ciu);
					request.getRequestDispatcher("/ListarCiudad.jsp").forward(request, response);
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
