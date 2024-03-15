package cs3500.nuplanner.model.hw05;

import java.util.List;

/**
 * All the operations and observations that can be performed on a Scheduling System.
 * <p>
 * Permissions are very trusting, as mentioned by assignment. every invitee is client and can remove
 * and add anyone. If non-host invitee of an Event is not in system, will be added automatically.
 * </p>
 */
public interface SchedulingSystem {

  /**
   * Adds a user to the scheduling system.
   *
   * @param user                             name of new user
   * @throws IllegalArgumentException        if user already exists in scheduling-system
   */
  void addUser(String user);

  /**
   * Removes a user from the scheduling system.
   *
   * @param user                                name of user
   * @throws IllegalArgumentException           if user does not exist in the scheduling-system
   */
  void removeUser(String user);

  /**
   * Observes all users existing within the scheduling system.
   *
   * @return all users in scheduling system
   */
  List<String> allUsers();

  /**
   * Creates and adds a new Event to the relevant Schedules.
   *
   * @param host                        host of Event
   * @param invitees                    users added to Event (includes host)
   * @param eventName                   name of Event
   * @param location                    location of Event
   * @param isOnline                    online/offline status of Event
   * @param startDay                    start day of event
   * @param startTime                   start time of Event
   * @param endDay                      end day of Event
   * @param endTime                     end time of Event
   *
   * @throws IllegalArgumentException   if provided host does not exist in scheduling system
   * @throws IllegalArgumentException   if Event cannot be constructed due to invalid information
   * @throws IllegalArgumentException   if the Event's host is not first in its invitees list
   * @throws IllegalArgumentException   if Event conflicts with any Schedule within the Scheduling
   *                                    System
   */
  void addEvent(String host, List<String> invitees,
                String eventName, String location, boolean isOnline,
                DaysOfTheWeek startDay, int startTime,
                DaysOfTheWeek endDay, int endTime);

  /**
   * Removes an Event from specified user's Schedule. Event state is updated accordingly.
   * Assumption that no Event in a Schedule shares the same start day and time.
   *
   *
   * @param user                        name of user whose schedule holds the Event
   * @param startDay                    start day of Event
   * @param startTime                   start time of Event
   * @throws IllegalArgumentException   if Event with above properties does not exist in Schedule
   */
  void removeEvent(String user, DaysOfTheWeek startDay, int startTime);

  /**
   * Modifies an Event within Scheduling System.
   *
   * @param user                        name of user whose schedule holds the Event
   * @param startDay                    start day of Event
   * @param startTime                   start time of Event
   * @param modification                modification to be made
   *
   * @throws IllegalArgumentException   if modification creates conflict with other Schedules
   */
  void modifyEvent(String user, DaysOfTheWeek startDay, int startTime, String modification);

  /**
   * Checks whether an Event conflicts with relevant Schedules in Scheduling System.
   *
   * @param host                        host of Event
   * @param invitees                    users added to Event (includes host)
   * @param eventName                   name of Event
   * @param location                    location of Event
   * @param isOnline                    online/offline status of Event
   * @param startDay                    start day of event
   * @param startTime                   start time of Event
   * @param endDay                      end day of Event
   * @param endTime                     end time of Event
   *
   * @return                            whether event can exist within scheduling system or not
   *
   * @throws IllegalArgumentException   if Event cannot be constructed due to invalid information
   */
  boolean eventConflict(String host, List<String> invitees,
                        String eventName, String location, boolean isOnline,
                        DaysOfTheWeek startDay, int startTime,
                        DaysOfTheWeek endDay, int endTime);

  /**
   * Observes all the Events contained within a user's Schedule.
   *
   * @param user                        name of user whose Schedule to return
   * @return                            Schedule belonging to that user
   * @throws IllegalArgumentException   if user does not exist in scheduling system
   */
  List<ReadableEvent> eventsInSchedule(String user);

  /**
   * Observes a unique Event contained within a user's schedule.
   *
   * @param user                        name of user whose Event to return
   * @return                            Event belonging to that user
   * @throws IllegalArgumentException   if user does not exist in scheduling system
   */
  ReadableEvent eventAt(String user, DaysOfTheWeek startDay, int startTime);




}
