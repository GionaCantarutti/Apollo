package ImageGenerator;

import java.io.File;

public class ImageGeneratorFactory {

    public static ImageGenerator createGenerator(String text) {
        return new StringToImageGenerator(text);
    }

    public static ImageGenerator createGenerator(String text, int width) {
        return new StringToImageGenerator(text, width);
    }

    public static ImageGenerator createGenerator(File source) {
        return new FileToImageGenerator(source);
    }

    public static ImageGenerator createGenerator(File source, int width) {
        return new FileToImageGenerator(source, width);
    }

}
