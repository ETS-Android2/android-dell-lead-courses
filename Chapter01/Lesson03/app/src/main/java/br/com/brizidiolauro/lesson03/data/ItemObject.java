package br.com.brizidiolauro.lesson03.data;

import android.graphics.drawable.Drawable;

public class ItemObject {
    private Drawable icon;
    private String filename;

    public ItemObject(String filename,Drawable icon){
        this.filename = filename;
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
