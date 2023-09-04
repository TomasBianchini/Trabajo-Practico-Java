package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pasajero;
import logic.CtrlPasajero;
/**
 * Servlet implementation class PasajeroServlet
 */
@WebServlet("/PasajeroServlet")
public class PasajeroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasajeroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		CtrlPasajero ct = new CtrlPasajero();
		LinkedList<Pasajero> lp = ct.getAll();
		request.setAttribute("listaPasajero", lp);
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion){
				case "editar":
				{
					Pasajero p = new Pasajero(); 
					String dniPasajero = request.getParameter("dniPasajero");
					p.setDni(dniPasajero); 
					CtrlPasajero cp = new CtrlPasajero(); 
					Pasajero pa = cp.getByDni(p); 
					request.setAttribute("Pasajero", pa);
					request.getRequestDispatcher("WEB-INF/EditarPasajero.jsp").forward(request, response);
					break;
				}
				case "eliminar":
				{
					String dni = request.getParameter("dniPasajero");
					Pasajero pas = new Pasajero();
					pas.setDni(dni);
					new CtrlPasajero().delete(pas);
					request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);
					break;
				}
				case "listarPasajero": 
				{
					this.listarPasajero(request,response);
					break;
				}
				case "AgregarPasajero": 
				{
					request.getRequestDispatcher("WEB-INF/AgregarPasajero.jsp").forward(request, response);
					break;
				}

			default:
				request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String accion = request.getParameter("accion");

		if(accion!=null)
		{
			switch (accion){
				case "insertarPasajero":{
					String dni = request.getParameter("dni");
					String nombre =  request.getParameter("nombre");
					String apellido = request.getParameter("apellido");
					String email = request.getParameter("email");
					String contrasenia = request.getParameter("contrasenia");
					Pasajero pasajero = new Pasajero();

						pasajero.setApellido(apellido);
						pasajero.setDni(dni);
						pasajero.setNombre(nombre);
						pasajero.setEmail(email);
						pasajero.setContrasenia(contrasenia);
					new CtrlPasajero().add(pasajero);
					//request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
					break;
				}
				case "editarPasajero":
				{
					String dniPasajero = request.getParameter("dniPasajero");
					String apellido = request.getParameter("apellido");
					String nombre =  request.getParameter("nombre");
					String email = request.getParameter("email");
					String contrasenia = request.getParameter("contrasenia");
					Pasajero pas = new Pasajero();
					pas.setDni(dniPasajero);
					pas.setApellido(apellido);
					pas.setEmail(email);
					pas.setNombre(nombre);
					pas.setContrasenia(contrasenia);
					new CtrlPasajero().edit(pas);
					break;
				}
				case "eliminar":
				{
					String dni = request.getParameter("dniPasajero");
					Pasajero pas = new Pasajero();
					pas.setDni(dni);
					new CtrlPasajero().delete(pas);
					request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
					break;
				}
			}
		}
		doGet(request, response);
    }
	

	private void insertarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("dni");
		String nombre =  request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String contraseña = request.getParameter("contraseña");
		Pasajero pasajero = new Pasajero();

			pasajero.setApellido(apellido);
			pasajero.setDni(dni);
			pasajero.setNombre(nombre);
			pasajero.setEmail(email);
			pasajero.setContrasenia(contraseña);
		new CtrlPasajero().add(pasajero);
		request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
	}


	private void eliminarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dniPasajero");
		Pasajero pas = new Pasajero();
		pas.setDni(dni);
		
		new CtrlPasajero().delete(pas);
		request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
		
	}
	
	private void listarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlPasajero ct = new CtrlPasajero();
		LinkedList<Pasajero> lp = ct.getAll();
		request.setAttribute("listaPersonas", lp);
		request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);

	}
	

	
}
