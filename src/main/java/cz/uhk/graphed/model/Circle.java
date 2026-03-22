package cz.uhk.graphed.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject{

    protected int a;
    protected int b;

    public Circle(Point position, Color color, int a) {
        super(color, position);
        this.a = a;
//        this.b = b;
    }

/*    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }*/

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.drawOval(position.x, position.y, a, a);
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
