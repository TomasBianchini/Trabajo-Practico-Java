package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Pasajero;
import logic.ctrlPasajero;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
				case "insertar":
					this.insertarPasajero(request,response);break;
				case "modificar":
					this.modificarPasajero(request,response);break;
				case "eliminar":
					this.eliminarPasajero(request,response);break;
			
				
			default:
				//  this.accionDefault(request,response);
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
			pasajero.setContraseña(contraseña);
		new ctrlPasajero().addPasajero(pasajero);
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
		Pasajero pasajeroActual = new ctrlPasajero().getByDni(pas);
			String apellido = request.getParameter("apellido");
			String nombre =  request.getParameter("nombre");
			String email = request.getParameter("email");
			String contraseña = request.getParameter("contraseña");
			
			pasajeroActual.setApellido(apellido);
			pasajeroActual.setEmail(email);
			pasajeroActual.setNombre(nombre);
			pasajeroActual.setContraseña(contraseña);
			
		new ctrlPasajero().editPasajero(pasajeroActual);
		request.getRequestDispatcher("/ListaUsuario.jsp").forward(request, response);
	}
	
	private void eliminarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dniPasajero");
		Pasajero pas = new Pasajero();
		pas.setDni(dni);
		
		new ctrlPasajero().deletePasajero(pas);
		request.getRequestDispatcher("/ListaUsuario.jsp").forward(request, response);
		
	}

}
