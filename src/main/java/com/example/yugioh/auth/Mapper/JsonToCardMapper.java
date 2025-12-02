package com.example.yugioh.auth.Mapper;

import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.enums.Limit;
import com.example.yugioh.auth.enums.LinkMarker;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonToCardMapper {
    private JsonToCardMapper() {
    }

    public static List<Card> fromJsonNode(JsonNode cardNode) {
        JsonNode cardImages = cardNode.path("card_images");

        List<Card> results = new ArrayList<>();

        cardImages.forEach(image -> {
            try {
                int id = image.path("id").asInt();
                String name = cardNode.path("name").asText();
                String type = cardNode.path("type").asText();
                String desc = cardNode.path("desc").asText();
                int atk = cardNode.path("atk").asInt();
                int def = cardNode.path("def").asInt();
                int level = cardNode.path("level").asInt();
                String race = cardNode.path("race").asText(null);
                String attribute = cardNode.path("attribute").asText(null);
                int linkval = cardNode.path("linkval").asInt();
                String s = linkMarkersAsString(cardNode.path("linkmarkers"));
                int scale = cardNode.path("scale").asInt();
                String frameType = cardNode.path("frameType").asText(null);
                String banlistInfo = getBanList(cardNode.path("banlist_info"));
                String big_img = image.path("image_url").asText();
                String small_img = image.path("image_url_small").asText();
                boolean hasEffect = cardNode.path("misc_info").path(0).path("has_effect").asBoolean();

                Card e = new Card(id, name, desc, big_img, small_img, race, banlistInfo, frameType, level, type, hasEffect, linkval, s, atk, def, attribute, scale);
                results.add(e);
            } catch (Exception e) {
                log.error("Erreur lors de la transformation de la carte JSON : {}", cardNode, e);
                throw e;
            }
        });

        return results;
    }

    private static String linkMarkersAsString(JsonNode markers) {
        if (markers.isEmpty()) {
            return "";
        }
        StringBuilder linkMarkersBuilder = new StringBuilder();

        for (JsonNode marker : markers) {
            String arrow = LinkMarker.getArrowForMarker(marker.asText());
            linkMarkersBuilder.append(arrow).append(", ");
        }
        String linkMarkers = linkMarkersBuilder.toString();
        if (!linkMarkers.isEmpty()) {
            linkMarkers = linkMarkers.substring(0, linkMarkers.length() - 2);
        }
        return linkMarkers;
    }

    private static String getBanList(JsonNode banList) {
        if (banList.isEmpty()) {
            return Limit.NO_LIMITED.getLimitName();
        }
        JsonNode banTcgNode = banList.path("ban_tcg");

        if (banTcgNode.isEmpty()) {
            return Limit.NO_LIMITED.getLimitName();
        }

        return banTcgNode.asText().replace('-', '_').toUpperCase();
    }

}
