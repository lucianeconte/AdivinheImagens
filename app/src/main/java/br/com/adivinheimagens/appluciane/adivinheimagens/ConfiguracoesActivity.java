package br.com.adivinheimagens.appluciane.adivinheimagens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfiguracoesActivity extends AppCompatActivity {

    private static final String ARQUIVO_CONFIG = "ArqConfig";
    private EditText nomeUsuario;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        nomeUsuario = (EditText) findViewById(R.id.edit_usuario);
        botaoSalvar = (Button) findViewById(R.id.bt_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_CONFIG,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nomeUsuario", nomeUsuario.getText().toString());
                editor.commit();

                Intent intent = new Intent(ConfiguracoesActivity.this, InicialActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Recuperar ome do usuário
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_CONFIG,0);
        nomeUsuario.setText(sharedPreferences.getString("nomeUsuario", ""));

    }
}
