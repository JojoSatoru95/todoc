package com.cleanup.todoc.databaseTest;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // FOR DATA
    private TodocDatabase database;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void initDb() throws Exception {
            this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                    TodocDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }

    @After
    public void closeDb() throws Exception {
            database.close();
    }



    // DATA SET FOR TEST
    private static long PROJECT_ID = 4L;

    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Cupidon", 0xFFA3CED2);



    @Test
    public void insertAndGetProject() throws InterruptedException {

        // BEFORE : Adding a new project
        this.database.projectDao().insertProject(PROJECT_DEMO);


        // TEST
        Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);

    }


    private static Task NEW_TASK_SHOPPING = new Task(4L, "Faire les courses", new Date().getTime());

    private static Task NEW_TASK_CHILDRENS = new Task(4L, "Chercher les enfants", new Date().getTime());

    private static Task NEW_TASK_STUDY = new Task(4L, "Etudier", new Date().getTime());



    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetItems() throws InterruptedException {


        // BEFORE : Adding demo projects & demo tasks
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK_SHOPPING);
        this.database.taskDao().insertTask(NEW_TASK_CHILDRENS);
        this.database.taskDao().insertTask(NEW_TASK_STUDY);


        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(tasks.size() == 3);
    }



    @Test
    public void insertAndDeleteItem() throws InterruptedException {


        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.


        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK_SHOPPING);

        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID)).get(0);
        this.database.taskDao().deleteTask(taskAdded);


        //TEST


        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(tasks.isEmpty());


    }

}