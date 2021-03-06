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
package io.motown.domain.api.chargingstation;

import io.motown.domain.api.security.IdentityContext;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code UpdateFirmwareStatusCommand} is the command which is published upon receipt of the status
 * notification on the firmware update process.
 */
public final class UpdateFirmwareStatusCommand {

    @TargetAggregateIdentifier
    private final ChargingStationId chargingStationId;

    private final FirmwareStatus status;

    private final IdentityContext identityContext;

    /**
     * Creates a {@code UpdateDiagnosticsUploadStatusCommand}.
     *
     * @param chargingStationId the identifier of the charging station.
     * @param status            the status of the firmware update.
     * @param identityContext   identity context.
     * @throws NullPointerException if {@code chargingStationId}, {@code status} or {@code identityContext} is {@code null}.
     */
    public UpdateFirmwareStatusCommand(ChargingStationId chargingStationId, FirmwareStatus status, IdentityContext identityContext) {
        this.chargingStationId = checkNotNull(chargingStationId);
        this.status = checkNotNull(status);
        this.identityContext = checkNotNull(identityContext);
    }

    /**
     * Gets the charging station identifier.
     *
     * @return the charging station identifier.
     */
    public ChargingStationId getChargingStationId() {
        return chargingStationId;
    }

    /**
     * @return the firmware status
     */
    public FirmwareStatus getStatus() {
        return status;
    }

    /**
     * Gets the identity context.
     *
     * @return the identity context.
     */
    public IdentityContext getIdentityContext() {
        return identityContext;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargingStationId, status, identityContext);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UpdateFirmwareStatusCommand other = (UpdateFirmwareStatusCommand) obj;
        return Objects.equals(this.chargingStationId, other.chargingStationId) && Objects.equals(this.status, other.status) && Objects.equals(this.identityContext, other.identityContext);
    }
}
