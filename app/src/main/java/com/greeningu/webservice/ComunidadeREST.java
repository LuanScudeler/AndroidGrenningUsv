package com.greeningu.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.greeningu.bean.Comunidade;
import com.greeningu.util.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jadson on 24/05/2015.
 */
public class ComunidadeREST {

    private static final String URL_WS = "/comunidade";

    public ArrayList<Comunidade> listarComunidades(){

        ArrayList<Comunidade> lista = new ArrayList<Comunidade>();

        String[]resposta = new WebServiceCliente().get(Constants.SERVER_URL + URL_WS + "/listar");
        if(resposta[0].equals("200")){

            Type listType = new TypeToken<List<Comunidade>>(){}.getType();
            lista = new Gson().fromJson(resposta[1],listType);

        }else{
            try {
                throw new Exception(resposta[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    public Integer getQuantidadeMembros(Integer id){
        Integer qtd = null;

        String[]resposta = new WebServiceCliente().get(Constants.SERVER_URL + URL_WS + "/qtdMembros/" + String.valueOf(id));
        if(resposta[0].equals("200")){

            qtd = Integer.parseInt(resposta[1]);

        }else{
            try {
                throw new Exception(resposta[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return qtd;
    }

    public Integer getPontuacao(Integer id){
        Integer pontuacao = null;

        String[]resposta = new WebServiceCliente().get(Constants.SERVER_URL + URL_WS + "/pontuacaoTotal/" + String.valueOf(id));
        if(resposta[0].equals("200")){

            pontuacao = Integer.parseInt(resposta[1]);

        }else{
            try {
                throw new Exception(resposta[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return pontuacao;
    }

    public String getNomeLider(Integer id){
        String nomeLider = null;

        String[]resposta = new WebServiceCliente().get(Constants.SERVER_URL + URL_WS + "/buscarNomeLider/" + String.valueOf(id));
        if(resposta[0].equals("200")){

            nomeLider = resposta[1];

        }else{
            try {
                throw new Exception(resposta[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return nomeLider;
    }
}
