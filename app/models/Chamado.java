package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Chamado extends Model {

	public String titulo;
	public String descricao;
	public String prioridade;
	public String status;
	public boolean ativo = true;
	
	@ManyToOne
	public Usuario usuario;
	
	@ManyToOne
	public Equipamento equipamento;
}
