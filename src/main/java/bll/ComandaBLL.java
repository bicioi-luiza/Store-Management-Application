package bll;

import bll.validators.ComandaValidator;
import bll.validators.Validator;
import dao.BillDAO;
import dao.ComandaDAO;
import model.Client;
import model.Comanda;
import model.Produs;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ComandaBLL
 * @author Bicioi Luiza
 */
public class ComandaBLL {
    private Validator validator;
    private ComandaDAO comandaDAO;

    /**
     * Constructor fara parametrii , contine DAO si validatorul pentru ca o comanda sa fie plasata
     */
    public ComandaBLL(){
        validator = new ComandaValidator();
        comandaDAO = new ComandaDAO();

    }

    public Comanda findProductById(int id) {
        Comanda comandaFind = comandaDAO.findById(id);
        if (comandaFind == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return comandaFind;
    }
    public List<Comanda> findAllOrders(){
        List<Comanda> lista = comandaDAO.findAll();
        if(lista == null){
            throw new NoSuchElementException("There are no orders");
        }
        return lista;
    }


    /**
     *
     * @param clientID id ul clientului care plaseaza comanda
     * @param produsID id ul produsului care este comandat
     * @param cantitate cate bucati se doresc din produs
     * @return
     */
    public int adaugareComandaBLL(int clientID, int produsID, int cantitate){
        ProdusBLL produsComandaBLL = new ProdusBLL();
        Comanda order = new Comanda(clientID,produsID,cantitate);
        validator.validate(order);

        Produs produsComanda = produsComandaBLL.findProductById(produsID);
        int id=comandaDAO.adaugaComanda(produsComanda,clientID,produsID,cantitate);

        produsComandaBLL.updateProductById(produsComanda,produsID);
        produsComanda = produsComandaBLL.findProductById(produsID);
        if(produsComanda.getInStoc() == 0){
            int idProd=produsComanda.getId();
            produsComandaBLL.deleteProduct(idProd);
        }
        return id;
    }

    /**
     * Sterge comanda
     * @param ID se cauta comanda cu id ul si se sterge
     */
    public void deleteOrder(int ID) {
        Comanda c = comandaDAO.findById(ID);
        comandaDAO.delete(ID);
    }
    public Comanda findOrderById(int id) {
        Comanda comandaFind = comandaDAO.findById(id);
        if (comandaFind == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return comandaFind;
    }

    /**
     * Editeaza o comanda deja existenta
     * @param ID id ul comenzii existente
     * @param clientID id ul clientului care o plaseaza
     * @param produsID id ul produsului care se comanda
     * @param cantitate numarul de bucati dorite
     */
    public void updateComandaBLL(int ID,int clientID, int produsID, int cantitate){
        ProdusBLL produsComandaBLL = new ProdusBLL();
        ComandaBLL comandaVeche=new ComandaBLL();
        Comanda comVeche=comandaVeche.findOrderById(ID);
        ProdusBLL produsVechiBLL = new ProdusBLL();
        Produs produsVechi = produsComandaBLL.findProductById(comVeche.getProdusID());
        produsVechi.setInStoc(produsVechi.getInStoc()+comVeche.getCantitate());
        produsVechiBLL.updateProductById(produsVechi,comVeche.getProdusID());

        Produs produsComanda = produsComandaBLL.findProductById(produsID);
        comandaDAO.updateComanda(produsComanda,ID,clientID,produsID,cantitate);

        produsComandaBLL.updateProductById(produsComanda,produsID);
        produsComanda = produsComandaBLL.findProductById(produsID);
        if(produsComanda.getInStoc() == 0){
            int idProd=produsComanda.getId();
            produsComandaBLL.deleteProduct(idProd);
        }
    }

public void createBillBLL(Comanda com, Produs p, Client c){
    BillDAO billDAO=new BillDAO();
    billDAO.createBill(com,p,c);
}






}