/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2009 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.serotonin.bacnet4j.type.constructed;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.util.queue.ByteQueue;

public class SetpointReference extends BaseType {
    private final ObjectPropertyReference setpointReference;

    public SetpointReference(ObjectPropertyReference setpointReference) {
        this.setpointReference = setpointReference;
    }

    @Override
    public void write(ByteQueue queue) {
        writeOptional(queue, setpointReference, 0);
    }
    
    public SetpointReference(ByteQueue queue) throws BACnetException {
        setpointReference = readOptional(queue, ObjectPropertyReference.class, 0);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((setpointReference == null) ? 0 : setpointReference.hashCode());
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
        final SetpointReference other = (SetpointReference) obj;
        if (setpointReference == null) {
            if (other.setpointReference != null)
                return false;
        }
        else if (!setpointReference.equals(other.setpointReference))
            return false;
        return true;
    }
}
