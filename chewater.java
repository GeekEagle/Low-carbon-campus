import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class chewater implements chaxun,ActionListener{
    public JButton providesys,regind,prind;
    public JFrame frame;
    public static final String WATER = "�鿴��ˮϵͳ����";
    public static final String GARBAGE = "�鿴������ˮ��������";
    public static final String INDUSTRY = "�鿴�Խ���ˮ��������";

    public void checking(){
        Font fn = new Font("����",Font.PLAIN,25);
        providesys = new JButton(WATER); regind = new JButton(GARBAGE);
        prind = new JButton(INDUSTRY); providesys.addActionListener(this);
        regind.addActionListener(this); prind.addActionListener(this);
        providesys.setFont(fn); regind.setFont(fn); prind.setFont(fn);

        frame = new JFrame();frame.setTitle("����ˮ̼�ŷŲ�ѯ");frame.setLocation(1000,300);frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout(); //���ò���
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
        Font fn = new Font("����",Font.PLAIN,20);
        JLabel rel1,rel2,rel3;
        JLabel spetextfield[] = new JLabel[101];
        JFrame qwqframe = new JFrame();
        int i,spenum=20;
        String SQL,spetext[] = new String[101],qwq1 = new String(),qwq2 = new String(),qwq3 = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if (e.getActionCommand().equals(WATER)) {
                SQL = "select * from ��ˮϵͳ;";
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

                    qwq1 = "�˿�������"+popuinfo+"  �˾���ˮ����"+avgwatercons+"  ����ˮ����ʣ�"+subrewaterrate+"  �˾�����ˮ��������"+avgdewater+ "  ������ˮ��������"+yeardewater;
                    qwq2 = "���ˮ   ȡˮ��������:"+getforwaterelecons+"  ��ˮ�������ģ�"+pureforwaterelecons+" ��ˮ�������ģ�"+matforwaterelecons+"  �ۺϵ����ŷţ�"+tolforwaterelecons+"  ��������̼�ŷţ�"+cirforwatercaremi;
                    qwq3 = "����ˮ   ȡˮ��������:"+getrewaterelecons+"  ��ˮ�������ģ�"+purerewaterelecons+"  ��ˮ�������ģ�"+matrewaterelecons+"  �ۺϵ����ŷţ�"+tolrewaterelecons+"  ��������̼�ŷţ�"+cirewatercaremi;
                }
                else {
                    JOptionPane.showMessageDialog(qwqframe,"�޼�¼");
                    return;
                }
                qwqframe.setTitle("��ˮϵͳ��ѯ");qwqframe.setSize(1250,300);qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                rel1 = new JLabel(qwq1); rel1.setFont(fn); qwqframe.add(rel1);
                rel2 = new JLabel(qwq2); rel2.setFont(fn); qwqframe.add(rel2);
                rel3 = new JLabel(qwq3); rel3.setFont(fn); qwqframe.add(rel3);
                qwqframe.setVisible(true);
            }
            else if(e.getActionCommand().equals(GARBAGE)){
                SQL = "select * from ���������ˮ���� ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String location = rs.getString(1).trim();
                    double avgenercons = rs.getDouble(2);
                    double accsewage = rs.getDouble(3);
                    double tolelecons = rs.getDouble(4);
                    spetext[i++] = "��Ŀ���ڵأ�" + location + "  ��ˮ����ƽ���ܺ�ˮƽ��" + avgenercons +
                            "  ��ˮ����" + accsewage + "  ��ˮ�����ۺϵ������ģ�" + tolelecons;
                }
                if(i==1) {
                    JOptionPane.showMessageDialog(qwqframe,"�޼�¼");
                    return;
                }
                spenum = i;
                qwqframe.setTitle("������ˮ�������");qwqframe.setSize(1200,400);qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                for(int j=1;j<spenum;j++){
                    spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                }
                qwqframe.setVisible(true);
            }
            else if(e.getActionCommand().equals(INDUSTRY)){
                SQL = "select * from �Խ���ˮ���� ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String proscale = rs.getString(1).trim();
                    double avgenercons = rs.getDouble(2);
                    double accsewage = rs.getDouble(3);
                    double tolelecons = rs.getDouble(4);
                    spetext[i++] = "��Ŀ���ڵأ�" + proscale + " ��ˮ����ƽ���ܺ�ˮƽ��" + avgenercons + "  ��ˮ����" + accsewage + "  ��ˮ�����ۺϵ������ģ�" + tolelecons;
                }
                if(i==1) {
                    JOptionPane.showMessageDialog(qwqframe,"�޼�¼");
                    return;
                }
                spenum = i;
                qwqframe.setTitle("�Խ���ˮ�������");qwqframe.setSize(1200,400);qwqframe.setBackground(Color.LIGHT_GRAY);
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
        Font fn = new Font("����",Font.PLAIN,25);
        selcitylabel = new JLabel("ѡ���ѯ����");selprojectlabel = new JLabel("ѡ���ѯ���");
        selcity = new JTextField(20);selproject = new JComboBox();
        selproject.addItem("��������");selproject.addItem("����");selproject.addItem("����");
        submit = new JButton("��ѯ");submit.addActionListener(this);
        selcitylabel.setFont(fn);selprojectlabel.setFont(fn);selcity.setFont(fn);selproject.setFont(fn);
        submit.setFont(fn);

        frame = new JFrame();frame.setTitle("��̼ͨ�ŷŲ�ѯ");frame.setLocation(1000,300);frame.setSize(600,300);
        GridBagLayout gridbag = new GridBagLayout(); //���ò���
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
        Font fn = new Font("����", Font.PLAIN, 20);
        JLabel rel1, rel2, rel3;
        JLabel spetextfield[] = new JLabel[101];
        JFrame qwqframe = new JFrame();
        int i, spenum = 20;
        String SQL, spetext[] = new String[101], qwq1 = new String(), qwq2 = new String(), qwq3 = new String();
        if (city.equals(""))
            JOptionPane.showMessageDialog(frame, "���������");
        else if(findcity(city)==false)
            JOptionPane.showMessageDialog(frame, "�޴˳���");
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
                    spetext[i++] = "��ݣ�" + taryear + " ռ�ȣ�" +tarrate+ " ̼�ŷ�ǿ�ȣ�" + tarcarint + " ̼�ŷ�����" + tarcarbonemi;
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
        Font fn = new Font("����",Font.PLAIN,25);
        selcitylabel = new JLabel("ѡ���ѯ����");selprojectlabel = new JLabel("ѡ���ѯ��Ŀ");
        selcity = new JTextField(20);selproject = new JComboBox();
        selproject.addItem("��ᳵ��");selproject.addItem("������");selproject.addItem("���⳵");selproject.addItem("�����ͨ");
        selproject.addItem("��������Դ��̯");selproject.addItem("ͳ��");selproject.addItem("������Ϣ");
        selproject.addItem("���ҵ���̼�ŷ�����");submit = new JButton("��ѯ");submit.addActionListener(this);
        selprojectlabel.setFont(fn);selprojectlabel.setFont(fn);selcity.setFont(fn);selproject.setFont(fn);
        submit.setFont(fn);selcitylabel.setFont(fn);

        frame = new JFrame();frame.setTitle("��̼ͨ�ŷŲ�ѯ");frame.setLocation(1000,300);frame.setSize(600,300);
        GridBagLayout gridbag = new GridBagLayout(); //���ò���
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
        Font fn = new Font("����",Font.PLAIN,20);
        JLabel spetextfield[] = new JLabel[101],sumtextfield[] = new JLabel[101],cityfield1,cityfield2;
        JLabel divlabel1[] = new JLabel[51],divlabel2[] = new JLabel[51];
        JFrame qwqframe = new JFrame();
        int i,spenum=20,sumnum=20;
        String SQL = new String(),spetext[] = new String[101],qwq1[] = new String[51],qwq2[] = new String[51];
        String sumtext[] = new String[101], citytext1 = new String(),citytext2 = new String();
        if (city.equals(""))
            JOptionPane.showMessageDialog(frame, "���������");
        else if(findcity(city)==false)
            JOptionPane.showMessageDialog(frame, "�޴˳���");
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                if(project.equals("�����ͨ")){
                    SQL = "select * from �����ͨ where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarenergycosint = rs.getDouble(3);
                        double taravgmileage = rs.getDouble(4);double tarelecons = rs.getDouble(5);
                        double tarcarbonemi = rs.getDouble(6);
                        spetext[i++] = "��ݣ�"+taryear+" �ܺ�ǿ�ȣ�"+tarenergycosint+" �����ʻ��̣�"+taravgmileage+" ��ģ�"+tarelecons+" ̼�ŷ�����"+tarcarbonemi;
                    }
                    spenum = i;
                    qwqframe.setTitle("�����ͨ "+city);qwqframe.setSize(800,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("��������Դ��̯")){
                    SQL = "select * from �ܺĺϼ� where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tartolencos = rs.getDouble(3);
                        double tartolcaremi = rs.getDouble(4);double tarphv = rs.getDouble(5);
                        double targardis = rs.getDouble(6);double tarnewdis = rs.getDouble(7);
                        double tartraencons = rs.getDouble(8);double tarcarcons = rs.getDouble(9);
                        qwq1[i++] = "��ݣ�"+taryear+" ���ܺģ�"+tartolencos+" ��̼�ŷţ�"+tartolcaremi+" �ֲ�ʽ������������"+tarphv+ " �������յ糧���������"+targardis;
                        qwq2[i++] = "��������Դ̼������"+tarnewdis+" ��ͨ���ܺģ�"+tartraencons+" ��ͨ��̼�ŷţ�"+tarcarcons;
                    }
                    spenum = i;
                    qwqframe.setTitle("��������Դ��̯ "+city);qwqframe.setSize(1200,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        divlabel1[j] = new JLabel(qwq1[j]); divlabel1[j].setFont(fn); qwqframe.add(divlabel1[j]);
                        divlabel2[j] = new JLabel(qwq2[j]); divlabel2[j].setFont(fn); qwqframe.add(divlabel2[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("ͳ��")){
                    SQL = "select * from ͳ�� where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double targascons = rs.getDouble(3);
                        double tarequelec = rs.getDouble(4);double tarelecons = rs.getDouble(5);
                        spetext[i++] = "��ݣ�"+taryear+" �������ģ�"+targascons+" ����ɵ�����"+tarequelec+" �������ģ�"+tarelecons;
                    }
                    spenum = i;
                    qwqframe.setTitle("ͳ�� "+city);qwqframe.setSize(800,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("���ҵ���̼�ŷ�����")){
                    SQL = "select * from ���ҵ���̼�ŷ����� where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarpar = rs.getDouble(3);
                        spetext[i++] = "��ݣ�"+taryear+" ̼�ŷ����ӣ�"+tarpar;
                    }
                    spenum = i;
                    qwqframe.setTitle("���ҵ���̼�ŷ����� "+city);qwqframe.setSize(500,100*spenum);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    for(int j=1;j<spenum;j++){
                        spetextfield[j] = new JLabel(spetext[j]); spetextfield[j].setFont(fn); qwqframe.add(spetextfield[j]);
                    }
                    qwqframe.setVisible(true);
                }
                else if(project.equals("������Ϣ")){
                    SQL = "select * from ������Ϣ where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if (rs.next()){
                        String tarcity = rs.getString(1).trim();int tarpopu = rs.getInt(2);double tarcarposs = rs.getDouble(3);
                        double taryearmile = rs.getDouble(4);double tarbusnum = rs.getDouble(5);double tarbusmile = rs.getDouble(6);
                        double tartaxinum = rs.getDouble(7);double tartaximile = rs.getDouble(8);
                        citytext1 = "���У�"+tarcity+"  �˿ڣ�"+tarpopu+"  �˾�������������"+tarcarposs+"  ���������ʻ��̣�"+taryearmile+"  �˾���������:"+tarbusnum;
                        citytext2 = "�����վ���ʻ��̣�"+tarbusmile+"  �˾����⳵��������"+tartaxinum+"  ���⳵�����ʻ��̣�"+tartaximile;
                    }
                    qwqframe.setTitle(city+"������Ϣ");qwqframe.setSize(1000,200);qwqframe.setBackground(Color.LIGHT_GRAY);
                    qwqframe.setLocation(500, 300);qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
                    cityfield1 = new JLabel(citytext1); cityfield1.setFont(fn); qwqframe.add(cityfield1);
                    cityfield2 = new JLabel(citytext2); cityfield2.setFont(fn); qwqframe.add(cityfield2);
                    qwqframe.setVisible(true);
                }
                else {
                    if(project.equals("��ᳵ��")) SQL = "select * from ��ᳵ�� where city='"+city+"';";
                    else if(project.equals("���⳵")) SQL = "select * from ���⳵ where city='"+city+"';";
                    else if(project.equals("������")) SQL = "select * from ������ where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery(); i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);String tarvehtype = rs.getString(3).trim();
                        String tarrate = rs.getString(4).trim();double targasconsint = rs.getDouble(5);
                        double taremissionint = rs.getDouble(6);double targascons = rs.getDouble(7);
                        double tarequelec = rs.getDouble(8);double tareleconsint = rs.getDouble(9);
                        double tarelecons = rs.getDouble(10);double tarcarbonemi = rs.getDouble(11);
                        spetext[i++] = "��ݣ�"+taryear+" �������ͣ�"+tarvehtype+" ռ�ȣ�"+tarrate+" �ͺ�ǿ�ȣ�"+targasconsint+" ̼�ŷ�ǿ�ȣ�"+taremissionint+
                                " �ͺģ�"+targascons+" ����ɵ�����"+tarequelec+" ���ǿ�ȣ�"+tareleconsint+" ��ģ�"+tarelecons+" ̼�ŷ�����"+tarcarbonemi;
                    }spenum = i;
                    if(project.equals("��ᳵ��")) SQL = "select * from ��ᳵ���ϼ� where city='"+city+"';";
                    else if(project.equals("���⳵")) SQL = "select * from ���⳵�ϼ� where city='"+city+"';";
                    else if(project.equals("������")) SQL = "select * from �������ϼ� where city='"+city+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();i = 1;
                    while (rs.next()){
                        int taryear = rs.getInt(1);double tarenergycons = rs.getDouble(3);
                        String tarcarbonemi = rs.getString(4).trim();
                        sumtext[i++] = "���:"+taryear+" ���ܺģ�"+tarenergycons+" ��̼�ŷ�����"+tarcarbonemi;
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
            SQL = "select * from ������Ϣ where city='"+city+"';";
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
    public static final String ROAD = "�鿴��·������Ϣ";
    public static final String SCENERY = "�鿴����������Ϣ";

    public void checking(){
        Font fn = new Font("����",Font.PLAIN,25);
        check_road = new JButton(ROAD); check_road.setFont(fn);check_road.addActionListener(this);
        check_scenery = new JButton(SCENERY);check_scenery.setFont(fn); check_scenery.addActionListener(this);

        frame = new JFrame();frame.setTitle("����̼�ŷŲ�ѯ");frame.setLocation(1000,300);frame.setSize(500,300);
        GridBagLayout gridbag = new GridBagLayout(); //���ò���
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
        Font fn = new Font("����",Font.PLAIN,25);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectURL, "sa", "123456");
            if(e.getSource()==check_road){
                qwqframe = new JFrame("��·������Ϣ");
                qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(300, 300);
                qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                SQL = "select * from ��·���� ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String res1 = rs.getString(1).trim();String res2 = rs.getString(2).trim();
                    int res3 = rs.getInt(3); double res4 = rs.getDouble(4);
                    double res5 = rs.getDouble(5); double res6 = rs.getDouble(6);
                    double res7 = rs.getDouble(7); double res8 = rs.getDouble(8);
                    wayinfo = "��·���ƣ�"+res1+"  ��·����"+res2+"  ������(��)��"+res3+"  ��·���ȣ�"+res4+"  ��·��ȣ�"+res5+
                            "  ���������ܶȣ�W/m2����"+res6+"  ����ʱ��(h/d)��"+res7+"  ȫ�������ܺ�(kwh/a)��"+res8;
                    wayinfo_label = new JLabel(wayinfo);
                    wayinfo_label.setFont(fn);
                    qwqframe.add(wayinfo_label);
                    i++;
                }
                SQL = "select sum(yearenercons) from ��·����";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next())
                    roadsum = rs.getDouble(1);
                sumstr = "��·�����ܼƣ�"+roadsum;
                sumstr_label = new JLabel(sumstr);
                sumstr_label.setFont(fn);
                qwqframe.setSize(2150, 100*i);
                qwqframe.add(sumstr_label);
                qwqframe.setVisible(true);
            }
            else if(e.getSource()==check_scenery){
                qwqframe = new JFrame("����������Ϣ");
                qwqframe.setBackground(Color.LIGHT_GRAY);
                qwqframe.setLocation(300, 300);
                qwqframe.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
                SQL = "select * from �������� ;";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                i = 1;
                while (rs.next()) {
                    String res1 = rs.getString(1).trim();String res2 = rs.getString(2).trim();
                    double res3 = rs.getDouble(3); String res4 = rs.getString(4).trim();
                    String res5 = rs.getString(5).trim(); double res6 = rs.getDouble(6);
                    double res7 = rs.getDouble(7); double res8 = rs.getDouble(8);
                    wayinfo = "�õر�ţ�"+res1+"  �õ����ʣ�"+res2+"  �õ������"+res3+"  ���������"+res4+"  �̵��ʣ�"+res5+
                            "  ���������ܶȣ�W/m2����"+res6+"  ����ʱ��(h/d)��"+res7+"  ȫ�������ܺ�(kwh/a)��"+res8;
                    wayinfo_label = new JLabel(wayinfo);
                    wayinfo_label.setFont(fn);
                    qwqframe.add(wayinfo_label);
                    i++;
                }
                SQL = "select sum(yearenercons) from ��������";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next())
                    roadsum = rs.getDouble(1);
                sumstr = "���������ܼƣ�"+roadsum;
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