package ru.job4j.ood.srp.wrongdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Нарушение принципа SRP:
 * Не управляем реализацией.
 * Класс как сознаёт экземпляр, так и контроллирует его существование в единственном экземпляре.
 */
public class ArticleDatabaseConnection {

    private static Connection connection;

    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("someUrl");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return connection;
    }
}
