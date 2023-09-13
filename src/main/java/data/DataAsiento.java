package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Asiento;
import entities.Avion;

public class DataAsiento {
	public LinkedList<Asiento> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Asiento> asientos = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					  "select av.idavion, av.cantidadasientos, asi.fila, asi.numero"
					+ "from asiento as asi"
					+ "inner join avion as av"
					+ "on asi.idavion = av.idavion"
			);
			if (rs != null) {
				while (rs.next()) {
					Asiento a = new Asiento();
					a.setAvion(new Avion());
					a.setFila(rs.getInt("fila"));
					a.setFila(rs.getInt("numero"));
					a.getAvion().setIdAvion(rs.getInt("idavion"));
					a.getAvion().setCantAsientos(rs.getInt("cantidadasientos"));
					asientos.add(a);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return asientos;
	}
	
	public Asiento getOne(Asiento asi) {
		Asiento a = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					  "select av.idavion, av.cantidadasientos, asi.fila, asi.numero"
					+ "from asiento as asi"
					+ "inner join avion as av"
					+ "on asi.idavion = av.idavion"
					+ "where asi.fila = ? and asi.numero = ? and asi.idavion = ?"
			);
			stmt.setInt(1, asi.getFila());
			stmt.setInt(2, asi.getNumero());
			stmt.setInt(3, asi.getAvion().getIdAvion());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				a = new Asiento();
				a.setAvion(new Avion());
				a.setFila(rs.getInt("fila"));
				a.setFila(rs.getInt("numero"));
				a.getAvion().setIdAvion(rs.getInt("idavion"));
				a.getAvion().setCantAsientos(rs.getInt("cantidadasientos"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public void add(Asiento a) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("insert into asiento(fila, numero, idavion) values(?,?,?)");
			stmt.setInt(1, a.getFila());
			stmt.setInt(2, a.getNumero());
			stmt.setInt(3, a.getAvion().getIdAvion());
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

	public void delete(Asiento a) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from asiento where fila=? and numero=? and idavion=?");
			stmt.setInt(1, a.getFila());
			stmt.setInt(2, a.getNumero());
			stmt.setInt(3, a.getAvion().getIdAvion());
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
}