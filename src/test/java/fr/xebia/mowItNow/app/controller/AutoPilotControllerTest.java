package fr.xebia.mowItNow.app.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.xebia.mowItNow.app.config.MowItNowConfig;
import fr.xebia.mowItNow.app.controller.AutoPilotController;
import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.domain.Lawn;
import fr.xebia.mowItNow.app.domain.AutoPilotParameters;
import fr.xebia.mowItNow.app.domain.Mower;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MowItNowConfig.class})
public class AutoPilotControllerTest extends TestCase {
	
	@Autowired
    private AutoPilotController pilotageController;
	
	/**
	 * 5 5
	 * 1 2 N
	 * GAGAGAGAA
	 * 3 3 E
	 * AADAADADDA 
	 */
	@Test
    public void testPilotageXebia() {
		// Préparation
		Lawn lawn = createLawnXebia();
		List<Mower> mowers = createMowersXebia();
        
        // Appel de la méthode
		pilotageController.startAutoPilot(new AutoPilotParameters(lawn, mowers));
        
        // Assert
        assertEquals(2, mowers.size());
        assertEquals(new Point(1,3), mowers.get(0).getPoint());
        assertEquals(Orientation.N, mowers.get(0).getOrientation());
        assertEquals(new Point(5,1), mowers.get(1).getPoint());
        assertEquals(Orientation.E, mowers.get(1).getOrientation());
    }

	/**
	 * 5 5
	 * 1 2 N
	 * GAGAGAGAA
	 * 3 3 E
	 * AADAADADDA
	 * @return
	 */
	private Lawn createLawnXebia() {
		return new Lawn(new Point(5, 5));
	}

	private List<Mower> createMowersXebia() {
		List<Mower> mowers = new ArrayList<>();
		Point pointMower1 = new Point(1, 2);
		Orientation orientation1 = Orientation.N;
		List<Instruction> instructions1 = new ArrayList<>(Arrays.asList(
				Instruction.G, Instruction.A, Instruction.G, Instruction.A,
				Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A));
 		Mower mower1 = new Mower(pointMower1, orientation1, instructions1);
 		mowers.add(mower1);
 		
 		Point pointMower2 = new Point(3, 3);
		Orientation orientation2 = Orientation.E;
		List<Instruction> instructions2 = new ArrayList<>(Arrays.asList(
				Instruction.A, Instruction.A, Instruction.D, Instruction.A, Instruction.A,
				Instruction.D, Instruction.A, Instruction.D, Instruction.D, Instruction.A));
 		Mower mower2 = new Mower(pointMower2, orientation2, instructions2);
 		mowers.add(mower2);
		return mowers;
	}
}
