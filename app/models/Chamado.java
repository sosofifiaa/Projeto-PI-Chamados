package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Chamado extends Model {
	
    @Required
	public String titulo;
    
    @Required
	public String descricao;
	
    public boolean ativo = true;
    @Required
	@Enumerated(EnumType.STRING)
	public Status status;
    @Required
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


