package service.impl;

import classes.Taxi;
import dao.Database;
import enums.TaxiType;
import service.TaxiService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaxiServiceImpl implements TaxiService {
    Database database = new Database();

    @Override
    public StringBuilder add(Taxi taxi) {
        if (taxi.getYear().isAfter(LocalDate.of(2010, 12, 31))) {
            database.getTaxis().add(taxi);
            return new StringBuilder("Taxi added successfully!");
        } else {
            return new StringBuilder("Taxi invalid!");
        }
    }

    @Override
    public StringBuilder add(List<Taxi> taxis) {
        boolean trueOrOFalse = false;
        for (Taxi taxi : taxis) {
            if(taxi.getYear().isAfter(LocalDate.of(2010,12,31))){
                trueOrOFalse = true;
                database.getTaxis().add(taxi);
            }
        }
        if(!trueOrOFalse) return new StringBuilder("Taxi invalid!");
        return new StringBuilder("Taxi added successfully!");
    }

    @Override
    public List<Taxi> findByInitialLetter(String model) {
        List<Taxi> taxi1 = database.getTaxis().stream().filter(taxi -> taxi.getModel().startsWith(model)).toList();
        return taxi1;
    }

    @Override
    public Map<TaxiType, List<Taxi>> grouping() {
        Map<TaxiType, List<Taxi>> map = database.getTaxis().stream().collect(Collectors.groupingBy(Taxi::getTaxiType));
        return map;

    }

    @Override
    public List<Taxi> filterByTaxiType(String taxiType) {
        switch (taxiType) {
            case "standart" -> {
                List<Taxi> standart = database.getTaxis().stream().filter(taxi -> taxi.getTaxiType().equals(TaxiType.STANDART)).toList();
                return standart;
            }
            case "comfort" -> {
                List<Taxi> comfort = database.getTaxis().stream().filter(taxi -> taxi.getTaxiType().equals(TaxiType.COMFORT)).toList();
                return comfort;
            }
            case "business" -> {
                List<Taxi> business = database.getTaxis().stream().filter(taxi -> taxi.getTaxiType().equals(TaxiType.BUSINESS)).toList();
                return business;
            }
        }
        return null;
    }

    @Override
    public void update(Long id) {
        Scanner scanner = new Scanner(System.in);
        List<Taxi> t = database.getTaxis().stream().filter(taxi -> taxi.getId().equals(id)).toList();
        System.out.print("Current id: ");
        t.stream().map(Taxi::getId).forEach(System.out::println);
        System.out.print("Write new id: ");
        t.forEach(taxi -> taxi.setId(scanner.nextLong()));
        System.out.print("Id changed successfully!");


    }
}
