package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
			rs = stmt.executeQuery("select av.idavion, asi.fila, asi.numero, asi.tipo " + " from asiento asi"
					+ " inner join avion av on asi.idavion = av.idavion");
			if (rs != null) {
				while (rs.next()) {
					Asiento a = new Asiento();
					a.setAvion(new Avion());
					a.setFila(rs.getString("fila"));
					a.setNumero(rs.getString("numero"));
					a.setTipo(rs.getString("tipo"));
					a.getAvion().setIdAvion(rs.getInt("idavion"));
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

	public HashMap<String, Asiento> getByAvion(Avion a) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashMap<String, Asiento> asientos = new HashMap<String, Asiento>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select av.idavion, asi.fila, asi.numero, asi.tipo from asiento asi"
							+ " inner join avion av on asi.idavion = av.idavion " + " where av.idAvion =  ? ");
			stmt.setInt(1, a.getIdAvion());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Asiento asi = new Asiento();
					asi.setAvion(new Avion());
					asi.setFila(rs.getString("fila"));
					asi.setNumero(rs.getString("numero"));
					asi.setTipo(rs.getString("tipo"));
					asi.getAvion().setIdAvion(rs.getInt("idavion"));
					asientos.put(asi.getFila() + asi.getNumero(), asi);
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
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select av.idavion, asi.fila, asi.numero, asi.tipo" + " from asiento as asi"
							+ " inner join avion as av" + " on asi.idavion = av.idavion"
							+ " where asi.fila = ? and asi.numero = ? and asi.idavion = ?");
			stmt.setString(1, asi.getFila());
			stmt.setString(2, asi.getNumero());
			stmt.setInt(3, asi.getAvion().getIdAvion());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				a = new Asiento();
				a.setAvion(new Avion());
				a.setFila(rs.getString("fila"));
				a.setNumero(rs.getString("numero"));
				a.setTipo(rs.getString("tipo"));
				a.getAvion().setIdAvion(rs.getInt("idavion"));
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
					.prepareStatement("insert into asiento(fila, numero, idavion, tipo) values(?,?,?,?)");
			stmt.setString(1, a.getFila());
			stmt.setString(2, a.getNumero());
			stmt.setString(4, a.getTipo());
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
			stmt.setString(1, a.getFila());
			stmt.setString(2, a.getNumero());
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