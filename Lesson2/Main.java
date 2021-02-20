package Lesson2;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static DataBase dataBase;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        preStartSettings();

        System.out.println("Для регистрации введите\n/reg login password nickname\n");
        System.out.println("Для авторизации введите\n/login login password\n");
        System.out.println("Для смены никнейма введите\n/change login password newNickname\n");

        while (true) {
            String command = scanner.nextLine();

            if (command.startsWith("/login")) {
                String[] messageParts = command.split(" ", 3);
                if (messageParts.length < 3) {
                    System.out.println("Команда введена не верно");
                } else {
                    dataBase.selectUserFromDB(messageParts[1], messageParts[2]);
                }
            }

            if (command.startsWith("/reg")) {
                String[] messageParts = command.split(" ", 4);
                if (messageParts.length < 4) {
                    System.out.println("Команда введена не верно");
                } else {
                    dataBase.addUserToDB(messageParts[1], messageParts[2], messageParts[3]);
                }
            }

            if (command.startsWith("/change")) {
                String[] messageParts = command.split(" ", 4);
                if (messageParts.length < 4) {
                    System.out.println("Команда введена не верно");
                } else {
                    dataBase.changeNicknameInDB(messageParts[1], messageParts[2], messageParts[3]);
                }
            }

            if (command.startsWith("/exit"))
                break;
        }
    }

    public static void preStartSettings() throws SQLException, ClassNotFoundException {
        dataBase = new DataBase("localhost", "3306", "root", "root");
    }
}
