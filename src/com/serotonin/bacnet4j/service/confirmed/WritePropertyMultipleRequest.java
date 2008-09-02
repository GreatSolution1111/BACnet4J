package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetErrorException;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.BACnetError;
import com.serotonin.bacnet4j.type.constructed.ObjectPropertyReference;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.constructed.WriteAccessSpecification;
import com.serotonin.bacnet4j.type.enumerated.ErrorClass;
import com.serotonin.bacnet4j.type.enumerated.ErrorCode;
import com.serotonin.bacnet4j.type.error.WritePropertyMultipleError;
import com.serotonin.util.queue.ByteQueue;

public class WritePropertyMultipleRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 16;
    
    private final SequenceOf<WriteAccessSpecification> listOfWriteAccessSpecifications;
    
    public WritePropertyMultipleRequest(SequenceOf<WriteAccessSpecification> listOfWriteAccessSpecifications) {
        this.listOfWriteAccessSpecifications = listOfWriteAccessSpecifications;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }
    
    @Override
    public void write(ByteQueue queue) {
        write(queue, listOfWriteAccessSpecifications);
    }
    
    WritePropertyMultipleRequest(ByteQueue queue) throws BACnetException {
        listOfWriteAccessSpecifications = readSequenceOf(queue, WriteAccessSpecification.class);
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from, Network network)
            throws BACnetException {
        BACnetObject obj;
        for (WriteAccessSpecification spec : listOfWriteAccessSpecifications) {
            obj = localDevice.getObject(spec.getObjectIdentifier());
            if (obj == null)
                throw createException(ErrorClass.property, ErrorCode.writeAccessDenied, spec, null);
            
            for (PropertyValue pv : spec.getListOfProperties()) {
                try {
                    if (localDevice.getEventHandler().checkAllowPropertyWrite(obj, pv)) {
                        obj.setProperty(pv);
                        localDevice.getEventHandler().propertyWritten(obj, pv);
                    }
                    else
                        throw createException(ErrorClass.property, ErrorCode.writeAccessDenied, spec, pv);
                }
                catch (BACnetServiceException e) {
                    throw createException(e.getErrorClass(), e.getErrorCode(), spec, pv);
                }
            }
        }
        
        return null;
    }
    
    private BACnetErrorException createException(ErrorClass errorClass, ErrorCode errorCode,
            WriteAccessSpecification spec, PropertyValue pv) {
        if (pv == null)
            pv = spec.getListOfProperties().get(1);
        return new BACnetErrorException(new WritePropertyMultipleError(getChoiceId(),
                new BACnetError(errorClass, errorCode),
                new ObjectPropertyReference(spec.getObjectIdentifier(), pv.getPropertyIdentifier(),
                        pv.getPropertyArrayIndex())));
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((listOfWriteAccessSpecifications == null) ? 0 : listOfWriteAccessSpecifications.hashCode());
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
        final WritePropertyMultipleRequest other = (WritePropertyMultipleRequest) obj;
        if (listOfWriteAccessSpecifications == null) {
            if (other.listOfWriteAccessSpecifications != null)
                return false;
        }
        else if (!listOfWriteAccessSpecifications.equals(other.listOfWriteAccessSpecifications))
            return false;
        return true;
    }
}
