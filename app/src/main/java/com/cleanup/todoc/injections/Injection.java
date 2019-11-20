package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideTaskDataSource(Context context){
        TodocDataBase dataBase = TodocDataBase.getInstance(context);
        return new TaskDataRepository(dataBase.taskDAO());
    }

    public static ProjectDataRepository provideProjectDataSource(Context context){
        TodocDataBase dataBase = TodocDataBase.getInstance(context);
        return new ProjectDataRepository(dataBase.projectDAO());
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context){
        ProjectDataRepository projectDataRepository = provideProjectDataSource(context);
        TaskDataRepository taskDataRepository = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(taskDataRepository,projectDataRepository,executor);
    }
}
