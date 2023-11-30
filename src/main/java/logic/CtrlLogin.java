package logic;

import java.sql.SQLException;

import data.DataUsuario;
import entities.Usuario;

public class CtrlLogin {
	private DataUsuario du;

	public CtrlLogin() {
		du = new DataUsuario();
	}

	public Usuario validate(Usuario usu) throws SQLException {
		return du.getByEmail(usu);

	}

}