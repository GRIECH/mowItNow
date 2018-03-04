package fr.xebia.mowItNow.app.services.impl;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.xebia.mowItNow.app.domain.Instruction;
import fr.xebia.mowItNow.app.domain.Lawn;
import fr.xebia.mowItNow.app.domain.Mower;
import fr.xebia.mowItNow.app.services.LawnService;
import fr.xebia.mowItNow.app.services.AutoPilotService;
import fr.xebia.mowItNow.app.services.MowerService;

@Service
public class AutoPilotServiceImpl implements AutoPilotService {
	
	@Autowired
	public LawnService lawnService;
	
	@Autowired
	public MowerService mowerService;

	/* (non-Javadoc)
	 * @see fr.xebia.mowItNow.tondeuse.services.impl.PilotageService#piloter(fr.xebia.mowItNow.tondeuse.domain.Pelouse, fr.xebia.mowItNow.tondeuse.domain.Tondeuse)
	 */
	@Override
	public void autoPilot(Lawn lawn, Mower mowers) {
		if (!isEmpty(mowers.getInstructions())) {
			for (Instruction instruction : mowers.getInstructions()) {
				executeInstruction(lawn, mowers, instruction);
			}
		} else {
			throw new IllegalArgumentException("La tondeuse n'est pas programmée");
		}
	}
	
	private void executeInstruction(Lawn lawn, Mower mower, Instruction instruction) {
		switch (instruction) {
		case A:
			if (lawnService.canMove(lawn, mower.getPoint(), mower.getOrientation())) {
				mower.setPoint(mowerService.move(mower));
			}
		default:
			mower.setOrientation(
					mowerService.pivot(mower.getOrientation(), instruction));
			break;
		}
	}
}