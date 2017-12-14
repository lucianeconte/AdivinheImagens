package br.com.adivinheimagens.appluciane.adivinheimagens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InicialActivity extends AppCompatActivity {

    private Toolbar toolbarPrincipal;
    private Button botoaIniciar;
    private static final String ARQUIVO_CONFIG = "ArqConfig";
    private TextView boasVindas;
    private RadioGroup radioGroup;
    private RadioButton imagemEscolhida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        botoaIniciar = (Button) findViewById(R.id.bt_iniciar);
        boasVindas = (TextView) findViewById(R.id.texto_boasVindas);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);

        //Configura toolbar
        toolbarPrincipal = (Toolbar) findViewById(R.id.toolbar_principal);
        //toolbarPrincipal.setLogo( R.drawable.logo_adivinhe );
        setSupportActionBar( toolbarPrincipal );

        botoaIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();

                Intent intent = new Intent(InicialActivity.this, MainActivity.class);
                if (idRadioButtonEscolhido > 0){
                    imagemEscolhida = (RadioButton) findViewById(idRadioButtonEscolhido);
                    intent.putExtra("tipoImagem", imagemEscolhida.getText());
                }

                startActivity(intent);
                finish();
            }
        });

        //Recuperar ome do usuário
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_CONFIG,0);
        boasVindas.setText("Olá " + sharedPreferences.getString("nomeUsuario", "") + "! Vamos adivinhar as imagens?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.action_sair:
                return true;
            case R.id.action_configuracoes:
                Intent intent = new Intent(InicialActivity.this, ConfiguracoesActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
