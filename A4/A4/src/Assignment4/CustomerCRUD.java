package Assignment4;

import java.sql.*;

/**
 *
 * @author syedmfaizan
 */
public class CustomerCRUD {
    private MysqlDatabaseConnection connection=null;
    
    CustomerCRUD(MysqlDatabaseConnection conn){
        this.connection=conn;
    }
    
    ResultSet getAllCustomers(){
        return connection.executeQuery("SELECT * FROM Customer");
    }
    
    void insertCustomer(String cus_id, String name, String address, String city, String country, String phoneNum){
         String s="CALL addCustomer("+cus_id+", \""+name+"\", \""+address+"\", \""+city+"\", \""+country+"\", \""+phoneNum+"\");";
         connection.executeUpdate(s);
                
    }
    
    ResultSet getByCities(){
        return connection.executeQuery("CALL getCustomerCountByCity()");
    }
    

    
    ResultSet getCustomerByCode(String cus_id){
        return connection.executeQuery("SELECT * FROM Customer WHERE CustomerId = "+cus_id);
    }
    void deleteCustomer(String cus_id){
        connection.executeUpdate("DELETE FROM Customer WHERE CustomerId = "+cus_id);
    }
    
    void deleteAllCustomers(){
        connection.executeUpdate("DELETE FROM Customer");
    } 
        int UpdateCustomer(String c_id, String updatedName, String updatedAddress, String city, String country, String updatedPhone){
        return connection.executeUpdate("UPDATE Customer SET `Name`=\""+updatedName+"\", `Street Address`=\""+updatedAddress+"\", City=\""+city+"\", Country=\""+country+"\", Phone=\""+updatedPhone+"\" WHERE CustomerId="+c_id);
    }
    
    
   
    ResultSet getCustomerBill(String Code){
        return connection.executeQuery( "SELECT `Order`.OrderID, Customer.`Name`, `Order`.`Date`, Customer.TotalBill\n" +
                                            "FROM `Order`INNER JOIN Customer ON `Order`.CustomerID=Customer.CustomerID\n" +
                                                "where Customer.CustomerId = \""+Code+"\";" );
    }
    
    
    ResultSet getCustomerProducts(){
        return connection.executeQuery("CALL CustomersOrders();");
    }

    
    
        

    
    ResultSet getAllProducts(){
        return connection.executeQuery("SELECT * FROM Product");

    }
    
    ResultSet getProductByCode(String p_id){
        return connection.executeQuery("SELECT * FROM Product WHERE ProductId = "+p_id);    
    }
    
    void insertProduct(String p_id, String name, String quantity, String price){
        String s = "CALL addProduct("+p_id+", \""+name+"\", \""+quantity+"\", \""+price+"\");";
        connection.executeUpdate(s);
    }   
    
    
    void deleteProduct(String p_id){
        connection.executeUpdate("DELETE FROM Product WHERE ProductId = "+p_id);
    }    
    
    
    void deleteAllProducts(){
        connection.executeUpdate("DELETE FROM Product");
    } 
    
    
    
    
    
    
    
    void addOrder(String o_id, String c_id, String p_id, String date, String num){
        String s = "CALL insertOrder("+o_id+", "+c_id+", "+", "+p_id+", \""+date+"\", \""+num+"\");";
        connection.executeUpdate(s);
    }    
    
    ResultSet getAllOrders(){
        return connection.executeQuery("Select * from `Order`");
    }
    
    ResultSet getOrderByCode(String o_id){
        return connection.executeQuery("SELECT * FROM `Order` WHERE OrderId = "+o_id);    
    }
    
    void deleteOrder(String o_id){
        connection.executeUpdate("DELETE FROM `Order` WHERE OrderId = "+o_id);
    } 
    
    void deleteAllOrders(){
        connection.executeUpdate("DELETE FROM `Order`");
    } 
    
}
