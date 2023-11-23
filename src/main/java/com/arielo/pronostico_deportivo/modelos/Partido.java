package com.arielo.pronostico_deportivo.modelos;

import com.arielo.pronostico_deportivo.enumeraciones.ResultadoEnum;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Partido {

    private Equipo equipo1;
    private Equipo equipo2;
    private ResultadoEnum resultado;

    public int puntoPorPartido(Partido pronostico) {
        if (this.getResultado() == pronostico.getResultado()) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Partido partido = (Partido) obj;
        return Objects.equals(equipo1, partido.equipo1) && Objects.equals(equipo2, partido.equipo2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipo1, equipo2);
    }
    
}
