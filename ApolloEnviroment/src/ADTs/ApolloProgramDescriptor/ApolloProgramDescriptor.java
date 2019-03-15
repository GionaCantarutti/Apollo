package ADTs.ApolloProgramDescriptor;

import ADTs.Channel;
import javafx.scene.paint.Color;

import java.util.Iterator;


/*
ToDo
Bidimensional or linear?
Static or dynamic size?
 */
public interface ApolloProgramDescriptor {

    Iterator iterator();

    Color getLine(int line);

    int getValue(Channel ch, int line);

    void setLine(Color c, int line);

    void setLine(String color, int line);

    void setValue(int value, Channel ch, int line);



    void writeColor(Color c);

    void writeColor(String s);

    void writeColor(int r, int g, int b);

    void movePointer(int line);

    void skipLines(int lines);

}
