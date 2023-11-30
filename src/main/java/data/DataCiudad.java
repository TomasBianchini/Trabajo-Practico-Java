package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Ciudad;
import entities.Pais;

public class DataCiudad {

	public LinkedList<Ciudad> getAll() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Ciudad> ciudades = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select ciu.codPostal as codPostal, ciu.nombre as nombreCiudad, p.idpais as idPais, p.nombre as nombrePais "
							+ "from ciudad ciu " + "inner join pais p on  ciu.idPais  = p.idPais");
			if (rs != null) {
				while (rs.next()) {
					Ciudad c = new Ciudad();
					c.setPais(new Pais());
					c.setCodPostal(rs.getString("codPostal"));
					c.setNombre(rs.getString("nombreCiudad"));
					c.getPais().setIdPais(rs.getInt("idPais"));
					c.getPais().setNombre(rs.getString("nombrePais"));
					ciudades.add(c);
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
		return ciudades;
	}

	public Ciudad getById(Ciudad c) throws SQLException {
		Ciudad ciu = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select codPostal, ciu.nombre, p.idPais, p.nombre " + "from ciudad ciu "
							+ "inner join pais p on  p.idPais  = ciu.idPais " + "where ciu.codpostal = ? ");
			stmt.setString(1, c.getCodPostal());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ciu = new Ciudad();
				ciu.setPais(new Pais());
				ciu.setCodPostal(rs.getString("codPostal"));
				ciu.setNombre(rs.getString("ciu.nombre"));
				ciu.getPais().setIdPais(rs.getInt("idPais"));
				ciu.getPais().setNombre(rs.getString("p.nombre"));
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
		return ciu;
	}

	public Ciudad getByNombre(Ciudad c) throws SQLException {
		Ciudad ciu = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select codPostal, ciu.nombre, p.idPais, p.nombre " + " from ciudad ciu "
							+ " inner join pais p on  p.idPais  = ciu.idPais" + " where ciu.nombre = ? ");
			stmt.setString(1, c.getNombre());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ciu = new Ciudad();
				ciu.setPais(new Pais());
				ciu.setCodPostal(rs.getString("codPostal"));
				ciu.setNombre(rs.getString("ciu.nombre"));
				ciu.getPais().setIdPais(rs.getInt("idPais"));
				ciu.getPais().setNombre(rs.getString("p.nombre"));
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
		return ciu;
	}

	public void add(Ciudad c) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into ciudad(codPostal, nombre, idPais) values(?,?,?)");
			stmt.setString(1, c.getCodPostal());
			stmt.setString(2, c.getNombre());
			stmt.setInt(3, c.getPais().getIdPais());
			stmt.executeUpdate();
		} catch (Exception e) {
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

	public void delete(Ciudad c) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("delete from ciudad where codPostal=?");
			pstmt.setString(1, c.getCodPostal());
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

	public void edit(Ciudad c) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE ciudad SET codPostal = ?,nombre =? WHERE codPostal=?");
			pstmt.setString(1, c.getCodPostal());
			pstmt.setString(2, c.getNombre());
			pstmt.setString(3, c.getCodPostal());
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

}
