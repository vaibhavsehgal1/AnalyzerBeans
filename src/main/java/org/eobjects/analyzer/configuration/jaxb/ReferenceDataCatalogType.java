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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.02 at 03:44:07 PM CET 
//


package org.eobjects.analyzer.configuration.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for referenceDataCatalogType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="referenceDataCatalogType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dictionaries" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="text-file-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}textFileDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="value-list-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}valueListDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="datastore-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}datastoreDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="custom-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}customElementType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="synonym-catalogs" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="text-file-synonym-catalog" type="{http://eobjects.org/analyzerbeans/configuration/1.0}textFileSynonymCatalogType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="custom-synonym-catalog" type="{http://eobjects.org/analyzerbeans/configuration/1.0}customElementType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="string-patterns" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="regex-pattern" type="{http://eobjects.org/analyzerbeans/configuration/1.0}regexPatternType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="simple-pattern" type="{http://eobjects.org/analyzerbeans/configuration/1.0}simplePatternType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referenceDataCatalogType", propOrder = {
    "dictionaries",
    "synonymCatalogs",
    "stringPatterns"
})
public class ReferenceDataCatalogType {

    protected ReferenceDataCatalogType.Dictionaries dictionaries;
    @XmlElement(name = "synonym-catalogs")
    protected ReferenceDataCatalogType.SynonymCatalogs synonymCatalogs;
    @XmlElement(name = "string-patterns")
    protected ReferenceDataCatalogType.StringPatterns stringPatterns;

    /**
     * Gets the value of the dictionaries property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataCatalogType.Dictionaries }
     *     
     */
    public ReferenceDataCatalogType.Dictionaries getDictionaries() {
        return dictionaries;
    }

    /**
     * Sets the value of the dictionaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataCatalogType.Dictionaries }
     *     
     */
    public void setDictionaries(ReferenceDataCatalogType.Dictionaries value) {
        this.dictionaries = value;
    }

    /**
     * Gets the value of the synonymCatalogs property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataCatalogType.SynonymCatalogs }
     *     
     */
    public ReferenceDataCatalogType.SynonymCatalogs getSynonymCatalogs() {
        return synonymCatalogs;
    }

    /**
     * Sets the value of the synonymCatalogs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataCatalogType.SynonymCatalogs }
     *     
     */
    public void setSynonymCatalogs(ReferenceDataCatalogType.SynonymCatalogs value) {
        this.synonymCatalogs = value;
    }

    /**
     * Gets the value of the stringPatterns property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataCatalogType.StringPatterns }
     *     
     */
    public ReferenceDataCatalogType.StringPatterns getStringPatterns() {
        return stringPatterns;
    }

    /**
     * Sets the value of the stringPatterns property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataCatalogType.StringPatterns }
     *     
     */
    public void setStringPatterns(ReferenceDataCatalogType.StringPatterns value) {
        this.stringPatterns = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="text-file-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}textFileDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="value-list-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}valueListDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="datastore-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}datastoreDictionaryType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="custom-dictionary" type="{http://eobjects.org/analyzerbeans/configuration/1.0}customElementType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "textFileDictionaryOrValueListDictionaryOrDatastoreDictionary"
    })
    public static class Dictionaries {

        @XmlElements({
            @XmlElement(name = "value-list-dictionary", type = ValueListDictionaryType.class),
            @XmlElement(name = "text-file-dictionary", type = TextFileDictionaryType.class),
            @XmlElement(name = "custom-dictionary", type = CustomElementType.class),
            @XmlElement(name = "datastore-dictionary", type = DatastoreDictionaryType.class)
        })
        protected List<Object> textFileDictionaryOrValueListDictionaryOrDatastoreDictionary;

        /**
         * Gets the value of the textFileDictionaryOrValueListDictionaryOrDatastoreDictionary property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the textFileDictionaryOrValueListDictionaryOrDatastoreDictionary property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTextFileDictionaryOrValueListDictionaryOrDatastoreDictionary().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ValueListDictionaryType }
         * {@link TextFileDictionaryType }
         * {@link CustomElementType }
         * {@link DatastoreDictionaryType }
         * 
         * 
         */
        public List<Object> getTextFileDictionaryOrValueListDictionaryOrDatastoreDictionary() {
            if (textFileDictionaryOrValueListDictionaryOrDatastoreDictionary == null) {
                textFileDictionaryOrValueListDictionaryOrDatastoreDictionary = new ArrayList<Object>();
            }
            return this.textFileDictionaryOrValueListDictionaryOrDatastoreDictionary;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="regex-pattern" type="{http://eobjects.org/analyzerbeans/configuration/1.0}regexPatternType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="simple-pattern" type="{http://eobjects.org/analyzerbeans/configuration/1.0}simplePatternType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "regexPatternOrSimplePattern"
    })
    public static class StringPatterns {

        @XmlElements({
            @XmlElement(name = "regex-pattern", type = RegexPatternType.class),
            @XmlElement(name = "simple-pattern", type = SimplePatternType.class)
        })
        protected List<Object> regexPatternOrSimplePattern;

        /**
         * Gets the value of the regexPatternOrSimplePattern property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the regexPatternOrSimplePattern property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRegexPatternOrSimplePattern().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RegexPatternType }
         * {@link SimplePatternType }
         * 
         * 
         */
        public List<Object> getRegexPatternOrSimplePattern() {
            if (regexPatternOrSimplePattern == null) {
                regexPatternOrSimplePattern = new ArrayList<Object>();
            }
            return this.regexPatternOrSimplePattern;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="text-file-synonym-catalog" type="{http://eobjects.org/analyzerbeans/configuration/1.0}textFileSynonymCatalogType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="custom-synonym-catalog" type="{http://eobjects.org/analyzerbeans/configuration/1.0}customElementType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "allTypesOfSynonymCatalogs"
    })
    public static class SynonymCatalogs {

        @XmlElements({
            @XmlElement(name = "custom-synonym-catalog", type = CustomElementType.class),
            @XmlElement(name = "text-file-synonym-catalog", type = TextFileSynonymCatalogType.class),
            @XmlElement(name = "dataStore-synonym-catalog", type = DataStoreSynonymCatalogType.class)
        })
        // TODO change name
        protected List<Object> allTypesOfSynonymCatalogs;

        /**
         * Gets the value of the textFileSynonymCatalogOrCustomSynonymCatalog property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the textFileSynonymCatalogOrCustomSynonymCatalog property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTextFileSynonymCatalogOrCustomSynonymCatalog().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CustomElementType }
         * {@link TextFileSynonymCatalogType }
         * 
         * 
         */
        public List<Object> getAllTypesOfSynonymCatalogs() {
            if (allTypesOfSynonymCatalogs == null) {
                allTypesOfSynonymCatalogs = new ArrayList<Object>();
            }
            return this.allTypesOfSynonymCatalogs;
        }
        
        
    }
}
