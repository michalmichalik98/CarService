package com.example.car_service.service;

import com.example.car_service.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final List<Vehicle> activeVehicles = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Vehicle> getActiveVehicles() {
        return activeVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        activeVehicles.add(vehicle);
        saveActiveVehicles();
    }

    public void repairVehicle(String registrationNumber) {
        Optional<Vehicle> vehicle = activeVehicles.stream()
                .filter(v -> v.getRegistrationNumber().equals(registrationNumber) && !v.isFixed())
                .findFirst();

        vehicle.ifPresent(v -> {
            v.setFixed(true);
            activeVehicles.remove(v);
            saveRepairedVehicle(v);
            saveActiveVehicles();
        });
    }

    // Metoda zapisująca aktywne pojazdy do pliku JSON/XML
    private void saveActiveVehicles() {
        try {
            objectMapper.writeValue(Paths.get("active_vehicles.json").toFile(), activeVehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda zapisująca naprawione pojazdy do pliku w katalogu "fixed"
    private void saveRepairedVehicle(Vehicle vehicle) {
        String date = LocalDate.now().toString();
        File dir = new File("fixed");
        if (!dir.exists()) dir.mkdir();

        File repairedFile = new File(dir, date + ".json");
        List<Vehicle> repairedVehicles = loadRepairedVehicles(repairedFile);

        repairedVehicles.add(vehicle);
        try {
            objectMapper.writeValue(repairedFile, repairedVehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda wczytująca pojazdy naprawione z pliku JSON/XML
    private List<Vehicle> loadRepairedVehicles(File file) {
        if (!file.exists()) return new ArrayList<>();
        try {
            return List.of(objectMapper.readValue(file, Vehicle[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
