package salestax.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExemptProductData {

    private List<String> data = new ArrayList<String>() {};

    public List<String> all() {
        return data;
    }

    public Integer size() {
        return data.size();
    }

    public Boolean has(String line) {
        return data.contains(line);
    }

    public ExemptProductData add(String line) {
        data.add(line);
        return this;
    }

    public ExemptProductData(Class classInstance, String filename) throws IOException {
        InputStream stream = classInstance.getResourceAsStream(filename);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);
        String line;

        while ((line = buffer.readLine()) != null)
        {
            data.add(line);
        }

        buffer.close();
        reader.close();
        stream.close();
    }

}
