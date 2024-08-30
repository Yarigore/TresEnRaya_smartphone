package com.example.tresenraya;

import java.util.Arrays;
import java.util.Random;

public class Partida {

    public final int DIFICULTAD;
    public int jugador;
    public int[] casillas;
    public final int[][] COMBINACIONES = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public Partida(int dificultad) {
        this.DIFICULTAD = dificultad;
        jugador = 1;
        casillas = new int[9];
        Arrays.fill(casillas, 0);
    }

    public boolean compruebaCasilla(int casilla){
        if (casillas[casilla] != 0){
            return false;
        }
        else {
            casillas[casilla] = jugador;
        }
        return true;
    }

    public int dosEnRaya(int jugador){

        int casilla =- 1;
        int cuantasLleva = 0;

        for (int i = 0; i < COMBINACIONES.length; i++) {
            for (int pos : COMBINACIONES[i]) {

                if (casillas[pos] == jugador) cuantasLleva++;
                if (casillas[pos] == 0) casilla = pos;
            }
            if (cuantasLleva == 2 && casilla != 1) return casilla;
            casilla =- 1;
            cuantasLleva = 0;
        }
        return -1;
    }

    public int ia(){

        int casilla;
        casilla = dosEnRaya(2);

        if (casilla != -1) return casilla;

        if (DIFICULTAD > 0){
            casilla = dosEnRaya(1);
            if (casilla != -1) return casilla;
        }
        if (DIFICULTAD == 2){
            if (casillas[0] == 0) return 0;
            if (casillas[2] == 0) return 2;
            if (casillas[6] == 0) return 6;
            if (casillas[8] == 0) return 8;
        }

        Random casillaRandom = new Random();
        casilla = casillaRandom.nextInt(9);
        return casilla;
    }

    public int turno(){

        boolean empate = true;
        boolean ultMovimiento = true;

        for (int i = 0; i < COMBINACIONES.length; i++){
            for (int pos : COMBINACIONES[i]){
                //System.out.println("Valor en posición " + pos + casillas[pos]);
                if (casillas[pos] != jugador) ultMovimiento = false;
                if (casillas[pos] == 0) empate = false;
            }
            if (ultMovimiento) return jugador;
            ultMovimiento = true;
        }

        if (empate){
            return 3;
        }

        jugador++;
        if (jugador > 2) jugador = 1;

        return 0;
    }
}
