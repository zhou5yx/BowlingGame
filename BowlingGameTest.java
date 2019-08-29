import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class BowlingGameTest
{

  @Test
  public void TestAllStrike()
  {
    BowlingGame allStrike = new BowlingGame("X-X-X-X-X-X-X-X-X-XXX");
    assertEquals(300, allStrike.getScore());
  }

  @Test
  public void TestAllSpare()
  {
    BowlingGame allSpare = new BowlingGame("5/-5/-5/-5/-5/-5/-5/-5/-5/-5/-5");
    assertEquals(150, allSpare.getScore());
  }

  @Test
  public void TestNormal()
  {
    BowlingGame normalThrows = new BowlingGame("45-54-36-27-09-63-81-18-90-72");
    assertEquals(90, normalThrows.getScore());
  }

  public void TestMixedThrows()
  {
    BowlingGame mixedThrows = new BowlingGame("8/-54-90-X-X-5/-53-63-9/-9/X");
    assertEquals(149, mixedThrows.getScore());

    BowlingGame mixedThrows2 = new BowlingGame("5/-54-81-X-5/-27-X-09-5/-5/-5/");
    assertEquals(130, mixedThrows2.getScore());
  }

}
