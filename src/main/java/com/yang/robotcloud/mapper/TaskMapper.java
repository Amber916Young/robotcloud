package com.yang.robotcloud.mapper;

import com.yang.robotcloud.entity.RobotInfo;
import com.yang.robotcloud.entity.Tasks;

import java.util.HashMap;
import java.util.List;

public interface TaskMapper {

    void insertTasks(HashMap<String, Object> param);

    void insertTasksRobots(HashMap<String, Object> param);

    List<Tasks> queryTasks();

    List<HashMap> queryTasksByTask(HashMap<String, Object> param);

    List<RobotInfo> queryTasksOfRobots(int id);

    HashMap queryTask_robots(HashMap<String, Object> param);

    void updateTasksRobots(HashMap<String, Object> param);

    void updateTasksShow(HashMap<String, Object> param);

    List<HashMap> queryTask_max(HashMap<String, Object> param);
}
