package com.gildedrose.core.service.tagging;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * Tagg an element based on selected {@link QUALITY_TAG} and a comparator
 * @param <V> the base object to process
 * @param <T> the collection of objects
 * @param <R> the collection of objects after applying the tagging
 */
public class QualityTagFunction<V extends Taggable, T extends Collection<V>, R extends Collection<V>>  implements Function<T, R> {

    public enum QUALITY_TAG{HQ, LQ, SELLIN, NORMAL}

    private final QUALITY_TAG TAG ;
    private Comparator<V> c1;
    private Collector<V, ?, Map<String, List<V>>> collector;

    private QualityTagFunction(){this(QUALITY_TAG.NORMAL);}
    private QualityTagFunction(QUALITY_TAG tag){
        this.TAG = tag;
    }

    public static  <V extends Taggable> QualityTagFunction getInstance(QUALITY_TAG tag, Comparator<V> c1){
        return getInstance(tag, null, c1);
    }

    public static <V extends Taggable> QualityTagFunction getInstance(QUALITY_TAG tag,
                                                                      Collector<V, ?, Map<String, List<V>>> collector, Comparator<V> c1){

        QualityTagFunction tagVisitor = new QualityTagFunction(tag);
        tagVisitor.c1 = c1;
        tagVisitor.collector = collector;

        return tagVisitor;
    }

    @Override
    public R apply(T t) {
        if(this.collector != null) {
            t.stream().collect(collector).forEach( (s, objects) ->
                    objects.stream().sorted(c1).findFirst().get().getTagList().add(TAG.name())
            );
        } else {
            t.stream().sorted(c1).findFirst().get().getTagList().add(TAG.name());
        }
        return (R)t;
    }
}