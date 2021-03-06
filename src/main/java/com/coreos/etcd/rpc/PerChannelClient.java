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
package com.coreos.etcd.rpc;

import com.coreos.etcd.proto.KVGrpc;
import com.coreos.etcd.proto.KVGrpc.KVFutureStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * A RPC client that talks to a server only
 */
public class PerChannelClient {

    private final ManagedChannel channel;
    private final KVFutureStub kvRpc;

    PerChannelClient(String host, int port) {
        // use plain text for development now
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    PerChannelClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        kvRpc = KVGrpc.newFutureStub(channel);
    }

}
