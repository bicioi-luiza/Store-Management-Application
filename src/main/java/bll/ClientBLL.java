package bll;

import bll.validators.ClientValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ClientBLL
 * @author Bicioi Luiza
 *
 */
public class ClientBLL {

    private Validator validator;
    private ClientDAO clientDAO;

    public ClientBLL(){
        validator = new ClientValidator();
        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }
    public List<Client> findAllClients(){
        List<Client> lista = clientDAO.findAll();
        if(lista == null){
            throw new NoSuchElementException("There are no clients");
        }
        return lista;
    }

    /**
     * Cauta clientul dupa numele sau , folsita in interfata pentru plasarea comezii la un combobox
     * @param name numele dupa care se cauta clientul
     * @return clientul cautat
     */
    public Client findClientByName(String name) {
        Client client = clientDAO.findByName(name);
        if (client == null) {
            throw new NoSuchElementException("The client with name =" + name + " was not found!");
        }
        return client;
    }

    /**
     * Se insereaza clientul
     * @param clientInsert reprezinta clientul care se doreste inserat
     */
    public void insertClient(Client clientInsert){
        validator.validate(clientInsert);
        clientDAO.insert(clientInsert);
    }

    /**
     * Sterge client
     * @param id id-ul clientului care se vrea inserat
     */
    public void deleteClient(int id)
    {
        Client clientDeSters = clientDAO.findById(id);

        clientDAO.delete(id);
    }

    /**
     * Editeaza clientul
     * @param clientUpdate clientul care se vrea editat
     * @param id id ul acestuia, pentru a simplifica .Se putea sa nu se transmita
     */
    public void updateClientById(Client clientUpdate, int id){
        validator.validate(clientUpdate);
        clientDAO.update(clientUpdate,id);
    }



}