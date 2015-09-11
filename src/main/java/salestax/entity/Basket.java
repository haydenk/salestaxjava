package salestax.entity;

import salestax.Application;
import util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    final public static Double taxRate = 0.10;

    final public static Double importTaxRate = 0.05;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public Basket() {
        products = new ArrayList<Product>();
    }

    public Basket addProduct(Product product) {
        products.add(product);

        return this;
    }

    public Integer getNumberOfProducts() {
        return products.size();
    }

    public Double getSalesTaxTotal() {
        Double salesTaxTotal = 0.00;

        for (Product product : products) salesTaxTotal += product.getPrice() * getTotalTaxRate(product);

        return getRoundedTotal(salesTaxTotal);
    }

    public Double getSubTotal() {
        Double runningTotal = 0.00;

        for (Product product : products) runningTotal += product.getPrice();

        return getRoundedTotal(runningTotal);
    }

    public Double getTotal() {
        return getSubTotal() + getSalesTaxTotal();
    }

    private Double getRoundedTotal(Double number) {
        BigDecimal totalValue = new BigDecimal(number.toString());
        BigDecimal displayTotalValue = totalValue.setScale(2, RoundingMode.HALF_EVEN);

        return displayTotalValue.doubleValue();
    }

    private Double getTotalTaxRate(Product product) {
        Double taxRate = 0.00;

        if (product.isExempt() && !product.isImported()) return taxRate;

        if (product.isImported()) taxRate += Basket.importTaxRate;

        taxRate += Basket.taxRate;

        return taxRate;
    }

    public Product getProductWithLongestString() {

        Product selectedProduct = getProducts().get(1);

        for(Product product : getProducts()) {
            if(product.toString().length() > selectedProduct.toString().length()) {
                selectedProduct = product;
            }
        }

        return selectedProduct;
    }

    public String toString() {
        String output="";

        Integer lineItemLength = getProductWithLongestString().toString().length()+1;

        for(Product product : products)
        {
            output+= product.toString(lineItemLength) + Application.newLine;
        }

        for(int i=0; i<lineItemLength-1; i++) output+="=";

        output+= Application.newLine;

        String stringSubTotal = String.format("$%7.2f", getSubTotal());
        String stringTaxTotal = String.format("$%7.2f", getSalesTaxTotal());
        String stringTotal = String.format("$%7.2f", getTotal());

        output+= StringUtils.padRight(" ", lineItemLength - stringSubTotal.length() - 11);
        output+= "Subtotal: " + stringSubTotal + Application.newLine;

        output+= StringUtils.padRight(" ", lineItemLength - stringTaxTotal.length() - 6);
        output+= "Tax: " + stringTaxTotal + Application.newLine;

        output+= StringUtils.padRight(" ", lineItemLength - stringTotal.length() - 8);
        output+= "Total: " + stringTotal + Application.newLine;

        return output;
    }

}
