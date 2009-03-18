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
package com.serotonin.bacnet4j.service.acknowledgement;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.primitive.SignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class AtomicWriteFileAck extends AcknowledgementService {
    public static final byte TYPE_ID = 7;
    
    private final boolean recordAccess;
    private final SignedInteger fileStart;
    
    public AtomicWriteFileAck(boolean recordAccess, SignedInteger fileStart) {
        this.recordAccess = recordAccess;
        this.fileStart = fileStart;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }
    
    @Override
    public void write(ByteQueue queue) {
        write(queue, fileStart, recordAccess ? 1 : 0);
    }
    
    AtomicWriteFileAck(ByteQueue queue) throws BACnetException {
        recordAccess = peekTagNumber(queue) == 1;
        fileStart = read(queue, SignedInteger.class, recordAccess ? 1 : 0);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((fileStart == null) ? 0 : fileStart.hashCode());
        result = PRIME * result + (recordAccess ? 1231 : 1237);
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
        final AtomicWriteFileAck other = (AtomicWriteFileAck) obj;
        if (fileStart == null) {
            if (other.fileStart != null)
                return false;
        }
        else if (!fileStart.equals(other.fileStart))
            return false;
        if (recordAccess != other.recordAccess)
            return false;
        return true;
    }
}
