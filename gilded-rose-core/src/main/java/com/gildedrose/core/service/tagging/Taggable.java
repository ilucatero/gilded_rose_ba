package com.gildedrose.core.service.tagging;

import java.util.List;

public interface Taggable {

    <T> List<T> getTagList();
}
