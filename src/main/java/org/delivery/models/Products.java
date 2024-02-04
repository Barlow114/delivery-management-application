package org.delivery.models;

import java.util.Objects;

public class Products {

    private String productName;



    public Products(String productName) {
        this.productName = productName;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productName, products.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public String toString() {
        return "Products{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
