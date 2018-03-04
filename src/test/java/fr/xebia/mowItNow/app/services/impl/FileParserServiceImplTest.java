package fr.xebia.mowItNow.app.services.impl;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.xebia.mowItNow.app.config.MowItNowConfig;
import fr.xebia.mowItNow.app.domain.AutoPilotParameters;
import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.services.FileParserService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MowItNowConfig.class})
public class FileParserServiceImplTest extends TestCase {

	private static final String RESOURCE_INPUT_TXT = "src/test/resources/input.txt";
	
	private static final String RESOURCE_INPUT_ERROR_TXT = "src/test/resources/input_error.txt";
	
	@Autowired
	private FileParserService fileParserService;
	
	@Test
    public void testParse() throws IOException {
        
        AutoPilotParameters autoPilotParameters = fileParserService.parse(RESOURCE_INPUT_TXT);
        
        assertNotNull(autoPilotParameters.getMowers());
        assertNotNull(autoPilotParameters.getLawn());
        assertEquals(autoPilotParameters.getMowers().size(), 2);
        assertEquals(autoPilotParameters.getLawn().getDimension(), new Point(5,5));
        assertEquals(autoPilotParameters.getMowers().get(0).getPoint(), new Point(1,2));
        assertEquals(autoPilotParameters.getMowers().get(1).getPoint(), new Point(3,3));
        assertEquals(autoPilotParameters.getMowers().get(0).getOrientation(), Orientation.N);
        assertEquals(autoPilotParameters.getMowers().get(1).getOrientation(), Orientation.E);
        assertEquals(autoPilotParameters.getMowers().get(0).getInstructions().size(), 9);
        assertEquals(autoPilotParameters.getMowers().get(1).getInstructions().size(), 10);
        testInstructions(autoPilotParameters.getMowers().get(0).getInstructions(),
        				 Arrays.asList(Instruction.G, Instruction.A,Instruction.G, Instruction.A,
        						 	   Instruction.G, Instruction.A,Instruction.G, Instruction.A,Instruction.A));
        testInstructions(autoPilotParameters.getMowers().get(1).getInstructions(),
				 Arrays.asList(Instruction.A, Instruction.A,Instruction.D, Instruction.A,
						 	   Instruction.A, Instruction.D,Instruction.A, Instruction.D,Instruction.D, Instruction.A));
    }

	private void testInstructions(List<Instruction> instructions, List<Instruction> instructionsResults) {
		for (int i = 0; i < instructions.size(); i++) {
			assertEquals(instructions.get(i), instructionsResults.get(i));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testParseError() throws IOException {
        
        fileParserService.parse(RESOURCE_INPUT_ERROR_TXT);
        
    }
}
