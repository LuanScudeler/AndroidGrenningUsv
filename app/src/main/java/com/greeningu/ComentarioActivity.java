package com.greeningu;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greeningu.bean.Comentario;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.Usuario;
import com.greeningu.wsclient.ComentarioREST;
import com.greeningu.wsclient.PostagemREST;


public class ComentarioActivity extends ActionBarActivity {

    TextView titulo, imagem, descricao;
    EditText conteudoComentario;
    private Button btnEnviarComment;
    Integer idPost = null;
    private ProgressDialog progress;

    Usuario usuario;
    private String usrJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        titulo = (TextView)findViewById(R.id.tvTitulo);
        imagem = (TextView)findViewById(R.id.tvImagem);
        descricao = (TextView)findViewById(R.id.tvDescricao);
        btnEnviarComment = (Button)findViewById(R.id.button);

        conteudoComentario = (EditText)findViewById(R.id.etConteudoComentario);


        if(getIntent().getExtras() != null){
            usrJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usrJson, Usuario.class);
        }


        //RIGHT WAY - idPost vai vim do item selecionad no listView em ListaPostagem.class
        /* if(getIntent().getExtras() != null ){
            idPost = getIntent().getExtras().getInt("idPost");
        }*/

        //ID  DA POSTAGEM HARDCODED:
        idPost = 1;

        DetalhesPostagemAsysnc dpa = new DetalhesPostagemAsysnc();
        dpa.execute(idPost);

    }


    public void btnButtonSend(View v){
        Log.d("Click: ", "Sim");
        Integer[] params;
        params = new Integer[10];

        EnviarComentarioAsysnc eca = new EnviarComentarioAsysnc();

        params[0] = idPost;
        params[1] = usuario.getId();

        eca.execute(params);

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
            progress = new ProgressDialog(ComentarioActivity.this);
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

    /*ENVIAR COMENTARIO*/
    public class EnviarComentarioAsysnc extends AsyncTask<Integer, Integer,  Integer[]> {

        @Override
        protected Integer[] doInBackground(Integer[] idsPostUser) {

            String status = null;
            Integer[] result = new Integer[10];
            Comentario comment = new Comentario();

            comment.setTexto(conteudoComentario.getText().toString());
            comment.setIdPostagem(idsPostUser[0]);
            comment.setIdUsuario(idsPostUser[1]);

            Log.d("Ids: Post: ", idsPostUser[0] + ", Comunidade: " + idsPostUser[1]);
            Log.d("Conteudo: ", conteudoComentario.getText().toString());

            ComentarioREST cRest = new ComentarioREST();
            try {
                status = cRest.inserirComentario(comment);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            MensagemPadrao mp = gson.fromJson(status,MensagemPadrao.class);
            if(mp.getStatus().equals("OK"))
                result[0] = 1;

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(ComentarioActivity.this);
            progress.setMessage("Enviando coment�rio...");
            progress.show();
        }

        @Override
        protected void onPostExecute(Integer[] result) {
            progress.cancel();
            super.onPostExecute(result);
            if(result[0] == 1) {
                Log.d("Status: ", "Sucesso.");
                Toast.makeText(ComentarioActivity.this, "Comentario enviado com sucesso!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ComentarioActivity.this, "Falha no envio.", Toast.LENGTH_SHORT).show();

            conteudoComentario.setText("");

        }
    }
}
