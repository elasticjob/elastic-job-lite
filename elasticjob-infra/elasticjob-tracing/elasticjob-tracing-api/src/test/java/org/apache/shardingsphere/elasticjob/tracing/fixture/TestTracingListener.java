/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.elasticjob.tracing.fixture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.elasticjob.tracing.event.DagJobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.listener.TracingListener;
import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.event.JobStatusTraceEvent;

@RequiredArgsConstructor
public final class TestTracingListener implements TracingListener {
    
    @Getter
    private static volatile boolean executionEventCalled;
    
    private final JobEventCaller jobEventCaller;
    
    @Override
    public void listen(final JobExecutionEvent jobExecutionEvent) {
        jobEventCaller.call();
        executionEventCalled = true;
    }
    
    @Override
    public void listen(final JobStatusTraceEvent jobStatusTraceEvent) {
        jobEventCaller.call();
    }

    @Override
    public void listen(DagJobExecutionEvent dagJobExecutionEvent) {
        jobEventCaller.call();
        executionEventCalled = true;
    }

    /**
     * Set executionEventCalled to false.
     */
    public static void reset() {
        executionEventCalled = false;
    }
}
