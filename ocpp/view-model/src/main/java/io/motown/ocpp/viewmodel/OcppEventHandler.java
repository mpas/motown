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
package io.motown.ocpp.viewmodel;

import io.motown.domain.api.chargingstation.*;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OcppEventHandler {
    private static final Logger log = LoggerFactory.getLogger(io.motown.ocpp.viewmodel.OcppEventHandler.class);

    @EventHandler
    public void handle(ChargingStationBootedEvent event) {
        log.info("ChargingStationBootedEvent");
    }

    @EventHandler
    public void handle(ChargingStationCreatedEvent event) {
        log.info("ChargingStationCreatedEvent");
    }

    @EventHandler
    public void handle(ConnectorNotFoundEvent event) {
        log.info("ConnectorNotFoundEvent");
    }

    @EventHandler
    public void handle(UnlockConnectorRequestedEvent event) {
        log.info("UnlockConnectorRequestedEvent");
    }

    @EventHandler
    public void handle(ConfigurationRequestedEvent event) {
        log.info("Handling ConfigurationRequestedEvent");
    }
}