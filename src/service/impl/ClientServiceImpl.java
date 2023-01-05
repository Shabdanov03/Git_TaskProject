package service.impl;

import classes.Client;
import classes.Driver;
import classes.Taxi;
import dao.Database;
import service.ClientService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ClientServiceImpl implements ClientService {
    Database database = new Database();

    @Override
    public String addClient(Client client) {
        try{
            for (Client databaseClient : database.getClients()) {
                if(client.getId().equals(databaseClient.getId()))
                    throw new Exception("Duplicate Client ID. Try another ID.");
            }
            database.getClients().add(client);
            return "Client added successfully!";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Something went wrong. (addClient Method)";
    }


    @Override
    public String addClient(List<Client> clients) {
        database.getClients().addAll(clients);
        return "Successfully Added ! ";
    }

    @Override
    public List<Client> getClientByName(String name) {
        List<Client> list = database.getClients().stream().filter(x -> x.getFullName().contains(name)).toList();
        return list;
    }

    @Override
    public Client removeClientById(Long id) {
        Client list = database.getClients().stream().filter(x -> x.getId().equals(id)).findAny().get();
        database.getClients().remove(list);
        return list;
    }

    @Override
    public Taxi orderTaxi(Long clientId, String taxiType) {
        Taxi taxi = new Taxi();
        Driver driver = new Driver();
        int sum = 0;
        try {
            for (Client client : database.getClients()) {
                if (client.getId().equals(clientId)) {
                    throw new Exception();
                } else {
                    System.out.println("Enter by command : (standart / comfort / business)");
                    if (taxiType.equals("business")) {
                        System.out.println(" Enter by km !");
                        int km = new Scanner(System.in).nextInt();
                        int k = taxi.getTaxiType().getPricePerKm().intValue();
                        sum = k * km;
                        int mon = client.getMoney().intValue();
                        client.setMoney(BigDecimal.valueOf(mon-sum));

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong input ! ");
                    }
        return taxi;
    }

    @Override
    public Map<Integer, Client> getClientAge() {
        Map<Integer, Client> map = new LinkedHashMap<>();
        for (Client client : database.getClients()) {
            int period = Period.between(client.getDateOfBirth(), LocalDate.now()).getYears();
            map.put(period, client);
        }
        return map;
    }

    @Override
    public void universalSorting(String word) {
        switch (word) {
            case "id" -> {
                System.out.println("Enter by command : (ascending / descending)");
                String w = new Scanner(System.in).nextLine();
                if (w.equals("ascending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getId)).forEach(System.out::println);
                } else if (w.equals("descending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getId).reversed()).forEach(System.out::println);
                }
            }
            case "fullName" -> {
                System.out.println("Enter by command : (ascending / descending)");
                String w = new Scanner(System.in).nextLine();
                if (w.equals("ascending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getFullName)).forEach(System.out::println);
                } else if (w.equals("descending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getFullName).reversed()).forEach(System.out::println);
                }
            }
            case "dateOfBirth" -> {
                System.out.println("Enter by command : (ascending / descending)");
                String w = new Scanner(System.in).nextLine();
                if (w.equals("ascending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getDateOfBirth)).forEach(System.out::println);
                } else if (w.equals("descending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getDateOfBirth).reversed()).forEach(System.out::println);
                }
            }
            case "phoneNumber" -> {
                System.out.println("Enter by command : (ascending / descending)");
                String w = new Scanner(System.in).nextLine();
                if (w.equals("ascending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getPhoneNumber)).forEach(System.out::println);
                } else if (w.equals("descending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getPhoneNumber).reversed()).forEach(System.out::println);
                }
            }
            case "money" -> {
                System.out.println("Enter by command : (ascending / descending)");
                String w = new Scanner(System.in).nextLine();
                if (w.equals("ascending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getMoney)).forEach(System.out::println);
                } else if (w.equals("descending")) {
                    database.getClients().stream().sorted(Comparator.comparing(Client::getMoney).reversed()).forEach(System.out::println);
                }
            }
            default -> System.out.println("NO SUCH COMMAND !");
        }

    }
}
