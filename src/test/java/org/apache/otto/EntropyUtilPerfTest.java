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

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(1)
public class EntropyUtilPerfTest {
  public static final int N = 10000;
  private static Random rng = new Random(0);


  static List<Map<?, Integer>> inputData = new ArrayList<Map<?, Integer>>(){{
    for(int i = 0;i < N;++i) {
      String randomString = RandomStringUtils.randomAlphanumeric(100, 200);
      add(stringToCount(randomString));
    }
  }};
  public static Map<String, Integer> stringToCount(String s) {
    Map<String, Integer> ret = new HashMap<>();
    for(int i = 0;i < s.length();++i) {
      String key = "" + s.charAt(i);
      ret.put(key, ret.getOrDefault(key, 0) + 1);
    }
    return ret;
  }

  static Map<?, Integer> getData() {
    return inputData.get(rng.nextInt(inputData.size()));
  }

  static Map<?, Integer> DATA = inputData.get(rng.nextInt(inputData.size()));

  @Benchmark
  public void nonStream() {
    EntropyUtil.entropy(getData(), 2);
  }

  @Benchmark
  public void stream() {
    EntropyUtil.stream_entropy(getData(), 2);
  }

  @Benchmark
  public void nonStreamPreFetch() {
    EntropyUtil.entropy(DATA, 2);
  }

  @Benchmark
  public void streamPreFetch() {
    EntropyUtil.stream_entropy(DATA, 2);
  }


}
