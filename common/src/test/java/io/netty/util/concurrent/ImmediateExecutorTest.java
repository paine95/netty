/*
 * Copyright 2020 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.netty.util.concurrent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.FutureTask;

import org.junit.Test;

public class ImmediateExecutorTest {

    @Test(expected = NullPointerException.class)
    public void testExecuteNullRunnable() {
        ImmediateExecutor.INSTANCE.execute(null);
    }

    @Test
    public void testExecuteNonNullRunnable() throws Exception {
        FutureTask<Void> task = new FutureTask<Void>(new Runnable() {
            @Override
            public void run() {
                // NOOP
            }
        }, null);
        ImmediateExecutor.INSTANCE.execute(task);
        assertTrue(task.isDone());
        assertFalse(task.isCancelled());
        assertNull(task.get());
    }
}
