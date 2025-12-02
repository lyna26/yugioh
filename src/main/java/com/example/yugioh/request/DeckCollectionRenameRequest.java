package com.example.yugioh.request;

import lombok.Value;

@Value
public class DeckCollectionRenameRequest {
    String name;
    int id;
}
