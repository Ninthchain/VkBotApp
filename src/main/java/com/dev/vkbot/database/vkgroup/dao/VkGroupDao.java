package com.dev.vkbot.database.vkgroup.dao;

import com.dev.vkbot.database.Dao;
import com.dev.vkbot.database.vkgroup.model.VkGroup;

import java.util.List;

public class VkGroupDao implements Dao<VkGroup> {
    @Override
    public void Persist(VkGroup entityObject) {

    }

    @Override
    public void Merge(VkGroup entityObject) {

    }

    @Override
    public <IdType extends Number> VkGroup Get(IdType id) {
        return null;
    }

    @Override
    public <ColumnValueType> List<VkGroup> getByColumnValue(String columnName, ColumnValueType value) {
        return null;
    }

    @Override
    public List<VkGroup> getAll() {
        return null;
    }
}
