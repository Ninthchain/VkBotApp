package com.dev.bot.database.dao;


import com.dev.bot.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public interface Dao<EntityType> {
    void persist(EntityType entityObject);

    void merge(EntityType entityObject);

    <IdType extends Number> EntityType getEntityById(IdType id);

    <ColumnValueType> List<EntityType> getEntitiesByColumnValue(String columnName, ColumnValueType value);

    List<EntityType> getAll();
}
