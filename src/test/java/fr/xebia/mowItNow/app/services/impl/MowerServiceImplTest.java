package fr.xebia.mowItNow.app.services.impl;

import java.awt.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.xebia.mowItNow.app.config.MowItNowConfig;
import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.services.MowerService;
import fr.xebia.mowItNow.app.domain.Mower;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MowItNowConfig.class})
public class MowerServiceImplTest extends TestCase {

	@Autowired
	private MowerService mowerService;
	
	@Test
    public void testPivotNordRight() {
        Orientation orientationResult = mowerService.pivot(Orientation.N, Instruction.D);
        assertEquals(Orientation.E, orientationResult);
    }
	
	@Test
    public void testPivotEstRight() {
        Orientation orientationResult = mowerService.pivot(Orientation.E, Instruction.D);
        assertEquals(Orientation.S, orientationResult);
    }
	
	@Test
    public void testPivotSudRight() {
        Orientation orientationResult = mowerService.pivot(Orientation.S, Instruction.D);
        assertEquals(Orientation.W, orientationResult);
    }
	
	@Test
    public void testPivotOuestRight() {
        Orientation orientationResult = mowerService.pivot(Orientation.W, Instruction.D);
        assertEquals(Orientation.N, orientationResult);
    }
	
	@Test
    public void testPivotNordLeft() {
        Orientation orientationResult = mowerService.pivot(Orientation.N, Instruction.G);
        assertEquals(Orientation.W, orientationResult);
    }
	
	@Test
    public void testPivotEstLeft() {
        Orientation orientationResult = mowerService.pivot(Orientation.E, Instruction.G);
        assertEquals(Orientation.N, orientationResult);
    }
	
	@Test
    public void testPivotSudLeft() {
        Orientation orientationResult = mowerService.pivot(Orientation.S, Instruction.G);
        assertEquals(Orientation.E, orientationResult);
    }
	
	@Test
    public void testPivotOuestLeft() {
        Orientation orientationResult = mowerService.pivot(Orientation.W, Instruction.G);
        assertEquals(Orientation.S, orientationResult);
    }
	
	@Test
    public void testPivotDefault() {
        Orientation orientationResult = mowerService.pivot(Orientation.N, Instruction.A);
        assertEquals(Orientation.N, orientationResult);
    }
	
	@Test
    public void testAvancerNord() {
		// Préparation
		int x = 1;
		int y = 2;
		Mower mower = new Mower(new Point(x, y), Orientation.N, null);
        
        // Appel de la méthode
        Point pointResult = mowerService.move(mower);
        
        // Assert
        assertEquals(x, (int) pointResult.getX());
        assertEquals(y+1, (int) pointResult.getY());
    }
	
	@Test
    public void testAvancerEst() {
		// Préparation
		int x = 1;
		int y = 2;
		Mower mower = new Mower(new Point(x, y), Orientation.E, null);
        
        // Appel de la méthode
        Point pointResult = mowerService.move(mower);
        
        // Assert
        assertEquals(x+1, (int) pointResult.getX());
        assertEquals(y, (int) pointResult.getY());
    }
	
	@Test
    public void testAvancerWest() {
		// Préparation
		int x = 1;
		int y = 2;
		Mower mower = new Mower(new Point(x, y), Orientation.W, null);
        
        // Appel de la méthode
        Point pointResult = mowerService.move(mower);
        
        // Assert
        assertEquals(x-1, (int) pointResult.getX());
        assertEquals(y, (int) pointResult.getY());
    }
	
	@Test
    public void testAvancerSud() {
		// Préparation
		int x = 1;
		int y = 2;
		Mower mower = new Mower(new Point(x, y), Orientation.S, null);
        
        // Appel de la méthode
        Point pointResult = mowerService.move(mower);
        
        // Assert
        assertEquals(x, (int) pointResult.getX());
        assertEquals(y-1, (int) pointResult.getY());
    }
}
