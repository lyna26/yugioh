package com.example.yugioh.application;

import com.example.yugioh.models.player.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;


public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_TO_SAVE = "C:/Users/Lola/IdeaProjects/demo/src/main/resources/com/example/yugioh/game.ser";
    private static Game gameInstance;
    private Player player;

    private Game() {
    }

    public static Game getInstance() {
        if (gameInstance == null) {
            synchronized (Game.class) {
                if (gameInstance == null) {
                    gameInstance = new Game();
                }
            }
        }
        return gameInstance;
    }

    /**
     * This function is used to load the game instance
     */
    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE_TO_SAVE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        gameInstance = (Game) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }

    /**
     * This function is used to save the game instance
     */
    public static void save() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_TO_SAVE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gameInstance);
        oos.close();
        fos.close();
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {

        this.player = player;
    }
}
