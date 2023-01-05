package service.impl;

import classes.Taxi;
import dao.Database;
import enums.TaxiType;
import service.TaxiService;

import java.util.List;
import java.util.Map;

public class TaxiServiceImpl implements TaxiService {
    Database database = new Database();

    @Override
    public StringBuilder add(Taxi taxi) {
        return null;
    }

    @Override
    public StringBuilder add(List<Taxi> taxis) {
        return null;
    }

    @Override
    public List<Taxi> findByInitialLetter(String model) {
        return null;
    }

    @Override
    public Map<TaxiType, List<Taxi>> grouping() {
        return null;
    }

    @Override
    public List<Taxi> filterByTaxiType(String taxiType) {
        return null;
    }

    @Override
    public void update(Long id) {

    }
}
