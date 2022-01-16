package com.cleanup.todoc.database.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Get all tasks
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    // Create a new task
    @Insert
    long insertTask(Task task);

    // Delete
    @Delete
    int deleteTask(Task task);
}
