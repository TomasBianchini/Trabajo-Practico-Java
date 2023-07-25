package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Avion;

public class DataAvion {

	
	public LinkedList<Avion> getAll(){

		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Avion> avion = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idAvion,cantAsientos from avion");

			if(rs!=null) {
				while(rs.next()) {
					Avion p=new Avion();
					p.setIdAvion(rs.getInt("idAvion"));
					p.setCantAsientos(rs.getInt("cantAsientos"));
				
					avion.add(p);
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
		
		
		return avion;
	}
	
	public Avion getByCantAsientos(Avion av) {
		Avion p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idAvion,cantAsientos from avion where cantAsientos=?"
					);
			stmt.setInt(1, av.getIdAvion());
			stmt.setInt(2, av.getCantAsientos());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Avion();
				p.setIdAvion(rs.getInt("IdAvion"));
				p.setCantAsientos(rs.getInt("CantAsientos"));
			
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
	
	
	public void add(Avion p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into avion(idAvion, cantAsientos) values(?,?)");
			stmt.setInt(1, p.getIdAvion());
			stmt.setInt(2, p.getCantAsientos());
	
			
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


	
	public void edit(Avion p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE avion SET idAvion=? cantAsientos=?  WHERE idAvion=?");
			pstmt.setInt(1, p.getIdAvion());
			pstmt.setInt(2, p.getCantAsientos());
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
	
	public void delete(Avion p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement(
					"delete from avion where idAvion=?");
			pstmt.setInt(1, p.getIdAvion());

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
