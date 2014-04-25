/**
 * eobjects.org AnalyzerBeans
 * Copyright (C) 2010 eobjects.org
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.eobjects.analyzer.connection;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.metamodel.DataContext;
import org.junit.Test;

public class HBaseDatastoreTest {

    @Test
    public void testGetDatastoreConnection() {
        HBaseDatastore ds = new HBaseDatastore("foobar", "127.0.0.1", 2181);
        assertEquals("foobar", ds.getName());
        
        DatastoreConnection con = ds.openConnection();
        DataContext dataContext = con.getDataContext();
        
        assertEquals("[information_schema, HBase]", Arrays.toString(dataContext.getSchemaNames()));
    }

}
