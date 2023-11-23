package com.arielo.pronostico_deportivo.modelos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Jugador {

    private String dni;
    private String nombre;
    private List<Partido> pronosticos;
    
    public int puntajePorJugadorPorRonda(Ronda ronda) {
        int suma = 0;
        for (Partido partido : ronda.getPartidos()) {
            for (Partido pronostico : this.getPronosticos()) {
                
                if (partido.equals(pronostico)) {
                    suma += partido.puntoPorPartido(pronostico);
                }
            }
        }
        return suma;
    }
    
    public int puntajeTotalPorJugador(List<Ronda> rondas) {
        int suma = 0;
        for (Ronda ronda : rondas) {
            suma += puntajePorJugadorPorRonda(ronda);
        }
        return suma;
    }
    
}
