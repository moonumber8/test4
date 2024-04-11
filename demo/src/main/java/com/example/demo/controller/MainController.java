package com.example.demo.controller;

import com.example.demo.service.EmpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {
    private static final Logger LOG = LogManager.getLogger(MainController.class);

    @Autowired
    EmpService empService;
    @GetMapping(value = "/")
    public String index(ModelMap model) {
        JSONObject ObjEmp = empService.getDataEmp();
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < ObjEmp.getJSONArray("data").length(); i++) {
            itemList.add(ObjEmp.getJSONArray("data").getJSONObject(i).getString("EmpName"));
       }
        model.addAttribute("itemList", itemList);

        return "index";
    }
}
