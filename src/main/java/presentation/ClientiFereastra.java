package presentation;
import model.Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import bll.*;
import start.*;

/**
 * Fereastra clienti
 * @author Bicioi Luiza
 */
public class ClientiFereastra {

    private JFrame frame;
    private JTextField textID;
    private JTextField textNume;
    private JTextField textEmail;
    private JTextField textAdresa;
    private JTextField textVarsta;
    private JTable table;
    ClientBLL clientBLL = new ClientBLL();

    /**
     * Launch the application.
     */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientiFereastra window = new ClientiFereastra();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
    public ClientiFereastra() {
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
        JLabel lbTitlu = new JLabel("Clienti");
        lbTitlu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbTitlu.setBounds(336, 73, 108, 37);
        frame.getContentPane().add(lbTitlu);

        JLabel lblNewLabel = new JLabel("Date client");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(111, 156, 108, 20);
        frame.getContentPane().add(lblNewLabel);

        JLabel lbID = new JLabel("ID");
        lbID.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbID.setBounds(10, 203, 50, 20);
        frame.getContentPane().add(lbID);

        textID = new JTextField();
        textID.setBounds(65, 202, 212, 26);
        frame.getContentPane().add(textID);
        textID.setColumns(10);

        textNume = new JTextField();
        textNume.setColumns(10);
        textNume.setBounds(65, 238, 212, 26);
        frame.getContentPane().add(textNume);

        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(65, 274, 212, 26);
        frame.getContentPane().add(textEmail);

        textAdresa = new JTextField();
        textAdresa.setColumns(10);
        textAdresa.setBounds(65, 310, 212, 26);
        frame.getContentPane().add(textAdresa);

        textVarsta = new JTextField();
        textVarsta.setColumns(10);
        textVarsta.setBounds(65, 346, 212, 26);
        frame.getContentPane().add(textVarsta);

        JLabel lbNumE = new JLabel("Nume");
        lbNumE.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbNumE.setBounds(10, 239, 45, 20);
        frame.getContentPane().add(lbNumE);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbEmail.setBounds(10, 280, 45, 20);
        frame.getContentPane().add(lbEmail);

        JLabel lblAdresa = new JLabel("Adresa");
        lblAdresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAdresa.setBounds(10, 316, 45, 20);
        frame.getContentPane().add(lblAdresa);

        JLabel lblVarsta = new JLabel("Varsta");
        lblVarsta.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVarsta.setBounds(10, 352, 45, 20);
        frame.getContentPane().add(lblVarsta);

        JLabel lblNewLabel_1 = new JLabel("Ce dorim");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setBounds(125, 390, 88, 20);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnAdauga = new JButton("Adauga client");
        btnAdauga.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdauga.setBounds(65, 420, 212, 26);
        frame.getContentPane().add(btnAdauga);

        JButton btnEditeaza = new JButton("Editeaza client");
        btnEditeaza.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEditeaza.setBounds(65, 462, 212, 26);
        frame.getContentPane().add(btnEditeaza);

        JButton btnStergeClient = new JButton("Sterge client");
        btnStergeClient.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStergeClient.setBounds(65, 502, 212, 26);
        frame.getContentPane().add(btnStergeClient);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(305, 199, 449, 354);
        frame.getContentPane().add(scrollPane);

        table = new JTable();

        List<Client> clients = clientBLL.findAllClients();
        ArrayList<String> fieldsList = new ArrayList<>();
        ReflectionEx.getFields(fieldsList, clients.get(0));
        String[] fields = new String[fieldsList.size()];
        int i = 0;
        for (String field : fieldsList) {
            fields[i++] = field;
        }

        Object[][] data = new Object[clients.size()][fieldsList.size()];
        i = 0;
        for (Client client : clients) {
            ArrayList<Object> obj = new ArrayList<>();
            ReflectionEx.getValues(obj, client);
            int j = 0;
            for (Object o : obj) {
                data[i][j++] = o;
            }
            i++;
        }
        table = new JTable(data, fields);


        scrollPane.setViewportView(table);

        JLabel lblListaClienti = new JLabel("Lista clienti");
        lblListaClienti.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblListaClienti.setBounds(473, 156, 108, 20);
        frame.getContentPane().add(lblListaClienti);

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

        btnAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Client cl = new Client(textNume.getText(), textEmail.getText(), textAdresa.getText(), Integer.parseInt(textVarsta.getText()));
                clientBLL.insertClient(cl);
                List<Client> clients = clientBLL.findAllClients();
                ArrayList<String> fieldsList = new ArrayList<>();
                ReflectionEx.getFields(fieldsList, clients.get(0));
                String[] fields = new String[fieldsList.size()];
                int i = 0;
                for (String field : fieldsList) {
                    fields[i++] = field;
                }

                Object[][] data = new Object[clients.size()][fieldsList.size()];
                i = 0;
                for (Client client : clients) {
                    ArrayList<Object> obj = new ArrayList<>();
                    ReflectionEx.getValues(obj, client);
                    int j = 0;
                    for (Object o : obj) {
                        data[i][j++] = o;
                    }
                    i++;
                }

                table = new JTable(data, fields);
                scrollPane.setViewportView(table);
            }
        });

        btnEditeaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client cl = new Client(Integer.parseInt(textID.getText()), textNume.getText(), textEmail.getText(), textAdresa.getText(), Integer.parseInt(textVarsta.getText()));
                clientBLL.updateClientById(cl, cl.getId());
                table=setareTabel();
                scrollPane.setViewportView(table);

            }
        });

        btnStergeClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               // Client cl = new Client(Integer.parseInt(textID.getText()), textNume.getText(), textEmail.getText(), textAdresa.getText(), Integer.parseInt(textVarsta.getText()));
                clientBLL.deleteClient(Integer.parseInt(textID.getText()));
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
        List<Client> clients = clientBLL.findAllClients();
        ArrayList<String> fieldsList = new ArrayList<>();
        ReflectionEx.getFields(fieldsList, clients.get(0));
        String[] fields = new String[fieldsList.size()];
        int i = 0;
        for (String field : fieldsList) {
            fields[i++] = field;
        }

        Object[][] data = new Object[clients.size()][fieldsList.size()];
        i = 0;
        for (Client client : clients) {
            ArrayList<Object> obj = new ArrayList<>();
            ReflectionEx.getValues(obj, client);
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
