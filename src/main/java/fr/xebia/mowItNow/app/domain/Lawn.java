package fr.xebia.mowItNow.app.domain;

import java.awt.Point;

/**
 * 
 * @author GRIECH
 *
 */
public class Lawn {

	/**
	 * La dimension de la pelouse
	 */
	private Point dimension;

	/**
	 * 
	 */
	public Lawn() {
		super();
	}

	/**
	 * @param dimension
	 */
	public Lawn(Point dimension) {
		super();
		this.dimension = dimension;
	}

	/**
	 * @return the dimension
	 */
	public Point getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(Point dimension) {
		this.dimension = dimension;
	}
}
