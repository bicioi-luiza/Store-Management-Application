package bll;

import bll.validators.ClientValidator;
import bll.validators.ProdusValidator;
import dao.ProdusDAO;
import model.Produs;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ProdusBLL
 * @author Bicioi Luiza
 */
public class ProdusBLL {

    private ProdusDAO produsDAO;
    private ProdusValidator validator;
    /**
     * Constructor fara parametrii , contine DAO si validatorul pentru ca o comanda sa fie plasata
     */
    public ProdusBLL() {
        validator = new ProdusValidator();
        produsDAO = new ProdusDAO();
    }

    public Produs findProductById(int id) {
        Produs produsFind = produsDAO.findById(id);
        if (produsFind == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return produsFind;
    }

    public List<Produs> findAllProducts() {
        List<Produs> lista = produsDAO.findAll();
        if (lista == null) {
            throw new NoSuchElementException("There are no products");
        }
        return lista;
    }
    /**
     * Cauta produsul dupa numele sau , folsita in interfata pentru plasarea comezii la un combobox
     * @param name numele dupa care se cauta produsul
     * @return produsul cautat
     */
    public Produs findProductByName(String name) {
        Produs pr = produsDAO.findByName(name);
        if (pr == null) {
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return pr;
    }

    /**
     * Apeleaza insertul din AbstractDAO , dar nu inainte de a verfica daca produsul ce se doreste inserat este valid
     * @param produsInsert reprezinta ce ne dorim sa inseram
     */
    public void insertProduct(Produs produsInsert) {
        validator.validate(produsInsert);
        produsDAO.insert(produsInsert);
    }

    /**
     * Sterge produsul , cu functia predefinita in AbsrtractDAO
     * @param ID id ul produsului care se va sterge
     */
    public void deleteProduct(int ID) {
        Produs p = produsDAO.findById(ID);
        produsDAO.delete(ID);
    }

    /**
     * Se editeaza produsul , dar nu inainte de a valida datele care se doresc schimbate
     * @param produsUpdate produsul care se vrea modificat
     * @param id id ul produsului respectiv
     */
    public void updateProductById(Produs produsUpdate, int id) {
        validator.validate(produsUpdate);
        produsDAO.update(produsUpdate, id);
    }



}
