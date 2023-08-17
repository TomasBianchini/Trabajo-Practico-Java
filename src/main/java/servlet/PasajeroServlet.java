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
		LinkedList<Pasajero> lp = ct.getAllPasajero();
		request.setAttribute("listaPasajero", lp);
		request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String accion = request.getParameter("accion");
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		/*String query= request.getPathInfo();
		if(query!=null)
		{
			switch (query){
				case "insertar":{
					this.insertarPasajero(request,response);
					break;
				}
				case "modificar":
				{
					this.modificarPasajero(request,response);
					break;
				}
				case "eliminar":
				{
					this.eliminarPasajero(request,response);
					break;
				}
				case "listarPasajero": 
				{
					this.listarPasajero(request,response);
					break;
				}
			
				
			default:
			}
		}
		else {
			//this.accionDefault(request,response);
		}*/
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
		new CtrlPasajero().addPasajero(pasajero);
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
			
		new CtrlPasajero().editPasajero(pasajeroActual);
		request.getRequestDispatcher("/ListaPasajero.jsp").forward(request, response);
	}
	
	private void eliminarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dniPasajero");
		Pasajero pas = new Pasajero();
		pas.setDni(dni);
		
		new CtrlPasajero().deletePasajero(pas);
		request.getRequestDispatcher("/ListarPasajero.jsp").forward(request, response);
		
	}
	
	private void listarPasajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlPasajero ct = new CtrlPasajero();
		LinkedList<Pasajero> lp = ct.getAllPasajero();
		request.setAttribute("listaPersonas", lp);
		request.getRequestDispatcher("WEB-INF/ListarPasajero.jsp").forward(request, response);

	}

}
