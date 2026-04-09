package cz.uhk.graphed.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject{

    protected int r;

    public Circle(Point position, Color color, int r) {
        super(position, color);
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.drawOval(position.x, position.y, 2 * r, 2 * r);
    }

    @Override
    public boolean contains(Point p) {
        return Math.pow(p.x - position.x - r, 2) + Math.pow(p.y - position.y - r, 2) <= r * r;
    }
}
