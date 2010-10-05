package org.eobjects.analyzer.lifecycle;

import java.util.LinkedList;
import java.util.List;

import org.eobjects.analyzer.beans.api.Analyzer;
import org.eobjects.analyzer.descriptors.AnalyzerBeanDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an instance of an @AnalyzerBean annotated class at runtime. The
 * AnalyzerBeanInstance class is responsible for performing life-cycle actions
 * at an per-instance level. This makes it possible to add callbacks at various
 * stages in the life-cycle of an AnalyzerBean
 */
public class AnalyzerBeanInstance extends AbstractBeanInstance<Analyzer<?>> {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalyzerBeanInstance.class);

	private final List<AnalyzerLifeCycleCallback> runCallbacks = new LinkedList<AnalyzerLifeCycleCallback>();
	private final List<AnalyzerLifeCycleCallback> returnResultsCallbacks = new LinkedList<AnalyzerLifeCycleCallback>();

	public AnalyzerBeanInstance(AnalyzerBeanDescriptor<?> descriptor) {
		super(descriptor);
	}

	public void run() {
		if (logger.isInfoEnabled()) {
			logger.info("run (" + getBean() + ")");
		}
		runAnalyzerCallbacks(runCallbacks, LifeCycleState.RUN);
	}

	public List<AnalyzerLifeCycleCallback> getRunCallbacks() {
		return runCallbacks;
	}

	public void returnResults() {
		if (logger.isInfoEnabled()) {
			logger.info("returnResults (" + getBean() + ")");
		}
		runAnalyzerCallbacks(returnResultsCallbacks,
				LifeCycleState.RETURN_RESULTS);
	}

	public List<AnalyzerLifeCycleCallback> getReturnResultsCallbacks() {
		return returnResultsCallbacks;
	}

	private void runAnalyzerCallbacks(
			List<AnalyzerLifeCycleCallback> callbacks, LifeCycleState state) {
		for (AnalyzerLifeCycleCallback lifeCycleCallback : callbacks) {
			lifeCycleCallback.onEvent(state, getBean(), (AnalyzerBeanDescriptor<?>) getDescriptor());
		}
	}
}
