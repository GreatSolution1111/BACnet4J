package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetErrorException;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.enumerated.ErrorClass;
import com.serotonin.bacnet4j.type.enumerated.ErrorCode;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class WritePropertyRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 15;
    
    private final ObjectIdentifier objectIdentifier;
    private final PropertyIdentifier propertyIdentifier;
    private final UnsignedInteger propertyArrayIndex;
    private final Encodable propertyValue;
    private final UnsignedInteger priority;
    
    public WritePropertyRequest(ObjectIdentifier objectIdentifier, PropertyIdentifier propertyIdentifier, 
            UnsignedInteger propertyArrayIndex, Encodable propertyValue, UnsignedInteger priority) {
        this.objectIdentifier = objectIdentifier;
        this.propertyIdentifier = propertyIdentifier;
        this.propertyArrayIndex = propertyArrayIndex;
        this.propertyValue = propertyValue;
        this.priority = priority;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }
    
    @Override
    public void write(ByteQueue queue) {
        write(queue, objectIdentifier, 0);
        write(queue, propertyIdentifier, 1);
        writeOptional(queue, propertyArrayIndex, 2);
        writeEncodable(queue, propertyValue, 3);
        writeOptional(queue, priority, 4);
    }
    
    WritePropertyRequest(ByteQueue queue) throws BACnetException {
        objectIdentifier = read(queue, ObjectIdentifier.class, 0);
        propertyIdentifier = read(queue, PropertyIdentifier.class, 1);
        propertyArrayIndex = readOptional(queue, UnsignedInteger.class, 2);
        propertyValue = readEncodable(queue, objectIdentifier.getObjectType(), propertyIdentifier, 
                propertyArrayIndex, 3);
        priority = readOptional(queue, UnsignedInteger.class, 4);
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from, Network network)
            throws BACnetErrorException {
        BACnetObject obj = localDevice.getObject(objectIdentifier);
        if (obj == null)
            throw new BACnetErrorException(getChoiceId(), ErrorClass.object, ErrorCode.unknownObject);
        
        PropertyValue pv = new PropertyValue(propertyIdentifier, propertyArrayIndex, propertyValue, priority);
        try {
            if (localDevice.getEventHandler().checkAllowPropertyWrite(obj, pv)) {
                obj.setProperty(pv);
                localDevice.getEventHandler().propertyWritten(obj, pv);
            }
            else
                throw new BACnetServiceException(ErrorClass.property, ErrorCode.writeAccessDenied);
        }
        catch (BACnetServiceException e) {
            throw new BACnetErrorException(getChoiceId(), e);
        }
        
        return null;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0 : objectIdentifier.hashCode());
        result = PRIME * result + ((priority == null) ? 0 : priority.hashCode());
        result = PRIME * result + ((propertyArrayIndex == null) ? 0 : propertyArrayIndex.hashCode());
        result = PRIME * result + ((propertyIdentifier == null) ? 0 : propertyIdentifier.hashCode());
        result = PRIME * result + ((propertyValue == null) ? 0 : propertyValue.hashCode());
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
        final WritePropertyRequest other = (WritePropertyRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null)
                return false;
        }
        else if (!objectIdentifier.equals(other.objectIdentifier))
            return false;
        if (priority == null) {
            if (other.priority != null)
                return false;
        }
        else if (!priority.equals(other.priority))
            return false;
        if (propertyArrayIndex == null) {
            if (other.propertyArrayIndex != null)
                return false;
        }
        else if (!propertyArrayIndex.equals(other.propertyArrayIndex))
            return false;
        if (propertyIdentifier == null) {
            if (other.propertyIdentifier != null)
                return false;
        }
        else if (!propertyIdentifier.equals(other.propertyIdentifier))
            return false;
        if (propertyValue == null) {
            if (other.propertyValue != null)
                return false;
        }
        else if (!propertyValue.equals(other.propertyValue))
            return false;
        return true;
    }
}
