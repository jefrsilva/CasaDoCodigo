package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jefrsilva on 11/01/17.
 */

@Module
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

}
