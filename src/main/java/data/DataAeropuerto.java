package data;

import entities.Aeropuerto;
import entities.Ciudad;


import java.util.LinkedList;
import java.sql.*;

public class DataAeropuerto {
	
	public LinkedList<Aeropuerto> getAll(){
		Statement stmt = null; 
		ResultSet rs = null;
		LinkedList<Aeropuerto> aeropuertos = new LinkedList<>();
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select a.idaeropuerto, a.nombre, a.descaeropuerto, ciu.codpostal, ciu.nombre" 
						+ "from aeropuerto a"
						+ "inner join ciudad ciu on  a.codpostal = ciu.codpostal");
				if(rs!=null) {
					while(rs.next()) {
						Aeropuerto a=new Aeropuerto();
						a.setCiudad(new Ciudad());
						a.setIdAeropuerto(rs.getInt("idaeropuerto"));
						a.setNombre(rs.getString("a.nombre"));
						a.setDescAeropuerto(rs.getString("descaeropuerto"));
						a.getCiudad().setCodPostal(rs.getString("codPostal"));
						a.getCiudad().setNombre(rs.getString("ciu.nombre"));
						aeropuertos.add(a);					
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return aeropuertos;
		
	}
	
	public Aeropuerto getByid(Aeropuerto a) {
		Aeropuerto aero = null; 
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select a.idaeropuerto, a.nombre, a.descaeropuerto, ciu.codpostal, ciu.nombre" 
							+ "from aeropuerto a"
							+ "inner join ciudad ciu on  ? = ciu.codpostal"
							+ "where a.idaeropuerto = ?"
					);
			stmt.setString(1, a.getCiudad().getCodPostal());
			stmt.setInt(1, a.getIdAeropuerto());

			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				aero =new Aeropuerto();
				aero.setCiudad(new Ciudad());
				aero.setIdAeropuerto(rs.getInt("a.idaeropuerto"));
				aero.setNombre(rs.getString("a.nombre"));
				aero.setDescAeropuerto(rs.getString("a.descaeropuerto"));
				
				aero.getCiudad().setCodPostal(rs.getString("codpostal"));
				aero.getCiudad().setNombre(rs.getString("ciu.nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return aero;
	}

	
	public void addAeropuerto(Aeropuerto a) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into aeropuerto(idaeropuerto, nombre, descaeropuerto, codpostal) values(?,?,?,?)");
			stmt.setInt(1, a.getIdAeropuerto());
			stmt.setString(2, a.getNombre());
			stmt.setString(2, a.getDescAeropuerto());
			stmt.setString(3, a.getCiudad().getCodPostal());
			
			stmt.executeUpdate();
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void deleteAeropuerto(Aeropuerto a) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement(
					"delete from aeropuerto where idaeropuerto=?");
			
			pstmt.setInt(1, a.getIdAeropuerto());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
	            try {
	                if(pstmt!=null)pstmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
		}
	}
		
	public void editAeropuerto(Aeropuerto a) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE aeropuerto SET nombre =?, descaeropuerto= ? WHERE idaeropuerto=?");
			pstmt.setInt(3, a.getIdAeropuerto());
			pstmt.setString(1, a.getNombre());
			pstmt.setString(2, a.getDescAeropuerto());
			pstmt.executeUpdate();	
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(pstmt!=null)pstmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}
	
