package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jefrsilva on 10/01/17.
 */
public class CarrinhoActivity extends AppCompatActivity {

    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;

    @BindView(R.id.valor_carrinho)
    TextView valorTotal;

    @Inject
    Carrinho carrinho;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getApplication();
        app.getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    public void carregaLista() {
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens(), this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));

        double total = 0;
        for (Item item : carrinho.getItens()) {
            total += item.getValor();
        }
        valorTotal.setText(String.format("R$ %.2f", total));
    }

//    @OnClick(R.id.fab_carrinho)
//    public void fazCheckout() {
//        final TextInputLayout layout = new TextInputLayout(this);
//        final EditText editText = new EditText(this);
//        editText.setHint("Email");
//        layout.addView(editText);
//
//        new AlertDialog.Builder(this).setView(layout).setPositiveButton("Pronto", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String json = new LivroConverter().toJson(carrinho.getItens(), editText.getText().toString());
//                webClient.enviaItensParaServidor(json);
//            }
//        }).show();
//    }

//    @Subscribe
//    public void recebeRespostaDoServer(RespostaEvent event) {
//        String body = event.body;
//
//        Toast.makeText(this, body, Toast.LENGTH_LONG)
//                .show();
//
//        carrinho.limpa();
//        carregaLista();
//    }

}