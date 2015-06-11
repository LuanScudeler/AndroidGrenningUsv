package com.greeningu;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.Usuario;
import com.greeningu.util.CodificadorBase64;
import com.greeningu.wsclient.PostagemREST;
import com.greeningu.wsclient.UsuarioREST;


public class DetalhesPostagemActivity extends ActionBarActivity {

    TextView txtTitulo, txtDescricao;
    ImageView ivImagem;
    Integer idPostagem = null;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_postagem);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        ivImagem = (ImageView) findViewById(R.id.ivImagem);


        if(getIntent().getExtras() != null ){
            idPostagem = getIntent().getExtras().getInt("idPostagem");
        }

        DetalhesPostagemAsysnc dpa = new DetalhesPostagemAsysnc();
        dpa.execute(idPostagem);

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

            byte[] b = CodificadorBase64.decodificar(result.getImagem());


            txtTitulo.setText(result.getTitulo().toString());
            txtDescricao.setText(result.getDescricao().toString());
            ivImagem.setImageBitmap(BitmapFactory.decodeByteArray(b,0,b.length));

            //Toast.makeText(DetalhesPostagemActivity.this,result.getTitulo(),Toast.LENGTH_SHORT).show();
            progress.cancel();
        }
    }


}
