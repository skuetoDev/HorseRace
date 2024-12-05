package com.example.demo;
import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.GameLogic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameLogicTest {

   @Test
   void testRoundMaxExceptionIsThrown() {
      GameLogic gameLogic = new GameLogic();
      // Simula una ronda múltiplo de 41 con counterExcepcion en 0
      assertThrows(RoundMaxException.class, () -> gameLogic.checkRound(41, 0));
   }

   @Test
   void testNoExceptionThrownForOtherRounds() throws RoundMaxException {
      GameLogic gameLogic = new GameLogic();
      // Verifica que no se lanza excepción para una ronda que no es múltiplo de 41
      gameLogic.checkRound(40, 0);
      gameLogic.checkRound(42, 0);
   }
}
