/*
 * Copyright © 2021 Vector Pro (teamvectorpro@googlegroups.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.vectorpro.dropwizard.swagger;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.restassured.RestAssured;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
class DefaultServerWithServerTest extends DropwizardTest {

    private static final DropwizardAppExtension<TestConfiguration> RULE = new DropwizardAppExtension<>(
            TestApplication.class, ResourceHelpers.resourceFilePath("test-default-servers.yaml"));

    public DefaultServerWithServerTest() {
        super(RULE.getLocalPort(), "/");
    }

    @Test
    void serverStarted() {

        RestAssured.expect().statusCode(HttpStatus.OK_200)
                .when()
                .get(Path.from(basePath, "swagger.json"));
    }
}
