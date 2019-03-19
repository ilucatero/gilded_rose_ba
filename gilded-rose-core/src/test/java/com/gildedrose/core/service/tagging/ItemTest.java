package com.gildedrose.core.service.tagging;

import java.util.ArrayList;
import java.util.List;


public class ItemTest implements Taggable {
    public long id;
    public String name;
    public String type;
    public int quality;
    public List<String> tagList = new ArrayList<>(0);

    public ItemTest (long id, String name, String type, int quality){
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.type = type;
    }

    /** ----------- INHERITED METHODS --------- */

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemTest))
            return false;

        ItemTest other = (ItemTest) o;
        boolean nameEquals = (this.name == null && other.name== null) || (this.name!= null && this.name.equals(other.name));
        boolean typeEquals = (this.type == null && other.type== null) || (this.type!= null && this.type.equals(other.type));


        return (this.id==other.id) && nameEquals && typeEquals;
    }

    @Override
    public final int hashCode() {
        int result = Long.hashCode(id);

        if (name!= null) {
            result = 31 * result + name.hashCode();
        }
        if (type!= null) {
            result = 31 * result + type.hashCode();
        }

        return result;
    }


    @Override
    public List<String> getTagList() {
        return tagList;
    }

}
