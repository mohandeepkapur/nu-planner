package cs3500.nuplanner.provider.adaptors;

import cs3500.nuplanner.model.hw05.Event;
import cs3500.nuplanner.model.hw05.RawEventData;
import cs3500.nuplanner.provider.controller.Features;
import cs3500.nuplanner.provider.model.IEvent;
import cs3500.nuplanner.provider.strategy.Strategy;

/**
 * Adaptor class for provider's View ->
 */
public class ClientToProviderFeaturesAdaptor implements Features {

  private cs3500.nuplanner.controller.Features clientFeatures;

  /**
   * Creates an adaptor to translate the view
   *
   * @param clientFeatures our features interface
   */
  public ClientToProviderFeaturesAdaptor(cs3500.nuplanner.controller.Features clientFeatures) {

    this.clientFeatures = clientFeatures;

  }

  @Override
  public void onUploadXMLFile(String filePath) {

    clientFeatures.requestXMLScheduleUpload(filePath);

  }

  @Override
  public void onSaveSchedules(String directoryPath) {

    clientFeatures.requestAllSchedulesDownload(directoryPath);

  }

  @Override
  public void onCreateEvent(String uid, IEvent event) {

    Event delOrigEvent = new ProviderToClientEventAdaptor(event);

    RawEventData delCreateEventRawData = extractEventData(delOrigEvent);

    clientFeatures.requestCreateEvent(uid, delCreateEventRawData);

  }

  @Override
  public void onModifyEvent(String uid, IEvent originalEvent, IEvent newEvent) {

    String host = originalEvent.getHostUser().getUid();

    Event delOrigEvent = new ProviderToClientEventAdaptor(originalEvent);

    Event delNewEvent = new ProviderToClientEventAdaptor(newEvent);

    RawEventData rawOriginalEventData = extractEventData(delOrigEvent);

    RawEventData rawNewEventData = extractEventData(delNewEvent);

    clientFeatures.requestModifyEvent(host, rawOriginalEventData, rawNewEventData);

  }

  @Override
  public void onRemoveEvent(IEvent event) {

    String host = event.getHostUser().toString();

    Event delRemoveEvent = new ProviderToClientEventAdaptor(event);

    RawEventData delRemoveEventRawData = extractEventData(delRemoveEvent);

    clientFeatures.requestRemoveEvent(host, delRemoveEventRawData);

  }

  /**
   * Extracts data from an Event and turns it into RawEventData.
   *
   * @param event the event passed in
   * @return the event in RawEventData form
   */
  private RawEventData extractEventData(Event event) {

    return new RawEventData(event.eventInvitees(), event.name(),
            event.location(), String.valueOf(event.isOnline()),
            event.startDay().toString(), String.valueOf(event.startTime()),
            event.endDay().toString(), String.valueOf(event.endTime()));

  }

  @Override
  public Strategy onCreateEventFrame() {
    return null;
  }

  @Override
  public void onSwitchUser(String userId) {

    clientFeatures.displayNewSchedule(userId);

  }

  @Override
  public void handleClick(IEvent e) {

    Event delEvent = new ProviderToClientEventAdaptor(e);

    // given provider view methods, there's no way to display the given Event
    clientFeatures.displayExistingEvent(delEvent.host(), delEvent);

  }

}
