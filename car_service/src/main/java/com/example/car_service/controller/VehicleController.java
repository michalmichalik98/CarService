package com.example.car_service.controller;

import com.example.car_service.model.Vehicle;
import com.example.car_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;


    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getActiveVehicles());
        return "vehicleList";
    }

    @PostMapping("/addVehicle")
    public String addVehicle(@RequestParam String registrationNumber,
                             @RequestParam String name,
                             @RequestParam String color,
                             @RequestParam int year) {
        vehicleService.addVehicle(new Vehicle(registrationNumber, name, color, year));
        return "redirect:/vehicles";
    }

    @PostMapping("/repairVehicle")
    public String repairVehicle(@RequestParam String registrationNumber) {
        vehicleService.repairVehicle(registrationNumber);
        return "redirect:/vehicles";
    }
}
