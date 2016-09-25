package models;
import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public class Produto extends Model{
    @Id @GeneratedValue
    public long id;
    public String nome;
    public BigDecimal valor;

    public static Finder<Long, Produto> find = new Finder<>(Produto.class);
}
