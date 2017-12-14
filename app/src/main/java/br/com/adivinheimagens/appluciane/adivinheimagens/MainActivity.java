package br.com.adivinheimagens.appluciane.adivinheimagens;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button botaoReiniciar;
    private MediaPlayer mediaPlayer;
    private ImageView imagem1;
    private ImageView imagem2;
    private ImageView imagem3;
    private ImageView imagem4;
    private ImageView imagem5;
    private ImageView imagem6;
    private Integer numSorteado;
    private Integer pontos;
    private TextView textoPontos;
    private int tentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoReiniciar = (Button) findViewById(R.id.bt_reiniciar);
        textoPontos = (TextView) findViewById(R.id.textViewPontos);

        imagem1 = (ImageView) findViewById(R.id.img1);
        imagem2 = (ImageView) findViewById(R.id.img2);
        imagem3 = (ImageView) findViewById(R.id.img3);
        imagem4 = (ImageView) findViewById(R.id.img4);
        imagem5 = (ImageView) findViewById(R.id.img5);
        imagem6 = (ImageView) findViewById(R.id.img6);

        imagem1.setOnClickListener(this);
        imagem2.setOnClickListener(this);
        imagem3.setOnClickListener(this);
        imagem4.setOnClickListener(this);
        imagem5.setOnClickListener(this);
        imagem6.setOnClickListener(this);

        mostrarImagens();

        botaoReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InicialActivity.class);
                startActivity(intent);
                finish();
            }
        });

        pontos = 0;
        tentativas = 0;
        jogar();
    }

    public void mostrarImagens(){
        Bundle extras = getIntent().getExtras();
        if (extras.getSerializable("tipoImagem").toString().equals("Animais")) {
            Toast.makeText(MainActivity.this, "Escolhido animais", Toast.LENGTH_LONG).show();
            imagem1.setImageResource(R.drawable.cachorro);
            imagem2.setImageResource(R.drawable.pintinho);
            imagem3.setImageResource(R.drawable.boi);
            imagem4.setImageResource(R.drawable.gato);
            imagem5.setImageResource(R.drawable.porco);
            imagem6.setImageResource(R.drawable.cavalo);
        }else if (extras.getSerializable("tipoImagem").toString().equals("Comida")) {
            Toast.makeText(MainActivity.this, "Escolhido comida", Toast.LENGTH_LONG).show();
            imagem1.setImageResource(R.drawable.brocolis);
            imagem2.setImageResource(R.drawable.morango);
            imagem3.setImageResource(R.drawable.banana);
            imagem4.setImageResource(R.drawable.cenoura);
            imagem5.setImageResource(R.drawable.maca);
            imagem6.setImageResource(R.drawable.batata);
        }else{
            Toast.makeText(MainActivity.this, "Escolhido outros", Toast.LENGTH_LONG).show();
            imagem1.setImageResource(R.drawable.flor);
            imagem2.setImageResource(R.drawable.bola);
            imagem3.setImageResource(R.drawable.sapato);
            imagem4.setImageResource(R.drawable.carro);
            imagem5.setImageResource(R.drawable.relogio);
            imagem6.setImageResource(R.drawable.balao);
        }
    }

    public void jogar(){
        Random randomico = new Random();

        int numSorteio = randomico.nextInt(7);

        tentativas = tentativas + 1;

        switch (numSorteio) {
              case 1:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img1);
                  numSorteado = R.id.img1;
                  tocarSom();
                  break;
              case 2:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img2);
                  numSorteado = R.id.img2;
                  tocarSom();
                  break;
              case 3:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img3);
                  numSorteado = R.id.img3;
                  tocarSom();
                  break;
              case 4:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img4);
                  numSorteado = R.id.img4;
                  tocarSom();
                  break;
              case 5:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img5);
                  numSorteado = R.id.img5;
                  tocarSom();
                  break;
              case 0:
                  mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.img6);
                  numSorteado = R.id.img6;
                  tocarSom();
                  break;
        }
    }


    @Override
    public void onClick(View view) {

        if (numSorteado == view.getId()) {
            pontos = pontos + 1;
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.parabens);
            tocarSom();
            textoPontos.setText("Pontos = " + String.valueOf(pontos));

            try{
                new Thread().sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tentativas < 6){
                jogar();
            }else{
                if (pontos == tentativas){
                    Toast.makeText(MainActivity.this, "Parabéns!!! Você acertou todas as imagens!", Toast.LENGTH_LONG).show();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.parabens_todas);
                }else{
                    Toast.makeText(MainActivity.this, "Começe novamente para tentar acertar todas as imagens.", Toast.LENGTH_LONG).show();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tentar_novamente);
                }
                tocarSom();
            }
        }else{
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.errado);
            tocarSom();
            pontos = pontos - 1;
        }
    }

    public void tocarSom() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // liberar resursos
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
