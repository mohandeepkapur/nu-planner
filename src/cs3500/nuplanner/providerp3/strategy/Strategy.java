package cs3500.nuplanner.providerp3.strategy;

import java.util.List;

import cs3500.nuplanner.providerp3.model.Event;
import cs3500.nuplanner.providerp3.model.IUser;

/**
 * Creates the interface for the strategy classes that implement it.
 */
public interface Strategy {
  /**
   * The method for creating the events with a duration.
   * @param eventName the name of the event
   * @param isOnline if it is online or not
   * @param location the location of the event
   * @param duration the duration of the event
   * @param selectedUser the host user
   * @param users the list of users
   * @return created event
   */
  Event createEventWithDuration(String eventName, boolean isOnline,
                                String location, String duration,
                                String selectedUser, List<IUser> users);
}
