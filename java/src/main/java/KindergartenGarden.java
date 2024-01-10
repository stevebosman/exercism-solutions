import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class KindergartenGarden {
  private static final List<String> students = List.of(
          "Alice", "Bob", "Charlie", "David",
          "Eve", "Fred", "Ginny", "Harriet",
          "Ileana", "Joseph", "Kincaid", "Larry");
  private final List<Map<Integer, Plant>> gardenRows;

  KindergartenGarden(final String garden) {
    gardenRows = Arrays.stream(garden.split("\n"))
                       .map(this::rowList)
                       .toList();
  }

  private Map<Integer, Plant> rowList(final String row) {
    return IntStream.range(0, row.length())
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(),i -> getPlant(row.charAt(i))));
  }

  private Plant getPlant(final char c) {
    return switch (c) {
      case 'C' -> Plant.CLOVER;
      case 'G' -> Plant.GRASS;
      case 'R' -> Plant.RADISHES;
      case 'V' -> Plant.VIOLETS;
      default -> throw new IllegalArgumentException(c + " is not a plant");
    };
  }

  List<Plant> getPlantsOfStudent(final String student) {
    final int studentIdOffset = students.indexOf(student) * 2;
    final Map<Integer, Plant> firstRow = gardenRows.get(0);
    final Map<Integer, Plant> secondRow = gardenRows.get(1);
    return List.of(firstRow.get(studentIdOffset), firstRow.get(studentIdOffset + 1),
                   secondRow.get(studentIdOffset), secondRow.get(studentIdOffset + 1));
  }

}
