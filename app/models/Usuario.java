package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Usuario extends Model{
	
    @Required // nao deixa deixar em branco
	public String nome;
    
    @Required
    @Email //nao deixar sem @
	public String email;
    
    @Required
	public String senha;
	public boolean ativo = true; //apagar sem apagar do bd
	
	 @Required
	 @Enumerated(EnumType.STRING) //salva o nome do enum(tec, admin, usuario) no bd
	 public Perfil perfil;
	 
	 public Usuario() { //CONSTRUTOR USUARIO, TODO USUARIO JA VEM COMO USUARIO
		 this.perfil = Perfil.USUARIO;
	 }
	 
	 public static Usuario obterUsuario(String email, String senha) {//procura usuarios ativos com o email e senha, se n achar retorna null
	        return Usuario.find("email = ?1 and senha = ?2 and ativo = true", email, senha).first();
	    }
	 public static boolean existeUsuario(String email) {//procura usuarios com esse email
	        return Usuario.find("email = ?1 and ativo = true", email).first() != null;
	    }
    
    
	
}
