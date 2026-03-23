package cz.uhk.graphed.model;

import java.awt.*;

public class Rectangle extends AbstractGraphicObject{

    protected int a;
    protected int b;

    public Rectangle(Point position, Color color, int a, int b) {
        super(color, position);
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2  = (Graphics2D) g;
        g2.setColor(color);
        g2.drawRect(position.x, position.y, a, b);
    }

    @Override
    public boolean contains(Point p) {
        return (p.x >= position.x && p.x <= position.x + a) && (p.y >= position.y && p.y <= position.y + b);
    }
}
