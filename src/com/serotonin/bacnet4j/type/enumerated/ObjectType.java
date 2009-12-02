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
package com.serotonin.bacnet4j.type.enumerated;

import com.serotonin.bacnet4j.type.primitive.Enumerated;
import com.serotonin.util.queue.ByteQueue;

public class ObjectType extends Enumerated {
    public static final ObjectType analogInput = new ObjectType(0);
    public static final ObjectType analogOutput = new ObjectType(1);
    public static final ObjectType analogValue = new ObjectType(2);
    public static final ObjectType binaryInput = new ObjectType(3);
    public static final ObjectType binaryOutput = new ObjectType(4);
    public static final ObjectType binaryValue = new ObjectType(5);
    public static final ObjectType calendar = new ObjectType(6);
    public static final ObjectType command = new ObjectType(7);
    public static final ObjectType device = new ObjectType(8);
    public static final ObjectType eventEnrollment = new ObjectType(9);
    public static final ObjectType file = new ObjectType(10);
    public static final ObjectType group = new ObjectType(11);
    public static final ObjectType loop = new ObjectType(12);
    public static final ObjectType multiStateInput = new ObjectType(13);
    public static final ObjectType multiStateOutput = new ObjectType(14);
    public static final ObjectType notificationClass = new ObjectType(15);
    public static final ObjectType program = new ObjectType(16);
    public static final ObjectType schedule = new ObjectType(17);
    public static final ObjectType averaging = new ObjectType(18);
    public static final ObjectType multiStateValue = new ObjectType(19);
    public static final ObjectType trendLog = new ObjectType(20);
    public static final ObjectType lifeSafetyPoint = new ObjectType(21);
    public static final ObjectType lifeSafetyZone = new ObjectType(22);
    public static final ObjectType accumulator = new ObjectType(23);
    public static final ObjectType pulseConverter = new ObjectType(24);
    public static final ObjectType eventLog = new ObjectType(25);
    public static final ObjectType trendLogMultiple = new ObjectType(27);
    public static final ObjectType loadControl = new ObjectType(28);
    public static final ObjectType structuredView = new ObjectType(29);
    public static final ObjectType accessDoor = new ObjectType(30);

    public ObjectType(int value) {
        super(value);
    }
    
    public ObjectType(ByteQueue queue) {
        super(queue);
    }
    
    @Override
    public String toString() {
        int type = intValue();
        if (type == analogInput.intValue())
            return "Analog Input";
        if (type == analogOutput.intValue())
            return "Analog Output";
        if (type == analogValue.intValue())
            return "Analog Value";
        if (type == binaryInput.intValue())
            return "Binary Input";
        if (type == binaryOutput.intValue())
            return "Binary Output";
        if (type == binaryValue.intValue())
            return "Binary Value";
        if (type == calendar.intValue())
            return "Calendar";
        if (type == command.intValue())
            return "Command";
        if (type == device.intValue())
            return "Device";
        if (type == eventEnrollment.intValue())
            return "Event Enrollment";
        if (type == file.intValue())
            return "File";
        if (type == group.intValue())
            return "Group";
        if (type == loop.intValue())
            return "Loop";
        if (type == multiStateInput.intValue())
            return "Multi-state Input";
        if (type == multiStateOutput.intValue())
            return "Multi-state Output";
        if (type == notificationClass.intValue())
            return "Notification Class";
        if (type == program.intValue())
            return "Program";
        if (type == schedule.intValue())
            return "Schedule";
        if (type == averaging.intValue())
            return "Averaging";
        if (type == multiStateValue.intValue())
            return "Multi-state Value";
        if (type == trendLog.intValue())
            return "Trend Log";
        if (type == lifeSafetyPoint.intValue())
            return "Life Safety Point";
        if (type == lifeSafetyZone.intValue())
            return "Life Safety Zone";
        if (type == accumulator.intValue())
            return "Accumulator";
        if (type == pulseConverter.intValue())
            return "Pulse Converter";
        if (type == eventLog.intValue())
            return "Event Log";
        if (type == trendLogMultiple.intValue())
            return "Trend Log Multiple";
        if (type == loadControl.intValue())
            return "Load Control";
        if (type == structuredView.intValue())
            return "Structured View";
        if (type == accessDoor.intValue())
            return "Access Door";
        return "Vendor Specific ("+ type +")";
    }
}
