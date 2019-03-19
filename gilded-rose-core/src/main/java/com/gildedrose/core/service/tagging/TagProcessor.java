package com.gildedrose.core.service.tagging;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.function.Function;

public class TagProcessor {

    private TagProcessor(){}

    /**
     * Giveng a list of items, execute a list of tagging actions.
     * @param withItems the list of items to process
     * @param tagVisitors a list of actions to perform
     * @return the last tagging action performed (so you can use the #then())
     */
    public static <V extends Taggable, T extends Collection<V>, R> void with(T withItems, @NotNull @NotEmpty Function<T, R>... tagVisitors){
        for (Function<T,R> tagVisitor : tagVisitors) {
            tagVisitor.apply(withItems);
        }
    }

}
