package ADTs;

public class AdressOutOfBoundsException extends Exception{

    int x, y;

    public AdressOutOfBoundsException(int x,int y) {

        this.x = x;
        this.y = y;

    }

    @Override
    public String getMessage() {

        return ("Adress coordinates out of interval [0,255] : x = " + x + ",y = " + y);

    }
}
