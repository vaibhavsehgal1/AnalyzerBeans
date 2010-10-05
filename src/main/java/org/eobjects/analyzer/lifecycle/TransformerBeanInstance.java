package org.eobjects.analyzer.lifecycle;

import org.eobjects.analyzer.beans.api.Transformer;
import org.eobjects.analyzer.descriptors.TransformerBeanDescriptor;

public class TransformerBeanInstance extends AbstractBeanInstance<Transformer<?>> {

	public TransformerBeanInstance(TransformerBeanDescriptor<?> descriptor) {
		super(descriptor);
	}
}
