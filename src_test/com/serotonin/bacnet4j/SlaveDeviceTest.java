package com.serotonin.bacnet4j;

import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.EngineeringUnits;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

public class SlaveDeviceTest {
    public static void main(String[] args) throws Exception {
        LocalDevice localDevice = new LocalDevice(1968, "192.168.0.255");
        localDevice.setPort(2068);
        
        // Set up a few objects.
        BACnetObject ai0 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.analogInput));
        ai0.setProperty(PropertyIdentifier.units, EngineeringUnits.centimeters);
        localDevice.addObject(ai0);
        
        BACnetObject ai1 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.analogInput));
        ai0.setProperty(PropertyIdentifier.units, EngineeringUnits.percentObscurationPerFoot);
        localDevice.addObject(ai1);
        
        BACnetObject bi0 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.binaryInput));
        localDevice.addObject(bi0);
        bi0.setProperty(PropertyIdentifier.objectName, new CharacterString("Off and on"));
        bi0.setProperty(PropertyIdentifier.inactiveText, new CharacterString("Off"));
        bi0.setProperty(PropertyIdentifier.activeText, new CharacterString("On"));
        
        BACnetObject bi1 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.binaryInput));
        localDevice.addObject(bi1);
        bi1.setProperty(PropertyIdentifier.objectName, new CharacterString("Good and bad"));
        bi1.setProperty(PropertyIdentifier.inactiveText, new CharacterString("Bad"));
        bi1.setProperty(PropertyIdentifier.activeText, new CharacterString("Good"));
        
        BACnetObject mso0 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.multiStateOutput));
        mso0.setProperty(PropertyIdentifier.objectName, new CharacterString("Vegetable"));
        mso0.setProperty(PropertyIdentifier.numberOfStates, new UnsignedInteger(4));
        mso0.setProperty(PropertyIdentifier.stateText, 1, new CharacterString("Tomatoe"));
        mso0.setProperty(PropertyIdentifier.stateText, 2, new CharacterString("Potatoe"));
        mso0.setProperty(PropertyIdentifier.stateText, 3, new CharacterString("Onion"));
        mso0.setProperty(PropertyIdentifier.stateText, 4, new CharacterString("Broccoli"));
        mso0.setProperty(PropertyIdentifier.presentValue, new UnsignedInteger(2));
        localDevice.addObject(mso0);
        
        BACnetObject ao0 = new BACnetObject(localDevice, 
                localDevice.getNextInstanceObjectIdentifier(ObjectType.analogOutput));
        ao0.setProperty(PropertyIdentifier.objectName, new CharacterString("Settable analog"));
        localDevice.addObject(ao0);
        
        
        // Start the local device.
        localDevice.initialize();
        
        // Send an iam.
        localDevice.sendBroadcast(localDevice.getIAm());
        
        // Let it go...
        float ai0value = 0;
        float ai1value = 0;
        boolean bi0value = false;
        boolean bi1value = false;
        mso0.setProperty(PropertyIdentifier.presentValue, new UnsignedInteger(2));
        while (true) {
            // Change the values.
            ai0value += 0.1;
            ai1value += 0.7;
            bi0value = !bi0value;
            bi1value = !bi1value;
            
            
            // Update the values in the objects.
            ai0.setProperty(PropertyIdentifier.presentValue, new Real(ai0value));
            ai1.setProperty(PropertyIdentifier.presentValue, new Real(ai1value));
            bi0.setProperty(PropertyIdentifier.presentValue, bi0value ? BinaryPV.active : BinaryPV.inactive);
            bi1.setProperty(PropertyIdentifier.presentValue, bi1value ? BinaryPV.active : BinaryPV.inactive);
            
            Thread.sleep(100000);
        }
    }
}
