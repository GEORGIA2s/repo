import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalRace {
    static final int HORSES = 10;
    static final int DISTANCE = 50;

    public static void main(String[] args) throws InterruptedException {
        List<Animal> list = new ArrayList<>();
        String[] icons = {"🐴", "🦓", "🦄", "🐵", "🐶", "🐺", "🐱", "🦁", "🐯", "🐮"};
        for (int i = 0; i < HORSES; i++) {
            list.add(new Animal((i + 1) + "번 동물", icons[i]));
        }

        Race race = new Race(list);

        Thread th = new Thread(race);
        th.start();

        th.join();

        race.displayResults();
    }
}

class Race extends Print implements Runnable {
    private List<Animal> list;
    private boolean raceFinished = false;
    public Race(List<Animal> list) {
        this.list = list;
    }

    @Override
    public void run() {

        for (int i = 0; i < AnimalRace.DISTANCE; i++) {
            System.out.println(FONT_BLUE+"===========================경기 진행중============================"+RESET);
            for (Animal h : list) {
                if (raceFinished) {
                    break;
                }
                if (h.getPosition() >= AnimalRace.DISTANCE) {
                    continue;
                }

                int progress = ThreadLocalRandom.current().nextInt(3);
                h.move(progress);
                printStatus(h);

                if (h.getPosition() >= AnimalRace.DISTANCE) {
                    raceFinished = true;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private void printStatus(Animal h) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h.getPosition(); i++) {
            sb.append("-");
        }
        sb.append(h.getIcon());
        for (int i = h.getPosition() + 1; i < AnimalRace.DISTANCE; i++) {
            sb.append("-");
        }
        System.out.println(h.getName() + " : " + sb);
    }

    public void displayResults() {
        Collections.sort(list);

        System.out.println("================================================");
        System.out.println(FONT_GREEN+"                   경기 종료!"+RESET);
        System.out.println("================================================");
        for (int i = 0; i < list.size(); i++) {
            Animal h = list.get(i);
            if(i<=2)
            System.out.println(FONT_RED+UNDER_LINE+(i + 1) + "등 : " + h.getName() + " (" + h.getPosition() + "M)"+RESET);
            else
            System.out.println((i + 1) + "등 : " + h.getName() + " (" + h.getPosition() + "M)");
        }

    }
}

class Animal implements Comparable<Animal> {
    private String name;
    private int position;
    private String icon;

    public Animal(String name, String icon) {
        this.name = name;
        this.position = 0;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void move(int progress) {
        position += progress;
        if (position > AnimalRace.DISTANCE)
            position = AnimalRace.DISTANCE;
    }

    @Override
    public int compareTo(Animal h) {
        return Integer.compare(h.getPosition(), this.getPosition());
    }
}
