package com.mycompany.gestion.peliculas.cine;

import java.text.Normalizer;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class MetodosAuxiliares {

    private Set<String> generosUnicos = new HashSet<>();

    // MENÚ PRINCIPAL
    public static void menuPrincipal() {
        imprimirSeparadores();
        System.out.println("Gestión de Películas en un Cine");
        System.out.println("1. Agregar una película");
        System.out.println("2. Listar todas las películas");
        System.out.println("3. Buscar una película por título (o parte del nombre)");
        System.out.println("4. Filtrar una película por género");
        System.out.println("5. Mostrar todas las películas disponibles");
        System.out.println("6. Mostrar estadísticas");
        System.out.println("7. Salir");
        imprimirSeparadores();
    }

    // SUBMENÚ GÉNEROS DISPONIBLES
    public void opcionesMenuBuscarPorGenero() {
        System.out.println("¿Cómo querés buscar por género?");
        System.out.println("1. Escribir el nombre del género (o parte del nombre)");
        System.out.println("2. Elegir de la lista de géneros disponibles");
    }

    public void menuGenerosDisponibles(Scanner sc, Cine cine) {
        System.out.println("\n--- Géneros disponibles ---");
        int opcionSeleccionadaMenu = leerIntEntre(sc, "Elija una opción: ", 1, 2);

        switch (opcionSeleccionadaMenu) {
            case 1 -> {
                String generoInput = solicitarEntradaNextLine(sc, "Ingrese un género: ", "género");

                if (cine.getPeliculas().size() > 0) {
                    boolean seEncontraronPeliculas = false;

                    mostrarTituloDatosFormaTabulada(); // Muestro columnas y títulos
                    for (Pelicula peli : cine.getPeliculas()) {
                        // Comparo si el género ingresado está contenido en la película
                        if (quitarTildes(peli.getGenero().toLowerCase()).contains(quitarTildes(generoInput).toLowerCase())) {
                            mostrarDatosDeFormaTabulada(peli);
                            seEncontraronPeliculas = true;
                        }
                    }

                    if (!seEncontraronPeliculas) {
                        imprimirSeparadores();
                        System.out.println("No se encontraron películas con el género ingresado.");
                    }
                } else {
                    advertenciaListaVacia();
                }
            }
            case 2 -> {

                if (cine.getPeliculas().size() > 0) {

                    for (Pelicula peli : cine.getPeliculas()) {
                        generosUnicos.add(peli.getGenero());
                    }

                    List<String> generos = new ArrayList<>(generosUnicos);
                    mostrarGenerosDeLaLista(generos);

                    String generoElegido = elegirGeneroDeLaLista(generos, sc);

                    mostrarTituloDatosFormaTabulada(); // Muestro columnas y títulos
                    for (Pelicula peli : cine.getPeliculas()) {
                        if (peli.getGenero().toLowerCase().equals(generoElegido.toLowerCase())) {
                            mostrarDatosDeFormaTabulada(peli);
                        }
                    }
                } else {
                    advertenciaListaVacia();
                }
            }
            default ->
                mensajeOpcionInvalida();
        }
    }

    public static void mostrarGenerosDeLaLista(List<String> generos) {
        for (int i = 0; i < generos.size(); i++) {
            System.out.println((i + 1) + ". " + generos.get(i));
        }
    }

    public static void mostrarPeliculasDeLaLista(List<Pelicula> peliculas) {
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
        }
    }

    public static String elegirGeneroDeLaLista(List<String> generos, Scanner sc) {
        int opcionElegida = leerIntEntre(sc, "Elija una opción: ", 1, generos.size());

        for (int i = 0; i < generos.size(); i++) {
            if (i == opcionElegida - 1) {
                return generos.get(i);
            }
        }
        return null;
    }

    // VALIDACIONES PARA LAS ENTRADAS INT DEL MENÚ Y SUBMENÚ
    public static int leerIntEntre(Scanner sc, String mensaje, int min, int max) {
        int numero = -1;

        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            imprimirSeparadores();

            try {
                numero = Integer.parseInt(entrada);

                if (numero < min || numero > max) {
                    System.out.println("Por favor ingresá un número entre " + min + " y " + max + ".");
                } else {
                    break; // número válido, salgo del bucle
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresá un número entero válido.");
            }
            imprimirSeparadores();
        }
        return numero;
    }

    public static String capitalizarTitulo(String frase) {
        return frase.substring(0, 1).toUpperCase() + frase.substring(1).toLowerCase();
    }

    public static String capitalizarMultiplesPalabras(String frase) {
        String[] palabras = frase.trim().split("\\s+");

        StringBuilder capitalizado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.length() > 0) {

                capitalizado.append(palabra.substring(0, 1).toUpperCase())
                        .append(palabra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalizado.toString().trim();
    }

    public static String quitarTildes(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return patron.matcher(normalizado).replaceAll("");
    }

    // VALIDACIÓN PARA ENTRADA DE TIPO BOOLEAN
    public static boolean leerBooleano(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("s") || input.equals("sí") || input.equals("si")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Entrada inválida. Por favor ingresá 's' para sí o 'n' para no.");
            }
        }
    }

    // VALIDACIONES ENTRADAS TEXTO E INT Y CREO UN NUEVA PELÍCULA
    public static Pelicula crearPeliculaDesdeInput(Scanner sc, List<Pelicula> peliculas) {
        String titulo = solicitarEntradaNextLine(sc, "Ingrese un título: ", "título");

        if (yaExisteLaPeliculaEnLaLista(peliculas, titulo)) {
            System.out.println("Ya existe una película con el título ingresado. No se puede duplicar.");
            return null;
        }

        String director = solicitarEntradaNextLine(sc, "Ingrese un director: ", "director");
        String genero = solicitarEntradaNextLine(sc, "Ingrese un género: ", "género");
        int anioEstreno = leerIntEntre(sc, "Ingrese un año de estreno: ", 1900, Year.now().getValue());
        boolean disponible = leerBooleano(sc, "Ingrese la disponibilidad: ");

        Pelicula peli = new Pelicula();
        peli.setTitulo(titulo);
        peli.setDirector(director);
        peli.setGenero(genero);
        peli.setAnioEstreno(anioEstreno);
        peli.setDisponible(disponible);

        return peli;
    }

    public static boolean yaExisteLaPeliculaEnLaLista(List<Pelicula> peliculas, String titulo) {
        if (titulo == null || peliculas == null) {
            return false;
        }

        String tituloNormalizado = quitarTildes(titulo.toLowerCase());

        for (Pelicula peli : peliculas) {
            if (quitarTildes(peli.getTitulo().toLowerCase()).equals(tituloNormalizado)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarTituloDatosFormaTabulada() {
        System.out.printf("%-30s %-25s %-15s %-15s %-10s%n", "Título", "Director", "Género", "Año de estreno", "Disponible");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static void mostrarDatosDeFormaTabulada(Pelicula peli) {
        System.out.printf("%-30s %-25s %-15s %-15d %-10s%n", peli.getTitulo(),
                peli.getDirector(), peli.getGenero(), peli.getAnioEstreno(),
                peli.getDisponible() ? "Sí" : "No");
    }

    public static String solicitarEntradaNextLine(Scanner sc, String mensaje, String tipo) {
        String input;

        while (true) {
            System.out.print(mensaje);
            input = sc.nextLine().trim();

            if (input.isBlank()) {
                System.out.println("El " + tipo + " no puede estar vacío.");
            } else if (!input.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$")) {
                System.out.println("El " + tipo + " solo debe contener letras y espacios.");
            } else {
                break;
            }
        }

        return capitalizarMultiplesPalabras(input);
    }

    // IMPRIMIR SEPARADORES
    public static void imprimirSeparadores() {
        System.out.println("-----------------------");
    }

    // MENSAJE OPCIÓN INVÁLIDA
    public static void mensajeOpcionInvalida() {
        System.out.println("Opción inválida.");
    }

    public static void advertenciaListaVacia() {
        imprimirSeparadores();
        System.out.println("La lista se encuentra vacía.");
    }
}