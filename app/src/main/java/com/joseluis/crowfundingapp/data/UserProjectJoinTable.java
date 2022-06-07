package com.joseluis.crowfundingapp.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(tableName = "user_project_join",
    primaryKeys = { "userId", "repoId" }, //una única combinación de id de usuario con id de proyecto
    foreignKeys = {
        @ForeignKey
        (entity = UserItem.class, parentColumns = "id", childColumns = "userId"),
        @ForeignKey
        (entity = ProjectItem.class, parentColumns = "id", childColumns = "repoId")
    })

public class UserProjectJoinTable {
    public final int userId;
    public final int repoId;

    public UserProjectJoinTable(int userId, int repoId) {
        this.userId = userId;
        this.repoId = repoId;
    }
}