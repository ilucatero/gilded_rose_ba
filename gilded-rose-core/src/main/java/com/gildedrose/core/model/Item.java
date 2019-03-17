package com.gildedrose.core.model;

import com.gildedrose.core.model.type.AgeingMode;


public class Item {
    public long id;
    public String name;
    public int sellIn;
    public int quality;
    public String type;
    public float ageingDegree;
    public AgeingMode ageingMode;

    public Item(){}

    public Item(String name, int sellIn, int quality) {
        this(0, name, sellIn, quality, name /* fixme : for prototype simplicity type == name, use a type parameter here */);
    }

    public Item(long id, String name, int sellIn, int quality, String type) {
        this(id, name, sellIn, quality, type, 1.0f, AgeingMode.BAD);
    }

    public Item(long id, String name, int sellIn, int quality, String type, float agingDegree, AgeingMode ageingMode) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type != null ? type.toUpperCase() : null;
        this.ageingDegree = agingDegree;
        this.ageingMode = ageingMode;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAgeingDegree() {
        return ageingDegree;
    }

    public void setAgeingDegree(float ageingDegree) {
        this.ageingDegree = ageingDegree;
    }

    public AgeingMode getAgeingMode() {
        return ageingMode;
    }

    public void setAgeingMode(AgeingMode ageingMode) {
        this.ageingMode = ageingMode;
    }



    /** ----------- INHERITED METHODS --------- */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("id:").append(this.id)
                .append(", name:").append(this.name)
                .append(", sellIn:").append(this.sellIn)
                .append(", quality:").append(this.quality)
                .append(", type:").append(this.type)
                .append(", ageingDegree:").append(this.ageingDegree)
                .append(", ageingMode:").append(this.ageingMode);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item ))
            return false;

        Item other = (Item ) o;
        boolean nameEquals = (this.name == null && other.name== null) || (this.name!= null && this.name.equals(other.name));
        boolean typeEquals = (this.type == null && other.type == null) || (this.type!= null && this.type.equals(other.type));
        boolean ageingModeEquals = (this.ageingMode == null && other.ageingMode == null)
                || (this.ageingMode!= null && this.ageingMode.equals(other.ageingMode));

        return (this.id==other.id) && nameEquals && (this.sellIn==other.sellIn) && (this.quality==other.quality)
                && typeEquals  && (this.ageingDegree==other.ageingDegree) && ageingModeEquals;
    }

    @Override
    public final int hashCode() {
        int result = Long.hashCode(id);

        if (name!= null) {
            result = 31 * result + name.hashCode();
        }

        result = 31 * result + Integer.hashCode(sellIn);
        result = 31 * result + Integer.hashCode(quality);

        if (type!= null) {
            result = 31 * result + type.hashCode();
        }

        result = 31 * result + Float.hashCode(ageingDegree);

        if (ageingMode!= null) {
            result = 31 * result + ageingMode.hashCode();
        }


        return result;
    }
}
