package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    private void executeSQL(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s);",
                tableName,
                "id SERIAL PRIMARY KEY"
        );
        executeSQL(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s",
                tableName
        );
        executeSQL(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD %s %s",
                tableName,
                columnName,
                type
        );
        executeSQL(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName
        );
        executeSQL(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        );
        executeSQL(sql);
        System.out.println(getTableScheme(tableName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            LOG.error("Error with app.properties file", e);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("newTable");
            tableEditor.addColumn("newTable", "name", "varchar(255)");
            tableEditor.renameColumn("newTable", "name", "name2");
            tableEditor.dropColumn("newTable", "name2");
            tableEditor.dropTable("newTable");
        } catch (Exception e) {
            LOG.error("Table editor exc", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
