package cz.uhk.graphed.gui;

import cz.uhk.graphed.Main;
import cz.uhk.graphed.model.AbstractGraphicObject;
import cz.uhk.graphed.model.GraphicGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private EditorFrame frame;
    private List<AbstractGraphicObject> graphicObjectList = new ArrayList<>();
    private AbstractGraphicObject selectedObject = null;
    private Point mouseOffset;

    public Canvas(EditorFrame frame) {
        this.frame = frame;
        setBackground(Color.white);
        setPreferredSize(new Dimension(800, 600));
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedObject != null) {
                    selectedObject.setPosition(new Point(e.getX() - mouseOffset.x, e.getY() - mouseOffset.y));
                    repaint();
                }
                frame.updateLabelCursorPosition(e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                frame.updateLabelCursorPosition(e.getPoint());
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedObject = findObjectContaining(e.getPoint());
                if (selectedObject != null) {
                    mouseOffset = new Point(e.getX() - selectedObject.getPosition().x, e.getY() - selectedObject.getPosition().y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedObject = null;
            }
        });
    }

    private AbstractGraphicObject findObjectContaining(Point point) {
        AbstractGraphicObject result = null;
        for (AbstractGraphicObject object : graphicObjectList) {
            if (object.contains(point)) {
                result = object;
            }
        }
        return result;
    }

    public void add(AbstractGraphicObject object) {
        graphicObjectList.add(object);
        repaint();
        frame.updateLabelObjectsCount(graphicObjectList.size(), computeTotalObjectCount());
    }

    private int computeTotalObjectCount() {
        int totalObjectCount = 0;
        for (AbstractGraphicObject obj : graphicObjectList) {
            if (obj instanceof GraphicGroup) {
                totalObjectCount += ((GraphicGroup) obj).getItemList().size();
            } else {
                totalObjectCount++;
            }
        }
        return totalObjectCount;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (var obj : graphicObjectList) {
            obj.draw(g);
        }
    }
}