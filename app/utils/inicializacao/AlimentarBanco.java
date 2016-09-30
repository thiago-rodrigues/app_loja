package utils.inicializacao;
import com.avaje.ebean.Ebean;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigFactory;
import models.Produto;
import models.Usuario;
import play.Logger;
import play.libs.Yaml;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;

@Singleton
public class AlimentarBanco {
    public AlimentarBanco(){
        carregarYaml();
    }

    private Collection<?> carregarYaml(String fullPath){
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream(fullPath);

        return (Collection<?>) Yaml.load(stream, this.getClass().getClassLoader());
    }

    private void carregarYaml() {
        Logger.info("data:" + LocalDate.now());
        if (Produto.find.findRowCount() == 0) {
            Collection<?> lista = carregarYaml(ConfigFactory.load()
                    .getString("alimentar_produtos"));
            Ebean.saveAll(lista);
        }
        if (Usuario.find.findRowCount() == 0) {
            Collection<?> lista = carregarYaml(ConfigFactory.load()
                    .getString("alimentar_usuarios"));
            Ebean.saveAll(lista);
        }
    }
}
