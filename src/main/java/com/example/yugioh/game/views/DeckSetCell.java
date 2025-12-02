package com.example.yugioh.game.views;

import com.example.yugioh.game.controllers.DeckSetInfosController;
import com.example.yugioh.auth.entity.deck.DeckCollection;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DeckSetCell extends ListCell<DeckCollection> {
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(DeckCollection deckCollection, boolean empty) {
        super.updateItem(deckCollection, empty);

        if (empty || deckCollection == null) {
            setGraphic(null);
            return;
        }

        if (fxmlLoader == null) {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/deck/DeckSetInfos.fxml"));
            fxmlLoader.setControllerFactory(param -> new DeckSetInfosController());
            try {
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        DeckSetInfosController controller = fxmlLoader.getController();
        controller.setData(deckCollection);
    }
}