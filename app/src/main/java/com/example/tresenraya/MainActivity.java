package com.example.tresenraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {

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
    }

    private int jugadores;

    private int[] casillas;
    private Partida partida;
}