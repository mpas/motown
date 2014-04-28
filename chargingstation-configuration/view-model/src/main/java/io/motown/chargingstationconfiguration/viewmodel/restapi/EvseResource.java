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
package io.motown.chargingstationconfiguration.viewmodel.restapi;

import io.motown.chargingstationconfiguration.viewmodel.domain.DomainService;
import io.motown.chargingstationconfiguration.viewmodel.persistence.entities.Evse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/chargingstationtypes/{chargingStationTypeId: [0-9]+}/evses")
@Produces(ApiVersion.V1_JSON)
public final class EvseResource {

    private DomainService domainService;

    @GET
    public Response getEvses(@PathParam("chargingStationTypeId") Long chargingStationTypeId) {
        return Response.ok(domainService.getEvses(chargingStationTypeId)).build();
    }

    @POST
    @Consumes(ApiVersion.V1_JSON)
    public Response createEvse(@PathParam("chargingStationTypeId") Long chargingStationTypeId, Evse evse) {
        return Response.status(Response.Status.CREATED).entity(domainService.createEvse(chargingStationTypeId, evse)).build();
    }

    @PUT
    @Path("/{id: [0-9]+}")
    @Consumes(ApiVersion.V1_JSON)
    public Response updateEvse(@PathParam("chargingStationTypeId") Long chargingStationTypeId, @PathParam("id") Long id, Evse evse) {
        return Response.ok(domainService.updateEvse(chargingStationTypeId, id, evse)).build();
    }

    @GET
    @Path("/{id: [0-9]+}")
    public Response getEvse(@PathParam("chargingStationTypeId") Long chargingStationTypeId, @PathParam("id") Long id) {
        return Response.ok(domainService.getEvse(chargingStationTypeId, id)).build();
    }

    @DELETE
    @Path("/{id: [0-9]+}")
    public Response deleteEvse(@PathParam("id") Long id) {
        domainService.deleteEvse(id);
        return Response.ok(id).build();
    }

    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }
}
