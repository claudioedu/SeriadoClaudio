package com.example.seriado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seriado.dao.SeriadoDAO;
import com.example.seriado.modelo.Seriado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText  etNome;
    private EditText etGenero;
    private EditText etStatus;
    private EditText etComentario;
    private EditText etNota;
    private EditText etDataLancamento;
    private Seriado seriado = new Seriado();
    private SeriadoDAO dao = new SeriadoDAO();
    private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etGenero = findViewById(R.id.etGenero);
        etStatus = findViewById(R.id.etStatus);
        etComentario = findViewById(R.id.etComentario);
        etNota = findViewById(R.id.etNota);
        etDataLancamento = findViewById(R.id.etDataLancamento);

        Intent intent = getIntent();

        if (intent != null) {
            Seriado seriadoSelecionado
                    = (Seriado) intent.getSerializableExtra("seriadoSelecionado");
            if (seriadoSelecionado != null) {
                popularFormulario(seriadoSelecionado);
            }
        }
    }

    private void popularFormulario(Seriado seriadoSelecionado) {
        etNome.setText(seriadoSelecionado.getNome());
        etGenero.setText(seriadoSelecionado.getGenero());
        etStatus.setText(seriadoSelecionado.getStatus());
        etComentario.setText(seriadoSelecionado.getComentario());
        etNota.setText(String.valueOf(seriadoSelecionado.getNota()));
        etDataLancamento.setText(sf.format(seriadoSelecionado.getDataLancamento()));
        seriado.setId(seriadoSelecionado.getId());
    }

    private void popularModelo() {

        seriado.setNome(etNome.getText().toString());
        seriado.setGenero(etGenero.getText().toString());
        seriado.setStatus(etStatus.getText().toString());
        seriado.setComentario(etComentario.getText().toString());
        seriado.setNota(Integer.parseInt(etNota.getText().toString()));

        Date data = null;
        try {
            data = sf.parse(etDataLancamento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        seriado.setDataLancamento(data);
    }

    public void salvar(View view) {
        popularModelo();
        if (seriado.getId() == null) {
            dao.incluir(seriado);
            Toast.makeText(getBaseContext(),
                    "Inclusão realizada com sucesso!" ,Toast.LENGTH_LONG).show();
            limparCampos();
        } else {
            dao.alterar(seriado);
            Toast.makeText(getBaseContext(),
                    "Alteração realizada com sucesso!" , Toast.LENGTH_LONG).show();
            limparCampos();
        }
    }

    private void limparCampos() {
        etNome.setText("");
        etGenero.setText("");
        etStatus.setText("");
        etComentario.setText("");
        etNota.setText("");
        etDataLancamento.setText("");
        seriado = new Seriado();
    }

    public void limpar(View view) {
        limparCampos();
    }

    public void listar(View view) {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }
}
