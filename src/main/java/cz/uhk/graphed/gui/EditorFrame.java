package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;

import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditorFrame extends JFrame {

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

    private Random random = new Random();

    private Point getRandomPoint(){
        int x = random.nextInt(700);
        int y = random.nextInt(500) + 50;
        return new Point(x, y);
    }

    private Color getRandomColor(){
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        //Square
        Action actionSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = random.nextInt(80) + 20; //20-100px
                canvas.add(new Square(getRandomPoint(), getRandomColor(), size));
            }
        };
        actionSquare.putValue(Action.SHORT_DESCRIPTION, "Draws a square.");
        toolBar.add(actionSquare);

        //Rectangle
        Action actionRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = random.nextInt(80) + 20;
                int b = random.nextInt(80) + 20;
                canvas.add(new Rectangle(getRandomPoint(), getRandomColor(), a, b));
            }
        };
        actionRectangle.putValue(Action.SHORT_DESCRIPTION, "Draws a rectangle.");
        toolBar.add(actionRectangle);

        //Circle
        Action actionCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = random.nextInt(100) + 10;
                canvas.add(new Circle(getRandomPoint(), getRandomColor(), r));
            }
        };
        actionCircle.putValue(Action.SHORT_DESCRIPTION, "Draws a circle.");
        toolBar.add(actionCircle);


        //Triangle
        Action actionTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = random.nextInt(100) + 20;
                canvas.add(new Triangle(getRandomPoint(), getRandomColor(), a));
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