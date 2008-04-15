package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetErrorException;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.service.Service;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.enumerated.ErrorClass;
import com.serotonin.bacnet4j.type.enumerated.ErrorCode;
import com.serotonin.util.queue.ByteQueue;

abstract public class ConfirmedRequestService extends Service {
    public static ConfirmedRequestService createConfirmedRequestService(byte type, ByteQueue queue) throws BACnetException {
        if (type == AcknowledgeAlarmRequest.TYPE_ID) // 0
            return new AcknowledgeAlarmRequest(queue);
        if (type == ConfirmedCovNotificationRequest.TYPE_ID) // 1
            return new ConfirmedCovNotificationRequest(queue);
        if (type == ConfirmedEventNotificationRequest.TYPE_ID) // 2
            return new ConfirmedEventNotificationRequest(queue);
        if (type == GetAlarmSummaryRequest.TYPE_ID) // 3
            return new GetAlarmSummaryRequest(queue);
        if (type == GetEnrollmentSummaryRequest.TYPE_ID) // 4
            return new GetEnrollmentSummaryRequest(queue);
        if (type == SubscribeCOVRequest.TYPE_ID) // 5
            return new SubscribeCOVRequest(queue);
        if (type == AtomicReadFileRequest.TYPE_ID) // 6
            return new AtomicReadFileRequest(queue);
        if (type == AtomicWriteFileRequest.TYPE_ID) // 7
            return new AtomicWriteFileRequest(queue);
        if (type == AddListElementRequest.TYPE_ID) // 8
            return new AddListElementRequest(queue);
        if (type == RemoveListElementRequest.TYPE_ID) // 9
            return new RemoveListElementRequest(queue);
        if (type == CreateObjectRequest.TYPE_ID) // 10
            return new CreateObjectRequest(queue);
        if (type == DeleteObjectRequest.TYPE_ID) // 11
            return new DeleteObjectRequest(queue);
        if (type == ReadPropertyRequest.TYPE_ID) // 12
            return new ReadPropertyRequest(queue);
        if (type == ReadPropertyConditionalRequest.TYPE_ID) // 13
            return new ReadPropertyConditionalRequest(queue);
        if (type == ReadPropertyMultipleRequest.TYPE_ID) // 14
            return new ReadPropertyMultipleRequest(queue);
        if (type == WritePropertyRequest.TYPE_ID) // 15
            return new WritePropertyRequest(queue);
        if (type == WritePropertyMultipleRequest.TYPE_ID) // 16
            return new WritePropertyMultipleRequest(queue);
        if (type == DeviceCommunicationControlRequest.TYPE_ID) // 17
            return new DeviceCommunicationControlRequest(queue);
        if (type == ConfirmedPrivateTransferRequest.TYPE_ID) // 18
            return new ConfirmedPrivateTransferRequest(queue);
        if (type == ConfirmedTextMessageRequest.TYPE_ID) // 19
            return new ConfirmedTextMessageRequest(queue);
        if (type == ReinitializeDeviceRequest.TYPE_ID) // 20
            return new ReinitializeDeviceRequest(queue);
        if (type == VtOpenRequest.TYPE_ID) // 21
            return new VtOpenRequest(queue);
        if (type == VtCloseRequest.TYPE_ID) // 22
            return new VtCloseRequest(queue);
        if (type == VtDataRequest.TYPE_ID) // 23
            return new VtDataRequest(queue);
        if (type == AuthenticateRequest.TYPE_ID) // 24
            return new AuthenticateRequest(queue);
        if (type == RequestKeyRequest.TYPE_ID) // 25
            return new RequestKeyRequest(queue);
        if (type == ReadRangeRequest.TYPE_ID) // 26
            return new ReadRangeRequest(queue);
        if (type == LifeSafetyOperationRequest.TYPE_ID) // 27
            return new LifeSafetyOperationRequest(queue);
        if (type == SubscribeCOVPropertyRequest.TYPE_ID) // 28
            return new SubscribeCOVPropertyRequest(queue);
        if (type == GetEventInformation.TYPE_ID) // 29
            return new GetEventInformation(queue);
        
        throw new BACnetErrorException(ErrorClass.device, ErrorCode.serviceRequestDenied);
    }
    
    abstract public AcknowledgementService handle(LocalDevice localDevice, Address from) throws BACnetException;
}
