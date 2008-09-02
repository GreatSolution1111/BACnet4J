package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.NotImplementedException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.PropertyReference;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class SubscribeCOVPropertyRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 28;
    
    private final UnsignedInteger subscriberProcessIdentifier;
    private final ObjectIdentifier monitoredObjectIdentifier;
    private final com.serotonin.bacnet4j.type.primitive.Boolean issueConfirmedNotifications; // optional
    private final UnsignedInteger lifetime; // optional
    private final PropertyReference monitoredPropertyIdentifier;
    private final Real covIncrement; // optional
    
    public SubscribeCOVPropertyRequest(UnsignedInteger subscriberProcessIdentifier, 
            ObjectIdentifier monitoredObjectIdentifier, Boolean issueConfirmedNotifications, UnsignedInteger lifetime, 
            PropertyReference monitoredPropertyIdentifier, Real covIncrement) {
        this.subscriberProcessIdentifier = subscriberProcessIdentifier;
        this.monitoredObjectIdentifier = monitoredObjectIdentifier;
        this.issueConfirmedNotifications = issueConfirmedNotifications;
        this.lifetime = lifetime;
        this.monitoredPropertyIdentifier = monitoredPropertyIdentifier;
        this.covIncrement = covIncrement;
    }
    
    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }
    
    @Override
    public void write(ByteQueue queue) {
        write(queue, subscriberProcessIdentifier, 0);
        write(queue, monitoredObjectIdentifier, 1);
        writeOptional(queue, issueConfirmedNotifications, 2);
        writeOptional(queue, lifetime, 3);
        write(queue, monitoredPropertyIdentifier, 4);
        writeOptional(queue, covIncrement, 5);
    }
    
    SubscribeCOVPropertyRequest(ByteQueue queue) throws BACnetException {
        subscriberProcessIdentifier = read(queue, UnsignedInteger.class, 0);
        monitoredObjectIdentifier = read(queue, ObjectIdentifier.class, 1);
        issueConfirmedNotifications = readOptional(queue, Boolean.class, 2);
        lifetime = readOptional(queue, UnsignedInteger.class, 3);
        monitoredPropertyIdentifier = read(queue, PropertyReference.class, 4);
        covIncrement = read(queue, Real.class, 5);
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from, Network network)
            throws BACnetException {
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((covIncrement == null) ? 0 : covIncrement.hashCode());
        result = PRIME * result + ((issueConfirmedNotifications == null) ? 0 : issueConfirmedNotifications.hashCode());
        result = PRIME * result + ((lifetime == null) ? 0 : lifetime.hashCode());
        result = PRIME * result + ((monitoredObjectIdentifier == null) ? 0 : monitoredObjectIdentifier.hashCode());
        result = PRIME * result + ((monitoredPropertyIdentifier == null) ? 0 : monitoredPropertyIdentifier.hashCode());
        result = PRIME * result + ((subscriberProcessIdentifier == null) ? 0 : subscriberProcessIdentifier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SubscribeCOVPropertyRequest other = (SubscribeCOVPropertyRequest) obj;
        if (covIncrement == null) {
            if (other.covIncrement != null)
                return false;
        }
        else if (!covIncrement.equals(other.covIncrement))
            return false;
        if (issueConfirmedNotifications == null) {
            if (other.issueConfirmedNotifications != null)
                return false;
        }
        else if (!issueConfirmedNotifications.equals(other.issueConfirmedNotifications))
            return false;
        if (lifetime == null) {
            if (other.lifetime != null)
                return false;
        }
        else if (!lifetime.equals(other.lifetime))
            return false;
        if (monitoredObjectIdentifier == null) {
            if (other.monitoredObjectIdentifier != null)
                return false;
        }
        else if (!monitoredObjectIdentifier.equals(other.monitoredObjectIdentifier))
            return false;
        if (monitoredPropertyIdentifier == null) {
            if (other.monitoredPropertyIdentifier != null)
                return false;
        }
        else if (!monitoredPropertyIdentifier.equals(other.monitoredPropertyIdentifier))
            return false;
        if (subscriberProcessIdentifier == null) {
            if (other.subscriberProcessIdentifier != null)
                return false;
        }
        else if (!subscriberProcessIdentifier.equals(other.subscriberProcessIdentifier))
            return false;
        return true;
    }
}
