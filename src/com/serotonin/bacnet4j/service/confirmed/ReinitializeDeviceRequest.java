package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.NotImplementedException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.Enumerated;
import com.serotonin.util.queue.ByteQueue;

public class ReinitializeDeviceRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 20;
    
    private ReinitializedStateOfDevice reinitializedStateOfDevice;
    private CharacterString password;
    
    public ReinitializeDeviceRequest(ReinitializedStateOfDevice reinitializedStateOfDevice, CharacterString password) {
        this.reinitializedStateOfDevice = reinitializedStateOfDevice;
        this.password = password;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, reinitializedStateOfDevice, 0);
        writeOptional(queue, password, 1);
    }
    
    ReinitializeDeviceRequest(ByteQueue queue) throws BACnetException {
        reinitializedStateOfDevice = read(queue, ReinitializedStateOfDevice.class, 0);
        password = readOptional(queue, CharacterString.class, 1);
    }
    
    public static class ReinitializedStateOfDevice extends Enumerated {
        public static final ReinitializedStateOfDevice coldstart = new ReinitializedStateOfDevice(0);
        public static final ReinitializedStateOfDevice warmstart = new ReinitializedStateOfDevice(1);
        public static final ReinitializedStateOfDevice startbackup = new ReinitializedStateOfDevice(2);
        public static final ReinitializedStateOfDevice endbackup = new ReinitializedStateOfDevice(3);
        public static final ReinitializedStateOfDevice startrestore = new ReinitializedStateOfDevice(4);
        public static final ReinitializedStateOfDevice endrestore = new ReinitializedStateOfDevice(5);
        public static final ReinitializedStateOfDevice abortrestore = new ReinitializedStateOfDevice(6);

        public ReinitializedStateOfDevice(int value) {
            super(value);
        }
        
        public ReinitializedStateOfDevice(ByteQueue queue) {
            super(queue);
        }
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from) throws BACnetException {
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((password == null) ? 0 : password.hashCode());
        result = PRIME * result + ((reinitializedStateOfDevice == null) ? 0 : reinitializedStateOfDevice.hashCode());
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
        final ReinitializeDeviceRequest other = (ReinitializeDeviceRequest) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (reinitializedStateOfDevice == null) {
            if (other.reinitializedStateOfDevice != null)
                return false;
        }
        else if (!reinitializedStateOfDevice.equals(other.reinitializedStateOfDevice))
            return false;
        return true;
    }
}
