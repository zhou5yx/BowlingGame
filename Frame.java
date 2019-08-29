
public class Frame
{
  private String throwType1 = "";
  private String throwType2 = "";
  private String bonusThrow = "";
  private boolean lastFrame;

  public Frame(String frame, boolean lastFrame)
  {
    this.lastFrame = lastFrame;
    SetFrameThrows(frame, lastFrame);
  }

  public void SetFrameThrows(String frame, boolean lastFrame)
  {
    if (frame.substring(0, 1).equals("X"))
    {
      throwType1 = "X";
    }
    else if (frame.substring(1, 2).equals("/"))
    {
      throwType1 = frame.substring(0, 1);
      throwType2 = "/";
    }
    else
    {
      throwType1 = frame.substring(0, 1);
      throwType2 = frame.substring(1);
    }
    if (lastFrame)
    {
      if (frame.substring(1, 2).equals("X"))
      {
        throwType2 = "X";
      }
      bonusThrow = frame.substring(2);
    }
  }

  public String getThrowType1()
  {
    return throwType1;
  }

  public String getThrowType2()
  {
    return throwType2;
  }

  public String getBonus()
  {
    return bonusThrow;
  }

  public boolean getLastFrame()
  {
    return this.lastFrame;
  }

}
