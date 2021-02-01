### Maze-generator
The goal of the game is to move the green cell to the bottom right corner of the grid.
The generation of the maze is based on the randomised version of Kruskal's algorithm. 
For more details, please look at https://en.wikipedia.org/wiki/Maze_generation_algorithm

### Technologies
* Java SE 11

### Running the app locally
1. Clone the repository.
2. Execute the following commands from the root of the project:
```
mvn package
java -jar target/MazePuzzle.jar

```

### TODO 
* Add some tests
* The current position(the green cell) is not displayed ideally. An improvement is needed.
