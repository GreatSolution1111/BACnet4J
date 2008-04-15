package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.NotImplementedException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.enumerated.LifeSafetyOperation;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class LifeSafetyOperationRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 27;
    
    private UnsignedInteger requestingProcessIdentifier;
    private CharacterString requestingSource;
    private LifeSafetyOperation request;
    private ObjectIdentifier objectIdentifier;
    
    public LifeSafetyOperationRequest(UnsignedInteger requestingProcessIdentifier, CharacterString requestingSource, 
            LifeSafetyOperation request, ObjectIdentifier objectIdentifier) {
        this.requestingProcessIdentifier = requestingProcessIdentifier;
        this.requestingSource = requestingSource;
        this.request = request;
        this.objectIdentifier = objectIdentifier;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from) throws BACnetException {
        throw new NotImplementedException();
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, requestingProcessIdentifier, 0);
        write(queue, requestingSource, 1);
        write(queue, request, 2);
        writeOptional(queue, objectIdentifier, 3);
    }
    
    LifeSafetyOperationRequest(ByteQueue queue) throws BACnetException {
         requestingProcessIdentifier = read(queue, UnsignedInteger.class, 0);
         requestingSource = read(queue, CharacterString.class, 1);
         request = read(queue, LifeSafetyOperation.class, 2);
         objectIdentifier = readOptional(queue, ObjectIdentifier.class, 3);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((objectIdentifier == null) ? 0 : objectIdentifier.hashCode());
        result = PRIME * result + ((request == null) ? 0 : request.hashCode());
        result = PRIME * result + ((requestingProcessIdentifier == null) ? 0 : requestingProcessIdentifier.hashCode());
        result = PRIME * result + ((requestingSource == null) ? 0 : requestingSource.hashCode());
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
        final LifeSafetyOperationRequest other = (LifeSafetyOperationRequest) obj;
        if (objectIdentifier == null) {
            if (other.objectIdentifier != null)
                return false;
        }
        else if (!objectIdentifier.equals(other.objectIdentifier))
            return false;
        if (request == null) {
            if (other.request != null)
                return false;
        }
        else if (!request.equals(other.request))
            return false;
        if (requestingProcessIdentifier == null) {
            if (other.requestingProcessIdentifier != null)
                return false;
        }
        else if (!requestingProcessIdentifier.equals(other.requestingProcessIdentifier))
            return false;
        if (requestingSource == null) {
            if (other.requestingSource != null)
                return false;
        }
        else if (!requestingSource.equals(other.requestingSource))
            return false;
        return true;
    }
}
