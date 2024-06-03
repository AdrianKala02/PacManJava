package toolBox;

public enum DIRECTION {
    N(0),
    E(1),
    S(2),
    W(3);
    private int  degrees;

    private DIRECTION(int  degrees) {
        this. degrees =  degrees;
    }
    public int getDegreee(){return degrees;}
}
