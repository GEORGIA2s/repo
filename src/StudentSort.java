import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(9, "이순신", 50, 20, 90));
        list.add(new Student(1, "홍길동", 90, 100, 60));
        list.add(new Student(7, "어우동", 80, 80, 45));
        list.add(new Student(5, "성춘향", 70, 60, 30));
        list.add(new Student(3, "강감찬", 60, 40, 75));


        System.out.println("정렬 전");
        printStudent(list);

        Collections.sort(list);

        System.out.println("정렬 후");
        printStudent(list);

        Collections.shuffle(list);

        System.out.println("셔플 후");
        printStudent(list);

        list.sort(new Descending());
        System.out.println("총점(학번)순 정렬 후");
        printStudent(list);

        calGrade(list);

        System.out.println("등수 지정 후");
        printStudent(list);


    }

    private static void calGrade(List<Student> list) {
        int grade = 1;
        list.get(0).setGrade(grade);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getSum() != list.get(i - 1).getSum())
                grade++;
            list.get(i).setGrade(grade);

//          grade = (list.get(i).getSum() == list.get(i - 1).getSum()) ? grade : grade +1;
//          list.get(i).setGrade(grade);
        }
    }

    static void printStudent(List<Student> list) {
        System.out.println("================================================================");
        for (Student st : list) {
            System.out.println(st);
        }
    }
}

class Descending implements Comparator<Student> {

    @Override
    public int compare(Student st1, Student st2) {
        int result = Integer.compare(st2.getSum(), st1.getSum());
        if (result == 0)
            result = Integer.compare(st2.getSt_no(), st1.getSt_no());

        return result;
    }
}

class Student implements Comparable<Student> {
    private int st_no;
    private String name;
    private int kor;
    private int eng;
    private int math;
    private int sum;
    private int grade;

    public Student(int st_no, String name, int kor, int eng, int math) {
        this.st_no = st_no;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.sum = kor + eng + math;
    }

    @Override
    public String toString() {
        return "Student{" +
                "st_no=" + st_no +
                ", name='" + name + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                ", sum=" + sum +
                ", grade=" + grade +
                '}';
    }

    public int getSt_no() {
        return st_no;
    }

    public void setSt_no(int st_no) {
        this.st_no = st_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Student st) {
        return Integer.compare(this.getSt_no(), st.getSt_no());
    }
}