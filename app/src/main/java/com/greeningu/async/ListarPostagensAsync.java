package com.greeningu.async;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.greeningu.HomeActivity;
import com.greeningu.bean.PostagemSimplificada;
import com.greeningu.wsclient.PostagemREST;

import java.util.ArrayList;

public class ListarPostagensAsync extends AsyncTask<Integer, Void, ArrayList<PostagemSimplificada>> {

    ProgressDialog dialog;
    public ArrayList<PostagemSimplificada> lista;

    public ListarPostagensAsync(ProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected ArrayList<PostagemSimplificada> doInBackground(Integer... params) {

        PostagemREST prest = new PostagemREST();
        ArrayList<PostagemSimplificada> list = prest.listarNovasPostagens(params[0]);
        return list;

    }

    @Override
    protected void onPostExecute(ArrayList<PostagemSimplificada> postagemSimplificadas) {
        super.onPostExecute(postagemSimplificadas);
        lista = postagemSimplificadas;
        dialog.cancel();
    }
}

