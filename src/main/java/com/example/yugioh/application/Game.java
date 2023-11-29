package com.example.yugioh.application;

import com.example.yugioh.models.player.Player;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;


public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //private static final String FILE_TO_SAVE = "System.getProperty("user.home") + "/yugioh/save";";
    private static final String FILE_TO_SAVE = "/game.ser";
    private static final AtomicReference<Game> gameInstance = new AtomicReference<>();
    private Player player;

    private Game() {}

    public static Game getInstance() {
        Game instance = gameInstance.get();

        if (instance == null) {
            synchronized (Game.class) {
                instance = gameInstance.get();
                if (instance == null) {
                    instance = new Game();
                    gameInstance.set(instance);
                }
            }
        }
        return instance;
    }

    public static void load() throws IOException, ClassNotFoundException {
        Path filePath = Paths.get(FILE_TO_SAVE);
        if(Files.exists(filePath)) {
            System.out.println("A saved game exists. We will continue playing with it");
            try (FileInputStream fileInputStream = new FileInputStream(FILE_TO_SAVE);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                gameInstance.set((Game) objectInputStream.readObject());
            }
        }else{
            System.out.println("File doesn't exist. It is a new game");
            Game game = new Game();
            game.setPlayer(new Player("new player"));
            gameInstance.set(game);
            save();
        }
    }

    public static void save() throws IOException {
        try(FileOutputStream fos = new FileOutputStream(FILE_TO_SAVE);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameInstance.get());
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}