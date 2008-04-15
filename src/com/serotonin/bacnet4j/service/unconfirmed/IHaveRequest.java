package com.serotonin.bacnet4j.service.unconfirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.RemoteObject;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.util.queue.ByteQueue;

public class IHaveRequest extends UnconfirmedRequestService {
    public static final byte TYPE_ID = 1;
    
    private ObjectIdentifier deviceIdentifier;
    private ObjectIdentifier objectIdentifier;
    private CharacterString objectName;
    
    public IHaveRequest(ObjectIdentifier deviceIdentifier, ObjectIdentifier objectIdentifier, CharacterString objectName) {
        super();
        this.deviceIdentifier = deviceIdentifier;
        this.objectIdentifier = objectIdentifier;
        this.objectName = objectName;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void handle(LocalDevice localDevice, Address from) {
        RemoteDevice d = localDevice.getRemoteDeviceCreate(deviceIdentifier.getInstanceNumber(), from);
        RemoteObject o = new RemoteObject(objectIdentifier);
        o.setObjectName(objectName.toString());
        d.setObject(o);
        
        localDevice.getEventHandler().fireIHaveReceived(d, o);
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, deviceIdentifier);
        write(queue, objectIdentifier);
        write(queue, objectName);
    }
    
    IHaveRequest(ByteQueue queue) throws BACnetException {
        deviceIdentifier = read(queue, ObjectIdentifier.class);
        objectIdentifier = read(queue, ObjectIdentifier.class);
        objectName = read(queue, CharacterString.class);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((deviceIdentifier == null) ? 0 : deviceIdentifier.hashCode());
        result = PRIME * result + ((objectIdentifier == null) ? 0 : objectIdentifier.hashCode());
        result = PRIME * result + ((objectName == null) ? 0 : objectName.hashCode());
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
        final IHaveRequest other = (IHaveRequest) obj;
        if (deviceIdentifier == null) {
            if (other.deviceIdentifier != null)
                return false;
        }
        else if (!deviceIdentifier.equals(other.deviceIdentifier))
            return false;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null)
                return false;
        }
        else if (!objectIdentifier.equals(other.objectIdentifier))
            return false;
        if (objectName == null) {
            if (other.objectName != null)
                return false;
        }
        else if (!objectName.equals(other.objectName))
            return false;
        return true;
    }
}
