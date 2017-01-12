package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import dagger.Component;

/**
 * Created by jefrsilva on 11/01/17.
 */

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {

    void inject(DetalhesLivroFragment fragment);
    void inject(CarrinhoActivity activity);

}
