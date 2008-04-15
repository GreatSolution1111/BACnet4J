package com.serotonin.bacnet4j.event;

import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.RemoteObject;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.Choice;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.constructed.TimeStamp;
import com.serotonin.bacnet4j.type.enumerated.EventState;
import com.serotonin.bacnet4j.type.enumerated.EventType;
import com.serotonin.bacnet4j.type.enumerated.MessagePriority;
import com.serotonin.bacnet4j.type.enumerated.NotifyType;
import com.serotonin.bacnet4j.type.notificationParameters.NotificationParameters;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

/**
 * A default class for easy implementation of the DeviceEventListener interface. Instead of having to implement
 * all of the defined methods, listener classes can override this and only implement the desired methods.
 * 
 * @author mlohbihler
 */
public class DefaultDeviceEventListener implements DeviceEventListener {
    public void listenerException(Throwable e) {
        // no op
    }
    
    public boolean allowPropertyWrite(BACnetObject obj, PropertyValue pv) {
        return true;
    }

    public void iAmReceived(RemoteDevice d) {
        // no op
    }

    public void propertyWritten(BACnetObject obj, PropertyValue pv) {
        // no op
    }

    public void iHaveReceived(RemoteDevice d, RemoteObject o) {
        // no op
    }

    public void covNotificationReceived(UnsignedInteger subscriberProcessIdentifier, 
            RemoteDevice initiatingDevice, ObjectIdentifier monitoredObjectIdentifier, 
            UnsignedInteger timeRemaining, SequenceOf<PropertyValue> listOfValues) {
        // no op
    }

    public void eventNotificationReceived(UnsignedInteger processIdentifier, 
            RemoteDevice initiatingDevice, ObjectIdentifier eventObjectIdentifier, TimeStamp timeStamp, 
            UnsignedInteger notificationClass, UnsignedInteger priority, EventType eventType, 
            CharacterString messageText, NotifyType notifyType, Boolean ackRequired, EventState fromState, 
            EventState toState, NotificationParameters eventValues) {
        // no op
    }

    public void textMessageReceived(RemoteDevice textMessageSourceDevice, Choice messageClass,
            MessagePriority messagePriority, CharacterString message) {
        // no op
    }

    public void privateTransferReceived(UnsignedInteger vendorId, UnsignedInteger serviceNumber,
            Encodable serviceParameters) {
        // no op
    }
}
