package com.example.yugioh.enums;

public enum LinkMarker {
    BOTTOM("\u2193"), // Flèche vers le bas Unicode
    BOTTOM_LEFT("\u2199"), // Flèche vers le bas à gauche Unicode
    BOTTOM_RIGHT("\u2198"), // Flèche vers le bas à droite Unicode
    TOP("\u2191"),   // Flèche vers le haut Unicode
    TOP_LEFT("\u2196"), // Flèche vers le haut à gauche Unicode
    TOP_RIGHT("\u2197"), // Flèche vers le haut à droite Unicode
    LEFT("\u2190"), // Flèche vers la gauche Unicode
    RIGHT("\u2192"); // Flèche vers la droite Unicode

    private final String arrow;

    LinkMarker(String arrow) {
        this.arrow = arrow;
    }

    public String getArrow() {
        return arrow;
    }

    public static String getArrowForMarker(String marker) {
        for (LinkMarker linkMarker : LinkMarker.values()) {
            if (linkMarker.name().equalsIgnoreCase(marker.replace("-", "_"))) {
                return linkMarker.getArrow();
            }
        }
        return marker; // Retourne le mot original s'il n'est pas trouvé dans l'enum
    }
}
