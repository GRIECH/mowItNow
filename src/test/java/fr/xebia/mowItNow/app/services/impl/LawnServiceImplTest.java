package fr.xebia.mowItNow.app.services.impl;

import java.awt.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.xebia.mowItNow.app.config.MowItNowConfig;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.services.LawnService;
import fr.xebia.mowItNow.app.domain.Lawn;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MowItNowConfig.class})
public class LawnServiceImplTest extends TestCase {
	
	@Autowired
	private LawnService lawnService;
	
	@Test
    public void testcanMoveEst() {
		// Préparation
		Lawn lawn = createLawn(5 ,5);

		Point point = new Point(1, 2);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.E);
        
        // Assert
        assertTrue(result);
    }
	
	@Test
    public void testCanMoveEstFalse() {
		// Préparation
		Lawn lawn = createLawn(5, 5);
		
		Point point = new Point((int) lawn.getDimension().getX(), 2);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.E);
        
        // Assert
        assertFalse(result);
    }
	
	@Test
    public void testCanMoveNord() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(3, 4);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.N);
        
        // Assert
        assertTrue(result);
    }
	
	@Test
    public void testCanMoveNordFalse() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(1, (int) lawn.getDimension().getY());
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.N);
        
        // Assert
        assertFalse(result);
    }
	
	@Test
    public void testCanMoveOuest() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(1, 0);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.W);
        
        // Assert
        assertTrue(result);
    }
	
	@Test
    public void testCanMoveOuestFalse() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(0, 0);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.W);
        
        // Assert
        assertFalse(result);
    }
	
	@Test
    public void testCanMoveSud() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(1, 2);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.S);
        
        // Assert
        assertTrue(result);
    }
	
	@Test
    public void testCanMoveSudFalse() {
		// Préparation
		Lawn lawn = createLawn(5,5);
		
		Point point = new Point(1, 0);
        
        // Appel de la méthode
        boolean result = lawnService.canMove(lawn, point, Orientation.S);
        
        // Assert
        assertFalse(result);
    }

	/**
	 * Créer une lawn
	 * @return
	 */
	private Lawn createLawn(int d1, int d2) {
		return new Lawn(new Point(d1, d2));
	}
}
