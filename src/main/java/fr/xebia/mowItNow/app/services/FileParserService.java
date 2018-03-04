package fr.xebia.mowItNow.app.services;

import java.io.IOException;

import fr.xebia.mowItNow.app.domain.AutoPilotParameters;

public interface FileParserService {

	AutoPilotParameters parse(String fileName) throws IOException;

}