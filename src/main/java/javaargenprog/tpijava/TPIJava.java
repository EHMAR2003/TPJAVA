/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package javaargenprog.tpijava;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaargenprog.tpijava.Resource.Conexion;
import javaargenprog.tpijava.Resource.IncorporaPronosticos;
import javaargenprog.tpijava.Resource.RepoEquipos;
/**
 *
 * @author ehmar
 */
public class TPIJava {
           
    public static void main(String[] args) throws SQLException, IOException {  
      try{
        
        RepoEquipos repoEquipos = new RepoEquipos();
        IncorporaPronosticos incorporaPronosticos = new IncorporaPronosticos();
        
        //Lee archivo configuracion y lo vuelca a un map
        Path rutaConfig = Paths.get(args[2]);
        Map<String, String> config = new HashMap<>();
        List<String> lineasConfig = null;
        lineasConfig = Files.readAllLines(rutaConfig);
        for(String lineaConfig: lineasConfig){
            String[] vectorConfig = lineaConfig.split(":");
            config.put(vectorConfig[0], vectorConfig[1]);            
        }
        
        
        //System.out.println("empiezo a leer archivo resultados.csv");
        //Path rutaResultados = Paths.get("C:/Users/ehmar/Documents/ARGENTINA PROGRAMA/JAVA/TPIJava/src/test/java/resultados.csv");
        Path rutaResultados = Paths.get(args[0]);
        List<String> lineasResultados = null;
        List<Partido> partidos = new ArrayList<Partido>();
        Map<Integer, Ronda> rondas = new HashMap<>();
        try{
            lineasResultados = Files.readAllLines(rutaResultados);           
        }
        catch(IOException e){
            System.out.println("Eror en lectura archivo: " + e.getMessage());
            System.exit(1);
        }
        boolean primerLinea = true;
        for(String lineaResultado: lineasResultados){
            if (primerLinea){  
                primerLinea = false;
            }
            else {
                String[] vectorResultados = lineaResultado.split(";");
                if (vectorResultados.length != 7){
                    System.out.println("El archivo resultados.csv No tiene el nro.correcto de campos");
                    System.exit(1);
                }
                //System.out.println(lineaResultado);
                Equipo equipo1 = new Equipo(vectorResultados[0]);
                Equipo equipo2 = new Equipo(vectorResultados[4]);
                Partido partido = new Partido(equipo1, equipo2);
                
                
                if (controlNumerico(vectorResultados[2])) {
                    partido.setGolesEquipo1(Integer.parseInt(vectorResultados[2]));
                } else {
                    System.out.println("El dato correspondiente a goles del equipo1 No es un valor permitido");
                    System.exit(1);
                }
                
                if (controlNumerico(vectorResultados[3])) {
                    partido.setGolesEquipo2(Integer.parseInt(vectorResultados[3]));
                } else {
                    System.out.println("El dato correspondiente a goles del equipo2 No es un valor permitido");
                    System.exit(1);
                }
                
                int numRonda = Integer.parseInt(vectorResultados[6]);
                Ronda ronda = null;
                if (! rondas.containsKey(numRonda)){
                    ronda = new Ronda((vectorResultados[6]));
                    rondas.put(numRonda, ronda);
                } else {
                    ronda = rondas.get(numRonda);
                }
                ronda.addPartido(partido);
                partido.setRonda(ronda);
                partidos.add(partido);                
            }
        }
        // lee archivo pronostico.csv
        //Path rutaPronostico = Paths.get("C:/Users/ehmar/Documents/ARGENTINA PROGRAMA/JAVA/TPIJava/src/test/java/pronostico.csv");
        Path rutaPronostico = Paths.get(args[1]);
        List<String> lineasPronostico = null;
        List<Pronostico> pronosticos = new ArrayList<Pronostico>();
        Map<String, Jugador> jugadores = new HashMap<>();
        try{
            lineasPronostico = Files.readAllLines(rutaPronostico);           
        }
        catch(IOException e){
            System.out.println("Eror en lectura archivo: " + e.getMessage());
            System.exit(1);
        }
        primerLinea = true;
        //int puntos = 0;
        for(String lineaPronostico: lineasPronostico){
            if (primerLinea){  
                primerLinea = false;
            }
            else {
                String[] vectorPronostico = lineaPronostico.split(";");
                Equipo equipo1 = new Equipo(vectorPronostico[0]);
                
                if(!repoEquipos.controlEquipo(equipo1.getNombre())){
                   System.err.println("Error. El equipo " + equipo1.getNombre() + "no existe en la base");                   
                }
                
		Equipo equipo2 = new Equipo(vectorPronostico[4]);
                
                if(!repoEquipos.controlEquipo(equipo2.getNombre())){
                   System.err.println("Error. El equipo " + equipo2.getNombre() + "no existe en la base");
                }
                
                // Agrega pronóstico a la base de datos
                incorporaPronosticos.agregarPronostico(vectorPronostico[0], vectorPronostico[1], vectorPronostico[2], vectorPronostico[3], vectorPronostico[4], vectorPronostico[5]);
                
                Partido partido = null;

		for (Partido partidovect : partidos) {
                    if (partidovect.getEquipo1().getNombre().equals(equipo1.getNombre())
			&& partidovect.getEquipo2().getNombre().equals(equipo2.getNombre())){						
			partido = partidovect;						
                    }
		}
                Equipo equipo = null;
                EnumResultado resultado;
                resultado = null;
                if(vectorPronostico[1].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.GANADOR;                    
                }
                if(vectorPronostico[2].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.EMPATE;                    
                }
                if(vectorPronostico[3].equals("X")){
                    equipo = equipo1;
                    resultado = EnumResultado.PERDEDOR;
                }
                Pronostico pronostico = new Pronostico(partido, equipo, resultado);      
                //sumar puntos del jugador
                String nomJugador = vectorPronostico[5];
                Jugador jugador = null;
                if (jugadores.containsKey(nomJugador)) {
                    jugador = jugadores.get(nomJugador);
                    
                } else {
                    jugador = new Jugador(nomJugador);
                    jugadores.put(nomJugador, jugador);
                }   
                jugador.addPronostico(pronostico);
                //jugador.puntosPorPronostico(pronostico.puntos());
            }                                
        }        
        // calcular bonus por cada ronda
        for (String nomjugador : jugadores.keySet()){
            for (Integer nroRonda : rondas.keySet()){
                Ronda ronda = rondas.get(nroRonda);
                Jugador jugador = jugadores.get(nomjugador);
                List<Pronostico> apuestas = jugador.getPronosticos();
                boolean rondaCumplida = ronda.acertoTodos(apuestas);
                if (rondaCumplida) {
                    //jugador.incrementarPuntosPorBonus(2);                   
                    jugador.incrementarPuntosPorBonus(Integer.parseInt(config.get("puntosBonus")));
                }
            }
        }
        // Muestra los puntos calculados
        for (String jugador : jugadores.keySet()){
            System.out.println("El puntaje obtenido por el jugador: " + jugador + " es de " + jugadores.get(jugador).puntosTotales());
        }
     }finally{
        Conexion.cerrarConexion();  
     }   
    }
    
    // Método para controlar que la cantidad de goles sea un nro válido.
    private static boolean controlNumerico(String nro){           
        boolean esNumero = (nro != null) && (nro.matches("[0-9]{1,2}"));
        return esNumero;        
    }
    
    
}
