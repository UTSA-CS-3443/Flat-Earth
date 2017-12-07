# Flat-Earth

Configuring and running on eclipse:

Open FlatEarth file tree
	right click build.gradle
	gradle > refresh gradle project (you need Internet access for this)
	should start downloading dependencies, let it finish
	
Click Run menu option at the top
	run > run configurations 
	in the window, select the "arguments" tab
	in working directory section, click workspace
		select Sample-core/assets
		hit ok
	hit apply

use java 8 (if not by default). Java 9 will give errors with a dependency

The main method is in the Sample-Desktop folder, so click that folder before hitting:
	run as > Java Application
		select DesktopLauncher
	
	
	
Choose your character and hit solo play. See below for multi player options
For settings, select Keyboard or controller (xbox controller)

-----------------------


# Multi Player

Still in alpha