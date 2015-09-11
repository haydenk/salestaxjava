package salestax.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileData {

    private Scanner reader;

    public TextFileData(File file) throws FileNotFoundException {
        reader = new Scanner(file);
    }

    public List<String> readFile() throws FileNotFoundException {
        List<String> lines = new ArrayList<String>() {};

        while(reader.hasNext()) lines.add(reader.nextLine());

        return lines;
    }

}
