package org.eobjects.analyzer.beans.coalesce;

import junit.framework.TestCase;

import org.eobjects.analyzer.data.MockInputColumn;
import org.eobjects.analyzer.data.MockInputRow;

public class CoalesceNumbersTransformerTest extends TestCase {

	public void testTransform() throws Exception {
		MockInputColumn<Number> col1 = new MockInputColumn<Number>("col1", Number.class);
		MockInputColumn<Number> col2 = new MockInputColumn<Number>("col2", Number.class);
		MockInputColumn<Number> col3 = new MockInputColumn<Number>("col3", Number.class);

		@SuppressWarnings("unchecked")
		CoalesceNumbersTransformer t = new CoalesceNumbersTransformer(col1, col2, col3);
		assertEquals(1, t.getOutputColumns().getColumnCount());

		assertEquals(1, t.transform(new MockInputRow().put(col2, 1).put(col3, 2))[0]);
		assertEquals(1, t.transform(new MockInputRow().put(col2, 2).put(col1, 1))[0]);
		assertEquals(54, t.transform(new MockInputRow().put(col2, 0).put(col1, 54))[0]);

		assertNull(t.transform(new MockInputRow().put(col2, null).put(col1, null))[0]);
	}
}
