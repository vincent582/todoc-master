package com.cleanup.todocmaster.repositories;

import android.arch.lifecycle.LiveData;
import com.cleanup.todocmaster.database.dao.ProjectDAO;
import com.cleanup.todocmaster.model.Project;
import java.util.List;

public class ProjectDataRepository {

    private ProjectDAO mProjectDAO;

    public ProjectDataRepository(ProjectDAO projectDAO) {
        mProjectDAO = projectDAO;
    }

    public LiveData<List<Project>> getAllProjects(){
        return this.mProjectDAO.getAllProjects();
    }

    public LiveData<Project> getProject(long projectId){
        return this.mProjectDAO.getProject(projectId);
    }

    public void insertProject(Project project){
        this.mProjectDAO.insertProject(project);
    }
}
