package controllers;

import models.Chamado;
import play.mvc.Controller;

public class Chamados extends Controller {
	
	public static void form() {
		render();
	}
	public static void salvar(Chamado c) {
        c.save();
        Usuarios.listar();
    }

}
