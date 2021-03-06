package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;


    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }


    // Get All Tasks
    public LiveData<List<Task>> getAllTasks() {
        return taskDao.getAllTasks();
    }

    // Get Sorted Tasks

    public LiveData<List<Task>> getAllTasksOrderByNameAsc() { return taskDao.getAllTasksOrderByNameAsc(); }

    public LiveData<List<Task>> getAllTasksOrderByNameDesc() { return taskDao.getAllTasksOrderByNameDesc(); }

    public LiveData<List<Task>> getAllTasksOrderByTimeAsc() { return taskDao.getAllTasksOrderByTimeAsc(); }

    public LiveData<List<Task>> getAllTasksOrderByTimeDesc() { return taskDao.getAllTasksOrderByTimeDesc(); }

    // Create
    public void createTask(Task task) { taskDao.insertTask(task); }

    // Delete
    public void deleteTask(Task task) { taskDao.deleteTask(task); }







}
