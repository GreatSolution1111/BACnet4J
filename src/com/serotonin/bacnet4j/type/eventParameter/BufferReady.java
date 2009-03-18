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

import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class BufferReady extends EventParameter {
    public static final byte TYPE_ID = 10;
    
    private final UnsignedInteger notificationThreshold;
    private final UnsignedInteger previousNotificationCount;
    
    public BufferReady(UnsignedInteger notificationThreshold, UnsignedInteger previousNotificationCount) {
        this.notificationThreshold = notificationThreshold;
        this.previousNotificationCount = previousNotificationCount;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        notificationThreshold.write(queue, 0);
        previousNotificationCount.write(queue, 1);
    }
    
    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((notificationThreshold == null) ? 0 : notificationThreshold.hashCode());
        result = PRIME * result + ((previousNotificationCount == null) ? 0 : previousNotificationCount.hashCode());
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
        final BufferReady other = (BufferReady) obj;
        if (notificationThreshold == null) {
            if (other.notificationThreshold != null)
                return false;
        }
        else if (!notificationThreshold.equals(other.notificationThreshold))
            return false;
        if (previousNotificationCount == null) {
            if (other.previousNotificationCount != null)
                return false;
        }
        else if (!previousNotificationCount.equals(other.previousNotificationCount))
            return false;
        return true;
    }
}
