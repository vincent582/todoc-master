package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    //REPOSITORIES
    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;
    private final Executor mExecutor;

    //Data
    @Nullable
    private LiveData<Project> currentProject;

    //CONSTRUCTOR
    public TaskViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        mProjectDataRepository = projectDataRepository;
        mTaskDataRepository = taskDataRepository;
        mExecutor = executor;
    }

    public void init(long projectId){
        if (currentProject != null){
            return;
        }
        currentProject = mProjectDataRepository.getProject(projectId);
    }

    public LiveData<Project> getProject(long projectId){
        return this.currentProject;
    }

    public LiveData<List<Task>> getTasks(long projectId){
        return this.mTaskDataRepository.getTasks(projectId);
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.mTaskDataRepository.getAllTasks();
    }

    public void insertTask(Task task){
        mExecutor.execute(()-> {
            this.mTaskDataRepository.insertTask(task);
        });
    }

    public void updateTask(Task task){
        mExecutor.execute(()->{
            this.mTaskDataRepository.updateTask(task);
        });
    }

    public void deleteTask(long taskId){
        mExecutor.execute(()->{
            this.mTaskDataRepository.deleteTask(taskId);
        });
    }
}
