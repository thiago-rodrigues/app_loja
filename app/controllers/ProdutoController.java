package controllers;
import models.Produto;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.produtos.indexProdutos;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        List<Produto> produtos_list = Produto.find.all();
        Logger.info("Produtos"+ produtos_list);
        return ok(indexProdutos.render(produtos_list));
    }

    public Result addProduto(){
        DynamicForm form = formFactory.form().bindFromRequest();
        Produto produto = new Produto();
        produto.nome = form.get("nome");
        produto.valor = new BigDecimal(form.get("valor"));
        produto.save();

        return redirect(routes.ProdutoController.index());
    }
}


