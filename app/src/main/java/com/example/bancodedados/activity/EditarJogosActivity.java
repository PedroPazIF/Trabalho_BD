package com.example.bancodedados.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bancodedados.R;
import com.example.bancodedados.data.Jogo;
import com.example.bancodedados.data.JogoDAO;

public class EditarJogosActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNome;
    private EditText edtNota;
    private EditText edtSituacao;
    private Button btnProcessar;
    private Button btnCancelar;
    private Jogo jogo;
    private JogoDAO jogoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jogo);

        edtNome = findViewById(R.id.edt_nome);
        edtNota = findViewById(R.id.edt_nota);
        edtSituacao = findViewById(R.id.edt_situacao);
        btnProcessar = findViewById(R.id.btnProcessar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnProcessar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        jogoDAO = JogoDAO.getInstance(this);

        jogo = (Jogo) getIntent().getSerializableExtra("produto");

        if (jogo != null){
            edtNome.setText(jogo.getNome());
            edtNota.setText(String.valueOf(jogo.getNota()));
            edtNome.setText(jogo.getSituacao());
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnProcessar){
            String nome = edtNome.getText().toString();
            double valor = Double.parseDouble(edtNota.getText().toString());
            String msg;

            if (jogo == null) {
                Jogo jogo = new Jogo(nome, valor);
                jogoDAO.save(jogo);
                msg = "Produto gravado com ID = " + jogo.getId();

            } else {
                jogo.setNome(nome);
                jogo.setValor(valor);
                jogoDAO.update(jogo);
                msg = "Produto atualizado com ID = " + jogo.getId();
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
        else if (view.getId() == R.id.btnCancelar){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}