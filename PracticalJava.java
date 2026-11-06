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
        this.name=name;
        this.billionaire=billionaire;
        this.moneyEarned=moneyEarned;
    }

    /**
     * Getters and setters
     * Returns or sets value
     */
    public Person getBillionaire(){
        return this.billionaire;
    }
}
class Store extends EvilCorporation{

}
class Person{
    Person(){}
}