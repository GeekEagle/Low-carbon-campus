import java.sql.*;

public class tracalsum {
    String city,vehtype;
    int year;

    public tracalsum(String city,int year,String vehtype){
        this.city = city;
        this.year = year;
        this.vehtype = vehtype;
    }

    public void calsum(String type){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        CallableStatement calls;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            if(type.equals("社会车辆")) {
                String SQL = "{call calsocsum(?,?)}";
                calls = con.prepareCall(SQL);
                calls.setString(1, city);
                calls.setInt(2, year);
                calls.execute();
                calls.close();
            }
            else if(type.equals("出租车")){
                String SQL = "{call caltaxisum(?,?)}";
                calls = con.prepareCall(SQL);
                calls.setString(1, city);
                calls.setInt(2, year);
                calls.execute();
                calls.close();
            }
            else if(type.equals("公交车")){
                String SQL = "{call calbussum(?,?)}";
                calls = con.prepareCall(SQL);
                calls.setString(1, city);
                calls.setInt(2, year);
                calls.execute();
                calls.close();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("calsum\n");
        }
        totalsum();
    }

    public int renewsum(double phvoldis,double garburndis){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        CallableStatement calls;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            String SQL = "{call renewsum(?,?,?,?)}";
            calls = con.prepareCall(SQL);
            calls.setString(1,city);calls.setInt(2,year);
            calls.setDouble(3,phvoldis);calls.setDouble(4,garburndis);
            calls.execute();calls.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("renewsum\n");
            return 0;
        }
        return 1;
    }

    public void totalsum(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        CallableStatement calls;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            String SQL = "{call totalsum(?,?)}";
            calls = con.prepareCall(SQL);
            calls.setString(1,city);calls.setInt(2,year);
            calls.execute();calls.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("totalsum\n");
        }
        renewsum(0,0);
        statistics();
    }

    public void statistics(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=practice";
        Connection con = null;
        //PreparedStatement stmt = null;
        ResultSet rs = null;
        CallableStatement calls;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            String SQL = "{call statistic(?,?)}";
            calls = con.prepareCall(SQL);
            calls.setString(1,city);calls.setInt(2,year);
            calls.execute();calls.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("statistics\n");
        }
    }
}
