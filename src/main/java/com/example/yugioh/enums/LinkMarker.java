package com.example.yugioh.enums;

public enum LinkMarker {
    BOTTOM("\u2193"),
    BOTTOM_LEFT("\u2199"),
    BOTTOM_RIGHT("\u2198"),
    TOP("\u2191"),
    TOP_LEFT("\u2196"),
    TOP_RIGHT("\u2197"),
    LEFT("\u2190"),
    RIGHT("\u2192");

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
        return marker;
    }
}
