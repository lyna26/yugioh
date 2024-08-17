package com.example.yugioh.views;

import com.example.yugioh.controllers.DeckSetInfosController;
import com.example.yugioh.models.deck.DeckSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DeckSetCell extends ListCell<DeckSet> {
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(DeckSet deckSet, boolean empty) {
        super.updateItem(deckSet, empty);

        if (empty || deckSet == null) {
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
        controller.setData(deckSet);
    }
}