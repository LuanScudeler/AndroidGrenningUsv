package com.greeningu.webservice;

import com.google.gson.Gson;
import com.greeningu.bean.Usuario;

/**
 * Created by Jadson on 03/05/2015.
 */
public class UsuarioREST {
    private static final String URL_WS = "http://192.168.0.103:8080/GreeningU/usuario";

    //TODO demais métodos para manipular usuário
    public String inserir(Usuario usuario)throws Exception{
        Gson gson = new Gson();

        String usuarioJSON = gson.toJson(usuario);
        String[] resposta = new WebServiceCliente().post(URL_WS + "/inserir", usuarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }


}

