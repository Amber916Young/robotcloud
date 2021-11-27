package com.yang.robotcloud.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Tasks {
    private int id;
    private String task_name;
    private String createTime;
    private String finishTime;
    private String task_detail;
    private int maxmember;
    private String robot_name;
    private Boolean checked;
    private List<RobotInfo> robotInfoList;
}
