package jlens;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

public class JLens {

    public static void main(String[] args) {
        File image = new File("IMG_2075.CR2");
        Path file = image.toPath();
        FileStore store = null;
        try {
            store = Files.getFileStore(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
            System.err.format("UserDefinedFileAttributeView not supported on %s\n", store);
            System.exit(-1);

        }
        UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
        try{
            List<String> list = view.list();
            for(String listElement : list){
                System.out.println(listElement);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
