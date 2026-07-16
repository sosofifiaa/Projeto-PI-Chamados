package controllers;

import java.util.List;
import models.Equipamento;
import play.mvc.Controller;

public class Equipamentos extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Equipamento e) {
        e.save();
        Usuarios.listar();
    }
}