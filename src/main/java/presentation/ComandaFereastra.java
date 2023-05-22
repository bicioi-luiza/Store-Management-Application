package presentation;

import bll.BillBLL;
import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import model.Client;
import model.Comanda;
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
 * Fereastra Comanda
 * @author Bicioi Luiza
 */
public class ComandaFereastra {

    private JFrame frame;
    private JTextField textID;
    private JTextField textField;
    private JTable table;
    ComandaBLL comandaBLL = new ComandaBLL();
    ClientBLL clientBLL = new ClientBLL();
    ProdusBLL produsBLL = new ProdusBLL();

    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public ComandaFereastra() {
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
        JLabel lblComanda = new JLabel("Comanda");
        lblComanda.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblComanda.setBounds(294, 52, 108, 37);
        frame.getContentPane().add(lblComanda);

        JLabel lblListaComenzi = new JLabel("Lista comenzi");
        lblListaComenzi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblListaComenzi.setBounds(462, 126, 108, 20);
        frame.getContentPane().add(lblListaComenzi);

        final JScrollPane scrollPane = new JScrollPane();
        table=setareTabel();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(294, 169, 449, 354);
        frame.getContentPane().add(scrollPane);
        final ClientBLL clientBLL = new ClientBLL();
        List<Client> clients = clientBLL.findAllClients();
        final String[] names = new String[clients.size()];
        int i = 0;
        for(Client cl:clients){
            names[i++] = cl.getNume();
        }
        final JComboBox comboBox = new JComboBox(names);
        comboBox.setBounds(104, 169, 134, 30);
        frame.getContentPane().add(comboBox);

        final ProdusBLL produsBLL = new ProdusBLL();
        List<Produs> produse = produsBLL.findAllProducts();
        String[] namesProduse = new String[produse.size()];
        i = 0;
        for(Produs prod: produse){
            namesProduse [i++] = prod.getName();
        }
        final JComboBox comboBox_1 = new JComboBox(namesProduse );
        comboBox_1.setBounds(104, 209, 134, 30);
        frame.getContentPane().add(comboBox_1);

        JLabel lblNewLabel = new JLabel("Nume client");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 173, 75, 20);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNumeProdus = new JLabel("Nume produs");
        lblNumeProdus.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNumeProdus.setBounds(10, 213, 84, 20);
        frame.getContentPane().add(lblNumeProdus);

        textField = new JTextField();
        textField.setBounds(104, 249, 134, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textID = new JTextField();
        textID.setColumns(10);
        textID.setBounds(104, 289, 134, 30);
        frame.getContentPane().add(textID);

        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblId.setBounds(10, 292, 84, 20);
        frame.getContentPane().add(lblId);

        JLabel lblCantitate = new JLabel("Cantitate");
        lblCantitate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCantitate.setBounds(10, 252, 84, 20);
        frame.getContentPane().add(lblCantitate);

        JLabel lblNewLabel_1 = new JLabel("Ce dorim");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setBounds(105, 342, 88, 20);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnAdaugaComanda = new JButton("Adauga comanda");
        btnAdaugaComanda.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdaugaComanda.setBounds(45, 372, 212, 26);
        frame.getContentPane().add(btnAdaugaComanda);

        JButton btnEditeazaComanda = new JButton("Editeaza comanda");

        btnEditeazaComanda.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEditeazaComanda.setBounds(45, 414, 212, 26);
        frame.getContentPane().add(btnEditeazaComanda);

        JButton btnStergeComanda = new JButton("Sterge comanda");
        btnStergeComanda.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStergeComanda.setBounds(45, 454, 212, 26);
        frame.getContentPane().add(btnStergeComanda);

        JButton btnBack = new JButton("<-");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBack.setBounds(10, 538, 60, 15);
        frame.getContentPane().add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainFereastra();
            }
        });
        btnAdaugaComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ok=1;
                Produs p=new Produs();
                try {
                    p = produsBLL.findProductByName(comboBox_1.getSelectedItem().toString());
                }catch (Exception thr){
                    ok=0;
                    JOptionPane.showMessageDialog(null, "Cantitatea ceruta este mai mare decat cea de pe stoc");
                }
                if(ok==1){Client c=clientBLL.findClientByName(comboBox.getSelectedItem().toString());
               comandaBLL.adaugareComandaBLL(c.getId(), p.getId(),Integer.parseInt(textField.getText()));
                //BillBLL billBLL=new BillBLL();
                //billBLL.insertBill();
                table=setareTabel();
                scrollPane.setViewportView(table);}
            }
        });
        btnStergeComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ok=1;
                try{
                    comandaBLL.deleteOrder(Integer.parseInt(textID.getText()));
                }catch (Exception ex){
                    ok=0;
                }
                table=setareTabel();
                scrollPane.setViewportView(table);
            }
        });
        btnEditeazaComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ok=1;
                Produs p=new Produs();
                try {
                    p = produsBLL.findProductByName(comboBox_1.getSelectedItem().toString());
                }catch (Exception thr){
                    ok=0;
                    JOptionPane.showMessageDialog(null, "Cantitatea ceruta este mai mare decat cea de pe stoc");
                }
                if(ok==1){Client c=clientBLL.findClientByName(comboBox.getSelectedItem().toString());
                    comandaBLL.updateComandaBLL(Integer.parseInt(textID.getText()),c.getId(), p.getId(),Integer.parseInt(textField.getText()));
                    table=setareTabel();
                    scrollPane.setViewportView(table);}
            }
        });
    }
    /**
     * Parcurge tabelul cu ajutorul reflexiei si adauga in tabel coloanele si datele din ele
     * @return un obiect de tipul JTable
     */
    public JTable setareTabel(){
        List<Comanda> comenzi = comandaBLL.findAllOrders();
        ArrayList<String> fieldsList = new ArrayList<>();
        ReflectionEx.getFields(fieldsList, comenzi.get(0));
        String[] fields = new String[fieldsList.size()];
        int i = 0;
        for (String field : fieldsList) {
            fields[i++] = field;
        }

        Object[][] data = new Object[comenzi.size()][fieldsList.size()];
        i = 0;
        for (Comanda comanda : comenzi) {
            ArrayList<Object> obj = new ArrayList<>();
            ReflectionEx.getValues(obj, comanda);
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


