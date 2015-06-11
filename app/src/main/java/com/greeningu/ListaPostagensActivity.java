package com.greeningu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greeningu.bean.PostagemSimplificada;
import com.greeningu.bean.Usuario;
import com.greeningu.wsclient.PostagemREST;

import java.util.ArrayList;

import static android.view.View.OnClickListener;


public class ListaPostagensActivity extends ActionBarActivity {


    protected ArrayList<PostagemSimplificada> lista;
    Usuario usuario;
    private String usrJson;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postagens);

        if(getIntent().getExtras() != null){
            usrJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usrJson, Usuario.class);
        }

        lista = new ArrayList<PostagemSimplificada>();

        ListarPostagensAsync lpa = new ListarPostagensAsync();

        lpa.execute(usuario.getId());

        /*ListView lvListaPostagens = (ListView) findViewById(R.id.lvPostagens);

        ListaPostagensAdapter adapter = new ListaPostagensAdapter(lista);

        lvListaPostagens.setAdapter(adapter);*/
    }


    public AdapterView.OnItemClickListener clickItemLista(final Context context){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idPostagem = lista.get(position).getId();

                Intent i = new Intent(ListaPostagensActivity.this, DetalhesPostagemActivity.class);
                Bundle b = new Bundle();
                b.putInt("idPostagem", idPostagem);
                i.putExtras(b);
                startActivity(i);
            }
        };
    }

    public class ListarPostagensAsync  extends AsyncTask<Integer, String, ArrayList<PostagemSimplificada>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ListaPostagensActivity.this);
            dialog.setMessage("Buscando Postagens...");
            dialog.show();
        }

        @Override
        protected ArrayList<PostagemSimplificada> doInBackground(Integer... idUser) {
            PostagemREST rest = new PostagemREST();

            ArrayList<PostagemSimplificada> postagens = rest.listarNovasPostagens(idUser[0]);

            return postagens;
        }

        @Override
        protected void onPostExecute(ArrayList<PostagemSimplificada> postagens) {
            super.onPostExecute(postagens);
            if(postagens != null) {
                ListView lvListaPostagens = (ListView) findViewById(R.id.lvPostagens);

                lista = postagens;

                ListaPostagensAdapter adapter = new ListaPostagensAdapter(lista);

                lvListaPostagens.setAdapter(adapter);

                lvListaPostagens.setOnItemClickListener(clickItemLista(ListaPostagensActivity.this));

                dialog.cancel();
            } else {
                Toast.makeText(ListaPostagensActivity.this,"Não existem novas postagens",Toast.LENGTH_SHORT).show();
                dialog.cancel();
                finish();
            }
        }
    }

    public class ListaPostagensAdapter extends BaseAdapter {

        private ArrayList<PostagemSimplificada> lista;

        public ListaPostagensAdapter(ArrayList<PostagemSimplificada> lista) {
            this.lista = lista;
        }

        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Object getItem(int position) {
            return lista.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            PostagemSimplificada ps = lista.get(position);
            View layout;

            Log.d("idPostagem: ", ps.getId().toString());

            LayoutInflater inflater = (LayoutInflater)ListaPostagensActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_lista_postagens,null);


            TextView titulo = (TextView)v.findViewById(R.id.txtTitulo);
            titulo.setText(ps.getTitulo());

            TextView username = (TextView)v.findViewById(R.id.txtNomeUsuario);
            username.setText(ps.getNomeUsuario());

            TextView data = (TextView)v.findViewById(R.id.txtData);
            data.setText(ps.getDataPostagem().toString());

            TextView idPostagem = (TextView)v.findViewById(R.id.txtIdPostagem);
            idPostagem.setText(ps.getId().toString());


            return v;
        }
    }
}
