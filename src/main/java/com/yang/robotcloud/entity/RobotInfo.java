package com.yang.robotcloud.entity;


import lombok.Data;

import java.util.List;

@Data
public class RobotInfo {
    private long id;
    private String robot_name;
    private String password;
    private String robot_img;
    private Double longitude;//经度
    private Double latitude;
    private String location_name;
    private int state;
    private String activeTime;
    private String registerTime;

}
