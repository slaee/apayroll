
package com.apayroll.swing;

import com.apayroll.models.components.SidebarModel;
import com.apayroll.views.main.MainFrame;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListSidebarItem<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = 0;
    
    public ListSidebarItem(){
        model = new DefaultListModel();
        setModel(model);
        setFixedCellHeight(62);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if(o instanceof SidebarModel){
                        SidebarModel sidebar = (SidebarModel) o;
                        if(sidebar.getType() == SidebarModel.SidebarType.SIDEBAR){
                            selectedIndex = index;
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
            }
        });
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus){
                SidebarModel data;
                
                if(o instanceof SidebarModel){
                    data = (SidebarModel) o;
                } else {
                    data = new SidebarModel("", o+"", SidebarModel.SidebarType.EMPTY);
                }
                SidebarItem item = new SidebarItem(data);
                item.setSelected(selectedIndex==index);
                return item;
            }
            
        };
    }
    
    public int getSelectedIndex(){
        return selectedIndex;
    }
    
    public void addItem(SidebarModel data){
        model.addElement(data);
    }
}
