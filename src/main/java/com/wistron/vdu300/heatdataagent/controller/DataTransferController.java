package com.wistron.vdu300.heatdataagent.controller;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataTransferController {

    @GetMapping("/monitor")
    public Map monitor() {
        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("version", "0.1.1");

        return results;
    }

    @GetMapping("/ports")
    public Map ports() {
        HashMap<String, Object> results = new HashMap<String, Object>();

        SerialPort[] commPorts = SerialPort.getCommPorts();

        for (SerialPort commPort : commPorts) {

        }

        List<String> names = Arrays.stream(commPorts).map(SerialPort::getDescriptivePortName).toList();

        List<String> descriptions = Arrays.stream(commPorts).map(SerialPort::toString).toList();

        results.put("portName", names);
        results.put("description", descriptions);

        return results;
    }
}
