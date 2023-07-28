package data;

import entities.Pais;
import entities.Ciudad;
import java.util.LinkedList;
import java.sql.*;


public class DataCiudad {
	
	
	public LinkedList<Ciudad> getAll(){
		Statement stmt = null; 
		ResultSet rs = null;
		LinkedList<Ciudad> ciudades = new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select idciudad,nombre, idpais from ciudad");
				if(rs!=null) {
					while(rs.next()) {
						Ciudad c=new Ciudad();
						
						Pais p = new Pais(); // objeto Pais
						//c.setPais(new Pais()); me parece q este o va, fijate q agregue lo de arriba y lo otro
						
						c.setIdCiudad(rs.getInt("idciudad"));
						c.setNombre(rs.getString("nombre"));
						
						
						int idPais = rs.getInt("idpais");
					    p.setIdPais(idPais); 
					    c.setPais(p); 
					    ciudades.add(c);
						

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
			
			
			return ciudades;
		
		
		
	}

	
	public Ciudad getByid(Ciudad c) {
		Pais p = new Pais();
		Ciudad ciu = null; 
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idCiudad,nombre,idpais from ciudad where idciudad =?"
					);
			stmt.setInt(1, c.getIdCiudad());

			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				ciu=new Ciudad();
				ciu.setPais(new Pais());
				ciu.setIdCiudad(rs.getInt("idciudad"));
				ciu.setNombre(rs.getString("nombre"));
				ciu.getPais().setIdPais(rs.getInt("idPais"));
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
		
		
		return ciu;
	}

}
