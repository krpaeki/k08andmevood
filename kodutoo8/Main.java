import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    private static int amount(String s) {
        return Integer.parseInt(s.split(" ")[1]);
    }

    public static void main(String[] arg) throws Exception {

        String input = "input.txt";
        String output = "output.txt";
        ArrayList < String > words = new ArrayList < > ();
        Map < String, Long > occurrence;
        ArrayList < String > wordsWithOccurrence = new ArrayList < > ();

        File file = new File(input);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            words.add(scan.next()
                .replaceAll("[^a-zA-Z-]", "")
                .toLowerCase());
        }
        scan.close();

        occurrence = words.stream()
            .collect(groupingBy(Function.identity(), counting()));

        for (Map.Entry < String, Long > entry: occurrence.entrySet()) {
            wordsWithOccurrence.add(entry.getKey() + " " + entry.getValue());
        }

        PrintWriter writer = new PrintWriter(new FileWriter(output));
        wordsWithOccurrence
            .stream()
            .sorted((s1, s2) -> amount(s2) - amount(s1))
            .collect(Collectors.toList())
            .forEach(writer::println);
        writer.close();
    }
}
