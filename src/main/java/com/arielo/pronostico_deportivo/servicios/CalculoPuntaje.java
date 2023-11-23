package com.arielo.pronostico_deportivo.servicios;

import com.arielo.pronostico_deportivo.excepciones.PuntajeExcepcion;
import com.arielo.pronostico_deportivo.modelos.Jugador;
import com.arielo.pronostico_deportivo.modelos.Ronda;
import java.util.List;

public class CalculoPuntaje {

    public static int puntajeTotal(List<Ronda> rondas, List<Jugador> jugadores) throws PuntajeExcepcion {
        if (puntajeTotalRecorriendoRondas(rondas, jugadores) == puntajeTotalRecorriendoJugadores(rondas, jugadores)) {
            return puntajeTotalRecorriendoRondas(rondas, jugadores);
        } else {
            throw new PuntajeExcepcion("Los puntajes no son iguales");
        }
    }
    
    private static int puntajeTotalRecorriendoRondas(List<Ronda> rondas, List<Jugador> jugadores) {
        int suma = 0;
        for (Ronda ronda : rondas) {
            suma += ronda.puntajeTotalPorRonda(jugadores);
        }
        return suma;
    }
    
    private static int puntajeTotalRecorriendoJugadores(List<Ronda> rondas, List<Jugador> jugadores) {
        int suma = 0;
        for (Jugador jugador : jugadores) {
            suma += jugador.puntajeTotalPorJugador(rondas);
        }
        return suma;
    }
}
