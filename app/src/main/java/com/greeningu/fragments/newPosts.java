package com.greeningu.fragments;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greeningu.HomeActivity;
import com.greeningu.ListPostagensAdapter;
import com.greeningu.R;
import com.greeningu.async.ListarPostagensAsync;
import com.greeningu.bean.PostagemSimplificada;

import java.util.ArrayList;

/**
 * Created by Luan on 16/05/2015.
 */
public class newPosts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.activity_newposts, container, false);

        ArrayList<PostagemSimplificada> list = getListaDePostagens();
        ListView lv = (ListView) getActivity().findViewById(R.id.lvPostagens);
        lv.setAdapter(new ListPostagensAdapter(getActivity(),list));
        return rootView;


    }

    private ArrayList<PostagemSimplificada> getListaDePostagens() {

        final ProgressDialog dialog = new ProgressDialog(this.getActivity());
        dialog.setMessage("Buscando novas postagens...");
        ListarPostagensAsync lpa = new ListarPostagensAsync(dialog);
        lpa.execute(HomeActivity.usuario.getId());
        return lpa.lista;
    }
}
