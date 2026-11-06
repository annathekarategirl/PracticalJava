import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class PracticalJava{
    public static void main(String[] args){

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
    public double getMoney(){
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
    public void earnMoreMoney(double amount){
        this.moneyEarned=(this.moneyEarned+amount);
    }

}
class Store extends EvilCorporation{
    private String name="";
    private ArrayList
}
class Person{
    Person(){}
}