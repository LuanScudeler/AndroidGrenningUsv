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
public class DetalhesUsuarioActivity extends ActionBarActivity {

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

        //TODO: Trazer quantidade de comunidades
        if(getIntent().getExtras() != null ){
            usuarioJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usuarioJson, Usuario.class);
        }

        username.setText(usuario.getNome());

        DetalhesUsuarioAsysnc dua = new DetalhesUsuarioAsysnc();
        dua.execute(usuario.getId());
    }

    public class DetalhesUsuarioAsysnc extends AsyncTask<Integer, Integer, Integer>{

        @Override
        protected Integer doInBackground(Integer... idUser) {
            Integer result = null;

            UsuarioREST uRest = new UsuarioREST();

            result = uRest.getQtdePosts(idUser[0]);

            return result;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(DetalhesUsuarioActivity.this);
            progress.setMessage("Buscando informaçõees do usuário...");
            progress.show();
        }

        @Override
        protected void onPostExecute(Integer result) {
            qtdePosts.setText(result.toString());
            progress.cancel();
        }
    }

}
