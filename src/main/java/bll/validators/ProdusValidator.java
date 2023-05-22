package bll.validators;

import model.Produs;


public class ProdusValidator implements Validator<Produs>{
    public void validate(Produs prod) {
        if(prod.getInStoc()<1){
            throw new IllegalArgumentException("Trebuie sa existe cel putin un produs in stoc!");
        }
        if(prod.getPret()<1){
            throw new IllegalArgumentException("Trebuie sa aiba un pret mai mare decat 0!");
        }
    }
}
