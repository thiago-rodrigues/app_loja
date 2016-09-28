package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.produtos.login.login;


public class LoginController extends Controller {

    public Result login(){
        return ok(login.render("Login"));
    }
}
