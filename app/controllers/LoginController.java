package controllers;

import models.Usuario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.produtos.login.login;

import javax.inject.Inject;


public class LoginController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result login(){
        String usuario = session("conectado");
        if(usuario != null){
            return redirect(routes.HomeController.index());
        }else{
            return ok(login.render("Login"));
        }
    }

    public Result logar(){
        DynamicForm form = formFactory.form().bindFromRequest();
        Usuario login = new Usuario();
        login.usuario = form.get("usuario");
        login.senha  = form.get("senha");

        if(Usuario.validaUsuario(login.usuario,login.senha) != null){
            session("conectado", login.usuario);
            return redirect(routes.HomeController.index());
        }else {
            return redirect(routes.LoginController.login());
        }
    }

    public Result Logout(){
        session().clear();
        return ok();
    }


}
