/*
 * Copyright © 2021 Vector Pro
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
package in.vectorpro.dropwizard.sample;

import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Tag(name = "HelloTag")
@SecuritySchemes({
  @SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "basic"),
  @SecurityScheme(
      type = SecuritySchemeType.OAUTH2,
      flows = @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "/oauth2/auth")))
})
public class SampleResource {

  @GET
  @Path("/hello")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Hello",
      description = "Returns hello",
      tags = {"HelloTag"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "hello",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON,
                  schema = @Schema(implementation = String.class)),
            }),
      })
  public Saying hello() {
    return new Saying("hello");
  }

  @GET
  @Path("/secret")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Secret",
      description = "Returns secret",
      tags = {"HelloTag"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "secret",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON,
                  schema = @Schema(implementation = String.class)),
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Please enter basic credentials or use oauth2 authentication",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON,
                  schema = @Schema(implementation = String.class)),
            }),
      },
      security = {
        @SecurityRequirement(
            name = "basic",
            scopes = {"basic"}),
        @SecurityRequirement(
            name = "oauth2",
            scopes = {"oauth2"})
      })
  public Saying secret(@Parameter(hidden = true) @Auth PrincipalImpl user) {
    return new Saying(String.format("Hi %s! It's a secret message...", user.getName()));
  }
}
