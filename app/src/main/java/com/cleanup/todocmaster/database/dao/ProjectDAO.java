package com.cleanup.todocmaster.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todocmaster.model.Project;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM Project WHERE id = :projectId")
    LiveData<Project> getProject(long projectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertProject(Project project);

    @Insert
    void insertAll(Project... projects);
}
