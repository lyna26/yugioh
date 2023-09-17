package com.example.yugioh.controllers;


import com.example.yugioh.models.card.CardImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;

public class CardResultGridView implements Callback<ListView<CardImpl>, ListCell<CardImpl>> {

    @Override
    public ListCell<CardImpl> call(ListView<CardImpl> listView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(CardImpl card, boolean empty) {
                super.updateItem(card, empty);
                if (card != null && !empty) {
                    GridPane cellContent = new GridPane();
                    cellContent.setHgap(10); // Espace horizontal entre les colonnes

                    // Créer le contrôleur de carte résultante et l'initialiser avec la carte
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/CardResult.fxml"));
                        Parent cardRoot = loader.load();
                        CardResultController controller = loader.getController();
                        controller.setCard(card);
                        // Ajouter la vue de la carte au contenu de la cellule
                        cellContent.add(cardRoot, 0, 0);

                        // Créer une deuxième vue de carte dans la deuxième colonne
                        // Vous pouvez répéter le processus de création et d'ajout pour la deuxième vue

                        // Définir le contenu de la cellule
                        setGraphic(cellContent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    setGraphic(null);
                }
            }
        };
    }
}