import com.sun.source.tree.SwitchTree;

import java.util.*;
import java.sql.*;

public class Atm {
    private static int amount1;

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/atm";
            String user = "root";
            String password = "";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connection Worked");

            Statement stmt = con.createStatement();

            // Creating a Table
//            String CreateTableQry = "CREATE TABLE Atm_Account(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,Username VARCHAR(100), Pin INT,Amount INT)";
//            stmt.execute(CreateTableQry);
//            System.out.println("Table Created");
//
            //Inserting vary commandline1
            Scanner input = new Scanner(System.in);

            System.out.println("Please select the action you want to perform ");
//            System.out.println("SELECT \n---1>>> Create an Account\n---2>>> Login Account\n");
            System.out.println("\n---1>>> Login Account\n");

            String x = input.nextLine();
            int Value = Integer.parseInt(x);

            switch (Value) {
                case 0:
                    System.out.println("Enter Username of Your Choose");
                    String username = input.nextLine();

                    System.out.println("Enter Pin of Your Choose");
                    String pin = input.nextLine();


                    String InsQry = "INSERT INTO Atm_Account(Username,Pin) VALUES(?,?)";
                    PreparedStatement ps = con.prepareStatement(InsQry);
                    ps.setString(1, username);
                    ps.setString(2, pin);
                    ps.execute();
                    System.out.println("Account has been Created");

                case 1:
//
                    //Reading
                    String readQry = "SELECT * from Atm_Account";
                    ResultSet rs = stmt.executeQuery(readQry);
                    String name = "";
                    String num ="";
                    while (rs.next()) {
                        int amount1;
                        name = rs.getString("Username");
                        num = rs.getString("Pin");
                        amount1 = rs.getInt("Amount");
//                        System.out.println(name + " uses " + num + " your amout");
//                        System.out.println("Accounts Accessed");
                    }


                    System.out.println("Please Enter the USERNAME:");
                    //Tumuhaise Michael
                    String your_username = input.nextLine();

                    System.out.println("Please Enter the PIN:");
                    //1234567890
                    String your_pin = input.nextLine();

                    if (name.equals(your_username) && num.equals(your_pin)) {
                        System.out.println("Your have logged in");

                        System.out.println("Please select the action you want to perform ");
                        System.out.println("SELECT \n---1>>> Deposit\n---2>>> Withdraw\n---3>>> CheckBalance\n");
                        int Amount = amount1;
                        int Withdraw;
                        int Removed;

                        String y = input.nextLine();
                        int Values = Integer.parseInt(y);

                        switch (Values) {
                            case 1:


                                String reary = "SELECT * from Atm_Account";
                                ResultSet rss = stmt.executeQuery(readQry);
                                String na = "";
                                String n = "";
                                int Amounts=0;

                                while (rss.next()) {
                                    na = rss.getString("Username");
                                    n = rss.getString("Pin");
                                    Amounts = rss.getInt("Amount");

                                }
                                System.out.println("This is the deposit option");
                                System.out.println("Enter amount to deposit");
                                String amount = input.nextLine();
                                int New_Amount = Integer.parseInt(amount);

                                Amount = New_Amount + Amounts;

                                System.out.println("Your total balance is: " + Amount);

//
                                String InsA = "UPDATE Atm_Account SET Amount=? WHERE Username=?";
                                PreparedStatement pst = con.prepareStatement(InsA);
                                pst.setInt(1, Amount);
                                pst.setString(2, your_username);
                                pst.execute();
                                System.out.println("Depoist Complete");
                                break;


                            case 2:
                                //Reading
                                String readry = "SELECT * from Atm_Account";
                                ResultSet r = stmt.executeQuery(readQry);
                                int amounts = 0;
                                while (r.next()) {
                                    amounts = r.getInt("Amount");
                                }
                                System.out.println("This is the withdraw option");
                                System.out.println("Enter amount to withdraw");
                                String removed = input.nextLine();
                                Removed = Integer.parseInt(removed);
                                Withdraw = amounts - Removed;
                                System.out.println("your current amount:" + Withdraw);

                                String InsW = "UPDATE Atm_Account SET Amount=? WHERE Username=?";
                                PreparedStatement pstt = con.prepareStatement(InsW);
                                pstt.setInt(1, Withdraw);
                                pstt.setString(2, your_username);
                                pstt.execute();
                                System.out.println("Withdraw Complete");
                                break;

                            case 3:
                                String reay = "SELECT * from Atm_Account";
                                ResultSet rt = stmt.executeQuery(readQry);
                                int Bal = 0;
                                while (rt.next()) {
                                    Bal = rt.getInt("Amount");
                                }
                                System.out.println(Bal);
                        }
                    } else {
                        System.out.println("Your Account Details Are Invaild");
                    }
            }
            }catch (ClassNotFoundException e) {
            System.out.println("SQL Expection failed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

    }

