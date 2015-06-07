package com.greeningu;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greeningu.bean.Comunidade;
import com.greeningu.bean.PostagemSimplificada;
import com.greeningu.wsclient.PostagemREST;

import java.util.ArrayList;

/**
 * Created by Luan on 06/06/2015.
 */
public class ListaPostagensAdpter extends BaseAdapter {
    private Context context;
    private ArrayList<PostagemSimplificada> lista;

    public ListaPostagensAdpter(Context context, ArrayList<PostagemSimplificada> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PostagemSimplificada ps = lista.get(position);
        View layout;

        Log.d("idPostagem: ", ps.getId().toString());
        //if() para evitar recriações do ListView desnecessárias
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.mensagemsimples, null);
        }
        else{
            layout = convertView;
        }

        TextView titulo = (TextView)layout.findViewById(R.id.tvTitulo);
        titulo.setText(ps.getTitulo());

        TextView username = (TextView)layout.findViewById(R.id.tvUsername);
        username.setText(ps.getNomeUsuario());

        TextView data = (TextView)layout.findViewById(R.id.tvDate);
        data.setText(ps.getDataPostagem());

        TextView idPostagem = (TextView)layout.findViewById(R.id.tvIdPostagem);
        idPostagem.setText(ps.getId().toString());


        return layout;
    }
}
