import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomTests {
    public static void testAStarAlgorithm() {
        //Create the stops based off 2018 exam question
        Stop s = new Stop(2.24, 2.24, "S", "S");
        Stop b = new Stop(0, 3, "B", "B");
        Stop c = new Stop(1.4, 1.4, "C", "C");
        Stop d = new Stop(1, 0, "D", "D");
        Stop e = new Stop(0, 1, "E", "E");
        Stop g = new Stop(0, 0, "G", "G");

        Edge s_to_c = new Edge(s, c, "bus", null, 2, 4);
        Edge s_to_b = new Edge(s, b, "bus", null, 1.5, 3);

        Edge b_to_s = new Edge(b, s, "bus", null, 1.5, 3);
        Edge b_to_d = new Edge(b, d, "bus", null, 3, 6);

        Edge c_to_s = new Edge(c, s, "bus", null, 2, 4);
        Edge c_to_e = new Edge(c, e, "bus", null, 1, 2);
        Edge c_to_d = new Edge(c, d, "bus", null, 1.5, 3);

        Edge d_to_b = new Edge(d, b, "bus", null, 3, 6);
        Edge d_to_c = new Edge(d, c, "bus", null, 1.5, 3);
        Edge d_to_g = new Edge(d, g, "bus", null, 1, 2);

        Edge e_to_c = new Edge(e, c, "bus", null, 1, 2);
        Edge e_to_g = new Edge(b, d, "bus", null, 2, 4);

        s.addForwardEdge(s_to_c);
        s.addForwardEdge(s_to_b);

        b.addForwardEdge(b_to_s);
        b.addForwardEdge(b_to_d);

        c.addForwardEdge(c_to_s);
        c.addForwardEdge(c_to_e);
        c.addForwardEdge(c_to_d);

        d.addForwardEdge(d_to_b);
        d.addForwardEdge(d_to_c);
        d.addForwardEdge(d_to_g);

        e.addForwardEdge(e_to_c);
        e.addForwardEdge(e_to_g);

        List<Edge> shortestPath = AStar.findShortestPath(s, g, "distance");
        List<Edge> correctAns = List.of(
                s_to_c,
                c_to_d,
                d_to_g
        );

        boolean testPassed = false;
        try {
            testPassed = shortestPath.get(0).equals(s_to_c) &&
                    shortestPath.get(1).equals(c_to_d) &&
                    shortestPath.get(2).equals(d_to_g);
        }
        catch (Exception ignored) {}

        System.out.println("===========================================");
        System.out.println("A* Algorithm Test!");
        if(testPassed) { System.out.println("Your A* algorithm works! (on a basic example at least) :)"); }
        else {
            System.out.println("Your A* algorithm fails :(");
            System.out.println("Expected output: " + correctAns);
            System.out.println("Actual output: " + shortestPath);
        }
        System.out.println("===========================================\n");
    }
    public static void testReconstructPath() {

        Stop nowhere = new Stop(0, 0, "NOWHERE", "NOWHERE");
        Stop s = new Stop(0, 0, "S", "S");
        Stop b = new Stop(0, 0, "B", "B");
        Stop d = new Stop(0, 0, "D", "D");
        Stop c = new Stop(0, 0, "C", "C");
        Stop e = new Stop(0, 0, "E", "E");
        Stop g = new Stop(0, 0, "G", "G");


        Edge startingEdge = new Edge(nowhere, s, "START", null, 0, 0);
        Edge s_to_b = new Edge(s, b, "bus", null, 0, 0);
        Edge s_to_c = new Edge(s, c, "bus", null, 0, 0);
        Edge c_to_e = new Edge(c, e, "bus", null, 0, 0);
        Edge e_to_g = new Edge(e, g, "bus", null, 0, 0);
        Edge c_to_d = new Edge(c, d, "bus", null, 0, 0);
        Edge d_to_g = new Edge(d, g, "bus", null, 0, 0);

        Map<Stop, Edge> backPointers = Map.of(
                s, startingEdge,
                b, s_to_b,
                c, s_to_c,
                e, c_to_e,
                d, c_to_d,
                g, d_to_g
        );

        List<Edge> reconstructedPath = AStar.reconstructPath(s, g, backPointers);
        List<Edge> correctAns = List.of(
                s_to_c,
                c_to_d,
                d_to_g
        );
        boolean testPassed = false;

        try {
            testPassed = reconstructedPath.get(0).equals(s_to_c) &&
                    reconstructedPath.get(1).equals(c_to_d) &&
                    reconstructedPath.get(2).equals(d_to_g);
        }
        catch (Exception ignored) {}

        System.out.println("===========================================");
        System.out.println("Reconstructed Path Algorithm Test!");
        if(testPassed) { System.out.println("Your reconstructed path algorithm works! (on a basic example at least) :)"); }
        else {
            System.out.println("Your reconstructed path algorithm fails :(");
            System.out.println("Expected output: " + correctAns);
            System.out.println("Actual output: " + reconstructedPath);
        }
        System.out.println("===========================================");
    }

    /**
     * Helper method to test find components actually works
     * Based off the lecture slides
     * */
    public static void testFindComponents() {
        int CORRECT_NUMBER_OF_SUBGRAPHS = 5;

        Stop a = new Stop(0, 0, "A", "A");
        Stop b = new Stop(0, 0, "B", "B");
        Stop c = new Stop(0, 0, "C", "C");
        Stop d = new Stop(0, 0, "D", "D");
        Stop e = new Stop(0, 0, "E", "E");

        Stop p = new Stop(0, 0, "P", "P");
        Stop q = new Stop(0, 0, "Q", "Q");
        Stop r = new Stop(0, 0, "R", "R");
        Stop s = new Stop(0, 0, "S", "S");
        Stop t = new Stop(0, 0, "T", "T");
        Stop u = new Stop(0, 0, "U", "U");

        Edge a_to_b = new Edge(a, b, "bus", null, 0, 0);
        Edge a_to_c = new Edge(a, c, "bus", null, 0, 0);

        Edge b_to_c = new Edge(b, c, "bus", null, 0, 0);

        Edge c_to_d = new Edge(c, d, "bus", null, 0, 0);
        Edge c_to_p = new Edge(c, p, "bus", null, 0, 0);

        Edge d_to_a = new Edge(d, a, "bus", null, 0, 0);
        Edge d_to_e = new Edge(d, e, "bus", null, 0, 0);

        //SECOND HALF
        Edge p_to_s = new Edge(p, s, "bus", null, 0, 0);

        Edge q_to_p = new Edge(q, p, "bus", null, 0, 0);
        Edge q_to_t = new Edge(q, t, "bus", null, 0, 0);

        Edge r_to_q = new Edge(r, q, "bus", null, 0, 0);

        Edge s_to_r = new Edge(s, r, "bus", null, 0, 0);

        Edge t_to_u = new Edge(t, u, "bus", null, 0, 0);

        //Now add the pointers and stuff
        a.addForwardEdge(a_to_b);
        a.addForwardEdge(a_to_c);
        a.addBackwardEdge(d_to_a);

        b.addForwardEdge(b_to_c);
        b.addBackwardEdge(a_to_b);

        c.addForwardEdge(c_to_d);
        c.addForwardEdge(c_to_p);
        c.addBackwardEdge(a_to_c);
        c.addBackwardEdge(b_to_c);

        d.addForwardEdge(d_to_a);
        d.addForwardEdge(d_to_e);
        d.addBackwardEdge(c_to_d);

        e.addBackwardEdge(d_to_e);

        //NEXT HALF
        p.addForwardEdge(p_to_s);
        p.addBackwardEdge(c_to_p);
        p.addBackwardEdge(q_to_p);

        q.addForwardEdge(q_to_p);
        q.addForwardEdge(q_to_t);
        q.addBackwardEdge(r_to_q);

        r.addForwardEdge(r_to_q);
        r.addBackwardEdge(s_to_r);

        s.addForwardEdge(s_to_r);
        s.addBackwardEdge(p_to_s);

        t.addForwardEdge(t_to_u);
        t.addBackwardEdge(q_to_t);

        u.addBackwardEdge(t_to_u);

        List<Stop> testStops = List.of(a,b,c,d,e,p,q,r,s,t,u);
        List<Line> emptyLineList = new ArrayList<>();

        Graph testGraph = new Graph(testStops, emptyLineList);
        Components.findComponents(testGraph);
        int numberOfSubGraphs = testGraph.getSubGraphCount();

        boolean testPassed = numberOfSubGraphs == CORRECT_NUMBER_OF_SUBGRAPHS;

        System.out.println("\n===========================================");
        System.out.println("Find Components Algorithm Test!");
        if(testPassed) { System.out.println("Your find components algorithm works! (on a basic example at least) :)"); }
        else {
            System.out.println("Your find components algorithm fails :(");
            System.out.println("Expected numOfSubGraphs: " +CORRECT_NUMBER_OF_SUBGRAPHS);
            System.out.println("Actual numOfSubGraphs: " + numberOfSubGraphs);
        }
        System.out.println("===========================================\n");
    }

    /**
     * Helper method to test Articulation points
     */
    public static void testArticulationPoints() {

        Stop a = new Stop(0, 0, "A", "A");
        Stop b = new Stop(0, 0, "B", "B");
        Stop c = new Stop(0, 0, "C", "C");
        Stop d = new Stop(0, 0, "D", "D");
        Stop e = new Stop(0, 0, "E", "E");
        Stop f = new Stop(0, 0, "F", "F");
        Stop g = new Stop(0, 0, "G", "G");
        Stop h = new Stop(0, 0, "H", "H");

        a.addNeighbour(b);
        a.addNeighbour(e);

        b.addNeighbour(a);
        b.addNeighbour(e);
        b.addNeighbour(c);
        b.addNeighbour(h);

        c.addNeighbour(d);
        c.addNeighbour(b);

        d.addNeighbour(c);
        d.addNeighbour(e);

        e.addNeighbour(a);
        e.addNeighbour(b);
        e.addNeighbour(d);

        //SECOND HALF
        h.addNeighbour(b);
        h.addNeighbour(f);

        f.addNeighbour(h);
        f.addNeighbour(g);

        g.addNeighbour(f);
        g.addNeighbour(h);

        List<Stop> testStops = List.of(a,b,c,d,e,f,g,h);
        List<Line> emptyLineList = new ArrayList<>();
        Graph testGraph = new Graph(testStops, emptyLineList);

        List<Stop> ourResult = new ArrayList<>(ArticulationPoints.findArticulationPoints(testGraph));
        List<Stop> correctResult = List.of(
                h,
                b
        );
        boolean testPassed = ourResult.contains(h) && ourResult.contains(b);

        System.out.println("\n===========================================");
        System.out.println("Test Articulation Algorithm Test!");
        if(testPassed) { System.out.println("Your test articulation algorithm works! (on a basic example at least) :)"); }
        else {
            System.out.println("Your test articulations algorithm fails :(");
            System.out.println("Expected output: " + correctResult);
            System.out.println("Actual output: " + ourResult);
        }
        System.out.println("===========================================");
    }
    public static void main(String[] args) {
        CustomTests.testReconstructPath();
        CustomTests.testAStarAlgorithm();
        CustomTests.testFindComponents();
        CustomTests.testArticulationPoints();
    }
}
