package controllers;
import models.Produto;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.*;
import views.html.produtos.deletar;
import views.html.produtos.editar;
import views.html.produtos.listar;
import views.html.produtos.novo;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.List;

public class ProdutoController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result listar() {
        List<Produto> produtos_list = Produto.find.orderBy("id desc").findList();
        return ok(listar.render(produtos_list));
    }

    public Result adicionar(){
        DynamicForm form = formFactory.form().bindFromRequest();
        Produto produto = new Produto();
        produto.nome = form.get("nome");
        produto.valor = new BigDecimal(form.get("valor"));
        produto.save();
        return redirect(routes.ProdutoController.listar());
    }
    public Result novo(){
        return ok(novo.render("Cadastro Produto"));
    }

    public Result editar(Long id){
        Produto produto = Produto.find.byId(id);
        return ok(editar.render(produto));
    }

    public Result atualizar(Long id){
        DynamicForm form = formFactory.form().bindFromRequest();
        Produto produto = new Produto();
        produto.id = new Long(form.get("id"));
        produto.nome = form.get("nome");
        produto.valor = new BigDecimal(form.get("valor"));
        produto.update();
        return redirect(routes.ProdutoController.listar());
    }
}


