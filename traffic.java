import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class traffic implements emission,ActionListener{
    public JFrame frame;
    public JTextField selcity;
    private JLabel citylabel;
    public JButton changecityinfo,addcityinfo,setsocialveh,setrailveh;
    public JButton setbusveh,setaxiveh,setreneweng,setemipar,back;
    public static final String CHANGECITY = "�޸ĳ�����Ϣ";
    public static final String ADDCITY = "���ӳ�����Ϣ";
    public static final String SOCVEH = "������ᳵ������";
    public static final String RAILVEH = "���ù����ͨ����";
    public static final String BUSVEH = "���ù���������";
    public static final String TAXIVEH = "���ó��⳵����";
    public static final String RENEWENG = "���ÿ�������Դ��̯����";
    public static final String EMIPAR = "����̼�ŷ�����";
    public static final String BACK = "����";

    /*public static void main(String args[]){
        traffic tra = new traffic();
        tra.changeparm();
    }*/

    public void changeparm(){
        Font fn = new Font("����",Font.PLAIN,20);
        citylabel = new JLabel("���������");citylabel.setFont(fn);
        selcity = new JTextField(20);selcity.setFont(fn);
        changecityinfo = new JButton(CHANGECITY); changecityinfo.addActionListener(this);//changecityinfo.setFont(fn);
        addcityinfo = new JButton(ADDCITY); addcityinfo.addActionListener(this);//addcityinfo.setFont(fn);
        setsocialveh = new JButton(SOCVEH); setsocialveh.addActionListener(this);//setsocialveh.setFont(fn);
        setrailveh = new JButton(RAILVEH); setrailveh.addActionListener(this);//setrailveh.setFont(fn);
        setbusveh = new JButton(BUSVEH); setbusveh.addActionListener(this);//setbusveh.setFont(fn);
        setaxiveh = new JButton(TAXIVEH); setaxiveh.addActionListener(this);//setaxiveh.setFont(fn);
        setreneweng = new JButton(RENEWENG); setreneweng.addActionListener(this);//setreneweng.setFont(fn);
        setemipar = new JButton(EMIPAR); setemipar.addActionListener(this);//setemipar.setFont(fn);
        back = new JButton(BACK); back.addActionListener(this);//back.setFont(fn);

        frame = new JFrame();
        frame.setTitle("��̼ͨ�ŷ����ý���");
        frame.setLocation(1000,300);
        frame.setSize(800,300);
        GridBagLayout gridbag = new GridBagLayout(); //���ò���
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(citylabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(selcity,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(changecityinfo,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(addcityinfo,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(setsocialveh,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(setrailveh,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(setbusveh,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(setaxiveh,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(setreneweng,gbc);
        gbc.gridx = 4;gbc.gridy = 3;gridbag.setConstraints(setemipar,gbc);

        frame.add(citylabel);frame.add(selcity);frame.add(changecityinfo);frame.add(addcityinfo);
        frame.add(setsocialveh);frame.add(setrailveh);frame.add(setbusveh);frame.add(setaxiveh);
        frame.add(setreneweng);frame.add(setemipar);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        boolean show;
        if (e.getActionCommand().equals(CHANGECITY)) {
            traopreate operation = new traopreate();
            operation.setcityinfo();
        }
        else if (e.getActionCommand().equals(ADDCITY)) {
            traopreate operation = new traopreate();
            operation.addcity();
        }
        else{
            if(selcity.getText().trim().equals(""))
                JOptionPane.showMessageDialog(frame, "���������");
            else if (findcity(selcity.getText().trim())==false)
                JOptionPane.showMessageDialog(frame, "�޴˳���");
                /*frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/

            else if (e.getActionCommand().equals(SOCVEH)) {
                traopreate operation = new traopreate();
                operation.chasocveh(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(RAILVEH)) {
                traopreate operation = new traopreate();
                operation.charailveh(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(BUSVEH)) {
                traopreate operation = new traopreate();
                operation.chabusveh(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(TAXIVEH)) {
                traopreate operation = new traopreate();
                operation.chataxiveh(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(RENEWENG)) {
                traopreate operation = new traopreate();
                operation.chareneweng(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(EMIPAR)) {
                traopreate operation = new traopreate();
                operation.setemipar(selcity.getText().trim());
            }
            else if (e.getActionCommand().equals(BACK)){
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
    }

    public boolean findcity(String city){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");

            String SQL = "select * from ������Ϣ where city = '" + city + "';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()) return true;
            else return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
        return true;
    }
}
