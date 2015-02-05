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
package org.eobjects.analyzer.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eobjects.analyzer.data.InputColumn;
import org.eobjects.analyzer.descriptors.BeanDescriptor;
import org.eobjects.analyzer.job.AnalyzerJob;
import org.eobjects.analyzer.job.ComponentJob;
import org.eobjects.analyzer.job.ComponentRequirement;
import org.eobjects.analyzer.job.ConfigurableBeanJob;
import org.eobjects.analyzer.job.builder.AbstractBeanJobBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for reusable methods and constants that represent user readable
 * labels
 */
public final class LabelUtils {

    private static final Logger logger = LoggerFactory.getLogger(LabelUtils.class);

    public static final String NULL_LABEL = "<null>";
    public static final String UNIQUE_LABEL = "<unique>";
    public static final String BLANK_LABEL = "<blank>";
    public static final String UNEXPECTED_LABEL = "<unexpected>";
    public static final String COUNT_LABEL = "COUNT(*)";

    private LabelUtils() {
        // prevent instantiation
    }

    public static String getLabel(AbstractBeanJobBuilder<?, ?, ?> builder) {
        String label = builder.getName();
        if (StringUtils.isNullOrEmpty(label)) {
            label = builder.getDescriptor().getDisplayName();
        }
        return label;
    }

    /**
     * Gets the label of a component job
     * 
     * @param job
     * @return
     */
    public static String getLabel(ComponentJob job) {
        return getLabel(job, false, true, true);
    }

    /**
     * Gets the label of a components job
     * 
     * @param job
     * @param includeDescriptorName
     * @param includeInputColumnNames
     * @param includeRequirements
     * 
     * @return
     */
    public static String getLabel(ComponentJob job, boolean includeDescriptorName, boolean includeInputColumnNames,
            boolean includeRequirements) {
        String jobName = job.getName();
        StringBuilder label = new StringBuilder();
        if (StringUtils.isNullOrEmpty(jobName)) {
            if (job instanceof ConfigurableBeanJob) {
                BeanDescriptor<?> descriptor = ((ConfigurableBeanJob<?>) job).getDescriptor();
                label.append(descriptor.getDisplayName());
            } else {
                label.append(job.toString());
            }
        } else {
            label.append(jobName);
        }

        if (job instanceof AnalyzerJob) {
            AnalyzerJob analyzerJob = (AnalyzerJob) job;
            if (includeDescriptorName && !StringUtils.isNullOrEmpty(jobName)) {
                label.append(" (");
                label.append(analyzerJob.getDescriptor().getDisplayName());
                label.append(')');
            }

            final InputColumn<?>[] input = analyzerJob.getInput();
            if (input.length == 1) {
                if (input[0].getName().equals(jobName)) {
                    // special case where jobName is the same as the single
                    // input column - in that case we'll leave out the column
                    // name
                    includeInputColumnNames = false;
                }
            }
            if (includeInputColumnNames && input.length > 0) {

                label.append(" (");
                if (input.length < 5) {
                    for (int i = 0; i < input.length; i++) {
                        if (i != 0) {
                            label.append(',');
                        }
                        label.append(input[i].getName());
                    }
                } else {
                    label.append(input.length);
                    label.append(" columns");
                }
                label.append(")");
            }

            final ComponentRequirement requirement = analyzerJob.getComponentRequirement();
            if (includeRequirements && requirement != null) {
                label.append(" (");
                label.append(requirement.toString());
                label.append(")");
            }
        }

        return label.toString();
    }

    public static String getLabel(String text) {
        if (text == null) {
            return NULL_LABEL;
        }
        if ("".equals(text)) {
            return BLANK_LABEL;
        }
        return text;
    }

    public static String getDataTypeLabel(Class<?> dataType) {
        if (dataType == null) {
            return "<undefined>";
        } else {
            return dataType.getSimpleName();
        }
    }

    /**
     * Gets the label of a value, eg. a value in a crosstab.
     * 
     * @param value
     * @return
     */
    public static String getValueLabel(Object value) {
        if (value == null) {
            return NULL_LABEL;
        }

        // format decimals
        if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
            final NumberFormat format = NumberFormat.getNumberInstance();
            final String result = format.format((Number) value);
            logger.debug("Formatted decimal {} to: {}", value, result);
            return result;
        }

        // format dates
        if (value instanceof Date) {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            final String result = format.format((Date) value);
            logger.debug("Formatted date {} to: {}", value, result);
            return result;
        }

        return getLabel(value.toString());
    }
}
