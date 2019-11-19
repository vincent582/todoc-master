package com.cleanup.todoc.database.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.Utils.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TaskDAOTest {

    private TodocDataBase mDataBase;

    //set data for test
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID,"Project test",0xFFEADAD1);
    private static Task TASK_DEMO = new Task(1,PROJECT_ID,"task test", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.mDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void tearDown() throws Exception {
        this.mDataBase.close();
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        Project project = LiveDataTestUtil.getValue(this.mDataBase.projectDAO().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);
        List<Task> tasks = LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(tasks.size() == 1);
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID)).get(0);
        task.setName("task updated");
        this.mDataBase.taskDAO().updateTask(task);

        List<Task> taskList = LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList.size() == 1);
        assertEquals("task updated", taskList.get(0).getName());
    }

    @Test
    public void InsertAndDeleteTask() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);

        List<Task> taskList = LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList.size() == 1);

        this.mDataBase.taskDAO().deleteTask(taskList.get(0).getId());
        List<Task> taskList1 = LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList1.isEmpty());
    }
}