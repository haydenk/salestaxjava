package salestax.factory;

import salestax.Application;
import salestax.data.TextFileData;
import salestax.entity.Basket;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketFactory {

    public static Basket create(File file, Class resourceClassInstance) throws FileNotFoundException, IllegalStateException
    {
        List<String> fileData = new TextFileData(file).readFile();

        if(fileData.size() == 0)
        {
            throw new IllegalStateException(file.getName() + " (Empty file)");
        }

        Basket basket = new Basket();

        for(String fileDataLine : fileData)
        {
            try {
                Integer quantity = getQuantityCount(fileDataLine);
                String productString = fileDataLine.replaceAll("^([0-9]+)\\s", "").trim();

                for(Integer i=0; i<quantity; i++)
                {
                    basket.addProduct(ProductFactory.create(productString, resourceClassInstance));
                }
            } catch (Exception e) {
                System.out.println(Application.newLine + e.getMessage());
            }
        }

        return basket;
    }

    private static Integer getQuantityCount(String productString) {
        Matcher m = getMatches("^([0-9.]+)\\s", productString);
        return (m.find()) ? Integer.valueOf(m.group(1).trim()) : 1;
    }

    private static Matcher getMatches(String regex, String search) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return p.matcher(search);
    }


}
