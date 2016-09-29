package controllers;
import com.avaje.ebean.Ebean;
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

import static com.avaje.ebean.Ebean.*;

public class ProdutoController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result listar() {
        beginTransaction();
        try {
            List<Produto> produtos_list = Produto.find.orderBy("id desc").findList();
            commitTransaction();
            return ok(listar.render(produtos_list));
        }catch(Exception e) {
            rollbackTransaction();
            return null;
        }finally {
            endTransaction();
        }
    }

    public Result adicionar(){
        beginTransaction();
        try{
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.save();
            commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }catch(Exception e){
            rollbackTransaction();
            return null;
        }
        finally {
            endTransaction();
        }
    }

    public Result novo(){
        return ok(novo.render("Cadastro Produto"));
    }

    public Result editar(Long id){
        beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            commitTransaction();
            return ok(editar.render(produto));
        }catch(Exception e) {
            rollbackTransaction();
            return null;
        }finally {
            endTransaction();
        }
    }

    public Result atualizar(Long id){
        beginTransaction();
        try {
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.id = new Long(form.get("id"));
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.update();
            commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }catch(Exception e) {
            rollbackTransaction();
            return null;
        }finally {
            endTransaction();
        }
    }

    public Result deletar(Long id){
        beginTransaction();
        try {
            Produto.find.ref(id).delete();
            commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }catch(Exception e) {
            rollbackTransaction();
            return null;
        }finally {
            endTransaction();
        }
    }

    public Result formDeletar(Long id){
        beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            commitTransaction();
            return ok(deletar.render(produto));
        }catch(Exception e) {
            rollbackTransaction();
            return null;
        }finally {
            endTransaction();
        }
    }


}


