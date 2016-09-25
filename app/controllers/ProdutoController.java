package controllers;
import play.mvc.*;
import views.html.produtos.indexProdutos;


public class ProdutoController extends Controller {

    public Result index() {
        return ok(indexProdutos.render("Index Produto Controller"));
    }

    public Result addProduto(){
        return ok(indexProdutos.render("Adicionado produtos!!"));
    }
}


