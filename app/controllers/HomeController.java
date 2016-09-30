package controllers;
import models.Usuario;
import play.mvc.*;
import views.html.*;


public class HomeController extends Auth {

    public Result index() {
        if(vericaSessaoUsuario() == null) {
            return redirect(routes.LoginController.login());
        }
        return ok(index.render("Pagina Inicial!!"));
    }

}
