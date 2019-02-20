package ImageGenerator;

public class InvalidFileFormatException extends Exception{

    @Override
    public String getMessage() {
        return "The file is not correctly formatted";
    }

}
