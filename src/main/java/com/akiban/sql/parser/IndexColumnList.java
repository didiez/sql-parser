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

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * List of IndexColumns. Also notes application of up to one function to
 * a consecutive list of IndexColumns.
 */
public class IndexColumnList extends QueryTreeNodeList<IndexColumn>
{
    private FunctionApplication functionApplication;

    public static enum FunctionType
    {
        Z_ORDER_LAT_LON, FULL_TEXT
        // ADD MORE AS NEEDED
    }

    private static class FunctionApplication
    {
        public FunctionApplication(FunctionType functionType,
                                   int firstArgumentPosition,
                                   int nArguments)
        {
            this.functionType = functionType;
            this.firstArgumentPosition = firstArgumentPosition;
            this.lastArgumentPosition = firstArgumentPosition + nArguments - 1;
            this.nArguments = nArguments;
        }

        public final FunctionType functionType;
        public final int firstArgumentPosition;
        public final int lastArgumentPosition;
        public final int nArguments;
    }

    public void applyFunction(Object functionType,
                              int firstArgumentPosition,
                              int nArguments) throws StandardException
    {
        if (functionApplication != null) {
            throw new StandardException("Cannot use multiple functions in one index definition");
        }
        functionApplication = new FunctionApplication((FunctionType) functionType,
                                                      firstArgumentPosition,
                                                      nArguments);
    }

    public int firstFunctionArg()
    {
        return
            functionApplication == null
            ? Integer.MAX_VALUE
            : functionApplication.firstArgumentPosition;
    }

    public int lastFunctionArg()
    {
        return
            functionApplication == null
            ? Integer.MIN_VALUE
            : functionApplication.lastArgumentPosition;
    }

    public FunctionType functionType()
    {
        return functionApplication == null ? null : functionApplication.functionType;
    }

    @Override
    public void copyFrom(QueryTreeNode node) throws StandardException
    {
        super.copyFrom(node);
        IndexColumnList that = (IndexColumnList) node;
        this.functionApplication = that.functionApplication;
    }

    @Override
    public String toString()
    {
        return
            functionApplication != null
            ? String.format("\nmethodName: %s\nfirstArg: %s\nlastArg: %s\n",
                            functionApplication.functionType, functionApplication.firstArgumentPosition, functionApplication.lastArgumentPosition)
            : super.toString();
    }
}
