package Assignment4;

/**
 *
 * @author syedmfaizan
 */
public class UserInterface {
    
    void printWelcomeGreetings(){
        System.out.println("Welcome to Sample Java MYSQL Connection Application!");
    }
    
    void printMainMenu(){
        System.out.println("Select Operation from List:");
        System.out.println("1: View Customer(s)");
        System.out.println("2: View Product(s)");
        System.out.println("3: View Order(s)");
        System.out.println("999: Exit Application");
    }
    
    void printViewCustomerMenu(){
        System.out.println("\tSelect Operation from List:");
        System.out.println("\t1: View All Customers");
        System.out.println("\t2: View Customer by Customer Code");
        System.out.println("\t3: View Customer by cities");
        System.out.println("\t4: Insert new customer");
        System.out.println("\t5: Delete a customer");
        System.out.println("\t6: Delete all customers");
        System.out.println("\t7: Customer bill");
        System.out.println("\t8: Customers orders");  
        System.out.println("\t9: Update a Product");
        System.out.println("\t999: Back");
    }
    
    void printProductMenu(){
        System.out.println("\tSelect Operation from List:");
        System.out.println("\t1: View All Products");
        System.out.println("\t2: View Product by Product Code");
        System.out.println("\t3: Insert new Product");
        System.out.println("\t4: Delete a Product");
        System.out.println("\t5: Delete all Products");
        System.out.println("\t999: Back");

        
    }
    
     void printOrderMenu(){
        System.out.println("\tSelect Operation from List:");
        System.out.println("\t1: View All Orders");
        System.out.println("\t2: View Order by Order Code");
        System.out.println("\t3: Insert new Order");
        System.out.println("\t4: Delete a Order");
        System.out.println("\t5: Delete all orders");
        System.out.println("\t999: Back");    
    }
    void printConfirmation(){
        System.out.print("Are you sure?(Yes/No) ");
    }
    
    void printEnterCustomerCodeText(){
        System.out.print("Enter Customer Code: ");
    }
    
    void enterCustomerName(){
        System.out.println("Enter customer name: ");
    }
   
    void enterCustomerAddress(){
        System.out.println("Enter customer address: ");
    }
    
    void enterCustomerCity(){
        System.out.println("Enter city name: ");
    }
    
    void enterCustomerCountry(){
        System.out.println("Enter country name: ");
    }
    
    void printEnterCustomerNewPhone(){
        System.out.print("Enter Updated Phone Number: ");
    }
    

    
    void printInitializationError(){
        System.out.println("Application Cannot Initialize!");
    }
    
     void printEnterOrderCodeText(){
        System.out.print("Enter Order Code: ");
    } 
     
     void printDateText(){
         System.out.println("Enter the date and time in the format \"yyyy-mm-dd hh-mi-ss\": ");
     }
     
     void printQuantity(){
         System.out.println("Enter the quantity: ");
     }
    
     void printPrice(){
         System.out.println("Enter the price: ");
     }
     
     void enterProductName(){
        System.out.println("Enter product name: ");
    }
     
    void printEnterProductCodeText(){
        System.out.print("Enter Product Code: ");
    }
     
}
