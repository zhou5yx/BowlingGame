import java.util.LinkedList;

/**
 * A Bowling game score calculator. this game does not check for valid throws, correct number of
 * frames or provide any intermediate scores
 * 
 * @author Rain Zhou
 *
 */
public class BowlingGame
{
  private LinkedList<Integer> frameScores;
  private int totalScore = 0;
  private String[] parseFrames;
  private LinkedList<Frame> f;

  /**
   * Default Constructor that takes in a string of frames with throws and parses each frame and
   * calculate the scores
   * 
   * @param frames
   *          - string of frames with throw types
   */
  public BowlingGame(String frames)
  {
    f = new LinkedList<Frame>();
    frameScores = new LinkedList<Integer>();
    parseFrames = frames.split("-");

    for (int i = 0; i < parseFrames.length; i++)
    {
      if (i < 9)
      {
        f.add(new Frame(parseFrames[i], false));
      }
      if (i == 9)
      {
        if (parseFrames[i].contains("/"))
        {
          parseFrames[i] = parseFrames[i].concat(parseFrames[parseFrames.length - 1]);
        }
        f.add(new Frame(parseFrames[i], true));
      }
    }

    // calculate the scores of individual frames and get the total score
    for (int i = 0; i < f.size(); i++)
    {
      frameScores.add(calculateFrameScore(f.get(i), i));
    }
    setTotalScore();
  }

  /**
   * Calculate the score of a frame
   * 
   * @param fr
   *          frame to be calculated
   * @param index
   *          the position of the frame in the list
   * @return score of the frame
   */
  public int calculateFrameScore(Frame fr, int index)
  {
    if (!fr.getLastFrame())
    {
      if (fr.getThrowType1().equals("X"))
      {
        return 10 + getNextTwoThrow(index + 1);
      }
      if (fr.getThrowType2().equals("/"))
      {
        return 10 + getNextThrowScore(index + 1);
      }
      return Integer.parseInt(fr.getThrowType1()) + Integer.parseInt(fr.getThrowType2());
    }
    return calculateLastFrame(index);
  }

  /**
   * Get the next throw score of the frame typically after a spare.
   * 
   * @param index
   *          the position of the frame
   * @return the next throw score
   */
  public int getNextThrowScore(int index)
  {
    if (f.get(index).getThrowType1().equals("X"))
    {
      return 10;
    }
    return Integer.parseInt(f.get(index).getThrowType1());
  }

  /**
   * Get the next two throw scores of the frame after a strike
   * 
   * @param index
   *          the position of the frame
   * @return the score of the next two throw
   */
  public int getNextTwoThrow(int index)
  {
    if (!f.get(index).getLastFrame())
    {
      if (f.get(index).getThrowType1().equals("X"))
      {
        return 10 + getNextThrowScore(index + 1);
      }
      if (f.get(index).getThrowType2().equals("/"))
      {
        return 10;
      }
      return Integer.parseInt(f.get(index).getThrowType1())
          + Integer.parseInt(f.get(index).getThrowType2());
    }
    // calculate the last frame score to be added with the 9th frame without the bonus
    return calculateLastFrameWithoutBonus(index);
  }

  /**
   * Get the last frame score without any bonus throws
   * 
   * @param index
   *          the position of the frame
   * @return the score of the last frame without bonus throws
   */
  public int calculateLastFrameWithoutBonus(int index)
  {
    int score = 0;
    if (f.get(index).getThrowType1().equals("X"))
    {
      score = 10;
      if (f.get(index).getThrowType2().contains("X") || f.get(index).getThrowType2().equals("/"))
      {
        score += 10;
      }
      else
      {
        score += Integer.parseInt(f.get(index).getThrowType1())
            + Integer.parseInt(f.get(index).getThrowType2());
      }
    }
    return score;
  }

  /**
   * Get the score of the last frame including the bonus
   * 
   * @param index
   *          the position of the frame
   * @return the score of the last frame with bonus scores
   */
  public int calculateLastFrame(int index)
  {
    int score = 0;
    if (f.get(index).getThrowType1().equals("X"))
    {
      score += 10;
    }
    if (f.get(index).getThrowType2().contains("X") || f.get(index).getThrowType2().equals("/"))
    {
      score += 10;
    }
    if (f.get(index).getBonus().isEmpty())
    {
      score += Integer.parseInt(f.get(index).getThrowType1())
          + Integer.parseInt(f.get(index).getThrowType2());
    }
    else if (f.get(index).getBonus().equals("X") || f.get(index).getBonus().equals("/"))
    {
      score += 10;
    }
    else
    {
      score += Integer.parseInt(f.get(index).getBonus());
    }

    return score;

  }

  /**
   * calculate the totalScore of all the frames
   */
  public void setTotalScore()
  {
    this.totalScore = frameScores.stream().reduce(0, Integer::sum);
  }

  /**
   * getter for game score
   * 
   * @return the score of the game
   */
  public int getScore()
  {
    return totalScore;
  }
}
