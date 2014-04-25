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
package org.eobjects.analyzer.connection;

import java.util.List;

import org.apache.metamodel.hbase.HBaseConfiguration;
import org.apache.metamodel.hbase.HBaseDataContext;

/**
 * Datastore implementation for HBase.
 * 
 * @author Tomasz Guzialek
 */
public class HBaseDatastore extends UsageAwareDatastore<HBaseDataContext> {

    private static final long serialVersionUID = 1L;
    
    private final int _zookeeperPort;
    private final String _zookeeperHostname;

    public HBaseDatastore(String name, String zookeeperHostname, int zookeeperPort) {
        super(name);
        _zookeeperHostname = zookeeperHostname;
        _zookeeperPort = zookeeperPort;
    }
    
    @Override
    public DatastoreConnection openConnection() {
        return (DatastoreConnection) super.openConnection();
    }

    @Override
    public PerformanceCharacteristics getPerformanceCharacteristics() {
        return new PerformanceCharacteristicsImpl(true);
    }

    @Override
    protected UsageAwareDatastoreConnection<HBaseDataContext> createDatastoreConnection() {
        HBaseConfiguration hBaseConfiguration = new HBaseConfiguration(_zookeeperHostname, _zookeeperPort);
        HBaseDataContext hBaseDataContext = new HBaseDataContext(hBaseConfiguration);
        return new DatastoreConnectionImpl<HBaseDataContext>(hBaseDataContext, this);
    }
    
    public String getZookeeperHostname() {
        return _zookeeperHostname;
    }
    
    public int getZookeeperPort() {
        return _zookeeperPort;
    }
    
    @Override
    protected void decorateIdentity(List<Object> identifiers) {
        super.decorateIdentity(identifiers);
        identifiers.add(_zookeeperHostname);
        identifiers.add(_zookeeperPort);
    }

}
