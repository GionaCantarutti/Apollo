package ImageGenerator;

public class InvalidFileExtensionException extends Exception {

    String requiredExtension;

    String extension;

    public InvalidFileExtensionException(String requiredExtension, String extension) {
        this.extension = extension;
        this.requiredExtension = requiredExtension;
    }

    @Override
    public String getMessage() {
        return "Invalid extension: " + extension + " instead of " + requiredExtension;
    }

}
