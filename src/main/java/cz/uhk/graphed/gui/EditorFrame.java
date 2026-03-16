package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Square;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame {

    private Canvas canvas = new Canvas();

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(canvas, BorderLayout.CENTER);

        initSampledata();

        pack();

    }

    private void initSampledata() {
        canvas.add(new Rectangle(new Point(100,100), Color.BLACK, 50, 20));
        canvas.add(new Square(new Point(100,200), Color.BLUE, 50));
        canvas.add(new Square(new Point(200,100), Color.GREEN, 50));
    }
}
