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

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.AmbiguousValue;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.StatusFlags;
import com.serotonin.util.queue.ByteQueue;

public class CommandFailure extends NotificationParameters {
    public static final byte TYPE_ID = 3;

    private final Encodable commandValue;
    private final StatusFlags statusFlags;
    private final Encodable feedbackValue;

    public CommandFailure(Encodable commandValue, StatusFlags statusFlags, Encodable feedbackValue) {
        this.commandValue = commandValue;
        this.statusFlags = statusFlags;
        this.feedbackValue = feedbackValue;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        write(queue, commandValue, 0);
        write(queue, statusFlags, 1);
        write(queue, feedbackValue, 2);
    }

    public CommandFailure(ByteQueue queue) throws BACnetException {
        // // Sweet Jesus...
        // int tag = (queue.peek(0) & 0xff);
        // if ((tag & 8) == 8) {
        // // A class tag, so this is a constructed value.
        // // constructedValue = new AmbiguousValue(queue, 0);
        // System.out.println("class tag");
        // }
        // else {
        // System.out.println("primitive");
        // // // A primitive value
        // // tag = tag >> 4;
        // // if (tag == Null.TYPE_ID)
        // // nullValue = new Null(queue);
        // // else if (tag == Real.TYPE_ID)
        // // realValue = new Real(queue);
        // // else if (tag == Enumerated.TYPE_ID)
        // // binaryValue = new BinaryPV(queue);
        // // else if (tag == UnsignedInteger.TYPE_ID)
        // // integerValue = new UnsignedInteger(queue);
        // // else
        // // throw new BACnetErrorException(ErrorClass.property, ErrorCode.invalidDataType,
        // // "Unsupported primitive id: " + tag);
        // }

        commandValue = new AmbiguousValue(queue, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
        feedbackValue = new AmbiguousValue(queue, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((commandValue == null) ? 0 : commandValue.hashCode());
        result = PRIME * result + ((feedbackValue == null) ? 0 : feedbackValue.hashCode());
        result = PRIME * result + ((statusFlags == null) ? 0 : statusFlags.hashCode());
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
        final CommandFailure other = (CommandFailure) obj;
        if (commandValue == null) {
            if (other.commandValue != null)
                return false;
        }
        else if (!commandValue.equals(other.commandValue))
            return false;
        if (feedbackValue == null) {
            if (other.feedbackValue != null)
                return false;
        }
        else if (!feedbackValue.equals(other.feedbackValue))
            return false;
        if (statusFlags == null) {
            if (other.statusFlags != null)
                return false;
        }
        else if (!statusFlags.equals(other.statusFlags))
            return false;
        return true;
    }
}
