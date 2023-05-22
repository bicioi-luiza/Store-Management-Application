package dao;

import bll.BillBLL;
import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Bill;
import model.Client;
import model.Comanda;
import model.Produs;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Clasa BillDAO
 * @author Bicioi Luiza
 */
public class BillDAO extends AbstractDAO<Bill>{
    /**
     * Face bill ul pt comanda dorita , apeland insertul sin AbstractDAO
     * @param com comanda a carei bill se doreste sa se faca
     * @param p produsul al carui id il avem in comanda
     * @param c clientul al carui id il avem in comanda
     */
    public void createBill(Comanda com, Produs p, Client c){
        double total=p.getPret()*com.getCantitate();
        Bill bill = new Bill(0,com.getId(),c.getNume(),c.getEmail(),p.getName(),p.getPret(),com.getCantitate(),total);
        BillDAO billDAO=new BillDAO();
        billDAO.insert(bill);
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(bill.idComanda() + ".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(7);
            PdfPCell cell1 = new PdfPCell(new Phrase("ComandaId"));
            PdfPCell cell2 = new PdfPCell(new Phrase("Nume client"));
            PdfPCell cell3 = new PdfPCell(new Phrase("Email client"));
            PdfPCell cell4 = new PdfPCell(new Phrase("Nume produs"));
            PdfPCell cell5 = new PdfPCell(new Phrase("Pret produs"));
            PdfPCell cell6 = new PdfPCell(new Phrase("Cantitate"));
            PdfPCell cell7 = new PdfPCell(new Phrase("Total plata"));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(String.valueOf(bill.idComanda()));
            table.addCell(String.valueOf(bill.numeClient()));
            table.addCell(String.valueOf(bill.emailClient()));
            table.addCell(String.valueOf(bill.numeProdus()));
            table.addCell(String.valueOf(bill.pretPerProdus()));
            table.addCell(String.valueOf(bill.cantitate()));
            table.addCell(String.valueOf(bill.totalDePlata()));
            document.add(table);
            document.close();
        }catch (Exception e){
        };
    }

    public void parcurgerBill(){
        ComandaBLL comandaBLL=  new ComandaBLL();
        int ok=1;
        List<Comanda> comenzi=comandaBLL.findAllOrders();
        BillBLL billBLL = new BillBLL();
        List<Bill> bills = billBLL.readBill();
        for (Comanda comanda : comenzi) {
            ok = 1;
            for (Bill bill : bills) {
                if (bill.idComanda() == comanda.getId()) {
                    ok = 0;
                    System.out.println("Ok este 0");
                    billBLL.deleteBill(bill.id());
                }
            }
            if (ok == 1) {
                ProdusBLL produsBLL = new ProdusBLL();
                Produs p = produsBLL.findProductById(comanda.getProdusID());
                ClientBLL clientBLL = new ClientBLL();
                Client c = clientBLL.findClientById(comanda.getClientID());
                createBill(comanda, p, c);
            }
        }
    }


}
