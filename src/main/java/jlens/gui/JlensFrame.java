package jlens.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Peter Miklosko
 * Date: 15/11/11
 * Time: 11:04
 */
public class JLensFrame extends JFrame{

    public JLensFrame(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(0,2));
        this.getContentPane().add(new FilesTree());
        this.getContentPane().add(new SwappingPanel());
        this.pack();
        this.setVisible(true);
    }
}
