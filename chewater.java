import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class chewater implements chaxun,ActionListener{
    public JButton providesys,regind,prind;
    public JFrame frame;
    public static final String WATER = "查看给水系统参数";
    public static final String GARBAGE = "查看地理污水处理厂参数";
    public static final String INDUSTRY = "查看自建污水处理厂参数";

    public void checking(){
        Font fn = new Font("宋体",Font.PLAIN,25);
        providesys = new JButton(WATER); regind = new JButton(GARBAGE);
        prind = new JButton(INDUSTRY); providesys.addActionListener(this);
        regind.addActionListener(this); prind.addActionListener(this);
        providesys.setFont(fn); regind.setFont(fn); prind.setFont(fn);

        frame = new JFrame();frame.setTitle("给排水碳排放查询");frame.setLocation(1000,300);frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints(); frame.setLayout(gridbag);
        gbc.insets.left = 5;gbc.insets.right = 5;gbc.insets.top = 5;gbc.insets.bottom = 5;

        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(providesys,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(regind,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(prind,gbc);

        frame.add(providesys);frame.add(regind);frame.add(prind);frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Font fn = new Font("宋体",Font.PLAIN,20);
        JLabel rel1,rel2,rel3;
        JLabel spetextfield[] = new JLabel[101];
        JFrame qwqframe = new JFrame();
        int i,spenum=20;
        String SQL,spetext[] = new String[101],qwq1 = new String(),qwq2 = new String(),qwq3 = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if (e.getActionCommand().equals(WATER)) {
                SQL = "select * from 给水系统;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                if(rs.next()){
                    int popuinfo = rs.getInt(1); double avgwatercons = rs.getDouble(2);
                    String  subrewaterrate = rs.getString(3).trim();double avgdewater = rs.getDouble(4);
                    double yeardewater = rs.getDouble(5);double getforwaterelecons = rs.getDouble(6);
                    double pureforwaterelecons = rs.getDouble(7);double matforwaterelecons = rs.getDouble(8);
                    double tolforwaterelecons = rs.getDouble(9);double cirforwatercaremi = rs.getDouble(10);
                    double getrewaterelecons = rs.getDouble(11);double purerewaterelecons = rs.getDouble(12);
                    double matrewaterelecons = rs.getDouble(13);double tolrewaterelecons = rs.getDouble(14);
                    double cirewatercaremi = rs.getDouble(15);

                    qwq1 = "人口数量："+popuinfo+"  人均用水量："+avgwatercons+"  再生水替代率："+subrewaterrate+"  人均自来水需求量："+avgdewater+ "  年自来水需求量："+yeardewater;
                    qwq2 = "外调水   取水电力消耗:"+getforwaterelecons+"  净水电力消耗："+pureforwaterelecons+" 配水电力消耗："+matforwaterelecons+"  综合电力排放："+tolforwaterelecons+"  生命周期碳排放："+cirforwatercaremi;
                    qwq3 = "再生水   取水电力消耗:"+getrewaterelecons+"  净水电力消耗："+purerewaterelecons+"  配水电力消耗："+matrewaterelecons+"  综合电力排放："+tolrewaterelecons+"  生命周期碳排放："+cirewatercaremi;
                }
                else {
                    JOptionPane.showMessageDialog(qwqframe,"无记录");
                    return;
                }
                qwqframe.setTitle("给水系统查询");qwqframe.setSize(1250,300);qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                rel1 = new JLabel(qwq1); rel1.setFont(fn); qwqframe.add(rel1);
                rel2 = new JLabel(qwq2); rel2.setFont(fn); qwqframe.add(rel2);
                rel3 = new JLabel(qwq3); rel3.setFont(fn); qwqframe.add(rel3);
                qwqframe.setVisible(true);
            }
            else if(e.getActionCommand().equals(GARBAGE)){
                SQL = "select * from 地理分区污水处理厂 ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String location = rs.getString(1).trim();
                    double avgenercons = rs.getDouble(2);
                    double accsewage = rs.getDouble(3);
                    double tolelecons = rs.getDouble(4);
                    spetext[i++] = "项目所在地：" + location + "  污水处理厂平均能耗水平：" + avgenercons +
                            "  污水量：" + accsewage + "  污水处理综合电力消耗：" + tolelecons;
                }
                if(i==1) {
                    JOptionPane.showMessageDialog(qwqframe,"无记录");
                    return;
                }
                spenum = i;
                qwqframe.setTitle("地理污水处理厂结果");qwqframe.setSize(1200,400);qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                for(int j=1;j<spenum;j++){
                    spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                }
                qwqframe.setVisible(true);
            }
            else if(e.getActionCommand().equals(INDUSTRY)){
                SQL = "select * from 自建污水处理厂 ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String proscale = rs.getString(1).trim();
                    double avgenercons = rs.getDouble(2);
                    double accsewage = rs.getDouble(3);
                    double tolelecons = rs.getDouble(4);
                    spetext[i++] = "项目所在地：" + proscale + " 污水处理厂平均能耗水平：" + avgenercons + "  污水量：" + accsewage + "  污水处理综合电力消耗：" + tolelecons;
                }
                if(i==1) {
                    JOptionPane.showMessageDialog(qwqframe,"无记录");
                    return;
                }
                spenum = i;
                qwqframe.setTitle("自建污水处理厂结果");qwqframe.setSize(1200,400);qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                for(int j=1;j<spenum;j++){
                    spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                }
                qwqframe.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("check water\n");
        }
    }
}

class chegarbage implements chaxun,ActionListener{
    public JLabel selcitylabel,selprojectlabel;
    public JTextField selcity;
    public JComboBox selproject;
    public JButton submit;
    public JFrame frame;

    public void checking(){
        Font fn = new Font("宋体",Font.PLAIN,25);
        selcitylabel = new JLabel("选择查询城市");selprojectlabel = new JLabel("选择查询类别");
        selcity = new JTextField(20);selproject = new JComboBox();
        selproject.addItem("卫生填埋");selproject.addItem("焚烧");selproject.addItem("其他");
        submit = new JButton("查询");submit.addActionListener(this);
        selcitylabel.setFont(fn);selprojectlabel.setFont(fn);selcity.setFont(fn);selproject.setFont(fn);
        submit.setFont(fn);

        frame = new JFrame();frame.setTitle("交通碳排放查询");frame.setLocation(1000,300);frame.setSize(600,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints(); frame.setLayout(gridbag);
        gbc.insets.left = 7;gbc.insets.right = 7;gbc.insets.top = 7;gbc.insets.bottom = 7;

        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(selcitylabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(selcity,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(selprojectlabel,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(selproject,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(submit,gbc);

        frame.add(selcitylabel);frame.add(selcity);frame.add(selprojectlabel);frame.add(selproject);
        frame.add(submit);frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String project = selproject.getSelectedItem().toString().trim();
        String city = selcity.getText().trim();
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Font fn = new Font("宋体", Font.PLAIN, 20);
        JLabel rel1, rel2, rel3;
        JLabel spetextfield[] = new JLabel[101];
        JFrame qwqframe = new JFrame();
        int i, spenum = 20;
        String SQL, spetext[] = new String[101], qwq1 = new String(), qwq2 = new String(), qwq3 = new String();
        if (city.equals(""))
            JOptionPane.showMessageDialog(frame, "请输入城市");
        else if(findcity(city)==false)
            JOptionPane.showMessageDialog(frame, "无此城市");
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                SQL = "select * from garinfo where city='" + city + "'and typeinfo='"+project+"';";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    int taryear = rs.getInt(2);
                    String tarrate = rs.getString(4).trim();
                    double tarcarint = rs.getDouble(5);
                    double tarcarbonemi = rs.getDouble(6);
                    spetext[i++] = "年份：" + taryear + " 占比：" +tarrate+ " 碳排放强度：" + tarcarint + " 碳排放量：" + tarcarbonemi;
                }
                spenum = i;
                qwqframe.setTitle(city+" "+project);
                qwqframe.setSize(800, 100 * spenum);
                qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);
                qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                for (int j = 1; j < spenum; j++) {
                    spetextfield[j] = new JLabel(spetext[j]);
                    spetextfield[j].setFont(fn);
                    qwqframe.add(spetextfield[j]);
                }
                qwqframe.setVisible(true);

            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("check garbage\n");
            }
        }
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
                int popu = rs.getInt(2);
                System.out.println(popu);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("find city\n");
        }
        return false;
    }
}

class chetraffic implements chaxun, ActionListener {
    public JLabel selcitylabel,selprojectlabel;
    public JTextField selcity;
    public JComboBox selproject;
    public JButton submit;
    public JFrame frame;
    /*public static void main(String args[]){
        chetraffic qwq = new chetraffic();
        qwq.checking();
    }*/
    public void checking(){
        Font fn = new Font("宋体",Font.PLAIN,25);
        selcitylabel = new JLabel("选择查询城市");selprojectlabel = new JLabel("选择查询项目");
        selcity = new JTextField(20);selproject = new JComboBox();
        selproject.addItem("社会车辆");selproject.addItem("公交车");selproject.addItem("出租车");selproject.addItem("轨道交通");
        selproject.addItem("可再生能源分摊");selproject.addItem("统计");selproject.addItem("城市信息");
        selproject.addItem("国家电网碳排放因子");submit = new JButton("查询");submit.addActionListener(this);
        selprojectlabel.setFont(fn);selprojectlabel.setFont(fn);selcity.setFont(fn);selproject.setFont(fn);
        submit.setFont(fn);selcitylabel.setFont(fn);

        frame = new JFrame();frame.setTitle("交通碳排放查询");frame.setLocation(1000,300);frame.setSize(600,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints(); frame.setLayout(gridbag);
        gbc.insets.left = 7;gbc.insets.right = 7;gbc.insets.top = 7;gbc.insets.bottom = 7;

        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(selcitylabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(selcity,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(selprojectlabel,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(selproject,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(submit,gbc);

        frame.add(selcitylabel);frame.add(selcity);frame.add(selprojectlabel);frame.add(selproject);
        frame.add(submit);frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String project = selproject.getSelectedItem().toString().trim();
        String city = selcity.getText().trim();
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Font fn = new Font("宋体",Font.PLAIN,20);
        JLabel spetextfield[] = new JLabel[101],sumtextfield[] = new JLabel[101],cityfield1,cityfield2;
        JLabel divlabel1[] = new JLabel[51],divlabel2[] = new JLabel[51];
        JFrame qwqframe = new JFrame();
        int i,spenum=20,sumnum=20;
        String SQL = new String(),spetext[] = new String[101],qwq1[] = new String[51],qwq2[] = new String[51];
        String sumtext[] = new String[101], citytext1 = new String(),citytext2 = new String();
        if (city.equals(""))
            JOptionPane.showMessageDialog(frame, "请输入城市");
        else if(findcity(city)==false)
            JOptionPane.showMessageDialog(frame, "无此城市");
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                if(project.equals("轨道交通")){
                    SQL = "select * from 轨道交通 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarenergycosint = rs.getDouble(3);
                        double taravgmileage = rs.getDouble(4);double tarelecons = rs.getDouble(5);
                        double tarcarbonemi = rs.getDouble(6);
                        spetext[i++] = "年份："+taryear+" 能耗强度："+tarenergycosint+" 年均行驶里程："+taravgmileage+" 电耗："+tarelecons+" 碳排放量："+tarcarbonemi;
                    }
                    spenum = i;
                    qwqframe.setTitle("轨道交通 "+city);qwqframe.setSize(800,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("可再生能源分摊")){
                    SQL = "select * from 能耗合计 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tartolencos = rs.getDouble(3);
                        double tartolcaremi = rs.getDouble(4);double tarphv = rs.getDouble(5);
                        double targardis = rs.getDouble(6);double tarnewdis = rs.getDouble(7);
                        double tartraencons = rs.getDouble(8);double tarcarcons = rs.getDouble(9);
                        qwq1[i++] = "年份："+taryear+" 总能耗："+tartolencos+" 总碳排放："+tartolcaremi+" 分布式光伏分配电力："+tarphv+ " 垃圾焚烧电厂分配电力："+targardis;
                        qwq2[i++] = "可再生能源碳减排量"+tarnewdis+" 交通总能耗："+tartraencons+" 交通总碳排放："+tarcarcons;
                    }
                    spenum = i;
                    qwqframe.setTitle("可再生能源分摊 "+city);qwqframe.setSize(1200,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        divlabel1[j] = new JLabel(qwq1[j]); divlabel1[j].setFont(fn); qwqframe.add(divlabel1[j]);
                        divlabel2[j] = new JLabel(qwq2[j]); divlabel2[j].setFont(fn); qwqframe.add(divlabel2[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("统计")){
                    SQL = "select * from 统计 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double targascons = rs.getDouble(3);
                        double tarequelec = rs.getDouble(4);double tarelecons = rs.getDouble(5);
                        spetext[i++] = "年份："+taryear+" 汽油消耗："+targascons+" 折算成电力："+tarequelec+" 电力消耗："+tarelecons;
                    }
                    spenum = i;
                    qwqframe.setTitle("统计 "+city);qwqframe.setSize(800,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("国家电网碳排放因子")){
                    SQL = "select * from 国家电网碳排放因子 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarpar = rs.getDouble(3);
                        spetext[i++] = "年份："+taryear+" 碳排放因子："+tarpar;
                    }
                    spenum = i;
                    qwqframe.setTitle("国家电网碳排放因子 "+city);qwqframe.setSize(500,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("城市信息")){
                    SQL = "select * from 城市信息 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if (rs.next()){
                        String tarcity = rs.getString(1).trim();int tarpopu = rs.getInt(2);double tarcarposs = rs.getDouble(3);
                        double taryearmile = rs.getDouble(4);double tarbusnum = rs.getDouble(5);double tarbusmile = rs.getDouble(6);
                        double tartaxinum = rs.getDouble(7);double tartaximile = rs.getDouble(8);
                        citytext1 = "城市："+tarcity+"  人口："+tarpopu+"  人均汽车保有量："+tarcarposs+"  汽车年均行驶里程："+taryearmile+"  人均公交数量:"+tarbusnum;
                        citytext2 = "公交日均行驶里程："+tarbusmile+"  人均出租车保有量："+tartaxinum+"  出租车年均行驶里程："+tartaximile;
                    }
                    qwqframe.setTitle(city+"城市信息");qwqframe.setSize(1000,200);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    cityfield1 = new JLabel(citytext1); cityfield1.setFont(fn); qwqframe.add(cityfield1);
                    cityfield2 = new JLabel(citytext2); cityfield2.setFont(fn); qwqframe.add(cityfield2);
                    qwqframe.setVisible(true);
                }
                else {
                    if(project.equals("社会车辆")) SQL = "select * from 社会车辆 where city='"+city+"';";
                    else if(project.equals("出租车")) SQL = "select * from 出租车 where city='"+city+"';";
                    else if(project.equals("公交车")) SQL = "select * from 公交车 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery(); i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);String tarvehtype = rs.getString(3).trim();
                        String tarrate = rs.getString(4).trim();double targasconsint = rs.getDouble(5);
                        double taremissionint = rs.getDouble(6);double targascons = rs.getDouble(7);
                        double tarequelec = rs.getDouble(8);double tareleconsint = rs.getDouble(9);
                        double tarelecons = rs.getDouble(10);double tarcarbonemi = rs.getDouble(11);
                        spetext[i++] = "年份："+taryear+" 车辆类型："+tarvehtype+" 占比："+tarrate+" 油耗强度："+targasconsint+" 碳排放强度："+taremissionint+
                                " 油耗："+targascons+" 折算成电力："+tarequelec+" 电耗强度："+tareleconsint+" 电耗："+tarelecons+" 碳排放量："+tarcarbonemi;
                    }spenum = i;
                    if(project.equals("社会车辆")) SQL = "select * from 社会车辆合计 where city='"+city+"';";
                    else if(project.equals("出租车")) SQL = "select * from 出租车合计 where city='"+city+"';";
                    else if(project.equals("公交车")) SQL = "select * from 公交车合计 where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarenergycons = rs.getDouble(3);
                        String tarcarbonemi = rs.getString(4).trim();
                        sumtext[i++] = "年份:"+taryear+" 总能耗："+tarenergycons+" 总碳排放量："+tarcarbonemi;
                    }sumnum = i;
                    qwqframe.setTitle(project+" "+city);qwqframe.setSize(1600,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    for(int j=1;j<sumnum;j++){
                        sumtextfield[j] = new JLabel(sumtext[j]); sumtextfield[j].setFont(fn); qwqframe.add(sumtextfield[j]);
                    }
                    cityfield1 = new JLabel(citytext1); cityfield1.setFont(fn); qwqframe.add(cityfield1);
                    /*cityfield2 = new JLabel(citytext2); cityfield2.setFont(fn); qwqframe.add(cityfield2);*/
                    qwqframe.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("find city\n");
            }
        }
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
            SQL = "select * from 城市信息 where city='"+city+"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
               int popu = rs.getInt(2);
                System.out.println(popu);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("find city\n");
        }
        return false;
    }
}

class chelight implements chaxun,ActionListener{

    public JButton check_road,check_scenery;
    public JFrame frame;
    public static final String ROAD = "查看道路照明信息";
    public static final String SCENERY = "查看景观照明信息";

    public void checking(){
        Font fn = new Font("宋体",Font.PLAIN,25);
        check_road = new JButton(ROAD); check_road.setFont(fn);check_road.addActionListener(this);
        check_scenery = new JButton(SCENERY);check_scenery.setFont(fn); check_scenery.addActionListener(this);

        frame = new JFrame();frame.setTitle("照明碳排放查询");frame.setLocation(1000,300);frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints(); frame.setLayout(gridbag);
        gbc.insets.left = 5;gbc.insets.right = 5;gbc.insets.top = 5;gbc.insets.bottom = 5;

        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(check_road,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(check_scenery,gbc);
        frame.add(check_road);frame.add(check_scenery);frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String connectURL = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JFrame qwqframe;
        double roadsum=0;
        String SQL,sumstr;
        JLabel wayinfo_label,zoneinfo_label[] = new JLabel[20],sumstr_label;
        String wayinfo,zoneinfo[] = new String[20];
        int i;
        Font fn = new Font("宋体",Font.PLAIN,25);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectURL, "sa", "123456");
            if(e.getSource()==check_road){
                qwqframe = new JFrame("道路照明信息");
                qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(300, 300);
                qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                SQL = "select * from 道路照明 ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String res1 = rs.getString(1).trim();String res2 = rs.getString(2).trim();
                    int res3 = rs.getInt(3); double res4 = rs.getDouble(4);
                    double res5 = rs.getDouble(5); double res6 = rs.getDouble(6);
                    double res7 = rs.getDouble(7); double res8 = rs.getDouble(8);
                    wayinfo = "道路名称："+res1+"  道路级别："+res2+"  车道数(条)："+res3+"  道路长度："+res4+"  道路宽度："+res5+
                            "  照明功率密度（W/m2）："+res6+"  照明时间(h/d)："+res7+"  全年照明能耗(kwh/a)："+res8;
                    wayinfo_label = new JLabel(wayinfo);
                    wayinfo_label.setFont(fn);
                    qwqframe.add(wayinfo_label);
                    i++;
                }
                SQL = "select sum(yearenercons) from 道路照明";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next())
                    roadsum = rs.getDouble(1);
                sumstr = "道路照明总计："+roadsum;
                sumstr_label = new JLabel(sumstr);
                sumstr_label.setFont(fn);
                qwqframe.setSize(2150, 100*i);
                qwqframe.add(sumstr_label);
                qwqframe.setVisible(true);
            }
            else if(e.getSource()==check_scenery){
                qwqframe = new JFrame("景观照明信息");
                qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(300, 300);
                qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                SQL = "select * from 景观照明 ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String res1 = rs.getString(1).trim();String res2 = rs.getString(2).trim();
                    double res3 = rs.getDouble(3); String res4 = rs.getString(4).trim();
                    String res5 = rs.getString(5).trim(); double res6 = rs.getDouble(6);
                    double res7 = rs.getDouble(7); double res8 = rs.getDouble(8);
                    wayinfo = "用地编号："+res1+"  用地性质："+res2+"  用地面积："+res3+"  建筑面积："+res4+"  绿地率："+res5+
                            "  照明功率密度（W/m2）："+res6+"  照明时间(h/d)："+res7+"  全年照明能耗(kwh/a)："+res8;
                    wayinfo_label = new JLabel(wayinfo);
                    wayinfo_label.setFont(fn);
                    qwqframe.add(wayinfo_label);
                    i++;
                }
                SQL = "select sum(yearenercons) from 景观照明";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next())
                    roadsum = rs.getDouble(1);
                sumstr = "景观照明总计："+roadsum;
                sumstr_label = new JLabel(sumstr);
                sumstr_label.setFont(fn);
                qwqframe.setSize(2150, 100*i);
                qwqframe.add(sumstr_label);
                qwqframe.setVisible(true);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("chelight");
        }
    }
}