package jlens.processing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 15/11/11
 * Time: 11:23
 */
public class DataExtraction {
    //TODO temporary location for metadata functionality
    public DataExtraction() {
        File image = new File("C:\\github\\jlens\\src\\main\\resources\\500d.jpg");
        Path file = image.toPath();
        UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
        try {
            List<String> list = view.list();
            for (String listElement : list) {
                System.out.println(listElement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
