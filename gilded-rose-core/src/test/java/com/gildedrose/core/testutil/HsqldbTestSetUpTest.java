package com.gildedrose.core.testutil;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HsqldbTestSetUpTest extends HsqldbBaseTestSetup {

    @Override
    protected void initDatabase() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute("DROP TABLE employee IF EXISTS");
            statement.execute("CREATE TABLE employee (id INT NOT NULL,"
                    + " name VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL,"
                    + " PRIMARY KEY (id))");
            connection.commit();

            statement.executeUpdate("INSERT INTO employee VALUES (1001,'Jean Paul Sartre', 'jps@philo.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1002,'Friedrich Nietzsche', 'fn@philo.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1003,'René Descartes', 'rd@philo.com')");
            connection.commit();
        }
    }


    /**
     * Get total records in table
     *
     * @return total number of records. In case of exception 0 is returned
     */
    private int getTotalRecords() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet result = statement.executeQuery("SELECT count(*) as total FROM employee");
            if (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Test
    public void getTotalRecordsTest() {
        assertThat(3, is(getTotalRecords()));
    }

    @Test
    public void checkNameExistsTest() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);) {

            ResultSet result = statement.executeQuery("SELECT name FROM employee");

            if (result.first()) {
                assertThat("Jean Paul Sartre", is(result.getString("name")));
            }

            if (result.last()) {
                assertThat("René Descartes", is(result.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
