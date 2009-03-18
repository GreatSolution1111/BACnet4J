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
package com.serotonin.bacnet4j.type.notificationParameters;

import com.serotonin.bacnet4j.exception.BACnetErrorException;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.BaseType;
import com.serotonin.bacnet4j.type.enumerated.ErrorClass;
import com.serotonin.bacnet4j.type.enumerated.ErrorCode;
import com.serotonin.util.queue.ByteQueue;

abstract public class NotificationParameters extends BaseType {
    public static NotificationParameters createNotificationParameters(ByteQueue queue, int contextId)
            throws BACnetException {
        popStart(queue, contextId);
        NotificationParameters result = createNotificationParameters(queue);
        popEnd(queue, contextId);
        return result;
    }
    
    public static NotificationParameters createNotificationParametersOptional(ByteQueue queue, int contextId)
            throws BACnetException {
        if (readStart(queue) != contextId)
            return null;
        return createNotificationParameters(queue, contextId);
    }
    
    public static NotificationParameters createNotificationParameters(ByteQueue queue) throws BACnetException {
        // Get the first byte. It will tell us what the service type is.
        int type = popStart(queue);
        
        NotificationParameters result;
        if (type == ChangeOfBitString.TYPE_ID) // 0
            result = new ChangeOfBitString(queue);
        else if (type == ChangeOfState.TYPE_ID) // 1
            result = new ChangeOfState(queue);
        else if (type == ChangeOfValue.TYPE_ID) // 2
            result = new ChangeOfValue(queue);
        else if (type == CommandFailure.TYPE_ID) // 3
            result = new CommandFailure(queue);
        else if (type == FloatingLimit.TYPE_ID) // 4
            result = new FloatingLimit(queue);
        else if (type == OutOfRange.TYPE_ID) // 5
            result = new OutOfRange(queue);
        else if (type == ComplexEventType.TYPE_ID) // 6
            result = new ComplexEventType(queue);
        else if (type == ChangeOfLifeSafety.TYPE_ID) // 8
            result = new ChangeOfLifeSafety(queue);
        else if (type == Extended.TYPE_ID) // 9
            result = new Extended(queue);
        else if (type == BufferReady.TYPE_ID) // 10
            result = new BufferReady(queue);
        else if (type == UnsignedRange.TYPE_ID) // 11
            result = new UnsignedRange(queue);
        else
            throw new BACnetErrorException(ErrorClass.property, ErrorCode.invalidParameterDataType);
        
        popEnd(queue, type);
        return result;
    }
    
    
    @Override
    final public void write(ByteQueue queue) {
        writeContextTag(queue, getTypeId(), true);
        writeImpl(queue);
        writeContextTag(queue, getTypeId(), false);
    }
    
    abstract protected int getTypeId();
    abstract protected void writeImpl(ByteQueue queue);
}
