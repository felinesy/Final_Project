package data;

import Main.GamePanel;
import entity.Entity;
import object.Obj_BluePotion;
import object.Obj_Key;
import object.Obj_Lantern;
import object.Obj_RedPotion;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName){ // for inventory ???
        Entity obj = null;

        switch(itemName){
            case "Health Potion": obj = new Obj_BluePotion(gp); break;
            case "Mana Potion": obj = new Obj_RedPotion(gp); break;
            case "Key": obj = new Obj_Key(gp); break;
            case "Lantern": obj = new Obj_Lantern(gp); break;
        }
        return obj;
    }

    public void save() {
        try {
            File savesDir = new File("saves");
            if (!savesDir.exists()) savesDir.mkdir();

            File saveFile = new File("saves/save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));

            DataStorage ds = new DataStorage();
            ds.life = gp.player.life;
            ds.mana = gp.player.mana;
            ds.x = gp.player.worldX;
            ds.y = gp.player.worldY;
            ds.map = gp.currentMap;

            for(int i=0; i<gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmount.add(gp.player.inventory.get(i).amount);
            }

            oos.writeObject(ds);
            oos.close();
            gp.ui.addMessage("SAVED!");
            System.out.println("SAVED to " + saveFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Save ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            File saveFile = new File("saves/save.txt");
            if (!saveFile.exists()) {
                System.out.println("No save file found!");
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.life = ds.life;
            gp.player.mana = ds.mana;
            gp.player.worldX = ds.x;
            gp.player.worldY = ds.y;
            gp.currentMap = ds.map;

            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                Entity item = getObject(ds.itemNames.get(i));
                if (item != null) {
                    item.amount = ds.itemAmount.get(i);
                    gp.player.inventory.add(item);
                } else {
                    System.out.println("Warning: Failed to load item \"" + ds.itemNames.get(i) + "\" — object is null.");
                }
            }


            ois.close();
            System.out.println("LOADED from " + saveFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Load ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
