package Lesson2;

import java.sql.*;

public class DataBase {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private final String login, password, host, port;

    public DataBase(String host, String port, String login, String password) throws SQLException, ClassNotFoundException {
        this.host = host;
        this.port = port;
        this.login = login;
        this.password = password;

        firstSettings();
    }

    /**
     * Создание таблицы при инициализации экземпляра класса
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void firstSettings() throws SQLException, ClassNotFoundException {
        connect();
        setStatement();
        createDB();
        closeConnection();
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:users.s2db");
    }

    private static void setStatement() throws SQLException {
        statement = connection.createStatement();
    }

    private static void closeConnection() throws SQLException {
        if (resultSet != null)
            resultSet.close();
        statement.close();
        connection.close();
    }

    private static void createDB() throws SQLException {
        statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'login' text, 'password' text, 'nickname' text);");
    }

    /**
     * Добавление нового пользователя в базу данных
     * @param login String
     * @param password String
     * @param nickname String
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addUserToDB(String login, String password, String nickname) throws SQLException, ClassNotFoundException {
        if (login.length() * password.length() * nickname.length() == 0) {
            System.out.println("Одно из полей пустое");
            return;
        }

        connect();
        setStatement();

        resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE login='%s' OR nickname='%s'", login, nickname));
        if (checkNickname(resultSet))
            return;

        statement.execute(String.format("INSERT INTO users (login, password, nickname) VALUES ('%s', '%s', '%s')", login, password, nickname));
        System.out.println("Регистрация прошла успешно");

        closeConnection();
    }

    /**
     * Выбор пользователя из базы данных
     * @param login String
     * @param password String
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectUserFromDB(String login, String password) throws SQLException, ClassNotFoundException {
        System.out.println(login + " " + password);
        if (login.length() * password.length() == 0) {
            System.out.println("Одно из полей пустое");
            return;
        }

        connect();
        setStatement();

        resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE login='%s' AND password='%s'", login, password));
        if (checkLogin(resultSet))
            return;

        System.out.println("Приветствую, " + resultSet.getString("nickname"));

        closeConnection();
    }

    /**
     * Изменение никнейма
     * @param login String
     * @param password String
     * @param newNickname Новый никнейм (String)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void changeNicknameInDB(String login, String password, String newNickname) throws SQLException, ClassNotFoundException {
        if (login.length() * password.length() * newNickname.length() == 0) {
            System.out.println("Одно из полей пустое");
            return;
        }

        connect();
        setStatement();

        resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE nickname='%s'", newNickname));
        if (checkNickname(resultSet))
            return;

        resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE login='%s' AND password='%s'", login, password));
        if (checkLogin(resultSet))
            return;

        statement.execute(String.format("UPDATE users SET nickname='%s' WHERE login='%s' AND password='%s'", newNickname, login, password));
        System.out.println("Никнейм изменен");

        closeConnection();
    }

    private boolean checkNickname(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            System.out.println("Такой никнейм уже занят");
            return true;
        }

        return false;
    }

    private boolean checkLogin(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            System.out.println("Не верный Логин или Пароль");
            return true;
        }

        return false;
    }
}
