package com.ans.cda;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class PdfObjectValue
 * 
 * @author bensa Nizar
 */
public class PdfObjectValue implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1575803794862548875L;
	/**
	 * pdfNormal
	 */
	private String pdfNormal;
	/**
	 * pdfA
	 */
	private String pdfA;
	/**
	 * autor
	 */
	private String autor;
	/**
	 * assunto
	 */
	private String assunto;
	/**
	 * constructor
	 * 
	 * @param pdfNormal
	 * @param pdfA
	 * @param autor
	 * @param assunto
	 */
	public PdfObjectValue(final String pdfNormal, final String pdfA, final String autor, final String assunto) {
		this.pdfNormal = pdfNormal;
		this.pdfA = pdfA;
		this.autor = autor;
		this.assunto = assunto;
	}

	/**
	 * constructor
	 * 
	 */
	public PdfObjectValue() {
		// default
	}

	/**
	 * @return the pdfNormal
	 */
	public String getPdfNormal() {
		return pdfNormal;
	}

	/**
	 * @param pdfNormal the pdfNormal to set
	 */
	public void setPdfNormal(final String pdfNormal) {
		this.pdfNormal = pdfNormal;
	}

	/**
	 * @return the pdfA
	 */
	public String getPdfA() {
		return pdfA;
	}

	/**
	 * @param pdfA the pdfA to set
	 */
	public void setPdfA(final String pdfA) {
		this.pdfA = pdfA;
	}

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(final String autor) {
		this.autor = autor;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(final String assunto) {
		this.assunto = assunto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assunto, autor, pdfA, pdfNormal);
	}

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
		final PdfObjectValue other = (PdfObjectValue) obj;
		return (bool != null) ? bool
				: Objects.equals(assunto, other.assunto) && Objects.equals(autor, other.autor)
						&& Objects.equals(pdfA, other.pdfA) && Objects.equals(pdfNormal, other.pdfNormal);
	}

	@Override
	public String toString() {
		return "PdfObj [pdfNormal=" + pdfNormal + ", pdfA=" + pdfA + ", autor=" + autor + ", assunto=" + assunto + "]";
	}
}
