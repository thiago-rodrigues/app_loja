package models;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public class Produto extends Model{
    @Id @GeneratedValue
    private long id;
    private String nome;
    private BigDecimal valor;
}
