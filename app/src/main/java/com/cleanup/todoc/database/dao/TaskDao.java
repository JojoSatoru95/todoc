package com.cleanup.todoc.database.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Create a new task
    @Insert
    long insertTask(Task task);

    // Get all tasks
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    // Get task from project
    @Query("SELECT * FROM task_table WHERE project_id = :projectId")
    LiveData<List<Task>> getTasks(long projectId);

    // Sorted Tasks
    @Query("SELECT * FROM task_table ORDER BY name ASC ")
    LiveData<List<Task>> getAllTasksOrderByNameAsc();

    @Query("SELECT * FROM task_table ORDER BY name DESC ")
    LiveData<List<Task>> getAllTasksOrderByNameDesc();

    @Query("SELECT * FROM task_table ORDER BY creationTimestamp ASC ")
    LiveData<List<Task>> getAllTasksOrderByTimeAsc();

    @Query("SELECT * FROM task_table ORDER BY creationTimestamp DESC ")
    LiveData<List<Task>> getAllTasksOrderByTimeDesc();


    // Delete
    @Delete
    int deleteTask(Task task);
}
