package vttp.shoppingdb.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/*
 * This is the class that will contain the methods we need i.e. add/load etc.
 */
public class ShoppingCartDB {
    private List<String> contents = new LinkedList<>();
    

    private String username;

    public ShoppingCartDB(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getContents() {
        return contents;
    }
        
    public void add (String item) {
        for (String inCart: contents)
            if(inCart.equals(item)) {
                return;
            }
            contents.add(item);
    }

    public String delete(int index) {
        if(index < contents.size()) {
            return contents.remove(index);
        }
        else {
            return "There is no such item";
        }
    }

    public void load(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String item;
        while ((item = br.readLine()) != null) {
            contents.add(item);
        }
        br.close();
        isr.close();
    }

    public void save(OutputStream os) throws IOException {
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);
        for (String item: contents) {
            bw.write(item + "\n");
        }

        ows.flush();
        bw.flush();
        bw.close();
        ows.close();        
    }

}

