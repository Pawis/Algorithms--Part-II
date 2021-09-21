import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;

public class BaseballElimination {
    private final ArrayList<String> teams;
    private final int[] w;
    private final int[] loss;
    private final int[] r;
    private final int[][] g;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In input = new In(filename);
        this.teams = new ArrayList<>();
        int n = Integer.parseInt(input.readLine());
        this.w = new int[n];
        this.loss = new int[n];
        this.r = new int[n];
        this.g = new int[n][n];

        int i = 0;
        while (i < n) {
            String[] parts = input.readLine().trim().split("\\s+");
            teams.add(parts[0]);
            w[i] = Integer.parseInt(parts[1]);
            loss[i] = Integer.parseInt(parts[2]);
            r[i] = Integer.parseInt(parts[3]);
            for (int j = 0; j < n; j++) {
                g[i][j] = Integer.parseInt(parts[j + 4]);
            }
            i++;
        }
    }

    // number of teams
    public int numberOfTeams() {
        return w.length;
    }

    // all teams
    public Iterable<String> teams() {
        return teams;
    }

    // number of wins for given team
    public int wins(String team) {
        if (!teams.contains(team))
            throw new IllegalArgumentException();
        return w[teams.indexOf(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!teams.contains(team))
            throw new IllegalArgumentException();
        return loss[teams.indexOf(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!teams.contains(team))
            throw new IllegalArgumentException();
        return r[teams.indexOf(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (!teams.contains(team1) || !teams.contains(team2))
            throw new IllegalArgumentException();
        return g[teams.indexOf(team1)][teams.indexOf(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (!teams.contains(team))
            throw new IllegalArgumentException();
        for (int i = 0; i < numberOfTeams(); i++) {
            if (w[teams.indexOf(team)] + r[teams.indexOf(team)] < w[i]) {
                return true;
            }
        }

        FlowNetwork flowNet = new FlowNetwork((((numberOfTeams() - 1) * ((numberOfTeams() - 1) - 1) / 2)) + numberOfTeams() - 1 + 2);
        int k = numberOfTeams();
        int capacity = 0;
        for (int i = 0; i < numberOfTeams(); i++) {
            for (int j = i + 1; j < numberOfTeams(); j++) {
                if (i == teams.indexOf(team) || j == teams.indexOf(team))
                    continue;
                FlowEdge edge = new FlowEdge(flowNet.V() - 1, k, g[i][j]);
                capacity += g[i][j];
                FlowEdge edge1 = new FlowEdge(k, i, Double.POSITIVE_INFINITY);
                FlowEdge edge2 = new FlowEdge(k, j, Double.POSITIVE_INFINITY);
                flowNet.addEdge(edge);
                flowNet.addEdge(edge1);
                flowNet.addEdge(edge2);
                k++;
            }
        }
        for (int i = 0; i < numberOfTeams(); i++) {
            FlowEdge edge = new FlowEdge(i, teams.indexOf(team), (w[teams.indexOf(team)] + r[teams.indexOf(team)] - w[i]));
            flowNet.addEdge(edge);
        }
        FordFulkerson foFu = new FordFulkerson(flowNet, flowNet.V() - 1, teams.indexOf(team));
        return capacity > foFu.value();

    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!isEliminated(team))
            return null;
        Stack<String> a = new Stack<>();
        if (!teams.contains(team))
            throw new IllegalArgumentException();
        for (int i = 0; i < numberOfTeams(); i++) {
            if (w[teams.indexOf(team)] + r[teams.indexOf(team)] < w[i]) {
                a.push(teams.get(i));
                return a;
            }
        }
        FlowNetwork flowNet = new FlowNetwork((((numberOfTeams() - 1) * ((numberOfTeams() - 1) - 1) / 2)) + numberOfTeams() - 1 + 2);
        int k = numberOfTeams();
        for (int i = 0; i < numberOfTeams(); i++) {
            for (int j = i + 1; j < numberOfTeams(); j++) {
                if (i == teams.indexOf(team) || j == teams.indexOf(team))
                    continue;
                FlowEdge edge = new FlowEdge(flowNet.V() - 1, k, g[i][j]);
                FlowEdge edge1 = new FlowEdge(k, i, Double.POSITIVE_INFINITY);
                FlowEdge edge2 = new FlowEdge(k, j, Double.POSITIVE_INFINITY);
                flowNet.addEdge(edge);
                flowNet.addEdge(edge1);
                flowNet.addEdge(edge2);
                k++;
            }
        }
        for (int i = 0; i < numberOfTeams(); i++) {
            FlowEdge edge = new FlowEdge(i, teams.indexOf(team), (w[teams.indexOf(team)] + r[teams.indexOf(team)] - w[i]));
            flowNet.addEdge(edge);
        }
        FordFulkerson foFu = new FordFulkerson(flowNet, flowNet.V() - 1, teams.indexOf(team));
        for (int i = 0; i < numberOfTeams(); i++) {
            if (foFu.inCut(i))
                a.push(teams.get(i));
        }
        return a;
    }

    public static void main(String[] args) {
        /*
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }

         */
    }
}

