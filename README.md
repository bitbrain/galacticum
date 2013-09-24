Galacticum
==========
Galacticum is a space game written in Java (Slick2D and Chronos).

About
-----
This game is still under development. The following features are planned:

* 2D game with fancy pixel graphics
* Infinite 2D space with generated space backgrounds
* Random generated enemies, items and regions
* Many different boss enemies with strong abilities
* Collect resources to build your own ships and weapons
* Full generated planets with own research level and resources
* Single parts of ships like weapons or shields are destroyable
* Features like leveling, skilling and weapon-building

Prototype
---------
The prototype has reached a playable state. You can see space battles, many different enemies and a full working shield system in
[this video](http://www.youtube.com/watch?v=sGOPboD2_CA/ "Galacticum Prototype video").

Getting started
---------
After cloning this repository, you need to download [LibGDX](http://libgdx.badlogicgames.com) and [Chunx](https://github.com/MyRealityCoding/chunx).

### LibGDX

Please run ```libgdx-setup-ui-jar``` to upgrade the libraries of your local repository.

### Chunx

Create an user library, called ```chunx``` and add the jar file to it.

Project structure
---------

The following explains how the structure of this project looks like:

```
licence.txt                <- licence document
README.md                  <- README document
meta.json                  <- meta information file

galacticum/				   <- core project which contains game logic
	src/

galacticum-html/		   <- HTML port for galacticum
	src/

galacticum-android/		   <- Android port for galacticum.Contains assets.
	src/

galacticum-desktop/	       <- Desktop port for galacticum
	src/

galacticum-ios/			   <- iOS port for galacticum
	Main.cs
```

Stay connected
--------------
Follow me [on twitter](https://twitter.com/tweetmyreality/ "Miguel's twitter") or send me [an email](mailto:miguel-gonzalez@gmx.de "Miguel's Email") for further questions.

