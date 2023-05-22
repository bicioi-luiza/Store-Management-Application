package model;

/**
 * Clasa Bill imutabila
 * @param id id ul facturii
 * @param idComanda id ul comenzii pt care se calculeaza factura
 * @param numeClient numele clientului din factura
 * @param emailClient emailul clientului din factura
 * @param numeProdus numele produsului selectat
 * @param pretPerProdus cat costa un produs
 * @param cantitate cate produse doreste clientul
 * @param totalDePlata cat are de plata
 */
public record Bill(int id,int idComanda,String numeClient,String emailClient,String numeProdus,double pretPerProdus,int cantitate,double totalDePlata){

}
