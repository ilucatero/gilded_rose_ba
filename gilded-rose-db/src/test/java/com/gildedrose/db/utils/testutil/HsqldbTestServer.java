package com.gildedrose.db.utils.testutil;


import java.util.Arrays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;
import org.hsqldb.Server;


/**
 * Create a simple hsqldb server and schema to use for testing.
 */
@Slf4j
public class HsqldbTestServer {

    // singleton server instance.
    private static Server server;

    // if -Dhsql.server.host hasn't been set to something like hsql://localhost.localdomain/ a defaul in-mem DB will be used
    private static final String IN_MEM = "mem:";

    // Database name can be altered too
    private static final String DATABASE_NAME = System.getProperty("hsql.database.name",  "db1");

    // hsqldb always capitalizes table and column names
    private static final String DUMMY_TABLE_NAME = "TWOINTTABLE";
    private static final String [] TWO_INT_TABLE_FIELDS = { "INTFIELD1", "INTFIELD2", };

    private static final String DB_URL = "jdbc:hsqldb:" + getServerHost() + DATABASE_NAME;
    private static final String DRIVER_CLASS = "org.hsqldb.jdbcDriver";

    // all user-created HSQLDB tables are in the "PUBLIC" schema when connected to a database.
    private static final String HSQLDB_SCHEMA_NAME = "PUBLIC";

    private static boolean inMemoryDB = IN_MEM.equals(getServerHost());


    public static String getServerHost() {
        String host = System.getProperty("hsql.server.host", IN_MEM);
        if (!host.endsWith("/")) {
            host += "/";
        }
        return host;
    }

    public static String getSchemaName() {
        return HSQLDB_SCHEMA_NAME;
    }

    public static String [] getFieldNames() {
        return Arrays.copyOf(TWO_INT_TABLE_FIELDS, TWO_INT_TABLE_FIELDS.length);
    }

    /**
     * Returns database URL for the server instance.
     * @return String representation of DB_URL
     */
    public static String getUrl() {
        return DB_URL;
    }

    public static String getTableName() {
        return DUMMY_TABLE_NAME;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    /**
     * start the server.
     */
    public void start() {
        if (null == server) {
            log.info("Starting new hsqldb server; database=" + DATABASE_NAME);
            String tmpDir = System.getProperty("test.build.data", "/tmp/");
            String dbLocation = tmpDir + "/testdb.file";
            if (inMemoryDB) {
                dbLocation = IN_MEM;
            }
            server = new Server();

            server.setDatabaseName(0, DATABASE_NAME);
            server.putPropertiesFromString("database.0=" + dbLocation + ";no_system_exit=true");
            server.start();
        }
    }

    /**
     * stop the server.
     */
    public void stop() {
        if (null != server) {
            server.stop();
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException cnfe) {
            log.error("Could not get connection; driver class not found: " + DRIVER_CLASS);
            return null;
        }

        Connection connection = DriverManager.getConnection(DB_URL);
        connection.setAutoCommit(false);
        return connection;
    }



    /**
     * Create a table.
     */
    public void createSchema() throws SQLException {

        Connection connection = null;
        Statement st = null;

        try {
            connection = getConnection();

            st = connection.createStatement();
            st.executeUpdate("DROP TABLE " + DUMMY_TABLE_NAME + " IF EXISTS");
            st.executeUpdate("CREATE TABLE " + DUMMY_TABLE_NAME + "(intField1 INT, intField2 INT)");

            connection.commit();
        } finally {
            if (null != st) {
                st.close();
            }

            if (null != connection) {
                connection.close();
            }
        }
    }


    /**
     * Delete any existing tables.
     */
    public void dropExistingSchema() throws SQLException {
//        ConnManager mgr = getManager();
//        String [] tables = mgr.listTables();
//        if (null != tables) {
//            Connection conn = mgr.getConnection();
//            for (String table : tables) {
//                Statement s = conn.createStatement();
//                try {
//                    s.executeUpdate("DROP TABLE " + table);
//                    conn.commit();
//                } finally {
//                    s.close();
//                }
//            }
//        }
    }


}