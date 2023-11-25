package study.generic.low;

class ArrayManipulator {
    public static <T> void swap(T[] array, int index1, int index2) {
        if(index2 < 0 || index1 >= array.length
                || index2 < 0 || index2 >= array.length){
            throw new IllegalArgumentException("Invalid indices");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> void printArray(T[] array){
        for(T e : array){
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
public class GenericArrayManipulator {
    public static void main(String[] args) {
        Integer[] intArray = {1,2,3,4,5};
        String[] strArray = {"Apple","Banana","Grape","Orange"};

        System.out.println("Original Array : ");
        ArrayManipulator.printArray(intArray);

        ArrayManipulator.swap(intArray,0,2);
        System.out.println("Array Swaping index : 0 and index : 2");
        ArrayManipulator.printArray(intArray);

        System.out.println("Original Array : ");
        ArrayManipulator.printArray(strArray);

        ArrayManipulator.swap(strArray, 1,2);
        System.out.println("Array Swaping index : 1 and index : 2");
        ArrayManipulator.printArray(strArray);
    }
}
