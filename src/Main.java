import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(2);
        System.out.println(numbers(list));
        findMinMax2(list.stream(),
                Integer::compareTo,
                (integer, integer2) -> System.out.println(integer + " " + integer2));

        findMinMax(list.stream(),
                Integer::compareTo,
                (integer, integer2) -> System.out.println(integer + " " + integer2));
    }

    //Задание 1
    public static <T> void findMinMax2(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.collect(Collectors.toList());
        Optional<T> min = list.stream().min(order);
        Optional<T> max = list.stream().max(order);
          min.ifPresentOrElse(minVal -> max.ifPresent(maxVal -> minMaxConsumer.accept(minVal, maxVal)),
                () -> minMaxConsumer.accept(null, null));
    }

    //Задание 1
    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.collect(Collectors.toList());
        Optional<T> min = list.stream().min(order);
        Optional<T> max = list.stream().max(order);
        if (!list.isEmpty())
            minMaxConsumer.accept(min.get(), max.get());
        else {
            minMaxConsumer.accept(null, null);
        }
    }

    //Задание 2
    public static long numbers(List<Integer> list) {
        return list.stream().filter(s -> s % 2 == 0).count();
    }
}
