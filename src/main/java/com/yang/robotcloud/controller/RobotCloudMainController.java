package com.yang.robotcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.yang.robotcloud.entity.RobotInfo;
import com.yang.robotcloud.service.RobotCloudMainService;
import com.yang.robotcloud.utils.AJAXReturn;
import com.yang.robotcloud.utils.RobotImg;
import com.yang.robotcloud.utils.TimeParse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/robot")
public class RobotCloudMainController {
    TimeParse timeParse = new TimeParse();
    @Autowired
    RobotCloudMainService robotCloudMainService;


    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public Object updaterobot(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        robotCloudMainService.updateRobot(param);
        return AJAXReturn.success("location update");
    }




    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/query/active/id/{id}",
            produces = "application/json; charset=utf-8")
    public Object queryAllActiveRobots(@PathVariable("id") int id) {
        //0 inactive 1 running 2 offline 3 broken
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("state", '1');
        List<RobotInfo> list = robotCloudMainService.queryAllActiveRobots(param);
        return AJAXReturn.success("query Successful", list);
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/query/all", produces = "application/json; charset=utf-8")
    public Object queryAllRobots(@RequestBody String jsonData) {
        HashMap<String, Object> param = new HashMap<>();
        if(jsonData==null){
            param =null;
        }else {
            param= JSONObject.parseObject(jsonData, HashMap.class);
        }
        List<RobotInfo> list = robotCloudMainService.queryAllRobots(param);
        if (list.size() > 0) {
            return AJAXReturn.success("query Successful", list);
        }
        return AJAXReturn.warn("no data");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/register", produces = "application/json; charset=utf-8")
    public Object registerobot(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        String Time = timeParse.convertDate2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        param.put("registerTime", Time);
        param.put("state", "0");
        if(!param.containsKey("longitude")){
            param.put("longitude", 0);
        }
        if(!param.containsKey("latitude")){
            param.put("latitude", 0);
        }
        String robot_name = param.get("robot_name").toString();
        String img = RobotImg.generateImg(robot_name, robot_name);
        param.put("robot_img", "data:image/png;base64," + img);
        robotCloudMainService.insertRobot(param);
        return AJAXReturn.success("Register Successful");
    }


    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    public Object loginRobots(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        RobotInfo robotInfo = robotCloudMainService.queryRobot(param);
        if (robotInfo != null) {
            if(robotInfo.getState()==3){
                return AJAXReturn.error("The robot is broken! cannot login");
            }
            String Time = timeParse.convertDate2String(new Date(), "yyyy-MM-dd HH:mm:ss");
            param.put("activeTime", Time);
            param.put("state", "1");
            param.put("id", robotInfo.getId());
            robotCloudMainService.updateRobot(param);
            robotInfo.setActiveTime(Time);
            robotInfo.setState(1);
            return AJAXReturn.success("Login Successful", robotInfo);
        }
        return AJAXReturn.error("Login Fail! please check password or robot name");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/broken/id/{id}", produces = "application/json; charset=utf-8")
    public Object lrokenRobots(@PathVariable("id") int id) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("state","3");
        robotCloudMainService.updateRobot(param);
        return AJAXReturn.success("the robot is broken!!!!");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/logout/id/{id}", produces = "application/json; charset=utf-8")
    public Object loginRobots(@PathVariable("id") int id) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("state", "2");
        robotCloudMainService.updateRobot(param);
        return AJAXReturn.success("Logout Successful");
    }


    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/detail/id/{id}", produces = "application/json; charset=utf-8")
    public Object detailRobots(@PathVariable("id") int id) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        robotCloudMainService.queryRobot(param);
        return AJAXReturn.success("Successful");
    }


}