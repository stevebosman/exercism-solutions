public class PopCount {
    public int eggCount(int number) {
        int count = 0;
        while (number > 0) {
            count += number % 2;
            number /= 2;
        }
        return count;
    }
}
