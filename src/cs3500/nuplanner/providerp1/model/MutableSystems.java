package cs3500.nuplanner.providerp1.model;

/**
 * Defines the contract for a mutable system within the planning application, extending the
 * capabilities of {@code ReadOnlySystems} by allowing modifications.
 * This interface supports operations to upload and save schedules, manage IUsers,
 * and manipulate events within the system.
 */
public interface MutableSystems extends ReadOnlySystems {
  /**
   * The XML file is converted into a schedule by using the XMLParser class.
   * @param filePath the string of where the file is located
   */
  void uploadXMLFileToSystem(String filePath);

  /**
   * The method that saves the schedule to a file.
   * @param filePath the string where the file should be saved
   */
  void saveXMLFileFromSystem(String filePath);

  /**
   * Adds a IUser to the list of IUsers.
   * @param IUser the IUser to add
   */
  void addIUser(IUser IUser);

  /**
   * The IUser that needs to be removed from the list of IUsers.
   * @param IUser the IUser to be removed
   */
  void removeIUser(IUser IUser);

  /**
   * Creates an event that is added to a IUser's schedule.
   * @param uid the string for the IUser id
   * @param event the event that is going to be created
   */
  void createEvent(String uid, Event event);

  /**
   * Changes an event with the given modified event.
   * @param uid the IUser id that the event is being change
   * @param event the event that is going to be changed
   * @param modifiedEvent the new modified event being changed
   */
  void modifyEvent(String uid, Event event, Event modifiedEvent);

  /**
   * The method that deletes an event from every IUser's schedule.
   * @param event the event to be deleted
   */
  void deleteEvent(Event event);
}