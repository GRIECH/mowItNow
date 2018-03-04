package fr.xebia.mowItNow.app.services;

import fr.xebia.mowItNow.app.domain.Lawn;
import fr.xebia.mowItNow.app.domain.Mower;

public interface AutoPilotService {

	/**
	 * Piloter une tondeuse déployée sur une pelouse
	 * 
	 * @param lawn
	 * @param mower
	 */
	void autoPilot(Lawn lawn, Mower mower);

}