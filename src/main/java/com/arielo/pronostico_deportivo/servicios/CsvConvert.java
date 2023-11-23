package com.arielo.pronostico_deportivo.servicios;

import com.arielo.pronostico_deportivo.enumeraciones.ResultadoEnum;
import com.arielo.pronostico_deportivo.modelos.Equipo;
import com.arielo.pronostico_deportivo.modelos.Partido;
import com.arielo.pronostico_deportivo.modelos.ResultadoCsv;
import com.arielo.pronostico_deportivo.modelos.PronosticoCsv;

public class CsvConvert {

    public static Partido convertirResultadoCsvAPartido(ResultadoCsv partidoCsv) {

        Partido partido = new Partido();
        partido.setEquipo1(new Equipo(partidoCsv.getNombreEquipo1()));
        partido.setEquipo2(new Equipo(partidoCsv.getNombreEquipo2()));
        partido.setResultado(obtenerResultadoPartido(partidoCsv));

        return partido;

    }

    private static ResultadoEnum obtenerResultadoPartido(ResultadoCsv partidoCsv) {

        if (partidoCsv.getGolesEquipo1() > partidoCsv.getGolesEquipo2()) {
            return ResultadoEnum.LOCAL;
        } else if (partidoCsv.getGolesEquipo1() < partidoCsv.getGolesEquipo2()) {
            return ResultadoEnum.VISITANTE;
        }
        return ResultadoEnum.EMPATE;
    }

    public static Partido convertirPronosticoCsvAPartido(PronosticoCsv pronosticoCsv) {

        Partido partido = new Partido();
        partido.setEquipo1(new Equipo(pronosticoCsv.getNombreEquipo1()));
        partido.setEquipo2(new Equipo(pronosticoCsv.getNombreEquipo2()));
        partido.setResultado(obtenerResultadoPronostico(pronosticoCsv));

        return partido;
    }

    private static ResultadoEnum obtenerResultadoPronostico(PronosticoCsv pronosticoCsv) {

        if (pronosticoCsv.getGanaEquipo1().equalsIgnoreCase("x")) {
            return ResultadoEnum.LOCAL;
        }

        if (pronosticoCsv.getGanaEquipo2().equalsIgnoreCase("x")) {
            return ResultadoEnum.VISITANTE;
        }

        return ResultadoEnum.EMPATE;
    }
}
