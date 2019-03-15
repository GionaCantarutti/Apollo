package ImageGenerator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Util.Parser;

public class FileToImageGenerator implements ImageGenerator {

    File source;
    WritableImage output;

    int width, height;

    Color background;

    /**
     * Sets file as source file.
     * @param file
     */
    public FileToImageGenerator(File file) {
        source = file;

        width = 255;
        height = 255;
        background = Color.rgb(255, 255, 255);
    }

    /**
     * Sets source as file and width as width. The height is calculated based on the number of pixels to be written.
     * @param file
     * @param width
     */
    public FileToImageGenerator(File file, int width) {
        source = file;
        this.width = width;

        height = minimumRequiredHeight();
        background = Color.rgb(255, 255, 255);
    }

    public void setSource(File file) {
        source = file;
    }

    @Override
    public void setImageSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void setBackgroundColor(Color color) {
        background = color;
    }

    @Override
    public void generateImage() {

        assert( source != null );

        output = new WritableImage(width, height);

        PixelWriter writer = output.getPixelWriter();

        try {
            BufferedReader br = new BufferedReader(new FileReader(source));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    String line = br.readLine();
                    if (line == null) {
                        writer.setColor(i, j, background);
                    } else {
                        writer.setColor(i, j, Parser.parseColor(line));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Image getOutput() {
        return output;
    }

    /**
     * ToDo
     * returns the minimum height required to fit all the pixels in the source file based on the width
     * @return
     */
    private int minimumRequiredHeight() {
        int h = 0;

        return h;
    }

}
