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

public class DailySchedule extends BaseType {
    private static final long serialVersionUID = -8539541069909649459L;
    private final SequenceOf<TimeValue> daySchedule;

    public DailySchedule(SequenceOf<TimeValue> daySchedule) {
        this.daySchedule = daySchedule;
    }

    public SequenceOf<TimeValue> getDaySchedule() {
        return daySchedule;
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, daySchedule, 0);
    }

    public DailySchedule(ByteQueue queue) throws BACnetException {
        daySchedule = readSequenceOf(queue, TimeValue.class, 0);
    }
}
