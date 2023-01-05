package service;

public interface ClientService {
    String addClient(Client client);
    String addClient(List<Client> clients);
    List<Client> getClientByName(String name);
    Client removeClientById(Long id);
    Taxi orderTaxi(Long clientId, String taxiType);
    Map<Integer, Client> getClientAge();
    void universalSorting(String word);

}
