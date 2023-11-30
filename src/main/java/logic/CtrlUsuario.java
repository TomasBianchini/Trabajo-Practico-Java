package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataUsuario;
import entities.Usuario;

public class CtrlUsuario {
	private DataUsuario du;

	public CtrlUsuario() {
		du = new DataUsuario();
	}

	public LinkedList<Usuario> getAll() throws SQLException {
		return du.getAll();
	}

	public Usuario getByEmail(Usuario uru) throws SQLException {
		return du.getByEmail(uru);

	}

	public Usuario getByDocumento(Usuario usu) throws SQLException {
		return du.getByDocumento(usu);
	}

	public Usuario getById(Usuario usu) throws SQLException {
		return du.getById(usu);
	}

	public void add(Usuario usu) throws SQLException {
		du.add(usu);
	}

	public void edit(Usuario usu) throws SQLException {
		du.edit(usu);
	}

	public void delete(Usuario usu) throws SQLException {
		du.delete(usu);
	}

}
