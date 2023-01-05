import classes.Client;
import classes.Driver;
import classes.License;
import classes.Taxi;
import enums.Gender;
import enums.TaxiType;
import service.impl.ClientServiceImpl;
import service.impl.DriverServiceImpl;
import service.impl.TaxiServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ClientServiceImpl clientService = new ClientServiceImpl();
    public static DriverServiceImpl driverService = new DriverServiceImpl();
    public static TaxiServiceImpl taxiService = new TaxiServiceImpl();

    public static void main(String[] args) {


        License license = new License(3L, LocalDate.of(2018, 2, 4), LocalDate.of(2028, 3, 5));
        License license2 = new License(88L, LocalDate.of(2019, 2, 4), LocalDate.of(2038, 3, 5));
        Taxi taxi = new Taxi(4L, "BMW", "07kg 156 aac", "red", LocalDate.of(2013, 5, 7), TaxiType.BUSINESS);
        Taxi taxi2 = new Taxi(21L, "FIT", "07kg 234 aac", "black", LocalDate.of(2019, 5, 7), TaxiType.COMFORT);
        Taxi taxi3 = new Taxi(14L, "MERS", "07kg 334 aac", "blue", LocalDate.of(2016, 5, 7), TaxiType.STANDART);
        List<Taxi> taxis = new ArrayList<>(List.of(taxi2, taxi3));

        Client client = new Client(1L, "ShabdanovIlim", LocalDate.of(2003, 10, 3), "+996123456", BigDecimal.valueOf(1000));
        Client client2 = new Client(7L, "AltynbekovAlibek", LocalDate.of(2002, 10, 3), "+99622323456", BigDecimal.valueOf(2000));
        List<Client> clients = new ArrayList<>(List.of(client));

        Driver driver = new Driver(2L, "Nurik", "Djoldoshov", Gender.MALE, "+9962344332", license, BigDecimal.valueOf(5000));
        Driver driver2 = new Driver(22L, "Dastan", "Akbaraliev", Gender.MALE, "+9968944332", license2, BigDecimal.valueOf(1500));
        List<Driver> drivers = new ArrayList<>(List.of(driver2));

        while (true) {
            System.out.println("""
                    =====CLIENT COMMANDS=====
                    1.ADD CLIENT MAIN 
                    2.ADD CLIENT LIST
                    3.GET CLIENT BY NAME :
                    4.REMOVE CLIENT BY ID 
                    5.ORDER TAXI
                    6.GET CLIENT AGE
                    7.UNIVERSAL SORTING :
                    =====DRIVER COMMANDS=====
                    8.ADD DRIVER MAIN
                    9.ADD DRIVER LIST
                    10.FIND BY ID
                    11.FIND BY NAME
                    12.ASSIGN TAXI TO DRIVER
                    13.CHANGE TAXI OR DRIVER
                    14.GET DRIVER BY TAXI MODEL
                    15.UPDATE DRIVER :
                    =====TAXI COMMANDS=====
                    16.ADD TAXI MAIN
                    17.ADD TAXI LIST
                    18.FIND BY INITIAL LETTER 
                    19.TAXI GROUPING
                    20.FILTER BY TAXI TYPE
                    21.UPDATE TAXI ID 
                                    
                    """);
            System.out.println("Enter by command :");
            int a = new Scanner(System.in).nextInt();
            switch (a) {
                case 1 -> System.out.println(clientService.addClient(client2));
                case 2 -> System.out.println(clientService.addClient(clients));
                case 3 -> {
                    System.out.println("Enter by name :");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println(clientService.getClientByName(name));
                }
                case 4 -> {
                    System.out.println("Enter by id :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(clientService.removeClientById(id));
                }
                case 5 -> {
                    System.out.println("Enter by client id :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter by taxi type :");
                    String type = new Scanner(System.in).nextLine();
                    System.out.println(clientService.orderTaxi(id, type));
                }
                case 6 -> System.out.println(clientService.getClientAge());
                case 7 -> {
                    System.out.println("Enter by command : (id / fullName / dateOfBirth / phoneNumber / money)");
                    System.out.println("Enter by command :");
                    String word = new Scanner(System.in).nextLine();
                    clientService.universalSorting(word);
                }
                case 8 -> System.out.println(driverService.add(driver));
                case 9 -> System.out.println(driverService.add(drivers));
                case 10 -> {
                    System.out.println("Enter by id :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(driverService.findById(id));
                }
                case 11 -> {
                    System.out.println("Enter by name :");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println(driverService.findByName(name));
                }
                case 12 -> {
                    System.out.println("Enter by taxi name :");
                    String taxiname = new Scanner(System.in).nextLine();
                    System.out.println("Enter by long :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(driverService.assignTaxiToDriver(taxiname, id));
                }
                case 13 -> {
                    System.out.println("Enter by driver id : ");
                    Long driverid = new Scanner(System.in).nextLong();
                    System.out.println("Enter by taxi id :");
                    Long taxiid = new Scanner(System.in).nextLong();
                    System.out.println(driverService.changeTaxiOrDriver(driverid, taxiid));
                }
                case 14 -> {
                    System.out.println("Enter by model :");
                    String model = new Scanner(System.in).nextLine();
                    System.out.println(driverService.getDriverByTaxiModel(model));
                }
                case 15 -> {
                    System.out.println("Enter by driver name :");
                    String driverName = new Scanner(System.in).nextLine();
                    driverService.update(driverName);
                }
                case 16 -> System.out.println(taxiService.add(taxi));
                case 17 -> System.out.println(taxiService.add(taxis));
                case 18 -> {
                    System.out.println("Enter by initial letter model :");
                    String l = new Scanner(System.in).nextLine();
                    System.out.println(taxiService.findByInitialLetter(l));
                }
                case 19-> System.out.println(taxiService.grouping());
                case 20->{
                    System.out.println("Select taxi type (standart / comfort / business)");
                    String com = new Scanner(System.in).nextLine();
                    System.out.println(taxiService.filterByTaxiType(com));
                }
                case 21->{
                    System.out.println("Enter by id :");
                    Long id = new Scanner(System.in).nextLong();
                    taxiService.update(id);
                }
                default -> System.out.println("No such command !");
            }
        }
    }
}