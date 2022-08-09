import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class traopreate implements ActionListener{
    public JButton back = new JButton("返回");
    public JButton changecity,addcity,chasocveh,charailveh;
    public JButton chataxiveh,chareneweng,chabusveh,chaemipar;
    public JLabel label1,label2,label3,label4;
    public JLabel label5,label6,label7,label8;
    public JTextField field1,field2,field3,field4;
    public JTextField field5,field6,field7,field8;
    public JComboBox combo1;
    public JFrame frame;
    public String city;
    public int popinfo;
    public double caremipar,avgcarposs,avgyearmile;
    public double avgbusnum,avgdaybusmile,avgtaxinum,avgyeartaximile;
    tracalsum calsum;

    public void setcityinfo(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        changecity = new JButton("提交");
        changecity.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("所在城市"); label2 = new JLabel("区域人口数量");
        label3 = new JLabel("市人均汽车保有量"); label4 = new JLabel("市均汽车年行驶里程");
        label5 = new JLabel("市人均公交车数量"); label6 = new JLabel("市日均公交里程");
        label7 = new JLabel("市人均出租车保有量"); label8 = new JLabel("市出租车年均里程");
        field1 = new JTextField(20); field2 = new JTextField(20);
        field3 = new JTextField(20); field4 = new JTextField(20);
        field5= new JTextField(20); field6 = new JTextField(20);
        field7 = new JTextField(20); field8 = new JTextField(20);
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);label4.setFont(fn);
        label5.setFont(fn);label6.setFont(fn);label7.setFont(fn);label8.setFont(fn);
        field1.setFont(fn);field2.setFont(fn);field3.setFont(fn);field4.setFont(fn);
        field5.setFont(fn);field6.setFont(fn);field7.setFont(fn);field8.setFont(fn);

        frame = new JFrame();
        frame.setTitle("修改城市信息");
        frame.setLocation(1000,300);
        frame.setSize(900,400);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(field2,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(label4,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(field4,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(label5,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(field5,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(label6,gbc);
        gbc.gridx = 4;gbc.gridy = 3;gridbag.setConstraints(field6,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(label7,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(field7,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(label8,gbc);
        gbc.gridx = 4;gbc.gridy = 4;gridbag.setConstraints(field8,gbc);
        gbc.gridx = 2;gbc.gridy = 5;gridbag.setConstraints(changecity,gbc);
        gbc.gridx = 3;gbc.gridy = 5;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(field2);
        frame.add(label3);frame.add(field3);frame.add(label4);frame.add(field4);
        frame.add(label5);frame.add(field5);frame.add(label6);frame.add(field6);
        frame.add(label7);frame.add(field7);frame.add(label8);frame.add(field8);
        frame.add(changecity);frame.add(back);
        frame.setVisible(true);
    }
    public void addcity(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        addcity = new JButton("提交");
        addcity.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("所在城市"); label2 = new JLabel("区域人口数量");
        label3 = new JLabel("市人均汽车保有量"); label4 = new JLabel("市均汽车年行驶里程");
        label5 = new JLabel("市人均公交车数量"); label6 = new JLabel("市日均公交里程");
        label7 = new JLabel("市人均出租车保有量"); label8 = new JLabel("市出租车年均里程");
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);label4.setFont(fn);
        label5.setFont(fn);label6.setFont(fn);label7.setFont(fn);label8.setFont(fn);
        field1 = new JTextField(20); field2 = new JTextField(20);
        field3 = new JTextField(20); field4 = new JTextField(20);
        field5= new JTextField(20); field6 = new JTextField(20);
        field7 = new JTextField(20); field8 = new JTextField(20);
        field1.setFont(fn);field2.setFont(fn);field3.setFont(fn);field4.setFont(fn);
        field5.setFont(fn);field6.setFont(fn);field7.setFont(fn);field8.setFont(fn);

        frame = new JFrame();
        frame.setTitle("添加城市信息");
        frame.setLocation(1000,300);
        frame.setSize(900,400);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(field2,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(label4,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(field4,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(label5,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(field5,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(label6,gbc);
        gbc.gridx = 4;gbc.gridy = 3;gridbag.setConstraints(field6,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(label7,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(field7,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(label8,gbc);
        gbc.gridx = 4;gbc.gridy = 4;gridbag.setConstraints(field8,gbc);
        gbc.gridx = 2;gbc.gridy = 5;gridbag.setConstraints(addcity,gbc);
        gbc.gridx = 3;gbc.gridy = 5;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(field2);
        frame.add(label3);frame.add(field3);frame.add(label4);frame.add(field4);
        frame.add(label5);frame.add(field5);frame.add(label6);frame.add(field6);
        frame.add(label7);frame.add(field7);frame.add(label8);frame.add(field8);
        frame.add(addcity);frame.add(back);
        frame.setVisible(true);
    }
    public void setemipar(String city){
        this.city = city;
        chaemipar = new JButton("提交");
        chaemipar.addActionListener(this); back.addActionListener(this);
        Font fn = new Font("宋体",Font.PLAIN,20);
        label1 = new JLabel("年份"); label2 = new JLabel("碳排放因子");
        field1 = new JTextField(30); field2 = new JTextField(30);
        label1.setFont(fn);label2.setFont(fn);field1.setFont(fn); field2.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置碳排放因子");
        frame.setLocation(1000,300);
        frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field2,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(chaemipar,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(field2);
        frame.add(chaemipar);frame.add(back);
        frame.setVisible(true);
    }
    public void chasocveh(String city){
        this.city = city;
        Font fn = new Font("宋体",Font.PLAIN,20);
        chasocveh = new JButton("提交");
        chasocveh.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("年份"); label2 = new JLabel("车辆类型");
        label3 = new JLabel("占比"); label4 = new JLabel("油（电）耗强度");
        label5 = new JLabel("碳排放强度");
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);label4.setFont(fn);
        label5.setFont(fn);
        field1 = new JTextField(20); combo1 = new JComboBox();
        combo1.addItem("燃油车"); combo1.addItem("电动车");
        field3 = new JTextField(20); field4 = new JTextField(20);
        field5= new JTextField(20);
        field1.setFont(fn);combo1.setFont(fn);field3.setFont(fn);field4.setFont(fn);
        field5.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置社会车辆参数");
        frame.setLocation(1000,300);
        frame.setSize(800,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(combo1,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(label4,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(field4,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(label5,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(field5,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(chasocveh,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(combo1);
        frame.add(label3);frame.add(field3);frame.add(label4);frame.add(field4);
        frame.add(label5);frame.add(field5);frame.add(chasocveh);frame.add(back);
        frame.setVisible(true);
    }
    public void charailveh(String city){
        this.city = city;
        Font fn = new Font("宋体",Font.PLAIN,20);
        charailveh = new JButton("提交");
        charailveh.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("年份"); label2 = new JLabel("能耗强度");
        label3 = new JLabel("年行驶里程");
        field1 = new JTextField(20); field2 = new JTextField(20);
        field3 = new JTextField(20);
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);
        field1.setFont(fn);field2.setFont(fn);field3.setFont(fn);charailveh.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置轨道交通参数");
        frame.setLocation(1000,300);
        frame.setSize(800,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(field2,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(charailveh,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(field2);
        frame.add(label3);frame.add(field3);frame.add(charailveh);frame.add(back);
        frame.setVisible(true);
    }
    public void chabusveh(String city){
        this.city = city;
        Font fn = new Font("宋体",Font.PLAIN,20);
        chabusveh = new JButton("提交");
        chabusveh.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("年份"); label2 = new JLabel("车辆类型");
        label3 = new JLabel("占比"); label4 = new JLabel("油（电）(CNG)能耗强度");
        label5 = new JLabel("碳排放强度");
        field1 = new JTextField(20); combo1 = new JComboBox();
        combo1.addItem("燃油车"); combo1.addItem("混合动力车");
        combo1.addItem("天然气车");combo1.addItem("电动车");
        field3 = new JTextField(20); field4 = new JTextField(20);
        field5= new JTextField(20);
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);label4.setFont(fn);
        label5.setFont(fn);field1.setFont(fn);combo1.setFont(fn);field3.setFont(fn);
        field4.setFont(fn);field5.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置公交车参数");
        frame.setLocation(1000,300);
        frame.setSize(800,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(combo1,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(label4,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(field4,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(label5,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(field5,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(chabusveh,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(combo1);
        frame.add(label3);frame.add(field3);frame.add(label4);frame.add(field4);
        frame.add(label5);frame.add(field5);frame.add(chabusveh);frame.add(back);
        frame.setVisible(true);
    }
    public void chataxiveh(String city){
        this.city = city;
        Font fn = new Font("宋体",Font.PLAIN,20);
        chataxiveh = new JButton("提交");
        chataxiveh.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("年份"); label2 = new JLabel("车辆类型");
        label3 = new JLabel("占比"); label4 = new JLabel("油（电）耗强度");
        label5 = new JLabel("碳排放强度");
        field1 = new JTextField(20); combo1 = new JComboBox();
        combo1.addItem("燃油车"); combo1.addItem("电动车");
        field3 = new JTextField(20); field4 = new JTextField(20);
        field5= new JTextField(20);
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);label4.setFont(fn);
        label5.setFont(fn);field1.setFont(fn);combo1.setFont(fn);field3.setFont(fn);
        field4.setFont(fn);field5.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置出租车参数");
        frame.setLocation(1000,300);
        frame.setSize(800,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(combo1,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(label4,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(field4,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(label5,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(field5,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(chataxiveh,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(combo1);
        frame.add(label3);frame.add(field3);frame.add(label4);frame.add(field4);
        frame.add(label5);frame.add(field5);frame.add(chataxiveh);frame.add(back);
        frame.setVisible(true);
    }
    public void chareneweng(String city){
        this.city = city;
        Font fn = new Font("宋体",Font.PLAIN,20);
        chareneweng = new JButton("提交");
        chareneweng.addActionListener(this); back.addActionListener(this);
        label1 = new JLabel("年份"); label2 = new JLabel("分布式光伏分配电力");
        label3 = new JLabel("垃圾焚烧电厂分配电力");
        field1 = new JTextField(20); field2 = new JTextField(20);
        field3 = new JTextField(20);
        label1.setFont(fn);label2.setFont(fn);label3.setFont(fn);
        field1.setFont(fn);field2.setFont(fn);field3.setFont(fn);

        frame = new JFrame();
        frame.setTitle("设置可再生能源参数");
        frame.setLocation(1000,300);
        frame.setSize(900,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 7;
        gbc.insets.right = 7;
        gbc.insets.top = 7;
        gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(label1,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(field1,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(label2,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(field2,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(label3,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(field3,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(chareneweng,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(back,gbc);

        frame.add(label1);frame.add(field1);frame.add(label2);frame.add(field2);
        frame.add(label3);frame.add(field3);frame.add(chareneweng);frame.add(back);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int int1,int2,int3,qwq;
//        JFrame frame = new JFrame();
        boolean show1,show2,lele;
        double num1,num2,num3,num4,num5,num6;
        String str1,str2,str3;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if(e.getSource() == changecity){    //修改城市
                str1 = field1.getText().trim();int1 = Integer.parseInt(field2.getText().trim());
                num1 = Double.parseDouble(field3.getText().trim());num2 = Double.parseDouble(field4.getText().trim());
                num3 = Double.parseDouble(field5.getText().trim());num4 = Double.parseDouble(field6.getText().trim());
                num5 = Double.parseDouble(field7.getText().trim());num6 = Double.parseDouble(field8.getText().trim());
                lele = findcity(str1);
                if(lele==false){
                    JOptionPane.showMessageDialog(frame, "无此城市");
                    return;
                }
                String SQL = "update 城市信息 set populationinfo='"+int1+"',avgcarposs='"+num1+"',avgyearmile='"+num2+
                        "',avgbusnum='"+num3+"',avgdaybusmile='"+num4+"',avgtaxinum='"+num5+
                        "',avgyeartaximile='"+num6+"' where city='"+str1+"';";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
            }
            else if(e.getSource()==addcity){  //添加城市
                str1 = field1.getText().trim();int1 = Integer.parseInt(field2.getText().trim());
                num1 = Double.parseDouble(field3.getText().trim());num2 = Double.parseDouble(field4.getText().trim());
                num3 = Double.parseDouble(field5.getText().trim());num4 = Double.parseDouble(field6.getText().trim());
                num5 = Double.parseDouble(field7.getText().trim());num6 = Double.parseDouble(field8.getText().trim());
                lele = findcity(str1);
                if(lele==true){
                    JOptionPane.showMessageDialog(frame, "城市已存在，不可添加");
                    return;
                }
                String SQL = "insert into 城市信息 values('"+str1+"','"+int1+"','"+num1+"','"+num2+"','"+num3+"','"+num4+"','"+num5+"','"+num6+"');";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "添加成功");
            }
            else if (e.getSource()==back){
                frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }
            else if(e.getSource()==chaemipar){
                int1 = Integer.parseInt(field1.getText().trim());
                String SQL;
                show2 = findout(true);
                caremipar = Double.parseDouble(field2.getText().trim());
                if(show2 == true) SQL = "update 国家电网碳排放因子 set caremipar='"+caremipar+"' where city='"+city+"' and Yeardate='"+int1+"';";
                else SQL = "insert into 国家电网碳排放因子 values('"+int1+"','"+city+"','"+caremipar+"');";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
            }   //碳排放因子
            else{
                show1 = findout(false);
                if(show1==false) {
                    JOptionPane.showMessageDialog(frame, "请先设置该城市该年的碳排放因子");
                    return;
                }
                if(e.getSource()==chasocveh){  //社会车辆
                    str1 = combo1.getSelectedItem().toString().trim();int1 = Integer.parseInt(field1.getText().trim());
                    str3 = field3.getText().trim();
                    num1 = Double.parseDouble(str3.replace("%",""))/100;num2 = Double.parseDouble(field4.getText().trim());
                    String SQL;
                    if(str1.equals("燃油车")){
                        num3 = Double.parseDouble(field5.getText().trim());
                        double gascons,equelec,carbonemi;
                        gascons = Double.parseDouble(String.format("%.2f",popinfo*avgcarposs*num1*(avgyearmile/100*num2)));//油耗
                        equelec = Double.parseDouble(String.format("%.2f",gascons*0.725*11.9639));
                        carbonemi = Double.parseDouble(String.format("%.2f",gascons*num3/1000));
                        show2 = findrecord(city,int1,str1,"社会车辆");
                        if(show2 == true) SQL = "update 社会车辆 set rate='"+str3+"',gasconsint='"+num2+"',emissionint='"+num3+
                                "',gascons='"+gascons+"',equelec='"+equelec+"',eleconsint='"+0+"',elecons='"+0+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"' and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 社会车辆 values('"+int1+"','"+city+"','"+str1+"','"+str3+"','"+num2+"','"+num3+"','"+
                                gascons+"','"+equelec+"',null,null,'"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    else if(str1.equals("电动车")){
                        double elecons,carbonemi;
                        elecons = Double.parseDouble(String.format("%.2f",popinfo*avgcarposs*num1*(avgyearmile/100*num2)));//油耗
                        carbonemi = Double.parseDouble(String.format("%.2f",elecons*caremipar/1000));
                        show2 = findrecord(city,int1,str1,"社会车辆");
                        if(show2 == true) SQL = "update 社会车辆 set rate='"+str3+"',gasconsint='"+0+"',emissionint='"+0+
                                "',gascons='"+0+"',equelec='"+0+"',eleconsint='"+num2+"',elecons='"+elecons+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"'and Yeardate='"+int1+"'and vehtype='"+str1+"';";
                        else SQL = "insert into 社会车辆 values('"+int1+"','"+city+"','"+str1+"','"+str3+
                                "',0,0,0,0,'"+num2+"','"+elecons+"','"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    calsum = new tracalsum(city,int1,str1);
                    calsum.calsum("社会车辆");
                }   //社会车辆

                else if(e.getSource()==charailveh){            //轨道交通
                    int1 = Integer.parseInt(field1.getText().trim());num1 = Double.parseDouble(field2.getText().trim());
                    num2 = Double.parseDouble(field3.getText().trim());
                    String SQL;
                    double elecons,carbonemi;
                    elecons = Double.parseDouble(String.format("%.2f",num1*num2/100));
                    carbonemi = Double.parseDouble(String.format("%.2f",elecons*caremipar/1000));
                    show2 = findrecord(city,int1,null,"轨道交通");
                    if(show2 == true) SQL = "update 轨道交通 set energyconsint='"+num1+"',avgmileage='"+num2+"',elecons='"+elecons+
                            "',carbonemi='"+carbonemi+"' where city='"+city+"' and Yeardate='"+int1+"';";
                    else SQL = "insert into 轨道交通 values('"+int1+"','"+city+"','"+num1+"','"+num2+"','"+elecons+"','"+carbonemi+"');";
                    stmt = con.prepareStatement(SQL);
                    qwq = stmt.executeUpdate();
                    if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    calsum = new tracalsum(city,int1,null);
                    calsum.totalsum();
                }  //轨道交通

                else if(e.getSource()==chataxiveh){         //出租车
                    str1 = combo1.getSelectedItem().toString().trim();int1 = Integer.parseInt(field1.getText().trim());
                    str3 = field3.getText().trim();num1 = Double.parseDouble(str3.replace("%",""))/100;
                    num2 = Double.parseDouble(field4.getText().trim());
                    String SQL;
                    if(str1.equals("燃油车")){
                        num3 = Double.parseDouble(field5.getText().trim());
                        double gascons,equelec,carbonemi;
                        gascons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgtaxinum*avgyeartaximile*num1/100*num2));//油耗
                        equelec = Double.parseDouble(String.format("%.2f",gascons*0.725*11.9723));
                        carbonemi = Double.parseDouble(String.format("%.2f",gascons*num3/1000));
                        show2 = findrecord(city,int1,str1,"出租车");
                        if(show2 == true) SQL = "update 出租车 set rate='"+str3+"',gasconsint='"+num2+"',emissionint='"+num3+
                                "',gascons='"+gascons+"',equelec='"+equelec+"',eleconsint='"+0+"',elecons='"+0+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"' and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 出租车 values('"+int1+"','"+city+"','"+str1+"','"+str3+"','"+num2+"','"+num3+"','"+
                                gascons+"','"+equelec+"',null,null,'"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    else if(str1.equals("电动车")){
                        double elecons,carbonemi;
                        elecons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgtaxinum*avgyeartaximile*num1/100*num2));//油耗
                        carbonemi = Double.parseDouble(String.format("%.2f",elecons*caremipar/1000));
                        show2 = findrecord(city,int1,str1,"出租车");
                        if(show2 == true) SQL = "update 出租车 set rate='"+str3+"',gasconsint='"+null+"',emissionint='"+null+
                                "',gascons='"+0+"',equelec='"+null+"',eleconsint='"+num2+"',elecons='"+elecons+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"'and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 出租车 values('"+int1+"','"+city+"','"+str1+"','"+str3+
                                "',null,null,null,null,'"+num2+"','"+elecons+"','"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    calsum = new tracalsum(city,int1,str1);
                    calsum.calsum("出租车");
                }   //出租车

                else if(e.getSource()==chareneweng){
                    int1 = Integer.parseInt(field1.getText().trim());
                    num2 = Double.parseDouble(field2.getText().trim());
                    num3 = Double.parseDouble(field3.getText().trim());

                    tracalsum calsum = new tracalsum(city,int1,null);
                    int2 = calsum.renewsum(num2,num3);
                    if(int2==1) JOptionPane.showMessageDialog(frame, "修改成功");
                    else JOptionPane.showMessageDialog(frame, "修改失败");
                }  //可再生能源替代

                else if(e.getSource()==chabusveh){
                    str1 = combo1.getSelectedItem().toString().trim();int1 = Integer.parseInt(field1.getText().trim());
                    str3 = field3.getText().trim();num1 = Double.parseDouble(str3.replace("%",""))/100;
                    num2 = Double.parseDouble(field4.getText().trim());

                    String SQL;
                    show2 = findrecord(city,int1,str1,"公交车");
                    if(str1.equals("燃油车")){
                        num3 = Double.parseDouble(field5.getText().trim());
                        double gascons,equelec,carbonemi;
                        gascons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgbusnum*148.29*365*num1/100*num2));//油耗
                        equelec = Double.parseDouble(String.format("%.2f",gascons*0.725*11.9723));
                        carbonemi = Double.parseDouble(String.format("%.2f",gascons*num3/1000));
                        if(show2 == true) SQL = "update 公交车 set rate='"+str3+"',gasconsint='"+num2+"',emissionint='"+num3+
                                "',gascons='"+gascons+"',equelec='"+equelec+"',eleconsint='"+0+"',elecons='"+0+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"' and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 公交车 values('"+int1+"','"+city+"','"+str1+"','"+str3+"','"+num2+"','"+num3+"','"+
                                gascons+"','"+equelec+"',0,0,'"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    else if(str1.equals("电动车")){
                        double elecons,carbonemi;
                        elecons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgbusnum*avgdaybusmile*365*num1/100*num2));//油耗
                        carbonemi = Double.parseDouble(String.format("%.2f",elecons*avgbusnum/1000));
                        if(show2 == true) SQL = "update 公交车 set rate='"+str3+"',gasconsint='"+0+"',emissionint='"+0+
                                "',gascons='"+0+"',equelec='"+0+"',eleconsint='"+num2+"',elecons='"+elecons+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"'and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 公交车 values('"+int1+"','"+city+"','"+str1+"','"+str3+
                                "',0,0,0,0,'"+num2+"','"+elecons+"','"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    else if(str1.equals("混合动力车")){
                        num3 = Double.parseDouble(field5.getText().trim());
                        double gascons,equelec,carbonemi;
                        gascons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgcarposs*avgdaybusmile*365*num1/100*num2));//油耗
                        equelec = Double.parseDouble(String.format("%.2f",gascons*0.725*11.9723));
                        carbonemi = Double.parseDouble(String.format("%.2f",gascons*num3/1000));
                        if(show2 == true) SQL = "update 公交车 set rate='"+str3+"',gasconsint='"+num2+"',emissionint='"+num3+
                                "',gascons='"+gascons+"',equelec='"+equelec+"',eleconsint='"+0+"',elecons='"+0+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"'and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 公交车 values('"+int1+"','"+city+"','"+str1+"','"+str3+"','"+num2+"','"+num3+"','"+
                                gascons+"','"+equelec+"',0,0,'"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    else if(str1.equals("天然气车")){
                        num3 = Double.parseDouble(field5.getText().trim());
                        double gascons,equelec,carbonemi;
                        gascons = Double.parseDouble(String.format("%.2f",popinfo/10000*avgcarposs*avgdaybusmile*365*num1/100*num2));//油耗
                        equelec = Double.parseDouble(String.format("%.2f",gascons*9.8804));
                        carbonemi = Double.parseDouble(String.format("%.2f",gascons*num3/1000));
                        if(show2 == true) SQL = "update 公交车 set rate='"+str3+"',gasconsint='"+num2+"',emissionint='"+num3+
                                "',gascons='"+gascons+"',equelec='"+equelec+"',eleconsint='"+0+"',elecons='"+0+
                                "',carbonemi='"+carbonemi+"' where city='"+city+"'and Yeardate='"+int1+"' and vehtype='"+str1+"';";
                        else SQL = "insert into 公交车 values('"+int1+"','"+city+"','"+str1+"','"+str3+"','"+num2+"','"+num3+"','"+
                                gascons+"','"+equelec+"',0,0,'"+carbonemi+"');";
                        stmt = con.prepareStatement(SQL);
                        qwq = stmt.executeUpdate();
                        if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
                    }
                    calsum = new tracalsum(city,int1,str1);
                    calsum.calsum("公交车");
                }    //公交车

            }
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
    }

    public boolean findrecord(String city,int int1,String vehtype,String table){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if(table.equals("社会车辆"))
                SQL = "select * from 社会车辆 where city = '" + city + "' and Yeardate='" + int1 + "' and vehtype='"+vehtype+"';";
            else if(table.equals("轨道交通"))
                SQL = "select * from 轨道交通 where city = '" + city + "' and Yeardate='" + int1 + "';";
            else if(table.equals("出租车"))
                SQL = "select * from 出租车 where city = '" + city + "' and Yeardate='" + int1 + "'and vehtype='"+vehtype+"';";
            else if(table.equals("公交车"))
                SQL = "select * from 公交车 where city = '" + city + "' and Yeardate='" + int1 + "'and vehtype='"+vehtype+"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
                int i = rs.getInt(1);
                System.out.println(i);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("findrecord\n");
        }
        return false;
    }

    public boolean findout(boolean input){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        JFrame frame = new JFrame();
        ResultSet rs = null;
        int int1,int2,int3;
        int1 = Integer.parseInt(field1.getText().trim());
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            String SQL = "select * from 国家电网碳排放因子 where city = '" + city + "'and Yeardate='" + int1 + "';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()) {
                caremipar= rs.getDouble(3);
                if (input) return true;
            }else return false;

            SQL = "select * from 城市信息 where city = '" + city + "';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()) {
                popinfo = rs.getInt(2); avgcarposs = rs.getDouble(3);
                avgyearmile = rs.getDouble(4); avgbusnum = rs.getDouble(5);
                avgdaybusmile = rs.getDouble(6);avgtaxinum = rs.getDouble(7);
                avgyeartaximile = rs.getDouble(8);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("find year error\n");
        }
        return true;
    }

    public boolean findcity(String city){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            SQL = "select * from 城市信息 where city='"+city+"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
                String qwq = rs.getString(1).trim();
                System.out.println(qwq);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("findcity\n");
        }
        return false;
    }
}