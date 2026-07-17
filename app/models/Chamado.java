package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Chamado extends Model {

	public String titulo;
	public String descricao;
	public boolean ativo = true;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@Enumerated(EnumType.STRING)
	public Prioridade prioridade;

	@ManyToOne
	public Usuario usuario;
	
	@ManyToOne
	public Equipamento equipamento;

	public Chamado() {
		this.status = Status.EM_ANDAMENTO;
		this.prioridade = Prioridade.ALTA;

	}
	
}


