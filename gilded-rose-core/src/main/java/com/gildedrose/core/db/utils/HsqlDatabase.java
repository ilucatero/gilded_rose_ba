package com.gildedrose.core.db.utils;


import java.util.List;

public interface HsqlDatabase {

    public void setDatabase(String database);

    public void setVersions(List<String> versions);

    public void setBasePath(String basePath);

    public void setup(String contexts) ;
}