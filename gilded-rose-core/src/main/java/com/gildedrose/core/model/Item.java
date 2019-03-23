package com.gildedrose.core.model;

import com.gildedrose.core.model.type.AgeingMode;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity @Table(name = "tblItems")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue @Column
    public long id;
    @Column public String name;
    @Column public int sellIn;
    @Column public int quality;
    @Column public String type;
    @Column public int ageingDegree;
    @Column @Enumerated(EnumType.STRING) public AgeingMode ageingMode;

    public Item(){}

    public Item(String name, int sellIn, int quality) {
        this(0, name, sellIn, quality, name /* fixme : for prototype simplicity type == name, use a type parameter here */);
    }

    public Item(long id, String name, int sellIn, int quality, String type) {
        this(id, name, sellIn, quality, type, 1, AgeingMode.BAD);
    }

    public Item(long id, String name, int sellIn, int quality, String type, int ageingDegree, AgeingMode ageingMode) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type != null ? type.toUpperCase() : null;
        this.ageingDegree = ageingDegree;
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

    public void setAgeingDegree(int ageingDegree) {
        this.ageingDegree = ageingDegree;
    }

    public AgeingMode getAgeingMode() {
        return ageingMode;
    }

    public void setAgeingMode(AgeingMode ageingMode) {
        this.ageingMode = ageingMode;
    }

}
