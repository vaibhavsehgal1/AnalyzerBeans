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
package org.eobjects.analyzer.beans.convert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import junit.framework.TestCase;

public class ConvertToStringTransformerTest extends TestCase {

	public void testTransformValue() throws Exception {
		assertEquals("hello\nworld", ConvertToStringTransformer.transformValue("hello\nworld"));

		assertEquals("w00p\nw0000p",
				ConvertToStringTransformer.transformValue(new ByteArrayInputStream("w00p\nw0000p".getBytes())));

		assertEquals("mrr\nrh", ConvertToStringTransformer.transformValue(new StringReader("mrr\nrh")));
	}

	public void testTransformInputStream() throws Exception {
		InputStream inputStream = new ByteArrayInputStream("hello\nworld".getBytes());
		assertEquals("hello\nworld", ConvertToStringTransformer.transformValue(inputStream));

		inputStream = new ByteArrayInputStream("hello\r\nworld\n".getBytes());
		assertEquals("hello\r\nworld\n", ConvertToStringTransformer.transformValue(inputStream));

		// make a string that will not fit into the buffer being used in the
		// converter
		StringBuilder longString = new StringBuilder("hello");
		for (int i = 0; i < 1024; i++) {
			longString.append(" hello");
		}

		inputStream = new ByteArrayInputStream(longString.toString().getBytes());
		assertEquals(longString.toString(), ConvertToStringTransformer.transformValue(inputStream));
	}
}
