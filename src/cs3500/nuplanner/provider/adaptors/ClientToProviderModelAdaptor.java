package cs3500.nuplanner.provider.adaptors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.nuplanner.model.hw05.Event;
import cs3500.nuplanner.model.hw05.SchedulingSystem;
import cs3500.nuplanner.provider.model.IEvent;
import cs3500.nuplanner.provider.model.ISchedule;
import cs3500.nuplanner.provider.model.IUser;
import cs3500.nuplanner.provider.model.ReadOnlySystems;

/**
 * Adapts client model to provider model their View expects. (read-only)
 */
public class ClientToProviderModelAdaptor implements ReadOnlySystems {

  private SchedulingSystem clientModel;

  /**
   * Creates the adapter between the different models.
   *
   * @param clientModel our version of the scheduling system passed in
   */

  public ClientToProviderModelAdaptor(SchedulingSystem clientModel) {
    this.clientModel = clientModel;
  }

  // converting string users into IUsers( userStr to schedule )
  @Override
  public Map<String, IUser> getAllUsers() {
    List<String> delUsers = this.clientModel.allUsers();
    Map<String, IUser> users = new HashMap<>();
    for (String user : delUsers) {
      users.put(user, new ClientToProviderUserAdaptor(user, clientModel));
    }
    return users;
  }

  @Override
  public IUser getUser(String uid) {
    // adaptor: takes in uid -> extracts schedule from model -> uses schedule
    return new ClientToProviderUserAdaptor(uid, clientModel);
  }

  @Override
  public ISchedule getUserSchedule(String uid) {
    return new ClientToProviderScheduleAdaptor(uid, clientModel);
  }

  @Override
  public boolean isEventConflicting(IEvent event) {

    Event dEvent = new ProviderToClientEventAdaptor(event);

    return this.clientModel.eventConflict(dEvent.host(),
            dEvent.eventInvitees(), dEvent.name(),
            dEvent.location(), dEvent.isOnline(),
            dEvent.startDay(), dEvent.startTime(),
            dEvent.endDay(), dEvent.endTime());

  }
}