package com.dev.vkbot.database.dao;

import java.util.List;

public interface Dao<EntityType> {
    void Persist(EntityType entityObject);

    void Merge(EntityType entityObject);

    <IdType extends Number> EntityType Get(IdType id);

    <ColumnValueType> List<EntityType> getByColumnValue(String columnName, ColumnValueType value);

    List<EntityType> getAll();
}
