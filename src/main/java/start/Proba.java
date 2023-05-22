package start;

import bll.ClientBLL;
import connection.ConnectionFactory;
import model.Client;
import start.ReflectionEx;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class Proba {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    public static void main(String[] args) throws SQLException {
        ClientBLL clientBLL = new ClientBLL();
        List<Client> clients = clientBLL.findAllClients();
        for(Client client:clients) {
            client.toString();
            ReflectionEx.retrieveProperties(client);
        }
    }


}
