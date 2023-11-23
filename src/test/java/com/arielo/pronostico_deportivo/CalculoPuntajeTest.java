package com.arielo.pronostico_deportivo;

import com.arielo.pronostico_deportivo.enumeraciones.ResultadoEnum;
import com.arielo.pronostico_deportivo.modelos.Equipo;
import com.arielo.pronostico_deportivo.modelos.Jugador;
import com.arielo.pronostico_deportivo.modelos.Partido;
import com.arielo.pronostico_deportivo.modelos.Ronda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculoPuntajeTest {

    
    @Test
    public void comprobarPuntajeDeUnaPersonaEnRondasConsecutivas() {
        
        // creo tres Partido
        Partido partido1 = new Partido();
        partido1.setEquipo1(new Equipo("Equipo1"));
        partido1.setEquipo2(new Equipo("Equipo2"));
        partido1.setResultado(ResultadoEnum.LOCAL);
        
        Partido partido2 = new Partido();
        partido2.setEquipo1(new Equipo("Equipo3"));
        partido2.setEquipo2(new Equipo("Equipo4"));
        partido2.setResultado(ResultadoEnum.EMPATE);
        
        Partido partido3 = new Partido();
        partido3.setEquipo1(new Equipo("Equipo5"));
        partido3.setEquipo2(new Equipo("Equipo6"));
        partido3.setResultado(ResultadoEnum.VISITANTE);
        
        // creo dos listas de partidos para agregar a Ronda
        List<Partido> partidosRonda1 = new ArrayList<>();
        partidosRonda1.add(partido1);
        partidosRonda1.add(partido2);
        
        List<Partido> partidosRonda2 = new ArrayList<>();
        partidosRonda2.add(partido3);
        
        // creo las dos Ronda y seteo las listas
        Ronda ronda1 = new Ronda();
        ronda1.setRonda("1");
        ronda1.setPartidos(partidosRonda1);
        
        Ronda ronda2 = new Ronda();
        ronda2.setRonda("2");
        ronda2.setPartidos(partidosRonda2);
        
        // JUGADOR
        // creo tres Partido
        Partido pronostico1 = new Partido();
        pronostico1.setEquipo1(new Equipo("Equipo1"));
        pronostico1.setEquipo2(new Equipo("Equipo2"));
        pronostico1.setResultado(ResultadoEnum.VISITANTE);
        
        Partido pronostico2 = new Partido();
        pronostico2.setEquipo1(new Equipo("Equipo3"));
        pronostico2.setEquipo2(new Equipo("Equipo4"));
        pronostico2.setResultado(ResultadoEnum.EMPATE);
        
        Partido pronsotico3 = new Partido();
        pronsotico3.setEquipo1(new Equipo("Equipo5"));
        pronsotico3.setEquipo2(new Equipo("Equipo6"));
        pronsotico3.setResultado(ResultadoEnum.LOCAL);
        
        // creo el Jugador
        Jugador jugador = new Jugador();
        List<Partido> pronosticos = Arrays.asList(pronostico1, pronostico2, pronsotico3);
        jugador.setPronosticos(pronosticos);
        
        // compruebo que en la Ronda 1 haya 1 punto y en la 2 0 puntos
        Assertions.assertTrue(jugador.puntajePorJugadorPorRonda(ronda1) == 1);
        Assertions.assertTrue(jugador.puntajePorJugadorPorRonda(ronda2) == 0);
        
        // creo una lista donde guardo las Ronda y compruebo que el resultado sea 1
        List<Ronda> rondas = Arrays.asList(ronda1, ronda2);
        Assertions.assertTrue(jugador.puntajeTotalPorJugador(rondas) == 1);
    }
}
