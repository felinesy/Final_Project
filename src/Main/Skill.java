package Main;

import entity.Player;

abstract class Skill {
    public String name;
    public String description;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void use(Player player) {
        System.out.println("Using skill: " + name);
    }
}
