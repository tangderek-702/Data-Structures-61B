package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private int statesTouched;
    private double timeSpent;
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private Vertex end;

    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private DoubleMapPQ<Vertex> fringe;



    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input) {
        if (input == null) {
            return;
        }
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (!distTo.containsKey(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, e.from());
            fringe.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
        } else if (distTo.get(p) + w < distTo.get(q)) {
            distTo.replace(q,distTo.get(p) + w);
            edgeTo.replace(q, p);
            if(fringe.contains(q)) {
                fringe.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            } else {
                fringe.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch timer = new Stopwatch();  //Timer for timeSpent

        this.end = end;

        //containers
        solution = new ArrayList<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        fringe = new DoubleMapPQ<>();   //create PQ

        //helper variables
        Vertex curr;
        List<WeightedEdge<Vertex>> neighbors;

        //Starting place
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        statesTouched = 0;

        while (fringe.size() > 0) {
            statesTouched++;

            //HArd code edge case
            if (start.equals(end)) {
                solution.add(start);
                outcome = SolverOutcome.SOLVED;
                timeSpent = timer.elapsedTime();
                break;
            }

            curr = fringe.removeSmallest();
            neighbors = input.neighbors(curr);

            for (WeightedEdge<Vertex> edge : neighbors) {
                if (!edge.to().equals(curr)) {
                    relax(edge, input);
                }
            }
            //handling cases

            //empty fringe
            if (fringe.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                break;
            //time's up
            } else if (timer.elapsedTime() >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            //reached end!
            } else if (fringe.getSmallest().equals(end) && timer.elapsedTime() < timeout) {
                //construct solution list
                //solution.add(end);
                curr = fringe.getSmallest();
                while (!curr.equals(start)) {
                    solution.add(curr);
                    curr = edgeTo.get(curr);
                }
                solution.add(start);
                Collections.reverse(solution);
                outcome = SolverOutcome.SOLVED;
                timeSpent = timer.elapsedTime();
                break;
            }
            timeSpent = timer.elapsedTime();    //update time
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return distTo.get(end);
    }

    @Override
    public int numStatesExplored() {
        return statesTouched;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }

}
