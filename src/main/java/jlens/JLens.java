package jlens;

import jlens.gui.JLensFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.UIManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;


/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 */
public class JLens {
    private static final Logger LOG = LoggerFactory.getLogger(JLens.class);

    public static void main(String[] args) {
        //TODO replace with image of logo
        File image = new File("C:\\github\\jlens\\src\\main\\resources\\500d.jpg");
        Path file = image.toPath();
        FileStore store = null;
        try {
            store = Files.getFileStore(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
            LOG.error("UserDefinedFileAttributeView not supported on %s\n", store);
            System.exit(-1);

        } else {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
        }
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOG.info("Couldn't use system look and feel.");
        }

        new JLensFrame("JLens");
    }
}
