package com.example.springserver.dir;

public class ListItem {
    public String name;         //имя файла/папки
    public String size;         // размер файла
    public int imageResource;   // ресурс изображения
    public int type;            // тип 0 - файл, 1 - папка

    public ListItem(){
        name = "";
        size = "";
        imageResource = 0;
        type = 0;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", imageResource=" + imageResource +
                ", type=" + type +
                '}';
    }
}
