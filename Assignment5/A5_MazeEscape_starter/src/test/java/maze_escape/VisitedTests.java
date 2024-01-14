package maze_escape;

import org.junit.jupiter.api.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class VisitedTests {

    Country nl, be, de, lux, fr, uk, ro, hu;
    Continent europe = new Continent();
    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @BeforeEach
    void setUp() {
        nl = new Country("NL", 18);
        be = new Country("BE", 12);
        nl.addBorder(be,100);
        de = new Country("DE", 83);
        de.addBorder(nl,200);
        de.addBorder(be,30);
        lux = new Country("LUX",1);
        lux.addBorder(be,60);
        lux.addBorder(de,50);
        fr = new Country("FR", 67);
        fr.addBorder(lux,30);
        fr.addBorder(be,110);
        fr.addBorder(de,50);
        uk = new Country("UK", 67);
        uk.addBorder(be,70);
        uk.addBorder(fr,150);
        uk.addBorder(nl,250);

        ro = new Country("RO", 19);
        hu = new Country("HU", 10);
        ro.addBorder(hu,250);
    }

    @Test
    void testDijkstraShortestPathVisited() {
        // Perform Dijkstra's algorithm
        AbstractGraph<Country>.GPath dijkstraPath = europe.dijkstraShortestPath(uk, nl, Country::distanceTo);

        // Validate the correctness of visited set
        assertNotNull(dijkstraPath);
        // Adjust the expected visited size based on your specific graph and test data
        assertEquals(6, dijkstraPath.getVisited().size());
    }

    @Test
    void testDepthFirstSearchVisited() {
        // Perform Depth-First Search
        AbstractGraph<Country>.GPath dfsPath = europe.depthFirstSearch(uk, nl);

        // Validate the correctness of visited set
        assertNotNull(dfsPath);
        // Adjust the expected visited size based on your specific graph and test data
        assertEquals(6, dfsPath.getVisited().size());
    }

    @Test
    void testBreadthFirstSearchVisited() {
        // Perform Breadth-First Search
        AbstractGraph<Country>.GPath bfsPath = europe.breadthFirstSearch(uk, nl);

        // Validate the correctness of visited set
        assertNotNull(bfsPath);
        // Adjust the expected visited size based on your specific graph and test data
        assertEquals(3, bfsPath.getVisited().size());
    }
}
