import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.sql.*;

public class light implements emission, ActionListener {
    public JFrame frame;
    public JTextField wayname,waylevel,waynum,waylength,waywidth,wayden,waytime;
    public JTextField scename,scechara,scesqu,sceden,scegreen,scepowden,scetime;
    public JLabel wayname_label,waylevel_label,waynum_label,waylength_label,waywidth_label;
    public JLabel waydensity_label,waytime_label;
    public JLabel scename_label,scechara_label,scesqu_label,sceden_label,scegreen_label;
    public JLabel scepowden_label,scetime_label;;
    public JButton subway,subsce;
    public static final String ROAD = "道路照明提交";
    public static final String SCENERY = "景观照明提交";

    /*public static void main(String args[]){
        light qwq = new light();
        qwq.changeparm();
    }*/
    public void changeparm(){
        Font fn = new Font("宋体",Font.PLAIN,20);
        wayname_label = new JLabel("道路名称"); wayname_label.setFont(fn);waylevel_label = new JLabel("道路级别"); waylevel_label.setFont(fn);
        waynum_label = new JLabel("车道数量"); waynum_label.setFont(fn);waylength_label = new JLabel("道路长度"); waylength_label.setFont(fn);
        waywidth_label= new JLabel("道路宽度"); waywidth_label.setFont(fn);waydensity_label = new JLabel("照明功率密度"); waydensity_label.setFont(fn);
        waytime_label= new JLabel("照明时间"); waytime_label.setFont(fn);
        scename_label = new JLabel("用地名称"); scename_label.setFont(fn);scechara_label = new JLabel("用地性质"); scechara_label.setFont(fn);
        scesqu_label = new JLabel("用地面积"); scesqu_label.setFont(fn);sceden_label = new JLabel("建筑密度"); sceden_label.setFont(fn);
        scegreen_label = new JLabel("绿地率"); scegreen_label.setFont(fn);scepowden_label = new JLabel("照明功率密度"); scepowden_label.setFont(fn);
        scetime_label = new JLabel("照明时间"); scetime_label.setFont(fn);
        wayname = new JTextField(20); wayname.setFont(fn); waylevel = new JTextField(20); waylevel.setFont(fn);
        waynum = new JTextField(20); waynum.setFont(fn);waylength = new JTextField(20); waylength.setFont(fn);
        waywidth = new JTextField(20); waywidth.setFont(fn); wayden = new JTextField(20); wayden.setFont(fn);
        waytime = new JTextField(20); waytime.setFont(fn);
        scename = new JTextField(20); scename.setFont(fn); scechara = new JTextField(20); scechara.setFont(fn);
        scesqu = new JTextField(20); scesqu.setFont(fn); sceden = new JTextField(20); sceden.setFont(fn);
        scegreen = new JTextField(20); scegreen.setFont(fn); scepowden = new JTextField(20); scepowden.setFont(fn);
        scetime = new JTextField(20); scetime.setFont(fn);
        subway = new JButton(ROAD); subway.addActionListener(this); subway.setFont(fn);
        subsce = new JButton(SCENERY); subsce.addActionListener(this); subsce.setFont(fn);

        frame = new JFrame();
        frame.setTitle("照明碳排放设置界面");
        frame.setLocation(1000,300);
        frame.setSize(800,700);
        GridBagLayout gridbag = new GridBagLayout(); //设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(gridbag);
        gbc.insets.left = 10;gbc.insets.right = 10;gbc.insets.top = 10;gbc.insets.bottom = 10;
        gbc.gridx = 1;gbc.gridy = 1;gridbag.setConstraints(wayname_label,gbc);
        gbc.gridx = 2;gbc.gridy = 1;gridbag.setConstraints(wayname,gbc);
        gbc.gridx = 3;gbc.gridy = 1;gridbag.setConstraints(waylevel_label,gbc);
        gbc.gridx = 4;gbc.gridy = 1;gridbag.setConstraints(waylevel,gbc);
        gbc.gridx = 1;gbc.gridy = 2;gridbag.setConstraints(waynum_label,gbc);
        gbc.gridx = 2;gbc.gridy = 2;gridbag.setConstraints(waynum,gbc);
        gbc.gridx = 3;gbc.gridy = 2;gridbag.setConstraints(waylength_label,gbc);
        gbc.gridx = 4;gbc.gridy = 2;gridbag.setConstraints(waylength,gbc);
        gbc.gridx = 1;gbc.gridy = 3;gridbag.setConstraints(waywidth_label,gbc);
        gbc.gridx = 2;gbc.gridy = 3;gridbag.setConstraints(waywidth,gbc);
        gbc.gridx = 3;gbc.gridy = 3;gridbag.setConstraints(waydensity_label,gbc);
        gbc.gridx = 4;gbc.gridy = 3;gridbag.setConstraints(wayden,gbc);
        gbc.gridx = 1;gbc.gridy = 4;gridbag.setConstraints(waytime_label,gbc);
        gbc.gridx = 2;gbc.gridy = 4;gridbag.setConstraints(waytime,gbc);

        gbc.gridx = 2;gbc.gridy = 5;gridbag.setConstraints(subway,gbc);
        gbc.gridx = 1;gbc.gridy = 6;gridbag.setConstraints(scename_label,gbc);
        gbc.gridx = 2;gbc.gridy = 6;gridbag.setConstraints(scename,gbc);
        gbc.gridx = 3;gbc.gridy = 6;gridbag.setConstraints(scechara_label,gbc);
        gbc.gridx = 4;gbc.gridy = 6;gridbag.setConstraints(scechara,gbc);
        gbc.gridx = 1;gbc.gridy = 7;gridbag.setConstraints(scesqu_label,gbc);
        gbc.gridx = 2;gbc.gridy = 7;gridbag.setConstraints(scesqu,gbc);
        gbc.gridx = 3;gbc.gridy = 7;gridbag.setConstraints(sceden_label,gbc);
        gbc.gridx = 4;gbc.gridy = 7;gridbag.setConstraints(sceden,gbc);
        gbc.gridx = 1;gbc.gridy = 8;gridbag.setConstraints(scegreen_label,gbc);
        gbc.gridx = 2;gbc.gridy = 8;gridbag.setConstraints(scegreen,gbc);
        gbc.gridx = 3;gbc.gridy = 8;gridbag.setConstraints(scepowden_label,gbc);
        gbc.gridx = 4;gbc.gridy = 8;gridbag.setConstraints(scepowden,gbc);
        gbc.gridx = 1;gbc.gridy = 9;gridbag.setConstraints(scetime_label,gbc);
        gbc.gridx = 2;gbc.gridy = 9;gridbag.setConstraints(scetime,gbc);
        gbc.gridx = 3;gbc.gridy = 10;gridbag.setConstraints(subsce,gbc);

        frame.add(wayname_label);frame.add(wayname);frame.add(waylevel_label);frame.add(waylevel);
        frame.add(waynum_label);frame.add(waynum);frame.add(waylength_label);frame.add(waylength);
        frame.add(waywidth_label);frame.add(waywidth);frame.add(waydensity_label);frame.add(wayden);
        frame.add(waytime_label);frame.add(waytime);frame.add(subway);
        frame.add(scename_label);frame.add(scename);frame.add(scechara_label);frame.add(scechara);
        frame.add(scesqu_label);frame.add(scesqu);frame.add(sceden_label);frame.add(sceden);
        frame.add(scegreen_label);frame.add(scegreen);frame.add(scepowden_label);frame.add(scepowden);
        frame.add(scetime_label);frame.add(scetime);frame.add(subsce);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        CallableStatement calls;
        ResultSet rs = null;
        boolean show;
        String roadnamestr,roaddivstr,zonenumstr,zonecharastr,densitystr,greenratestr,SQL;
        double roadlendou,roadwidou,waylightpowerdou,waylightimedou,wayyearenerconsdou;
        double squqredou,scelightpowerdou,scelightimedou,zoneyearenerconsdou,density;
        int waynumint,qwq;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if(e.getSource()==subway){
                roadnamestr = wayname.getText().trim(); roaddivstr = waylevel.getText().trim();
                waynumint = Integer.parseInt(waynum.getText().trim());
                roadlendou = Double.parseDouble(waylength.getText().trim());
                roadwidou = Double.parseDouble(waywidth.getText().trim());
                waylightpowerdou = Double.parseDouble(wayden.getText().trim());
                waylightimedou = Double.parseDouble(waytime.getText().trim());
                wayyearenerconsdou = roadlendou*roadwidou*waylightpowerdou*waylightimedou*0.365;
                show = findrecord(roadnamestr,roaddivstr,subway);
                if(show)
                    SQL = "update 道路照明 set waynum='"+waynumint+"',roadlength=round('"+roadlendou+"',2),roadwidth=round('"+roadwidou+"',2)," +
                            "lightpower=round('"+waylightpowerdou+"',2),lightime=round('"+waylightimedou+ "',2),yearenercons=round('"+
                            wayyearenerconsdou+"',2) " + "where roadname='"+roadnamestr+"'and roaddiv='"+roaddivstr+"';";
                else SQL = "insert 道路照明 values('"+roadnamestr+"','"+roaddivstr+"','"+waynumint+"',round('"+roadlendou+
                        "',2),round('"+roadwidou+"',2),round('"+waylightpowerdou+"',2),round('"+waylightimedou+"',2),round('"+wayyearenerconsdou+"',2));";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
            }
            else if(e.getSource()==subsce){
                zonenumstr = scename.getText().trim(); zonecharastr = scechara.getText().trim();
                squqredou = Double.parseDouble(scesqu.getText().trim());
                densitystr = sceden.getText().trim();
                density = Double.parseDouble(densitystr.replace("%",""))/100;
                greenratestr = scegreen.getText().trim();
                scelightpowerdou = Double.parseDouble(scepowden.getText().trim());
                scelightimedou = Double.parseDouble(scetime.getText().trim());
                zoneyearenerconsdou = squqredou*(1-density)*scelightpowerdou*scelightimedou*0.365;
                show = findrecord(zonenumstr,zonecharastr,subsce);
                if(show)
                    SQL = "update 景观照明 set squqre=round('"+squqredou+"',2),density='"+densitystr+
                            "',greenrate='"+greenratestr+"',lightpower=round('"+scelightpowerdou+"',2),lightime=round('"+scelightimedou+
                            "',2),yearenercons=round('"+zoneyearenerconsdou+"',2) where zonenum='"+zonenumstr+"'and zonechara='"+zonecharastr+"';";
                else SQL = "insert 景观照明 values('"+zonenumstr+"','"+zonecharastr+"',round('"+squqredou+"',2),'"+densitystr+
                        "','"+greenratestr+"',round('"+scelightpowerdou+"',2),round('"+scelightimedou+"',2),round('"+zoneyearenerconsdou+"',2));";
                stmt = con.prepareStatement(SQL);
                qwq = stmt.executeUpdate();
                if(qwq>0) JOptionPane.showMessageDialog(frame, "修改成功");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("light");
        }
    }

    public boolean findrecord(String name,String div,JButton btn){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if(btn.equals(subway))
                SQL = "select * from 道路照明 where roadname = '"+name+"' and roaddiv='"+div+"';";
            else if(btn.equals(subsce))
                SQL = "select * from 景观照明 where zonenum = '"+name+"' and zonechara='"+div +"';";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if (rs.next()){
                String i = rs.getString(1);
                System.out.println(i);
                return true;
            }
            else
                return false;
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("find light record\n");
        }
        return false;
    }
}