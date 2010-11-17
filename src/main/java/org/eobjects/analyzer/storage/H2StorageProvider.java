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
package org.eobjects.analyzer.storage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * H2 based implementation of the StorageProvider.
 * 
 * @author Kasper Sørensen
 * 
 */
public final class H2StorageProvider extends SqlDatabaseStorageProvider implements StorageProvider {

	private static final AtomicInteger databaseIndex = new AtomicInteger(0);
	private static final String DATABASE_FILENAME = "h2-storage";

	public H2StorageProvider() {
		super("org.h2.Driver", "jdbc:h2:mem:ab" + databaseIndex.getAndIncrement());
	}

	public H2StorageProvider(int inMemoryThreshold) {
		super(inMemoryThreshold, "org.h2.Driver", "jdbc:h2:mem:ab" + databaseIndex.getAndIncrement());
	}

	public H2StorageProvider(String directoryPath) {
		super("org.h2.Driver", "jdbc:h2:" + cleanDirectory(directoryPath) + File.separatorChar + DATABASE_FILENAME);
	}

	public H2StorageProvider(int inMemoryThreshold, String directoryPath) {
		super(inMemoryThreshold, "org.h2.Driver", "jdbc:h2:" + cleanDirectory(directoryPath) + File.separatorChar
				+ DATABASE_FILENAME);
	}

	private static String cleanDirectory(String directoryPath) {
		File file = new File(directoryPath);
		if (file.exists() && file.isDirectory()) {
			// remove all files from previous uncleansed database
			File[] dbFiles = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(DATABASE_FILENAME);
				}
			});
			for (File f : dbFiles) {
				f.delete();
			}
		}
		return directoryPath;
	}
}