package com.yang.robotcloud.mapper;


import com.yang.robotcloud.entity.RobotInfo;

import java.util.HashMap;
import java.util.List;

public interface RobotCloudMainMapper {


    List<RobotInfo> queryAllActiveRobots(HashMap<String, Object> param);


    RobotInfo queryRobot(HashMap<String, Object> param);

    void updateRobot(HashMap<String, Object> param);

    List<RobotInfo> queryAllRobots(HashMap<String, Object> param);

    void insertRobot(HashMap<String, Object> param);
}
