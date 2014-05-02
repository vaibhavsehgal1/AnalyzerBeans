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
package org.eobjects.analyzer.connection.placeholder;

import java.util.Arrays;

import junit.framework.TestCase;

import org.apache.metamodel.schema.ColumnType;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.eobjects.analyzer.connection.DatastoreConnection;

public class PlaceholderDatastoreTest extends TestCase {

    public void testQuery() throws Exception {
        PlaceholderDatastore ds = new PlaceholderDatastore("foo", Arrays.asList("bar", "baz"), Arrays.asList(
                ColumnType.VARCHAR, ColumnType.INTEGER));
        DatastoreConnection con = ds.openConnection();
        Schema schema = con.getDataContext().getDefaultSchema();
        assertEquals("Schema[name=schema]", schema.toString());

        Table table = schema.getTable(0);
        assertEquals("Table[name=table,type=null,remarks=null]", table.toString());

        String[] columnNames = table.getColumnNames();
        assertEquals("[bar, baz]", Arrays.toString(columnNames));

        try {
            con.getDataContext().query().from(table).select(columnNames).execute();
            fail("Exception expected - you cannot query a PlaceholderDatastore");
        } catch (UnsupportedOperationException e) {
            assertEquals("You cannot query a PlaceholderDataContext", e.getMessage());
        }

        con.close();
    }
}
