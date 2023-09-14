package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Avion;

public class DataAvion {

	public LinkedList<Avion> getAll() {

		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Avion> aviones = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select avi.idAvion, marca, modelo, anio, count(asi.idAvion) as cantAsientos "
					+ " from avion avi" + " left join asiento asi on asi.idAvion = avi.idAvion"
					+ " group by idAvion, marca, modelo, anio");

			if (rs != null) {
				while (rs.next()) {
					Avion a = new Avion();
					a.setIdAvion(rs.getInt("avi.idAvion"));
					a.setCantAsientos(rs.getInt("cantAsientos"));
					a.setMarca(rs.getString("marca"));
					a.setModelo(rs.getString("modelo"));
					a.setAnio(rs.getString("anio"));
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select avi.idAvion, marca, modelo, anio, count(asi.idAvion) as cantAsientos "
							+ " from avion avi" + " left join asiento asi on asi.idAvion = avi.idAvion"
							+ " where avi.idAvion = ? " + " group by idAvion, marca, modelo, anio");
			stmt.setInt(1, av.getIdAvion());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				a = new Avion();
				a.setIdAvion(rs.getInt("avi.idAvion"));
				a.setCantAsientos(rs.getInt("cantAsientos"));
				a.setMarca(rs.getString("marca"));
				a.setModelo(rs.getString("modelo"));
				a.setAnio(rs.getString("anio"));
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

	public void add(Avion p) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into avion(marca, modelo, anio) values(?, ?, ?)");
			stmt.setString(1, p.getMarca());
			stmt.setString(2, p.getModelo());
			stmt.setString(3, p.getAnio());
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

	/*
	 * public void edit(Avion p) { PreparedStatement pstmt = null; try { pstmt =
	 * DbConnector.getInstancia().getConn()
	 * .prepareStatement("UPDATE avion SET cantidadAsientos=?  WHERE idAvion=?");
	 * pstmt.setInt(1, p.getCantAsientos()); pstmt.setInt(2, p.getIdAvion());
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * finally { try { if (pstmt != null) pstmt.close();
	 * DbConnector.getInstancia().releaseConn(); } catch (SQLException e) {
	 * e.printStackTrace(); } } }
	 */

	public void delete(Avion p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("delete from avion where idAvion=?");
			pstmt.setInt(1, p.getIdAvion());
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
