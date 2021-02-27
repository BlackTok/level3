package lesson3;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final int ROWS_COUNT = 100;
    private static final String ABSOLUTE_PATH = "F:\\";
    private static final String FILE_NAME = "logs";
    private static final String FILE_TYPE = ".txt";
    private static File logs;
    private static int countFileName = 1;
    private static final Map<String, String > censorship = new HashMap<>();

    public static void main(String[] args) throws IOException {
        censorship.put("москва", "Москва");
        censorship.put("спб", "СПб");

        Scanner scanner = new Scanner(System.in);
        logs = new File(ABSOLUTE_PATH + FILE_NAME + FILE_TYPE);

        if (!logs.exists())
            createLogsFile();

        readMessages(logs);

        BufferedWriter logsWriter = new BufferedWriter(new FileWriter(logs, true));

        while (true) {
            String message = scanner.nextLine();
            if (message.startsWith("/exit"))
                break;

            String[] messageWords = message.split(" ");
            for (int i = 0; i < messageWords.length; i++) {
                if (censorship.containsKey(messageWords[i]))
                    messageWords[i] = censorship.get(messageWords[i]);
            }

            StringBuilder newMessageBuilder = new StringBuilder();
            for (int i = 0; i < messageWords.length; i++) {
                newMessageBuilder.append(messageWords[i]);
            }
            String newMessage = newMessageBuilder.toString();

            logsWriter.append(newMessage + "\n");
            logsWriter.flush();
        }

        scanner.close();
        logsWriter.close();
    }

    public static void readMessages(File logs) throws IOException {
        List<String> allRows = Files.readAllLines(logs.toPath());
        int i = 0;

        if (allRows.size() > ROWS_COUNT)
            i = allRows.size() - ROWS_COUNT;

        for (; i < allRows.size(); i++) {
            System.out.println(allRows.get(i));
        }
    }

    public static void createLogsFile() throws IOException {
        logs = new File(ABSOLUTE_PATH + FILE_NAME + countFileName + FILE_TYPE);
        boolean fileIsCreated = logs.createNewFile();
        ++countFileName;

        if (!fileIsCreated) {
            createLogsFile();
        }
    }
}
