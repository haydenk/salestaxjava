package salestax.factory;

import salestax.data.ExemptProductData;
import salestax.data.Resources;
import salestax.entity.Product;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductFactory {

    private static ExemptProductData exemptProduct;

    private static String productDescription = null;

    private static Double productPrice = 0.00;

    public static Product create(String productString, Class resourceClassInstance) throws Exception {

        exemptProduct = new ExemptProductData(resourceClassInstance, Resources.exemptProductsData);

        if(productString.length() == 0)
        {
            throw new Exception("You did not provide me anything to be able to create a product.");
        }

        getDescriptionAndPriceFromString(productString);

        if(productDescription.length() == 0)
        {
            throw new Exception("Unable to find product description from: " + productString);
        }

        Boolean imported = getImportStatus(productString);
        Boolean exempt = getExemptStatus(productDescription);

        return new Product(productDescription, productPrice, exempt, imported);
    }

    private static void getDescriptionAndPriceFromString(String productString) {

        productPrice = getPriceFromString(productString);

        productDescription = productString
                .replaceAll("\\s?([0-9.]+)$", "")
                .replaceAll("\\s?at$", "")
                .trim();
    }

    private static Double getPriceFromString(String productString) {
        Matcher m = getMatches("([0-9.]+)$", productString);

        return (m.find()) ? Double.parseDouble(m.group(1)) : 0.00;
    }

    private static Boolean getImportStatus(String productString) {
        return productString.toLowerCase().contains("imported");
    }

    private static Boolean getExemptStatus(String productDescription) {

        /*
         * Strip out the word "imported" from the description before looking for exemptions
         */
        Matcher importMatches = getMatches("imported\\s?", productDescription);

        if(importMatches.find())
        {
            productDescription = importMatches.replaceFirst("");
        }

        if(exemptProduct.has(productDescription))
        {
            return true;
        }

        /*
         * Strip out salestax.data from the string to return just the name only, such as:
         * - box of
         * - bottle of
         * - packet of
         */
        Matcher productNameMatches = getMatches("of\\s([A-Za-z\\s]+)$", productDescription);

        if(productNameMatches.find()) productDescription = productNameMatches.group(1);

        return exemptProduct.has(productDescription);

    }

    private static Matcher getMatches(String regex, String search) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        return p.matcher(search);
    }

}
