package controllers;

import java.util.List;
import models.Usuario;
import models.Chamado;
import models.Equipamento;
import play.mvc.Controller;

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

    public static void salvar(Usuario u, Equipamento e, Chamado c) {
        u.save();
        e.save();
        c.usuario = u;
        c.equipamento = e;
        c.save();
        listar();
    }

    public static void editarUsuario(Long id) {
        Usuario u = Usuario.findById(id);
        render(u);
    }

    public static void atualizarUsuario(Long id, Usuario u) {
        Usuario usuario = Usuario.findById(id);
        usuario.nome = u.nome;
        usuario.email = u.email;
        usuario.senha = u.senha;
        usuario.save();
        listar();
    }

    public static void removerUsuario(Long id) {
        Usuario u = Usuario.findById(id);
        u.ativo = false;
        u.save();
        listar();
    }

    public static void editarEquipamento(Long id) {
        Equipamento e = Equipamento.findById(id);
        render(e);
    }

    public static void atualizarEquipamento(Long id, Equipamento e) {
        Equipamento equipamento = Equipamento.findById(id);
        equipamento.nome = e.nome;
        equipamento.patrimonio = e.patrimonio;
        equipamento.tipo = e.tipo;
        equipamento.laboratorio = e.laboratorio;
        equipamento.save();
        listar();
    }

    public static void removerEquipamento(Long id) {
        Equipamento e = Equipamento.findById(id);
        e.ativo = false;
        e.save();
        listar();
    }

    public static void editarChamado(Long id) {
        Chamado c = Chamado.findById(id);
        render(c);
    }

    public static void atualizarChamado(Long id, Chamado c) {
        Chamado chamado = Chamado.findById(id);
        chamado.titulo = c.titulo;
        chamado.descricao = c.descricao;
        chamado.prioridade = c.prioridade;
        chamado.status = c.status;
        chamado.save();
        listar();
    }

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