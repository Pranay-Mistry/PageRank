// Pranay Mistry cs 435 mp
import java.util.Arrays;

public class Main2805 {
    public static void main(String args[]) {
        int iteration;
        int initVal;
        double d = 0.85;
        double c;
        int difference = 0;
        int num = 1;
        String file = "";
        double total;
        iteration = Integer.parseInt(args[0]);
        initVal = Integer.parseInt(args[1]);
        double val;
        file = args[2];
        MakeGraph2805 graph = new MakeGraph2805(file, initVal);
        double currRank[] = new double[graph.vertexNum];
        double oldRank[] = new double[graph.vertexNum];
        if (initVal == -1)
            val = 1.0 / graph.vertexNum;
        else if (initVal == -2)
            val = 1.0 / Math.sqrt(graph.vertexNum);
        else
            val = initVal;
        Arrays.fill(oldRank, val);
        if (graph.vertexNum < 10) {
            System.out.print("Base \t\t:");
            for (int i = 0; i < oldRank.length; i++) {
                System.out.print("P(" + i + ")= " + String.format("%.6f", oldRank[i]) + " ");
            }
        }
        System.out.println();
        while (true) {
            for (int j = 0; j < graph.vertexNum; j++) {
                total = 0.0;
                for (int k = 0; k < graph.vertexNum; k++) {
                    if (graph.valueAt(j, k) != 0) {
                        c = graph.numOutDegrees(k);

                        total += (oldRank[k] / c);
                    }
                }
                currRank[j] = ((1 - d) / graph.vertexNum) + (d * total);
            }
            if (num <= iteration && iteration > 0) {
                System.out.print("iter\t" + num + " \t:");
                for (int n = 0; n < currRank.length; n++) {
                    System.out.print("P(" + n + ")= " + String.format("%.6f", currRank[n]) + " ");
                }
                System.out.println();
            }


            if (num == iteration && iteration > 0)
                System.exit(0);
            else if (iteration == 0) {

                for (int i = 0; i < oldRank.length; i++) {
                    if ((oldRank[i] - currRank[i]) > 0.0001) {
                        difference++;
                    }
                }
                if (difference != 0 && graph.vertexNum < 10) {
                    System.out.print("iter\t" + num + " \t:");
                    for (int n = 0; n < currRank.length; n++) {
                        System.out.print("P(" + n + ")= " + String.format("%.6f", currRank[n]) + " ");
                    }
                    System.out.println();
                }
                if (difference == 0 && graph.vertexNum > 10) {
                    System.out.println("iter : " + num);
                    for (int n = 0; n < graph.vertexNum; n++) {
                        System.out.println("P(" + n + ") = " + currRank[n]);
                    }
                    System.out.println();
                    System.exit(0);

                } else if (difference == 0) {
                    System.out.print("iter\t" + num + " \t:");
                    for (int n = 0; n < currRank.length; n++) {
                        System.out.print("P(" + n + ")= " + String.format("%.6f",currRank[n]) + " ");
                    }
                    System.out.println();
                    System.exit(0);
                }
            }

            difference = 0;
            num++;
            for (int m = 0; m < oldRank.length; m++) {
                oldRank[m] = currRank[m];
            }
        }

    }
}
