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

package org.apache.shardingsphere.elasticjob.lite.tracing.rdb.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shardingsphere.elasticjob.lite.tracing.exception.TracingConfigurationException;
import org.apache.shardingsphere.elasticjob.lite.tracing.rdb.listener.RDBTracingListener;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class RDBTracingConfigurationTest {
    
    @Test
    public void assertGetDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:job_event_storage");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        assertThat(new RDBTracingConfiguration(dataSource).getDataSource(), is(dataSource));
    }
    
    @Test
    public void assertCreateTracingListenerSuccess() throws TracingConfigurationException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:job_event_storage");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        assertThat(new RDBTracingConfiguration(dataSource).createTracingListener(), instanceOf(RDBTracingListener.class));
    }
    
    @Test(expected = TracingConfigurationException.class)
    public void assertCreateTracingListenerFailure() throws TracingConfigurationException {
        new RDBTracingConfiguration(new BasicDataSource()).createTracingListener();
    }
}
