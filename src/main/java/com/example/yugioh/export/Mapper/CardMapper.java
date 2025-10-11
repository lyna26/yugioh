package com.example.yugioh.export.Mapper;

import com.example.yugioh.enums.Limit;
import com.example.yugioh.enums.LinkMarker;
import com.example.yugioh.export.model.CardDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardMapper {
    private CardMapper() {
        // Utility class
    }

    public static CardDTO fromJsonNode(JsonNode cardNode) {
        if (cardNode == null || cardNode.isEmpty()) {
            log.warn("Carte vide reçue pour transformation.");
            return null;
        }

        try {
            int id = cardNode.path("id").asInt();
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
            int scale =cardNode.path("scale").asInt();
            String type1 = cardNode.path("frameType").asText(null);
            String banlistInfo = getBanList(cardNode.path("banlist_info"));

            CardDTO dto = new CardDTO(id, name, desc,  race, banlistInfo, type1, level, type, linkval, s, atk, def, attribute, scale);
            return dto;
        } catch (Exception e) {
            log.error("Erreur lors de la transformation de la carte JSON : {}", cardNode, e);
            return null;
        }
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

    // 🔧 Renvoie un int nullable (car certaines cartes n'ont pas atk/def)
    private static Integer getNullableInt(JsonNode node, String field) {
        JsonNode value = node.path(field);
        return value.isMissingNode() || !value.isNumber() ? null : value.asInt();
    }
}
