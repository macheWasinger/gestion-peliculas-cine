package com.mycompany.gestion.peliculas.cine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cine {

    private List<Pelicula> peliculas = new ArrayList<>();

    // AGREGAR UNA PELÍCULA
    public void agregarPelicula(Scanner sc) {
        System.out.println("\n--- Agregar una película ---");
        Pelicula nueva = MetodosAuxiliares.crearPeliculaDesdeInput(sc, peliculas);

        if (nueva != null) {
            peliculas.add(nueva);
            MetodosAuxiliares.imprimirSeparadores();
            System.out.println("Película agregada con éxito.");
        }
    }

    // LISTAR TODAS LAS PELÍCULAS
    public void listarPeliculas(Scanner sc) {
        if (peliculas.size() > 0) {
            System.out.println("\n--- Listar todas las películas ---");
            MetodosAuxiliares.mostrarTituloDatosFormaTabulada();

            for (Pelicula peli : peliculas) {
                MetodosAuxiliares.mostrarDatosDeFormaTabulada(peli);
            }

            MetodosAuxiliares.imprimirSeparadores();
            // OPCIÓN PARA MARCAR COMO NO DISPONIBLE
            boolean marcarComoNoDisponible = MetodosAuxiliares.leerBooleano(sc, "¿Querés marcar alguna como no disponible? ");

            if (marcarComoNoDisponible) {
                MetodosAuxiliares.mostrarPeliculasDeLaLista(peliculas);
                int opcionElegida = MetodosAuxiliares.leerIntEntre(sc, "Elija una opción: ", 1, peliculas.size());
                
                for(int i = 0; i < peliculas.size(); i++) {
                    if (i == (opcionElegida - 1)) {
                        peliculas.get(i).setDisponible(false);
                        System.out.println("Disponibilidad cambiada con éxito.");
                    }
                }
            }
        } else {
            MetodosAuxiliares.advertenciaListaVacia();
        }

    }

    // BUSCAR PELÍCULA POR TÍTULO
    public void buscarPorTitulo(Scanner sc) {
        if (peliculas.size() > 0) {
            System.out.println("\n--- Buscar películas por título ---");
            String titulo = MetodosAuxiliares.solicitarEntradaNextLine(sc, "Ingrese un título: ", "título");

            MetodosAuxiliares.mostrarTituloDatosFormaTabulada();
            for (Pelicula peli : peliculas) {
                if (MetodosAuxiliares.quitarTildes(peli.getTitulo().toLowerCase()).contains(MetodosAuxiliares.quitarTildes(titulo.toLowerCase()))) {
                    MetodosAuxiliares.mostrarDatosDeFormaTabulada(peli);
                }
            }
        } else {
            MetodosAuxiliares.advertenciaListaVacia();
        }
    }

    // MOSTRAR PELÍCULAS DISPONIBLES
    public void mostrarPeliculasDisponibles() {
        if (peliculas.size() > 0) {
            System.out.println("\n--- Mostrar todas las películas disponibles ---");

            MetodosAuxiliares.mostrarTituloDatosFormaTabulada();
            for (Pelicula peli : peliculas) {
                if (peli.getDisponible() == true) {
                    MetodosAuxiliares.mostrarDatosDeFormaTabulada(peli);
                }
            }
        } else {
            MetodosAuxiliares.advertenciaListaVacia();
        }

    }
    
    // MOSTRAR ESTADISTICAS
    public void mostrarEstadisticas() {
        Map<String, Integer> conteoPorGenero = new HashMap<>();
        int disponibles = 0;
        int noDisponibles = 0;
        
        if (peliculas.size() > 0) {
            for (Pelicula peli : peliculas) {
                // Contar por género
                String genero = peli.getGenero().toLowerCase();
                conteoPorGenero.put(genero, conteoPorGenero.getOrDefault(genero, 0) + 1);
                
                // Contar disponibilidad
                if (peli.getDisponible()) {
                    disponibles++;
                } else {
                    noDisponibles++;
                }
            }
            
            System.out.println("\n--- Estadísticas ---");
            System.out.println("Películas por género: ");
            for (String genero : conteoPorGenero.keySet()) {
                System.out.println("- " + genero + ": " + conteoPorGenero.get(genero));
            }
            
            System.out.println("\nDisponibilidad: ");
            System.out.println("Disponibles: " + disponibles);
            System.out.println("No disponibles: " + noDisponibles);
                
        } else {
            MetodosAuxiliares.advertenciaListaVacia();
        }
    }

    // GETTER Y SETTER
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
