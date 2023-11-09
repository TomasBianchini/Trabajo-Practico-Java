package logic;

import java.util.LinkedList;

import data.DataUsuario;
import entities.Usuario;

public class CtrlUsuario {
	private DataUsuario du;

	public CtrlUsuario() {
		du = new DataUsuario();
	}

	public LinkedList<Usuario> getAll() {
		return du.getAll();
	}

	public Usuario getByEmail(Usuario uru) {
		return du.getByEmail(uru);

	}

	public Usuario getByDocumento(Usuario usu) {
		return du.getByDocumento(usu);
	}

	public Usuario getById(Usuario usu) {
		return du.getById(usu);
	}

	public void add(Usuario usu) {
		du.add(usu);
	}

	public void edit(Usuario usu) {
		du.edit(usu);
	}

	public void delete(Usuario usu) {
		du.delete(usu);
	}

}
