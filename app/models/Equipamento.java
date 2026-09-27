package models;

import javax.persistence.Entity;
import play.data.validation.Required;

import play.db.jpa.Model;

@Entity
public class Equipamento extends Model {
	@Required

	public String nome;
	@Required
	public String patrimonio;
	@Required
	public String tipo;
	@Required
	public String laboratorio;
	
	public boolean ativo = true;

}
