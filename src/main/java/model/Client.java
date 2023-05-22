package model;

/**
 *  Class Client
 *  @author Bicioi Luiza
 */
public class Client {

    private int id;
    private String nume;
    //private String prenume;

    private String email;
    private String adresa;
    private int varsta;

    public Client(){

    }
    /**
     * The constructor with params - instantiates a new client
     * @param id
     *          the id of the client
     * @param nume
     *          the name of the client
     * @param email
     *          client's email
     * @param adresa
     *          client's address
     * @param varsta
     *          client's age
     */
    public Client(int id, String nume, String email, String adresa, int varsta){
        super();
        this.id = id;
        this.nume = nume;
        //this.prenume = prenume;
        this.adresa = adresa;
        this.email = email;
        this.varsta = varsta;
    }
    /**
     * The constructor without id
     */
    public Client(String name, String email, String adresa, int age) {
        super();
        this.nume = name;
        this.adresa = adresa;
        this.email = email;
        this.varsta = age;
    }

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public String getNume() {

        return nume;
    }
    public void setNume(String name) {

        this.nume = name;
    }

    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }
    public String getAdresa() {

        return adresa;
    }

    public void setAdresa(String address) {

        this.adresa = address;
    }

    public int getVarsta() {

        return varsta;
    }
    public void setVarsta(int age) {

        this.varsta = age;
    }











    /*public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
*/

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", adresa='" + adresa + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}

