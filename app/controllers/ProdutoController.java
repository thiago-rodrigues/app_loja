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

public class ProdutoController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result listar() {
        Ebean.beginTransaction();
        try {
            List<Produto> produtos_list = Produto.find.orderBy("id desc").findList();
            Ebean.commitTransaction();
            return ok(listar.render(produtos_list));
        }finally {
            Ebean.rollbackTransaction();
        }
    }

    public Result adicionar(){
        Ebean.beginTransaction();
        try{
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.save();
            Ebean.commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }finally {
            Ebean.rollbackTransaction();
        }
    }
    public Result novo(){
        return ok(novo.render("Cadastro Produto"));
    }

    public Result editar(Long id){
        Ebean.beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            Ebean.commitTransaction();
            return ok(editar.render(produto));
        }finally {
            Ebean.rollbackTransaction();
        }
    }

    public Result atualizar(Long id){
        Ebean.beginTransaction();
        try {
            DynamicForm form = formFactory.form().bindFromRequest();
            Produto produto = new Produto();
            produto.id = new Long(form.get("id"));
            produto.nome = form.get("nome");
            produto.valor = new BigDecimal(form.get("valor"));
            produto.update();
            Ebean.commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }finally {
            Ebean.rollbackTransaction();
        }
    }
    public Result deletar(Long id){
        Ebean.beginTransaction();
        try {
            Produto.find.ref(id).delete();
            Ebean.commitTransaction();
            return redirect(routes.ProdutoController.listar());
        }finally {
            Ebean.rollbackTransaction();
        }
    }

    public Result formDeletar(Long id){
        Ebean.beginTransaction();
        try {
            Produto produto = Produto.find.byId(id);
            Ebean.commitTransaction();
            return ok(deletar.render(produto));
        }finally {
            Ebean.rollbackTransaction();
        }

    }


}


