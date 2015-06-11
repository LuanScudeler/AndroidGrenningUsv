package com.greeningu.wsclient;

import android.util.Log;

import com.google.gson.Gson;
import com.greeningu.bean.Comentario;
import com.greeningu.bean.Usuario;
import com.greeningu.util.Constants;

/**
 * Created by Luan on 10/06/2015.
 */
public class ComentarioREST {

    private static final String URL_WS = "/comentario";

    private static final String ERRO = "Erro: ";

    public String inserirComentario(Comentario comentario)throws Exception{

        Gson gson = new Gson();

        String comentarioJSON = gson.toJson(comentario);
        String[] resposta = new WebServiceCliente().post(Constants.SERVER_URL + URL_WS + "/inserirComentario", comentarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            Log.e(ERRO, resposta[1]);
            return resposta[1];
        }
    }
}
