package com.wistron.vdu300.heatdataagent.controller;

import com.fazecast.jSerialComm.SerialPort;
import net.codecrete.usb.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        for (UsbDevice device : Usb.getDevices()) {
            System.out.println("device.getSerialNumber() = " + device.getSerialNumber());
            System.out.println("device.getManufacturer() = " + device.getManufacturer());
            System.out.println("device.getProduct() = " + device.getProduct());
            System.out.println("device = " + device.toString());
        }

        List<byte[]> devices = Usb.getDevices().stream().map(UsbDevice::getDeviceDescriptor).toList();

        results.put("description", devices);

        return results;
    }

    @GetMapping("/device")
    public Map deviceTest() {
        HashMap<String, Object> results = new HashMap<String, Object>();

        Usb.setOnDeviceConnected(usbDevice -> System.out.println("connected = " + usbDevice));
        Usb.setOnDeviceDisconnected(usbDevice -> System.out.println("disconnected = " + usbDevice));

        return results;
    }

    @GetMapping("/message/{message}")
    public Map controlDevice(@PathVariable String message) {
        HashMap<String, Object> results = new HashMap<String, Object>();

        int vendorId = 0x0957;
        int productId = 0x0507;

        var testDevice = Usb.findDevice(vendorId, productId).orElseThrow();
        testDevice.open();
        var transfer = new UsbControlTransfer(UsbRequestType.VENDOR, UsbRecipient.DEVICE, 0x01, 1234, 1);
        testDevice.controlTransferOut(transfer, null);
        testDevice.close();

        return results;
    }
}
