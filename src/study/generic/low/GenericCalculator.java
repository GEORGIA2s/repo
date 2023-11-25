package study.generic.low;

interface Calculable<T> {
    T add(T value, T operand);
    T subtract(T value, T operand);
    T multiply(T value, T operand);
    T divide(T value, T operand);
}

class Calculator<T extends Number> implements Calculable<T> {

    @Override
    public T add(T value, T operand) {
        if(value instanceof Integer){
            return (T) Integer.valueOf(value.intValue() + operand.intValue());
        }else if(value instanceof Double){
            return (T) Double.valueOf(value.doubleValue() + operand.doubleValue());
        }
        throw new IllegalArgumentException("TYPE ERROR");
    }

    @Override
    public T subtract(T value, T operand) {
        if(value instanceof Integer){
            return (T) Integer.valueOf(value.intValue() - operand.intValue());
        }else if(value instanceof Double){
            return (T) Double.valueOf(value.doubleValue() - operand.doubleValue());
        }
        throw new IllegalArgumentException("TYPE ERROR");
    }

    @Override
    public T multiply(T value, T operand) {
        if(value instanceof Integer){
            return (T) Integer.valueOf(value.intValue() * operand.intValue());
        }else if(value instanceof Double){
            return (T) Double.valueOf(value.doubleValue() * operand.doubleValue());
        }
        throw new IllegalArgumentException("TYPE ERROR");
    }

    @Override
    public T divide(T value, T operand) {
        if(value instanceof Integer){
            return (T) Integer.valueOf(value.intValue() / operand.intValue());
        }else if(value instanceof Double){
            return (T) Double.valueOf(value.doubleValue() / operand.doubleValue());
        }
        throw new IllegalArgumentException("TYPE ERROR");
    }
}

public class GenericCalculator {
    public static void main(String[] args) {
        Calculator<Integer> intCal = new Calculator<>();
        Calculator<Double> doubleCal = new Calculator<>();

        Integer result = intCal.add(5,10);
        System.out.println("INT RESULT : " + result);

        Integer result_2 = intCal.subtract(5,10);
        System.out.println("INT RESULT : " + result_2);

        Double result_3 = doubleCal.multiply(7.5,2.5);
        System.out.println("DOUBLE RESULT : " + result_3);

        Double result_4 = doubleCal.divide(7.5,2.5);
        System.out.println("DOUBLE RESULT : " + result_4);
    }
}
