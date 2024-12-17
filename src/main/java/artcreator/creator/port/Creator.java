package artcreator.creator.port;


import artcreator.config.Configuration;

public interface Creator {
	void loadImageFromPath(String path);
	void startGeneration();
}
