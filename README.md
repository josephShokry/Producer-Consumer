# Producer-Consumer-back-end
## This project simulate Assembly Machine production

Assignment #5: Producer/Consumer Simulation Program (CSED25 Alexandria University)

### project objectives
- Design an object-oriented queueing simulation program.
- Apply different design patterns such as concurrency DP, snapshot DP, and observer DP.
- working with multi threads. 

### Description 
An assembly line that produces different products consists of different processing machines Ms that are
responsible for processing the product at different stages and queue Qs to handle product movement
between different processing stages (see figure below). In this assignment, we will develop a simulation
program to simulate this production line as a queuing network.
![img](https://user-images.githubusercontent.com/96181216/215475359-806d9422-fe6e-4d4a-bb99-3e058cecfea5.png)


### System features
- Users add graphically as many as they want queues and machines. 
- The first queue has a random input rate. 
- Each machine work in a different thread. 
- Each product has a random color when it enters a machine it clones its color to the machine. 
- Resimulate the system after it finishes the simulation.

### Implemented Design Patterns
- Factory design pattern 
- Facade design pattern 
- Observable design pattern 
- Memento design pattern (snapshot)
- Producer-consumer design pattern

### the demo
https://user-images.githubusercontent.com/96320048/215320441-474453da-f381-4694-be33-214b8b2e66b2.mp4



