package com.yang.robotcloud.service;


import com.yang.robotcloud.entity.RobotInfo;
import com.yang.robotcloud.entity.Tasks;
import com.yang.robotcloud.mapper.RobotCloudMainMapper;
import com.yang.robotcloud.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;


    public void  insertTasks(HashMap<String, Object> param) {
        taskMapper.insertTasks(param);
    }

    public void insertTasksRobots(HashMap<String, Object> param) {
        taskMapper.insertTasksRobots(param);
    }

    public List<Tasks> queryTasks() {
        return  taskMapper.queryTasks();
    }

    public List<HashMap> queryTasksByTask(HashMap<String, Object> param) {
        return  taskMapper.queryTasksByTask(param);
    }

    public List<RobotInfo> queryTasksOfRobots(int id) {
        return  taskMapper.queryTasksOfRobots(id);
    }

    public HashMap queryTask_robots(HashMap<String, Object> param) {
        return taskMapper.queryTask_robots(param);
    }

    public void updateTasksRobots(HashMap<String, Object> param) {
        taskMapper.updateTasksRobots(param);
    }

    public void updateTasksShow(HashMap<String, Object> param) {
        taskMapper.updateTasksShow(param);
    }

    public List<HashMap> queryTask_max(HashMap<String, Object> param) {
        return taskMapper.queryTask_max(param);
    }
}
