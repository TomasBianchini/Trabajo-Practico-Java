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
    			rs= stmt.executeQuery("select ciu.codPostal as codPostal, ciu.nombre as nombreCiudad, p.idpais as idPais, p.nombre as nombrePais " 
						+ "from ciudad ciu "
						+ "inner join pais p on  ciu.idPais  = p.idPais" );
				if(rs!=null) {
					while(rs.next()) {
						Ciudad c=new Ciudad();
						c.setPais(new Pais());
						c.setCodPostal(rs.getString("codPostal"));
						c.setNombre(rs.getString("nombreCiudad"));
						c.getPais().setIdPais(rs.getInt("idPais"));
						c.getPais().setNombre(rs.getString("nombrePais"));
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
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return ciudades;	
	}
	
	public Ciudad getById(Ciudad c) {
		Ciudad ciu = null; 
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select codPostal, ciu.nombre, p.idPais, p.nombre " 
							+ "from ciudad ciu "
							+ "inner join pais p on  ? = p.idPais "
							+ "where c.codpostal = ? "
					);
			stmt.setInt(1, c.getPais().getIdPais());
			stmt.setString(2, c.getCodPostal());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				ciu=new Ciudad();
				ciu.setPais(new Pais());
				ciu.setCodPostal(rs.getString("codPostal"));
				ciu.setNombre(rs.getString("ciu.nombre"));
				ciu.getPais().setIdPais(rs.getInt("idPais"));
				ciu.getPais().setNombre(rs.getString("p.nombre"));
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

	
	public void add(Ciudad c) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into ciudad(codPostal, nombre, idPais) values(?,?,?)");
			stmt.setString(1, c.getCodPostal());
			stmt.setString(2, c.getNombre());
			stmt.setInt(3, c.getPais().getIdPais());
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
	
	public void delete(Ciudad c) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement(
					"delete from ciudad where codPostal=?");		
			pstmt.setString(1, c.getCodPostal());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally {
	            try {
	                if(pstmt!=null)pstmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
		}
	}
		
	public void edit(Ciudad c) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE ciudad SET codPostal = ?,nombre =? WHERE codPostal=?");
			pstmt.setString(1, c.getCodPostal());
			pstmt.setString(2, c.getNombre());
			pstmt.setString(3, c.getCodPostal());
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

