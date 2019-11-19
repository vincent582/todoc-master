package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.model.Project;
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
