package com.example.tresenraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {

    private int jugadores;
    private int[] casillas;
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // INICIAMOS EL ARRAY CASILLAS QUE IDENTIFICA CADA CASILLA Y LA ALMACENA EN EL ARRAY
        casillas = new int[9];
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;
    }

    public void Jugar(View view){

        ImageView imagen;

        for (int casilla : casillas){
            imagen = findViewById(casilla);
            imagen.setImageResource(R.drawable.casilla);
        }

        jugadores = 1;

        if (view.getId()==R.id.dosjug){
            jugadores=2;
        }

        RadioGroup configDificultad = findViewById(R.id.configD);
        int id = configDificultad.getCheckedRadioButtonId();
        int dificultad = 0;
        if (id == R.id.normal){
            dificultad = 1;
        } else if (id == R.id.imposible) {
            dificultad = 2;
        }

        partida = new Partida(dificultad);

        findViewById(R.id.unjug).setEnabled(false);
        findViewById(R.id.configD).setAlpha(0);
        findViewById(R.id.dosjug).setEnabled(false);

    }

    public void toque(View view){

        if (partida == null){
            return;
        }

        int casilla = 0;
        for (int i = 0; i < casillas.length; i++){
            if (casillas[i] == view.getId()){
                casilla = i;
                break;
            }
        }

        if (!partida.compruebaCasilla(casilla)) return;

        marcar(casilla);
        int resultado = partida.turno();
        if (jugadores == 2) partida.turno();
        if (resultado > 0){
            termina(resultado);
            return;
        }
        if (jugadores == 1){
            casilla = partida.ia();
            while (!partida.compruebaCasilla(casilla)){
                casilla = partida.ia();
            }
        }
        marcar(casilla);
        resultado = partida.turno();
        if (resultado > 0) termina(resultado);
    }

    private void termina(int resultado){
        String mensaje = "";

        if (resultado == 1) mensaje = getString(R.string.circulos_ganan);
        else if (resultado == 2) mensaje = getString(R.string.cruces_ganan);
        else if (resultado == 3) mensaje = getString(R.string.empate);

        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        partida = null;

        findViewById(R.id.unjug).setEnabled(true);
        findViewById(R.id.configD).setAlpha(1);
        findViewById(R.id.dosjug).setEnabled(true);
    }

    private void marcar(int casilla){

        ImageView imagen;

        imagen = findViewById(casillas[casilla]);

        if (partida.jugador == 1){
            imagen.setImageResource(R.drawable.circulo);
        }
        else{
            imagen.setImageResource(R.drawable.cruz);
        }
    }
}