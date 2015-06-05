package com.greeningu;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.greeningu.bean.Usuario;
import com.greeningu.wsclient.UsuarioREST;

/**
 * Created by Luan on 29/05/2015.
 */
public class MenuDetalhesUsuarioActivity extends ActionBarActivity {

    TextView username, qtdePosts, qtdeComu;
    String usuarioJson;
    Usuario usuario;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infouser);

        username = (TextView)findViewById(R.id.username);
        qtdePosts = (TextView)findViewById(R.id.qtdePosts);
        qtdeComu = (TextView)findViewById(R.id.qtdeComu);

        if(getIntent().getExtras() != null ){
            usuarioJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usuarioJson, Usuario.class);
        }

        username.setText(usuario.getNome());

        DetalhesUsuarioAsysnc dua = new DetalhesUsuarioAsysnc();
        dua.execute(usuario.getId());
    }

    public class DetalhesUsuarioAsysnc extends AsyncTask<Integer, Integer,  Object[]>{

        @Override
        protected Object[] doInBackground(Integer... idUser) {

            Object[] results = new Object[2];

            UsuarioREST uRest = new UsuarioREST();

            results[0] = (Integer)uRest.getQtdePosts(idUser[0]);
            results[1] = (Integer)uRest.getQtdeComunidade(idUser[0]);

            return results;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(MenuDetalhesUsuarioActivity.this);
            progress.setMessage("Buscando informacoes do usuario...");
            progress.show();
        }

        @Override
        protected void onPostExecute( Object[] result) {
            super.onPostExecute(result);
            qtdePosts.setText(result[0].toString());
            qtdeComu.setText(result[1].toString());
            progress.cancel();
        }
    }

}
