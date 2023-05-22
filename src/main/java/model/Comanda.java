package model;

/**
 *  Class Comanda
 *  @author Bicioi Luiza
 */
public class Comanda {

    private int id;
    private int clientID;
    private int produsID;
    private int cantitate;


    public Comanda(){

    }
    /**
     * The constructor with params  - instantiates a new order
     * @param id
     *          the order id
     * @param clientID
     *          the id of the client
     * @param produsID
     *          the id of the product
     * @param cantitate
     *          the quantity of the product
     */
    Comanda(int id, int clientID, int produsID, int cantitate){
        super();
        this.id = id;
        this.clientID = clientID;
        this.produsID = produsID;
        this.cantitate = cantitate;

    }
    /**
     * The constructor without id
     */

    public Comanda(int clientID, int produsID, int cantitate){
        super();
        this.clientID = clientID;
        this.produsID = produsID;
        this.cantitate = cantitate;

    }

    public int getId() {

        return id;
    }


    public void setId(int id) {

        this.id = id;
    }

    public int getCantitate() {

        return cantitate;
    }

    public void setCantitate(int cantitate) {

        this.cantitate = cantitate;
    }

    public int getClientID() {

        return clientID;
    }

    public void setClientID(int clientID) {

        this.clientID = clientID;
    }

    public int getProdusID() {
        return produsID;
    }

    public void setProdusID(int produsID) {

        this.produsID = produsID;
    }
}

