package salestax.entity;

import util.StringUtils;

public class Product
{
    final public static Integer defaultPadding = 2;

    private String description;

    private Double price;

    private Boolean exempt;

    private Boolean imported;

    public Product(String description, Double price, Boolean exempt, Boolean imported) {
        this.description = description;
        this.price = price;
        this.exempt = exempt;
        this.imported = imported;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isExempt() {
        return exempt;
    }

    public Boolean isImported() {
        return imported;
    }

    public String toString() {
        return this.toString(defaultPadding);
    }

    public String toString(Integer productLength) {
        String output="";

        Integer paddingSize = getPaddingSize(productLength);
        String priceFormat = (productLength >= defaultPadding) ? "$%7.2f" : "$%7.2f";

        output+= description;
        output+= StringUtils.padRight(" ", paddingSize);
        output+= String.format(priceFormat, price);

        return output;
    }

    private Integer getPaddingSize(Integer padding) {
        if(padding <= defaultPadding) {
            return defaultPadding;
        }

        String priceFormat = (padding >= defaultPadding) ? "$%6.2f" : "$%7.2f";

        padding-= description.trim().length();
        padding-= defaultPadding;
        padding-= String.format(priceFormat, price).length();

        return (padding > 0) ? padding : 1;

    }

}
