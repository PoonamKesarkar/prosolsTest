package com.example.prosolsmachinetest;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM tableName")
    List<EmpData> getAll();

    @Insert
    void insert(EmpData empData);

    @Delete
    void delete(EmpData empData);

    @Update
    void update(EmpData empData);
}
