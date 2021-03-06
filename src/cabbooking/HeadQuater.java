/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabbooking;

import cabbooking.com.test.reportGenertors.Report;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*; 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.Desktop;
import java.net.URI;
/**
 *
 * @author Radhesh
 */



public class HeadQuater 
{

  
   
    
    
   public static String getLocationDescription(int loc)
   {
       String answer = null;
       if(loc==1)
       {
           answer="Ameerpet";
       }
       if(loc==2)
       {
           answer="Balanagar";
       }
       if(loc==3)
       {
           answer="Begumpet";
       }
       if(loc==4)
       {
           answer="Charminar";
       }
       if(loc==5)
       {
           answer="Gachibowli";
       }
       if(loc==6)
       {
           answer="Hakimpet";
       }
       if(loc==7)
       {
           answer="Himayatnagar";
       }
       if(loc==8)
       {
           answer="Kachiguda";
       }
       if(loc==9)
       {
           answer="Kompally";
       }
       if(loc==10)
       {
           answer="Kukatpally";
       }
       if(loc==11)
       {
           answer="Nampally";
       }
       if(loc==12)
       {
           answer="Malakpet";
       }
       if(loc==13)
       {
           answer="Medchal";
       }
       if(loc==14)
       {
           answer="Shamirpet";
       }
       if(loc==15)
       {
           answer="Shamshabad";
       }
       if(loc==16)
       {
           answer="Trimulgherry";
       }
       return answer;
   }
    
    
    
  public static int getLocationNumber(String str)
     {
       int answer = 0;
       if(str.equals("Ameerpet"))
       {
           answer=1;
       }
       if(str.equals("Balanagar"))
       {
           answer=2;
       }
       if(str.equals("Begumpet"))
       {
           answer=3;
       }
       if(str.equals("Charminar"))
       {
           answer=4;
       }
       if(str.equals("Gachibowli"))
       {
           answer=5;
       }
       if(str.equals("Hakimpet"))
       {
           answer=6;
       }
       if(str.equals("Himayatnagar"))
       {
           answer=7;
       }
       if(str.equals("Kachiguda"))
       {
           answer=8;
       }
       if(str.equals("Kompally"))
       {
           answer=9;
       }
       if(str.equals("Kukatpally"))
       {
           answer=10;
       }
       if(str.equals("Nampally"))
       {
           answer=11;
       }
       if(str.equals("Malakpet"))
       {
           answer=12;
       }
       if(str.equals("Medchal"))
       {
           answer=13;
       }
       if(str.equals("Shamirpet"))
       {
           answer=14;
       }
       if(str.equals("Shamshabad"))
       {
           answer=15;
       }
       if(str.equals("Trimulgherry"))
       {
           answer=16;
       }
       
       return answer;
   }
   
      public static int Distance(String pickup, String drop)
    {
        return Math.abs(getLocationNumber(pickup) - getLocationNumber(drop));
    }  
    public static int CalculateFare(String pickup, String drop)
    {
        return 80*(Distance(pickup,drop));
    }
    public static boolean isCabAvailable(Customer ob)
    {
        
        boolean answer = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
      try
      {       
      con= dbm2.dbconnect();
      
      String sql = "SELECT * FROM driver WHERE ISBUSY=? ";
            ps =con.prepareStatement(sql);
            ps.setInt(1,0);
              rs=ps.executeQuery();
              
              while(rs.next())
              {
        
                  answer = true;   
                  break;
              }      
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
      finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
         
        return answer;
    }
   
    
    public static boolean CanBookCab(Customer ob,int fare)
    {
       int balance = ob.getBalance();
       return !(balance < 300 || balance < fare);
    }
    
  
    @SuppressWarnings("empty-statement")
    public static Driver FindNearestDriverWithHighestRating(String Cust_Loc_Description)
    {
        int answer;
        
       answer = -1;
        Integer curr_distance = Integer.MAX_VALUE;
        int curr_rating = -1;
        int cust_loc = getLocationNumber(Cust_Loc_Description);
        
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        
      try
      {
          con= dbm2.dbconnect();
          String sql = "SELECT DRIVERID,RATING,LOCATION FROM driver WHERE ISBUSY = ?";
           ps =con.prepareStatement(sql);
            ps.setInt(1,0);
              rs=ps.executeQuery();
              
              while(rs.next())
              {
                   int driver_loc = rs.getInt("LOCATION");
                   String driver_loc_description = getLocationDescription(driver_loc);
                   if(Distance(Cust_Loc_Description,driver_loc_description) < curr_distance)
                   {
                       answer = rs.getInt("DRIVERID");
                       curr_rating = rs.getInt("RATING");
                       curr_distance = Distance(Cust_Loc_Description,driver_loc_description);
                   } 
              }
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
;
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
;
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
;
}
      
      
        Connection conn = null;
        PreparedStatement ps1 = null;
        ResultSet rs1= null;
        
      try
      {
           conn = dbm2.dbconnect();
    

          String sql = "SELECT DRIVERID,RATING,LOCATION FROM driver WHERE ISBUSY = ?";
      
             ps1 =conn.prepareStatement(sql);
            ps1.setInt(1,0);
               rs1=ps1.executeQuery();
              
              while(rs1.next())
              {
                  
                  int driver_loc = rs1.getInt("LOCATION");
                   String driver_loc_description = getLocationDescription(driver_loc);
                   
                   if(Distance(Cust_Loc_Description,driver_loc_description) == curr_distance)
                   {
                       int driver_rating = rs1.getInt("RATING");
                       
                       if(driver_rating > curr_rating)
                       {
                            answer = rs1.getInt("DRIVERID");
                           curr_rating = rs1.getInt("RATING");
                           
                       }
                   }
                       
                   
                   
              }
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
         finally {
    try { if (rs1 != null) rs1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
;
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
     
      
      
      
      
      return retriveDriverData(answer);
      
    }
    
    public static String CalculateTripEndtime(String pickup_loc,String drop_loc)
    {

        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
     LocalDateTime answer = LocalDateTime.now().plusMinutes(CalculateTripDuration(pickup_loc,drop_loc));
     
     return dtf.format(answer);
        
    }
    public static int CalculateTripDuration(String pickup_loc,String drop_loc)
    {
        return Distance(pickup_loc,drop_loc);
    }
    @SuppressWarnings("empty-statement")
    public static Booking AddBooking(int Customer_Location,int Drop_Location,Customer currentuser,Driver tripdriver)      
    {
         String referencenumber=String.valueOf(new Date().getTime());
        Booking ob = new Booking(referencenumber,currentuser.getUsername(),tripdriver.getDriverId(),String.valueOf(Customer_Location),String.valueOf(Drop_Location),HeadQuater.GetCurrentTime(),CalculateTripEndtime(getLocationDescription(Customer_Location),getLocationDescription(Drop_Location)),0,0);
          AddBookingData(ob);
        UpdateDriverStatusStartTrip(tripdriver);
           UpdateCustomerStatusStartTrip(currentuser);
           return ob;
    }
    
    
    public static void UpdateDriverStatusStartTrip(Driver ob)
    {
       
        ob.setIsBusy(1);
        updateDriverData(ob);  
    }
    
    public static void UpdateDriverStatusEndTrip(String driverid,int location)
    {
        Connection con = null;
        PreparedStatement ps = null ;
       
        try
        {
             con= dbm2.dbconnect();
                 String sqlQuery = "UPDATE driver SET ISBUSY = ? , LOCATION = ? WHERE DRIVERID = ?";
                ps =con.prepareStatement(sqlQuery);
                 ps.setInt(1,0);
                 ps.setInt(2, location);
                ps.setInt(3,Integer.parseInt(driverid));
                ps.executeUpdate();
        }
        catch(SQLException | NumberFormatException e){
            System.out.println( e.getMessage());
        }
          finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
        
    }
    
    public static void Randomize()
    {
        
        ArrayList<Integer> freedriverlist = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        
        try
        {
             conn= dbm2.dbconnect();
             String sql = "SELECT DRIVERID FROM driver WHERE ISBUSY = ?";
      
           ps1 =conn.prepareStatement(sql);
            ps1.setInt(1,0);
               rs1=ps1.executeQuery();
              
              while(rs1.next())
              {
                  int driver_id = rs1.getInt("DRIVERID");
                  freedriverlist.add(driver_id);
                  
              }
            
            
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
         
        finally {
    try { if (rs1 != null) rs1.close(); }
    catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } 
    catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } 
    catch (SQLException e) {System.out.println(e.getMessage());}
}
        
        int numberofdrivers = freedriverlist.size();
        
        if(numberofdrivers == 0 )
        {
            return ;
        }
        int delta = 16/numberofdrivers;
        
        int new_location = 1;
        
        
        
        for (Integer freedriverlist1 : freedriverlist) 
        {
             Connection con = null;
               PreparedStatement ps = null;
            try {
                con= dbm2.dbconnect();
                String sqlQuery = "UPDATE driver SET LOCATION = ? WHERE DRIVERID = ?";
                 ps =con.prepareStatement(sqlQuery);
                ps.setInt(1,new_location);
                ps.setInt(2, freedriverlist1);
                ps.executeUpdate();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
               finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
            
            new_location += delta;
        }

    }
    public static void ChangeUserBalance(String userid,int Fare)
    {
        
        int userbalance  = 0;
        Connection conn = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        
         try
        {
            conn= dbm.dbconnect();
             String sql = "SELECT BALANCE FROM customer WHERE USERNAME =?";
      
             ps1 =conn.prepareStatement(sql);
            ps1.setString(1,userid);
              rs1=ps1.executeQuery();
            userbalance  = rs1.getInt("BALANCE");
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs1 != null) rs1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
         
         
         int newuserbalance = userbalance - Fare;
         Connection con = null;
        PreparedStatement ps = null;   
           try
        {
                 con= dbm.dbconnect();
                 String sqlQuery = "UPDATE customer SET BALANCE = ? WHERE USERNAME = ?";
                 ps =con.prepareStatement(sqlQuery);
                 ps.setInt(1,newuserbalance);
                ps.setString(2,(userid));
                ps.executeUpdate();
        }
         catch(SQLException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
                finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
         
    }
     
   public static void UpdateCustomerStatusStartTrip(Customer ob)
   {
      
       ob.setIsBusy(1);
       updateCustomerData(ob);
   }
    
   public static void UpdateCustomerStatusEndTrip(String userid)
   {
       Connection con = null ; 
       PreparedStatement ps = null; 
       try
        {
              con= dbm.dbconnect();
                 String sqlQuery = "UPDATE customer SET ISBUSY = ? WHERE USERNAME = ?";
                ps =con.prepareStatement(sqlQuery);
                 ps.setInt(1,0);
                ps.setInt(2,Integer.parseInt(userid));
                ps.executeUpdate();
        }
        catch(SQLException | NumberFormatException e){
            System.out.println( e.getMessage());
        }
             finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
   }
   
   
   public static String GetCurrentTime()
   {
       
       
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
     LocalDateTime now = LocalDateTime.now();
     
     return dtf.format(now);
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
   // Date date = new Date();  
   }
   public static int Comparetime(String s1,String s2)
   {
       return s1.compareTo(s2);
   }
   
   
   public static void EndTrip(Customer currentuser,Booking ob)
   {
           String pickuploc =HeadQuater.getLocationDescription(Integer.parseInt(ob.getPickUpLocation()));
           String droploc = HeadQuater.getLocationDescription(Integer.parseInt(ob.getDropLocation()));
       
       currentuser = HeadQuater.retriveCustomerData(currentuser.getUsername());
       ob = HeadQuater.retriveBookingData(ob.getReferenceNumber());
       
       
       try
       {
           ChangeBookingStatus(ob.getUserName());
       UpdateCustomerStatusEndTrip(ob.getUserName());
      UpdateDriverStatusEndTrip(String.valueOf(ob.getDriverId()),Integer.parseInt(ob.getDropLocation()));
      ChangeUserBalance(ob.getUserName(),HeadQuater.CalculateFare(pickuploc,droploc));
      SendEndTripEmail(currentuser,ob); 
       }
       catch(NumberFormatException e)
       {
         System.out.println(e.getMessage());
           
       }
   }
   
   
   public static void TimeChecker()
   {
       
       Connection conn = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
       
        String currenttime = GetCurrentTime();
        
        ArrayList<String> toend = new ArrayList();
        
        
           try
        {
            conn= dbm3.dbconnect();
             String sql = "SELECT * FROM booking WHERE ISTRIPENDED = ?";
             ps1 =conn.prepareStatement(sql);
             ps1.setInt(1,0);
             rs1=ps1.executeQuery();
             
             while(rs1.next())
             {
                 String tripendtime = rs1.getString("TRIPENDTIME");
                 if(Comparetime(currenttime,tripendtime)>=0)
                 {
                     toend.add(rs1.getString("REFERENCENUMBER"));
                 }
                 
             }
             
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
          
           finally {
    try { if (rs1 != null) rs1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       
       
       for(int i = 0; i < toend.size();i++)
       {
          
          
               Booking ob = retriveBookingData(toend.get(i));
               Customer ob2 = retriveCustomerData(ob.getUserName());
               EndTrip(ob2,ob);
               
           
           
                   
       }
   }
   
   public static void removedriver(Driver ob)
   {
        Connection conn=dbm2.dbconnect();
     PreparedStatement ps1=null;
      try{
     String query="DELETE FROM driver WHERE DRIVERID= ? ";
     ps1=conn.prepareStatement(query);
      ps1.setInt(1,ob.getDriverId());
      ps1.executeUpdate();
      }
      catch(java.sql.SQLException e){ }
      finally{
         
         try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
      }
   }
   
   public static void adddriver(Driver ob)
   {
       
              Connection con = null;
        PreparedStatement ps = null;
        
      try
      {
            con = dbm2.dbconnect();
           String query="insert into driver values(?,?,?,?,?,?,?,?,?)";
          ps=con.prepareStatement(query);
          ps.setString(1,ob.getDriverName());
          ps.setInt(2,ob.getDriverId());
          ps.setString(3,ob.getPhoneNumber());
          ps.setString(4,ob.getRating());
          ps.setString(5,ob.getVehicleNumber());
          ps.setString(6,ob.getVehicleName());
          ps.setInt(7,ob.getLocation());      
          ps.setInt(8,0);
          ps.setInt(9,ob.getNumberoftrips());
          ps.execute();
      }
        catch(SQLException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
             finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
   }
   
   public static Driver retriveDriverData(int driverid)
   {
       
       
         Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Driver ob = null;
        
          try
        {
            connect= dbm2.dbconnect();
             String sql = "SELECT * FROM DRIVER WHERE DRIVERID = ?";
             ps=connect.prepareStatement(sql);
             ps.setInt(1,driverid);
             rs=ps.executeQuery(); 
             ob = new Driver(rs.getString("DRIVERNAME"),driverid,rs.getString("PHONENUMBER"),rs.getString("RATING"),rs.getString("VEHICLENUMBER"),rs.getString("VEHICLENAME"),rs.getInt("LOCATION"),rs.getInt("ISBUSY"),rs.getInt("NUMBEROFBOOKINGS"));
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (connect != null) connect.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       
          return ob;
   }
   
   public static boolean canEditProfile(String emailid,String address,String phonenumber)
   {
        if(emailid.length()==0)
       {
           JOptionPane.showMessageDialog(null, "Enter Valid Email ID");
           return false;
       }
         if(verify_email(emailid) == false)
         {
             JOptionPane.showMessageDialog(null, "Enter Valid Email ID");
           return false;
         }
       
       if(phonenumber.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter Phone Number");
             return false;
         }
         if(verify_phonenumber(phonenumber) == false)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Phone Number");
           return false;
         }
         
         if("Enter Address".equals(address) || address.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter Valid Address");
             return false;
         } 
         
       return true;
   }

   public static boolean verify_email(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean verify_name(String name) {
        String Regex = "^[\\p{L} .'-]+$";
        Pattern pat = Pattern.compile(Regex); 
        if (name == null) 
            return false;
        return pat.matcher(name).matches();
    }
    public static boolean verify_phonenumber(String phonenumber) {
        String Regex = "^[6-9]\\d{9}$";
        Pattern pat = Pattern.compile(Regex); 
        if (phonenumber == null) 
            return false;
        return pat.matcher(phonenumber).matches();
    }
    public static boolean verify_passwordValidity(String password) {
        String Regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pat = Pattern.compile(Regex); 
        if (password == null) 
            return false;
        return pat.matcher(password).matches();
    }
   public static boolean canSignUp(String name,Date dob,String emailid,String userid,String password,String phoneNumber,String address)
   {
      
       
       if("Enter Name".equals(name))
       {

           JOptionPane.showMessageDialog(null, "Enter Valid Name");
           return false;
       }
        if(verify_name(name) == false)
         {  
             JOptionPane.showMessageDialog(null, "Enter Valid Name");
           return false;
         }
       
       Date d1 = null;
       Date presentdate = new Date();
       
       if(dob == d1)
       {
           JOptionPane.showMessageDialog(null, "Enter Valid Date Of Birth");
           return false;
       }
       
        try 
        {
                if(presentdate.before(dob))
               {

               JOptionPane.showMessageDialog(null, "Enter Valid Date Of Birth");
               return false;
               }
         
        }
        catch(HeadlessException e)
        {

                JOptionPane.showMessageDialog(null, "Enter Valid Date Of Birth");
                return false;
        }
        
         if("Enter Email ID".equals(emailid))
       {
           JOptionPane.showMessageDialog(null, "Enter Valid Email ID");
           return false;
       }
         if(verify_email(emailid) == false)
         {
             JOptionPane.showMessageDialog(null, "Enter Valid Email ID");
           return false;
         }
         
         if("Enter User ID".equals(userid))
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid User Name");
             return false;
         }
         
         if(doesUserIdExists(userid))
         {
              JOptionPane.showMessageDialog(null, "User ID Already in Use");
             return false;
         }
         
         if(password.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter Valid Password");
             return false;
         }
         if(verify_passwordValidity(password) == false)
         {
             String base = "Password Policy is : \n";
             String one = "A digit must occur at least once \n";
             String two = "A lower case letter and an upper case letter must occur at least once \n";
             String three = "A special character must occur at least once \n";
             String four = "no whitespace allowed in the entire string";
             String five = "Length is atleast 8 characters \n";
             
             String answer = base + five + one + two + three + four;
             
             
             JOptionPane.showMessageDialog(null,answer);
             return false;
         }
         
         if("Enter Phone Number".equals(phoneNumber))
         {
             JOptionPane.showMessageDialog(null, "Enter Phone Number");
             return false;
         }
         if(verify_phonenumber(phoneNumber) == false)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Phone Number");
           return false;
         }
         
         if("Enter Address".equals(address))
         {
             JOptionPane.showMessageDialog(null, "Enter Valid Address");
             return false;
         }
         
         
       
       return true;
   }
   
   public static boolean doesUserIdExists(String userid)
   {
       boolean answer = false;
       
       Connection conn = null;
       PreparedStatement ps1 = null;
       ResultSet rs1 = null;
         try
        {
            conn= dbm.dbconnect();
             String sql = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
             ps1 =conn.prepareStatement(sql);
             ps1.setString(1,userid);
             rs1=ps1.executeQuery();
             
            while(rs1.next())
            {
                answer = true;
            }
             
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs1 != null) rs1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       return answer;
       
   }

    /**
     *
     * @param ob
     */
    public static void AddCustomer(Customer ob)
   {
             Connection con = null;
        PreparedStatement ps = null;      
      try
      {
            con = dbm.dbconnect();
           String query="insert into CUSTOMER values(?,?,?,?,?,?,?,?,?)";
          ps=con.prepareStatement(query);
          ps.setString(1,ob.getName());
          ps.setString(2,ob.getAddress());
          ps.setString(3,ob.getEmail());
          ps.setString(4, ob.getUsername());
          ps.setString(5,ob.getPassword());
          ps.setInt(6,ob.getBalance());
          ps.setString(7,ob.getDob());
         ps.setInt(8,ob.getIsBusy());
          ps.setString(9,ob.getPhonenumber());
         ps.execute();
      }
        catch(SQLException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
             finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}    
   }
   
   public static Customer retriveCustomerData(String userid)
   {
       Customer ob = null;      
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
             try
        {
            connect= dbm.dbconnect();
             String sql = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
             ps=connect.prepareStatement(sql);
             ps.setString(1,userid);
             rs=ps.executeQuery();             
             ob = new Customer(rs.getString("NAME"),rs.getString("ADDRESS"),rs.getString("EMAIL"),userid,rs.getString("PASSWORD"),rs.getInt("BALANCE"),rs.getString("DATE OF BIRTH"),rs.getInt("ISBUSY"),rs.getString("PHONENUMBER"));     
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (connect != null) connect.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       return ob;
   }
   public static void updateCustomerData(Customer ob)
   {
        Connection con = null;
      PreparedStatement ps = null;      
      try
      {
     con= dbm.dbconnect();
     String sql = "UPDATE customer SET BALANCE =? , ISBUSY =? , EMAIL =? , PASSWORD =? , PHONENUMBER =? WHERE USERNAME =?";     
       ps =con.prepareStatement(sql);        
       ps.setInt(1, ob.getBalance());
       ps.setInt(2,ob.getIsBusy());
       ps.setString(3,ob.getEmail());
       ps.setString(4,ob.getPassword());
       ps.setString(5,ob.getPhonenumber());
       ps.setString(6,ob.getUsername());
             ps.executeUpdate();
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
      finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    
    
} 
   }
   public static boolean isCustomerPasswordCorrect(String userid,String password)
   {
       Customer ob = retriveCustomerData(userid);
       return ob.getPassword().equals(password);
   }
   public static boolean isAdminUser(String userid)
   {
       return userid.equals("Radhesh")||userid.equals("Amogh")||userid.equals("Simran")||userid.equals("Jalaj");
   }
    public static boolean isAdminPasswordCorrect(String userid,String password)
   {
       if(userid.equals("Radhesh")||userid.equals("Amogh")||userid.equals("Simran")||userid.equals("Jalaj"))
       {
           if(password.equals("admin"))
           {
               return true;
           }
       }  
       return false;
   }
 public static void ChangeBookingStatus(String userid)
   {
       Connection con = null ; 
       PreparedStatement ps = null; 
       try
        {
              con= dbm3.dbconnect();
                 String sqlQuery = "UPDATE booking SET ISTRIPENDED = ? WHERE USERNAME = ?";
                ps =con.prepareStatement(sqlQuery);
                 ps.setInt(1,1);
                ps.setInt(2,Integer.parseInt(userid));
                ps.executeUpdate();
        }
        catch(SQLException | NumberFormatException e){
            System.out.println( e.getMessage());
        }
             finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
   }
   public static void AddBookingData(Booking ob)
   {
             Connection con = null;
        PreparedStatement ps = null;
        
      try
      {
            con = dbm3.dbconnect();
           String query="insert into BOOKING values(?,?,?,?,?,?,?,?,?)";
          ps=con.prepareStatement(query);
          ps.setString(1,ob.getReferenceNumber());
          ps.setString(2,ob.getUserName());
          ps.setInt(3,ob.getDriverId());
          ps.setString(4, ob.getPickUpLocation());
          ps.setString(5,ob.getDropLocation());
          ps.setString(6,ob.getTripStartTime());
          ps.setString(7,ob.getTripEndTime());
         ps.setInt(8,ob.getIsTripEnded()); 
         ps.setInt(9,ob.getRating());
          ps.execute();
      }
        catch(SQLException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
             finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
      
      
      
   }
   
   public static Booking retriveBookingData(String referno)
   {
       Booking ob = null;
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         try
        {
            connect= dbm3.dbconnect();
             String sql = "SELECT * FROM BOOKING WHERE REFERENCENUMBER = ?";
             ps=connect.prepareStatement(sql);
             ps.setString(1,referno);
             rs=ps.executeQuery();            
             ob = new Booking(referno,rs.getString("USERNAME"),rs.getInt("DRIVERID"),rs.getString("PICKUPLOCATION"),rs.getString("DROPLOCATION"),rs.getString("TRIPSTARTTIME"),rs.getString("TRIPENDTIME"),rs.getInt("ISTRIPENDED"),rs.getInt("RATING"));

        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (connect != null) connect.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       return ob;
   } 
    /**
     *
     * @param ob
     */
    public static void updateBookingData(Booking ob)
   {
        Connection con = null;
      PreparedStatement ps = null;     
      try
      {
     con= dbm3.dbconnect();
     String sql = "UPDATE booking SET ISTRIPENDED =?,RATING=? WHERE REFERENCENUMBER =?";
          
       ps =con.prepareStatement(sql);
           
       ps.setInt(1, ob.getIsTripEnded());
       ps.setInt(2, ob.getRating());
       ps.setString(3,ob.getReferenceNumber());
       
           ps.executeUpdate();
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
      finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}   
}
     
   }
    public static void updateDriverData(Driver ob)
   {
        Connection con = null;
      PreparedStatement ps = null;  
      try
      {
     con= dbm2.dbconnect();
     String sql = "UPDATE driver SET ISBUSY =? , PHONENUMBER =? , LOCATION =? ,RATING = ?, NUMBEROFBOOKINGS = ? WHERE DRIVERID =?";
          
       ps =con.prepareStatement(sql);
           
       ps.setInt(1, ob.getIsBusy());
       ps.setString(2,ob.getPhoneNumber());
       ps.setInt(3,ob.getLocation());
       ps.setString(4,ob.getRating());
       ps.setInt(5,ob.getNumberoftrips());
       ps.setInt(6,ob.getDriverId());
          ps.executeUpdate();
      }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
      finally {
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (con != null) con.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
 
}
   }
   
    public static int numberofbookings()
    {
               Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int answer = 0;
        
         try
        {
            connect= dbm3.dbconnect();
             String sql = "SELECT COUNT(*) AS total FROM booking";
             ps=connect.prepareStatement(sql);
             rs=ps.executeQuery();       
             answer = rs.getInt("total");
            
       }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs != null) rs.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps != null) ps.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (connect != null) connect.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
         
         return answer;
         
    }    
   public static void CallRandomize()
   {
       if(numberofbookings()%3 == 0)
       {
           Randomize();
       }
   }
   
   public static void SendConfirmationEmail(Customer currentuser,Driver tripdriver,Booking currentbooking)
   {
       Report obj=new Report();
       String pickuploc = HeadQuater.getLocationDescription(Integer.parseInt(currentbooking.getPickUpLocation()));
       String droploc = HeadQuater.getLocationDescription(Integer.parseInt(currentbooking.getDropLocation()));
       String body = "<html><body>";
       
       
       body += "<h1>Dear " + currentuser.getName() + " " + "</h1>";
       body += "<h2>Your Booking is Confirmed! </h2>";
       body += "<h3>Booking Reference : "+currentbooking.getReferenceNumber()+"</h3>";
       body += "<h3>Pickup Location : "+ pickuploc+"</h3>";
       body += "<h3>Drop Location : "+ droploc+"</h3>";
       body += "<h3>Trip Start Time : "+ currentbooking.getTripStartTime()+"</h3>";
       body += "<h3>Estimated Fare : "+ String.valueOf(CalculateFare(pickuploc,droploc))+"</h3>";
       body += "<h3>Driver Name : "+ tripdriver.getDriverName()+"</h3>";
       body += "<h3>Car : "+ tripdriver.getVehicleName()+"</h3>";
       body += "<h3>License Plate Number : "+ tripdriver.getVehicleNumber()+"</h3>";
       body += "<h2>Thanks & Best Regards,</h2>";
       body += "<h2>The Cab Booking Application Team</h2>";
       body+="</body></html>";
       
       
       
           
       obj.sendMail(currentuser.getEmail(),body);
                 
       
   
   }
   
   public static void SendEndTripEmail(Customer currentuser,Booking currentbooking)
   {
       
       Report obj=new Report();
       String pickuploc = HeadQuater.getLocationDescription(Integer.parseInt(currentbooking.getPickUpLocation()));
       String droploc = HeadQuater.getLocationDescription(Integer.parseInt(currentbooking.getDropLocation()));
       String body = "<html><body>";
       body += "<h1>Thanks for Riding with us! </h1>";
       body += "<h2>We hope you enjoyed your ride </h2>";
       body += "<h2>Total Fare of " + String.valueOf(CalculateFare(pickuploc,droploc))+ " Rupees has been deducted from your account </h3>";
       body += "<h2>For any kind of feedback, please do not hesitate to use the help section of our app</h2>";
        body += "<h2></h2>";
        body += "<h2>Thanks & Best Regards,</h2>";
       body += "<h2>The Cab Booking Application Team</h2>";
       body+="</body></html>";
       
       obj.sendMail(currentuser.getEmail(),body);    
       
      
       
   }
   
   public static boolean canAddDriver(String name,String driverid,String phonenumber,String vehiclenumber,String vehiclename,String location)
   {
       if("Enter Name".equals(name) || name.length() == 0)
       {

           JOptionPane.showMessageDialog(null, "Enter Valid Name");
           return false;
       }
        if(verify_name(name) == false)
         {  
             JOptionPane.showMessageDialog(null, "Enter Valid Name");
           return false;
         }
        
          if("Enter Driver ID".equals(driverid) || driverid.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Driver ID");
             return false;
         }
          
         if("Enter Phone Number".equals(phonenumber))
         {
             JOptionPane.showMessageDialog(null, "Enter Phone Number");
             return false;
         }
         if(verify_phonenumber(phonenumber) == false)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Phone Number");
           return false;
         }
         
         if(vehiclename.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Vehicle Number");
           return false;
         }
         
         if(vehiclenumber.length() == 0)
         {
             JOptionPane.showMessageDialog(null, "Enter a Valid Vehicle Number");
           return false;
         }
         

        if(location == null)
        {
            JOptionPane.showMessageDialog(null, "Select a location");
            return false;
        }
        
        return true;
   }
   
       public static boolean doesDriverIdExists(Driver ob)
   {
       boolean answer = false;
       
       Connection conn = null;
       PreparedStatement ps1 = null;
       ResultSet rs1 = null;
         try
        {
            conn= dbm2.dbconnect();
             String sql = "SELECT * FROM DRIVER WHERE driverid = ?";
             ps1 =conn.prepareStatement(sql);
             ps1.setInt(1,ob.getDriverId());
             rs1=ps1.executeQuery();
             
            while(rs1.next())
            {
                answer = true;
                break;
            }
             
        }
         catch(SQLException e){
            System.out.println(e.getMessage());
        }
            finally {
    try { if (rs1 != null) rs1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (ps1 != null) ps1.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
    try { if (conn != null) conn.close(); } catch (SQLException e) {System.out.println(e.getMessage());}
}
       
       return answer;
       
   }  
       public static void modifydriverrating(int newrating,Driver ob,Booking currenttrip)
       {
           
           double newdriverrating =( Double.parseDouble(ob.getRating()) * (ob.getNumberoftrips())  + newrating) / (ob.getNumberoftrips() + 1);  
           ob.setRating(String.valueOf(newdriverrating));
           ob.setNumberoftrips(ob.getNumberoftrips() + 1);
           System.out.println(ob.toString());
           updateDriverData(ob);
           currenttrip.setRating(newrating);
           updateBookingData(currenttrip);

       }
       
       public static void googleMapDirections(String pickuplocation,String droplocation)
       {
           String base = "https://www.google.com/maps/dir/?api=1&";
           String origin = "origin=" + pickuplocation + "+Hyderabad&";
           String destination = "destination=" + droplocation + "+Hyderabad&";
           String travelmode = "travelmode=driving";    
           String url = base + origin + destination + travelmode;
           
           
           try
           {
                 if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
               {
                   Desktop.getDesktop().browse(new URI(url));
               }
            }   
                 
           }
           catch (Exception e) {
               // TODO Auto-generated catch block
                    System.out.println(e.getMessage());
            }
          
           
           
           
       }
       
       public static boolean addamountchecker(int amount,String password,Customer currentuser)
       {
           
         
        if(password.length() == 0 )
       {
        JOptionPane.showMessageDialog(null, "Enter Valid Password");
        return false;
        }
         if(amount <= 0)
       {
             JOptionPane.showMessageDialog(null, "Kindly Enter a Positive amount");
             return false;
       }
        if(! password.equals(currentuser.getPassword()))
        {
            JOptionPane.showMessageDialog(null ,"Incorrect password!");
            return false;
            
        }
           
           
           
           return true;
       }
       
}