package start;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import presentation.MainFereastra;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFereastra window = new MainFereastra();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
