package com.greeningu;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Usuario;
import com.greeningu.webservice.UsuarioREST;


public class RegistroActivity extends ActionBarActivity {
    EditText edtNome;
    EditText edtSobrenome;
    EditText edtEmail;
    EditText edtLogin;
    EditText edtSenha;
    EditText edtRepSenha;
    Spinner spnSexo;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtSobrenome = (EditText)findViewById(R.id.edtSobrenome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        edtRepSenha = (EditText)findViewById(R.id.edtRepSenha);

        spnSexo = (Spinner)findViewById(R.id.spnSexo);
        ArrayAdapter<CharSequence> arraySexo = ArrayAdapter.createFromResource(
                this, R.array.sexo, android.R.layout.simple_spinner_dropdown_item
        );
        spnSexo.setAdapter(arraySexo);

    }

    public void btnCadastrarClick(View v){
        String nome = edtNome.getText().toString();
        String sobrenome = edtSobrenome.getText().toString();
        String sexoTemp = (String) spnSexo.getSelectedItem();
        String sexo = sexoTemp;
        String email = edtEmail.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();
        String repSenha = edtRepSenha.getText().toString();

        if(!senha.equals(repSenha)){
            Toast.makeText(this,"Senhas não conferem!", Toast.LENGTH_SHORT).show();
        } else{
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setSexo(sexo);
            usuario.setEmail(email);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            CadastroUsuarioAsync caa = new CadastroUsuarioAsync();
            caa.execute(usuario);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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

    public class CadastroUsuarioAsync extends AsyncTask<Usuario,String,String> {
        @Override
        protected String doInBackground(Usuario... params) {
            publishProgress("Cadastrando...");
            UsuarioREST urest = new UsuarioREST();
            String result = "";
            try{

                for(Usuario usuario : params){
                    result = urest.inserir(usuario);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            Gson gson = new Gson();

            MensagemPadrao mp = gson.fromJson(result,MensagemPadrao.class);

            publishProgress(mp.getStatus());

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(RegistroActivity.this);
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
            finish();
        }
    }
}
