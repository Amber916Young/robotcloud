package com.yang.robotcloud.service;


import com.yang.robotcloud.entity.RobotInfo;
import com.yang.robotcloud.mapper.RobotCloudMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RobotCloudMainService {
    @Autowired
    RobotCloudMainMapper mapper;

    public List<RobotInfo> queryAllActiveRobots(HashMap<String, Object> param) {
        return mapper.queryAllActiveRobots(param);
    }

    public RobotInfo queryRobot(HashMap<String, Object> param) {
        return mapper.queryRobot(param);
    }

    public void updateRobot(HashMap<String, Object> param) {
        mapper.updateRobot(param);
    }

    public List<RobotInfo> queryAllRobots(HashMap<String, Object> param) {
        return mapper.queryAllRobots(param);
    }

    public void insertRobot(HashMap<String, Object> param) {
        mapper.insertRobot(param);
    }
}
