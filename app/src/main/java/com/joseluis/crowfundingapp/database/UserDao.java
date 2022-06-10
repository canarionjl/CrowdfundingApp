package com.joseluis.crowfundingapp.database;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joseluis.crowfundingapp.data.UserItem;

import java.util.List;



@Dao
public interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserItem User);

    @Query("SELECT * FROM users")
    List<UserItem> loadUsers();

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    UserItem loadUser(int id);
}
