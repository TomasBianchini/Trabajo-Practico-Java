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
import logic.CtrlPais;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlCiudad cc = new CtrlCiudad();
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "eliminar": {
				String codPostal = request.getParameter("codPostal");
				Ciudad ciu = new Ciudad();
				ciu.setCodPostal(codPostal);
				try {
					cc.delete(ciu);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;

			}
			case "editar": {
				Ciudad p = new Ciudad();
				String codPostal = request.getParameter("codPostal");
				p.setCodPostal(codPostal);
				Ciudad ciu = cc.getById(p);
				request.setAttribute("Ciudad", ciu);
				request.getRequestDispatcher("WEB-INF/ui-ciudad/EditarCiudad.jsp").forward(request, response);
			}

			case "AgregarCiudad": {
				CtrlPais cp = new CtrlPais();
				LinkedList<Pais> paises = cp.getAll();
				request.setAttribute("listaPaises", paises);
				request.getRequestDispatcher("WEB-INF/ui-ciudad/AgregarCiudad.jsp").forward(request, response);
				break;
			}
			}
		}
		LinkedList<Ciudad> ciudades = cc.getAll();
		request.setAttribute("listaCiudades", ciudades);
		request.getRequestDispatcher("WEB-INF/ui-ciudad/ListarCiudad.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		CtrlCiudad cc = new CtrlCiudad();
		if (accion != null) {
			switch (accion) {
			case "insertar": {
				String codPostal = request.getParameter("codPostal");
				String nombre = request.getParameter("nombre");
				String nombrePais = request.getParameter("pais");
				Ciudad ciu = new Ciudad();
				Pais pais = new Pais();
				CtrlPais cp = new CtrlPais();
				pais.setNombre(nombrePais);
				pais = cp.getByNombre(pais);
				ciu.setNombre(nombre);
				ciu.setCodPostal(codPostal);
				ciu.setPais(pais);
				try {
					cc.add(ciu);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;
			}
			case "editarCiudad": {
				String codPostal = request.getParameter("codPostal");
				String nombre = request.getParameter("nombre");
				Ciudad pa = new Ciudad();
				pa.setCodPostal(codPostal);
				pa.setNombre(nombre);
				try {
					cc.edit(pa);
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);

				}
				break;

			}
			}
		}
		doGet(request, response);

	}

}
