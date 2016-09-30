import com.google.inject.AbstractModule;
import utils.inicializacao.AlimentarBanco;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(AlimentarBanco.class).asEagerSingleton();
    }

}
