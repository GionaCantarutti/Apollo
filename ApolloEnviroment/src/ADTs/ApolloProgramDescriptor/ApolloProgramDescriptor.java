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

}
