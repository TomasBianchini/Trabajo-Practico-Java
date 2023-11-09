package logic;

import data.DataUsuario;
import entities.Usuario;

public class CtrlLogin {
	private DataUsuario du;

	public CtrlLogin() {
		du = new DataUsuario();
	}

	public Usuario validate(Usuario usu) {
		return du.getByEmail(usu);

	}

}