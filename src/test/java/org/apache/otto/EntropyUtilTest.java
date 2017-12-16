/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.otto;

import java.util.HashMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple EntropyUtil.
 */
public class EntropyUtilTest extends TestCase {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public EntropyUtilTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(EntropyUtilTest.class);
  }

  /**
   * Test that we get the same result calling the stream or the looping version of the function
   */
  public void testEntropyUtilStreamAndForEqual() {
    HashMap map = new HashMap() {{
      put("a", 10);
      put("b", 5);
      put("c", 5);
    }};
    assertEquals(EntropyUtil.entropy(map, Math.log(2)),
        EntropyUtil.stream_entropy(map, Math.log(2)));
  }
}
