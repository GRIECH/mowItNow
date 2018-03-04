package fr.xebia.mowItNow.app.services;

import java.awt.Point;

import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.domain.Mower;

public interface MowerService {

	/**
	 * Pivoter la tondeuse à droite ou à gauche à 90°
	 * 
	 * @param orientation
	 * @param instruction
	 * @return
	 */
	Orientation pivot(Orientation orientation, Instruction instruction);

	/**
	 * Avancer la tondeuse selon son orientation
	 * @param mower
	 * @return
	 */
	Point move(Mower mower);
}