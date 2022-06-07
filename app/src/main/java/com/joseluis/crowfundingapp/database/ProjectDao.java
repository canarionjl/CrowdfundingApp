package com.joseluis.crowfundingapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joseluis.crowfundingapp.data.ProjectItem;

import java.util.List;

@Dao
public interface ProjectDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(ProjectItem Project);

    @Update
    void updateProject(ProjectItem Project);

    @Delete
    void deleteProject(ProjectItem Project);

    @Query("SELECT * FROM projects")
    List<ProjectItem> loadProjects();

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    ProjectItem loadProject(int id);
}
