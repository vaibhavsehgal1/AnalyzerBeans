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
package org.eobjects.analyzer.result.html;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides
 */
public final class HtmlUtils {

    private static final ThreadLocal<AtomicInteger> threadLocalFunctionCounter = new ThreadLocal<AtomicInteger>();
    private static final ThreadLocal<AtomicInteger> threadLocalElementIdCounter = new ThreadLocal<AtomicInteger>();

    private HtmlUtils() {
        // prevent instantiation
    }

    private static AtomicInteger getFunctionCounter(ThreadLocal<AtomicInteger> threadLocal) {
        AtomicInteger counter = threadLocal.get();
        if (counter == null) {
            counter = new AtomicInteger();
            threadLocal.set(counter);
        }
        return counter;
    }

    public static void resetIds() {
        threadLocalFunctionCounter.remove();
        threadLocalElementIdCounter.remove();
    }

    public static String createFunctionName() {
        return "analysisResult.callback" + getFunctionCounter(threadLocalFunctionCounter).incrementAndGet();
    }

    public static String createElementId() {
        return "analysisResultElement" + getFunctionCounter(threadLocalElementIdCounter).incrementAndGet();
    }

}
