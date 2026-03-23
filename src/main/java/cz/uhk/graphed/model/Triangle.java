package cz.uhk.graphed.model;

import java.awt.*;
import java.util.Map;

public class Triangle extends AbstractGraphicObject{

    protected int a;
    private int cx, cy; //pomocne souradnice

    public Triangle( Point position, Color color, int a) {
        super(color, position);
        this.a = a;
        computeC();
    }

    private void computeC() {
        cx = position.x + (int) Math.round(a / 2.0);
        cy = position.y - (int) Math.round(a * Math.sin(Math.PI / 3));
    }

    public Triangle() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawLine(position.x, position.y, position.x +a, position.y);
        g2.drawLine(position.x, position.y, cx, cy);
        g2.drawLine(position.x + a, position.y, cx, cy);
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
