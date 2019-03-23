package com.gildedrose.core.testutil;

import org.junit.*;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public abstract class HsqldbBaseTestSetup {

    @Before
    public void setup() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        // initialize database
        initDatabase();
    }


    @After
    public void tearDown() throws SQLException, ClassNotFoundException, IOException {
        try ( Connection connection = getConnection();
              Statement statement = connection.createStatement();
             ){

            statement.execute("SET DATABASE REFERENTIAL INTEGRITY FALSE");
            Set<String> tables = new HashSet<String>();
            ResultSet rs = statement.executeQuery("SELECT table_name " +
                    "FROM INFORMATION_SCHEMA.system_tables " +
                    "WHERE table_type='TABLE' AND table_schem='PUBLIC'");
            while (rs.next()) {
                if (!rs.getString(1).startsWith("DUAL_")) {
                    tables.add(rs.getString(1));
                }
            }
            rs.close();
            for (String table : tables) {
                statement.executeUpdate("DELETE FROM " + table);
                statement.execute("DROP TABLE "+ table + " IF EXISTS");
            }
            statement.execute("SET DATABASE REFERENTIAL INTEGRITY TRUE");

            statement.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
            connection.commit();
        }
    }


    /**
     * Database initialization for testing i.e.
     * <ul>
     * <li>Creating Table</li>
     * <li>Inserting record</li>
     * </ul>
     *
     * @throws SQLException
     */
    protected void initDatabase() throws SQLException {
    }

    /**
     * Create a connection
     *
     * @return connection object
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "sa");
    }

}
