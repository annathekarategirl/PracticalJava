import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class PracticalJava{
    public static void main(String[] args){
        Store store=new Store();
        store.earnMoreMoney(100);
        System.out.print(store.getMoneyEarned()+"\n"+store.bigCorpo.getMoneyEarned());
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
    private String name="";
    private ArrayList<Item> inventory=new ArrayList<>();
    private ArrayList<Employee> employees=new ArrayList<>();
    double moneyEarned= 0;
    EvilCorporation bigCorpo=new EvilCorporation("Big Corpo",new Person(),10000);

    Store(){}
    Store(int id,String name, ArrayList<Item> inventory,ArrayList<Employee> employees,double moneyEarned, EvilCorporation bigCorpo){
        this.id=id;
        this.name=name;
        this.inventory=inventory;
        this.employees=employees;
        this.moneyEarned=moneyEarned;
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
        return this.name;
    }
    public double getMoneyEarned(){
        return this.moneyEarned;
    }
    
    public void setName(String name){
        this.name=name;
    }
    public void setMoney(double bal){
        this.moneyEarned=bal;
    }
    public void setInventory(ArrayList<Item> list){

    }
    @Override 
    public void earnMoreMoney(double amount){
        bigCorpo.earnMoreMoney(amount);
        this.moneyEarned+=amount;
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

class Item{
    Item(){}
}