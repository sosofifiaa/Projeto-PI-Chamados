package controllers;

import java.util.List;
import models.Usuario;
import models.Chamado;
import models.Equipamento;
import models.Perfil;
import play.mvc.Controller;
import play.mvc.With;
import security.Administrador;
import security.Seguranca;


@With(Seguranca.class)
public class Usuarios extends Controller {

    public static void listar() {
        List<Usuario> usuarios = Usuario.find("ativo = ?1", true).fetch();
        List<Equipamento> equipamentos = Equipamento.find("ativo = true").fetch();
        List<Chamado> chamados = Chamado.find("ativo = ?1", true).fetch();
        render(usuarios, equipamentos, chamados);
    }

    public static void form() {
        render();
    }

    public static void salvar(Equipamento e, Chamado c) {
    	if (validation.hasErrors()) {
    		flash.error("Corrija os campos destacados antes de continuar!");
    		params.flash();
    		validation.keep();
    		form();
    	}
    	
      Usuario logado = Usuario.findById(Long.valueOf(session.get("idUsuario")));
      
        e.save();
        c.usuario = logado;
        c.equipamento = e;
        c.save();
        listar();
        flash.success("Chamado aberto com sucesso!");
        listar();
    }
    public static void cadastro() {
        render();
    }
    public static void salvarCadastro(Usuario u) {
    	if (Usuario.existeUsuario(u.email)) {
    		validation.addError("u.email", "Este email já está cadastrado");
    		
    	}
    	  if (validation.hasErrors()) {
              flash.error("Corrija os campos destacados antes de continuar.");
              params.flash();
              validation.keep();
              cadastro();
          }
    	  u.perfil = Perfil.USUARIO;
    	  u.save();
    	  
    	  flash.success("Cadastro realizado com sucesso! Faça login para continuar.");
          Logins.form();
    }
    @Administrador
    public static void editarUsuario(Long id) {
        Usuario u = Usuario.findById(id);
        render(u);
    }

    public static void atualizarUsuario(Long id, Usuario u) {
        Usuario usuario = Usuario.findById(id);
        usuario.nome = u.nome;
        usuario.email = u.email;
        if (u.senha != null && !u.senha.isEmpty()) {
        	usuario.senha = u.senha;
        }
        
        usuario.perfil = u.perfil;
        usuario.save();
        listar();
    }
    @Administrador
    public static void removerUsuario(Long id) {
        Usuario u = Usuario.findById(id);
        u.ativo = false;
        u.save();
        listar();
    }
    
    @Administrador
    public static void editarEquipamento(Long id) {
        Equipamento e = Equipamento.findById(id);
        render(e);
    }
    @Administrador
    public static void atualizarEquipamento(Long id, Equipamento e) {
        Equipamento equipamento = Equipamento.findById(id);
        equipamento.nome = e.nome;
        equipamento.patrimonio = e.patrimonio;
        equipamento.tipo = e.tipo;
        equipamento.laboratorio = e.laboratorio;
        equipamento.save();
        listar();
    }
    @Administrador

    public static void removerEquipamento(Long id) {
        Equipamento e = Equipamento.findById(id);
        e.ativo = false;
        e.save();
        listar();
    }
    @Administrador

    public static void editarChamado(Long id) {
        Chamado c = Chamado.findById(id);
        render(c);
    }
    @Administrador

    public static void atualizarChamado(Long id, Chamado c) {
        Chamado chamado = Chamado.findById(id);
        chamado.titulo = c.titulo;
        chamado.descricao = c.descricao;
        chamado.prioridade = c.prioridade;
        chamado.status = c.status;
        chamado.save();
        listar();
    }
    @Administrador

    public static void removerChamado(Long id) {
        Chamado c = Chamado.findById(id);
        c.ativo = false;
        c.save();
        listar();
    }
    public static void pesquisar(String termo) {
        String busca = "%" + termo.toLowerCase() + "%";
        List<Usuario> usuarios = Usuario.find(
            "ativo = true and (lower(nome) like ?1 or lower(email) like ?1)", busca).fetch();
        List<Equipamento> equipamentos = Equipamento.find(
            "ativo = true and (lower(nome) like ?1 or lower(patrimonio) like ?1 or lower(tipo) like ?1 or lower(laboratorio) like ?1)", busca).fetch();
        List<Chamado> chamados = Chamado.find(
            "ativo = true and (lower(titulo) like ?1 or lower(descricao) like ?1 or lower(prioridade) like ?1 or lower(status) like ?1)", busca).fetch();
        render("Usuarios/listar.html", usuarios, equipamentos, chamados);
    }
}