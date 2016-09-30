package controllers;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import models.Produto;
import models.Usuario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.*;
import views.html.produtos.deletar;
import views.html.produtos.editar;
import views.html.produtos.listar;
import views.html.produtos.login.login;
import views.html.produtos.novo;
import javax.inject.Inject;
import javax.sound.midi.MidiDevice;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static com.avaje.ebean.Ebean.*;

public class ProdutoController extends Auth {

    @Inject
    FormFactory formFactory;

    public Result listar(int pagina){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            PagedList<Produto> pagedProdutosList = Produto.find.findPagedList(pagina,5);
            List<Produto> produtosList = pagedProdutosList.getList();
            int qtdePaginas = pagedProdutosList.getTotalPageCount();
            commitTransaction();
            return ok(listar.render(produtosList,pagina,qtdePaginas));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

    public Result adicionar(){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.save();
            commitTransaction();
            return redirect(routes.ProdutoController.listar(1));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

    public Result novo(){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        return ok(novo.render("Cadastro Produto"));
    }

    public Result editar(Long id){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            commitTransaction();
            return ok(editar.render(produto));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

    public Result atualizar(Long id){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.id = new Long(form.get("id"));
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.update();
            commitTransaction();
            return redirect(routes.ProdutoController.listar(1));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

    public Result deletar(Long id){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            Produto.find.ref(id).delete();
            commitTransaction();
            return redirect(routes.ProdutoController.listar(1));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

    public Result formDeletar(Long id){
        if(vericaSessaoUsuario() == null){
            return redirect(routes.LoginController.login());
        }
        beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            commitTransaction();
            return ok(deletar.render(produto));
        } catch (Exception e) {
            rollbackTransaction();
            return null;
        } finally {
            endTransaction();
        }
    }

}


