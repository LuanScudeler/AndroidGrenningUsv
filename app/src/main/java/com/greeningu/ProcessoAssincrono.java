package com.greeningu;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.greeningu.bean.Usuario;
import com.greeningu.webservice.UsuarioREST;

/**
 * Created by Jadson on 03/05/2015.
 */
public class ProcessoAssincrono extends AsyncTask<Object,Void,String> {
    @Override
    protected String doInBackground(Object... params) {
        //Teste OK
        String resposta = "";
        Usuario usuario = new Usuario();
        usuario.setNome("Jadson");
        usuario.setSobrenome("Oliveira");
        usuario.setEmail("j@com.br");
        usuario.setLogin("jor2");

        UsuarioREST usuarioREST = new UsuarioREST();

        //TODO usar o parametro Object -> terminar de implementar

        try{
            resposta = usuarioREST.inserir(usuario);
        }catch(Exception e){
            e.printStackTrace();
        }
        return resposta;
    }

}
