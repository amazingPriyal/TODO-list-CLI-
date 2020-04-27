import javax.print.attribute.SupportedValuesAttribute;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*;
import java.sql.PreparedStatement;
public class listmain {
   public static Connection myConn=null;
   public static Statement myStmt=null;
   public static ResultSet myResult=null;


    public static String title;
    public static String status;
    public static DateTimeFormatter dtf;
    public static LocalDateTime now;
    public static void main(String[] args){
        int Selectoption;
//
        do{
            System.out.println("1.put details \n 2.show details \n 3.updatestatus \n 4.delete \n 5.updateTitle \n 6.Exits");
            Scanner in1=new Scanner(System.in);
            Selectoption=in1.nextInt();
            switch (Selectoption){
                case 1:
                    putdetails();
                    break;
                case 2:
                    showdetails();
                    break;
            case 3:
                updatestatus();
                break;
            case 4:
                delete();
                break;
                case 5:
                    updateTitle();
                    break;
                case 6:
                   System.out.println("byeeeeeeeeeeee");

            }
        } while(Selectoption!=6);



    }

    public static void putdetails(){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter your title");

       dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       now = LocalDateTime.now();

        //System.out.println(dtf.format(now));
        status="false";

        try{

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododatabase", "root", "");
            String sql = "INSERT INTO dbtodo (Title, Date, status) values (?, ?, ?)";
            PreparedStatement statement = myConn.prepareStatement(sql);
            title=in.nextLine();
            statement.setString(1,title);
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
           statement.setDate(2,sqlDate);
            statement.setString(3,status);
            statement.executeUpdate();
            statement.close();
            myConn.close();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public static void showdetails(){

try{

    myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododatabase", "root", "");
    String sql = "SELECT *FROM dbtodo";
    PreparedStatement statement = myConn.prepareStatement(sql);
    myResult=statement.executeQuery();
    System.out.println("ID\t\tDATE\t\t\tSTATUS\t\tTITLE");
    while(myResult.next()){
        System.out.println(myResult.getInt("id")+"\t\t"+myResult.getString("date")+"\t\t"+myResult.getString("status")+"\t\t"+myResult.getString("Title"));


    }
}catch (Exception exc){
    exc.printStackTrace();
}


    }

    public static void updatestatus(){
        Scanner forstatus=new Scanner(System.in);
        try{

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododatabase", "root", "");
            String sql = "UPDATE dbtodo set status='true' where id=?";
            PreparedStatement statement = myConn.prepareStatement(sql);
            //title=in.nextLine();
            System.out.println("Pls enter the ID which u want to update :");
            int idstatus=forstatus.nextInt();
            statement.setInt(1,idstatus);

            statement.executeUpdate();
//            while(myResult.next()){
//                System.out.println(myResult.getString("Title")+" | "+myResult.getString("date")+" | "+myResult.getString("status"));
//            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }


    public static void delete(){

        Scanner forid = new Scanner(System.in);
        try{

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododatabase", "root", "");
            String sql = "delete from dbtodo where id=?";
            PreparedStatement statement = myConn.prepareStatement(sql);
            //title=in.nextLine();
            System.out.println("Enter id which you want to delete");
            int id=forid.nextInt();
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();
            myConn.close();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

    }


    public static void updateTitle(){
        Scanner forstatus=new Scanner(System.in);
        try{

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododatabase", "root", "");
            String sql = "UPDATE dbtodo set Title=? where id=?";
            PreparedStatement statement = myConn.prepareStatement(sql);
            //title=in.nextLine();
            System.out.println("Pls enter the new Title which u want to update :");
            String tat=forstatus.nextLine();
            statement.setString(1,tat);
            System.out.println("Pls enter the ID which u want to update :");
            int idstatus=forstatus.nextInt();
            statement.setInt(2,idstatus);

            statement.executeUpdate();

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }


}
