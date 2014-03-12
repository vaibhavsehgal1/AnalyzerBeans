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

import java.util.Collection;

import org.eobjects.analyzer.beans.api.Analyzer;
import org.eobjects.analyzer.job.AnalysisJob;
import org.eobjects.analyzer.job.AnalyzerJob;
import org.eobjects.analyzer.job.ComponentJob;
import org.eobjects.analyzer.job.runner.AnalysisListener;
import org.eobjects.analyzer.job.runner.JobAndResult;
import org.eobjects.analyzer.result.AnalyzerResult;
import org.eobjects.analyzer.result.HasAnalyzerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Task that collects the {@link AnalyzerResult} from an Analyzer.
 */
public final class CollectResultsTask implements Task {

	private static final Logger logger = LoggerFactory.getLogger(CollectResultsTask.class);

	private final HasAnalyzerResult<?> _hasResult;
	private final Collection<JobAndResult> _results;
	private final AnalysisListener _analysisListener;
	private final AnalysisJob _job;
	private final ComponentJob _componentJob;

	public CollectResultsTask(HasAnalyzerResult<?> hasResult, AnalysisJob job, ComponentJob componentJob,
			Collection<JobAndResult> results, AnalysisListener analysisListener) {
		_hasResult = hasResult;
		_job = job;
		_componentJob = componentJob;
		_results = results;
		_analysisListener = analysisListener;
	}

	@Override
	public void execute() throws Exception {
		logger.debug("execute()");

		AnalyzerResult result = _hasResult.getResult();
		if (result == null) {
			throw new IllegalStateException("Analyzer result (from " + _hasResult + ") was null");
		}
		if (_hasResult instanceof Analyzer) {
			_analysisListener.analyzerSuccess(_job, (AnalyzerJob) _componentJob, result);
		} else {
			throw new UnsupportedOperationException("Unsupported component type: " + _hasResult);
		}
		_results.add(new JobAndResult(_componentJob, result));
	}

}
