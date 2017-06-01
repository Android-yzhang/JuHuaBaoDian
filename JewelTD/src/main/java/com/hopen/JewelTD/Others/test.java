package com.hopen.JewelTD.Others;

import com.hopen.JewelTD.AStar.AStar;
import com.hopen.JewelTD.AStar.MapInfo;
import com.hopen.JewelTD.AStar.Node;

/**
 * Created by yzhang on 2017/2/27.
 */

public class test {
    /**
     * 寻路地图
     */
    int[][] graph = new int[33][33];
    /**
     * 邻接矩阵
     */
    int[][] adjacencyMatrix = new int[32*32][32*32];

    private void graphTOadjacencyMatrix(){
        for(int i = 0 ; i < 32 ; i++){
            for(int j = 0 ; j < 32 ; j++){
                if(graph[i][j] == 0){
                    int a = i*32+j;
                    adjacencyMatrix[a][a] = 0;
                    if(i != 0){
                        adjacencyMatrix[(i-1)*32+j][a] = 1;
                    }
                    if(j != 0){
                        adjacencyMatrix[a][a-1] = 1;
                    }
                    if(i != 32){
                        adjacencyMatrix[(i+1)*32+j][a] = 1;
                    }
                    if(j != 32){
                        adjacencyMatrix[a][a+1] = 1;
                    }
                }
            }
        }
    }

    public void test(){
        graph[16][0] = 1;
        graph[16][1] = 1;
        graph[16][2] = 1;
        graph[16][3] = 1;

        graph[16][32] = 1;
        graph[16][31] = 1;
        graph[16][30] = 1;
        graph[16][29] = 1;

        graph[0][16] = 1;
        graph[1][16] = 1;
        graph[2][16] = 1;
        graph[3][16] = 1;

        graph[29][16] = 1;
        graph[30][16] = 1;
        graph[31][16] = 1;
        graph[32][16] = 1;

        graph[15][4] = 1;
        graph[16][5] = 1;
        graph[16][6] = 1;
        graph[16][7] = 1;
        graph[16][8] = 1;
        graph[16][9] = 1;
        graph[16][10] = 1;
        graph[16][11] = 1;
        graph[16][12] = 1;
        graph[16][13] = 1;
        graph[16][14] = 1;
        graph[16][15] = 1;
        graph[16][16] = 1;
        graph[16][17] = 1;
        graph[16][18] = 1;

        graph[15][15] = 1;
        graph[15][16] = 1;
        graph[15][17] = 1;
        graph[15][18] = 1;

        graph[14][16] = 1;
        graph[14][17] = 1;
        graph[14][18] = 1;

        graph[17][14] = 1;
        graph[17][15] = 1;
        graph[17][16] = 1;
        graph[17][17] = 1;

        graph[18][14] = 1;
        graph[18][15] = 1;
        graph[18][16] = 1;

        graph[28][17] = 1;
        graph[27][17] = 1;
        graph[26][18] = 1;
        graph[25][19] = 1;
        graph[24][20] = 1;
        graph[23][21] = 1;
        graph[22][22] = 1;
        graph[21][23] = 1;
        graph[20][24] = 1;
        graph[19][25] = 1;
        graph[18][26] = 1;
        graph[17][27] = 1;
        graph[16][27] = 1;
        graph[15][27] = 1;
        graph[14][28] = 1;
        graph[13][28] = 1;
        graph[12][28] = 1;
        graph[11][28] = 1;
        graph[10][28] = 1;
        graph[9][28] = 1;
        graph[8][28] = 1;
        graph[7][28] = 1;
        graph[6][28] = 1;
        graph[5][29] = 1;
        graph[4][29] = 1;
        graph[3][28] = 1;
        graph[4][27] = 1;
        graph[5][26] = 1;
        graph[6][25] = 1;
        graph[7][24] = 1;
        graph[8][23] = 1;
        graph[9][22] = 1;
        graph[10][21] = 1;
        graph[11][20] = 1;
        graph[12][19] = 1;
        graph[12][18] = 1;
        graph[12][17] = 1;
        graph[12][16] = 1;
        graph[12][15] = 1;
        graph[13][14] = 1;
        graph[14][13] = 1;
        graph[16][11] = 0;
        graph[16][13] = 0;
        graph[15][12] = 1;
        graph[17][12] = 1;
        graph[17][10] = 1;
        graph[18][10] = 1;
        graph[19][11] = 1;
        graph[20][12] = 1;
        graph[19][13] = 1;

        MapInfo info=new MapInfo(graph,graph[0].length, graph.length,new Node(4, 16), new Node(28, 16)); // 4,4  4,16  28,16  28,4  16,4  16,28  28,28
        new AStar().start(info);
        printMap(graph);
    }

    /**
     * 打印地图
     */
    public static void printMap(int[][] maps)
    {
        for (int i = 0; i < maps.length; i++)
        {
            for (int j = 0; j < maps[i].length; j++)
            {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }

}
