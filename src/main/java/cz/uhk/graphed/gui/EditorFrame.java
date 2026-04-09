package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditorFrame extends JFrame {
    ;
    private Canvas canvas = new Canvas(this);

    public EditorFrame() throws HeadlessException {
        super("Graphic Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(canvas, BorderLayout.CENTER);
        add(createToolBar(), BorderLayout.NORTH);
        add(createStatusPanel(), BorderLayout.SOUTH);
        initSampleData();
        pack();
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        Action actionSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(new Square(new Point(0, 0), Color.BLACK, 50));
            }
        };
        actionSquare.putValue(Action.SHORT_DESCRIPTION, "Draws a square.");
        toolBar.add(actionSquare);
        Action actionRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(new Rectangle(new Point(0, 0), Color.BLACK, 50, 100));
            }
        };
        actionRectangle.putValue(Action.SHORT_DESCRIPTION, "Draws a rectangle.");
        toolBar.add(actionRectangle);
        Action actionCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(new Circle(new Point(0, 0), Color.BLACK, 50));
            }
        };
        actionCircle.putValue(Action.SHORT_DESCRIPTION, "Draws a circle.");
        toolBar.add(actionCircle);
        Action actionTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(new Triangle(new Point(0, 50), Color.BLACK, 50));
            }
        };
        actionTriangle.putValue(Action.SHORT_DESCRIPTION, "Draws a triangle.");
        toolBar.add(actionTriangle);
        return toolBar;
    }

    private JLabel labelCursorPosition;
    private JLabel labelObjectsCount;
    private JPanel createStatusPanel() {
        labelCursorPosition = new JLabel("[x, y]");
        labelObjectsCount = new JLabel("Objects: 0");

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 3 ,10));
        statusPanel.add(labelCursorPosition, BorderLayout.WEST);
        statusPanel.add(labelObjectsCount, BorderLayout.EAST);
        return statusPanel;
    }

    public void updateLabelCursorPosition(Point position) {
        labelCursorPosition.setText(String.format("[ %d, %d ]", position.x, position.y));
    }

    public void updateLabelObjectsCount(int count, int totalCount) {
        labelObjectsCount.setText(String.format("Logic Objects: %d, Total Objects: %d", count, totalCount));
    }

    private void initSampleData() {
        canvas.add(new Rectangle(new Point(100, 200), Color.RED, 150, 50));
        canvas.add(new Circle(new Point(300, 100), Color.BLACK, 50));

        GraphicGroup group1 = new GraphicGroup();
        group1.add(new Square(new Point(100, 100), Color.BLACK, 50));
        group1.add(new Square(new Point(100, 150), Color.BLACK, 50));
        group1.add(new Triangle(new Point(100, 100), Color.BLACK, 50));
        canvas.add(group1);

        ArrayList<AbstractGraphicObject> group2 = new ArrayList<>();
        group2.add(new Square(new Point(200, 100), Color.BLUE, 50));
        group2.add(new Triangle(new Point(200, 100), Color.BLUE, 50));
        canvas.add(new GraphicGroup(group2));

    }
}