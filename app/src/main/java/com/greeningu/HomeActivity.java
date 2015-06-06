package com.greeningu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.greeningu.bean.Comunidade;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.PostagemSimplificada;
import com.greeningu.bean.Usuario;
import com.greeningu.util.CodificadorBase64;
import com.greeningu.wsclient.ComunidadeREST;
import com.greeningu.wsclient.PostagemREST;
import com.greeningu.wsclient.UsuarioREST;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;


public class HomeActivity extends ActionBarActivity {

    // ########## Global resources ##########
    public static ProgressDialog dialog;
    TextView username, qtdePosts, qtdeComu;
    String usuarioJson;
    Usuario usuario;

    private ProgressDialog progress;


    public static final String FALHA_AC_SERV = "Falha ao acessar o serviço";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.menu_nova_postagem:
                Intent i = new Intent(HomeActivity.this, NovaPostagemActivity.class);
                Bundle b = new Bundle();
                b.putString("usuario",usuarioJson);
                i.putExtras(b);
                startActivity(i);
                return true;
            case R.id.menu_infocomu:
                Log.d("Entrou: ", "Sim");
                Intent intent2 = new Intent(this, MenuDetalhesComunidadeActivity.class);
                Bundle b2 = new Bundle();
                b2.putString("usuario", usuarioJson);
                intent2.putExtras(b2);
                startActivity(intent2);
                return true;
            case R.id.menu_listar_postagens:
                // TODO
            default:
                Log.e("Caiu no default: ", "Sim");
                return super.onOptionsItemSelected(item);
        }
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
            progress = new ProgressDialog(HomeActivity.this);
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


