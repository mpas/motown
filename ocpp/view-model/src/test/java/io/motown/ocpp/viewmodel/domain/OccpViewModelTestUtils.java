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
package io.motown.ocpp.viewmodel.domain;

import com.google.common.collect.ImmutableMap;
import io.motown.domain.api.chargingstation.*;
import io.motown.ocpp.viewmodel.persistence.entities.ChargingStation;
import org.axonframework.domain.EventMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.motown.domain.api.chargingstation.ChargingStationTestUtils.*;

public final class OccpViewModelTestUtils {

    public static final String CHARGING_STATION_ADDRESS = "127.0.0.1";
    public static final int NUMBER_OF_RETRIES = 3;
    public static final int RETRY_INTERVAL = 20;
    public static final int LIST_VERSION = 1;

    private OccpViewModelTestUtils() {
        // Private no-arg constructor to prevent instantiation of utility class.
    }

    public static ChargingStation getRegisteredAndConfiguredChargingStation() {
        ChargingStation cs = new ChargingStation(CHARGING_STATION_ID.getId(), CHARGING_STATION_ADDRESS);
        cs.setRegistered(true);
        cs.setNumberOfEvses(EVSES.size());
        cs.setConfigured(true);

        return cs;
    }

    public static String getVendor() {
        return "Mowotn";
    }

    public static EvseId getChargingStationComponentId() {
        return new EvseId(DomainService.CHARGING_STATION_EVSE_ID);
    }

    public static String getConfigurationKey() {
        return "configKey";
    }

    public static String getConfigurationValue() {
        return "configValue";
    }

    public static String getFirmwareUpdateLocation() {
        return "ftp://test";
    }

    public static Map<String, String> getUpdateFirmwareAttributes(String numberOfRetries, String retryInterval) {
        return ImmutableMap.<String, String>builder()
                .put("NUM_RETRIES", numberOfRetries)
                .put("RETRY_INTERVAL", retryInterval)
                .build();
    }

    public static List<IdentifyingToken> getAuthorizationList() {
        List<IdentifyingToken> list = new ArrayList<>();
        list.add(new TextualToken("1"));
        list.add(new TextualToken("2"));
        return list;
    }

    public static Integer getAuthorizationListVersion() {
        return 1;
    }

    public static String getAuthorizationListHash() {
        return "hash";
    }

    public static AuthorizationListUpdateType getAuthorizationListUpdateType() {
        return AuthorizationListUpdateType.DIFFERENTIAL;
    }

    public static ReservationStatus getReservationStatus() {
        return ReservationStatus.UNAVAILABLE;
    }

    public static String getChargingStationSerialNumber() {
        return "serialNumber";
    }

    public static String getFirmwareVersion() {
        return "firmwareVersion";
    }

    public static String getIccid() {
        return "iccid";
    }

    public static String getImsi() {
        return "imsi";
    }

    public static String getMeterType() {
        return "meterType";
    }

    public static String getMeterSerialNumber() {
        return "meterSerialNumber";
    }

    public static Map<String, String> getEmptyAttributesMap() {
        return ImmutableMap.<String, String>of();
    }

    public static Map<String, String> getStartTransactionAttributesMap(int reservationId) {
        return ImmutableMap.<String, String>builder()
                .put("reservationId", new NumberedReservationId(CHARGING_STATION_ID, PROTOCOL, reservationId).getId())
                .build();
    }

    public static List<MeterValue> getEmptyMeterValuesList() {
        return new ArrayList<MeterValue>();
    }

    public static String getDiagnosticsFileName() {
        return "file.txt";
    }

    public static String getStatusNotifactionErrorCode() {
        return "ConnectorLockFailure";
    }

    public static String getStatusNotificationInfo() {
        return "Lock failed";
    }

    public static String getVendorErrorCode() {
        return "001";
    }

    public static FutureEventCallback getFutureEventCallback() {
        return new FutureEventCallback() {
            @Override
            public boolean onEvent(EventMessage<?> event) {
                return false;
            }
        };
    }
}