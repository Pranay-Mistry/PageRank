// Pranay Mistry cs435 2805 mp
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MakeGraph2805 {
    public double[][] buildGraph;
    int vertexNum;
    int edgesNum;
    String fileN;
    double initValue;

    public MakeGraph2805(String fileN, double initValue) {
        this.fileN = fileN;
        this.initValue = initValue;
        BuildGraph();
    }
    public double valueAt(int i, int j){
        return buildGraph[i][j];
    }

    public void BuildGraph() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileN));
            String line;
            line = reader.readLine();
            String temp[] = line.split(" ");
            vertexNum = Integer.parseInt(temp[0]);
            edgesNum = Integer.parseInt(temp[1]);
            buildGraph = new double[vertexNum][vertexNum];
            while ((line = reader.readLine()) != null) {
                temp = line.split(" ");
                int i = Integer.parseInt(temp[0]);
                int j = Integer.parseInt(temp[1]);
                buildGraph[j][i] = 1;
            }
            for (int k = 0; k < vertexNum; k++) {
                int numDegree = numOutDegrees(k);
                if (numDegree > 1) {
                    for (int l = 0; l < vertexNum; l++) {
                        if (buildGraph[l][k] == 1)
                            buildGraph[l][k] = 1.0 / numDegree;
                    }
                }
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void printGraph() {
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                System.out.print(buildGraph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int numOutDegrees(int v) {
        int degree = 0;
        for (int c = 0; c < vertexNum; c++) {
            if (buildGraph[c][v] != 0)
                degree++;
        }
        return degree;
    }

}