package model;
/**
 *  Class Produs
 *  @author Bicioi Luiza
 */
public class Produs {

    private int id;
    private String name;
    private int inStoc;
    private double pret;

    public Produs() {

    }
    /**
     * The constructor with params - instantiates a new product
     * @param id
     *          the id
     * @param name
     *          product's name
     * @param pret
     *          the price
     * @param inStoc
     *          initial stock
     */

    public Produs(int id, String name, int inStoc, double pret) {
        super();
        this.id = id;
        this.name = name;
        this.inStoc = inStoc;
        this.pret = pret;
    }
    /**
     * The constructor without id
     */

    public Produs(String name, int inStoc, double pret) {
        super();
        this.name = name;
        this.inStoc = inStoc;
        this.pret = pret;
    }



    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }



    public int getInStoc() {

        return inStoc;
    }

    public void setInStoc(int inStoc) {

        this.inStoc = inStoc;
    }

    public double getPret() {

        return pret;
    }

    public void setPret(double pret) {

        this.pret = pret;
    }
}

