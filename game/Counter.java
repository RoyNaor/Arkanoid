package game;


public class Counter {
    private int number = 0;

    public Counter() {
    }

    // add number to current count.
    public void increase(int number){
        this.number = this.number +number;
    }
    // subtract number from current count.
    public void decrease(int number){
        this.number = this.number - number;
    }
    // get current count.
    public int getValue(){
        return this.number ;
    }

}
