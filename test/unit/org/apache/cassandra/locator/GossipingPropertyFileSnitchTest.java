/*
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

package org.apache.cassandra.locator;

import java.net.InetAddress;

import com.google.common.net.InetAddresses;
import org.junit.Test;

import org.apache.cassandra.utils.FBUtilities;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link GossipingPropertyFileSnitch}.
 */
public class GossipingPropertyFileSnitchTest
{
    public static void checkEndpoint(final AbstractNetworkTopologySnitch snitch,
                                     final String endpointString, final String expectedDatacenter,
                                     final String expectedRack)
    {
        final InetAddress endpoint = InetAddresses.forString(endpointString);
        assertEquals(expectedDatacenter, snitch.getDatacenter(endpoint));
        assertEquals(expectedRack, snitch.getRack(endpoint));
    }

    @Test
    public void testLoadConfig() throws Exception
    {
        final GossipingPropertyFileSnitch snitch = new GossipingPropertyFileSnitch();
        checkEndpoint(snitch, FBUtilities.getBroadcastAddress().getHostAddress(), "DC1", "RAC1");
    }
}
