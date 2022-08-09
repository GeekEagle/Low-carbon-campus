import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class water implements emission, ActionListener {
    public JFrame frame;
    public JTextField popu,avgcons,subratere,getfor,purefor;
    public JTextField matfor,getre,purere,matre;
    public JLabel populabel,avgconslabel,subraterelabel,getforeleconslabel,pureforeleconslabel;
    public JLabel matforeleconslabel,getrelabel,purerelabel,matrelabel;
    public JButton submit;
    public static final String TRAFFIC = "提交修改";

    /*public static void main(String args[]){
        water qwq = new water();
        qwq.changeparm();
    }*/

    public void changeparm(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        populabel = new JLabel("人口数量"); populabel.setFont(fn);
        avgconslabel = new JLabel("人均用水量");avgconslabel.setFont(fn);
        subraterelabel = new JLabel("再生水替代率");subraterelabel.setFont(fn);
        getforeleconslabel = new JLabel("外调水取水电力消耗");getforeleconslabel.setFont(fn);
        pureforeleconslabel = new JLabel("外调水净水电力消耗"); pureforeleconslabel.setFont(fn);
        matforeleconslabel = new JLabel("外调水配水电力消耗");matforeleconslabel.setFont(fn);
        getrelabel = new JLabel("再生水取水电力消耗");getrelabel.setFont(fn);
        purerelabel = new JLabel("再生水净水电力消耗");purerelabel.setFont(fn);
        matrelabel = new JLabel("再生水配水电力消耗");matrelabel.setFont(fn);
        popu = new JTextField(15);popu.setFont(fn); avgcons = new JTextField(15); avgcons.setFont(fn);
        subratere = new JTextField(15); subratere.setFont(fn);getfor = new JTextField(15); getfor.setFont(fn);
        purefor = new JTextField(15);purefor.setFont(fn); matfor = new JTextField(15); matfor.setFont(fn);
        getre = new JTextField(15);getre.setFont(fn); purere = new JTextField(15); purere.setFont(fn);
        matre = new JTextField(15); matre.setFont(fn);submit = new JButton(TRAFFIC);submit.setFont(fn);
        submit.addActionListener(this);

        frame = new JFrame();
        frame.setTitle("给排水碳排放设置界面");
        frame.setLocation(1000,300);
        frame.setSize(900,400);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 10;
        gbc.insets.right = 10;
        gbc.insets.top = 10;
        gbc.insets.bottom = 10;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(populabel,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(popu,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(avgconslabel,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(avgcons,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(subraterelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(subratere,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(getforeleconslabel,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(getfor,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(pureforeleconslabel,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(purefor,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(matforeleconslabel,gbc);
        gbc.gridx = 4;gbc.gridy = 3;gridbag.setConstraints(matfor,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(getrelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(getre,gbc);
        gbc.gridx = 3;gbc.gridy = 4;gridbag.setConstraints(purerelabel,gbc);
        gbc.gridx = 4;gbc.gridy = 4;gridbag.setConstraints(purere,gbc);
        gbc.gridx = 1;gbc.gridy = 5;gridbag.setConstraints(matrelabel,gbc);
        gbc.gridx = 2;gbc.gridy = 5;gridbag.setConstraints(matre,gbc);
        gbc.gridx = 3;gbc.gridy = 5;gridbag.setConstraints(submit,gbc);

        frame.add(populabel);frame.add(popu);frame.add(avgconslabel);frame.add(avgcons);
        frame.add(subraterelabel);frame.add(subratere);frame.add(getforeleconslabel);frame.add(getfor);
        frame.add(pureforeleconslabel);frame.add(purefor);frame.add(matforeleconslabel);frame.add(matfor);
        frame.add(getrelabel);frame.add(getre);frame.add(purerelabel);frame.add(purere);
        frame.add(matrelabel);frame.add(matre);frame.add(submit);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        CallableStatement calls;
        ResultSet rs = null;
        int popudata,qwq;
        String subraterestr,SQL,location,ANO;
        double avgcondouble,getfordouble,purefordouble,avgelecons,rel1,rel2;
        double matfordouble,getredouble,pureredouble,matredouble;
        popudata = Integer.parseInt(popu.getText().trim()); avgcondouble = Double.parseDouble(avgcons.getText().trim());
        subraterestr = subratere.getText().trim(); getfordouble = Double.parseDouble(getfor.getText().trim());
        purefordouble = Double.parseDouble(purefor.getText().trim()); matfordouble = Double.parseDouble(matfor.getText().trim());
        getredouble = Double.parseDouble(getre.getText().trim());pureredouble = Double.parseDouble(purere.getText().trim());
        matredouble = Double.parseDouble(matre.getText().trim());
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            double subrate = Double.parseDouble(subraterestr.replace("%",""))/100;
            double avgdewater,yeardewater,tolforwaterelecons,cirforwatercaremi,tolrewaterelecons,cirewatercaremi;
            double temp = popudata*subrate*avgcondouble*0.365;
            avgdewater = Double.parseDouble(String.format("%.2f",avgcondouble*(1-subrate)));
            yeardewater = Double.parseDouble(String.format("%.2f",popudata*avgdewater*365/1000));
            tolforwaterelecons = Double.parseDouble(String.format("%.2f",yeardewater*(getfordouble+purefordouble+matfordouble)));
            cirforwatercaremi = Double.parseDouble(String.format("%.2f",yeardewater*1.093));
            tolrewaterelecons = Double.parseDouble(String.format("%.2f",(getredouble+pureredouble+matredouble)*temp));
            cirewatercaremi = Double.parseDouble(String.format("%.2f",temp*1.023));
            SQL = "update 给水系统 set popuinfo='"+popudata+"',avgwatercons='"+avgcondouble+"',subrewaterrate='"+subraterestr+
                    "',avgdewater='"+avgdewater+"',yeardewater= '"+yeardewater+"',getforwaterelecons='"+getfordouble+
                    "',pureforwaterelecons='"+purefordouble+"',matforwaterelecons='"+matfordouble+"',tolforwaterelecons='"+tolforwaterelecons+
                    "',cirforwatercaremi='"+cirforwatercaremi+"',getrewaterelecons='"+getredouble+"',purerewaterelecons='"+pureredouble+
                    "',matrewaterelecons='"+matredouble+"',tolrewaterelecons='"+tolrewaterelecons+"',cirewatercaremi='"+cirewatercaremi+"';";
            stmt = con.prepareStatement(SQL);
            qwq = stmt.executeUpdate();
            SQL = "select * from 地理分区污水处理厂";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            while(rs.next()){
                location = rs.getString(1).trim();avgelecons = rs.getDouble(2);
                rel1 = Double.parseDouble(String.format("%.2f",popudata*avgcondouble*0.365)); rel2 = Double.parseDouble(String.format("%.2f",avgelecons*rel1));
                ANO = "update 地理分区污水处理厂 set accsewage='"+rel1+"',tolelecons='"+rel2+"' where locationinfo='"+location+"' and avgenercons='"+avgelecons+"';";
                stmt = con.prepareStatement(ANO);
                qwq = stmt.executeUpdate();
            }
            SQL = "select * from 自建污水处理厂";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            while(rs.next()){
                location = rs.getString(1).trim();avgelecons = rs.getDouble(2);
                rel1 = Double.parseDouble(String.format("%.2f",popudata*avgcondouble*0.365)); rel2 = Double.parseDouble(String.format("%.2f",avgelecons*rel1));
                ANO = "update 自建污水处理厂 set accsewage='"+rel1+"',tolelecons='"+rel2+"' where proscale='"+location+"' and avgenercons='"+avgelecons+"';";
                stmt = con.prepareStatement(ANO);
                qwq = stmt.executeUpdate();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
    }
}
