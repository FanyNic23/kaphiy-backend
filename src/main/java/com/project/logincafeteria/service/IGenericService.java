package com.project.logincafeteria.service;

import java.util.List;

public interface IGenericService<T, ID> {
    T save(T t) throws Exception; // Guarda una nueva instancia de T en la base de datos.
                                  // Devuelve el objeto guardado (con ID asignado).

    T update(T t, ID id) throws Exception; // Recibe el objeto con los nuevos datos y el ID de la entidad que va a
                                           // modificar.
                                           // Devuelve el objeto actualizado.

    List<T> findAll() throws Exception; // Leer, Devuelve una lista completa de objetos T.

    T findById(ID id) throws Exception; // Leer, Busca en la base de datos un objeto del tipo T usando su ID.

    void delete(ID id) throws Exception; // Eliminar un objeto de tipo T por su ID.
    // 👇 Agrega aquí tu método personalizado (si aplica para todos)

}
