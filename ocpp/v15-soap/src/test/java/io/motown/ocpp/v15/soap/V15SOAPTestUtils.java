/**
 * Copyright (C) 2013 Motown.IO (info@motown.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.motown.ocpp.v15.soap;

import io.motown.domain.api.chargingstation.MeterValue;
import io.motown.ocpp.v15.soap.centralsystem.schema.TransactionData;
import io.motown.ocpp.v15.soap.chargepoint.schema.*;

import java.util.*;

import static io.motown.domain.api.chargingstation.test.ChargingStationTestUtils.FIVE_MINUTES_AGO;

public final class V15SOAPTestUtils {

    public static final String CONFIGURATION_KEY = "HEARTBEATINTERVAL";

    public static final String CONFIGURATION_VALUE = "900";

    public static final String DIAGNOSTICS_FILENAME = "diagnostics.txt";

    public static final String UPLOAD_LOCATION = "FTP://test";

    public static final Integer NUMBER_OF_RETRIES = 3;

    public static final Integer RETRY_INTERVAL = 60;

    public static final Date PERIOD_START_TIME = getFixedDate();

    public static final Date PERIOD_STOP_TIME = getFixedDate();

    public static final String DOWNLOAD_LOCATION = "FTP://test";

    public static final Date RETRIEVED_DATE = getFixedDate();

    public static final Date EXPIRY_DATE = getFixedDate();

    public static final String AUTH_LIST_HASH = "hash";

    public static final int LIST_VERSION = 3;

    public static final String STATUS_NOTIFICATION_ERROR_INFO = "error info";

    public static final String STATUS_NOTIFICATION_VENDOR_ERROR_CODE = "007";

    /**
     * Private no-arg constructor to prevent instantiation of utility class.
     */
    private V15SOAPTestUtils() {
    }

    public static GetConfigurationResponse getGetConfigurationResponse() {
        GetConfigurationResponse response = new GetConfigurationResponse();

        response.getConfigurationKey().add(getKeyValue("HeartbeatInterval", "900"));
        response.getConfigurationKey().add(getKeyValue("ConnectionTimeOut", "1200"));
        response.getConfigurationKey().add(getKeyValue("ResetRetries", "5"));

        return response;
    }

    public static KeyValue getKeyValue(String key, String value) {
        KeyValue keyValue = new KeyValue();
        keyValue.setKey(key);
        keyValue.setValue(value);
        return keyValue;
    }

    public static RemoteStartTransactionResponse getRemoteStartTransactionResponse(RemoteStartStopStatus status) {
        RemoteStartTransactionResponse response = new RemoteStartTransactionResponse();
        response.setStatus(status);
        return response;
    }

    public static RemoteStopTransactionResponse getRemoteStopTransactionResponse(RemoteStartStopStatus status) {
        RemoteStopTransactionResponse response = new RemoteStopTransactionResponse();
        response.setStatus(status);
        return response;
    }

    public static ResetResponse getResetResponse(ResetStatus status) {
        ResetResponse response = new ResetResponse();
        response.setStatus(status);
        return response;
    }

    public static UnlockConnectorResponse getUnlockConnectorResponse(UnlockStatus status) {
        UnlockConnectorResponse response = new UnlockConnectorResponse();
        response.setStatus(status);
        return response;
    }

    public static ChangeAvailabilityResponse getChangeAvailabilityResponse(AvailabilityStatus status) {
        ChangeAvailabilityResponse response = new ChangeAvailabilityResponse();
        response.setStatus(status);
        return response;
    }

    public static DataTransferResponse getDataTransferResponse(DataTransferStatus status) {
        DataTransferResponse response = new DataTransferResponse();
        response.setStatus(status);
        return response;
    }

    public static ChangeConfigurationResponse getChangeConfigurationResponse(ConfigurationStatus status) {
        ChangeConfigurationResponse response = new ChangeConfigurationResponse();
        response.setStatus(status);
        return response;
    }

    public static GetDiagnosticsResponse getGetDiagnosticsResponse(String filename) {
        GetDiagnosticsResponse response = new GetDiagnosticsResponse();
        response.setFileName(filename);
        return response;
    }

    public static ClearCacheResponse getClearCacheResponse(ClearCacheStatus status) {
        ClearCacheResponse response = new ClearCacheResponse();
        response.setStatus(status);
        return response;
    }

    public static GetLocalListVersionResponse getGetLocalListVersionResponse(int listVersion) {
        GetLocalListVersionResponse response = new GetLocalListVersionResponse();
        response.setListVersion(listVersion);
        return response;
    }

    public static SendLocalListResponse getSendLocalListResponse(UpdateStatus status) {
        SendLocalListResponse response = new SendLocalListResponse();
        response.setStatus(status);
        return response;
    }

    public static ReserveNowResponse getReserveNowResponse(ReservationStatus status) {
        ReserveNowResponse response = new ReserveNowResponse();
        response.setStatus(status);
        return response;
    }

    public static List<TransactionData> getTransactionDataForMeterValues(List<MeterValue> meterValues) {
        List<TransactionData> transactionData = new ArrayList<>();

        for (MeterValue meterValue : meterValues) {
            io.motown.ocpp.v15.soap.centralsystem.schema.MeterValue.Value value = new io.motown.ocpp.v15.soap.centralsystem.schema.MeterValue.Value();
            value.setValue(meterValue.getValue());

            io.motown.ocpp.v15.soap.centralsystem.schema.MeterValue meterValueSoap = new io.motown.ocpp.v15.soap.centralsystem.schema.MeterValue();
            meterValueSoap.getValue().add(value);
            meterValueSoap.setTimestamp(FIVE_MINUTES_AGO);

            TransactionData data = new TransactionData();
            data.getValues().add(meterValueSoap);

            transactionData.add(data);
        }

        return transactionData;
    }

    /**
     * Creates a fixed date so it can be compared to an instance of this method created later in time.
     *
     * @return fixed date.
     */
    private static Date getFixedDate() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

}
