package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.listener.EndlessListListener;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    private List<Livro> livros = new ArrayList<>();
    private FirebaseRemoteConfig firebaseRemoteConfig;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        ButterKnife.bind(this, view);

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        firebaseRemoteConfig.fetch(5)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        boolean zebrada = true;
                        if (task.isSuccessful()) {
                            firebaseRemoteConfig.activateFetched();
                            zebrada = firebaseRemoteConfig.getBoolean("tipo_da_lista");
                        }
                        recyclerView.setAdapter(new LivroAdapter(livros, zebrada));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                });

        return view;
    }

    public void populaListaCom(final List<Livro> livros) {
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();

        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void carregaMaisItens() {
                new WebClient().getLivros(livros.size(), 10);
            }
        });
   }
}
