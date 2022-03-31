/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models.components;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author sly
 */
public class SidebarModel {

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public SidebarType getType() {
        return type;
    }
    
    public SidebarModel(String icon, String name, SidebarType type){
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
    
    public SidebarModel(){
    }
    
    /**
     * @param type the type to set
     */
    public void setType(SidebarType type) {
        this.type = type;
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource("/com/apayroll/icons/"+icon+".png"));
    }
    
    private String icon;
    private String name;
    private SidebarType type;
    
    public static enum SidebarType {
        TITLE, SIDEBAR, EMPTY
    }
}
