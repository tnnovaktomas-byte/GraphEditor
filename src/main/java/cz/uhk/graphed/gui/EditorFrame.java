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

    private Color getRandomColor(){
        int a = random.nextInt(256);
        int b = random.nextInt(256);
        int c = 0;
        int d = 255;
        
        if (a == d && b == d) {
            c = random.nextInt(100);
        } else if (a == d && b <= 100) {
            c = random.nextInt(256);
        } else if (a <= 100 && b == d){
            c = random.nextInt(256);
        } 
/*        int b = 0;
        int c = 0;
        int d = 255;

        if (a == d) {
            b = random.nextInt(256);
                    if (b == d)     c = random.nextInt(100);
        }

        b = random.nextInt(256);
        if (b == d || a <=100) {
            c = random.nextInt(256);
        }
        int c = random.nextInt(256);
*/

        return new Color(a, b, c);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        //Square
        Action actionSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = random.nextInt(100) + 20;
                int x = random.nextInt(Math.max(1, canvas.getWidth() - size));
                int y = random.nextInt(Math.max(1, canvas.getHeight() - size));
                canvas.add(new Square(new Point(x, y), getRandomColor(), size));
            }
        };
        actionSquare.putValue(Action.SHORT_DESCRIPTION, "Draws a square.");
        toolBar.add(actionSquare);

        //Rectangle
        Action actionRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = random.nextInt(100) + 20;
                int height = random.nextInt(100) + 20;
                int x = random.nextInt(Math.max(1, canvas.getWidth() - width));
                int y = random.nextInt(Math.max(1, canvas.getHeight() - height));
                canvas.add(new Rectangle(new Point(x, y), getRandomColor(), width, height));
            }
        };
        actionRectangle.putValue(Action.SHORT_DESCRIPTION, "Draws a rectangle.");
        toolBar.add(actionRectangle);

        //Circle
        Action actionCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int radius = random.nextInt(100) + 15;
                int diamater = radius * 2;
                int x = random.nextInt(Math.max(1, canvas.getWidth() - diamater));
                int y = random.nextInt(Math.max(1, canvas.getHeight() - diamater));
                canvas.add(new Circle(new Point(x, y), getRandomColor(), radius));
            }
        };
        actionCircle.putValue(Action.SHORT_DESCRIPTION, "Draws a circle.");
        toolBar.add(actionCircle);


        //Triangle
        Action actionTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int side = random.nextInt(100) + 20;
                int height = (int)(side * Math.sin(Math.PI / 3));
                int x = random.nextInt(Math.max(1, canvas.getWidth() - side));
                int y = random.nextInt(Math.max(1, canvas.getHeight() - height)) + height;
                canvas.add(new Triangle(new Point(x, y), getRandomColor(), side));
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