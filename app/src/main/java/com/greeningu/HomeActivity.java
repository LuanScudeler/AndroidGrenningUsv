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
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.greeningu.bean.Comunidade;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.Usuario;
import com.greeningu.util.CodificadorBase64;
import com.greeningu.wsclient.ComunidadeREST;
import com.greeningu.wsclient.PostagemREST;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;


public class HomeActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    private android.support.v7.app.ActionBar actionbar;
    private ViewPager viewpager;
    private FragmentPagerAdapter ft;

    // ########## createPosts ##########
    private static String imgPath;
    private Button btnSelecionarImagem;
    private Button btnEnviar;
    private EditText edtTituloPostagem;
    private EditText edtDescricaoPostagem;
    private ImageView ivImagemPostagem;
    public static final int RESULT_LOAD_IMG = 1;

    // ########## Global resources ##########
    private String usuarioJson;
    private Usuario usuario;
    private ProgressDialog dialog;

    public static final String FALHA_AC_SERV = "Falha ao acessar o serviço";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getIntent().getExtras() != null){
            usuarioJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usuarioJson,Usuario.class);
        }

        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));


        actionbar = getSupportActionBar();
        actionbar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        actionbar.addTab(actionbar.newTab().setText("Criar postagem").setTabListener(this));
        actionbar.addTab(actionbar.newTab().setText("Novas Postagens").setTabListener(this));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewpager);



        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0){
                   // TODO
                } else if(position == 1){
                    // TODO
                }
            }

            @Override
            public void onPageSelected(int position) {
                actionbar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // ########## createPosts ##########

        btnSelecionarImagem = (Button) findViewById(R.id.btnSelecionarImagem);
        edtTituloPostagem = (EditText) findViewById(R.id.edtTituloPostagem);
        edtDescricaoPostagem = (EditText) findViewById(R.id.edtDescricaoPostagem);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
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
            case R.id.menu_infouser:
                Log.d("Entrou: ", "Sim");
                Intent intent = new Intent(this, MenuDetalhesUsuarioActivity.class);
                Bundle b = new Bundle();
                b.putString("usuario", usuarioJson);
                intent.putExtras(b);
                startActivity(intent);
                return true;
            case R.id.menu_infocomu:
                Log.d("Entrou: ", "Sim");
                Intent intent2 = new Intent(this, MenuDetalhesComunidadeActivity.class);
                Bundle b2 = new Bundle();
                b2.putString("usuario", usuarioJson);
                intent2.putExtras(b2);
                startActivity(intent2);
                return true;
            default:
                Log.e("Caiu no default: ", "Sim");
                return super.onOptionsItemSelected(item);
        }
        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/


    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    // ########## createPosts ##########

    public void btnSelecionarImagemClick(View v){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {


                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ivImagemPostagem = (ImageView) findViewById(R.id.ivImagemPostagem);

                imgPath = imgDecodableString;

                ivImagemPostagem.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "Você não escolheu uma imagem.",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Falha", Toast.LENGTH_LONG).show();
            Log.e("Erro: ", e.getMessage());
        }


    }


    public void btnEnviarClick(View v){

        if(edtTituloPostagem.getText().toString().equals("")){
            Toast.makeText(HomeActivity.this,"Insira um título!",Toast.LENGTH_SHORT).show();
        } else if(edtDescricaoPostagem.getText().toString().equals("")){
            Toast.makeText(HomeActivity.this,"Insira uma descrição!",Toast.LENGTH_SHORT).show();
        } else if(imgPath.equals(null) || imgPath.equals("")){
            Toast.makeText(HomeActivity.this,"Selecione uma imagem!",Toast.LENGTH_SHORT).show();
        } else {

            try {
                File file = new File(imgPath);
                FileInputStream fis = new FileInputStream(file);

                byte[] b = new byte[(int) file.length()];

                fis.read(b);

                String imgStr = CodificadorBase64.codificarParaString(b);

                Postagem postagem = new Postagem();

                edtTituloPostagem = (EditText) findViewById(R.id.edtTituloPostagem);
                edtDescricaoPostagem = (EditText) findViewById(R.id.edtDescricaoPostagem);

                postagem.setIdUsuario(usuario.getId());
                postagem.setTitulo(edtTituloPostagem.getText().toString());
                postagem.setDescricao(edtDescricaoPostagem.getText().toString());
                postagem.setData(new Date());
                postagem.setImagem(imgStr);

                InserirPostagemAsync ipa = new InserirPostagemAsync();

                ipa.execute(postagem);


            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }


    // ########## Asysnc extended classes ##########

    public class InserirPostagemAsync extends AsyncTask<Postagem, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(HomeActivity.this);
            dialog.setMessage("Salvando postagem...");
            dialog.show();
        }

        @Override
        protected String doInBackground(Postagem... params) {

            PostagemREST prest = new PostagemREST();

            String result = "";

            try{
                result = prest.inserir(params[0]);
            } catch(Exception e){
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.cancel();
            super.onPostExecute(s);

            if(s.contains("Falha")) {
                Toast.makeText(HomeActivity.this, FALHA_AC_SERV, Toast.LENGTH_SHORT).show();
            } else {
                Gson gson = new Gson();
                MensagemPadrao msg = gson.fromJson(s, MensagemPadrao.class);
                if(msg.getStatus().equals("OK")){
                    Log.i("STATUS.INFO",msg.getInfo());
                    Toast.makeText(HomeActivity.this,msg.getInfo(),Toast.LENGTH_SHORT).show();
                }

                edtTituloPostagem.setText("");
                edtDescricaoPostagem.setText("");
                ivImagemPostagem.setImageBitmap(null);

            }
        }
    }


}
