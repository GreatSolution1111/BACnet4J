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
package com.serotonin.bacnet4j.service.unconfirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.DateTime;
import com.serotonin.bacnet4j.type.constructed.ServicesSupported;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.util.queue.ByteQueue;

public class TimeSynchronizationRequest extends UnconfirmedRequestService {
    public static final byte TYPE_ID = 6;

    private final DateTime time;

    public TimeSynchronizationRequest(DateTime time) {
        this.time = time;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, time);
    }

    TimeSynchronizationRequest(ByteQueue queue) throws BACnetException {
        time = read(queue, DateTime.class);
    }

    @Override
    public void handle(LocalDevice localDevice, Address from, Network network) {
        try {
            ServicesSupported servicesSupported = (ServicesSupported) localDevice.getConfiguration().getProperty(
                    PropertyIdentifier.protocolServicesSupported);
            if (servicesSupported.isTimeSynchronization())
                localDevice.getEventHandler().synchronizeTime(time, false);
        }
        catch (BACnetServiceException e) {
            // no op
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((time == null) ? 0 : time.hashCode());
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
        final TimeSynchronizationRequest other = (TimeSynchronizationRequest) obj;
        if (time == null) {
            if (other.time != null)
                return false;
        }
        else if (!time.equals(other.time))
            return false;
        return true;
    }
}
