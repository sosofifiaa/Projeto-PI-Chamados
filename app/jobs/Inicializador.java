package jobs;
import models.Perfil;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	public void doJob() {
		if(Usuario.count() == 0 ) {
			Usuario admin = new Usuario();
			admin.nome = "Administrador";
			admin.email = "admin@ti.com";
			admin.senha = "admin123";
			admin.perfil = Perfil.ADMINISTRADOR;
			admin.save();
					
		}
	}

}
