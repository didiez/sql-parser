/**
 * Copyright © 2012 Akiban Technologies, Inc.  All rights
 * reserved.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program may also be available under different license terms.
 * For more information, see www.akiban.com or contact
 * licensing@akiban.com.
 *
 * Contributors:
 * Akiban Technologies, Inc.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.IntersectNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * A IntersectOrExceptOrMinusNode represents an INTERSECT or EXCEPT (= MINUS) DML statement.
 *
 */

public class IntersectOrExceptOrMinusNode extends SetOperatorNode
{
    public static enum OpType { 
        INTERSECT("INTERSECT"), 
        EXCEPT("EXCEPT"),
	MINUS("MINUS");

        String operatorName;
        OpType(String operatorName) {
            this.operatorName = operatorName;
        }
    }
    private OpType opType;

    /**
     * Initializer for an IntersectOrExceptOrMinusNode.
     *
     * @param leftResult The ResultSetNode on the left side of this union
     * @param rightResult The ResultSetNode on the right side of this union
     * @param all Whether or not this is an ALL.
     * @param tableProperties Properties list associated with the table
     *
     * @exception StandardException Thrown on error
     */

    public void init(Object opType,
                     Object leftResult,
                     Object rightResult,
                     Object all,
                     Object tableProperties) 
            throws StandardException {
        super.init(leftResult, rightResult, all, tableProperties);
        this.opType = (OpType)opType;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        IntersectOrExceptOrMinusNode other = (IntersectOrExceptOrMinusNode)node;
        this.opType = other.opType;
    }

    public OpType getOpType() {
        return opType;
    }
        
    public String getOperatorName() {
        return opType.operatorName;
    }

}
