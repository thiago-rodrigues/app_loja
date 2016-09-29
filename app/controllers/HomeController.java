package controllers;
import models.Usuario;
import play.mvc.*;
import views.html.*;


public class HomeController extends Controller {

    public Result index() {
        Usuario usuario = new Usuario();
        if(usuario.vericaSessaoUsuario() != null) {
            return ok(index.render("Pagina Inicial!!"));
        }
        return redirect(routes.LoginController.login());
    }

}
