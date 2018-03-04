package fr.xebia.mowItNow.app.services;

import java.awt.Point;

import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.domain.Lawn;

public interface LawnService {

	boolean canMove(Lawn lawn, Point point, Orientation orientation);

}