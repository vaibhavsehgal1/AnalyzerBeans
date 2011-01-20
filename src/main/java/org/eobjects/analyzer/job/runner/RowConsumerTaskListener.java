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
package org.eobjects.analyzer.job.runner;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.eobjects.analyzer.job.AnalysisJob;
import org.eobjects.analyzer.job.concurrent.TaskListener;
import org.eobjects.analyzer.job.tasks.Task;

public final class RowConsumerTaskListener implements TaskListener {

	private final AtomicInteger _counter = new AtomicInteger();
	private final AtomicBoolean _errorsReported = new AtomicBoolean(false);
	private final AnalysisListener _analysisListener;
	private final AnalysisJob _analysisJob;

	public RowConsumerTaskListener(AnalysisJob analysisJob, AnalysisListener analysisListener) {
		_analysisListener = analysisListener;
		_analysisJob = analysisJob;
	}

	@Override
	public void onBegin(Task task) {
	}

	@Override
	public void onComplete(Task task) {
		incrementCounter();
	}

	@Override
	public void onError(Task task, Throwable throwable) {
		boolean alreadyRegisteredError = _errorsReported.getAndSet(true);
		if (!alreadyRegisteredError) {
			_analysisListener.errorUknown(_analysisJob, throwable);
		}

		incrementCounter();
	}
	
	private void incrementCounter() {
		synchronized (this) {
			_counter.incrementAndGet();
			notifyAll();
		}
	}

	public boolean isErrornous() {
		return _errorsReported.get();
	}

	public void awaitTasks(final int numTasks) {
		synchronized (this) {
			while (numTasks > _counter.get()) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println("!!! " + e.getMessage());
				}
			}
		}
	}
}
