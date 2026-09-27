package controllers;

import models.Usuario;
import play.mvc.Controller;

public class Logins extends Controller{
	
    public static void form() {
        render();
    }
	
	public static void  logar(String email, String senha) {
		Usuario usuario = Usuario.obterUsuario(email, senha);
		
		if (usuario == null) {
			flash.error("Email ou senha inválidos, tente novamente");
			flash.put("email", email);
			form();
			}
		session.put("usuarioLogado", usuario.email);
		session.put("idUsuario", usuario.id.toString());
		session.put("nomeUsuario", usuario.nome);
		session.put("perfilUsuario", usuario.perfil.name());
		
		flash.success("Bem-vindo(a), " + usuario.nome + "!");
		Usuarios.listar();
	}
	
	public static void sair() {
		session.clear();
		flash.success("Voce saiu do sistema.");
		Application.index();
	}

}
