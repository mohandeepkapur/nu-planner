package cs3500.nuplanner.provider.adaptors;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import cs3500.nuplanner.model.hw05.DaysOfTheWeek;
import cs3500.nuplanner.model.hw05.ReadableEvent;
import cs3500.nuplanner.model.hw05.SchedulingSystem;
import cs3500.nuplanner.provider.model.Day;
import cs3500.nuplanner.provider.model.Event;
import cs3500.nuplanner.provider.model.IEvent;
import cs3500.nuplanner.provider.model.IUser;

/**
 * Adaptor class that converts an Event (client) into an IEvent (provider).
 */
public class ClientToProviderEventAdaptor implements IEvent {

  private final IEvent ievent;
  private final SchedulingSystem model;

  /**
   * Constructs an Event-to-IEvent adaptor.
   *
   * @param event the ReadableEvent to convert
   * @param model client-version of Model
   */
  public ClientToProviderEventAdaptor(ReadableEvent event, SchedulingSystem model) {
    this.ievent = convertEventIntoIEvent(event);
    this.model = model;

  }

  /**
   * Converts Event to IEvent. IEvent formatting of data is followed (host separated from IEvent
   * invitees list, though for Event, host is included as first in invitees list).
   *
   * @param event   the Event to convert
   * @return        an IEvent
   */
  private IEvent convertEventIntoIEvent(ReadableEvent event) {
    // extract IEvent time params from Event
    LocalTime startTime = convertEventMilitaryTimeToLocalTime(event.startTime());
    LocalTime endTime = convertEventMilitaryTimeToLocalTime(event.endTime());

    // extract IEvent day params from Event
    Day startDay = convertDaysOfTheWeekToDay(event.startDay());
    Day endDay = convertDaysOfTheWeekToDay(event.startDay());

    // extract IEvent invitee list and host (host is not considered invitee)
    List<IUser> iEventInvitees = new ArrayList<>();

    for (String user : event.eventInvitees()) {
      // need to remove host of Event from IEvent invitee list
      // convert every String in Event to User in IEvent
      iEventInvitees.add(new ClientToProviderUserAdaptor(user, model));
    }

    // save and remove host from IEvent invitee list
    IUser iEventHost = iEventInvitees.get(0);
    iEventInvitees.remove(0);

    // return an IEvent for the adaptor class to use
    return new Event(event.name(), startDay, startTime,
            endDay, endTime, event.isOnline(),
            event.location(), iEventHost, iEventInvitees);

  }

  /**
   * Converts client DaysOfTheWeek enum into the provider Day enum.
   *
   * @param day   a DaysOfTheWeek enum
   * @return      a Day enum
   */
  private Day convertDaysOfTheWeekToDay(DaysOfTheWeek day) {
    String dayRep = day.toString().toLowerCase();
    return Day.toDay(Character.toUpperCase(dayRep.charAt(0)) + dayRep.substring(1));
  }

  /**
   * Converts Event military time to IEvent time. Adaptor method assumes all Events work with
   * military time (undesirable).
   *
   * @param militaryTime  the military time from our version
   * @return              local time that the provider can use
   */
  private LocalTime convertEventMilitaryTimeToLocalTime(int militaryTime) {
    int hour = militaryTime / 100;
    int minute = militaryTime % 100;
    return LocalTime.of(hour, minute);
  }

  @Override
  public String getName() {
    return this.ievent.getName();
  }

  @Override
  public String getPlace() {
    return this.ievent.getPlace();
  }

  @Override
  public boolean isOnline() {
    return this.ievent.isOnline();
  }

  @Override
  public LocalTime getStartTime() {
    return this.ievent.getStartTime();
  }

  @Override
  public LocalTime getEndTime() {
    return this.ievent.getEndTime();
  }

  @Override
  public Day getStartDay() {
    return this.ievent.getStartDay();
  }

  @Override
  public Day getEndDay() {
    return this.ievent.getEndDay();
  }

  @Override
  public IUser getHostUser() {
    return this.ievent.getHostUser();
  }

  @Override
  public List<IUser> getInvitedUsers() {
    return this.ievent.getInvitedUsers();
  }

  @Override
  public String invites() {
    return this.ievent.invites();
  }

}
