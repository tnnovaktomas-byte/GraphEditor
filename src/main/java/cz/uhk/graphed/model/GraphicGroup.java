package cz.uhk.graphed.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicGroup extends AbstractGraphicObject{
    protected List<AbstractGraphicObject> items = new ArrayList<>();
    private List<Point> itemsOffset = new ArrayList<>();
    public GraphicGroup() {
        position = new Point(0, 0);
    }

    public GraphicGroup(List<AbstractGraphicObject> items) {
        position = new Point(0, 0);
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            itemsOffset.add(new Point(items.get(i).position.x - position.x, items.get(i).position.y - position.y));
        }
    }

    public void add(AbstractGraphicObject item) {
        items.add(item);
        itemsOffset.add(new Point(item.position.x - position.x, item.position.y - position.y));
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setPosition(new Point(position.x + itemsOffset.get(i).x, position.y + itemsOffset.get(i).y));
            items.get(i).draw(g);
        }
    }

    @Override
    public boolean contains(Point p) {
        for(AbstractGraphicObject item : items) {
            if(item.contains(p)) {
                return true;
            }
        }
        return false;
    }

    public List getItemList() {
        return items;
    }
}