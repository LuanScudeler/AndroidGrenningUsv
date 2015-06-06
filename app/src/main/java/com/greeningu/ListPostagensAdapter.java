package com.greeningu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greeningu.bean.PostagemSimplificada;

import java.util.ArrayList;

/**
 * Created by Jadson on 06/06/2015.
 */
public class ListPostagensAdapter extends BaseAdapter{
    private static ArrayList<PostagemSimplificada> list;
    private LayoutInflater inflater;

    public ListPostagensAdapter(Context fragment, ArrayList<PostagemSimplificada> list){
        this.list = list;
        inflater = LayoutInflater.from(fragment);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_newposts, null);
            holder = new ViewHolder();
            holder.txtNomeUsuario = (TextView) convertView.findViewById(R.id.txtNome);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeUsuario.setText(list.get(position).getNomeUsuario());

        return convertView;
    }

    static class ViewHolder{
        TextView txtNomeUsuario;
    }
}
