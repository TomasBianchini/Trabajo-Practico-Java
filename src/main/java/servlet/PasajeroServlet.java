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
		// TODO Auto-generated method stub
		
		CtrlPasajero ct = new CtrlPasajero();
		LinkedList<Pasajero> lp = ct.getAll();
		request.setAttribute("listaPasajero", lp);
		String accion = request.getParameter("accion");
		
		if(accion!=null)
		{
			switch (accion){
				case "modificar":
				{
					this.modificarPasajero(request,response);
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
			//this.accionDefault(request,response);
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
				case "modificar":
				{
					this.modificarPasajero(request,response);
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
				
			default:
			}
		}
		else {
			//this.accionDefault(request,response);
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
	
	/*private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int codUser = Integer.parseInt(request.getParameter("codUser"));
		Usuario usuario = new Usuario();
			usuario.setCodUser(codUser);
		Usuario usu = new LogicUsuario().getBycod(usuario);
			request.setAttribute("usuario", usu);
		request.getRequestDispatcher("/EditarUsuario.jsp").forward(request, response);
		
	}*/
	
	private void modificarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String dniPasajero = request.getParameter("dniPasajero");
		Pasajero pas = new Pasajero();
		pas.setDni(dniPasajero);
		Pasajero pasajeroActual = new CtrlPasajero().getByDni(pas);
			String apellido = request.getParameter("apellido");
			String nombre =  request.getParameter("nombre");
			String email = request.getParameter("email");
			String contraseña = request.getParameter("contraseña");
			
			pasajeroActual.setApellido(apellido);
			pasajeroActual.setEmail(email);
			pasajeroActual.setNombre(nombre);
			pasajeroActual.setContrasenia(contraseña);
			
		new CtrlPasajero().edit(pasajeroActual);
		request.getRequestDispatcher("/ListaPasajero.jsp").forward(request, response);
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
