package maze_escape;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimMazeEscapeMainTest {

    private static final long SEED = 20231113L;
    private static final int WIDTH = 100;
    private static final int HEIGHT = WIDTH;
    private static final int REMOVE = 250;
    private Set<Integer> vertices;
    private Maze maze;

    @BeforeEach
    public void setUp() {
        Maze.reSeedRandomizer(SEED);
        maze = new Maze(WIDTH, HEIGHT);
        maze.generateRandomizedPrim();
        maze.configureInnerEntry();
        maze.removeRandomWalls(REMOVE);
         this.vertices = maze.getAllVertices(maze.getStartNode());
    }

    @Test
    public void testDFSOutput(){
        Maze.GPath path = maze.depthFirstSearch(maze.getStartNode(), maze.getExitNode());
        if (path.getTotalWeight() == 0.0) path.reCalculateTotalWeight(maze::manhattanTime);
        Assertions.assertEquals(5167, path.getVisited().size());
        Assertions.assertEquals(463, path.getTotalWeight());
        Assertions.assertEquals(162, path.getVertices().size());
    }
    @Test
    public void testBFSOutput(){
        Maze.GPath path = maze.breadthFirstSearch(maze.getStartNode(), maze.getExitNode());
        if (path.getTotalWeight() == 0.0) path.reCalculateTotalWeight(maze::manhattanTime);
        Assertions.assertEquals(5126, path.getVisited().size());
        Assertions.assertEquals(226, path.getTotalWeight());
        Assertions.assertEquals(79, path.getVertices().size());
    }
    @Test
    public void testDijkstraShortestPathOutput(){
        Maze.GPath path = maze.dijkstraShortestPath(maze.getStartNode(), maze.getExitNode(), (v1,v2)-> maze.manhattanTime(v1, v2));
        if (path.getTotalWeight() == 0.0) path.reCalculateTotalWeight(maze::manhattanTime);
        Assertions.assertEquals(5233, path.getVisited().size());
        Assertions.assertEquals(226, path.getTotalWeight());
        Assertions.assertEquals(79, path.getVertices().size());
    }


}
