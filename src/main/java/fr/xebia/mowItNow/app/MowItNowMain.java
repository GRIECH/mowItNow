package fr.xebia.mowItNow.app;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.xebia.mowItNow.app.config.MowItNowConfig;
import fr.xebia.mowItNow.app.controller.AutoPilotController;
import fr.xebia.mowItNow.app.domain.AutoPilotParameters;
import fr.xebia.mowItNow.app.services.FileParserService;
import fr.xebia.mowItNow.app.domain.Mower;

public class MowItNowMain {

	public static void main(String[] args) throws IOException {
		
		if(args.length != 1) {
			System.out.println("Le fichier d'entrée est obligatoire");
		}
		
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MowItNowConfig.class)) {

			// Parser le fichier
	    	FileParserService fileParserService = context.getBean(FileParserService.class);
	    	AutoPilotParameters autoPilotParameters = fileParserService.parse(args[0]);
	    	
	    	// Démarrer le pilotage automatique
	    	AutoPilotController autoPilotController = context.getBean(AutoPilotController.class);
	    	List<Mower> mowers = autoPilotController.startAutoPilot(autoPilotParameters);
	    	
	    	// Ecrire les résultats
	    	for (Mower mower : mowers) {
	    		System.out.println(mower);
	    	}
	    	
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
    }
}
