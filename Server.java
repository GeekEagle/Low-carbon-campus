import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server implements ActionListener{
    private JFrame frame = new JFrame();
    public First firstpage;
    public JTextField  usernamelog;
    public JPasswordField  passwordlog;
    public static final String LOGIN = "µÇÂ¼";
    public static final String EXIT = "ÍË³ö";
    public static final String CHAPASS = "ÐÞ¸ÄÃÜÂë";

    public Server(JTextField usernamelog,JPasswordField passwordlog){
        this.usernamelog = usernamelog;
        this.passwordlog = passwordlog;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(EXIT)) {
            System.exit(1);
        }
        else if (e.getActionCommand().equals(LOGIN)) {
            if (usernamelog.getText().length() == 0 || usernamelog.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "please input text");
                return;
            }
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // Establish the connection.
                //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                // Create and execute an SQL statement that returns some data.
                String SQL = "select userid,password" + " from µÇÂ¼ÐÅÏ¢±í" + " where userid='" + usernamelog.getText().trim() + "'and " +
                        "password='" + passwordlog.getText().trim()+"';";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    firstpage = new First();
                    firstpage.login(usernamelog);
                }
                else JOptionPane.showMessageDialog(frame, "ÕËºÅ»òÃÜÂë´íÎó");
            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("LOG IN\n");
            }
        }
    }
}
