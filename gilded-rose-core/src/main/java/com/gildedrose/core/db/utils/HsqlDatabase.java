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
public class HsqlDatabase {
    private static final String CHANGE_LOG_BASE_V = "/src/main/resources/liquibase/db.changelog.{v}.xml";
    private static final String CONNECTION_BASE_STR = "jdbc:hsqldb:mem:{dbname};shutdown=false";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "sa";

    private Connection holdingConnection;
    private Liquibase liquibase;

    public void setUp(String contexts, String database, List<String> versions) {
        try {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();

            // TODO : move to path test/resources once hsql pass in mode unit test only
            String basePath = Paths.get("").toAbsolutePath().normalize().toString();
            // workaround for tests since this have bugs with the path
            basePath = basePath.replace("gilded-rose-core", "");
            basePath = basePath.replace("gilded-rose-web", "");
            basePath = basePath +  "gilded-rose-core";

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
}