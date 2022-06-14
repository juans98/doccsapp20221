//CRUD Objects
package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.Client;

public interface ClientDAOInt {
    public List<Client> listClients() throws FileNotFoundException, IOException, ClassNotFoundException;
    public String addClient(Client client) throws FileNotFoundException, IOException;
    public void deleteClient(Client client);
    public void updateClient(Client client);
    
}
