package com.vedruna.perezvazquez01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.perezvazquez01.R;
import com.vedruna.perezvazquez01.model.Personaje;

import java.util.List;
/**
 * La clase {@code PersonajeAdapter} es un adaptador personalizado utilizado para poblar un ListView o GridView con
 * objetos {@link Personaje}. Extiende {@link BaseAdapter} y proporciona métodos para manejar la vinculación de datos
 * entre el conjunto de datos y los componentes de la interfaz de usuario.
 *
 * El adaptador utiliza el patrón ViewHolder para el reciclaje eficiente de vistas y la optimización del rendimiento.
 *
 */
public class PersonajeAdapter extends BaseAdapter {

    /**
     * La lista de objetos {@link Personaje} que se mostrarán en el adaptador.
     */
    private List<Personaje> personajes;

    /**
     * El contexto en el que se utiliza el adaptador.
     */
    private Context context;

    /**
     * Construye un nuevo {@code PersonajeAdapter} con el contexto y la lista de personajes especificados.
     *
     * @param context    El contexto en el que se utiliza el adaptador.
     * @param personajes La lista de objetos {@link Personaje} que se mostrarán.
     */
    public PersonajeAdapter(Context context, List<Personaje> personajes) {
        this.context = context;
        this.personajes = personajes;
    }

    /**
     * Devuelve el número total de elementos en el conjunto de datos.
     *
     * @return El número total de elementos.
     */
    @Override
    public int getCount() {
        return personajes.size();
    }

    /**
     * Devuelve el elemento en la posición especificada
     *
     * @param position La posición del elemento en el conjunto de datos.
     * @return El elemento en la posición especificada.
     */
    @Override
    public Object getItem(int position) {
        return personajes.get(position);
    }

    /**
     * Devuelve el identificador único para el elemento en la posición especificada.
     *
     * @param position La posición del elemento en el conjunto de datos.
     * @return El identificador único del elemento en la posición especificada.
     */
    @Override
    public long getItemId(int position) {
        return personajes.get(position).getId();
    }

    /**
     * Devuelve una vista que muestra la información en la posición especificada en el conjunto de datos.
     *
     * @param position    La posición del elemento en el conjunto de datos.
     * @param convertView La vista reciclada para mostrar el contenido.
     * @param parent      La vista principal que contiene el conjunto de datos.
     * @return La vista que muestra la información en la posición especificada.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.personajes_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.descripcionText = convertView.findViewById(R.id.descripcionText);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el personaje actual
        Personaje personaje = (Personaje) getItem(position);

        // Asignar los valores del personaje a las vistas
        viewHolder.nameText.setText(personaje.getNombre());
        viewHolder.descripcionText.setText(personaje.getDescripcion());
        Picasso.get().load(personaje.getUrl()).into(viewHolder.imageView);

        return convertView;
    }

    /**
     * Clase estática que actúa como contenedor para las vistas de cada elemento en la interfaz de usuario.
     */
    static class ViewHolder {

        /**
         * TextView para mostrar el nombre del personaje.
         */
        TextView nameText;

        /**
         * TextView para mostrar la descripción del personaje.
         */
        TextView descripcionText;

        /**
         * ImageView para mostrar la imagen del personaje.
         */
        ImageView imageView;
    }
}
