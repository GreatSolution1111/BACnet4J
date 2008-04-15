package com.serotonin.bacnet4j.service.unconfirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.enumerated.Segmentation;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class IAmRequest extends UnconfirmedRequestService {
    public static final byte TYPE_ID = 0;
    
    private ObjectIdentifier iAmDeviceIdentifier;
    private UnsignedInteger maxAPDULengthAccepted;
    private Segmentation segmentationSupported;
    private UnsignedInteger vendorId;
    
    public IAmRequest(ObjectIdentifier iamDeviceIdentifier, UnsignedInteger maxAPDULengthAccepted, 
            Segmentation segmentationSupported, UnsignedInteger vendorId) {
        this.iAmDeviceIdentifier = iamDeviceIdentifier;
        this.maxAPDULengthAccepted = maxAPDULengthAccepted;
        this.segmentationSupported = segmentationSupported;
        this.vendorId = vendorId;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void handle(LocalDevice localDevice, Address from) {
        // Make sure we're not hearing from ourselves.
        if (iAmDeviceIdentifier.getInstanceNumber() == localDevice.getConfiguration().getInstanceId())
            return;
        
        // Register the device in the list of known devices.
        RemoteDevice d = localDevice.getRemoteDeviceCreate(iAmDeviceIdentifier.getInstanceNumber(), from);
        d.setMaxAPDULengthAccepted(maxAPDULengthAccepted.intValue());
        d.setSegmentationSupported(segmentationSupported);
        d.setVendorId(vendorId.intValue());
        
        // Fire the appropriate event.
        localDevice.getEventHandler().fireIAmReceived(d);
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, iAmDeviceIdentifier);
        write(queue, maxAPDULengthAccepted);
        write(queue, segmentationSupported);
        write(queue, vendorId);
    }
    
    IAmRequest(ByteQueue queue) throws BACnetException {
        iAmDeviceIdentifier = read(queue, ObjectIdentifier.class);
        maxAPDULengthAccepted = read(queue, UnsignedInteger.class);
        segmentationSupported = read(queue, Segmentation.class);
        vendorId = read(queue, UnsignedInteger.class);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((iAmDeviceIdentifier == null) ? 0 : iAmDeviceIdentifier.hashCode());
        result = PRIME * result + ((maxAPDULengthAccepted == null) ? 0 : maxAPDULengthAccepted.hashCode());
        result = PRIME * result + ((segmentationSupported == null) ? 0 : segmentationSupported.hashCode());
        result = PRIME * result + ((vendorId == null) ? 0 : vendorId.hashCode());
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
        final IAmRequest other = (IAmRequest) obj;
        if (iAmDeviceIdentifier == null) {
            if (other.iAmDeviceIdentifier != null)
                return false;
        }
        else if (!iAmDeviceIdentifier.equals(other.iAmDeviceIdentifier))
            return false;
        if (maxAPDULengthAccepted == null) {
            if (other.maxAPDULengthAccepted != null)
                return false;
        }
        else if (!maxAPDULengthAccepted.equals(other.maxAPDULengthAccepted))
            return false;
        if (segmentationSupported == null) {
            if (other.segmentationSupported != null)
                return false;
        }
        else if (!segmentationSupported.equals(other.segmentationSupported))
            return false;
        if (vendorId == null) {
            if (other.vendorId != null)
                return false;
        }
        else if (!vendorId.equals(other.vendorId))
            return false;
        return true;
    }
}
