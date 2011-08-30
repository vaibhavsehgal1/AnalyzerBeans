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
package org.eobjects.analyzer.descriptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.eobjects.analyzer.beans.api.Categorized;
import org.eobjects.analyzer.beans.api.ComponentCategory;
import org.eobjects.analyzer.beans.api.Configured;
import org.eobjects.analyzer.beans.api.Description;
import org.eobjects.analyzer.beans.api.Provided;
import org.eobjects.analyzer.util.ReflectionUtils;

/**
 * Abstract implementation of the {@link BeanDescriptor} interface. Convenient
 * for implementing it's subclasses.
 * 
 * @author Kasper Sørensen
 * 
 * @param <B>
 */
abstract class AbstractBeanDescriptor<B> extends SimpleComponentDescriptor<B> implements BeanDescriptor<B> {

	protected final Set<ProvidedPropertyDescriptor> _providedProperties;
	private final boolean _requireInputColumns;

	public AbstractBeanDescriptor(Class<B> beanClass, boolean requireInputColumns) {
		super(beanClass);
		_providedProperties = new TreeSet<ProvidedPropertyDescriptor>();
		_requireInputColumns = requireInputColumns;
	}

	@Override
	protected void visitClass() {
		super.visitClass();

		if (_requireInputColumns) {
			int numConfiguredColumns = 0;
			int numConfiguredColumnArrays = 0;
			for (ConfiguredPropertyDescriptor cd : _configuredProperties) {
				if (cd.isInputColumn()) {
					if (cd.isArray()) {
						numConfiguredColumnArrays++;
					} else {
						numConfiguredColumns++;
					}
				}
			}
			final int totalColumns = numConfiguredColumns + numConfiguredColumnArrays;
			if (totalColumns == 0) {
				throw new DescriptorException(getComponentClass()
						+ " does not define a @Configured InputColumn or InputColumn-array");
			}
		}
	}

	@Override
	protected void visitField(Field field) {
		super.visitField(field);

		Inject injectAnnotation = field.getAnnotation(Inject.class);
		Configured configuredAnnotation = field.getAnnotation(Configured.class);
		Provided providedAnnotation = field.getAnnotation(Provided.class);

		if (configuredAnnotation == null && (injectAnnotation != null || providedAnnotation != null)) {
			// provided properties = @Inject or @Provided, and NOT @Configured
			_providedProperties.add(new ProvidedPropertyDescriptorImpl(field, this));
		}

		if (configuredAnnotation != null && providedAnnotation != null) {
			throw new DescriptorException("The field " + field
					+ " is annotated with both @Configured and @Provided, which are mutually exclusive.");
		}
	}

	@Override
	public Set<ConfiguredPropertyDescriptor> getConfiguredPropertiesForInput() {
		return getConfiguredPropertiesForInput(true);
	}

	@Override
	public Set<ConfiguredPropertyDescriptor> getConfiguredPropertiesForInput(boolean includeOptional) {
		Set<ConfiguredPropertyDescriptor> descriptors = new TreeSet<ConfiguredPropertyDescriptor>(_configuredProperties);
		for (Iterator<ConfiguredPropertyDescriptor> it = descriptors.iterator(); it.hasNext();) {
			ConfiguredPropertyDescriptor propertyDescriptor = it.next();
			if (!propertyDescriptor.isInputColumn()) {
				it.remove();
			} else if (!includeOptional && !propertyDescriptor.isRequired()) {
				it.remove();
			}
		}
		return descriptors;
	}

	@Override
	public String getDescription() {
		Description description = getAnnotation(Description.class);
		if (description == null) {
			return null;
		}
		return description.value();
	}

	@Override
	public Set<ProvidedPropertyDescriptor> getProvidedProperties() {
		return Collections.unmodifiableSet(_providedProperties);
	}

	@Override
	public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
		return getComponentClass().getAnnotation(annotationClass);
	}

	@Override
	public Set<Annotation> getAnnotations() {
		Annotation[] annotations = getComponentClass().getAnnotations();
		return new HashSet<Annotation>(Arrays.asList(annotations));
	}

	@Override
	public Set<ComponentCategory> getComponentCategories() {
		Categorized categorized = getAnnotation(Categorized.class);
		if (categorized == null) {
			return Collections.emptySet();
		}
		Class<? extends ComponentCategory>[] value = categorized.value();
		if (value == null || value.length == 0) {
			return Collections.emptySet();
		}

		Set<ComponentCategory> result = new HashSet<ComponentCategory>();
		for (Class<? extends ComponentCategory> categoryClass : value) {
			ComponentCategory category = ReflectionUtils.newInstance(categoryClass);
			result.add(category);
		}

		return result;
	}
}