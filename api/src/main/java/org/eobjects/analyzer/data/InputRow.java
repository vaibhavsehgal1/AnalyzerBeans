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
package org.eobjects.analyzer.data;

import java.io.Serializable;
import java.util.List;

import org.eobjects.analyzer.beans.api.Transformer;

/**
 * Represents a row of data where each value pertain to a column.
 * 
 * An InputRow can contain both values that are physical (ie. a raw output from
 * a datastore) and virtual (ie. generated values, created by Transformers).
 * 
 * The contents of an InputRow is visualized in the image below:
 * 
 * <img src="doc-files/AnalyzerBeans-inputrow.png" alt="InputRow contents" />
 * 
 * @see Transformer
 * @see InputColumn
 */
public interface InputRow extends Serializable {

    /**
     * Gets a value from the row on a given column position, or null if no value
     * exists at this column position.
     * 
     * @param <E>
     * @param column
     * @return
     */
    public <E> E getValue(InputColumn<E> column);

    /**
     * An id identifying this row. The id is guaranteed to be unique (and
     * typically sequential) within a single dataset only.
     * 
     * @return an identifier for this row
     */
    public int getId();

    /**
     * @return the input columns represented in this row
     */
    public List<InputColumn<?>> getInputColumns();

    /**
     * Determines whether a particular {@link InputColumn} is mapped within the
     * row or not.
     * 
     * @param inputColumn
     * @return true if the input column is mapped in this input row
     */
    public boolean containsInputColumn(InputColumn<?> inputColumn);

    /**
     * Gets multiple values in one go. Will delegate to
     * {@link #getValue(InputColumn)} for each column in the array.
     * 
     * @param columns
     * @return
     */
    public List<Object> getValues(InputColumn<?>... columns);

    /**
     * Gets multiple values in one go. Will delegate to
     * {@link #getValue(InputColumn)} for each column in the list.
     * 
     * @param columns
     * @return
     */
    public List<Object> getValues(List<InputColumn<?>> columns);
}
