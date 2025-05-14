package com.example.matrix;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matrix.databinding.ActivityMainBinding;

public class  MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    int[][] matrix1;
    int[][] matrix2;
    String matrix1input;
    String materx2input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matrix1input = mainBinding.ed1.getText().toString();
                materx2input = mainBinding.ed2.getText().toString();
                matrix1 = pres(matrix1input);
                matrix2 = pres(materx2input);


                if(matrix1 !=null && matrix2 !=null){
                    if(matrix1.length==matrix2.length&& matrix1[0].length==matrix2[0].length)
                    {
                        int[][]sum = addMat(matrix1,matrix2);
                        displayMatrix(sum);
                    }
                }

            }
        });
        mainBinding.mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matrix1input = mainBinding.ed1.getText().toString();
                materx2input = mainBinding.ed2.getText().toString();
                matrix1 = pres(matrix1input);
                matrix2 = pres(materx2input);


                if(matrix1 !=null && matrix2 !=null){
                    if(matrix1.length>matrix2.length&& matrix1[0].length>matrix2[0].length)
                    {
                        int[][]mul = multiplyMatrices(matrix1,matrix2);
                        displayMatrix(mul);
                    }
                }
            }
        });

    }
//////////////////////////////////////////////
    public int[][] pres(String matrix) {
        try {
            String[] row = matrix.split("\n");
            int reocunt = row.length;
            int colum = row[0].trim().split(" ").length;
            int matrixpro[][] = new int[reocunt][colum];
            for (int i = 0; i < reocunt; i++) {
                String[] elements = row[i].trim().split(" ");
                for (int j = 0; j < colum; j++) {
                    matrixpro[i][j] = Integer.parseInt(elements[j]);
                }
            }
            return matrixpro;
        } catch (Exception e) {
            Toast.makeText(this, "Invalid input format.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    /////////////////////////////////////////
    public  int[][] addMat(int materix1[][], int matrix[][])
    {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] sum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return sum;

    }
    private void displayMatrix(int[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int[] row : matrix) {
            for (int element : row) {
                result.append(element).append("   ");
            }
            result.append("\n");
        }
       mainBinding.result.setText(result.toString());
}
    private int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        int sumLength = matrix2.length;
        int[][] product = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < sumLength; k++) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return product;
    }


}