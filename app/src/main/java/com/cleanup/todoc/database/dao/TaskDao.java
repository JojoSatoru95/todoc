package com.cleanup.todoc.database.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Get task list for a projet
    @Query("SELECT * FROM Task WHERE projectId = :projectId")

    LiveData<List<Task>> getTasksFromProject(long projectId);

    // Get all tasks
    @Query("SELECT * FROM Task")

    LiveData<List<Task>> getAllTasks();


    @Query("SELECT * FROM task WHERE projectId = :projectId")

    Cursor getItemWithCursor(long projectId);

    // Create a new task
    @Insert

    long insertTask(Task task);

    // Update task
    @Update

    int updateTask(Task task);

    // Delete
    @Query("SELECT * FROM task WHERE id = :taskId")

    int deleteTask(long taskId);
}
