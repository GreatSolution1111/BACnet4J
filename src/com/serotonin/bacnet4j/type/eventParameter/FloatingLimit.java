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
package com.serotonin.bacnet4j.type.eventParameter;

import com.serotonin.bacnet4j.type.constructed.DeviceObjectPropertyReference;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class FloatingLimit extends EventParameter {
    public static final byte TYPE_ID = 4;
    
    private final UnsignedInteger timeDelay;
    private final DeviceObjectPropertyReference setpointReference;
    private final Real lowDiffLimit;
    private final Real highDiffLimit;
    private final Real deadband;
    
    public FloatingLimit(UnsignedInteger timeDelay, DeviceObjectPropertyReference setpointReference, Real lowDiffLimit, Real highDiffLimit, Real deadband) {
        this.timeDelay = timeDelay;
        this.setpointReference = setpointReference;
        this.lowDiffLimit = lowDiffLimit;
        this.highDiffLimit = highDiffLimit;
        this.deadband = deadband;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        timeDelay.write(queue, 0);
        setpointReference.write(queue, 1);
        lowDiffLimit.write(queue, 2);
        highDiffLimit.write(queue, 3);
        deadband.write(queue, 4);
    }
    
    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((deadband == null) ? 0 : deadband.hashCode());
        result = PRIME * result + ((highDiffLimit == null) ? 0 : highDiffLimit.hashCode());
        result = PRIME * result + ((lowDiffLimit == null) ? 0 : lowDiffLimit.hashCode());
        result = PRIME * result + ((setpointReference == null) ? 0 : setpointReference.hashCode());
        result = PRIME * result + ((timeDelay == null) ? 0 : timeDelay.hashCode());
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
        final FloatingLimit other = (FloatingLimit) obj;
        if (deadband == null) {
            if (other.deadband != null)
                return false;
        }
        else if (!deadband.equals(other.deadband))
            return false;
        if (highDiffLimit == null) {
            if (other.highDiffLimit != null)
                return false;
        }
        else if (!highDiffLimit.equals(other.highDiffLimit))
            return false;
        if (lowDiffLimit == null) {
            if (other.lowDiffLimit != null)
                return false;
        }
        else if (!lowDiffLimit.equals(other.lowDiffLimit))
            return false;
        if (setpointReference == null) {
            if (other.setpointReference != null)
                return false;
        }
        else if (!setpointReference.equals(other.setpointReference))
            return false;
        if (timeDelay == null) {
            if (other.timeDelay != null)
                return false;
        }
        else if (!timeDelay.equals(other.timeDelay))
            return false;
        return true;
    }
}
