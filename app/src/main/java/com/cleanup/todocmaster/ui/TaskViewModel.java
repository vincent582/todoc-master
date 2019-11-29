package com.cleanup.todocmaster.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.cleanup.todocmaster.model.Project;
import com.cleanup.todocmaster.model.Task;
import com.cleanup.todocmaster.repositories.ProjectDataRepository;
import com.cleanup.todocmaster.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    //REPOSITORIES
    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;
    private final Executor mExecutor;

    //CONSTRUCTOR
    public TaskViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        mProjectDataRepository = projectDataRepository;
        mTaskDataRepository = taskDataRepository;
        mExecutor = executor;
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.mTaskDataRepository.getAllTasks();
    }

    public void insertTask(Task task){
        mExecutor.execute(()-> {
            this.mTaskDataRepository.insertTask(task);
        });
    }

    public void deleteTask(long taskId){
        mExecutor.execute(()->{
            this.mTaskDataRepository.deleteTask(taskId);
        });
    }
}
