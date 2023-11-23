package com.arielo.pronostico_deportivo;

import com.arielo.pronostico_deportivo.modelos.Jugador;
import java.util.List;
import com.arielo.pronostico_deportivo.modelos.Ronda;
import com.arielo.pronostico_deportivo.servicios.LectorArchivoCsv;

public class App {

    public static void main(String[] args) {

        String rutaAbsoluta = System.getProperty("user.dir");
        LectorArchivoCsv lectorArchivo = new LectorArchivoCsv();

        List<Ronda> rondas = lectorArchivo.leerResultadoCsv(rutaAbsoluta + "\\src\\main\\resources\\resultados.csv");
        List<Jugador> jugadores = lectorArchivo.leerPronosticoCsv(rutaAbsoluta + "\\src\\main\\resources\\pronosticos.csv");

        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.puntajeTotalPorJugador(rondas));
        }
    }
}
