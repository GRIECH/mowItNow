package fr.xebia.mowItNow.app.services.impl;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Orientation;
import fr.xebia.mowItNow.app.domain.Lawn;
import fr.xebia.mowItNow.app.domain.AutoPilotParameters;
import fr.xebia.mowItNow.app.services.FileParserService;
import fr.xebia.mowItNow.app.domain.Mower;

@Service
public class FileParserServiceImpl implements FileParserService {

	private static final String ERROR_FORMAT_FICHIER_INCORRECT = "Le format du fichier est incorrect";

	private final String DIMENSION_PATTERN = "^([1-9][0-9]*) ([1-9][0-9]*)$";
	
	private final String POINT_PATTERN = "^([1-9][0-9]*) ([1-9][0-9]*) ([N,E,S,D])$";
	
	private final String INSTRUCTIONS_PATTERN = "^([G,D,A]*)$";
	
	/* (non-Javadoc)
	 * @see fr.xebia.mowItNow.tondeuse.services.impl.FileParserService#parse(java.lang.String)
	 */
	@Override
	public AutoPilotParameters parse(String fileName) throws IOException {

    	AutoPilotParameters autoPilotParameters = new AutoPilotParameters();
    	
    	try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
    		
    		List<String> linesAsList = lines.collect(Collectors.toList());

    		if (!isEmpty(linesAsList)) {
    			
    			autoPilotParameters = parseFile(linesAsList);
    		}
    		
    	}
    	
		return autoPilotParameters;
    }

	private AutoPilotParameters parseFile(List<String> linesAsList) {
		
		AutoPilotParameters autoPilotParameters = new AutoPilotParameters();
		
		// Compilation des regex
    	Pattern patternDimension=Pattern.compile(DIMENSION_PATTERN);
    	Pattern patternPoint=Pattern.compile(POINT_PATTERN);
    	Pattern patternInstructions=Pattern.compile(INSTRUCTIONS_PATTERN);
    	
		// Pelouse
    	Iterator<String> iterator = linesAsList.iterator();
		autoPilotParameters.setLawn(parseLawnDimension(patternDimension, iterator.next()));
		
		// Tondeuses
		autoPilotParameters.setMowers(new ArrayList<>());
		while (iterator.hasNext()) {
			// Point
			Mower mower = parsePointAndOrientation(patternPoint, (String) iterator.next());
			// Orientation
			if (iterator.hasNext()) {
		    	mower.setInstructions(parseInscructions(patternInstructions, (String) iterator.next()));
			} else {
				throw new IllegalArgumentException(ERROR_FORMAT_FICHIER_INCORRECT);
			}
			autoPilotParameters.getMowers().add(mower);
		}
		
		return autoPilotParameters;
	}

	private Lawn parseLawnDimension(Pattern patternDimension, String line) {
		
		Lawn lawn = new Lawn();
		Matcher matcher=patternDimension.matcher(line);
		if (matcher.find()) {
			lawn.setDimension(new Point(Integer.valueOf(matcher.group(1)),
										  Integer.valueOf(matcher.group(2))));
		} else {
			throw new IllegalArgumentException("La première ligne du fichier est incorrect");
		}
		return lawn;
	}

	private Mower parsePointAndOrientation(Pattern patternPoint, String line) {
		
		Mower mower = new Mower();

		Matcher matcher=patternPoint.matcher(line);
		
		if (matcher.find()) {
			// Point
			mower.setPoint(new Point(Integer.valueOf(matcher.group(1)),
									  Integer.valueOf(matcher.group(2))));
			// Orientation
			mower.setOrientation(Orientation.valueOf(matcher.group(3)));
		} else {
			throw new IllegalArgumentException(ERROR_FORMAT_FICHIER_INCORRECT);
		}
		return mower;
	}

	private List<Instruction> parseInscructions(Pattern patternInstructions, String line) {
		
		List<Instruction> instructions = new ArrayList<>();
		
		// Instructions
		Matcher matcher=patternInstructions.matcher(line);
		if (matcher.find()) {
			for(String instruction : matcher.group(1).split("")) {
				instructions.add(Instruction.valueOf(instruction));
			}
		} else {
			throw new IllegalArgumentException(ERROR_FORMAT_FICHIER_INCORRECT);
		}
		return instructions;
	}
}
