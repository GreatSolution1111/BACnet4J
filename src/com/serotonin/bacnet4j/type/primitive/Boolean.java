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
package com.serotonin.bacnet4j.type.primitive;

import com.serotonin.util.queue.ByteQueue;

public class Boolean extends Primitive {
    public static final byte TYPE_ID = 1;
    
    protected boolean value;
    
    public Boolean(boolean value) {
        this.value = value;
    }
    
    public boolean booleanValue() {
        return value;
    }
    
    public Boolean(ByteQueue queue) {
        long length = readTag(queue);
        if (contextSpecific)
            value = queue.pop() == 1;
        else
            value = length == 1;
    }
    
    @Override
    public void writeImpl(ByteQueue queue) {
        if (contextSpecific)
            queue.push((byte)(value ? 1 : 0));
    }

    @Override
    protected long getLength() {
        if (contextSpecific)
            return 1;
        return (byte)(value ? 1 : 0);
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (value ? 1231 : 1237);
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
        final Boolean other = (Boolean) obj;
        if (value != other.value)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return java.lang.Boolean.toString(value);
    }
}
