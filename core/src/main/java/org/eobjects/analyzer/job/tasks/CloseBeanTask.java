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
package org.eobjects.analyzer.job.tasks;

import org.eobjects.analyzer.lifecycle.AbstractBeanInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseBeanTask implements Task {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final AbstractBeanInstance<?> _beanInstance;

	public CloseBeanTask(AbstractBeanInstance<?> beanInstance) {
		_beanInstance = beanInstance;
	}

	@Override
	public void execute() throws Exception {
		logger.debug("execute()");

		// close can occur AFTER completion
		_beanInstance.close();
	}
}