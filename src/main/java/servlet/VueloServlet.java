package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Aeropuerto;
import entities.Avion;
import entities.Vuelo;
import logic.CtrlVuelo;
import java.time.LocalDateTime;
import java.util.LinkedList; 

/**
 * Servlet implementation class VueloServlet
 */
@WebServlet("/VueloServlet")
public class VueloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VueloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlVuelo cv = new CtrlVuelo(); 
		LinkedList<Vuelo> vuelos = cv.getAll(); 
		request.setAttribute("listaVuelos", vuelos);
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion){

				case "eliminar":
				{
					int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
					Vuelo vue = new Vuelo();
					vue.setIdvuelo(idvuelo);
					
					new CtrlVuelo().delete(vue);
					
					break;
				}
				case "AgregarVuelo": 
				{
					request.getRequestDispatcher("WEB-INF/AgregarVuelo.jsp").forward(request, response);
					break;
				}			
			default:
				request.getRequestDispatcher("WEB-INF/ListarVuelo.jsp").forward(request, response);
			}
		}
		else {			
			request.getRequestDispatcher("WEB-INF/ListarVuelo.jsp").forward(request, response);
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

					int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
					//DateTime fechaHoraSalida =  request.getParameter("fechaHoraSalida");
					//String fechaHoraLlegada = request.getParameter("fechaHoraLlegada");
					int idAeropuertoOrigen = Integer.parseInt(request.getParameter("idAeropuertoOrigen"));
					int idAeropuertoDestino = Integer.parseInt(request.getParameter("idAeropuertoDestino"));
					int idAvion = Integer.parseInt(request.getParameter("idAvion"));
					Vuelo vue = new Vuelo();

					vue.setIdvuelo(idvuelo);
					vue.setAeropuertoOrigen(new Aeropuerto());
					vue.setAeropuertoDestino(new Aeropuerto());
					vue.getAeropuertoOrigen().setIdAeropuerto(idAeropuertoOrigen);
					vue.getAeropuertoDestino().setIdAeropuerto(idAeropuertoDestino);
					vue.setAvion(new Avion());
					vue.getAvion().setIdAvion(idAvion); 

					new CtrlVuelo().add(vue);
					
					break;
				}
				case "modificar":
				{
					//Implementar
				}
				case "eliminar":
				{
					int idvuelo = Integer.parseInt(request.getParameter("idvuelo"));
					Vuelo vue = new Vuelo();
					vue.setIdvuelo(idvuelo);
					
					new CtrlVuelo().delete(vue);
					
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
