import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo{
    public static void main(String[] args) throws IOException {
        // 1. Integer Stream
        IntStream
                .range(1,10)
                .forEach(x->System.out.print(x+"\t"));
        System.out.println("\n=========================================\n");

        // 2. Integer Stream with skip()
        IntStream
                .range(1,10)
                .skip(5)
                .forEach(x->System.out.print(x+"\t"));
        System.out.println("\n=========================================\n");

        // 3. Integer Stream with sum()
        System.out.println(IntStream
                .range(1,5)
                .sum());

        System.out.println("\n=========================================\n");

        // 4. Streaam.of , sorted and findFirst
        Stream.of("Snaaa","Noha","Alaa")
                .sorted()
                .findFirst()
                .ifPresent(x->System.out.print(x+"\t"));

        System.out.println("\n=========================================\n");

        // 5. Streaam From Array , sort, filter and print
        String [] names ={"fathia","fatma","Alaa","mahmoud","fathy","layla","fawzy"};
        Arrays.stream(names)
                .filter(s->s.startsWith("f"))
                .sorted()
                .forEach(x->System.out.print(x+"\t"));

        System.out.println("\n=========================================\n");

        // 6. Avarage of squares an int array

        Arrays.stream(new int[]{2,4,6,8,10})
                .map(x->x*x)
                .average()
                .ifPresent(x->System.out.print(x));

        System.out.println("\n=========================================\n");

        // 7. stream from list, filter and print
        List<String> people = Arrays.asList("fathia","fatma","Alaa","mahmoud","fathy","layla","fawzy");
        people
                .stream()
                .map(x->x.toUpperCase())
                .filter(x->x.startsWith("M"))
                .forEach(x->System.out.print(x+"\t"));

        System.out.println("\n=========================================\n");

        // 8. stream from text file sort, filter and print
        Stream<String> fruits =  Files.lines(Paths.get("fruits"));
        fruits
                .filter(x->x.length()<5)
                .forEach(x->System.out.print(x+"\t"));

        System.out.println("\n=========================================\n");

        // 9. stream from text file and save to list
        List<String> fruits2 =  Files.lines(Paths.get("fruits"))
                .filter(x->x.contains("an"))
                .collect(Collectors.toList());

        fruits2.forEach(s->System.out.print(s+"\t"));

        System.out.println("\n=========================================\n");

        // 10. Stream rows from txt file and count
        Stream<String> rows1 = Files.lines(Paths.get("data"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();
        System.out.println("\n=========================================\n");

        // 11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("data"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        System.out.println("\n=========================================\n");

        // 12. Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("data"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }

        System.out.println("\n=========================================\n");

        // 13. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println("Total = " + total);

        System.out.println("\n=========================================\n");

        // 14. Reduction - summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);
        
    }




}
