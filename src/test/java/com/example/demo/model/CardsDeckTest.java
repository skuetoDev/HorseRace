package com.example.demo.model;

import com.example.demo.model.Cards.Card;
import com.example.demo.model.Cards.CardsDeck;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CardsDeckTest {
    @Test
    void testGetCardFromDeck_NoDuplicates() {
        CardsDeck cardsDeck = new CardsDeck();
        Set<Card> cardSet = new HashSet<>();

        // Obtener todas las cartas del mazo (40 en total)
        for (int i = 0; i < 40; i++) {
            Card card = cardsDeck.getCardFromDeck();
            // Verificar que no se añadan cartas repetidas
            assertTrue(cardSet.add(card), "La carta ya existe en el conjunto: " + card);
        }

        // Verificar que el mazo contiene exactamente 40 cartas únicas
        assertEquals(40, cardSet.size(), "El mazo no contiene 40 cartas únicas.");

    }
}
