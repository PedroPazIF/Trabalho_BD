package com.example.bancodedados.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bancodedados.R;
import com.example.bancodedados.data.Jogo;

import java.util.ArrayList;
import java.util.List;

public class JogoAdapter extends BaseAdapter {
    private Context context;
    private List<Jogo> jogos = new ArrayList<>();

    public JogoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return jogos.size();
    }

    @Override
    public Jogo getItem(int i) {
        return jogos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return jogos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_listjogos, viewGroup, false);

        TextView txtNome = v.findViewById(R.id.txt_nome);
        TextView txtNota = v.findViewById(R.id.txt_nota);
        TextView txtSituacao = v.findViewById(R.id.txt_situacao);

        Jogo jogo = jogos.get(i);
        txtNome.setText(jogo.getNome());
        txtNota.setText(Double.toString(jogo.getNota()));
        txtSituacao.setText(jogo.getSituacao());

        return v;
    }

    public void setItems(List<Jogo> jogos) {
        this.jogos = jogos;
        notifyDataSetChanged();
    }
}
