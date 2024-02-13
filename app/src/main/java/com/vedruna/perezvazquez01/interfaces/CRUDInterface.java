package com.vedruna.perezvazquez01.interfaces;

import com.vedruna.perezvazquez01.dto.PersonajeDTO;
import com.vedruna.perezvazquez01.model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
/**
 * La interfaz {@code CRUDInterface} define las operaciones básicas para realizar el CRUD (Leer, Crear, Actualizar, Eliminar)
 * en la entidad de Personajes mediante operaciones HTTP con Retrofit.
 */
public interface CRUDInterface {

    /**
     * Obtiene la lista de todos los personajes disponibles.
     *
     * @return Una llamada que devuelve una lista de objetos {@link Personaje}.
     */
    @GET("personajes")
    Call<List<Personaje>> getAll();

    /**
     * Crea un nuevo personaje.
     *
     * @param dto La representación simplificada del personaje a crear.
     * @return Una llamada que devuelve el nuevo objeto {@link Personaje} creado.
     */
    @POST("personajes")
    Call<Personaje> create(@Body PersonajeDTO dto);

    /**
     * Actualiza la información de un personaje existente.
     *
     * @param id            El identificador del personaje a actualizar.
     * @param personajeDTO  La representación simplificada del personaje con la nueva información.
     * @return Una llamada que devuelve el objeto {@link Personaje} actualizado.
     */
    @PUT("personajes/{id}")
    Call<Personaje> actualizar(@Path("id") int id, @Body PersonajeDTO personajeDTO);

    /**
     * Elimina un personaje existente.
     *
     * @param id El identificador del personaje a eliminar.
     * @return Una llamada que no devuelve ningún dato, solo indica si la eliminación fue exitosa.
     */
    @DELETE("personajes/{id}")
    Call<Void> delete(@Path("id") int id);
}
