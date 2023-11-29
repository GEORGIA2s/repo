package study.level.middle;

import java.util.ArrayList;
import java.util.List;

enum SkillType {
    공격,
    방어,
    힐
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
        System.out.println(name + "의 스킬:");

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
        System.out.println(name + " 스킬이 " + power + " 데미지를 입혔습니다. [Skill Type : " + type + "]");
    }
}

public class GameExample {
    public static void main(String[] args) {
        Skill<SkillType> fireball = new Skill<>("파이어볼", 50, SkillType.공격);
        Skill<SkillType> shield = new Skill<>("쉴드", 30, SkillType.방어);
        Skill<SkillType> healingWave = new Skill<>("힐링 웨이브", 20, SkillType.힐);

        Character<Skill<SkillType>> mage = new Character<>("마법사", 10);
        mage.addSkill(fireball);
        mage.addSkill(healingWave);

        Character<Skill<SkillType>> warrior = new Character<>("전사", 12);
        warrior.addSkill(shield);

        mage.useSkills();
        System.out.println("----");
        warrior.useSkills();
    }
}
