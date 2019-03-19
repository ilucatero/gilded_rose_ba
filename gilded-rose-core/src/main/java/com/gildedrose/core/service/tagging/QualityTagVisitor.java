package com.gildedrose.core.service.tagging;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Tagg an element based on selected {@link QUALITY_TAG} and a comparator
 * @param <V> the base object to process
 * @param <T> the collection of objects
 * @param <R> the collection of objects after applying the tagging
 */
public class QualityTagVisitor<V extends Taggable, T extends Collection<V>, R extends Collection<V>>  implements Function<T, R> {

    public enum QUALITY_TAG{HQ, LQ, SELLIN,NORMAL}

    private final QUALITY_TAG TAG ;
    private Comparator<V> c1;

    private QualityTagVisitor(){this(QUALITY_TAG.NORMAL);}
    private QualityTagVisitor(QUALITY_TAG tag){
        this.TAG = tag;
    }

    public static QualityTagVisitor getInstance(QUALITY_TAG tag, Comparator<Object> c1){
        QualityTagVisitor tagVisitor = new QualityTagVisitor(tag);
        tagVisitor.c1 = c1;

        return tagVisitor;
    }

    @Override
    public R apply(T t) {
        // TODO do it by type
        t.stream().sorted(c1).findFirst().get().getTagList().add(TAG.name());
        return (R)t;
    }
}