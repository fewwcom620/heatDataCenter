package com.wistron.vdu300.heatdataagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataTransferController {

    @GetMapping("/monitor")
    public Map monitor() {
        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("version", "0.1.1");

        return results;
    }

}
