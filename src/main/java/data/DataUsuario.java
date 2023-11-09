package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Usuario;

public class DataUsuario {

	public LinkedList<Usuario> getAll() {

		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Usuario> usuarios = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select idUsuario,email,nombre,apellido,"
					+ " tipoDocumento,nroDocumento, fechaNacimiento, tipo" + " from usuario");

			if (rs != null) {
				while (rs.next()) {
					Usuario usu = new Usuario();
					usu.setEmail(rs.getString("email"));
					usu.setNombre(rs.getString("nombre"));
					usu.setApellido(rs.getString("apellido"));
					usu.setIdUsuario(rs.getInt("idusuario"));
					usu.setTipoDocumento(rs.getString("tipoDocumento"));
					usu.setNroDocumento(rs.getString("nroDocumento"));
					usu.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
					usu.setTipo(rs.getString("tipo"));

					usuarios.add(usu);
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

		return usuarios;
	}

	public Usuario getByEmail(Usuario usuario) {
		Usuario usu = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idUsuario,email,nombre,apellido, tipoDocumento,nroDocumento, fechaNacimiento, tipo, contrasenia"
							+ " from usuario where email=? and contrasenia=? ");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getContrasenia());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				usu = new Usuario();
				usu.setEmail(rs.getString("email"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setIdUsuario(rs.getInt("idusuario"));
				usu.setTipoDocumento(rs.getString("tipoDocumento"));
				usu.setNroDocumento(rs.getString("nroDocumento"));
				usu.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
				usu.setTipo(rs.getString("tipo"));
				usu.setContrasenia(rs.getString("contrasenia"));
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

		return usu;
	}

	public Usuario getById(Usuario usu) {
		Usuario us = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idUsuario,email,nombre,apellido, tipoDocumento,nroDocumento, fechaNacimiento, tipo "
							+ "from usuario where idUsuario = ?");
			stmt.setInt(1, usu.getIdUsuario());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				us = new Usuario();
				us.setEmail(rs.getString("email"));
				us.setNombre(rs.getString("nombre"));
				us.setApellido(rs.getString("apellido"));
				us.setIdUsuario(rs.getInt("idusuario"));
				us.setTipoDocumento(rs.getString("tipoDocumento"));
				us.setNroDocumento(rs.getString("nroDocumento"));
				us.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
				us.setTipo(rs.getString("tipo"));
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

		return us;
	}

	public Usuario getByDocumento(Usuario usu) {
		Usuario us = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idUsuario,email,nombre,apellido, tipoDocumento,nroDocumento, fechaNacimiento, tipo "
							+ "from usuario where tipoDocumento=? and nroDocumento = ?");
			stmt.setString(1, usu.getTipoDocumento());
			stmt.setString(2, usu.getNroDocumento());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				us = new Usuario();
				us.setEmail(rs.getString("email"));
				us.setNombre(rs.getString("nombre"));
				us.setApellido(rs.getString("apellido"));
				us.setIdUsuario(rs.getInt("idusuario"));
				us.setTipoDocumento(rs.getString("tipoDocumento"));
				us.setNroDocumento(rs.getString("nroDocumento"));
				us.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
				us.setTipo(rs.getString("tipo"));
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

		return us;
	}

	public void add(Usuario usu) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into usuario(email,nombre,apellido, tipoDocumento,nroDocumento, fechaNacimiento, tipo, contrasenia) "
							+ " values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, usu.getEmail());
			stmt.setString(2, usu.getNombre());
			stmt.setString(3, usu.getApellido());
			stmt.setString(4, usu.getTipoDocumento());
			stmt.setString(5, usu.getNroDocumento());
			stmt.setObject(6, usu.getFechaNacimiento());
			stmt.setString(7, usu.getTipo());
			stmt.setString(8, usu.getContrasenia());

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

	public void edit(Usuario usu) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn()
					.prepareStatement("UPDATE usuario SET email=?, nombre = ?, apellido = ? , tipoDocumento = ?,"
							+ " nroDocumento= ?, fechaNacimiento=?, tipo=?,contrasenia =? WHERE idUsuario = ?");
			pstmt.setString(1, usu.getEmail());
			pstmt.setString(2, usu.getNombre());
			pstmt.setString(3, usu.getApellido());
			pstmt.setString(4, usu.getTipoDocumento());
			pstmt.setString(5, usu.getNroDocumento());
			pstmt.setObject(6, usu.getFechaNacimiento());
			pstmt.setString(7, usu.getTipo());
			pstmt.setString(8, usu.getContrasenia());
			pstmt.setInt(9, usu.getIdUsuario());
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

	public void delete(Usuario usu) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("delete from usuario where idUsuario=?");
			pstmt.setInt(1, usu.getIdUsuario());

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