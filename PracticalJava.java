import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class PracticalJava{
    public static void main(String[] args){
        Store store=new Store();
        store.earnMoreMoney(100);
        System.out.print(store.getMoneyEarned()+"\n"+store.bigCorpo.getMoneyEarned()+"\n"+store.getName());
        
    }
}
class EvilCorporation{
    private String name="";
    private Person billionaire=new Person();
    private double moneyEarned=0;

    EvilCorporation(){}
    EvilCorporation(String name, Person billionaire, double moneyEarned){
        this.name=name; //name of company
        this.billionaire=billionaire; //owner of company
        this.moneyEarned=moneyEarned;
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

}
class Store extends EvilCorporation{
    int id=0;
    //private String name="STORE";
    private ArrayList<Item> inventory=new ArrayList<>();
    private ArrayList<Employee> employees=new ArrayList<>();
    //double moneyEarned= 0;
    EvilCorporation bigCorpo=new EvilCorporation("Big Corpo",new Person(),10000);

    Store(){
        
    }
    //*
    // Chatgpt
    // Asked how to fix super
    // Was getting error on super
    // I was told to get rid of repeated variables and change getters to return super.get___ */
    Store(String name, Person billionare, double moneyEarned,EvilCorporation bigCorpo,int id, ArrayList<Item> inventory,ArrayList<Employee> employees){
        super(name,billionare, moneyEarned);
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
}

//Genesis
class Person{
    Person(){}
}

//Genesis
class Employee extends Person{
    Employee(){}
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