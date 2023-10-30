package src.code.u2l9.part7;

public class Pie extends Dessert{
    private int size;
    public Pie(){
        super("Apple", 12.99);
        int size = 12;
    }
    public Pie(String flavor, double price, int size){
        super(flavor, price);
        this.size = size;
    }
    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }
}
