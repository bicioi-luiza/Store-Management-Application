package presentation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Fereastra principala
 * @author Bicioi Luiza
 */
public class MainFereastra {

    private JFrame frame;

    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public MainFereastra() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.getContentPane().setForeground(new Color(64, 128, 128));
        frame.setBounds(100, 100, 550, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        JLabel lbTitlu = new JLabel(" Orders Management");
        lbTitlu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lbTitlu.setBounds(136, 80, 265, 27);
        frame.getContentPane().add(lbTitlu);

        JButton btnClienti = new JButton("Clienti");
        btnClienti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientiFereastra();
                frame.setVisible(false);

            }
        });
        btnClienti.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnClienti.setBounds(124, 171, 277, 41);
        frame.getContentPane().add(btnClienti);

        JButton btnProduse = new JButton("Produse");
        btnProduse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProdusFereastra();
                frame.setVisible(false);
            }
        });
        btnProduse.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnProduse.setBounds(124, 246, 277, 41);
        frame.getContentPane().add(btnProduse);

        JButton btnComanda = new JButton("Comanda");
        btnComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ComandaFereastra();
                frame.setVisible(false);
            }
        });
        btnComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnComanda.setBounds(124, 324, 277, 41);
        frame.getContentPane().add(btnComanda);
    }
}
