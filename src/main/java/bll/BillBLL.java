package bll;

import bll.validators.ClientValidator;
import dao.BillDAO;
import dao.ClientDAO;
import dao.ComandaDAO;
import model.Bill;
import model.Client;
import model.Comanda;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa BillBLL
 * @author Bicioi Luiza
 */
public class BillBLL {
    private BillDAO billDAO;

    public BillBLL(){

        billDAO = new BillDAO();
    }

    /**
     * Insereaza bill ul pentru comanda plasata
     */
    public void insertBill(){


        billDAO.parcurgerBill();
    }

    /**
     * Citeste toate facturile
     * @return lista de bill uri deja existente
     */
    public List<Bill> readBill(){

        List<Bill> lista = billDAO.findAll();
        if(lista == null){
            throw new NoSuchElementException("There are no orders");
        }
        return lista;
    }

    public void deleteBill(int id)
    {
       Bill bill = billDAO.findById(id);

        billDAO.delete(id);
    }
}
