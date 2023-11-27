package study.level.middle;

import java.util.ArrayList;
import java.util.List;

enum SkillType {
    ATTACK,
    DEFENSE,
    HEALING
}

class Character<T extends Skill> {
    private String name;
    private int level;
    private List<T> skills;

    public Character(String name, int level) {
        this.name = name;
        this.level = level;
        this.skills = new ArrayList<>();
    }

    public void addSkill(T skill) {
        skills.add(skill);
    }

    public void useSkills() {
        System.out.println(name + "'s skills:");

        for (T skill : skills) {
            skill.use();
        }
    }
}

class Skill<T extends Enum<T>> {
    private String name;
    private int power;
    private T type;

    public Skill(String name, int power, T type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public void use() {
        System.out.println(name + " skill used with power " + power + " and type " + type);
    }
}

public class GameExample {
    public static void main(String[] args) {
        Skill<SkillType> fireball = new Skill<>("Fireball", 50, SkillType.ATTACK);
        Skill<SkillType> shield = new Skill<>("Shield", 30, SkillType.DEFENSE);
        Skill<SkillType> healingWave = new Skill<>("Healing Wave", 20, SkillType.HEALING);

        Character<Skill<SkillType>> mage = new Character<>("Mage", 10);
        mage.addSkill(fireball);
        mage.addSkill(healingWave);

        Character<Skill<SkillType>> warrior = new Character<>("Warrior", 12);
        warrior.addSkill(shield);

        mage.useSkills();
        System.out.println("----");
        warrior.useSkills();
    }
}
