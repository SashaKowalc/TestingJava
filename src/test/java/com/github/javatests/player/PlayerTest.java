package com.github.javatests.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

  @Test
  public void looses_when_dice_number_is_too_low() {
    //Dice dice = new Dice(6); Esto no es adecuado, ya que el dado sale aleatorio y aveces el test va a dar bien y
    //aveces va a dar mal
    Dice dice = Mockito.mock(Dice.class);
    Mockito.when(dice.roll()).thenReturn(2); //Esta linea esta simulando el dado y va a devolver un 2.
    Player player = new Player(dice, 3);
    assertEquals(false, player.play());
  }

  @Test
  public void wins_when_dice_number_is_big() {
    Dice dice = Mockito.mock(Dice.class);
    Mockito.when(dice.roll()).thenReturn(4); //Esta linea esta simulando el dado y va a devolver un 2.
    Player player = new Player(dice, 3);
    assertEquals(true, player.play());
  }

}