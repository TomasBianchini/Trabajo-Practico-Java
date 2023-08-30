package data;
 

import entities.Pasajero;

import java.sql.*;
import java.util.LinkedList;

public class DataPasajero {
	
	public LinkedList<Pasajero> getAllPasajero(){

		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Pasajero> pasajeros = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select email,nombre,apellido,dni from pasajero");

			if(rs!=null) {
				while(rs.next()) {
					Pasajero p=new Pasajero();
					p.setEmail(rs.getString("email"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					
					pasajeros.add(p);
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
		
		
		return pasajeros;
	}
	
	public Pasajero getByEmail(Pasajero pas) {
		Pasajero p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select dni,nombre,apellido,email from pasajero where email=? and contrasenia=?");
			stmt.setString(1, pas.getEmail());
			stmt.setString(2, pas.getContrasenia());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Pasajero();
				p.setDni(rs.getString("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEmail(rs.getString("email"));
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
		
		return p;
	}
	
	public Pasajero getByDni(Pasajero pas) {
		Pasajero p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select dni,nombre,apellido,email from pasajero where dni=? and contrasenia=?"
					);
			stmt.setString(1, pas.getDni());
			stmt.setString(2, pas.getContrasenia());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Pasajero();
				p.setDni(rs.getString("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEmail(rs.getString("email"));
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
		
		return p;
	}
	
	public void addPasajero(Pasajero p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into pasajero(nombre, apellido, dni , email, contrasenia) values(?,?,?,?,?)");
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getDni());
			stmt.setString(4, p.getEmail());	
			stmt.setString(5, p.getContrasenia());	
			
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


	
	public void editPasajero(Pasajero p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE pasajero SET email=?,contraseña =?, nombre = ?, apellido = ?WHERE dni=?");
			pstmt.setString(1, p.getEmail());
			pstmt.setString(2, p.getContrasenia());
			pstmt.setString(3, p.getNombre());
			pstmt.setString(4, p.getApellido());
			
			pstmt.setString(5, p.getDni());
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
	
	public void deletePasajero(Pasajero p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement(
					"delete from pasajero where dni=?");
			pstmt.setString(1, p.getDni());

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


}