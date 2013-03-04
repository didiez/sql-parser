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

public class AlterTableRenameColumnNode extends TableElementNode
{
    private String oldName;     // old column name
    private String newName;     // new column name
    
    @Override
    public void init(Object oldN, Object newN)
    {
        oldName = (String) oldN;
        newName = (String) newN;
        super.init(oldName, ElementType.AT_RENAME_COLUMN);
    }
    
    @Override
    public void copyFrom(QueryTreeNode node) throws StandardException
    {
        super.copyFrom(node);
        oldName = ((AlterTableRenameColumnNode)node).oldName;
        newName = ((AlterTableRenameColumnNode)node).newName;
    }
    
    public String newName()
    {
        return newName;
    }
}
