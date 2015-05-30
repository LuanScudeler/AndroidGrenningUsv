package com.greeningu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.greeningu.R;

/**
 * Created by Luan on 16/05/2015.
 */
public class createPosts extends Fragment {
    private EditText edtDescricao;
    private Button btnSelecionarImagem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        btnSelecionarImagem = (Button)getActivity().findViewById(R.id.btnSelecionarImagem);

        return inflater.inflate(R.layout.activity_createposts, container, false);

    }

    public void btnSelecionarImagemClick(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 1234);
    }


}
