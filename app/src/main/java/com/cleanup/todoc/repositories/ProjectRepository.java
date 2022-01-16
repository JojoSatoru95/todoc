package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // Get all projects
    public LiveData<List<Project>> getProjects() { return this.projectDao.getProjects(); }






}
