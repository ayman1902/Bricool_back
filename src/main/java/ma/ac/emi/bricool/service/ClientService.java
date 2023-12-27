package ma.ac.emi.bricool.service;

import ma.ac.emi.bricool.entities.Client;
import ma.ac.emi.bricool.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Create a new client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get a client by ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Update a client by ID
    public Optional<Client> updateClient(Long id, Client clientDetails) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            // Update client details
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setEmail(clientDetails.getEmail());
            client.setPassword(clientDetails.getPassword());
            client.setPhoneNumber(clientDetails.getPhoneNumber());
            client.setYearsOfBirth(clientDetails.getYearsOfBirth());
            client.setGender(clientDetails.getGender());
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }

    // Delete a client by ID
    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}