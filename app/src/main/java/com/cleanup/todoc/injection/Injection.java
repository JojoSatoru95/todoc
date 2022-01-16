package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static ProjectRepository provideProjectDataSource(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectRepository(database.projectDao());
    }

    public static TaskRepository provideTaskDataSource(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new TaskRepository(database.taskDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ProjectRepository projectDataSource = provideProjectDataSource(context);
        TaskRepository taskDataSource = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataSource, taskDataSource, executor);
    }
}
