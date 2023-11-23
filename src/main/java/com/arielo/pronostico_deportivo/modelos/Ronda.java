package com.arielo.pronostico_deportivo.modelos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ronda {

    private String ronda;
    private List<Partido> partidos;

    public int puntajePorRondaPorJugador(Jugador jugador) {
        int suma = 0;
        for (Partido partido : this.getPartidos()) {
            for (Partido pronostico : jugador.getPronosticos()) {
                if (partido == pronostico) {
                    suma += partido.puntoPorPartido(pronostico);
                }
            }
        }
        return suma;
    }
    
    public int puntajeTotalPorRonda(List<Jugador> jugadores) {
        int suma = 0;
        for (Jugador jugador : jugadores) {
            suma += puntajePorRondaPorJugador(jugador);
        }
        return suma;
    }

}
