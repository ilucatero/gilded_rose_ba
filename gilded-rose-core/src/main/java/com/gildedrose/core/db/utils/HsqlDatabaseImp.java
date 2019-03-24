package com.gildedrose.core.db.utils;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HsqlDatabaseImp implements HsqlDatabase{
    private static final String CHANGE_LOG_BASE_V = "/src/main/resources/liquibase/db.changelog.{v}.xml";
    private static final String CONNECTION_BASE_STR = "jdbc:hsqldb:mem:{dbname};shutdown=false";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "sa";

    private Connection holdingConnection;
    private Liquibase liquibase;

    private String basePath;
    private String database;
    private List<String> versions;

    @Override
    public void setDatabase(String database) {
        this.database = database;
    }
    @Override
    public void setVersions(List<String> versions) {
        this.versions = versions;
    }
    @Override
    public void setBasePath(String basePath) {

        // TODO : move to path test/resources once hsql pass in mode unit test only
        basePath = Paths.get(basePath).toAbsolutePath().normalize().toString();

        this.basePath = basePath;
    }
    @Override
    public void setup(String contexts) {
        try {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();

            String connStr = CONNECTION_BASE_STR.replace("{dbname}", database);
            holdingConnection = getConnectionImpl(connStr);
            HsqlConnection hsConn = new HsqlConnection(holdingConnection);

            // apply changes on db
            boolean isFirstRun = false;
            for (String v : versions) {
                String versionToApply = CHANGE_LOG_BASE_V.replace("{v}", v);
                liquibase = new Liquibase(basePath + versionToApply, resourceAccessor, hsConn);
                if(Boolean.FALSE == isFirstRun) {
                    liquibase.dropAll();
                    isFirstRun = true;
                }
                liquibase.update(contexts);
            }

            hsConn.close();
        } catch (Exception ex) {
            log.error("Error during database initialization", ex);
            throw new RuntimeException("Error during database initialization", ex);
        }
    }

    private Connection getConnectionImpl(String connection) throws SQLException {
        return DriverManager.getConnection(connection, USER_NAME, PASSWORD);
    }

    public void init(){
        setup(null);
    }
}