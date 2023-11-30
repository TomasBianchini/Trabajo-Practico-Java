package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Pais;

public class DataPais {

	public LinkedList<Pais> getAll() throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Pais> pais = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select idPais, nombre from pais");

			if (rs != null) {
				while (rs.next()) {
					Pais p = new Pais();
					p.setIdPais(rs.getInt("IdPais"));
					p.setNombre(rs.getString("nombre"));

					pais.add(p);
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

		return pais;
	}

	public Pais getByNombre(Pais pa) throws SQLException {
		Pais p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select IdPais,nombre from pais where nombre=? ");
			stmt.setString(1, pa.getNombre());

			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Pais();
				p.setIdPais(rs.getInt("idPais"));
				p.setNombre(rs.getString("nombre"));
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

	public Pais getById(Pais pa) throws SQLException {
		Pais p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select IdPais,nombre from pais where idPais=? ");
			stmt.setInt(1, pa.getIdPais());

			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Pais();
				p.setIdPais(rs.getInt("idPais"));
				p.setNombre(rs.getString("nombre"));
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

	public void add(Pais p) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into pais(nombre) values(?)");

			stmt.setString(1, p.getNombre());

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

	public void edit(Pais p) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE pais SET nombre =? WHERE IdPais=?");
			pstmt.setString(1, p.getNombre());
			pstmt.setInt(2, p.getIdPais());
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

	public void delete(Pais p) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("delete from pais where IdPais=?");
			pstmt.setInt(1, p.getIdPais());

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
