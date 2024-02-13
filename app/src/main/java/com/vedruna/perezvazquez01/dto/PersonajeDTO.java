package com.vedruna.perezvazquez01.dto;
/**
 * La clase {@code PersonajeDTO} es un objeto de transferencia de datos que representa un personaje
 * en un formato simplificado. Contiene información como el nombre, descripción y URL de la imagen del personaje.
 *
 * Este objeto se utiliza para transportar datos entre diferentes partes de la aplicación.
 */
public class PersonajeDTO {

    /**
     * El nombre del personaje.
     */
    private String nombre;

    /**
     * La descripción del personaje.
     */
    private String descripcion;

    /**
     * La URL de la imagen del personaje.
     */
    private String url;

    /**
     * Crea un nuevo objeto {@code PersonajeDTO} con la información proporcionada.
     *
     * @param nombre      El nombre del personaje.
     * @param descripcion La descripción del personaje.
     * @param url         La URL de la imagen del personaje.
     */
    public PersonajeDTO(String nombre, String descripcion, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del personaje.
     *
     * @param nombre El nuevo nombre del personaje.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del personaje.
     *
     * @param descripcion La nueva descripción del personaje.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la URL de la imagen del personaje.
     *
     * @return La URL de la imagen del personaje.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de la imagen del personaje.
     *
     * @param url La nueva URL de la imagen del personaje.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
