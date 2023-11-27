package study.level.high;
import java.util.Collection;
import java.util.EnumSet;
import java.util.IntSummaryStatistics;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// 이넘(Enum) 정의
enum StatisticType {
    SUM, AVERAGE, MIN, MAX, COUNT
}

// 제네릭 유틸리티 클래스 정의
class StatisticsUtility<T extends Number> {

    // 컬렉션의 통계를 계산하는 메서드
    public double calculateStatistic(Collection<T> collection, StatisticType statisticType) {
        ToIntFunction<? super T> mapper = Number::intValue;

        Collector<T, ?, IntSummaryStatistics> collector = Collectors.summarizingInt(mapper);

        IntSummaryStatistics stats = collection.stream().collect(collector);

        switch (statisticType) {
            case SUM:
                return stats.getSum();
            case AVERAGE:
                return stats.getAverage();
            case MIN:
                return stats.getMin();
            case MAX:
                return stats.getMax();
            case COUNT:
                return stats.getCount();
            default:
                throw new IllegalArgumentException("Unsupported StatisticType");
        }
    }
}

public class AdvancedStatExample {
    public static void main(String[] args) {
        // 제네릭 유틸리티 클래스 사용 예제
        StatisticsUtility<Double> doubleUtility = new StatisticsUtility<>();
        StatisticsUtility<Integer> integerUtility = new StatisticsUtility<>();

        // 더미 데이터 생성
        Collection<Double> doubleData = EnumSet.allOf(DummyData.class).stream()
                .map(DummyData::getDoubleValue)
                .collect(Collectors.toList());

        Collection<Integer> integerData = EnumSet.allOf(DummyData.class).stream()
                .map(DummyData::getIntegerValue)
                .collect(Collectors.toList());

        // 통계 계산 및 출력
        System.out.println("Double Data Statistics:");
        printStatistics(doubleUtility, doubleData);

        System.out.println("\nInteger Data Statistics:");
        printStatistics(integerUtility, integerData);
    }

    // 통계 출력 메서드
    private static <T extends Number> void printStatistics(StatisticsUtility<T> utility, Collection<T> data) {
        System.out.println("Sum: " + utility.calculateStatistic(data, StatisticType.SUM));
        System.out.println("Average: " + utility.calculateStatistic(data, StatisticType.AVERAGE));
        System.out.println("Min: " + utility.calculateStatistic(data, StatisticType.MIN));
        System.out.println("Max: " + utility.calculateStatistic(data, StatisticType.MAX));
        System.out.println("Count: " + utility.calculateStatistic(data, StatisticType.COUNT));
    }

    // 더미 데이터 정의
    enum DummyData {
        DATA1(1.5, 10),
        DATA2(2.5, 20),
        DATA3(3.5, 30);

        private final double doubleValue;
        private final int integerValue;

        DummyData(double doubleValue, int integerValue) {
            this.doubleValue = doubleValue;
            this.integerValue = integerValue;
        }

        public double getDoubleValue() {
            return doubleValue;
        }

        public int getIntegerValue() {
            return integerValue;
        }
    }
}
