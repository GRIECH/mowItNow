package fr.xebia.mowItNow.app.controller;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.xebia.mowItNow.app.domain.AutoPilotParameters;
import fr.xebia.mowItNow.app.services.AutoPilotService;
import fr.xebia.mowItNow.app.domain.Mower;

@Controller
public class AutoPilotController {

	@Autowired
	private AutoPilotService autoPilotService;

	/**
	 * Démarrer le pilotage automatique
	 * @param pelouse
	 * @param tondeuses
	 */
	public List<Mower> startAutoPilot(AutoPilotParameters autoPilotParameters) {
		List<Mower> mowersPiloted = new ArrayList<>();
		if (!isEmpty(autoPilotParameters.getMowers())) {
			for (Mower mower : autoPilotParameters.getMowers()) {
				autoPilotService.autoPilot(autoPilotParameters.getLawn(), mower);
				mowersPiloted.add(mower);
			}
		} else {
			throw new IllegalArgumentException("Aunce tondeuse n'est déployé sur la pelouse");
		}
		return mowersPiloted;
	}
}
