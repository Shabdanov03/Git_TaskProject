package service.impl;

import classes.Driver;
import classes.License;
import classes.Taxi;
import dao.Database;
import enums.Gender;
import service.DriverService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverServiceImpl implements DriverService {
    Database database = new Database();

    @Override
    public Driver add(Driver driver) {
        Driver dr = null;
        try {
            for (Driver data : database.getDrivers()) {
                if (driver.getId().equals(data.getId())) {
                    throw new Exception("Duplicate Client ID. Try another ID.");
                }
                database.getDrivers().add(driver);
                return dr;
            }
        } catch (Exception e) {
            System.out.println("Wrong input !");
        }
        return null;
    }

    @Override
    public List<Driver> add(List<Driver> drivers) {
        database.getDrivers().addAll(drivers);
        return database.getDrivers().stream().toList();
    }

    @Override
    public Driver findById(Long id) {
        return database.getDrivers().stream().filter(x -> x.getId().equals(id)).findAny().get();
    }

    @Override
    public List<Driver> findByName(String name) {
        List<Driver> list = database.getDrivers().stream().filter(x -> x.getName().equals(name)).toList();
        return list;
    }

    @Override
    public String assignTaxiToDriver(String taxiName, Long driverId) {
        Driver drive = null;
        Taxi taxi = null;
        try {
            for (Driver driver : database.getDrivers()) {
                if (driverId.equals(driver.getId())) {
                    throw new Exception();
                }
                drive = driver;
            }
            for (Taxi tax : database.getTaxis()) {
                if (taxiName.equals(tax.getModel())) {
                    throw new Exception();
                }
                taxi = tax;
            }
            if (drive != null) {
                if (taxi != null) {
                    return taxi.getModel() + "successfully assign to " + drive.getName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return " Wrong input ! ";
    }

    @Override
    public String changeTaxiOrDriver(Long driverId, Long taxiId) {
        Driver driver = database.getDrivers()
                .stream()
                .filter(x -> x.getId().equals(driverId)).findAny().get();
        Taxi taxi = database.getTaxis()
                .stream()
                .filter(x -> x.getId().equals(taxiId)).findAny().get();
        if (driver.getTaxi() != null){
            for (Driver d : database.getDrivers()) {
                if (!d.getTaxi().equals(taxi)) {
                    driver.setTaxi(taxi);
                    return "Taxi successfully changed!";
                } else {
                    return "Taxi " + taxi.getModel() + " is busy";
                }
            }
        } else {
            return "Вы не можете изменит taxi driver'ра, так как у driver не имеется taxi";
        }
        return "";
    }

    @Override
    public List<Driver> getDriverByTaxiModel(String model) {
        List<Driver> driverList = new ArrayList<>();
        for (Taxi taxi : database.getTaxis()) {
            for (Driver driver : database.getDrivers()) {
                if (taxi.getModel().equals(model)){
                    driverList.add(driver);
                }
            }
        }
        return driverList;
    }

    @Override
    public void update(String driverName) {
        Driver driver = database.getDrivers().stream().filter(x -> x.getName().equals(driverName)).findAny().get();
        System.out.println(" Current id : ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println(" Enter name : ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter surname :");
        String surname = new Scanner(System.in).nextLine();
        System.out.println("Select gender (1.male / 2.female)");
        int c = new Scanner(System.in).nextInt();
        Gender gender = null;
        switch (c){
            case 1-> gender = Gender.MALE;
            case 2-> gender = Gender.FEMALE;
        }
        System.out.println("Enter phone NUmber :");
        String phone = new Scanner(System.in).nextLine();
        System.out.println("Automatic licence :");
        License license = new License(11L, LocalDate.of(2000,3,5),LocalDate.of(2030,2,5));
        System.out.println("Enter by money : ");
        BigDecimal money = new Scanner(System.in).nextBigDecimal();
        driver.setId(id);driver.setName(name);driver.setSurName(surname);
        driver.setGender(gender);driver.setPhoneNumber(phone);
        driver.setLicense(license);driver.setMoney(money);
        System.out.println(" Successfully updated driver ! ");



    }
}
