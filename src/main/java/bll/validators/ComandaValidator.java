package bll.validators;

import bll.ProdusBLL;
import model.Comanda;
import model.Produs;

import javax.swing.*;

public class ComandaValidator implements Validator<Comanda> {

    @Override
    public void validate(Comanda order) {
        ProdusBLL produsPtComanda = new ProdusBLL();
        Produs produs = produsPtComanda.findProductById(order.getProdusID());
        if (order.getCantitate() <=0 || order.getCantitate() > produs.getInStoc()) {
            JOptionPane.showMessageDialog(null, "Cantitatea ceruta este mai mare decat cea de pe stoc");
            throw new IllegalArgumentException("The Product Stock limit is not respected!");
        }
    }
}
