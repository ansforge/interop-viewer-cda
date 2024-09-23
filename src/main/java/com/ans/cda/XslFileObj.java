package com.ans.cda;

import java.io.Serializable;
import java.util.Objects;

/**
 * XslFileObj
 * 
 * @author bensalem Nizar
 */
public class XslFileObj implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6615537153754682712L;
	/**
	 * outStr
	 */
	private String outStr;
	/**
	 * xslLineAutoP
	 */
	private String xslLineAutoP;
	/**
	 * xmlFileParent
	 */
	private String xmlFileParent;
	/**
	 * xmlStr
	 */
	private String xmlStr;

	/**
	 * XslFileObj
	 * 
	 * @param outStr
	 * @param xslLineAutoP
	 * @param xmlFileParent
	 * @param xmlStr
	 */
	public XslFileObj(final String outStr, final String xslLineAutoP, final String xmlFileParent, final String xmlStr) {
		this.outStr = outStr;
		this.xslLineAutoP = xslLineAutoP;
		this.xmlFileParent = xmlFileParent;
		this.xmlStr = xmlStr;
	}

	/**
	 * constructor
	 * 
	 */
	public XslFileObj() {
		// default
	}

	/**
	 * @return the outStr
	 */
	public String getOutStr() {
		return outStr;
	}

	/**
	 * @param outStr the outStr to set
	 */
	public void setOutStr(final String outStr) {
		this.outStr = outStr;
	}

	/**
	 * @return the xslLineAutoP
	 */
	public String getXslLineAutoP() {
		return xslLineAutoP;
	}

	/**
	 * @param xslLineAutoP the xslLineAutoP to set
	 */
	public void setXslLineAutoP(final String xslLineAutoP) {
		this.xslLineAutoP = xslLineAutoP;
	}

	/**
	 * @return the xmlFileParent
	 */
	public String getXmlFileParent() {
		return xmlFileParent;
	}

	/**
	 * @param xmlFileParent the xmlFileParent to set
	 */
	public void setXmlFileParent(final String xmlFileParent) {
		this.xmlFileParent = xmlFileParent;
	}

	/**
	 * @return the xmlStr
	 */
	public String getXmlStr() {
		return xmlStr;
	}

	/**
	 * @param xmlStr the xmlStr to set
	 */
	public void setXmlStr(final String xmlStr) {
		this.xmlStr = xmlStr;
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(outStr, xmlFileParent, xmlStr, xslLineAutoP);
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(final Object obj) {
		Boolean bool = null;
		if (this == obj) {
			bool = true;
		}
		if (obj == null) {
			bool = false;
		}
		if (getClass() != obj.getClass()) {
			bool = false;
		}
		final XslFileObj other = (XslFileObj) obj;
		return (bool != null) ? bool
				: Objects.equals(outStr, other.outStr) && Objects.equals(xmlFileParent, other.xmlFileParent)
						&& Objects.equals(xmlStr, other.xmlStr) && Objects.equals(xslLineAutoP, other.xslLineAutoP);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "XslFileObj [outStr=" + outStr + ", xslLineAutoP=" + xslLineAutoP + ", xmlFileParent=" + xmlFileParent
				+ ", xmlStr=" + xmlStr + "]";
	}
}
