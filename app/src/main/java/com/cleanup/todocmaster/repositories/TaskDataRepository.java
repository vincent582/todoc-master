package com.cleanup.todocmaster.repositories;

import android.arch.lifecycle.LiveData;
import com.cleanup.todocmaster.database.dao.TaskDAO;
import com.cleanup.todocmaster.model.Task;
import java.util.List;

public class TaskDataRepository {

    private TaskDAO mTaskDAO;

    public TaskDataRepository(TaskDAO taskDAO) { mTaskDAO = taskDAO; }

    public LiveData<List<Task>> getAllTasks(){
        return this.mTaskDAO.getAllTasks();
    }

    public void insertTask(Task task){
        this.mTaskDAO.insertTask(task);
    }

    public void deleteTask(long taskId){
        this.mTaskDAO.deleteTask(taskId);
    }
}
