public class BinaryAddition implements BinaryOperation{
    public @interface Overide{}
    public int execute (int num1,int num2){
        return num1 + num2;
    }
    @Overide
    public String getSymbol(){
        return "+";
    }
}
