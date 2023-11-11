package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Aeropuerto;
import entities.Ciudad;
import entities.Pais;

public class DataAeropuerto {

	public LinkedList<Aeropuerto> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Aeropuerto> aeropuertos = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select a.idaeropuerto, a.nombre as nombreAero, "
					+ "a.descaeropuerto, ciu.codpostal, ciu.nombre as nombreCiudad, p.nombre as nombrePais "
					+ "from aeropuerto a " + "inner join ciudad ciu on  a.codpostal = ciu.codpostal "
					+ "inner join pais p on p.idpais = ciu.idpais");
			if (rs != null) {
				while (rs.next()) {
					Aeropuerto a = new Aeropuerto();
					a.setCiudad(new Ciudad());
					a.setIdAeropuerto(rs.getInt("idaeropuerto"));
					a.setNombre(rs.getString("nombreAero"));
					a.setDescAeropuerto(rs.getString("descaeropuerto"));
					a.getCiudad().setCodPostal(rs.getString("codPostal"));
					a.getCiudad().setNombre(rs.getString("nombreCiudad"));
					a.getCiudad().setPais(new Pais());
					a.getCiudad().getPais().setNombre(rs.getString("nombrePais"));
					aeropuertos.add(a);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return aeropuertos;
	}

	public Aeropuerto getById(Aeropuerto a) {
		Aeropuerto aero = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select a.idaeropuerto, a.nombre, a.descaeropuerto, ciu.codpostal, ciu.nombre, p.nombre "
							+ " from aeropuerto a " + " inner join ciudad ciu on  a.codpostal = ciu.codpostal "
							+ " inner join pais p on p.idpais = ciu.idPais " + " where a.idaeropuerto = ?");
			stmt.setInt(1, a.getIdAeropuerto());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				aero = new Aeropuerto();
				aero.setCiudad(new Ciudad());
				aero.setIdAeropuerto(rs.getInt("a.idaeropuerto"));
				aero.setNombre(rs.getString("a.nombre"));
				aero.setDescAeropuerto(rs.getString("a.descaeropuerto"));
				aero.getCiudad().setPais(new Pais());
				aero.getCiudad().getPais().setNombre(rs.getString("p.nombre"));
				aero.getCiudad().setCodPostal(rs.getString("codpostal"));
				aero.getCiudad().setNombre(rs.getString("ciu.nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return aero;
	}

	public Aeropuerto getByNombre(Aeropuerto a) {
		Aeropuerto aero = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select a.idaeropuerto, a.nombre, a.descaeropuerto, ciu.codpostal, ciu.nombre, p.nombre "
							+ " from aeropuerto a " + " inner join ciudad ciu on  a.codpostal = ciu.codpostal "
							+ " inner join pais p on p.idpais = ciu.idPais " + " where a.nombre = ?");
			stmt.setString(1, a.getNombre());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				aero = new Aeropuerto();
				aero.setCiudad(new Ciudad());
				aero.setIdAeropuerto(rs.getInt("a.idaeropuerto"));
				aero.setNombre(rs.getString("a.nombre"));
				aero.setDescAeropuerto(rs.getString("a.descaeropuerto"));
				aero.getCiudad().setPais(new Pais());
				aero.getCiudad().getPais().setNombre(rs.getString("p.nombre"));
				aero.getCiudad().setCodPostal(rs.getString("codpostal"));
				aero.getCiudad().setNombre(rs.getString("ciu.nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return aero;
	}

	public void add(Aeropuerto a) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into aeropuerto(nombre, descaeropuerto, codpostal) values(?,?,?)");
			stmt.setString(1, a.getNombre());
			stmt.setString(2, a.getDescAeropuerto());
			stmt.setString(3, a.getCiudad().getCodPostal());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Aeropuerto a) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from aeropuerto where idaeropuerto=?");
			pstmt.setInt(1, a.getIdAeropuerto());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void edit(Aeropuerto a) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE aeropuerto SET nombre =?, descaeropuerto= ? WHERE idaeropuerto=?");
			pstmt.setInt(3, a.getIdAeropuerto());
			pstmt.setString(1, a.getNombre());
			pstmt.setString(2, a.getDescAeropuerto());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
