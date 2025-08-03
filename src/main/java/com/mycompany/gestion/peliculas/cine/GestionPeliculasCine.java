package com.mycompany.gestion.peliculas.cine;

import java.util.Scanner;

public class GestionPeliculasCine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetodosAuxiliares metodosAux = new MetodosAuxiliares();
        Cine cine = new Cine();

        int opcionMenu;
        boolean salir = false;

        while (!salir) {
            metodosAux.menuPrincipal();
            opcionMenu = metodosAux.leerIntEntre(sc, "Elegí una opción: ", 1, 7);
            
            switch(opcionMenu) {
                case 1 -> cine.agregarPelicula(sc);
                case 2 -> cine.listarPeliculas(sc);
                case 3 -> cine.buscarPorTitulo(sc);
                case 4 -> {
                    metodosAux.opcionesMenuBuscarPorGenero();
                    metodosAux.menuGenerosDisponibles(sc, cine);
                }
                case 5 -> cine.mostrarPeliculasDisponibles();
                case 6 -> cine.mostrarEstadisticas();
                case 7 -> salir = true;
                default -> {
                    metodosAux.mensajeOpcionInvalida();
                }
            }
        }
    }
}
