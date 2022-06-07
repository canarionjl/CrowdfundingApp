package com.joseluis.crowfundingapp.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class UserItem {

        @PrimaryKey
        public int id;

        public String username;
        public String email;
        public String password;
}
