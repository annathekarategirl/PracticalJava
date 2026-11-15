/**
 * Anna M and Genesis I
 * 11/15/26
 * Practical Exam
 * Shppping Simulator
 * We pledge that we did not cheat or break the AI policy
 * I have only used resources indicated that are allowable.
    The work submitted is my, and only my work.
Signature: Anna Moore
Genesis Ijeomah
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.ArithmeticException;


public class PracticalJava{
    public static void main(String[] args){
        /*Initialize variables
        */
       //Anna
        EvilCorporation ParkWest= new EvilCorporation("ParkWest", new Person("Alan Resnick"),1000,new ArrayList<Store>());
        Store pennStation=new Store("Penn Station", new Person("Alana Resnick"),0,new ArrayList<Store>(),ParkWest,1, new ArrayList<Item>(),new ArrayList<Employee>());
        Store ross=new Store("Ross", new Person("Silly Billy"),0,new ArrayList<Store>(),ParkWest,2, new ArrayList<Item>(),new ArrayList<Employee>());
        Store target=new Store("Target", new Person("Target Dog"),0,new ArrayList<Store>(),ParkWest,3, new ArrayList<Item>(),new ArrayList<Employee>());
        Item sandwich=new Item(1, 9, "Sandwich");
        Item drink=new Item(2, 3, "Drink");
        pennStation.addInventory(sandwich);
        pennStation.addInventory(drink);
        Item shirt=new Item(3,14,"Shirt");
        Item pants= new Item(4,20,"Pants");
        ross.addInventory(pants);
        ross.addInventory(shirt);
        Item mug=new Item(5,100,"Mug");
        Item gallonJugOfArizonaIceTea= new Item(6, 2, "Gallon jug of Arizona Ice Tea");
        target.addInventory(gallonJugOfArizonaIceTea);
        target.addInventory(mug);
        Customer customer=new Customer("Player", 200, new ArrayList<Item>());
        Employee cassandra= new Employee("Cassandra", 1, 1.7, target);
        target.addEmployee(cassandra);
        Employee joe= new Employee("Joe", 2, 1.3, pennStation);
        pennStation.addEmployee(joe);
        Employee girlypop = new Employee("Girlypop",3,2.0,ross);
        ross.addEmployee(girlypop);
        Boolean shopping=false;
        ParkWest.addStore(target);
        ParkWest.addStore(ross);
        ParkWest.addStore(pennStation);

        //Genesis
        Scanner scanner=new Scanner(System.in);
        Scanner myScanner = new Scanner(System.in);
       System.out.println("Enter your name exactly as you did last time if you played before");
       String user_id=myScanner.nextLine();
       //set customer name to this
       customer.setName(user_id);
       Path filePath = Path.of("players_name.txt");
       boolean player_found=false;
       //line number varable chat
       int lineNumber = 0;
       try{
        //File myFile=new File("players_name.txt");
        //Checking for previous users and balance
        if(Files.exists(filePath)){
            Scanner fileScanner = new Scanner(filePath);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineNumber++; // Count every line read from the file


                if (line.equals(user_id)) {
                    System.out.println("Found " + user_id + " on line " + lineNumber);


                    if (fileScanner.hasNextLine()) {
                        String balanceLine = fileScanner.nextLine();
                        lineNumber++; // Count the balance line too!
           
                        double savedBalance = Double.parseDouble(balanceLine);
                        customer.setBalance(savedBalance);
                        System.out.println("Balance is on line " + lineNumber + ": " + savedBalance);
                }
                player_found = true;
                break;
            }
        }
        fileScanner.close();
            }//Writes new name if not found
            if (!player_found){
                String new_entry = user_id;
                //+"\n"+customer.getBalance()+"\n";
                 Files.writeString(
                    filePath,
                    //new_entry,
                    user_id+'\n',
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                );
            }


        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException a){
        System.out.println(a);
       }

        //System.out.println(ross.getInventory().size());
       double theSavedBalance=customer.getBalance();
       /*
       Shopping sim */
       //Anna
       do{
        try{
            ParkWest.printStores();
            System.out.println("Type the number of store to go in: ");
            int currentStoreId=scanner.nextInt();
            Store currentStore= ParkWest.getStores().get(currentStoreId);
            System.out.println("Welcome to "+currentStore.getName());
            System.out.println("What will you buy?");
            currentStore.printInventory();
            int currentItemId= scanner.nextInt();
            Item currentItem= currentStore.getInventory().get(currentItemId);
            if(currentItem.getPrice()<customer.getBalance()){
                customer.addItem(currentItem);
                customer.withdraw(currentItem.getPrice());
                System.out.println("You have a "+currentItem.getName()+" and have $"+customer.getBalance());
            }else{
                System.out.println("Hah you can't afford that");
            }
            double multiply=currentStore.getEmployees().get(0).dialogue();
            customer.setBalance(customer.getBalance()*multiply);
            System.out.println("You now have $"+customer.getBalance());
            currentStore.earnMoreMoney(currentItem.getPrice());
            
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Put in an actual value");
        } catch(NullPointerException ex){
            System.out.println(ex);
        }catch(InputMismatchException ex){
            System.out.println("Don't even joke.");
        }catch(RuntimeException ex){
            System.out.println(ex);
        }catch(Exception ex){
            System.out.println(ex);
        }
        Boolean not_valid=true;
            while(not_valid){
            System.out.print("Keep shopping? y/n (lowercase)");
            String sentinel=scanner.next();
            switch (sentinel){
                case "y": shopping=true; not_valid=false; break;
                case "n": shopping=false; not_valid=false; break;
                default: break;
            }
        }
       }while(shopping);
       //Generate lucky numbers to get bonus
       int[] winningIds=new int[2];
       winningIds[0]=(int)(Math.random()*6)+1;
       winningIds[1]=(int)(Math.random()*6)+1;
       System.out.println("Winning IDs: \n"+ winningIds[0]+"\n"+winningIds[1]);
     int[] boughtIds=new int[2];
     for(int i=0;i<boughtIds.length;i++){
        if(i<customer.getItems().size()){
            boughtIds[i]=customer.getItems().get(i).getId();
        }
    }
    int matches=0;
    for(int i=0;i<winningIds.length;i++){
        if(winningIds[i]==boughtIds[i]){
            matches++;
        }
    }
    double luckyWinRate=0;
    // AI: Gemini
// Purpose: Win rate looked off so I asked why. It said integer division was the issue, so cast it to a double
// Changes: Casting to double was only AI change
    if(customer.getItems().size()!=0) {
        luckyWinRate=(double)matches/customer.getItems().size();
}
     //Since you're "Luckier" if you bought less items but bought the lucky item anyway (Let the customer think they're luckier)
    double beforeLucky=customer.getBalance();
        System.out.println("Two lucky items were chosen. If you match, you get a bonus!");
        switch(matches){
            case 0: System.out.println("You didn't buy any of the lucky items.");System.out.println("You now have $"+customer.getBalance());break;
            case 1: System.out.println("You bought one lucky item!"); customer.setBalance(customer.getBalance()*1.5); System.out.println("You now have $"+customer.getBalance()); break;
            case 2: System.out.println("You bought all the lucky items!"); customer.setBalance(customer.getBalance()*2);
        }
       //for the statistics file we could make a string and just write it to a new file
       for(int i=0;i<ParkWest.getStores().size();i++){
        System.out.println(ParkWest.getStores().get(i).getName()+" earned $"+ParkWest.getStores().get(i).getMoneyEarned());
       }
       System.out.println("You earned $"+customer.getBalance()+" and bought: ");
       customer.printInventory();
       System.out.println("and spent $"+customer.getTotalSpent());
    
       //write balance to file
       //Anna and Genesis
       try{
       if(!player_found){
        String bal_entry = ""+customer.getBalance();
                //+"\n"+customer.getBalance()+"\n";
                 Files.writeString(
                    filePath,
                    //new_entry,
                    bal_entry+'\n',
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                );
       }else{
        //write on linenumber varible
        //Used Claude to inform us on how to put all lines in an arraylist because file syntax can be tricky
        ArrayList<String> lines=new ArrayList<>(Files.readAllLines(filePath));
        lines.set(lineNumber-1, ""+customer.getBalance());
        Files.write(filePath,lines);
       }//Generate Statistics file
       Files.writeString(Path.of("statistics.txt"),"Total spent: $"+customer.getTotalSpent()+"\nNet earnings: $"+(customer.getBalance()-theSavedBalance)
    +"\nLucky Matches: "+matches+"\nLucky Earnings: "+(customer.getBalance()-beforeLucky)+"\nLucky Win Rate: "+luckyWinRate);
    }catch(IOException ex){
        System.out.println("File failed");
    }

    }
}
//Anna
/**
*@description: This class creates an evil corporation where we make a *person from the person class.
*@getter/ setters: This class gets the money earned, name, and stores.
*But sets the money, and name. 
*@params: It takes the name, money earned, billionaire status and array of *stores. 


*/

class EvilCorporation{
    private String name="";
    private Person billionaire=new Person();
    private double moneyEarned=0;
    private ArrayList<Store> stores=new ArrayList<>();
    public Scanner input=new Scanner(System.in);
    EvilCorporation(){}
    EvilCorporation(String name, Person billionaire, double moneyEarned, ArrayList<Store> stores){
        this.name=name; //name of company
        this.billionaire=billionaire; //owner of company
        this.moneyEarned=moneyEarned;   
        this.stores=stores;
    }

    /**
     * Getters and setters
     * Returns or sets value
     */
    public Person getBillionaire(){
        return this.billionaire;
    }
    public double getMoneyEarned(){
        return this.moneyEarned;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Store> getStores(){
        return this.stores;
    }
    public void setBillionaire(Person bill){
        this.billionaire=bill;
    }
    public void setMoney(double money){
        this.moneyEarned=money;
    }
    public void setName(String name){
        this.name=name;
    }
    /**
     * Adds money 
     * @param amount the amount added
     */
    public void earnMoreMoney(double amount){
        this.moneyEarned=(this.moneyEarned+amount);
    }
    public void addStore(Store store){
        this.stores.add(store);
    }
    public void printStores(){
        for (int i=0;i<stores.size();i++){
            System.out.print(i+" ");
            System.out.println(stores.get(i).getName());
        }
    }
    /*public Store selectStore(){
        for (int i=0;i<stores.size();i++){
            System.out.print(stores.get(i).getId()+" ");
            System.out.println(stores.get(i).getName());
        }
        System.out.print("Type the number of store to go in: ");
        int currentStoreId=input.nextInt();
        for (int i=0;i<stores.size();i++){
            if(currentStoreId==stores.get(i).getId()){
                Store currentStore=stores.get(i);
                return currentStore;
            }else{
                return new Store();
            }
        }
    }*/

}
/**
*@description: This class is an extension of the evil copration class .
*@getter/ setters: This class gets the inventory, employee,ID, Name
* and money earned. But sets the inventory,money, and name. 
*@params: It takes everything that the evil coperation does with id+ *bigcorpo
*/

//Anna
class Store extends EvilCorporation{
    private int id=0;
    //private String name="STORE";
    private ArrayList<Item> inventory=new ArrayList<>();
    private ArrayList<Employee> employees=new ArrayList<>();
    //double moneyEarned= 0;
    EvilCorporation bigCorpo=new EvilCorporation("Big Corpo",new Person(),10000,new ArrayList<>());

    Store(){
        
    }
    //*
    // Chatgpt
    // Asked how to fix super
    // Was getting error on super
    // I was told to get rid of repeated variables and change getters to return super.get___ */
    Store(String name, Person billionare, double moneyEarned, ArrayList<Store> stores,EvilCorporation bigCorpo,int id, ArrayList<Item> inventory,ArrayList<Employee> employees){
        super(name,billionare, moneyEarned,stores);
        this.id=id;
        //this.name=name;
        this.inventory=inventory;
        this.employees=employees;
        //this.moneyEarned=moneyEarned;
        this.bigCorpo=bigCorpo;
    }
    /**
     * Getters and setters
     * @return
     */
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }
    public ArrayList<Employee> getEmployees(){
        return this.employees;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return super.getName();
    }
    public double getMoneyEarned(){
        return super.getMoneyEarned();
    }
    
    
    public void setName(String name){
        super.setName(name);
    }
    public void setMoney(double bal){
        super.setMoney(bal);
    }
    public void setInventory(ArrayList<Item> list){
        this.inventory=list;
    }
   //** *@param: These methods takes in amount, item, and employee
    /*@ description: These methods adds and removes things to inventory
    * Also adds employees and money.
    */

    @Override 
    public void earnMoreMoney(double amount){
        bigCorpo.earnMoreMoney(amount);
        super.earnMoreMoney(amount);
    }
    public void addInventory(Item item){
        this.inventory.add(item);
    }
    public void removeInventory(Item item){
        this.inventory.remove(item);
    }
    public void addEmployee(Employee emp){
        this.employees.add(emp);
    }
    public void printInventory(){
        int i=0;
        for (i=0;i<inventory.size();i++){
            System.out.print(i+" ");
            System.out.println(inventory.get(i).getName()+" $"+inventory.get(i).getPrice());
        }
    }
}

//Genesis
class Person{
    private String name="YN";
    Person(){}
    Person(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    
   
   
}
/**
*@description: This class is for the employee it extends the person class
*@getter/ setters: This class gets and sets, store, id and multiplier
*@params: It takes the name, id, happy multiplier and store object


*/


//Genesis
class Employee extends Person{
    //private String name=this.name;
    private int id;
    private double happyMultiplier;
    private Store store_obj= new Store();
    private Scanner myscan=new Scanner(System.in);
    Employee(){
        super();
    }
    Employee(String name, int id, double happyMultiplier, Store store_obj){
        super(name);
        this.id=id;
        this.happyMultiplier=happyMultiplier;
        this.store_obj=store_obj;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public Store getStore(){
        return this.store_obj;
    }
    public void setStore(Store store_obj){
        this.store_obj=store_obj;
    }
    public double getMultiplier(){
        return this.happyMultiplier;
    }
    public void setMultiplier(double happyMultiplier){
        this.happyMultiplier=happyMultiplier;
    }
    public double dialogue(){
        System.out.println("\nWhat do you want to say to the employee?\n1: Have a good day!\n2: Have a bad day >:(\n3: Have a day.");
        int x=myscan.nextInt();
        switch(x){
            case 1 : System.out.println("You earned money!"); return this.getMultiplier();
            case 2 : System.out.println("You didn't earn anything cuz you are mean. Soon you will run out of money and you will have NO ONE to turn to."); return 1.0; 
            case 3 : System.out.println("I think you were trying to be funny. Anyway, you got some extra cash."); return 1.2;
            default: System.out.println("Carving your own path ig"); return 1.0; 
        }
        
    }
}
/**
*@description: This class extends the person class 
*@getter/ setters: This class gets and set name, balance, items and total 
* money spent 
*@params: It takes the name, balance and items 


*/
//Genesis
class Customer extends Person{
    private double balance = 1000;
    private ArrayList<Item> items= new ArrayList<>();
    private double totalSpent=0;
    //private String name
    Customer(){
        super();
    }
    Customer( String name, double balance, ArrayList<Item> items){
        super(name);
        this.balance=balance;
        this.items=items;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance){
    if(balance<0){
        System.out.println("Balance can't be negative");
    }else{
        this.balance=balance;
    }
}
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    public double getTotalSpent(){
        return this.totalSpent;
    }
    public ArrayList<Item> getItems(){
    return this.items;
}
    public  void withdraw(double amount){
        this.balance-=amount;
        totalSpent+=amount;
    }
    public void deposit(double amount){
        this.balance+=amount;
    }
    public  void addItem(Item item){
        this.items.add(item);
    }
    public void printInventory(){
        int i=0;
        for (i=0;i<this.items.size();i++){
            
            System.out.println(this.items.get(i).getName());
        }
    }
}
/**
*@description: This class creates an item  
*@getter/ setters: This class gets and sets id price and name.
*@params: It takes the id, price, and name.


*/

//Anna
class Item{
    private int id=0;
    private double price=0;
    private String name="Item";
    Item(){}
    Item(int id, double price, String name){
        this.id=id;
        this.price=price;
        this.name=name;
    }
    /**
     * getters and setters
     * @return
     */
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
    if(price<0){
        System.out.println("Price can't be negative");
    }else{
        this.price=price;
    }
}
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
}