package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.NotImplementedException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.BaseType;
import com.serotonin.bacnet4j.type.constructed.RecipientProcess;
import com.serotonin.bacnet4j.type.enumerated.EventType;
import com.serotonin.bacnet4j.type.primitive.Enumerated;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class GetEnrollmentSummaryRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 4;
    
    public interface AcknowledgmentFilters {
        int all = 0;
        int acked = 1;
        int notAcked = 2;
    }
    
    public interface EventStateFilter {
        int offnormal = 0;
        int fault = 1;
        int normal = 2;
        int all = 3;
        int active = 4;
    }
    
    private Enumerated acknowledgmentFilter;  // 0
    private RecipientProcess enrollmentFilter;  // 1 optional
    private Enumerated eventStateFilter;  // 2 optional
    private EventType eventTypeFilter;  // 3 optional
    private PriorityFilter priorityFilter;  // 4 optional
    private UnsignedInteger notificationClassFilter;  // 5 optional
    
    public GetEnrollmentSummaryRequest(Enumerated acknowledgmentFilter, RecipientProcess enrollmentFilter, 
            Enumerated eventStateFilter, EventType eventTypeFilter, PriorityFilter priorityFilter, 
            UnsignedInteger notificationClassFilter) {
        this.acknowledgmentFilter = acknowledgmentFilter;
        this.enrollmentFilter = enrollmentFilter;
        this.eventStateFilter = eventStateFilter;
        this.eventTypeFilter = eventTypeFilter;
        this.priorityFilter = priorityFilter;
        this.notificationClassFilter = notificationClassFilter;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from) throws BACnetException {
        throw new NotImplementedException();
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, acknowledgmentFilter, 0);
        writeOptional(queue, enrollmentFilter, 1);
        writeOptional(queue, eventStateFilter, 2);
        writeOptional(queue, eventTypeFilter, 3);
        writeOptional(queue, priorityFilter, 4);
        writeOptional(queue, notificationClassFilter, 5);
    }
    
    GetEnrollmentSummaryRequest(ByteQueue queue) throws BACnetException {
        acknowledgmentFilter = read(queue, Enumerated.class, 0);
        enrollmentFilter = readOptional(queue, RecipientProcess.class, 1);
        eventStateFilter = readOptional(queue, Enumerated.class, 2);
        eventTypeFilter = readOptional(queue, EventType.class, 3);
        priorityFilter = readOptional(queue, PriorityFilter.class, 4);
        notificationClassFilter = readOptional(queue, UnsignedInteger.class, 5);
    }
    
    public static class PriorityFilter extends BaseType {
        private UnsignedInteger minPriority;
        private UnsignedInteger maxPriority;
        
        public PriorityFilter(UnsignedInteger minPriority, UnsignedInteger maxPriority) {
            this.minPriority = minPriority;
            this.maxPriority = maxPriority;
        }

        @Override
        public void write(ByteQueue queue) {
            minPriority.write(queue, 0);
            maxPriority.write(queue, 1);
        }
        
        public PriorityFilter(ByteQueue queue) throws BACnetException {
            minPriority = read(queue, UnsignedInteger.class, 0);
            maxPriority = read(queue, UnsignedInteger.class, 1);
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((maxPriority == null) ? 0 : maxPriority.hashCode());
            result = PRIME * result + ((minPriority == null) ? 0 : minPriority.hashCode());
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
            final PriorityFilter other = (PriorityFilter) obj;
            if (maxPriority == null) {
                if (other.maxPriority != null)
                    return false;
            }
            else if (!maxPriority.equals(other.maxPriority))
                return false;
            if (minPriority == null) {
                if (other.minPriority != null)
                    return false;
            }
            else if (!minPriority.equals(other.minPriority))
                return false;
            return true;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((acknowledgmentFilter == null) ? 0 : acknowledgmentFilter.hashCode());
        result = PRIME * result + ((enrollmentFilter == null) ? 0 : enrollmentFilter.hashCode());
        result = PRIME * result + ((eventStateFilter == null) ? 0 : eventStateFilter.hashCode());
        result = PRIME * result + ((eventTypeFilter == null) ? 0 : eventTypeFilter.hashCode());
        result = PRIME * result + ((notificationClassFilter == null) ? 0 : notificationClassFilter.hashCode());
        result = PRIME * result + ((priorityFilter == null) ? 0 : priorityFilter.hashCode());
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
        final GetEnrollmentSummaryRequest other = (GetEnrollmentSummaryRequest) obj;
        if (acknowledgmentFilter == null) {
            if (other.acknowledgmentFilter != null)
                return false;
        }
        else if (!acknowledgmentFilter.equals(other.acknowledgmentFilter))
            return false;
        if (enrollmentFilter == null) {
            if (other.enrollmentFilter != null)
                return false;
        }
        else if (!enrollmentFilter.equals(other.enrollmentFilter))
            return false;
        if (eventStateFilter == null) {
            if (other.eventStateFilter != null)
                return false;
        }
        else if (!eventStateFilter.equals(other.eventStateFilter))
            return false;
        if (eventTypeFilter == null) {
            if (other.eventTypeFilter != null)
                return false;
        }
        else if (!eventTypeFilter.equals(other.eventTypeFilter))
            return false;
        if (notificationClassFilter == null) {
            if (other.notificationClassFilter != null)
                return false;
        }
        else if (!notificationClassFilter.equals(other.notificationClassFilter))
            return false;
        if (priorityFilter == null) {
            if (other.priorityFilter != null)
                return false;
        }
        else if (!priorityFilter.equals(other.priorityFilter))
            return false;
        return true;
    }
}
