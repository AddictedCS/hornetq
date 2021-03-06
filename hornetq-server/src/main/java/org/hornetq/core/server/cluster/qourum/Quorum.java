/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.core.server.cluster.qourum;

import org.hornetq.core.client.impl.Topology;

/**
 * A quorum can be registered with the @link QuorumManager to receive notifications about the state of a cluster.
 * It can then use the {@link QuorumManager} for the quorum within a cluster to vote on a specific outcome.
 * */
public interface Quorum
{
   /**
   * the name of the Quorum. this should be unique and is used to locate the correct quorum to use for voting
   * */
   String getName();

   /**
   * called by the quorum manager when a quorum is registered
   * */
   void setQuorumManager(QuorumManager quorumManager);

   /**
   * called by the quorum when a node in the quorum goes down
   * */
   void nodeDown(Topology topology, long eventUID, String nodeID);

  /**
  * called by the quorum when a node in the quorum goes up
  * */
   void nodeUp(Topology topology);

   /**
   * A quorum can register to receive notifications when a specific connection goes down, at this point this method is called.
   * see {@link QuorumManager#addAsFailureListenerOf(org.hornetq.core.protocol.core.CoreRemotingConnection, Quorum)}
   * */
   void connectionFailed(Topology topology);

   /**
    * called if the quorum manager is stopping so we can clean up
    */
   void close();
}
