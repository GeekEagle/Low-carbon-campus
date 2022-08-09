import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class MainFrame extends JFrame{
    private JLabel yonghuming;
    private JLabel mima;
    public JTextField  usernamelog;
    public JPasswordField  passwordlog;
    private Dimension size;
    public First firstpage;
    private JPanel btnpanel;
    public static final String LOGIN = "登录";
    public static final String EXIT = "退出";
    public static final String CHAPASS = "修改密码";


    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception evt){}
        MainFrame frame = new MainFrame();
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent E){
                System.exit(0);
            }
        });
        frame.setLocation(1000,300);
        frame.setSize(500, 420);
        frame.setVisible(true);
    }

    public MainFrame() {
        super("碳排放数据库");
        size = new Dimension(800,500);
        setloginpanel();
    }

    public void setloginpanel(){
        yonghuming = new JLabel("用户名");
        mima = new JLabel("密码");
        usernamelog = new JTextField(20);
        passwordlog = new JPasswordField(20);
        JButton login = new JButton(LOGIN);
        JButton exit = new JButton(EXIT);
        JButton chapass = new JButton(CHAPASS);
        Server btn = new Server(usernamelog,passwordlog);
        login.addActionListener(btn);
        exit.addActionListener(btn);
        chapass.addActionListener(btn);

        btnpanel = new JPanel();GridBagLayout gridbag = new GridBagLayout();
        btnpanel.setLayout(gridbag);GridBagConstraints gbc = new GridBagConstraints();
        btnpanel.add(yonghuming);btnpanel.add(usernamelog);btnpanel.add(mima);btnpanel.add(passwordlog);
        btnpanel.add(login);btnpanel.add(exit);

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;
        gbc.gridx = 0;gbc.gridy = 0;gridbag.setConstraints(yonghuming, gbc);
        gbc.gridx = 1;gbc.gridy = 0;gridbag.setConstraints(usernamelog, gbc);
        gbc.gridx = 0;gbc.gridy = 1;gridbag.setConstraints(mima, gbc);
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(passwordlog, gbc);

        gbc.insets.left = 2; gbc.insets.right = 2; gbc.insets.top = 15;
        gbc.gridx = 0; gbc.gridy = 5;gridbag.setConstraints(login, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 5;gridbag.setConstraints(exit, gbc);
        gbc.gridx = 2; gbc.gridy = 5;gridbag.setConstraints(chapass, gbc);
        this.add(btnpanel);
    }

}
