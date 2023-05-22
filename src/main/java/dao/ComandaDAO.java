package dao;

import bll.BillBLL;
import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionFactory;
import model.Bill;
import model.Client;
import model.Comanda;
import model.Produs;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa ComandaDAO
 * @author Bicioi Luiza
 */
public class ComandaDAO extends AbstractDAO<Comanda> {

    /**
     * Metoda este similara cu insertul din AbstracDAO doar ca aici se si seteaza stocul din produsul ce se doreste inserat
     * @param produsComanda este produsul care se doreste sa fie comandat
     * @param clientID id ul clientului al carui nume a fost selectat
     * @param produsID id ul produsului al carui nume a fost selectat
     * @param cantitate numarul de bucati dorite
     */
    public int adaugaComanda( Produs produsComanda,int clientID, int produsID, int cantitate){
        Connection connection = null;
        PreparedStatement statement = null;
        Comanda com = new Comanda();
        ArrayList<Object> valori = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<String>();
        String insertQuery = "INSERT INTO comanda  (";
        String fieldsForQuery = getFieldsQueryString(com,fields);
        String valuesForQuery = getValuesQueryString(com,fields,valori);
        try {
            insertQuery += fieldsForQuery + " VALUES(" + valuesForQuery;
            int stocRamas = produsComanda.getInStoc() - cantitate;
            produsComanda.setInStoc(stocRamas);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertQuery);
            statement.setInt(1,clientID);
            statement.setInt(2,produsID);
            statement.setInt(3,cantitate);
            statement.execute();
        } catch (SQLException|SecurityException | IllegalArgumentException   e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
        return com.getId();
    }




    /**
     * Similara cu update din AbstractDao si adaugaComanda ,in bll se face modificarea produsului vechi , in cazul in care sa doreste alt produs nou
     * @param produsComanda  este produsul care se doreste sa fie comandat
     * @param id id ul comenzi care se doreste sa fie modificata
     * @param clientID id ul clientului al carui nume a fost selectat
     * @param produsID id ul produsului al carui nume a fost selectat
     * @param cantitate numarul de bucati dorite
     */
    public void updateComanda(Produs produsComanda, int id,int clientID, int produsID, int cantitate ) {
        Comanda com = new Comanda();
        ArrayList<Object> valori = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<String>();
        String updateQuery = "UPDATE comanda SET ";
        Connection connection = null;
        PreparedStatement statement = null;
        String fieldsForQuery = getFieldsForUpdateQuery(com,fields,valori);
        updateQuery+=fieldsForQuery+ "WHERE id = ?;";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuery);
            int stocRamas = produsComanda.getInStoc() - cantitate;
            produsComanda.setInStoc(stocRamas);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuery);
            statement.setInt(1,id);
            statement.setInt(2,clientID);
            statement.setInt(3,produsID);
            statement.setInt(4,cantitate);
            int n=fields.size()+1;
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException|SecurityException | IllegalArgumentException   e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }




}

