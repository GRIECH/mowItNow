package fr.xebia.mowItNow.app.domain;

import java.awt.Point;
import java.util.List;

/**
 * 
 * @author GRIECH
 *
 */
public class Mower {

	/**
	 * Coordonnées de la tondeuse
	 */
	private Point point;
	
	/**
	 * Orientation de la tondeuse
	 */
	private Orientation orientation;
	
	/**
	 * Programmation de la tondeuse
	 */
	public List<Instruction> instructions;

	/**
	 * 
	 */
	public Mower() {
		super();
	}

	/**
	 * 
	 * @param point
	 * @param orientation
	 * @param instructions
	 */
	public Mower(Point point, Orientation orientation, List<Instruction> instructions) {
		super();
		this.point = point;
		this.orientation = orientation;
		this.instructions = instructions;
	}

	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the instructions
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tondeuse [point=" + point + ", orientation=" + orientation + "]";
	}
}
