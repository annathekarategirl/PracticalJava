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

//maybe get rid of store id or make it the same as the index? nah
public class PracticalJava{
    public static void main(String[] args){
        /*Initialize variables
        */
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
        Scanner scanner=new Scanner(System.in);
        Scanner myScanner = new Scanner(System.in);
       System.out.println("Enter you name id");
       String user_id=myScanner.nextLine();
       //set customer name to this
       customer.setName(user_id);
       Path filePath = Path.of("players_name.txt");
       boolean player_found=false;
       //line number varable chat
       int lineNumber = 0;
       try{
        //File myFile=new File("players_name.txt");
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
            }
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

       /*
       Shopping sim */
       do{
        try{
            ParkWest.printStores();
            System.out.print("Type the number of store to go in: ");
            int currentStoreId=scanner.nextInt();
            Store currentStore= ParkWest.getStores().get(currentStoreId);
            System.out.println("Welcome to "+currentStore.getName());
            System.out.println("What will you buy?");
            currentStore.printInventory();
            int currentItemId= scanner.nextInt();
            Item currentItem= currentStore.getInventory().get(currentItemId);
            customer.addItem(currentItem);
            customer.setBalance(customer.getBalance()-currentItem.getPrice());
            System.out.print("You have a "+currentItem.getName()+" and have $"+customer.getBalance());
            double multiply=currentStore.getEmployees().get(0).dialogue();
            customer.setBalance(customer.getBalance()*multiply);
            System.out.println("You now have $"+customer.getBalance());
            currentStore.earnMoreMoney(currentItem.getPrice());
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
        }catch(IndexOutOfBoundsException ex){
            System.out.print(ex);
        } catch(NullPointerException ex){
            System.out.print(ex);
        }catch(RuntimeException ex){
            System.out.print(ex);
        }catch(Exception ex){
            System.out.print(ex);
        }
       }while(shopping);
       for(int i=0;i<ParkWest.getStores().size();i++){
        System.out.println(ParkWest.getStores().get(i).getName()+" earned $"+ParkWest.getStores().get(i).getMoneyEarned());
       }
       System.out.print("You earned $"+customer.getBalance()+" and bought ");
       customer.printInventory();
       //write balance to file


    }
}
//Anna
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
//Anna
class Store extends EvilCorporation{
    int id=0;
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
        //this.bigCorpo=bigCorpo;
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
        this.setName(name);
    }
    public void setMoney(double bal){
        super.setMoney(bal);
    }
    public void setInventory(ArrayList<Item> list){
        this.inventory=list;
    }
    //*DO JAVADOCS
    // !!!!!!!!!!!
    // !!!!!!!!!!*/
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
    //MY Account placeholder
   
   
}


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
        //placeholder
        System.out.println("\nWhat do you want to say to the employee?\n1: Have a good day!\n2: Have a bad day >:(\n3: Have a day.");
        int x=myscan.nextInt();
        switch(x){
            case 1 : System.out.println("You earned money!"); return this.getMultiplier();
            case 2 : System.out.print("You didn't earn anything"); return 1.0; 
            case 3 : System.out.print("You didn't earn anything"); return 1.0;
            default: System.out.print("Carving your own path ig"); return 1.0; 
        }
        
    }
}
class Customer extends Person{
    private double balance = 1000;
    private ArrayList<Item> items= new ArrayList<>();
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
        // TODO Auto-generated method stub
        return super.getName();
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }
    public  void withdraw(double amount){
        this.balance-=amount;
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

//Anna
class Item{
    int id=0;
    double price=0;
    String name="Item";
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
        this.price=price;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
}