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
package com.serotonin.bacnet4j.enums;

public enum Month {
    JANUARY     (1),
    FEBRUARY    (2),
    MARCH       (3),
    APRIL       (4),
    MAY         (5),
    JUNE        (6),
    JULY        (7),
    AUGUST      (8),
    SEPTEMBER   (9),
    OCTOBER     (10),
    NOVEMBER    (11),
    DECEMBER    (12),
    ODD_MONTHS  (13),
    EVEN_MONTHS (14),
    UNSPECIFIED (255);
    
    private byte id;
    
    Month(int id) {
        this.id = (byte)id;
    }
    
    public byte getId() {
        return id;
    }
    
    public static Month valueOf(byte id) {
        if (id == JANUARY.id)
            return JANUARY;
        if (id == FEBRUARY.id)
            return FEBRUARY;
        if (id == MARCH.id)
            return MARCH;
        if (id == APRIL.id)
            return APRIL;
        if (id == MAY.id)
            return MAY;
        if (id == JUNE.id)
            return JUNE;
        if (id == JULY.id)
            return JULY;
        if (id == AUGUST.id)
            return AUGUST;
        if (id == SEPTEMBER.id)
            return SEPTEMBER;
        if (id == OCTOBER.id)
            return OCTOBER;
        if (id == NOVEMBER.id)
            return NOVEMBER;
        if (id == DECEMBER.id)
            return DECEMBER;
        if (id == ODD_MONTHS.id)
            return ODD_MONTHS;
        if (id == EVEN_MONTHS.id)
            return EVEN_MONTHS;
        if (id == UNSPECIFIED.id)
            return UNSPECIFIED;
            
        throw new IllegalArgumentException("Unknown id: "+ id);
    }
}
