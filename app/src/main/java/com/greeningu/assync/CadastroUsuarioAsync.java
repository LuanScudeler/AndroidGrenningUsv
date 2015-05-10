package com.greeningu.assync;

import android.os.AsyncTask;

import com.greeningu.bean.Usuario;
import com.greeningu.webservice.UsuarioREST;

/**
 * Created by Jadson on 05/05/2015.
 */
public class CadastroUsuarioAsync extends AsyncTask<Usuario,String,String> {
    @Override
    protected String doInBackground(Usuario... params) {
        UsuarioREST urest = new UsuarioREST();
        String result = "";
        try{

            for(Usuario usuario : params){
                result = urest.inserir(usuario);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
