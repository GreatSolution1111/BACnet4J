/*
    Copyright (C) 2006-2007 Serotonin Software Technologies Inc.
 	@author Matthew Lohbihler
 */
package com.serotonin.bacnet4j;

import java.util.List;

import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.enumerated.Segmentation;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.OctetString;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.util.PropertyReferences;

/**
 * @author Matthew Lohbihler
 */
public class DiscoveryTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        LocalDevice localDevice = new LocalDevice(1234, "192.168.0.255");
        localDevice.initialize();
        
        // Who is
        //localDevice.sendBroadcast(new WhoIsRequest(null, null));
//        localDevice.sendUnconfirmed(
//                new Address(new UnsignedInteger(47808), new OctetString(new byte[] {(byte)206, (byte)210, 100, (byte)134})), 
//                new WhoIsRequest(new UnsignedInteger(105), new UnsignedInteger(105)));
        RemoteDevice rd = new RemoteDevice(105, new Address(new UnsignedInteger(47808), 
                new OctetString(new byte[] {(byte)206, (byte)210, 100, (byte)134})), null);
        rd.setSegmentationSupported(Segmentation.segmentedBoth);
        rd.setMaxAPDULengthAccepted(1476);
        localDevice.addRemoteDevice(rd);
        
        // Wait a bit for responses to come in.
        Thread.sleep(1000);
        
        // Get extended information for all remote devices.
        for (RemoteDevice d : localDevice.getRemoteDevices()) {
            localDevice.getExtendedDeviceInformation(d);
            List<ObjectIdentifier> oids = ((SequenceOf<ObjectIdentifier>)localDevice.sendReadPropertyAllowNull(
                    d, d.getObjectIdentifier(), PropertyIdentifier.objectList)).getValues();
            
            PropertyReferences refs = new PropertyReferences();
            for (ObjectIdentifier oid : oids)
                addPropertyReferences(refs, oid);
          
            localDevice.readProperties(d, refs);
            System.out.println(d);
        }
        
        // Wait a bit for responses to come in.
        Thread.sleep(2000);
        
        localDevice.terminate();
    }
    
    private static void addPropertyReferences(PropertyReferences refs, ObjectIdentifier oid) {
        refs.add(oid, PropertyIdentifier.objectName);
        
        ObjectType type = oid.getObjectType();
        if (ObjectType.accumulator.equals(type)) {
            refs.add(oid, PropertyIdentifier.units);
        }
        else if (ObjectType.analogInput.equals(type) || 
                ObjectType.analogOutput.equals(type) || 
                ObjectType.analogValue.equals(type) ||
                ObjectType.pulseConverter.equals(type)) {
            refs.add(oid, PropertyIdentifier.units);
        }
        else if (ObjectType.binaryInput.equals(type) || 
                ObjectType.binaryOutput.equals(type) || 
                ObjectType.binaryValue.equals(type)) {
            refs.add(oid, PropertyIdentifier.inactiveText);
            refs.add(oid, PropertyIdentifier.activeText);
        }
        else if (ObjectType.lifeSafetyPoint.equals(type)) {
            refs.add(oid, PropertyIdentifier.units);
        }
        else if (ObjectType.loop.equals(type)) {
            refs.add(oid, PropertyIdentifier.outputUnits);
        }
        else if (ObjectType.multiStateInput.equals(type) || 
                ObjectType.multiStateOutput.equals(type) || 
                ObjectType.multiStateValue.equals(type)) {
            refs.add(oid, PropertyIdentifier.stateText);
        }
        else
            return;
        
        refs.add(oid, PropertyIdentifier.presentValue);
    }
}
