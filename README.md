# 🎬 Gestión de Películas en un Cine

Este proyecto es una solución a una **prueba técnica** que simula un sistema de administración de películas en cartelera para un cine. Permite agregar películas, filtrarlas por género, buscar por título y controlar su disponibilidad.

---

## 🚀 Funcionalidades implementadas

✔️ Agregar una película con:
- Título  
- Director  
- Género (Ej: Acción, Comedia, Drama, etc.)  
- Año de estreno  
- Estado de disponibilidad (en cartelera o no)

✔️ Listar todas las películas en forma tabulada  
✔️ Buscar películas por título (coincidencia parcial y case-insensitive)  
✔️ Filtrar por género  
✔️ Mostrar solo las películas actualmente en cartelera (`disponible == true`)  
✔️ Validaciones completas al agregar películas  
✔️ Evita películas duplicadas por título  

---

## 🛠️ Tecnologías usadas

- **Lenguaje:** Java 17  
- **IDE:** NetBeans  
- **Paradigma:** Programación Orientada a Objetos  
- **Versión de JDK:** 17  
- **Dependencias externas:** Ninguna

---

## 📁 Estructura del proyecto

El sistema está organizado en las siguientes clases:

- `Principal.java` → Menú principal, control de flujo y entrada de usuario  
- `Pelicula.java` → Modelo con atributos y getters/setters  
- `Cine.java` → Lógica de negocio (gestión de la lista de películas)  
- `MetodosAuxiliares.java` → Validaciones, formatos, menús, submenús y separadores

---

## ▶️ Cómo ejecutar el proyecto

1. Cloná el repositorio:

```bash
git clone https://github.com/tu-usuario/gestion-peliculas-cine.git

## 👤 Autor

**Marcelo Wasinger**

---

Gracias por visitar este repositorio.  
¡Cualquier sugerencia o feedback es más que bienvenido!