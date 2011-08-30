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

import java.util.Arrays;

import org.eobjects.metamodel.schema.Schema;
import org.eobjects.metamodel.schema.Table;

import junit.framework.TestCase;

public class FixedWidthDatastoreTest extends TestCase {

	public void testSimpleGetters() throws Exception {
		FixedWidthDatastore ds = new FixedWidthDatastore("name", "filename", "encoding", 5);
		assertEquals("name", ds.getName());
		assertEquals("filename", ds.getFilename());
		assertEquals("encoding", ds.getEncoding());
		assertEquals(5, ds.getFixedValueWidth());

		assertFalse(ds.getPerformanceCharacteristics().isQueryOptimizationPreferred());
	}

	public void testToString() throws Exception {
		FixedWidthDatastore ds = new FixedWidthDatastore("name", "filename", "encoding", 5);
		assertEquals(
				"FixedWidthDatastore[name=name, filename=filename, encoding=encoding, valueWidths=[], fixedValueWidth=5]",
				ds.toString());
	}

	public void testGetDataContextProvider() throws Exception {
		FixedWidthDatastore ds = new FixedWidthDatastore("example datastore",
				"src/test/resources/employees-fixed-width.txt", "UTF-8", 19, false);

		DataContextProvider dcp = ds.getDataContextProvider();

		Schema schema = dcp.getDataContext().getDefaultSchema();
		assertEquals("employees-fixed-width.txt", schema.getName());

		Table table = schema.getTables()[0];
		assertEquals("employees-fixed-width", table.getName());

		assertEquals("[name, email]", Arrays.toString(table.getColumnNames()));

		dcp.close();
	}
}