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
package org.eobjects.analyzer.beans.standardize;

import java.util.ArrayList;
import java.util.List;

import org.eobjects.analyzer.beans.api.Categorized;
import org.eobjects.analyzer.beans.api.Configured;
import org.eobjects.analyzer.beans.api.Description;
import org.eobjects.analyzer.beans.api.Initialize;
import org.eobjects.analyzer.beans.api.OutputColumns;
import org.eobjects.analyzer.beans.api.Transformer;
import org.eobjects.analyzer.beans.api.TransformerBean;
import org.eobjects.analyzer.beans.categories.MatchingAndStandardizationCategory;
import org.eobjects.analyzer.data.InputColumn;
import org.eobjects.analyzer.data.InputRow;
import org.eobjects.analyzer.util.HasGroupLiteral;
import org.eobjects.analyzer.util.NamedPattern;
import org.eobjects.analyzer.util.NamedPatternMatch;

@TransformerBean("URL standardizer")
@Description("Retrieve the individual parts of an URL, including protocol, domain, port, path and querystring.")
@Categorized({ MatchingAndStandardizationCategory.class })
public class UrlStandardizerTransformer implements Transformer<String> {

	public static final String[] PATTERNS = { "PROTOCOL://DOMAIN:PORTPATH\\?QUERYSTRING",
			"PROTOCOL://DOMAINPATH\\?QUERYSTRING", "PROTOCOL://DOMAIN:PORTPATH", "PROTOCOL://DOMAIN:PORT\\?QUERYSTRING",
			"PROTOCOL://DOMAIN\\?QUERYSTRING", "PROTOCOL://DOMAINPATH", "PROTOCOL://DOMAIN:PORT", "PROTOCOL://DOMAIN" };

	public static enum UrlPart implements HasGroupLiteral {
		PROTOCOL, DOMAIN, PORT, PATH, QUERYSTRING;

		@Override
		public String getGroupLiteral() {
			if (this == DOMAIN) {
				return "([a-zA-Z0-9\\._\\-@]+)";
			}
			if (this == PORT) {
				return "([0-9]+)";
			}
			if (this == PATH) {
				return "(/[a-zA-Z0-9\\._\\-/#:%]+)";
			}
			if (this == QUERYSTRING) {
				return "([a-zA-Z0-9\\.=\\?_\\-/%]+)";
			}
			return null;
		}
	}

	@Configured
	InputColumn<String> inputColumn;

	private List<NamedPattern<UrlPart>> namedPatterns;

	@Initialize
	public void init() {
		namedPatterns = new ArrayList<NamedPattern<UrlPart>>(PATTERNS.length);
		for (String pattern : PATTERNS) {
			namedPatterns.add(new NamedPattern<UrlPart>(pattern, UrlPart.class));
		}
	}

	@Override
	public OutputColumns getOutputColumns() {
		return new OutputColumns("Protocol", "Domain", "Port", "Path", "Querystring");
	}

	@Override
	public String[] transform(InputRow inputRow) {
		String value = inputRow.getValue(inputColumn);
		return transform(value);
	}

	public String[] transform(String value) {
		String protocol = null;
		String domain = null;
		String port = null;
		String path = null;
		String queryString = null;

		if (value != null) {
			for (NamedPattern<UrlPart> namedPattern : namedPatterns) {
				NamedPatternMatch<UrlPart> match = namedPattern.match(value);
				if (match != null) {
					protocol = match.get(UrlPart.PROTOCOL);
					domain = match.get(UrlPart.DOMAIN);
					port = match.get(UrlPart.PORT);
					path = match.get(UrlPart.PATH);
					queryString = match.get(UrlPart.QUERYSTRING);
					break;
				}
			}
		}

		return new String[] { protocol, domain, port, path, queryString };
	}

}
