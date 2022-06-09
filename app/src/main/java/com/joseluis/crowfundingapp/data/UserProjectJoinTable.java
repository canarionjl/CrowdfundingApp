package com.joseluis.crowfundingapp.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(tableName = "user_project_join",
    primaryKeys = { "userId", "projectId" }, //una única combinación de id de usuario con id de proyecto
    foreignKeys = {
        @ForeignKey
        (entity = UserItem.class, parentColumns = "id", childColumns = "userId"),
        @ForeignKey
        (entity = ProjectItem.class, parentColumns = "id", childColumns = "projectId")
    })

public class UserProjectJoinTable {
    public final int userId;
    public final int projectId;

    public UserProjectJoinTable(int userId, int projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }
}