import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class select implements ActionListener {
    private JFrame frame;
    chaxun che;
    JButton settraffic,setwater,setgarbage,setlight;
    public static final String TRAFFIC = "��̼ͨ�ŷŲ�ѯ";
    public static final String WATER = "����ˮ̼�ŷŲ�ѯ";
    public static final String GARBAGE = "��������̼�ŷŲ�ѯ";
    public static final String LIGHT = "����̼�ŷŲ�ѯ";

    public select(){
        settraffic = new JButton(TRAFFIC);setwater = new JButton(WATER);
        setgarbage = new JButton(GARBAGE);setlight = new JButton(LIGHT);

        frame = new JFrame();
        frame.setTitle("��ҳ");
        frame.setLocation(1000,300);
        frame.setSize(600,200);
        GridBagLayout gridbag = new GridBagLayout();
        frame.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 5;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(settraffic, gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(setwater, gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(setgarbage, gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(setlight, gbc);

        frame.add(settraffic);frame.add(setwater);frame.add(setgarbage);
        frame.add(setlight);
        frame.setVisible(true);
    }
    public void addbut(){
        frame.setTitle("��ѯ����");
        settraffic.addActionListener(this);setwater.addActionListener(this);
        setgarbage.addActionListener(this);setlight.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(TRAFFIC)) {
            che = new chetraffic();
            che.checking();
        }
        else if(e.getActionCommand().equals(WATER)){
            che = new chewater();
            che.checking();
        }
        else if(e.getActionCommand().equals(GARBAGE)){
            che = new chegarbage();
            che.checking();
        }
        else if(e.getActionCommand().equals(LIGHT)) {
            che = new chelight();
            che.checking();
        }
    }
}
