package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Task;
import java.util.List;

public class TaskDataRepository {

    private TaskDAO mTaskDAO;

    public TaskDataRepository(TaskDAO taskDAO) { mTaskDAO = taskDAO; }

    public LiveData<List<Task>> getTasks(long projectId){
        return this.mTaskDAO.getTasks(projectId);
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.mTaskDAO.getAllTasks();
    }

    public void insertTask(Task task){
        this.mTaskDAO.insertTask(task);
    }

    public void updateTask(Task task){
        this.mTaskDAO.updateTask(task);
    }

    public void deleteTask(long taskId){
        this.mTaskDAO.deleteTask(taskId);
    }
}
