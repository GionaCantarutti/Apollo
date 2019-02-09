package Virtual_Machine;

import javafx.scene.image.Image;

public class InvalidImageException extends Exception {

    Image image;

    InvalidImageException(Image image) {

        this.image = image;

    }

    @Override
    public String getMessage() {
        if (image == null) {
            return "Image was null";
        } else {
            return "Invalid non-null image";
        }
    }
}
