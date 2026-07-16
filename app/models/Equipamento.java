package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Equipamento extends Model {
	
	public String nome;
	public String patrimonio;
	public String tipo;
	public String laboratorio;
	public boolean ativo = true;

}
