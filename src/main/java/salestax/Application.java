package salestax;

import salestax.data.Resources;
import salestax.entity.Basket;
import salestax.factory.BasketFactory;

import java.io.*;

public class Application {

    final public static String newLine = System.getProperty("line.separator");

    public static void main (String args[]) {

        if(args.length == 0)
        {
            System.out.println("You must provide at least one filename as an argument.");
            System.exit(0);
        }

        for (String s: args) {
            File f = new File(s);

            try {
                Basket basket = BasketFactory.create(f, Resources.class);
                System.out.println(basket.toString());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

        }

        System.exit(0);

    }

}
