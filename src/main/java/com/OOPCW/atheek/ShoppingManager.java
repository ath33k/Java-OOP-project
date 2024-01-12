package com.OOPCW.atheek;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ShoppingManager {
    void addProduct(Product product);
    void deleteProduct(String id);
    void printAllProducts();
    void saveFile() throws IOException;
    void loadFile() throws IOException, ClassNotFoundException;
}
