package ADTs;

public class Adress {

    public int x;

    public int y;

    public Adress( int x, int y ) {

        this.x = x;

        this.y = y;

    }

    public Adress() {

        this.x = 0;

        this.y = 0;

    }

    public String toString() {

        return("(" + x + "," + y + ")");

    }

}
