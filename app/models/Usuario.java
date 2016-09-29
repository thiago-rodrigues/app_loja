package models;
import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario extends Model{
    @Id
    public Long id;
    @Column(unique=true)
    public String usuario;
    public String senha;
    public static Finder<Long, Usuario> find = new Finder(Usuario.class);

    public static Usuario validaUsuario(String usuario, String senha) {
        Usuario usuarioLogin = Usuario.find.where().eq("usuario",usuario).eq("senha",senha).findUnique();
        return usuarioLogin;
    }
}


