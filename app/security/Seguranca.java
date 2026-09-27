package security;

import controllers.Logins;
import models.Perfil;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

    @Before
    static void auth() {
        // request.action vem como "Usuarios.cadastro", "Usuarios.salvarCadastro", etc.
        String acao = request.action;

        boolean rotaPublica = "Usuarios.cadastro".equals(acao)
                || "Usuarios.salvarCadastro".equals(acao);

        if (!rotaPublica && !session.contains("usuarioLogado")) {
            flash.error("Restrito para usuários autenticados!");
            Logins.form();
        }
    }

    @Before
    static void verificarAdministrador() {
        String perfil = session.get("perfilUsuario");

        Administrador possuiAnotacaoAdministrador = getActionAnnotation(Administrador.class);

        boolean autorizado = Perfil.ADMINISTRADOR.name().equals(perfil)
                || Perfil.TECNICO.name().equals(perfil);

        if (possuiAnotacaoAdministrador != null && !autorizado) {
            forbidden("Acesso restrito aos administradores e técnicos do sistema");
        }
    }
}