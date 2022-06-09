package com.joseluis.crowfundingapp.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;


@Database(entities = {UserItem.class, ProjectItem.class, UserProjectJoinTable.class}, version = 1)

public abstract class CrowdfundingDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProjectDao projectDao();
    public abstract UserProjectJoinTableDao userProjectJoinTableDao();

}
