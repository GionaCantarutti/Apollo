package ADTs.ApolloProgramDescriptor;

import ADTs.Channel;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

//ToDo
public class APDInts implements ApolloProgramDescriptor {

    ArrayList<int[]> lines;

    int pointer;

    public APDInts() {
        lines = new ArrayList<int[]>();
        pointer = 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Color getLine(int line) {
        return null;
    }

    @Override
    public int getValue(Channel ch, int line) {
        return 0;
    }

    @Override
    public void setLine(Color c, int line) {

    }

    @Override
    public void setLine(String color, int line) {

    }

    @Override
    public void setValue(int value, Channel ch, int line) {

    }

    @Override
    public void writeColor(Color c) {

    }

    @Override
    public void writeColor(String s) {

    }

    @Override
    public void writeColor(int r, int g, int b) {

    }

    @Override
    public void movePointer(int line) {

    }

    @Override
    public void skipLines(int lines) {

    }
}
