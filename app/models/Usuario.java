package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

import play.data.validation.Required;

@Entity
public class Usuario extends Model{
	
    @Required 
	public String nome;
	// public String email;
	//public String senha;
	//public boolean ativo = true;
}
