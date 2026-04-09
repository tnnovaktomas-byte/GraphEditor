package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.AbstractGraphicObject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();
    private AbstractGraphicObject selectedObject;
    private int dx;
    private int dy;

    public Canvas() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedObject = findObjectContaining(e.getPoint());
                if (selectedObject != null) {
                    //nalezen utvar/objekt
                    dx = e.getX() - selectedObject.getPosition().x;
                    dy = e.getY() - selectedObject.getPosition().y;
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(selectedObject != null){
                    selectedObject.setPosition(e.getX()-dx, e.getY()-dy);
                    //                    o.setPosition(e.getPoint());
                    repaint();
                }
                //super.mouseDragged(e);
            }
        });
    }

    private AbstractGraphicObject findObjectContaining(Point point) {
        AbstractGraphicObject result = null;
        for (var object : graphicObjects) {
            if (object.contains(point))
                result = object;
        }
        return result;
    }

    public void add(AbstractGraphicObject object) {
        graphicObjects.add(object);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (var o : graphicObjects) {
            o.draw(g);
        }
    }
}
