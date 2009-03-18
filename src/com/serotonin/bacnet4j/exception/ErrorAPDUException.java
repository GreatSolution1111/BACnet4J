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
package com.serotonin.bacnet4j.exception;

import com.serotonin.bacnet4j.apdu.Error;
import com.serotonin.bacnet4j.type.constructed.BACnetError;

public class ErrorAPDUException extends BACnetException {
    private static final long serialVersionUID = -1;
    
    private final Error apdu;

    public ErrorAPDUException(Error apdu) {
        super(apdu.toString());
        this.apdu = apdu;
    }

    public Error getApdu() {
        return apdu;
    }
    
    public BACnetError getBACnetError() {
        return apdu.getError().getError();
    }
}
