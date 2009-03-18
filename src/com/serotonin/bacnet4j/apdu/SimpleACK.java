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
package com.serotonin.bacnet4j.apdu;

import com.serotonin.util.queue.ByteQueue;

public class SimpleACK extends AckAPDU {
    public static final byte TYPE_ID = 2;

    /**
     * This parameter shall contain the value of the BACnetConfirmedServiceChoice corresponding to the service 
     * contained in the previous BACnet-Confirmed-Service-Request that has resulted in this acknowledgment.
     */
    private final int serviceAckChoice;
    
    public SimpleACK(byte originalInvokeId, int serviceAckChoice) {
        this.originalInvokeId = originalInvokeId;
        this.serviceAckChoice = serviceAckChoice;
    }
    
    @Override
    public byte getPduType() {
        return TYPE_ID;
    }

    @Override
    public void write(ByteQueue queue) {
        queue.push(getShiftedTypeId(TYPE_ID));
        queue.push(originalInvokeId);
        queue.push(serviceAckChoice);
    }
    
    public SimpleACK(ByteQueue queue) {
        queue.pop(); // no news here
        originalInvokeId = queue.pop();
        serviceAckChoice = queue.popU1B();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + originalInvokeId;
        result = PRIME * result + serviceAckChoice;
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
        final SimpleACK other = (SimpleACK) obj;
        if (originalInvokeId != other.originalInvokeId)
            return false;
        if (serviceAckChoice != other.serviceAckChoice)
            return false;
        return true;
    }

    @Override
    public boolean expectsReply() {
        return false;
    }
}
