package com.greeningu;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.Usuario;
import com.greeningu.wsclient.PostagemREST;
import com.greeningu.wsclient.UsuarioREST;


public class DetalhesPostagemActivity extends ActionBarActivity {

    TextView titulo, imagem, descricao;
    Integer idPost = null;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_postagem);

        if(getIntent().getExtras() != null ){
            idPost = getIntent().getExtras().getInt("idPost");
        }

        DetalhesPostagemAsysnc dpa = new DetalhesPostagemAsysnc();
        dpa.execute(idPost);

    }

    public class DetalhesPostagemAsysnc extends AsyncTask<Integer, Integer,  Postagem> {

        @Override
        protected Postagem doInBackground(Integer... idUser) {

            Postagem results = new Postagem();

            PostagemREST pRest = new PostagemREST();

            results = pRest.buscarPostagem(idUser[0]);

            Log.d("Titulo: ", results.getTitulo() + " idPost: " + results.getId() + " Data: " + results.getData());

            return results;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(DetalhesPostagemActivity.this);
            progress.setMessage("Buscando detalhes da postagem...");
            progress.show();
        }

        @Override
        protected void onPostExecute( Postagem result) {
            super.onPostExecute(result);
            titulo.setText(result.getTitulo().toString());
            imagem.setText(result.getImagem().toString());
            descricao.setText(result.getDescricao().toString());
            progress.cancel();
        }
    }


}
