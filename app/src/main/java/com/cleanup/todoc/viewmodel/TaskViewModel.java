package com.cleanup.todoc.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // Repositories
    private final ProjectRepository projectDataSource;
    private final TaskRepository taskDataSource;
    private final Executor executor;


    // Data
    @Nullable
    private LiveData<List<Project>> mProjects;


    public TaskViewModel(ProjectRepository projectDataSource, TaskRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public void init() {
        if(mProjects == null)
            mProjects = projectDataSource.getProjects();
    }

    @Nullable
    public LiveData<List<Project>> getProjects() {
        return mProjects;
    }


    // For Task
    public LiveData<List<Task>> getAllTasks() {
        return taskDataSource.getAllTasks();
    }



    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskDataSource.deleteTask(task);
        });
    }





}
