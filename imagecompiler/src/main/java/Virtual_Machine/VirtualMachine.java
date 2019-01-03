package Virtual_Machine;

import ADTs.Adress;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class VirtualMachine implements VMInterface {

    final int NUMBER_OF_COMMANDS = 51;

    //region Instance variables
    Image image;
    PixelReader reader;
    PixelWriter writer;

    Adress PC;
    Adress Pointer;
    Adress[] BGPointers;
    Boolean[] Flags;
    int XOffset;
    int YOffset;
    Boolean ERROR;
    int runningStatus;
    int RGBOffset;
    //endregion


    //region Constructors and initialization functions
    public VirtualMachine() {

        image = null;
        initializeInstance();

    }

    public VirtualMachine(Image image) {

        this.image = image;
        initializeInstance();
        prepImage();

    }

    /**
     * Turns the image into a writableImage and sets the writer and reader instance variables
     * Modifies image, reader and writer variables
     * Asserts that the image variable is not null
     */
    void prepImage() {

        //Assert that the image is not null
        assert (this.image != null);

        //Create reader for the image
        PixelReader tReader = image.getPixelReader();

        //Get width and height
        int imageWidth = (int)image.getWidth() , imageHeight = (int)image.getHeight();

        //Create writable image and related writer
        WritableImage writable = new WritableImage(imageWidth, imageHeight);
        PixelWriter tWriter = writable.getPixelWriter();

        //Copy every pixel from the image to the writable image
        for(int x = 0; x < imageWidth; x++) {
            for(int y = 0; y < imageHeight; y++) {

                Color color = tReader.getColor(x, y);
                tWriter.setColor(x, y, color);

            }
        }

        //Set the image as the new writable
        this.image = writable;
        this.reader = writable.getPixelReader();
        this.writer = tWriter;

    }

    /**
        Initializes every variable
        PC and all pointers start at 0,0
        All flags start as false ??
        Offsets, RGBOffset and runningStatus start as 0
        reader and writer are null
    */
    void initializeInstance() {

        PC = new Adress();
        Pointer = new Adress();
        BGPointers = new Adress[256];
        for (int i = 0; i < BGPointers.length; i++) {
            BGPointers[i] = new Adress();
        }
        Flags = new Boolean[256];
        for (int i = 0; i < Flags.length; i++) {
            Flags[i] = false;
        }
        XOffset = 0;
        YOffset = 0;
        ERROR = false;
        runningStatus = 0;
        RGBOffset = 0;

        reader = null;
        writer = null;

    }
    //endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
        Steps the execution, calling the command pointed by the PC and if the PC is unchanged at the end of the call
        it gets moved to the next position as a default
        @throws InvalidImageException if either the image, the writer or the reader are null
        @throws MachineStoppedException if the function is called while runningStatus is set at 3 or higher.
     */
    public void step() throws InvalidImageException, MachineStoppedException {

        if (runningStatus >= 3) {

            throw new MachineStoppedException(runningStatus);

        } else {

            checkImage();

            Color PCColor = reader.getColor(PC.x + XOffset, PC.y + YOffset);

            //System.out.println("Color is: " + getColor(PC).getRed() + " " + getColor(PC).getGreen() + " " + getColor(PC).getBlue());

            double ID = getColor(PC).getRed()*255;
            double Arg1 = getColor(PC).getGreen()*255;
            double Arg2 = getColor(PC).getBlue()*255;

            Adress prevPC = PC;

            System.out.println("Running ID: " + ID);
            System.out.println("With args: " + Arg1 + ", " + Arg2);

            executeCommand(ID, Arg1, Arg2);

            if (PC == prevPC) {
                movePC();
            }

            if (runningStatus == 2 || runningStatus == 1) {

                runningStatus--;

            }

        }

    }

    /**
        resets the virtualMachine to its default state by resetting all instance variables and setting the image to null
     */
    public void reset() {
        this.image = null;
        initializeInstance();
    }

    public void runColor(Color c) {

        executeCommand(c.getRed()*255, c.getGreen()*255, c.getBlue()*255);

    }

    //region private methods
    /**
        @throws InvalidImageException if either the image, the writer or the reader are null
     */
    private void checkImage() throws InvalidImageException {

        if((this.image == null) || (this.reader == null) || (this.writer == null)) {

            throw new InvalidImageException(image);

        }

    }

    /**
        moves the PC to the next position (scans from left to right, up to down when at the end of a line)
     */
    private void movePC() {

        if(PC.x >= 255) {
            PC.x = 0;
            if(PC.y >= 255) {
                PC.y = 0;
            } else {
                PC.y++;
            }
        } else {
            PC.x++;
        }

    }

    private void movePCBack() {

        if(PC.x <= 0) {
            PC.x = 255;
            if(PC.y <= 0) {
                PC.y = 255;
            } else {
                PC.y--;
            }
        } else {
            PC.x--;
        }

    }

    //region color write and read functions
    private Color getColor(Adress adress) {
        adress.x += XOffset;
        adress.y += YOffset;

        return reader.getColor(adress.x, adress.y);
    }

    private Color getColor(double X, double Y) {
        X += XOffset;
        Y += YOffset;

        return reader.getColor((int)X, (int)Y);
    }

    private void setColor(double X, double Y, Color c) {
        X += XOffset;
        Y += YOffset;

        writer.setColor((int)X,(int)Y,c);
    }

    private void setColor(Adress adress, Color c) {
        adress.x += XOffset;
        adress.y += YOffset;

        writer.setColor(adress.x, adress.y, c);
    }
    //endregion
    //endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //region getters and setters
    public void resetRunningStatus() {
        runningStatus = 0;
    }

    public Image getCurrentImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        prepImage();
    }

    public Adress getPC() {
        return PC;
    }

    public Color getPCColor() {
        return reader.getColor(PC.x,PC.y);
    }

    public Adress getPointer() {
        return Pointer;
    }

    public Color getPointerColor() {
        return reader.getColor(Pointer.x, Pointer.y);
    }

    public Adress[] getBackgroundPointers() {
        return new Adress[0];
    }

    public Boolean[] getFlags() {
        return Flags;
    }

    public int getXOffset() {
        return XOffset;
    }

    public int getYOffset() {
        return YOffset;
    }

    public boolean getERROR() {
        return ERROR;
    }

    public boolean getRunningStatus() {
        if (runningStatus == 2) {
            return false;
        } else return true;
    }
    //endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
        calls the command identified by the ID with arg1 and arg2 as arguments
     */
    private void executeCommand(double ID, double arg1, double arg2) {

        if (isCommandNumber(0, ID)) {
            C_addOffset(arg1, arg2);
        } else if (isCommandNumber(1, ID)) {
            C_AND(arg1, arg2);
        } else if (isCommandNumber(2, ID)) {
            C_breakFinalize(arg1, arg2);
        } else if (isCommandNumber(3, ID)) {
            C_breakInitialize(arg1, arg2);
        } else if (isCommandNumber(4, ID)) {
            C_choosePointer(arg1, arg2);
        } else if (isCommandNumber(5, ID)) {
            C_clone(arg1, arg2);
        } else if (isCommandNumber(6, ID)) {
            C_coinFlip(arg1, arg2);
        } else if (isCommandNumber(7, ID)) {
            C_compare(arg1, arg2);
        } else if (isCommandNumber(8, ID)) {
            C_conditionalGoTo(arg1, arg2);
        } else if (isCommandNumber(9, ID)) {
            C_conditionalRelativeGoTo(arg1, arg2);
        } else if (isCommandNumber(10, ID)) {
            C_conditionalThrow(arg1, arg2);
        } else if (isCommandNumber(11, ID)) {
            C_divide(arg1, arg2);
        } else if (isCommandNumber(12, ID)) {
            C_execute(arg1, arg2);
        } else if (isCommandNumber(13, ID)) {
            C_fibonacci(arg1, arg2);
        } else if (isCommandNumber(14, ID)) {
            C_goTo(arg1, arg2);
        } else if (isCommandNumber(15, ID)) {
            C_invert(arg1, arg2);
        } else if (isCommandNumber(16, ID)) {
            C_lazyCompare(arg1, arg2);
        } else if (isCommandNumber(17, ID)) {
            C_massWrite(arg1, arg2);
        } else if (isCommandNumber(18, ID)) {
            C_mirrorPixels(arg1, arg2);
        } else if (isCommandNumber(19, ID)) {
            C_multiply(arg1, arg2);
        } else if (isCommandNumber(20, ID)) {
            C_next(arg1, arg2);
        } else if (isCommandNumber(21, ID)) {
            C_NOT(arg1, arg2);
        } else if (isCommandNumber(22, ID)) {
            C_OR(arg1, arg2);
        } else if (isCommandNumber(23, ID)) {
            C_pickPointer(arg1, arg2);
        } else if (isCommandNumber(24, ID)) {
            C_point(arg1, arg2);
        } else if (isCommandNumber(25, ID)) {
            C_randomize(arg1, arg2);
        } else if (isCommandNumber(26, ID)) {
            C_randomizePointers(arg1, arg2);
        } else if (isCommandNumber(27, ID)) {
            C_readERROR(arg1, arg2);
        } else if (isCommandNumber(28, ID)) {
            C_readFlag(arg1, arg2);
        } else if (isCommandNumber(29, ID)) {
            C_rectangle(arg1, arg2);
        } else if (isCommandNumber(30, ID)) {
            C_relativeGoTo(arg1, arg2);
        } else if (isCommandNumber(31, ID)) {
            C_repeat(arg1, arg2);
        } else if (isCommandNumber(32, ID)) {
            C_resetERROR(arg1, arg2);
        } else if (isCommandNumber(33, ID)) {
            C_resetOffset(arg1, arg2);
        } else if (isCommandNumber(34, ID)) {
            C_rotateFlags(arg1, arg2);
        } else if (isCommandNumber(35, ID)) {
            C_rotateColors(arg1, arg2);
        } else if (isCommandNumber(36, ID)) {
            C_rotatePixelValues(arg1, arg2);
        } else if (isCommandNumber(37, ID)) {
            C_rotatePointers(arg1, arg2);
        } else if (isCommandNumber(38, ID)) {
            C_skip(arg1, arg2);
        } else if (isCommandNumber(39, ID)) {
            C_subtract(arg1, arg2);
        } else if (isCommandNumber(40, ID)) {
            C_subtractOffset(arg1, arg2);
        } else if (isCommandNumber(41, ID)) {
            C_sum(arg1, arg2);
        } else if (isCommandNumber(42, ID)) {
            C_switchFlag(arg1, arg2);
        } else if (isCommandNumber(43, ID)) {
            C_switchPointers(arg1, arg2);
        } else if (isCommandNumber(44, ID)) {
            C_tintBlue(arg1, arg2);
        } else if (isCommandNumber(45, ID)) {
            C_tintGreen(arg1, arg2);
        } else if (isCommandNumber(46, ID)) {
            C_tintRed(arg1, arg2);
        } else if (isCommandNumber(47, ID)) {
            C_write(arg1, arg2);
        } else if (isCommandNumber(48, ID)) {
            C_writeFlag(arg1, arg2);
        } else if (isCommandNumber(49, ID)) {
            C_writePointer(arg1, arg2);
        } else if (isCommandNumber(50, ID)) {
            C_XOR(arg1, arg2);
        }

    }

    /**
        checks if the command ID is within the range of the number-th command
        @param number the number of the command range the caller is checking for
        @param ID the ID of the command
        @returns true if the command is within the range
     */
    private boolean isCommandNumber(int number, double ID) {

        assert (number < NUMBER_OF_COMMANDS);

        ID = ID % 256;

        double gap = 255/NUMBER_OF_COMMANDS;
        double lower = gap*number;
        double higher = gap*(number + 1);

        if(ID == 255 && number == NUMBER_OF_COMMANDS - 1) return true;

        return (lower <= ID && higher > ID);

    }

    //Documentation on the commands can be found in the dedicated excel file
    //ToDo translate the commands documentation from italian to english
    //region Commands

    private void C_addOffset(double arg1, double arg2) {
        XOffset += arg1;
        YOffset += arg2;

        XOffset = Math.max(0, XOffset);
        YOffset = Math.max(0, YOffset);
        XOffset = (XOffset % (int)(image.getWidth()-256));
        YOffset = (XOffset % (int)(image.getHeight()-256));
    }
    private void C_AND(double arg1, double arg2) {
        Flags[0] = Flags[(int)arg1] && Flags[(int)arg2];
    }
    private void C_breakFinalize(double arg1, double arg2) {
        if ((getColor(BGPointers[255]) == getColor(arg1,arg2)) && (runningStatus == 1 || runningStatus == 2)) {
            runningStatus = 3;
        } else {
            ERROR = true;
        }
    }
    private void C_breakInitialize(double arg1, double arg2) {
        if (getColor(Pointer) == getColor(arg1, arg2)) {
            runningStatus = 2;
        }
    }
    private void C_choosePointer(double arg1, double arg2) {
        Pointer = BGPointers[(int)arg1];
    }
    private void C_clone(double arg1, double arg2) {
        Color c = getColor(PC);
        setColor((int)arg1, (int)arg2, c);
    }
    private void C_coinFlip(double arg1, double arg2) {
        double r = Math.random();
        if (r < 0.5) {
            Flags[0] = true;
        } else {
            Flags[0] = false;
        }
    }
    private void C_compare(double arg1, double arg2) {
        boolean b = (getColor(arg1,arg2) == getColor(Pointer));
        if (b) {
            Flags[0] = true;
        } else {
            Flags[0] = false;
        }
    }
    private void C_conditionalGoTo(double arg1, double arg2) {
        if (Flags[0] && (new Adress(arg1,arg2) != PC)) {
            PC.x = (int)arg1;
            PC.y = (int)arg2;
        }
    }
    private void C_conditionalRelativeGoTo(double arg1, double arg2) {
        if (Flags[0] && (new Adress(arg1,arg2) != PC)) {
            PC.x = PC.x + (int)arg1;
            PC.y = PC.y + (int)arg2;
        }
    }
    private void C_conditionalThrow(double arg1, double arg2) {
        if (Flags[0]) {
            ERROR = true;
        }
    }
    private void C_divide(double arg1, double arg2) {
        Color c1 = getColor(arg1, arg2);
        Color c2 = getColor(Pointer);
        double red, green, blue;

        try {
            red = (c1.getRed()/c2.getRed());
        } catch(Exception e) {
            ERROR = true;
            red = 0;
        }
        try {
            green = (c1.getGreen()/c2.getGreen());
        } catch(Exception e) {
            ERROR = true;
            green = 0;
        }
        try {
            blue = (c1.getBlue()/c2.getBlue());
        } catch(Exception e) {
            ERROR = true;
            blue = 0;
        }

        Color c3 = Color.rgb((int)(red*255)%256, (int)(green*255)%256, (int)(blue*255)%256);

        setColor((int)arg1, (int)arg2, c3);
    }
    private void C_execute(double arg1, double arg2) {
        PC = Pointer;
    }
    private void C_fibonacci(double arg1, double arg2) {
        Color c = Color.rgb((int)(getColor(PC).getRed()*255), (int)(arg2), (int)((arg1 + arg2)%256));
        setColor(PC.x, PC.y, c);
    }
    private void C_goTo(double arg1, double arg2) {
        if (new Adress(arg1,arg2) != PC) {
            PC = new Adress(arg1, arg2);
        }
    }
    private void C_invert(double arg1, double arg2) {
        Flags[0] = !Flags[0];
    }
    private void C_lazyCompare(double arg1, double arg2) {
        boolean b = (getColor(arg1,arg2) == getColor(Pointer));
        if (b) {
            Flags[0] = true;
        }
    }
    private void C_massWrite(double arg1, double arg2) {
        Color c = getColor(Pointer);
        for (int i = 0; i < 256; i++) {
            setColor(BGPointers[i], c);
        }
    }
    private void C_mirrorPixels(double arg1, double arg2) {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < i; j++) {
                Color t = getColor(i, j);
                setColor(i, j, getColor(j, i));
                setColor(j, i, t);
            }
        }
    }
    private void C_multiply(double arg1, double arg2) {
        Color c1 = getColor(arg1, arg2);
        Color c2 = getColor(Pointer);
        double red, green, blue;

        red = (c1.getRed()*c2.getRed())%256;
        green = (c1.getGreen()*c2.getGreen())%256;
        blue = (c1.getBlue()*c2.getBlue())%256;

        Color c3 = Color.rgb((int)(red*255)%256, (int)(green*255)%256, (int)(blue*255)%256);

        setColor((int)arg1, (int)arg2, c3);
    }
    private void C_next(double arg1, double arg2) {
        //Does nothing
    }
    private void C_NOT(double arg1, double arg2) {
        Flags[0] = !Flags[(int)arg1];
    }
    private void C_OR(double arg1, double arg2) {
        Flags[0] = Flags[(int)arg1] || Flags[(int)arg2];
    }
    private void C_pickPointer(double arg1, double arg2) {
        Adress t = Pointer;
        Pointer = BGPointers[(int)arg1];
        BGPointers[(int)arg1] = Pointer;
    }
    private void C_point(double arg1, double arg2) {
        Pointer = new Adress(arg1, arg2);
    }
    private void C_randomize(double arg1, double arg2) {
        Color c = new Color(Math.random(), Math.random(), Math.random(), 1.0);
        setColor((int)arg1, (int)arg2, c);
    }
    private void C_randomizePointers(double arg1, double arg2) {
        for(int i = 0; i < 256; i++) {
           BGPointers[i] = new Adress(Math.random() * 255, Math.random() * 255);
        }
    }
    private void C_readERROR(double arg1, double arg2) {
        Flags[0] = ERROR;
    }
    private void C_readFlag(double arg1, double arg2) {
        Flags[0] = Flags[(int)arg1];
    }
    private void C_rectangle(double arg1, double arg2) {
        Color c = getColor(Pointer);
        int minX = (int)Math.min(Pointer.x, arg1);
        int minY = (int)Math.min(Pointer.y, arg2);
        int maxX = (int)Math.max(Pointer.x, arg1);
        int maxY = (int)Math.max(Pointer.y, arg2);

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                setColor(i, j, c);
            }
        }
    }
    private void C_relativeGoTo(double arg1, double arg2) {
        if (new Adress(arg1,arg2) != PC) {
            PC.x += arg1;
            PC.y += arg2;
        }
    }
    private void C_repeat(double arg1, double arg2) {
        Color c = Color.rgb((int)(getColor(PC).getRed()*255), (int)(((getColor(PC).getGreen()*255) - 1)%256), (int)(getColor(PC).getBlue()*255));
        setColor(PC, c);
        if (getColor(PC).getGreen()*255 >= 1) {
            movePCBack();
            movePCBack();
        }
    }
    private void C_resetERROR(double arg1, double arg2) {
        ERROR = false;
    }
    private void C_resetOffset(double arg1, double arg2) {
        XOffset = 0;
        YOffset = 0;

        XOffset = Math.max(0, XOffset);
        YOffset = Math.max(0, YOffset);
        XOffset = (XOffset % (int)(image.getWidth()-256));
        YOffset = (XOffset % (int)(image.getHeight()-256));
    }
    private void C_rotateFlags(double arg1, double arg2) {
        boolean[] t = new boolean[256];
        for (int i = 0; i < 256; i++) {
            t[i] = Flags[i];
        }
        for (int i = 0; i < 256; i++) {
            Flags[i] = t[(int)((i+arg1)%256)];
        }
    }
    private void C_rotateColors(double arg1, double arg2) {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                Color src = getColor(i, j);
                Color c = Color.rgb((int)(src.getBlue() * 255), (int)(src.getRed() * 255), (int)(src.getGreen() * 255));
                setColor(i, j, c);
            }
        }
    }
    private void C_rotatePixelValues(double arg1, double arg2) {
        Color src = getColor(arg1, arg2);
        Color c = Color.rgb((int)(src.getBlue() * 255), (int)(src.getRed() * 255), (int)(src.getGreen() * 255));
        setColor(arg1, arg2, c);
    }
    private void C_rotatePointers(double arg1, double arg2) {
        Adress[] t = new Adress[256];
        for (int i = 0; i < 256; i++) {
            t[i] = BGPointers[i];
        }
        for (int i = 0; i < 256; i++) {
            BGPointers[i] = t[(int)((i+arg1)%256)];
        }
    }
    private void C_skip(double arg1, double arg2) {
        for (int i = 0; i < arg1; i++) {
            movePC();
        }
    }
    private void C_subtract(double arg1, double arg2) {
        Color c1 = getColor(arg1, arg2);
        Color c2 = getColor(Pointer);
        double red, green, blue;

        red = (c1.getRed()-c2.getRed());
        if (red < 0) red = 0;
        green = (c1.getGreen()-c2.getGreen());
        if (green < 0) green = 0;
        blue = (c1.getBlue()-c2.getBlue());
        if (blue < 0) blue = 0;

        Color c3 = Color.rgb((int)(red*255)%256, (int)(green*255)%256, (int)(blue*255)%256);

        setColor((int)arg1, (int)arg2, c3);
    }
    private void C_subtractOffset(double arg1, double arg2) {
        XOffset -= arg1;
        YOffset -= arg2;

        XOffset = Math.max(0, XOffset);
        YOffset = Math.max(0, YOffset);
        XOffset = (XOffset % (int)(image.getWidth()-256));
        YOffset = (XOffset % (int)(image.getHeight()-256));
    }
    private void C_sum(double arg1, double arg2) {
        Color c1 = getColor(arg1, arg2);
        Color c2 = getColor(Pointer);
        double red, green, blue;

        red = (c1.getRed()+c2.getRed())%256;
        green = (c1.getGreen()+c2.getGreen())%256;
        blue = (c1.getBlue()+c2.getBlue())%256;

        Color c3 = Color.rgb((int)(red*255)%256, (int)(green*255)%256, (int)(blue*255)%256);

        setColor((int)arg1, (int)arg2, c3);
    }
    private void C_switchFlag(double arg1, double arg2) {
        boolean t = Flags[(int)arg2];
        Flags[(int)arg2] = Flags[(int)arg1];
        Flags[(int)arg1] = t;
    }
    private void C_switchPointers(double arg1, double arg2) {
        Adress t = BGPointers[(int)arg2];
        BGPointers[(int)arg2] = BGPointers[(int)arg1];
        BGPointers[(int)arg1] = t;
    }
    private void C_tintBlue(double arg1, double arg2) {
        arg1 = arg1/10;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                Color c = Color.rgb( ((int)((getColor(i, j).getRed()*255))%256), ((int)((getColor(i, j).getGreen()*255))%256), ((int)((getColor(i, j).getBlue()*255) + arg1)%256) );
                setColor(i, j, c);
            }
        }
    }
    private void C_tintGreen(double arg1, double arg2) {
        arg1 = arg1/10;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                Color c = Color.rgb( ((int)((getColor(i, j).getRed()*255))%256), ((int)((getColor(i, j).getGreen()*255) + arg1)%256), ((int)((getColor(i, j).getBlue()*255) + arg1)%256) );
                setColor(i, j, c);
            }
        }
    }
    private void C_tintRed(double arg1, double arg2) {
        arg1 = arg1/10;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                Color c = Color.rgb( ((int)((getColor(i, j).getRed()*255) + arg1)%256), ((int)((getColor(i, j).getGreen()*255))%256), ((int)((getColor(i, j).getBlue()*255) + arg1)%256) );
                setColor(i, j, c);
            }
        }
    }
    private void C_write(double arg1, double arg2) {
        Color c = getColor(Pointer);
        setColor(arg1, arg2, c);
    }
    private void C_writeFlag(double arg1, double arg2) {
        Flags[(int)arg1] = Flags[0];
    }
    private void C_writePointer(double arg1, double arg2) {
        BGPointers[(int)arg1] = Pointer;
    }
    private void C_XOR(double arg1, double arg2) {
        Flags[0] = Flags[(int)arg1] != Flags[(int)arg2];
    }


    //endregion
}