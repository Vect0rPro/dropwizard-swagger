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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class DropwizardCommonTest {

  protected final int port;
  protected final Path basePath;

  protected DropwizardCommonTest(int port, String basePath) {
    this.port = port;
    this.basePath = Path.from(basePath);
  }

  @BeforeEach
  public void setPort() {
    RestAssured.port = port;
  }

  @Test
  public void resourceIsAvailable() throws Exception {
    RestAssured.expect().statusCode(HttpStatus.OK_200).when().get(Path.from(basePath, "test.json"));
  }

  static class Path {
    private final List<String> pathComponents = new ArrayList<>();

    public static Path from(String basePath) {
      final Path path = new Path();
      path.pathComponents.addAll(Splitter.on("/").omitEmptyStrings().splitToList(basePath));
      return path;
    }

    public static String from(Path basePath, String additionalPath) {
      final List<String> pathComponents = new ArrayList<>();
      pathComponents.addAll(basePath.pathComponents);
      pathComponents.add(additionalPath);
      return asString(pathComponents);
    }

    public static String asString(List<String> pathComponents) {
      return pathComponents.isEmpty() ? "/" : Joiner.on("/").join(pathComponents);
    }
  }
}
