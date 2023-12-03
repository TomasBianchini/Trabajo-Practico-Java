package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.Aeropuerto;
import entities.Asiento;
import entities.Avion;
import entities.Ciudad;
import entities.Pais;
import entities.Pasaje;
import entities.Usuario;
import entities.Vuelo;

public class DataPasaje {

	public LinkedList<Pasaje> getAll() throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Pasaje> pasajes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select pas.idPasaje, pas.estado, usu.tipoDocumento, usu.nroDocumento, usu.nombre, usu.apellido,"
							+ " asi.*, "
							+ " pO.nombre as nPaisO, pD.nombre as nPaisD, ciuD.nombre as nCiudadD, vue.*, ciuO.nombre as nCiudadO, "
							+ " aeroO.nombre as nAeroO, aeroD.nombre as nAeroD " + " from pasaje pas "
							+ " inner join vuelo vue on vue.idVuelo = pas.idVuelo"
							+ " inner join usuario usu on usu.idUsuario = pas.idUsuario"
							+ "	inner join asiento asi on asi.fila = pas.fila and asi.numero = pas.numero and asi.idAvion = pas.idAvion "
							+ " inner join aeropuerto aeroO on aeroO.idaeropuerto = vue.idAeropuertoOrigen"
							+ " inner join aeropuerto aeroD on aeroD.idaeropuerto = vue.idAeropuertoDestino"
							+ " inner join ciudad ciuO on ciuO.codPostal = aeroO.codPostal "
							+ " inner join ciudad ciuD on ciuD.codPostal = aeroD.codPostal "
							+ " inner join pais pO on pO.idpais = ciuO.idPais"
							+ " inner join pais pD on pD.idpais = ciuD.idPais");

			if (rs != null) {
				while (rs.next()) {
					Pasaje p = new Pasaje();
					p.setIdPasaje(rs.getInt("idPasaje"));
					p.setEstado(rs.getString("estado"));
					p.setAsiento(new Asiento());
					p.getAsiento().setAvion(new Avion());
					p.getAsiento().getAvion().setIdAvion(rs.getInt("asi.idAvion"));
					p.getAsiento().setFila(rs.getString("asi.fila"));
					p.getAsiento().setNumero(rs.getString("asi.numero"));
					p.getAsiento().setTipo(rs.getString("asi.tipo"));
					p.setUsuario(new Usuario());
					p.getUsuario().setNroDocumento(rs.getString("usu.nroDocumento"));
					p.getUsuario().setTipoDocumento(rs.getString("usu.tipoDocumento"));
					p.getUsuario().setNombre(rs.getString("usu.nombre"));
					p.getUsuario().setApellido(rs.getString("usu.apellido"));
					p.setVuelo(new Vuelo());
					p.getVuelo().setAeropuertoDestino(new Aeropuerto());
					p.getVuelo().setAeropuertoOrigen(new Aeropuerto());
					p.getVuelo().getAeropuertoDestino().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoOrigen().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoDestino().getCiudad().setPais(new Pais());
					p.getVuelo().getAeropuertoOrigen().getCiudad().setPais(new Pais());
					p.getVuelo().setIdvuelo(rs.getInt("idVuelo"));
					p.getVuelo().setFechaHoraSalida(rs.getObject("fechaHoraSalida", LocalDateTime.class));
					p.getVuelo().setFechaHoraLlegada(rs.getObject("fechaHoraLlegada", LocalDateTime.class));
					p.getVuelo().getAeropuertoOrigen().setIdAeropuerto(rs.getInt("idAeropuertoOrigen"));
					p.getVuelo().getAeropuertoOrigen().setNombre(rs.getString("nAeroO"));
					p.getVuelo().getAeropuertoDestino().setIdAeropuerto(rs.getInt("idAeropuertoDestino"));
					p.getVuelo().getAeropuertoDestino().setNombre(rs.getString("nAeroD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().setNombre(rs.getString("nCiudadO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().setNombre(rs.getString("nCiudadD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().getPais().setNombre(rs.getString("nPaisO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().getPais().setNombre(rs.getString("nPaisD"));
					pasajes.add(p);
				}
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}

		return pasajes;
	}

	public LinkedList<Pasaje> getByIdUsuario(Usuario usu) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Pasaje> pasajes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select pas.idPasaje, pas.estado, usu.tipoDocumento, usu.idusuario, usu.nroDocumento, usu.nombre, usu.apellido, \r\n"
							+ "							 asi.* ,\r\n"
							+ "							 pO.nombre as nPaisO, pD.nombre as nPaisD, ciuD.nombre as nCiudadD, vue.*, ciuO.nombre as nCiudadO, \r\n"
							+ "							 aeroO.nombre as nAeroO, aeroD.nombre as nAeroD from pasaje pas \r\n"
							+ "							 inner join vuelo vue on vue.idVuelo = pas.idVuelo\r\n"
							+ "						inner join usuario usu on usu.idUsuario = pas.idUsuario\r\n"
							+ "								inner join asiento asi on asi.fila = pas.fila and asi.numero = pas.numero and asi.idAvion = pas.idAvion \r\n"
							+ "							 inner join aeropuerto aeroO on aeroO.idaeropuerto = vue.idAeropuertoOrigen\r\n"
							+ "							 inner join aeropuerto aeroD on aeroD.idaeropuerto = vue.idAeropuertoDestino\r\n"
							+ "							 inner join ciudad ciuO on ciuO.codPostal = aeroO.codPostal \r\n"
							+ "							 inner join ciudad ciuD on ciuD.codPostal = aeroD.codPostal \r\n"
							+ "							 inner join pais pO on pO.idpais = ciuO.idPais \r\n"
							+ "							 inner join pais pD on pD.idpais = ciuD.idPais  where pas.idUsuario = ?");
			stmt.setInt(1, usu.getIdUsuario());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Pasaje p = new Pasaje();
					p.setIdPasaje(rs.getInt("idPasaje"));
					p.setEstado(rs.getString("estado"));
					p.setAsiento(new Asiento());
					p.getAsiento().setAvion(new Avion());
					p.getAsiento().getAvion().setIdAvion(rs.getInt("asi.idAvion"));
					p.getAsiento().setFila(rs.getString("asi.fila"));
					p.getAsiento().setNumero(rs.getString("asi.numero"));
					p.getAsiento().setTipo(rs.getString("asi.tipo"));
					p.setUsuario(new Usuario());
					p.getUsuario().setIdUsuario(rs.getInt("usu.idUsuario"));
					p.getUsuario().setNroDocumento(rs.getString("usu.nroDocumento"));
					p.getUsuario().setTipoDocumento(rs.getString("usu.tipoDocumento"));
					p.getUsuario().setNombre(rs.getString("usu.nombre"));
					p.getUsuario().setApellido(rs.getString("usu.apellido"));
					p.setVuelo(new Vuelo());
					p.getVuelo().setAeropuertoDestino(new Aeropuerto());
					p.getVuelo().setAeropuertoOrigen(new Aeropuerto());
					p.getVuelo().getAeropuertoDestino().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoOrigen().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoDestino().getCiudad().setPais(new Pais());
					p.getVuelo().getAeropuertoOrigen().getCiudad().setPais(new Pais());
					p.getVuelo().setIdvuelo(rs.getInt("idVuelo"));
					p.getVuelo().setFechaHoraSalida(rs.getObject("fechaHoraSalida", LocalDateTime.class));
					p.getVuelo().setFechaHoraLlegada(rs.getObject("fechaHoraLlegada", LocalDateTime.class));
					p.getVuelo().getAeropuertoOrigen().setIdAeropuerto(rs.getInt("idAeropuertoOrigen"));
					p.getVuelo().getAeropuertoOrigen().setNombre(rs.getString("nAeroO"));
					p.getVuelo().getAeropuertoDestino().setIdAeropuerto(rs.getInt("idAeropuertoDestino"));
					p.getVuelo().getAeropuertoDestino().setNombre(rs.getString("nAeroD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().setNombre(rs.getString("nCiudadO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().setNombre(rs.getString("nCiudadD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().getPais().setNombre(rs.getString("nPaisO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().getPais().setNombre(rs.getString("nPaisD"));
					pasajes.add(p);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return pasajes;

	}

	public LinkedList<Pasaje> getByVuelo(Vuelo vue) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Pasaje> pasajes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select pas.idPasaje, pas.estado, usu.tipoDocumento, usu.nroDocumento, usu.nombre, usu.apellido,"
							+ " asi.*, "
							+ " pO.nombre as nPaisO, pD.nombre as nPaisD, ciuD.nombre as nCiudadD, vue.*, ciuO.nombre as nCiudadO, "
							+ " aeroO.nombre as nAeroO, aeroD.nombre as nAeroD " + " from pasaje pas "
							+ " inner join vuelo vue on vue.idVuelo = pas.idVuelo"
							+ " inner join usuario usu on usu.idUsuario = pas.idUsuario"
							+ "	inner join asiento asi on asi.fila = pas.fila and asi.numero = pas.numero and asi.idAvion = pas.idAvion "
							+ " inner join aeropuerto aeroO on aeroO.idaeropuerto = vue.idAeropuertoOrigen"
							+ " inner join aeropuerto aeroD on aeroD.idaeropuerto = vue.idAeropuertoDestino"
							+ " inner join ciudad ciuO on ciuO.codPostal = aeroO.codPostal "
							+ " inner join ciudad ciuD on ciuD.codPostal = aeroD.codPostal "
							+ " inner join pais pO on pO.idpais = ciuO.idPais"
							+ " inner join pais pD on pD.idpais = ciuD.idPais"
							+ " where vue.idVuelo = ? and pas.estado= 'Confirmado' ");
			stmt.setInt(1, vue.getIdvuelo());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Pasaje p = new Pasaje();
					p.setIdPasaje(rs.getInt("idPasaje"));
					p.setEstado(rs.getString("estado"));
					p.setAsiento(new Asiento());
					p.getAsiento().setAvion(new Avion());
					p.getAsiento().getAvion().setIdAvion(rs.getInt("asi.idAvion"));
					p.getAsiento().setFila(rs.getString("asi.fila"));
					p.getAsiento().setNumero(rs.getString("asi.numero"));
					p.getAsiento().setTipo(rs.getString("asi.tipo"));
					p.setUsuario(new Usuario());
					p.getUsuario().setNroDocumento(rs.getString("usu.nroDocumento"));
					p.getUsuario().setTipoDocumento(rs.getString("usu.tipoDocumento"));
					p.getUsuario().setNombre(rs.getString("usu.nombre"));
					p.getUsuario().setApellido(rs.getString("usu.apellido"));
					p.setVuelo(new Vuelo());
					p.getVuelo().setAeropuertoDestino(new Aeropuerto());
					p.getVuelo().setAeropuertoOrigen(new Aeropuerto());
					p.getVuelo().getAeropuertoDestino().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoOrigen().setCiudad(new Ciudad());
					p.getVuelo().getAeropuertoDestino().getCiudad().setPais(new Pais());
					p.getVuelo().getAeropuertoOrigen().getCiudad().setPais(new Pais());
					p.getVuelo().setIdvuelo(rs.getInt("idVuelo"));
					p.getVuelo().setFechaHoraSalida(rs.getObject("fechaHoraSalida", LocalDateTime.class));
					p.getVuelo().setFechaHoraLlegada(rs.getObject("fechaHoraLlegada", LocalDateTime.class));
					p.getVuelo().getAeropuertoOrigen().setIdAeropuerto(rs.getInt("idAeropuertoOrigen"));
					p.getVuelo().getAeropuertoOrigen().setNombre(rs.getString("nAeroO"));
					p.getVuelo().getAeropuertoDestino().setIdAeropuerto(rs.getInt("idAeropuertoDestino"));
					p.getVuelo().getAeropuertoDestino().setNombre(rs.getString("nAeroD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().setNombre(rs.getString("nCiudadO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().setNombre(rs.getString("nCiudadD"));
					p.getVuelo().getAeropuertoOrigen().getCiudad().getPais().setNombre(rs.getString("nPaisO"));
					p.getVuelo().getAeropuertoDestino().getCiudad().getPais().setNombre(rs.getString("nPaisD"));
					pasajes.add(p);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return pasajes;
	}

	public void cambiarEstado(Pasaje p) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE pasaje SET estado= ? WHERE IdPasaje=?");
			pstmt.setString(1, p.getEstado());
			pstmt.setInt(2, p.getIdPasaje());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	public void add(Pasaje p) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into pasaje(Estado, idVuelo, fila, numero, idAvion, idUsuario) values(?,?,?,?,?,?)");
			stmt.setString(1, p.getEstado());
			stmt.setInt(2, p.getVuelo().getIdvuelo());
			stmt.setString(3, p.getAsiento().getFila());
			stmt.setString(4, p.getAsiento().getNumero());
			stmt.setInt(5, p.getAsiento().getAvion().getIdAvion());
			stmt.setInt(6, p.getUsuario().getIdUsuario());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	public Pasaje getById(Pasaje pas) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Pasaje p = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select pas.idPasaje, pas.estado, usu.tipoDocumento, usu.nroDocumento, usu.nombre, usu.apellido,"
							+ " asi.*, "
							+ " pO.nombre as nPaisO, pD.nombre as nPaisD, ciuD.nombre as nCiudadD, vue.*, ciuO.nombre as nCiudadO, "
							+ " aeroO.nombre as nAeroO, aeroD.nombre as nAeroD " + " from pasaje pas "
							+ " inner join vuelo vue on vue.idVuelo = pas.idVuelo"
							+ " inner join usuario usu on usu.idUsuario = pas.idUsuario"
							+ "	inner join asiento asi on asi.fila = pas.fila and asi.numero = pas.numero and asi.idAvion = pas.idAvion "
							+ " inner join aeropuerto aeroO on aeroO.idaeropuerto = vue.idAeropuertoOrigen"
							+ " inner join aeropuerto aeroD on aeroD.idaeropuerto = vue.idAeropuertoDestino"
							+ " inner join ciudad ciuO on ciuO.codPostal = aeroO.codPostal "
							+ " inner join ciudad ciuD on ciuD.codPostal = aeroD.codPostal "
							+ " inner join pais pO on pO.idpais = ciuO.idPais"
							+ " inner join pais pD on pD.idpais = ciuD.idPais" + " where pas.idpasaje = ?  ");
			stmt.setInt(1, pas.getIdPasaje());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {

				p = new Pasaje();
				p.setIdPasaje(rs.getInt("idPasaje"));
				p.setEstado(rs.getString("estado"));
				p.setAsiento(new Asiento());
				p.getAsiento().setAvion(new Avion());
				p.getAsiento().getAvion().setIdAvion(rs.getInt("asi.idAvion"));
				p.getAsiento().setFila(rs.getString("asi.fila"));
				p.getAsiento().setNumero(rs.getString("asi.numero"));
				p.getAsiento().setTipo(rs.getString("asi.tipo"));
				p.setUsuario(new Usuario());
				p.getUsuario().setNroDocumento(rs.getString("usu.nroDocumento"));
				p.getUsuario().setTipoDocumento(rs.getString("usu.tipoDocumento"));
				p.getUsuario().setNombre(rs.getString("usu.nombre"));
				p.getUsuario().setApellido(rs.getString("usu.apellido"));
				p.setVuelo(new Vuelo());
				p.getVuelo().setAeropuertoDestino(new Aeropuerto());
				p.getVuelo().setAeropuertoOrigen(new Aeropuerto());
				p.getVuelo().getAeropuertoDestino().setCiudad(new Ciudad());
				p.getVuelo().getAeropuertoOrigen().setCiudad(new Ciudad());
				p.getVuelo().getAeropuertoDestino().getCiudad().setPais(new Pais());
				p.getVuelo().getAeropuertoOrigen().getCiudad().setPais(new Pais());
				p.getVuelo().setIdvuelo(rs.getInt("idVuelo"));
				p.getVuelo().setFechaHoraSalida(rs.getObject("fechaHoraSalida", LocalDateTime.class));
				p.getVuelo().setFechaHoraLlegada(rs.getObject("fechaHoraLlegada", LocalDateTime.class));
				p.getVuelo().getAeropuertoOrigen().setIdAeropuerto(rs.getInt("idAeropuertoOrigen"));
				p.getVuelo().getAeropuertoOrigen().setNombre(rs.getString("nAeroO"));
				p.getVuelo().getAeropuertoDestino().setIdAeropuerto(rs.getInt("idAeropuertoDestino"));
				p.getVuelo().getAeropuertoDestino().setNombre(rs.getString("nAeroD"));
				p.getVuelo().getAeropuertoOrigen().getCiudad().setNombre(rs.getString("nCiudadO"));
				p.getVuelo().getAeropuertoDestino().getCiudad().setNombre(rs.getString("nCiudadD"));
				p.getVuelo().getAeropuertoOrigen().getCiudad().getPais().setNombre(rs.getString("nPaisO"));
				p.getVuelo().getAeropuertoDestino().getCiudad().getPais().setNombre(rs.getString("nPaisD"));

			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return p;
	}

}
