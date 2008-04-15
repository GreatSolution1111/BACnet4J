package com.serotonin.bacnet4j.service.acknowledgement;

import java.util.ArrayList;
import java.util.List;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.OctetString;
import com.serotonin.bacnet4j.type.primitive.SignedInteger;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class AtomicReadFileAck extends AcknowledgementService {
    public static final byte TYPE_ID = 6;
    
    private Boolean endOfFile;
    private SignedInteger fileStartPosition;
    private OctetString fileData;
    private UnsignedInteger returnedRecordCount;
    private SequenceOf<OctetString> fileRecordData;
    
    public AtomicReadFileAck(Boolean endOfFile, SignedInteger fileStartPosition, OctetString fileData) {
        super();
        this.endOfFile = endOfFile;
        this.fileStartPosition = fileStartPosition;
        this.fileData = fileData;
    }

    public AtomicReadFileAck(Boolean endOfFile, SignedInteger fileStartPosition, UnsignedInteger returnedRecordCount, 
            SequenceOf<OctetString> fileRecordData) {
        super();
        this.endOfFile = endOfFile;
        this.fileStartPosition = fileStartPosition;
        this.returnedRecordCount = returnedRecordCount;
        this.fileRecordData = fileRecordData;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }
    
    @Override
    public void write(ByteQueue queue) {
        write(queue, endOfFile);
        if (fileData != null) {
            writeContextTag(queue, 0, true);
            write(queue, fileStartPosition);
            write(queue, fileData);
            writeContextTag(queue, 0, false);
        }
        else {
            writeContextTag(queue, 1, true);
            write(queue, fileStartPosition);
            write(queue, returnedRecordCount);
            write(queue, fileRecordData);
            writeContextTag(queue, 1, false);
        }
    }
    
    AtomicReadFileAck(ByteQueue queue) throws BACnetException {
        endOfFile = read(queue, Boolean.class);
        if (popStart(queue) == 0) {
            fileStartPosition = read(queue, SignedInteger.class);
            fileData = read(queue, OctetString.class);
            popEnd(queue, 0);
        }
        else {
            fileStartPosition = read(queue, SignedInteger.class);
            returnedRecordCount = read(queue, UnsignedInteger.class);
            List<OctetString> records = new ArrayList<OctetString>();
            for (int i=0; i<returnedRecordCount.intValue(); i++)
                records.add(read(queue, OctetString.class));
            fileRecordData = new SequenceOf<OctetString>(records);
            popEnd(queue, 1);
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((endOfFile == null) ? 0 : endOfFile.hashCode());
        result = PRIME * result + ((fileData == null) ? 0 : fileData.hashCode());
        result = PRIME * result + ((fileRecordData == null) ? 0 : fileRecordData.hashCode());
        result = PRIME * result + ((fileStartPosition == null) ? 0 : fileStartPosition.hashCode());
        result = PRIME * result + ((returnedRecordCount == null) ? 0 : returnedRecordCount.hashCode());
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
        final AtomicReadFileAck other = (AtomicReadFileAck) obj;
        if (endOfFile == null) {
            if (other.endOfFile != null)
                return false;
        }
        else if (!endOfFile.equals(other.endOfFile))
            return false;
        if (fileData == null) {
            if (other.fileData != null)
                return false;
        }
        else if (!fileData.equals(other.fileData))
            return false;
        if (fileRecordData == null) {
            if (other.fileRecordData != null)
                return false;
        }
        else if (!fileRecordData.equals(other.fileRecordData))
            return false;
        if (fileStartPosition == null) {
            if (other.fileStartPosition != null)
                return false;
        }
        else if (!fileStartPosition.equals(other.fileStartPosition))
            return false;
        if (returnedRecordCount == null) {
            if (other.returnedRecordCount != null)
                return false;
        }
        else if (!returnedRecordCount.equals(other.returnedRecordCount))
            return false;
        return true;
    }
}
