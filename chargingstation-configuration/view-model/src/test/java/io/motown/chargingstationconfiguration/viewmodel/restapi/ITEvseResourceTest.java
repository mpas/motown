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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;
import io.motown.chargingstationconfiguration.viewmodel.domain.TestUtils;
import io.motown.chargingstationconfiguration.viewmodel.persistence.entities.ChargingStationType;
import io.motown.chargingstationconfiguration.viewmodel.persistence.entities.Evse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("classpath:jersey-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ITEvseResourceTest extends JerseyTest {

    private static final int OK = 200;

    private static final int NOT_FOUND = 404;

    private static final String BASE_URI = "http://localhost:9998/config/api/chargingstationtypes/1/evses";

    private Client client;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ITEvseResourceTest() throws TestContainerException {
        super(new GrizzlyWebTestContainerFactory());
    }

    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder()
                .contextParam("contextConfigLocation", "classpath:jersey-test-config.xml")
                .contextListenerClass(ContextLoaderListener.class)
                .contextPath("/config")
                .requestListenerClass(RequestContextListener.class)
                .servletClass(SpringServlet.class)
                .servletPath("/api")
                .initParam("com.sun.jersey.config.property.packages", "io.motown.chargingstationconfiguration.viewmodel.restapi")
                .build();
    }

    @Override
    public Client client() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        return Client.create(config);
    }

    @Before
    public void setUp() {
        this.client = client();
    }

    @Test
    public void testUpdateEvse() {
        ChargingStationType chargingStationType = TestUtils.getChargingStationTypeNonTransient(entityManagerFactory);
        Evse evse = chargingStationType.getEvses().iterator().next();

        evse.setIdentifier(2);

        ClientResponse response = client.resource(BASE_URI)
                .path("/" + evse.getId())
                .type(ApiVersion.V1_JSON)
                .accept(ApiVersion.V1_JSON)
                .put(ClientResponse.class, evse);

        assertEquals(OK, response.getStatus());
        assertEquals(evse.getIdentifier(), response.getEntity(Evse.class).getIdentifier());
    }

    @Test
    public void testGetEvse() {
        ChargingStationType chargingStationType = TestUtils.getChargingStationTypeNonTransient(entityManagerFactory);
        Long evseId = chargingStationType.getEvses().iterator().next().getId();

        ClientResponse response = client.resource(BASE_URI)
                .path("/" + evseId)
                .accept(ApiVersion.V1_JSON)
                .get(ClientResponse.class);

        assertEquals(OK, response.getStatus());
    }

    @Test
    public void testGetEvseNotFound() {
        TestUtils.getChargingStationTypeNonTransient(entityManagerFactory);

        ClientResponse response = client.resource(BASE_URI)
                .path("/999")
                .accept(ApiVersion.V1_JSON)
                .get(ClientResponse.class);

        assertEquals(NOT_FOUND, response.getStatus());
    }

    @Test
    public void testDeleteEvseNotFound() {
        ClientResponse response = client.resource(BASE_URI)
                .path("/999")
                .accept(ApiVersion.V1_JSON)
                .delete(ClientResponse.class);

        assertEquals(NOT_FOUND, response.getStatus());
    }

}
