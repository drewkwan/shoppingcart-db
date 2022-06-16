package vttp.shoppingdb.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


//The repository is where we house our file data. The repository object is a file.

public class Repository {
    private File repository;

    public Repository (String repo) {
        this.repository = new File(repo); //constructor 1 instantiates a new file.
    }
    //i need getShopping carts for the USERS function
    public List<String> getShoppingCarts() { 
        List <String> carts = new LinkedList<>();
        for (String n: repository.list()) {
            carts.add(n.replace(".cart", "")); //this is for the list function so that i list all the files less the .cart
        }
        return carts; 
    }

    public void save (ShoppingCartDB cart) {
        String cartName = cart.getUsername() + ".cart"; //save file becomes username plus .cart
        String saveLocation = repository.getPath() + File.separator + cartName; 
        //File separator makes the  \\. so repositroy getPath directs us to folder db and ends with username.cart
        File saveFile = new File(saveLocation);
        OutputStream os = null;

        try {
            if (!saveFile.exists()) {
            Path path = Paths.get(repository.getPath());
            Files.createDirectories(path); //this ensures that a new folder is created is there are no save files. If this doenst exist there will be an error on the first save as the path will not exist.
            saveFile.createNewFile();
        }

        os = new FileOutputStream(saveLocation);
        cart.save(os);
        os.flush();
        
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShoppingCartDB load(String username) {
        String cartName = username + ".cart";
        ShoppingCartDB cart = new ShoppingCartDB(username);
        for (File cartFile: repository.listFiles()) { //if the cart file is in the list of files. 
            if (cartFile.getName().equals(cartName)) { //ensures the correct cart is retrieved
                try{
                    InputStream is = new FileInputStream(cartFile);
                    cart.load(is);
                    is.close();
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
        }
        return cart;
            
    }
}
