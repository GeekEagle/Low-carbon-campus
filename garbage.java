import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;

public class garbage implements emission, ActionListener {

    public JTextField yeardate,rate,carbonint,city;
    private JLabel yeardatelabel,typelabel,ratelabel,carbonintlabel,citylabel;
    private JComboBox typeqwq;
    public JButton submitcity,submitpar,setcity,calcaremi;
    public static final String SUBMIT = "提交";
    public static final String SETCITY = "设置城市参数";
    public static final String CALEMI = "设置计算碳排放参数";
    public int popu;
    public double avgpro;
    JTextField chacity,population,garproduct;
    JLabel chacitylabel,populationlabel,garproductlabel;

    /*public static void main(String args[]){
        garbage qwq = new garbage();
        qwq.changeparm();
    }*/

    public void changeparm(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        setcity = new JButton(SETCITY); calcaremi = new JButton(CALEMI);
        setcity.setFont(fn);calcaremi.setFont(fn);
        setcity.addActionListener(this);calcaremi.addActionListener(this);

        JFrame frame; frame = new JFrame();
        frame.setTitle("垃圾处理碳排放设置界面");
        frame.setLocation(1000,300);
        frame.setSize(400,200);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 5; gbc.insets.right = 5;
        gbc.insets.top = 5;gbc.insets.bottom = 5;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(setcity,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(calcaremi,gbc);
        frame.add(setcity);frame.add(calcaremi);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int int1,qwq;
        JFrame frame = new JFrame();
        boolean show1,show2;
        double num1,num2,num3,num4,num5,num6;
        String str1,str2,str3,SQL;
        if (e.getSource()==setcity)
            chacity();
        else if(e.getSource()==calcaremi)
            calculateframe();
        else if(e.getSource()==submitcity){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                str1 = chacity.getText().trim(); int1 = Integer.parseInt(population.getText().trim());
                num1 = Double.parseDouble(garproduct.getText().trim());
                show1 = findcity(str1);
                if(show1 == true) SQL = "update citygarinfo set populationinfo='"+int1+"',avgprogar='"+num1+
                        "' where city='"+str1+"';";
                else SQL = "insert into citygarinfo values('"+str1+"','"+int1+"','"+num1+"');";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "城市信息修改成功");
            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("submit city\n");
            }
        }
        else if(e.getSource()==submitpar){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                str1 = city.getText().trim(); int1 = Integer.parseInt(yeardate.getText().trim());
                str2 = typeqwq.getSelectedItem().toString().trim();
                str3 = rate.getText().trim();
                num1 = Double.parseDouble(str3.replace("%",""))/100;
                num2 = Double.parseDouble(carbonint.getText().trim());
                show1 = findcity(str1);
                if(!show1) {
                    JOptionPane.showMessageDialog(frame, "无此城市");
                    return;
                }
                num3 = Double.parseDouble(String.format("%.2f",popu*avgpro*num1*num2/1000));
                show2 = finddata(str1,int1,str2);
                if(show2 == true) SQL = "update garinfo set rate='"+str3+"',carint='"+num2+"',carbonemi='"+num3+
                        "' where city='"+str1+"'and yeardate='"+int1+"' and typeinfo='"+str2+"';";
                else SQL = "insert into garinfo values('"+str1+"','"+int1+"','"+str2+"','"+str3+"','"+num2+"','"+num3+"');";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "城市信息修改成功");
            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("submit parameter\n");
            }
        }
    }

    public void chacity(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        JFrame frchacity = new JFrame();
        frchacity.setTitle("垃圾处理碳排放城市信息设置");
        chacitylabel = new JLabel("城市名"); populationlabel = new JLabel("区域人口数量");
        garproductlabel = new JLabel("人均垃圾产生量"); chacity = new JTextField(20);
        population = new JTextField(20); garproduct = new JTextField(20);
        submitcity = new JButton(SUBMIT);submitcity.addActionListener(this);chacitylabel.setFont(fn);
        populationlabel.setFont(fn);garproductlabel.setFont(fn);chacity.setFont(fn);
        population.setFont(fn);garproduct.setFont(fn);submitcity.setFont(fn);

        frchacity.setLocation(1000,300);
        frchacity.setSize(700,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frchacity.setLayout(gridbag);
        gbc.insets.left = 5; gbc.insets.right = 5;
        gbc.insets.top = 5;gbc.insets.bottom = 5;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(chacitylabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(chacity,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(populationlabel,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(population,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(garproductlabel,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(garproduct,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(submitcity,gbc);

        frchacity.add(chacitylabel);frchacity.add(chacity);frchacity.add(populationlabel);frchacity.add(population);
        frchacity.add(garproductlabel);frchacity.add(garproduct);frchacity.add(submitcity);
        frchacity.setVisible(true);
    }

    public void calculateframe(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        citylabel = new JLabel("城市");yeardatelabel = new JLabel("年份");typelabel = new JLabel("分类");
        ratelabel = new JLabel("占比"); carbonintlabel = new JLabel("碳排放强度");citylabel.setFont(fn);
        typelabel.setFont(fn);yeardatelabel.setFont(fn);ratelabel.setFont(fn);carbonintlabel.setFont(fn);
        typeqwq = new JComboBox();typeqwq.setFont(fn);typeqwq.addItem("卫生填埋"); typeqwq.addItem("焚烧");typeqwq.addItem("其他");
        yeardate = new JTextField(20);rate = new JTextField(20); carbonint = new JTextField(20);
        city = new JTextField(20); submitpar = new JButton(CALEMI);submitpar.addActionListener(this);
        yeardate.setFont(fn);rate.setFont(fn);carbonint.setFont(fn);city.setFont(fn);submitpar.setFont(fn);

        JFrame caldata = new JFrame();
        caldata.setTitle("垃圾处理碳排放参数设置");
        caldata.setLocation(1000,300);
        caldata.setSize(800,400);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        caldata.setLayout(gridbag);
        gbc.insets.left = 7; gbc.insets.right = 7;
        gbc.insets.top = 7;gbc.insets.bottom = 7;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(yeardatelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(yeardate,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(typelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(typeqwq,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(ratelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(rate,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(carbonintlabel,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(carbonint,gbc);
        gbc.gridx = 1;gbc.gridy = 5;gridbag.setConstraints(citylabel,gbc);
        gbc.gridx = 2;gbc.gridy = 5;gridbag.setConstraints(city,gbc);
        gbc.gridx = 1;gbc.gridy = 6;gridbag.setConstraints(submitpar,gbc);

        caldata.add(yeardatelabel);caldata.add(yeardate);caldata.add(typelabel);caldata.add(typeqwq);
        caldata.add(ratelabel);caldata.add(rate);caldata.add(carbonintlabel);caldata.add(carbonint);
        caldata.add(citylabel);caldata.add(city);caldata.add(submitpar);
        caldata.setVisible(true);
    }

    public boolean findcity(String city){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            SQL = "select * from citygarinfo where city='"+city+"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
                popu = rs.getInt(2);
                avgpro = rs.getDouble(3);
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

    public boolean finddata(String city,int year,String type){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            SQL = "select * from garinfo where city='"+city+"'and yeardate= '"+year+"'and typeinfo='"+type +"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
                int i = rs.getInt(2);
                System.out.println(i);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("find data\n");
        }
        return false;
    }
}
