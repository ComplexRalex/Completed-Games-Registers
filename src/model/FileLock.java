package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileLock {

    private FileInputStream file;
    private String path;

    public FileLock(String path) {
        file = null;
        this.path = path;
    }

    public void lock() throws FileNotFoundException {
        file = new FileInputStream(path);
    }

    public void unlock() throws IOException {
        if(file != null){
            file.close();
            file = null;
        }
    }

}