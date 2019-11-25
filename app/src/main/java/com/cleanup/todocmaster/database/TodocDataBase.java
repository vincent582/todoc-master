package com.cleanup.todocmaster.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todocmaster.database.dao.ProjectDAO;
import com.cleanup.todocmaster.database.dao.TaskDAO;
import com.cleanup.todocmaster.model.Project;
import com.cleanup.todocmaster.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDataBase extends RoomDatabase {

    //SINGLETON
    private static volatile TodocDataBase INSTANCE;

    //DAO
    public abstract TaskDAO taskDAO();
    public abstract ProjectDAO projectDAO();

    //INSTANCE
    public static TodocDataBase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (TodocDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TodocDataBase.class, "TodocDatabase.db")
                            .addCallback(populateDatabase(context))
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback populateDatabase(Context context) {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).projectDAO().insertAll(Project.getAllProjects());
                    }
                });
            }
        };
    }
}
