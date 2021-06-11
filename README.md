## LotkaVolterra
Project prepared as part of the university course "Numerical methods". This is an updated and free of JavaFX library import issues version of original project https://github.com/IzabelaPopiel/LotkaVolterraGUI. 

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)

## General info
This project is simple simulation of dynamics of interaction between two spieces (predator and prey) basing on Lotka–Volterra model [https://en.wikipedia.org/wiki/Lotka%E2%80%93Volterra_equations].
The populations change in time can be described using equations:

![image](https://user-images.githubusercontent.com/44273512/121685133-dec5c780-cabf-11eb-8fbe-1a77717cfd0e.png)

where:
* x - prey population
* y - predator population
* a - reproduction rate of prey
* b - mortality rate of prey
* c - reproduction rate of predator
* d - mortality rate of predator

The application creates a phase space chart of the population of preys and predators basing on input parameters.

## Screenshots
![image](https://user-images.githubusercontent.com/44273512/121686101-09fce680-cac1-11eb-98dc-5a856407f344.png)
![image](https://user-images.githubusercontent.com/44273512/121686367-5c3e0780-cac1-11eb-806e-c7e566624a3f.png)


## Technologies
Project is created with:
* Java 11
* JavaFx 11.0.2
* Apache Commons Math 3.6.1
	
