package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Pais;


public class DataPais {
	
	public LinkedList<Pais> getAll(){

		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Pais> pais = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select email,nombre,apellido,dni from pasajero");

			if(rs!=null) {
				while(rs.next()) {
					Pais p=new Pais();
					p.setIdPais(rs.getString("IdPais"));
					p.setNombre(rs.getString("nombre"));
					
					pais.add(p);
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
		
		
		return pais;
}
		
		
		
		public Pais getByNombre(Pais pa) {
			Pais p=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select IdPais,nombre from pais where nombre=? "
						);
				stmt.setString(1, pa.getNombre());
				
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					p=new Pais();
					
					p.setNombre(rs.getString("nombre"));
					
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
		
		public void add(Pais p) {
			PreparedStatement stmt= null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into pais(idPais,nombre) values(?,?)");
				stmt.setString(1, p.getIdPais());
				stmt.setString(2, p.getNombre());
				
			
				
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


		
		public void edit(Pais p) {
			PreparedStatement pstmt = null;
			try {
				pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE pais SET IdPais=?,nombre =? WHERE IdPais=?");
				pstmt.setString(1, p.getIdPais());
				pstmt.setString(2, p.getNombre());
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
		
		public void delete(Pais p) {
			PreparedStatement pstmt = null;
			try {
				pstmt = DbConnector.getInstancia().getConn().prepareStatement(
						"delete from pais where IdPais=?");
				pstmt.setString(1, p.getIdPais());

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
