package com.serotonin.bacnet4j.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.BACnetError;
import com.serotonin.bacnet4j.type.constructed.ObjectPropertyReference;
import com.serotonin.bacnet4j.type.constructed.PropertyReference;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

public class PropertyValues implements Iterable<ObjectPropertyReference> {
    private final Map<ObjectPropertyReference, Encodable> values = new HashMap<ObjectPropertyReference, Encodable>();
    
    public void add(ObjectIdentifier oid, PropertyIdentifier pid, UnsignedInteger pin, Encodable value) {
        values.put(new ObjectPropertyReference(oid, pid, pin), value);
    }
    
    public Encodable get(ObjectIdentifier oid, PropertyReference ref) {
        return values.get(new ObjectPropertyReference(oid, ref.getPropertyIdentifier(), ref.getPropertyArrayIndex()));
    }
    
    public Encodable get(ObjectIdentifier oid, PropertyIdentifier pid) {
        return values.get(new ObjectPropertyReference(oid, pid));
    }
    
    public String getString(ObjectIdentifier oid, PropertyIdentifier pid) {
        return get(oid, pid).toString();
    }
    
    public String getString(ObjectIdentifier oid, PropertyIdentifier pid, String defaultValue) {
        Encodable value = get(oid, pid);
        if (value == null || value instanceof BACnetError)
            return defaultValue;
        return value.toString();
    }

    public Iterator<ObjectPropertyReference> iterator() {
        return values.keySet().iterator();
    }
}
