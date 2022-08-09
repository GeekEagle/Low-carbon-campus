import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;

class First extends JFrame implements ActionListener {
    private JFrame frame;
    emission emi;
    select checkout;
    JButton settraffic,setwater,setgarbage,setlight,check;
    public static final String TRAFFIC = "�޸Ľ�̼ͨ�ŷ�ֵ";
    public static final String WATER = "�޸ĸ���ˮ̼�ŷ�ֵ";
    public static final String GARBAGE = "�޸���������̼�ŷ�ֵ";
    public static final String LIGHT = "�޸�����̼�ŷ�ֵ";
    public static final String CHECK = "��ѯ����";

    public First(){
        settraffic = new JButton(TRAFFIC);setwater = new JButton(WATER);setgarbage = new JButton(GARBAGE);
        setlight = new JButton(LIGHT);check = new JButton(CHECK);

        frame = new JFrame();
        frame.setTitle("��ҳ");
        frame.setLocation(1000,300);
        frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout();
        frame.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 5;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(settraffic, gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(setwater, gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(setgarbage, gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(setlight, gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(check, gbc);
        frame.add(settraffic);frame.add(setwater);frame.add(setgarbage);
        frame.add(setlight);frame.add(check);
        frame.setVisible(true);
    }

    public void login(JTextField username){
        frame.setTitle("��ӭ�㣬"+username.getText().trim());
        settraffic.addActionListener(this);setwater.addActionListener(this);
        setgarbage.addActionListener(this);
        setlight.addActionListener(this);check.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(TRAFFIC)) {
            emi = new traffic();
            emi.changeparm();
        }
        else if(e.getActionCommand().equals(WATER)){
            emi = new water();
            emi.changeparm();
        }
        else if(e.getActionCommand().equals(GARBAGE)){
            emi = new garbage();
            emi.changeparm();
        }
        else if(e.getActionCommand().equals(LIGHT)) {
            emi = new light();
            emi.changeparm();
        }
        else if(e.getActionCommand().equals(CHECK)){
            checkout = new select();
            checkout.addbut();
        }
    }
}
