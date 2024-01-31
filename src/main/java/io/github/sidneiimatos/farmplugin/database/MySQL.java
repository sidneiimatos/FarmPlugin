package io.github.sidneiimatos.farmplugin.database;

import io.github.sidneiimatos.farmplugin.FarmPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    private String user;
    private String host;
    private String database;
    private String password;
    public static Connection connection;
    static Statement statement;

    public MySQL(String user, String host, String database, String password, FarmPlugin plugin) {
        this.user = user;
        this.host = host;
        this.database = database;
        this.password = password;
    }

    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ this.host + "/"+ this.database, this.user, this.password);
            (statement = connection.createStatement()).execute("CREATE TABLE IF NOT EXISTS farmplugin (username VARCHAR(16) NOT NULL, fungo_qtd INT NOT NULL, melon_qtd INT NOT NULL, sugar_qtd INT NOT NULL, blocos INT NOT NULL, limite INT NOT NULL, farming BOOLEAN NOT NULL)");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
