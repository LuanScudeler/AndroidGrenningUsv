package com.greeningu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.UsuarioLogin;
import com.greeningu.webservice.UsuarioREST;

import java.util.concurrent.Executor;


public class MainActivity extends ActionBarActivity {
    private ProgressDialog progress;
    private EditText edtUsuario;
    private EditText edtSenha;
    public static String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
    }

    public void btnRegistroClick(View v){
        Intent intent = new Intent(this,RegistroActivity.class);
        startActivity(intent);
    }

    public void btnEntrarClick(View v){

        if(edtUsuario.getText().equals("")){
            Toast.makeText(this, "Favor inserir um usuário!", Toast.LENGTH_SHORT).show();
        }else if(edtSenha.getText().equals("")){
            Toast.makeText(this, "Favor inserir a senha!", Toast.LENGTH_SHORT).show();
        }else{
            UsuarioLogin ul = new UsuarioLogin();

            ul.setId(0);
            ul.setLogin(String.valueOf(edtUsuario.getText()));
            ul.setSenha(String.valueOf(edtSenha.getText()));

            LoginAsync la = new LoginAsync();

            la.execute(ul);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class LoginAsync extends AsyncTask<UsuarioLogin,String, String>{

        @Override
        protected String doInBackground(UsuarioLogin[] params) {
            publishProgress("Fazendo login...");

            UsuarioREST urest = new UsuarioREST();

            String result = "";

            try{
                for (UsuarioLogin ul : params){
                    result = urest.login(ul);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MainActivity.this);
            progress.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progress.setMessage(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            progress.cancel();
            super.onPostExecute(s);
            //Resposta do servidor
            Gson gson = new Gson();
            MensagemPadrao msg = gson.fromJson(s, MensagemPadrao.class);

            if(msg.getStatus().equals("OK")){
                //String usuarioJSON = msg.getInfo();

                //UsuarioLogin ul = gson.fromJson(usuarioJSON, UsuarioLogin.class);

                //TODO instanciar novo objeto UsuarioLogin e enviar para HomeActivity

                Log.i("STATUS.INFO",msg.getInfo());

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(MainActivity.this, "Usuário e/ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
