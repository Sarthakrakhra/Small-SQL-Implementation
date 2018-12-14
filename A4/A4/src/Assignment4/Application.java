package Assignment4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author syedmfaizan
 */
public class Application {
    private UserInterface UI=null;
    private MysqlDatabaseConnection dbConnection=null;
    private Scanner scanner = null;
    private Boolean isRunning=true;
    
    void start(){
        this.initialize();
        this.run();
    }
    
    void initialize(){
        this.scanner = new Scanner(System.in);
        this.UI = new UserInterface();
        MysqlDatabaseConnection dbConnection = new MysqlDatabaseConnection();
        if(dbConnection.connect())
        {
            this.dbConnection=dbConnection;
        }
        else{
            this.isRunning=false;
            this.UI.printInitializationError();
        }
    }
    
    void run(){
        UI.printWelcomeGreetings();
        while(this.isRunning){
            UI.printMainMenu();
            int mainOption = Integer.parseInt(scanner.nextLine());
            switch(mainOption){
                case 1: viewCustomer();
                        break;
                case 2: viewProduct();
                        break;
                case 3: viewOrder();
                        break;
                case 999: this.exit();
                        break;
                default: System.out.println("Incorrect Option selected");
            }
            System.out.flush();
        }
    }
    
    void exit(){
        this.dbConnection.disconnect();
        this.scanner.close();
        this.isRunning=false;
    }
    
    void viewCustomer(){
        Boolean backPressed = false;
        while(!backPressed){
            this.UI.printViewCustomerMenu();
            int customerOption = Integer.parseInt(scanner.nextLine());
            switch(customerOption){
                case 1: viewAllCustomers();
                        break;
                case 2: viewCustomerByCode();
                        break;
                case 3: viewCustomerByCity();
                        break;
                case 4: insertCustomer();
                        break;
                case 5: deleteCustomer();
                        break;
                case 6: deleteAllCustomers();
                        break;
                case 7: viewCustomerBill();
                        break;
                case 8: viewCustomerProducts();
                        break;
                case 9: updateCustomer();
                        break;
                case 999: backPressed=true;
                        break;
                default: System.out.println("Incorrect Option selected");
            }
        }
    }
    
    void viewProduct(){
        Boolean backPressed = false;
        while(!backPressed){
            this.UI.printProductMenu();
            int customerOption = Integer.parseInt(scanner.nextLine());
            switch(customerOption){
                case 1: viewAllProducts();
                        break;
                case 2: viewProductByCode();
                        break;
                case 3: insertProduct();
                        break;
                case 4: deleteProduct();
                        break;
                case 5: deleteAllProducts();
                        break;
//                case 6: updateProduct();
//                        break;
                case 999: backPressed=true;
                        break;
                default: System.out.println("Incorrect Option selected");
            }
        }
    }
    
        void viewOrder(){
        Boolean backPressed = false;
        while(!backPressed){
            this.UI.printOrderMenu();
            int customerOption = Integer.parseInt(scanner.nextLine());
            switch(customerOption){
                case 1: viewAllOrder();
                        break;
                case 2: viewOrderByCode();
                        break;
                case 3: insertOrder();
                        break;
                case 4: deleteOrder();
                        break;
                case 5: deleteAllOrder();
                        break;
                case 999: backPressed=true;
                        break;
                default: System.out.println("Incorrect Option selected");
            }
        }
    }
   
    
    /**********************************************************
                        Customers options
    **********************************************************/
    void viewAllCustomers(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        ResultSet rs = customerService.getAllCustomers();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s%-20s%-10s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4), rsmd.getColumnName(5), rsmd.getColumnName(6), rsmd.getColumnName(7));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30s%-20s%-20s%-10s%d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void viewCustomerByCode(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterCustomerCodeText();
        String customerCode = scanner.nextLine();
        ResultSet rs = customerService.getCustomerByCode(customerCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s%-20s%-10s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4), rsmd.getColumnName(5), rsmd.getColumnName(6), rsmd.getColumnName(7));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30s%-20s%-20s%-10s%d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
     void viewCustomerByCity(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        ResultSet rs = customerService.getByCities();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-10s%-25s\n", rsmd.getColumnName(1), rsmd.getColumnName(2));
            while(rs.next())
                System.out.printf("%-10s%-25s\n", rs.getInt(1), rs.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void insertCustomer(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printEnterCustomerCodeText();
            String c_code=scanner.nextLine();
            
            UI.enterCustomerName();
            String name=scanner.nextLine();
            
            UI.enterCustomerAddress();
            String address=scanner.nextLine();
            
            UI.enterCustomerCity();
            String city=scanner.nextLine();
            
            UI.enterCustomerCountry();
            String country = scanner.nextLine();
            
            UI.printEnterCustomerNewPhone();
            String phone=scanner.nextLine();

            customerService.insertCustomer(c_code, name, address, city, country, phone);
            System.out.println("Customer Inserted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteCustomer(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printEnterCustomerCodeText();
            String c_code=scanner.nextLine();
           
            customerService.deleteCustomer(c_code);
            System.out.println("Customer Deleted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteAllCustomers(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printConfirmation();
            String choice=scanner.nextLine();
           
            if(choice.equalsIgnoreCase("Yes")){
                customerService.deleteAllCustomers();
                System.out.println("Customers Deleted!");
            }else
                return;
            
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void updateCustomer(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterCustomerCodeText();
        String customerCode = scanner.nextLine();
        ResultSet rs = customerService.getCustomerByCode(customerCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s%-20s%-10s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4), rsmd.getColumnName(5), rsmd.getColumnName(6), rsmd.getColumnName(7));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30s%-20s%-20s%-10s%d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                
            UI.enterCustomerName();
            String updatedName=scanner.nextLine();

            UI.enterCustomerAddress();
            String address=scanner.nextLine();
            
            UI.enterCustomerCity();
            String city = scanner.nextLine();
            
            UI.enterCustomerCountry();
            String country = scanner.nextLine();
            
            
            UI.printEnterCustomerNewPhone();
            String updatedPhone=scanner.nextLine();
            
            
            int updated = customerService.UpdateCustomer(customerCode,updatedName, address, city, country, updatedPhone);
            if(updated==1)
                System.out.println("Customer Updated!");
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void viewCustomerBill(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterCustomerCodeText();
        String customerCode = scanner.nextLine();
        ResultSet rs = customerService.getCustomerBill(customerCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30s$%-20s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void viewCustomerProducts(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        ResultSet rs = customerService.getCustomerProducts();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("\n%-24s%-30s%-30s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3));
            while(rs.next())
                System.out.printf("%-20s\t%-30s%-30s\n", rs.getString(1), rs.getString(2), rs.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /**********************************************************
                        Products options
    **********************************************************/    
    void viewAllProducts(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        ResultSet rs = customerService.getAllProducts();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30d$%-20d\n", rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void viewProductByCode(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterProductCodeText();
        String productCode = scanner.nextLine();
        ResultSet rs = customerService.getProductByCode(productCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-25s%-30s%-20s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4));
            while(rs.next())
                System.out.printf("%-10d\t%-25s%-30d$%-20d\n", rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void insertProduct(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {            
            UI.enterProductName();
            String name=scanner.nextLine();
            
            UI.printEnterProductCodeText();
            String p_id = scanner.nextLine();
            
            UI.printQuantity();
            String q = scanner.nextLine();
            
            UI.printPrice();
            String p = scanner.nextLine();
            

            customerService.insertProduct(p_id, name, q, p);
            System.out.println("Product Inserted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteProduct(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printEnterProductCodeText();
            String p_code=scanner.nextLine();
           
            customerService.deleteProduct(p_code);
            System.out.println("Product Deleted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteAllProducts(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printConfirmation();
            String choice=scanner.nextLine();
           
            if(choice.equalsIgnoreCase("Yes")){
                customerService.deleteAllCustomers();
                System.out.println("Products Deleted!");
            }else
                return;
            
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
     /*void updateProduct(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterCustomerCodeText();
        String productCode = scanner.nextLine();
        ResultSet rs = customerService.getProductByCode(productCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3));
            while(rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        
            UI.printEnterCustomerNewFName();
            String updatedFName=scanner.nextLine();
            UI.printEnterCustomerNewMInitial();
            String updatedMInitial=scanner.nextLine();
            UI.printEnterCustomerNewLName();
            String updatedLName=scanner.nextLine();
            UI.printEnterCustomerNewAreaCode();
            String updatedAreaCode=scanner.nextLine();
            UI.printEnterCustomerNewPhone();
            String updatedPhone=scanner.nextLine();
            int updated = customerService.UpdateCustomer(customerCode,updatedFName,updatedMInitial,updatedLName,updatedAreaCode,updatedPhone);
            if(updated==1)
                System.out.println("Customer Updated!");
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    /**********************************************************
                        Order options
    **********************************************************/    
    void viewAllOrder(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        ResultSet rs = customerService.getAllOrders();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-16s%-20s%-30s%-30s%-20s%-20s%-20s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4), rsmd.getColumnName(5), rsmd.getColumnName(6), rsmd.getColumnName(7), rsmd.getColumnName(8));
            while(rs.next())
                System.out.printf("%-10d\t%-16d%-20d%-30s%-30s%-20s%-20s%-15s\n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void viewOrderByCode(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterOrderCodeText();
        String o_id = scanner.nextLine();
        ResultSet rs = customerService.getOrderByCode(o_id);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-16s%-16s%-20s%-30s%-30s%-20s%-20s%-20s\n", rsmd.getColumnName(1), rsmd.getColumnName(2), rsmd.getColumnName(3), rsmd.getColumnName(4), rsmd.getColumnName(5), rsmd.getColumnName(6), rsmd.getColumnName(7), rsmd.getColumnName(8));
            while(rs.next())
                System.out.printf("%-10d\t%-16d%-20d%-30s%-30s%-20s%-20s%-15s\n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void insertOrder(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printEnterCustomerCodeText();
            String c_code=scanner.nextLine();
            
            UI.printEnterOrderCodeText();
            String o_id=scanner.nextLine();
            
            UI.printEnterProductCodeText();
            String p_id=scanner.nextLine();
            
            UI.printDateText();
            String d = scanner.nextLine();
   
            
            UI.printQuantity();
            String q = scanner.nextLine();
            
            customerService.addOrder(o_id, c_code, p_id, d, q);
            System.out.println("Order Inserted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteOrder(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printEnterOrderCodeText();
            String o_id=scanner.nextLine();
           
            customerService.deleteOrder(o_id);
            System.out.println("Order Deleted!");
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deleteAllOrder(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        
        try {
            UI.printConfirmation();
            String choice=scanner.nextLine();
           
            if(choice.equalsIgnoreCase("Yes")){
                customerService.deleteAllOrders();
                System.out.println("Orders Deleted!");
            }else
                return;
            
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
     /*void updateProduct(){
        CustomerCRUD customerService = new CustomerCRUD(dbConnection);
        UI.printEnterCustomerCodeText();
        String productCode = scanner.nextLine();
        ResultSet rs = customerService.getProductByCode(productCode);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3));
            while(rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        
            UI.printEnterCustomerNewFName();
            String updatedFName=scanner.nextLine();
            UI.printEnterCustomerNewMInitial();
            String updatedMInitial=scanner.nextLine();
            UI.printEnterCustomerNewLName();
            String updatedLName=scanner.nextLine();
            UI.printEnterCustomerNewAreaCode();
            String updatedAreaCode=scanner.nextLine();
            UI.printEnterCustomerNewPhone();
            String updatedPhone=scanner.nextLine();
            int updated = customerService.UpdateCustomer(customerCode,updatedFName,updatedMInitial,updatedLName,updatedAreaCode,updatedPhone);
            if(updated==1)
                System.out.println("Customer Updated!");
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
