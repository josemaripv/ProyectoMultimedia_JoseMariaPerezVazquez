package com.vedruna.perezvazquez01.model;

import java.io.Serializable;
/**
 * La clase {@code Personaje} representa a un personaje en la aplicación.
 * Implementa la interfaz {@link Serializable} para permitir la serialización del objeto.
 */
public class Personaje implements Serializable {

    /**
     * El identificador único del personaje.
     */
    private int id;

    /**
     * El nombre del personaje.
     */
    private String nombre;

    /**
     * La edad del personaje.
     */
    private int edad;

    /**
     * La aldea a la que pertenece el personaje.
     */
    private String aldea;

    /**
     * La descripción del personaje.
     */
    private String descripcion;

    /**
     * La URL de la imagen asociada al personaje.
     */
    private String url;

    /**
     * Crea un nuevo objeto {@code Personaje} con valores predeterminados.
     */
    public Personaje() {
    }

    /**
     * Crea un nuevo objeto {@code Personaje} con la información proporcionada.
     *
     * @param id          El identificador único del personaje.
     * @param nombre      El nombre del personaje.
     * @param edad        La edad del personaje.
     * @param aldea       La aldea a la que pertenece el personaje.
     * @param descripcion La descripción del personaje.
     * @param url         La URL de la imagen asociada al personaje.
     */
    public Personaje(int id, String nombre, int edad, String aldea, String descripcion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.aldea = aldea;
        this.descripcion = descripcion;
        this.url = url;
    }

    /**
     * Obtiene el identificador único del personaje.
     *
     * @return El identificador único del personaje.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del personaje.
     *
     * @param id El nuevo identificador único del personaje.
     */
    public void setId(int id) {
        this.id = id;
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
     * Obtiene la edad del personaje.
     *
     * @return La edad del personaje.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del personaje.
     *
     * @param edad La nueva edad del personaje.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la aldea a la que pertenece el personaje.
     *
     * @return La aldea del personaje.
     */
    public String getAldea() {
        return aldea;
    }

    /**
     * Establece la aldea a la que pertenece el personaje.
     *
     * @param aldea La nueva aldea del personaje.
     */
    public void setAldea(String aldea) {
        this.aldea = aldea;
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
     * Obtiene la URL de la imagen asociada al personaje.
     *
     * @return La URL de la imagen del personaje.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de la imagen asociada al personaje.
     *
     * @param url La nueva URL de la imagen del personaje.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code Personaje}.
     *
     * @return Una cadena que representa el objeto {@code Personaje}.
     */
    @Override
    public String toString() {
        return "Id: " + getId() + " Nombre: " + getNombre() + " Edad: " + getEdad() + " Aldea: " + getAldea() + " Descripcion: " + getDescripcion() + " URL: " + getUrl();
    }
}
