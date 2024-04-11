package com.example.demo.service;

import com.example.demo.controller.MainController;
import com.example.demo.dao.EmpDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    private static final Logger LOG = LogManager.getLogger(EmpService.class);

    @Autowired
    EmpDao empDao;
    public JSONObject getDataEmp() {
        JSONObject result = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray(empDao.getDataEmp());
            result.put("data", jsonArray);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
