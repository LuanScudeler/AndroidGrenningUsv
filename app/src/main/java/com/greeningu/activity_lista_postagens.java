package com.greeningu;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.greeningu.bean.PostagemSimplificada;
import com.greeningu.bean.Usuario;
import com.greeningu.wsclient.PostagemREST;

import java.util.ArrayList;
import java.util.Iterator;


public class activity_lista_postagens extends ActionBarActivity {


    protected ArrayList<PostagemSimplificada> lista;
    Usuario usuario;
    private String usrJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lista_postagens);

        if(getIntent().getExtras() != null){
            usrJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usrJson, Usuario.class);
        }

        lista = new ArrayList<PostagemSimplificada>();

        carregarListaPostagens();

        ListView lvListaPostagens = (ListView) findViewById(R.id.lvPostagens);
        lvListaPostagens.setAdapter(new ListaPostagensAdpter(this,lista));
        lvListaPostagens.setOnItemClickListener(chamaPosts(this));
    }

    public AdapterView.OnItemClickListener chamaPosts(final Context context){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO: Identificar id_postagem do item da lista clicado
                    //esse id ira ser transferido para a activity seguinte
                    // para busca as informações da postagem (image, titulo, descrição..)
                TextView idPost = (TextView)view.findViewById(R.id.tvIdPostagem);
                Integer rowIdPost = Integer.parseInt(idPost.getText().toString());

                Intent i = new Intent(activity_lista_postagens.this, DetalhesPostagemActivity.class);
                Bundle b = new Bundle();
                b.putInt("idPost", rowIdPost);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    private void carregarListaPostagens() {

        ListarPostagensAsync lpa = new ListarPostagensAsync();

        lpa.execute(usuario.getId());

    }

    public class ListarPostagensAsync  extends AsyncTask<Integer, String, ArrayList<PostagemSimplificada>> {

        @Override
        protected ArrayList<PostagemSimplificada> doInBackground(Integer... idUser) {
            PostagemREST rest = new PostagemREST();

            ArrayList<PostagemSimplificada> postagens = rest.listarNovasPostagens(idUser[0]);

            return postagens;
        }

        @Override
        protected void onPostExecute(ArrayList<PostagemSimplificada> postagens) {
            super.onPostExecute(postagens);

            lista.addAll(postagens);
        }
    }
}
