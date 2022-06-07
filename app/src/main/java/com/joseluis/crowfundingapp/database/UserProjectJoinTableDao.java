package com.joseluis.crowfundingapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joseluis.crowfundingapp.data.UserProjectJoinTable;

import java.util.List;

@Dao
public interface UserProjectJoinTableDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserProjectJoin(UserProjectJoinTable userProjectJoinTable);

    @Update
    void updateUserProjectJoin(UserProjectJoinTable userProjectJoinTable);

    @Delete
    void deleteUserProjectJoin(UserProjectJoinTable userProjectJoinTable);

    @Query("SELECT * FROM user_project_join")
    List<UserProjectJoinTable> loadAllUserProjectJoinsTable();

    @Query("SELECT * FROM user_project_join WHERE userId = :id")
    List<UserProjectJoinTable> loadUserProjectsWithUserId(int id);
}
