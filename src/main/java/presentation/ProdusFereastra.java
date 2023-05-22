package presentation;
import bll.ProdusBLL;
import model.Client;
import model.Produs;
import start.ReflectionEx;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Fereastra Produs
 * @author Bicioi Luiza
 */
public class ProdusFereastra {

    private JFrame frame;
    private JTextField texID;
    private JTextField textNume;
    private JTextField textStoc;
    private JTextField textPret;
    private JTable table;
    ProdusBLL produsBLL = new ProdusBLL();

    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public ProdusFereastra() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        JLabel lbProdus = new JLabel("Date produs");
        lbProdus.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbProdus.setBounds(122, 136, 108, 20);
        frame.getContentPane().add(lbProdus);

        JLabel lbID = new JLabel("ID");
        lbID.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbID.setBounds(21, 183, 50, 20);
        frame.getContentPane().add(lbID);

        texID = new JTextField();
        texID.setColumns(10);
        texID.setBounds(76, 182, 212, 26);
        frame.getContentPane().add(texID);

        JLabel lbNumE = new JLabel("Nume");
        lbNumE.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbNumE.setBounds(21, 219, 45, 20);
        frame.getContentPane().add(lbNumE);

        textNume = new JTextField();
        textNume.setColumns(10);
        textNume.setBounds(76, 218, 212, 26);
        frame.getContentPane().add(textNume);

        JLabel lblBucati = new JLabel("Bucati");
        lblBucati.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblBucati.setBounds(21, 260, 45, 20);
        frame.getContentPane().add(lblBucati);

        textStoc = new JTextField();
        textStoc.setColumns(10);
        textStoc.setBounds(76, 254, 212, 26);
        frame.getContentPane().add(textStoc);

        JLabel lblPret = new JLabel("Pret");
        lblPret.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPret.setBounds(21, 296, 45, 20);
        frame.getContentPane().add(lblPret);

        textPret = new JTextField();
        textPret.setColumns(10);
        textPret.setBounds(76, 290, 212, 26);
        frame.getContentPane().add(textPret);

        JLabel lbTitlu = new JLabel("Produs");
        lbTitlu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbTitlu.setBounds(313, 62, 108, 37);
        frame.getContentPane().add(lbTitlu);

        JLabel lblNewLabel_1 = new JLabel("Ce dorim");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setBounds(136, 355, 88, 20);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnAdaugaProdus = new JButton("Adauga produs");
        btnAdaugaProdus.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdaugaProdus.setBounds(76, 385, 212, 26);
        frame.getContentPane().add(btnAdaugaProdus);

        JButton btnEditeazaProdus = new JButton("Editeaza produs");
        btnEditeazaProdus.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEditeazaProdus.setBounds(76, 427, 212, 26);
        frame.getContentPane().add(btnEditeazaProdus);

        JButton btnStergeProdus = new JButton("Sterge produs");
        btnStergeProdus.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStergeProdus.setBounds(76, 467, 212, 26);
        frame.getContentPane().add(btnStergeProdus);

        JLabel lbList = new JLabel("Lista produse");
        lbList.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbList.setBounds(481, 136, 108, 20);
        frame.getContentPane().add(lbList);

        final JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        table=setareTabel();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(313, 179, 449, 354);
        frame.getContentPane().add(scrollPane);

        JButton btnBack = new JButton("<-");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainFereastra();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBack.setBounds(10, 538, 60, 15);
        frame.getContentPane().add(btnBack);
        btnAdaugaProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Produs produsInsert = new Produs(textNume.getText(), Integer.parseInt(textStoc.getText()), Double.parseDouble(textPret.getText()));
                produsBLL.insertProduct(produsInsert);
                table=setareTabel();
                scrollPane.setViewportView(table);
            }
        });
        btnEditeazaProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Produs produsUpdate = new Produs(Integer.parseInt(texID.getText()),textNume.getText(), Integer.parseInt(textStoc.getText()), Double.parseDouble(textPret.getText()));
                produsBLL.updateProductById(produsUpdate,Integer.parseInt(texID.getText()));
                table=setareTabel();
                scrollPane.setViewportView(table);
            }
        });
        btnStergeProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                produsBLL.deleteProduct(Integer.parseInt(texID.getText()));
                table=setareTabel();
                scrollPane.setViewportView(table);
            }
        });
    }

    /**
     * Parcurge tabelul cu ajutorul reflexiei si adauga in tabel coloanele si datele din ele
     * @return un obiect de tipul JTable
     */
    public JTable setareTabel(){
        List<Produs> produse = produsBLL.findAllProducts();
        ArrayList<String> fieldsList = new ArrayList<>();
        ReflectionEx.getFields(fieldsList, produse.get(0));
        String[] fields = new String[fieldsList.size()];
        int i = 0;
        for (String field : fieldsList) {
            fields[i++] = field;
        }

        Object[][] data = new Object[produse.size()][fieldsList.size()];
        i = 0;
        for (Produs prod : produse) {
            ArrayList<Object> obj = new ArrayList<>();
            ReflectionEx.getValues(obj, prod);
            int j = 0;
            for (Object o : obj) {
                data[i][j++] = o;
            }
            i++;
        }

        JTable table1 = new JTable(data, fields);
        return table1;
    }
}
