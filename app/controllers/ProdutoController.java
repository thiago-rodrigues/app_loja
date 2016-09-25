package controllers;
import models.Produto;
import play.mvc.*;
import views.html.produtos.indexProdutos;
import java.util.List;

public class ProdutoController extends Controller {

    public Result index() {
        List<Produto> produtos_list = Produto.find.all();
        return ok(indexProdutos.render(produtos_list));
    }

    public Result addProduto(){
        return ok();
    }
}


