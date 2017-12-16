/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.otto;

import static java.util.stream.Collectors.reducing;

import java.util.Map;

public class EntropyUtil {

  /**
   * Computes entropy using for loops
   * @param counts
   * @param logOfBase
   * @return
   */
  public static double entropy(Map<?, Integer> counts, double logOfBase) {
    double ret = 0.0;
    int n = 0;
    if (counts == null || counts.isEmpty()) {
      return ret;
    }
    for (Integer f : counts.values()) {
      n += f;
    }

    for (Integer f : counts.values()) {
      double p = f.doubleValue() / n;
      ret -= p * Math.log(p) / logOfBase;
    }
    return ret;
  }

  /**
   * Computes entropy using java 8 streams
   * @param counts
   * @param logOfBase
   * @return
   */
  public static double stream_entropy(Map<?, Integer> counts, double logOfBase) {
    double ret = 0.0;
    if (counts == null || counts.isEmpty()) {
      return ret;
    }
    final int n = counts.values().stream().mapToInt((d) -> d).sum();
    return counts.values().stream().collect(reducing(0.0, (f) -> f.doubleValue(), (a, b) -> {
      double p = b.doubleValue() / n;
      a -= p * Math.log(p) / logOfBase;
      return a;
    }));
  }
}
