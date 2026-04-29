package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    //player stats
    int life;
    int mana;
    int x;
    int y;
    int map;

    //player inventory
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmount = new ArrayList<>();
}
