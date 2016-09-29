package models;
import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import javax.persistence.Entity;

@Entity
public class Usuario extends Model{


    public String usuario;
    public String senha;
    public static Finder<Long, Usuario> find = new Finder(Usuario.class);

    public Usuario validaUsuario(String usuario, String senha){
        Usuario usuarioLogin = new Usuario();
        return  usuarioLogin;
    }

}
