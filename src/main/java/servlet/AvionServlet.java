package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Asiento;
import entities.Avion;
import entities.Usuario;
import logic.CtrlAsiento;
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

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAvion ca = new CtrlAvion();
		CtrlAsiento cas = new CtrlAsiento();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		boolean reenviar = true;
		if (usuario == null || !usuario.getTipo().equals("admin")) {
			response.sendRedirect("index.html");
		} else {
			if (accion != null) {
				switch (accion) {
				case "eliminar": {
					try {
						int idAvion = Integer.parseInt(request.getParameter("idAvion"));
						Avion avi = new Avion();
						avi.setIdAvion(idAvion);
						ca.delete(avi);
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;
				}
				case "redirecAgregarAvion": {
					request.getRequestDispatcher("WEB-INF/ui-avion/AgregarAvion.jsp").forward(request, response);
					reenviar = false;
					break;
				}
				case "listarAsientos": {
					try {
						Avion a = new Avion();
						int idAvion = Integer.parseInt(request.getParameter("idAvion"));
						a.setIdAvion(idAvion);
						Avion avi = ca.getById(a);
						request.setAttribute("avion", avi);
						request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "redirecAgregarAsiento": {
					try {
						Avion a = new Avion();
						int idAvion = Integer.parseInt(request.getParameter("idAvion"));
						a.setIdAvion(idAvion);
						Avion avi = ca.getById(a);
						request.setAttribute("avion", avi);
						request.getRequestDispatcher("WEB-INF/ui-asiento/AgregarAsiento.jsp").forward(request,
								response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);
					}
					break;
				}
				case "eliminarAsiento": {
					try {
						int idavion = Integer.parseInt(request.getParameter("idAvion"));
						String fila = request.getParameter("fila");
						String numero = request.getParameter("numero");
						Asiento asi = new Asiento();
						asi.setFila(fila);
						asi.setNumero(numero);
						asi.setAvion(new Avion());
						asi.getAvion().setIdAvion(idavion);

						cas.delete(asi);
						Avion a = new Avion();
						a.setIdAvion(idavion);
						Avion avi = ca.getById(a);
						request.setAttribute("avion", avi);
						request.getRequestDispatcher("WEB-INF/ui-asiento/ListarAsiento.jsp").forward(request, response);
						reenviar = false;
					} catch (Exception e) {
						String message = e.getMessage();
						request.setAttribute("message", message);

					}
					break;

				}
				}
			}
			try {
				LinkedList<Avion> la = ca.getAll();
				request.setAttribute("listaAviones", la);
			} catch (Exception e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
			}
			if (reenviar)
				request.getRequestDispatcher("WEB-INF/ui-avion/ListarAvion.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CtrlAvion ca = new CtrlAvion();
		CtrlAsiento cas = new CtrlAsiento();
		String accion = request.getParameter("accion");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		if (usuario == null || !usuario.getTipo().equals("admin")) {
			response.sendRedirect("index.html");
		} else {
			switch (accion) {
			case "insertarAvion": {

				try {
					Avion avi = verificarInput(request);
					ca.add(avi);
					request.setAttribute("message", "Avion agregado correctamente");
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);
				}
				request.getRequestDispatcher("WEB-INF/ui-avion/AgregarAvion.jsp").forward(request, response);
				break;
			}
			case "insertarAsiento": {
				try {
					int idavion = Integer.parseInt(request.getParameter("IdAvion"));
					String fila = request.getParameter("fila");
					String numero = request.getParameter("numero");
					String tipo = request.getParameter("tipo");
					Asiento asi = new Asiento();
					asi.setFila(fila);
					asi.setNumero(numero);
					asi.setTipo(tipo);
					asi.setAvion(new Avion());
					asi.getAvion().setIdAvion(idavion);

					cas.add(asi);
					Avion a = new Avion();
					a.setIdAvion(idavion);
					Avion avi = ca.getById(a);
					request.setAttribute("avion", avi);
					request.setAttribute("message", "Asiento agregado correctamente");
				} catch (Exception e) {
					String message = e.getMessage();
					request.setAttribute("message", message);
				}
				request.getRequestDispatcher("WEB-INF/ui-asiento/AgregarAsiento.jsp").forward(request, response);
				break;

			}
			default:
				doGet(request, response);
			}

		}
	}

	private Avion verificarInput(HttpServletRequest request) {
		Avion a = null;

		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String anio = request.getParameter("anio");

		if (marca != null && modelo != null && anio != null) {
			if (!marca.isEmpty() && !modelo.isEmpty() && !anio.isEmpty()) {

				a = new Avion();
				a.setMarca(marca);
				a.setModelo(modelo);
				a.setAnio(anio);

			}
		}
		return a;
	}
}
