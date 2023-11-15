package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Avion;

public class DataAvion {

	public LinkedList<Avion> getAll() {
		DataAsiento da = new DataAsiento();
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Avion> aviones = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select idAvion, marca, modelo, anio from avion ");

			if (rs != null) {
				while (rs.next()) {
					Avion a = new Avion();
					a.setIdAvion(rs.getInt("idAvion"));
					a.setMarca(rs.getString("marca"));
					a.setModelo(rs.getString("modelo"));
					a.setAnio(rs.getString("anio"));
					a.setAsientos(da.getByAvion(a));
					aviones.add(a);
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

		return aviones;
	}

	public Avion getById(Avion av) {
		Avion a = null;
		DataAsiento da = new DataAsiento();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select idAvion, marca, modelo, anio  from avion avi  where avi.idAvion = ? ");
			stmt.setInt(1, av.getIdAvion());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				a = new Avion();
				a.setIdAvion(rs.getInt("idAvion"));
				a.setMarca(rs.getString("marca"));
				a.setModelo(rs.getString("modelo"));
				a.setAnio(rs.getString("anio"));
				a.setAsientos(da.getByAvion(a));
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
		return a;
	}

	public void add(Avion p) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into avion(marca, modelo, anio) values(?, ?, ?)");
			stmt.setString(1, p.getMarca());
			stmt.setString(2, p.getModelo());
			stmt.setString(3, p.getAnio());
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

	public void delete(Avion p) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("delete from avion where idAvion=?");
			pstmt.setInt(1, p.getIdAvion());
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
