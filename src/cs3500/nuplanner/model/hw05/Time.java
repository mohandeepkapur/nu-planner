package cs3500.nuplanner.model.hw05;

// advantages of having a time interface/impl
// in theory, if I swap out this time-impl and enforce a new kind of time, codebase should still run

/**
 * A class to represent time in a specific military time format.
 */
public class Time {

  private int militaryTime;

  /**
   * Creates the time with a specific military format.
   *
   * @param time the time passed in
   */

  public Time(int time) {

    // perform checks to make sure time is valid
    ensureProperMilitaryTime(time);
    this.militaryTime = time;
  }

  /**
   * Creates a time with proper military format.
   *
   * @param time the time passed in
   */
  public void setTime(int time) {
    // perform checks to make sure time is valid
    ensureProperMilitaryTime(time);
    this.militaryTime = time;
  }

  private void ensureProperMilitaryTime(int time) {
    if (time / 100 < 0 || time / 100 > 23 || time % 100 > 59) {
      throw new IllegalArgumentException("Time cannot be init. with invalid military time...  ");
    }
  }

  public int getTime() {
    return this.militaryTime;
  }

  // very useful
  public int convertTimeToObjValue() {
    return 0;
  }


}
