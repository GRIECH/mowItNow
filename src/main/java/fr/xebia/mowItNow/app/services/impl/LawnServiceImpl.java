package fr.xebia.mowItNow.app.services.impl;

import java.awt.Point;

import org.springframework.stereotype.Service;

import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.services.LawnService;
import fr.xebia.mowItNow.app.domain.Lawn;

@Service
public class LawnServiceImpl implements LawnService {

	/* (non-Javadoc)
	 * @see fr.xebia.mowItNow.tondeuse.services.PelouseService#peutAvancer(fr.xebia.mowItNow.tondeuse.domain.Pelouse, java.awt.Point, fr.xebia.mowItNow.tondeuse.domain.Orientation)
	 */
	@Override
	public boolean canMove(Lawn lawn, Point point, Orientation orientation) {
		boolean result;
		switch (orientation) {
		case N:
			result = point.getY() < lawn.getDimension().getY();
			break;
		case E:
			result = point.getX() < lawn.getDimension().getX();
			break;
		case S:
			result = point.getY() > 0;
			break;
		case W:
			result = point.getX() > 0;
			break;
		default:
			result = false;
			break;
		}
		return result;
	}
}
