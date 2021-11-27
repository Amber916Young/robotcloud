package com.yang.robotcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.yang.robotcloud.entity.RobotInfo;
import com.yang.robotcloud.entity.Tasks;
import com.yang.robotcloud.service.TaskService;
import com.yang.robotcloud.utils.AJAXReturn;
import com.yang.robotcloud.utils.TimeParse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    TimeParse timeParse = new TimeParse();

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/add/new/task", produces = "application/json; charset=utf-8")
    public Object addNewTasks(@RequestBody String jsonData) {
        HashMap<String, Object> param =
                JSONObject.parseObject(jsonData, HashMap.class);
        String Time = timeParse.convertDate2String(new Date(),
                "yyyy-MM-dd HH:mm:ss");
        param.put("createTime", Time);
        taskService.insertTasks(param);
        return AJAXReturn.success("Add successfully");
    }
    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/hind/task", produces = "application/json; charset=utf-8")
    public Object HinddenTask(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        param.put("is_show_task", "FALSE");
        double dtid = Double.valueOf(param.get("tid").toString());
        int tid = (int)(dtid);
        int rid = Integer.parseInt(param.get("rid").toString());
        param.put("tid",tid);
        param.put("rid",rid);
        taskService.updateTasksShow(param);
        return AJAXReturn.success("It will not show again,but you still can check all the tasks!");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/finish/task", produces = "application/json; charset=utf-8")
    public Object FinishTask(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        String Time = timeParse.convertDate2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        param.put("finishTime", Time);
        param.put("checked", "TRUE");
        taskService.updateTasksRobots(param);
        return AJAXReturn.success("Will done!");
    }
    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/join/task", produces = "application/json; charset=utf-8")
    public Object JoinTask(@RequestBody String jsonData) {
        HashMap<String, Object> param = JSONObject.parseObject(jsonData, HashMap.class);
        HashMap exist = taskService.queryTask_robots(param);
        if (exist == null) {
            int maxmember = Integer.parseInt(param.get("maxmember").toString());
            List<HashMap> maxList = taskService.queryTask_max(param);
            if (maxList.size() >= maxmember) {
                return AJAXReturn.warn("The number reached the maximum--cannot join");
            } else {
                String Time = timeParse.convertDate2String(new Date(), "yyyy-MM-dd HH:mm:ss");
                param.put("joinTime", Time);
                taskService.insertTasksRobots(param);
                return AJAXReturn.success("Join successfully");
            }
        }
        return AJAXReturn.warn("You are in this task's group!!!");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/query/tasks/robot", produces = "application/json; charset=utf-8")
    public Object queryTasks() {
        List<Tasks> list = taskService.queryTasks();
        if (list.size() > 0) {
            for(int i=0;i<list.size();i++){
                int id = list.get(i).getId();
                List<RobotInfo> robotInfoList =taskService.queryTasksOfRobots(id);
                list.get(i).setRobotInfoList(robotInfoList);
            }
            return AJAXReturn.success("query Successful", list);
        }
        return AJAXReturn.warn("no data");
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "/query/task/robots/id/{id}", produces = "application/json; charset=utf-8")
    public Object queryTaskRobots(@PathVariable("id") int id) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",id);
        List<HashMap> list = taskService.queryTasksByTask(param);
        if (list.size() > 0) {
            return AJAXReturn.success("query Successful", list);
        }
        return AJAXReturn.warn("no data");
    }


}
