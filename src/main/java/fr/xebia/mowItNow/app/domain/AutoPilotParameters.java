package fr.xebia.mowItNow.app.domain;

import java.util.List;

public class AutoPilotParameters {

	private Lawn lawn;
	
	private List<Mower> mowers;

	/**
	 * 
	 */
	public AutoPilotParameters() {
		super();
	}

	/**
	 * @param lawn
	 * @param mowers
	 */
	public AutoPilotParameters(Lawn lawn, List<Mower> mowers) {
		super();
		this.lawn = lawn;
		this.mowers = mowers;
	}

	/**
	 * @return the lawn
	 */
	public Lawn getLawn() {
		return lawn;
	}

	/**
	 * @param lawn the lawn to set
	 */
	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}

	/**
	 * @return the mowers
	 */
	public List<Mower> getMowers() {
		return mowers;
	}

	/**
	 * @param mowers the mowers to set
	 */
	public void setMowers(List<Mower> mowers) {
		this.mowers = mowers;
	}
}
