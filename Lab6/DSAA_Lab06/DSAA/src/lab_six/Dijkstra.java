package lab_six;


import java.util.Arrays;

public class Dijkstra {
    private final double[][] graph;
    private final int N;

    public Dijkstra(double[][] graph) throws Exception {
        if (graph == null || graph.length <= 0 || graph[0].length != graph.length) {
            throw new Exception("Null distance array");
        }
        N = graph.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                if (graph[i][j] != graph[j][i] || graph[i][j] < 0)
                    throw new Exception("Invalid Graph");
        this.graph = graph;
    }

    public String[] shortest(int start) throws Exception {
        if (0 > start || start >= N) {
            throw new Exception("Invalid vertex");
        }
        boolean[] visit = new boolean[N];
        visit[start] = true;
        double[] minDis = new double[N];
        String[] path = new String[N];
        for (int i = 0; i < N; i++) {
            path[i] = String.format("%d-->%d", start, i);
        }
        double[][] distance = new double[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(graph[i], 0, distance[i], 0, N);
        }
        double max = Double.MAX_VALUE;
        
        // todo: complete code here
        Arrays.fill(visit, false);
        Arrays.fill(minDis,0);
        visit[start]=true;
        for(int k=0;k<N;k++) { //循环中每次确定一个新的确定最短路径的节点
            double dmin=max;
            int position=0;
            //找出一个未标记的离出发点最近的节点
            for(int i=0;i<N;i++) {
                    if (!visit[i] && distance[start][i] < dmin&&i!=start) {
                            dmin = distance[start][i];
                            position = i;
                    }
            }
            //标记该节点为已经访问过
            visit[position]=true;
            for(int i=0;i<N;i++) {
                minDis = distance[start];
                    if (distance[start][position] + distance[position][i] < distance[start][i]&&i!=position) {
                        double temp = distance[start][position] + distance[position][i];
                        distance[start][i] = temp;
                        minDis[i] = temp;
                        if (start!=i) {
                            path[i] = path[position] + "-->" + i;
                        }
                    }
            }
        }
        minDis[start]=0;

        String[] allShortest = new String[N];
        for (int i = 0; i < N; i++) {
            allShortest[i] = String.format("distance from %3d to %3d: %7.2f, path: %s", start, i, minDis[i], path[i]);
        }
        return allShortest;
    }

    public String shortest(int start, int end) throws Exception {
        String[] allShortest = shortest(start);
        if (0 > end || end >= N) {
            throw new Exception("Invalid vertex");
        }
        return allShortest[end];
    }

}
