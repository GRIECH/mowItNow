package fr.xebia.mowItNow.app.services.impl;

import java.awt.Point;

import org.springframework.stereotype.Service;

import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.services.MowerService;
import fr.xebia.mowItNow.app.domain.Mower;

@Service
public class MowerServiceImpl implements MowerService {

	/* (non-Javadoc)
	 * @see fr.xebia.mowItNow.tondeuse.services.TondeuseService#pivoter(fr.xebia.mowItNow.tondeuse.domain.Orientation, fr.xebia.mowItNow.tondeuse.domain.Instruction)
	 */
	@Override
	public Orientation pivot(Orientation orientation, Instruction instruction) {
		Orientation orientationResult;
		switch (instruction) {
		case D:
			orientationResult = pivotRight(orientation);
			break;
		case G:
			orientationResult = pivotLeft(orientation);
			break;
		default:
			orientationResult = orientation;
			break;
		}
		return orientationResult;
	}

	/**
	 * Pivoter à gauche
	 * @param orientation
	 * @return
	 */
	private Orientation pivotLeft(Orientation orientation) {
		Orientation orientationResult;
		switch (orientation) {
		case N:
			orientationResult = Orientation.W;
			break;
		case E:
			orientationResult = Orientation.N;
			break;
		case S:
			orientationResult = Orientation.E;
			break;
		case W:
			orientationResult = Orientation.S;
			break;
		default:
			orientationResult = orientation;
			break;
		}
		return orientationResult;
	}

	/**
	 * pivoter à droite
	 * @param orientation
	 * @return
	 */
	private Orientation pivotRight(Orientation orientation) {
		Orientation orientationResult;
		switch (orientation) {
		case N:
			orientationResult = Orientation.E;
			break;
		case E:
			orientationResult = Orientation.S;
			break;
		case S:
			orientationResult = Orientation.W;
			break;
		case W:
			orientationResult = Orientation.N;
			break;
		default:
			orientationResult = orientation;
			break;
		}
		return orientationResult;
	}
	
	/* (non-Javadoc)
	 * @see fr.xebia.mowItNow.tondeuse.services.TondeuseService#avancer(fr.xebia.mowItNow.tondeuse.domain.Tondeuse)
	 */
	@Override
	public Point move(Mower mower) {
		Point pointResult;
		switch (mower.getOrientation()) {
		case N:
			pointResult = new Point((int) mower.getPoint().getX(),
									  (int) mower.getPoint().getY() + 1);
			break;
		case E:
			pointResult = new Point((int) mower.getPoint().getX() + 1,
									  (int) mower.getPoint().getY());
			break;
		case S:
			pointResult = new Point((int) mower.getPoint().getX(),
									  (int) mower.getPoint().getY() - 1);
			break;
		case W:
			pointResult = new Point((int) mower.getPoint().getX() - 1,
									  (int) mower.getPoint().getY());
			break;
		default:
			pointResult = mower.getPoint();
			break;
		}
		return pointResult;
	}
}
