package com.arielo.pronostico_deportivo.servicios;

import com.arielo.pronostico_deportivo.excepciones.FaltanDatosExcepcion;
import com.arielo.pronostico_deportivo.modelos.Jugador;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.arielo.pronostico_deportivo.modelos.PronosticoCsv;
import com.arielo.pronostico_deportivo.modelos.Partido;
import com.arielo.pronostico_deportivo.modelos.ResultadoCsv;
import com.arielo.pronostico_deportivo.modelos.Ronda;

import com.opencsv.bean.CsvToBeanBuilder;
import java.util.Map;
import java.util.stream.Collectors;

public class LectorArchivoCsv {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Ronda> leerResultadoCsv(String rutaResultado) {

        // creo la lista de Ronda a retornar
        List<Ronda> rondas = new ArrayList<>();

        // creo una lista donde guardaremos las lecturas del .csv
        List<ResultadoCsv> lineasResultadoCsv = null;

        // creamos una variable Partido donde guardaremos cada ResultadoCsv leído
        Partido partido;

        try {
            // guardo las lecturas del .csv en la lista creada
            lineasResultadoCsv = new CsvToBeanBuilder(new FileReader(rutaResultado, StandardCharsets.UTF_8))
                    .withSkipLines(1)
                    .withType(ResultadoCsv.class)
                    .build().parse();

            // mapeo la lista del csv para agruparlos según el número de ronda, key=Ronda, Value=List<ResultadosCSV>
            Map<String, List<ResultadoCsv>> partidosPorRonda = lineasResultadoCsv.stream()
                    .collect(Collectors.groupingBy(ResultadoCsv::getRonda));

            // recorro el Map y guardo los datos en Ronda
            for (Map.Entry<String, List<ResultadoCsv>> entry : partidosPorRonda.entrySet()) {
                Ronda ronda = new Ronda();
                ronda.setRonda(entry.getKey());
                // al guardar la lista de partidos, transformo PartidoCsv a Partido
                ronda.setPartidos(entry.getValue().stream().map(resultadoCsv -> CsvConvert.convertirResultadoCsvAPartido(resultadoCsv))
                        .collect(Collectors.toList()));
                rondas.add(ronda);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuantra el archivo especificado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }

        return rondas;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Jugador> leerPronosticoCsv(String rutaPronostico) {

        // creo la lista de jugadores a retornar
        List<Jugador> jugadores = new ArrayList<>();

        // creo una lista donde guardo las lecturas del .csv
        List<PronosticoCsv> lineasPronosticoCsv = null;

        try {
            // guardo las lecturas del .csv en la lista creada
            lineasPronosticoCsv = new CsvToBeanBuilder(new FileReader(rutaPronostico, StandardCharsets.UTF_8))
                    .withSkipLines(1)
                    .withType(PronosticoCsv.class)
                    .build().parse();

            // mapeo la lista del csv para agruparlos según el nombre del jugador
            Map<String, List<PronosticoCsv>> pronosticoPorJugador = lineasPronosticoCsv.stream()
                    .collect(Collectors.groupingBy(PronosticoCsv::getNombreJugador));

            // recorro el Map y guardo los datos en Jugador
            for (Map.Entry<String, List<PronosticoCsv>> entry : pronosticoPorJugador.entrySet()) {
                Jugador jugador = new Jugador();
                jugador.setNombre(entry.getKey());
                // al guardar la lista de partidos, transformo PartidoCsv a Partido
                jugador.setPronosticos(entry.getValue().stream()
                        // filtro solo los datos que estan completos
                        .filter(pronsoticoCsv -> {
                            try {
                                verificarNumeroDeCampos(pronsoticoCsv);
                                return true;
                            } catch (FaltanDatosExcepcion e) {
                                e.getMessage();
                                return false;
                            }
                        })
                        // convierto PronosticoCsv a Pronostico
                        .map(pronosticoCsv -> CsvConvert.convertirPronosticoCsvAPartido(pronosticoCsv))
                        .collect(Collectors.toList()));
                jugadores.add(jugador);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuantra el archivo especificado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }

        return jugadores;
    }

    private void verificarNumeroDeCampos(PronosticoCsv pronosticoCsv) throws FaltanDatosExcepcion {

        String mensaje = null;

        if (pronosticoCsv.getNombreJugador() == null) {
            mensaje = "Faltan el nombre del jugador";
        }
        if (pronosticoCsv.getNombreEquipo1() == null) {
            mensaje = "Faltan el nombre del primer equipo";
        }
        if (pronosticoCsv.getNombreEquipo2() == null) {
            mensaje = "Faltan el nombre del segundo equipo";
        }
        if (pronosticoCsv.getGanaEquipo1() == null && pronosticoCsv.getGanaEquipo2() == null
                && pronosticoCsv.getEmpate() == null) {
            mensaje = "Falta asignar 'local', 'empate' o 'visitante'";
        }

        if (mensaje != null) {
            throw new FaltanDatosExcepcion(mensaje);
        }
    }

}
