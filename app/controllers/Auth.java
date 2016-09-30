package controllers;
import play.mvc.Controller;

public class Auth extends Controller {

    public String vericaSessaoUsuario(){
        String usuario = session("conectado");
        return usuario;
    }

}
