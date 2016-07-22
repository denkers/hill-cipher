package com.kyleruss.hillc.base;

import java.util.ArrayList;
import java.util.List;


public class MatrixUtils
{
    private static List<Character> alphaCharList;
    
    static
    {
        initAlphaCharList();
    }
    
    private static void initAlphaCharList()
    {
        alphaCharList   =   new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++)
            alphaCharList.add(c);
    }
    
    public static int[] multiplyMatrix(int[][] a, int[] b, int mod)
    {
        int m                =   b.length;
        int[] resultMatrix   =   new int[m];
        
        for(int i = 0; i < m; i++)
        {
            int total   =   0;
            
            for(int j = 0; j < m; j++)
            {
                int aValue  =   a[i][j];
                int bValue  =   b[j];
                total       +=  aValue * bValue;
            }
            
            resultMatrix[i] =   total % mod;
        }
        
        return resultMatrix;
    }
    
    public static int[][] multiplyDimMatrix(int[][] a, int[][] b, int mod)
    {
        int n                   =   a.length;
        int[][] resultMatrix    =   new int[n][n];
        
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                    resultMatrix[i][j]  +=   a[i][k] * b[k][j];
                
                resultMatrix[i][j]  =   resultMatrix[i][j] % mod;
            }
        }
        
        return resultMatrix;
    }
    
    
    public static String vectorToString(int[] v)
    {
        String str  =   "[";
        
        for(int i = 0; i < v.length; i++)
            str +=  v[i] + ((i < v.length - 1)? ", " : "");
        
        return str + "]";
    }
    
    public static String matrixToString(int[][] m)
    {
        String str  =   "";
        
        for(int i = 0; i < m.length; i++)
        {
            str += ((str.length() > 0)? ",\n" : "") + "[";
            
            for(int j = 0; j < m[0].length; j++)
                str +=  m[i][j] + ((j < m[0].length - 1)? ", " : "");
            
            str += "]";
        }
        return str;
    }
    
    
    private static int euclInverse(int a, int mod)
    {
        int x       =   0;
        int r       =   mod;
        int nextX   =   1;
        int nextR   =   a;
        
        while(nextR != 0)
        {
            int q       =   r / nextR;
            int preX    =   x;
            x           =   nextX;
            nextX       =   preX - (q * nextX);
            int preR    =   r;
            r           =   nextR;
            nextR       =   preR - (q * nextR);
        }
        
        if(r > 1) return -1;
        if(x < 0) x += mod;
        
        return x;
    }
    
    public static int[] getPlaintextVector(String textSegment, int size, int mod)
    {
        int[] pTextVector   =   new int[size];
        for(int i = 0; i < size; i++)
        {
            int charCode;
            
            if(textSegment.length() <= i) charCode = 0;
            else 
            {
                if(mod == 26)
                    charCode    =   alphaCharList.indexOf(textSegment.charAt(i));
                else
                    charCode    =   (int) textSegment.charAt(i);
            }
            
            pTextVector[i]  =   charCode;
        }
        
        return pTextVector;
    }
    
    public static int[][] getStrMatrix(String str, int size, int mod)
    {
        int tSize               =   str.length();
        int n                   =   tSize / size;
        if(tSize % size != 0) n++;
        int[][] plainTextMatrix =   new int[n][size];  
        
        for(int i = 0, j = 0; i < n; i++, j += size)
        {
            String textSegment  =   (tSize < j + size)? str.substring(j) : str.substring(j, j + size);
            plainTextMatrix[i]  =   getPlaintextVector(textSegment, size, mod);
        }
        
        return plainTextMatrix;
    }
    
    public static String getMatrixString(int[][] m, int mod)
    {
        String text =   "";
        for(int i = 0; i < m.length; i++)
            text += getVectorString(m[i], mod);
        
        return text;
    }
    
    public static String getVectorString(int[] v, int mod)
    {
        String text =   "";
        for(int i = 0; i < v.length; i++)
        {
            if(mod == 26)
                text += alphaCharList.get(v[i]);
            else
                text += (v[i] == 0? "" : ((char) v[i]));
        }
        
        return text;
    }
    
    public static int[][] getInverse(int[][] m, int mod)
    {
        int[][] inverseMatrix;
        boolean smallMatrix         =   m.length == 2;
        int determinant             =   getDeterminant(m, mod);
        
        int determinantInverse      =   euclInverse(determinant, mod);
        
        if(smallMatrix)
            inverseMatrix           =   getAdjSmallMatrix(m, mod);
        
        else
        {
            inverseMatrix           =   getTranspose(m);
            inverseMatrix           =   createCofactorMatrix(inverseMatrix, mod);
        }
        
        inverseMatrix               =   scalarMultiplyMatrix(inverseMatrix, determinantInverse, mod);
        
        return inverseMatrix;
    }
    
    public static int getDeterminant(int[][] m, int mod)
    {
        int det =   (m.length == 2? getDeterminantSmall(m) : getDeterminantLarge(m)) % mod;
        if(det < 0)
            det += mod;
        
        return det;
    }
    
    public static int getDeterminantLarge(int[][] m)
    {
        int value            =   0;
        int[][] detMatrix    =   getDeterminantMatrix(m);
        
        for(int i = 0; i < 3; i++)
            value += getDeterminantCol(detMatrix, i, true);
        
        int endIndex = detMatrix[0].length - 1;
        for(int j = endIndex; j > endIndex - 3; j--)
            value -= getDeterminantCol(detMatrix, j, false);
        
        return value;
    }
    
    public static int getDeterminantSmall(int[][] m)
    {
        int ad  =   m[0][0] * m[1][1];
        int bc  =   m[0][1] * m[1][0];
        return ad - bc;
    }
    
    public static int[][] getAdjSmallMatrix(int[][] m, int mod)
    {
        int[][] adjMatrix   =   new int[m.length][m[0].length];
        
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[0].length; j++)
            {
                int val =   m[i][j];
                if((j == 1 && i == 0) || (j == 0 && i == 1))
                    val *= -1;
                
                val  =   val % mod;
                
                if(val < 0)
                    val += mod;
                
                adjMatrix[i][j] = val;
            }
        }
        
        int prevA       =   adjMatrix[0][0];
        adjMatrix[0][0] =   adjMatrix[1][1];
        adjMatrix[1][1] =   prevA;
        
        return adjMatrix;
    }
    
    public static boolean isInvertible(int[][] m, int mod)
    {
        int det     =   getDeterminant(m, mod);
        return det != 0 && euclInverse(det, mod) > 0;
    }
    
    private static int[][] createCofactorMatrix(int[][] m, int mod)
    {
        int[][] cofactorMatrix  =   new int[m.length][m[0].length];
        
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[0].length; j++)
            {
                boolean minor        =   (i == 1 && j != 1) || ((i == 0 || i == 2) && j == 1);
                cofactorMatrix[i][j] =   getCofactorEntry(m, i, j, minor, mod);
            }
        }
        
        return cofactorMatrix;
    }
    
    private static int getCofactorEntry(int[][] m, int row, int col, boolean minor, int mod)
    {
        int[][] tempM       =   new int[2][2];
        int y               =   0;
        int x;
        
        for(int i = 0; i < m.length; i++)
        {
            if(i == row) continue;
            x   =   0;
            
            for(int j = 0; j < m[0].length; j++)
            {
                if(j == col) continue;
                
                tempM[y][x] =   m[i][j];
                x++;
            }
            
            y++;
        }
        
        int v1       =   tempM[0][0] * tempM[1][1];
        int v2       =   tempM[1][0] * tempM[0][1];
        int result   =   v1 - v2;
        
        if(minor)
            result      =   result * -1;
        
        result          =   result == -0? 0 : result;
        result          =   result % mod;
        
        if(result < 0)
            result      +=  mod;
        
        return result;
    }
    
    public static int[][] scalarMultiplyMatrix(int[][] m, int k, int mod)
    {
        int[][] transformedMatrix   =   new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                transformedMatrix[i][j] = (m[i][j] * k) % mod;
        
        return transformedMatrix;
    }
    
    public static int[][] getDeterminantMatrix(int[][] m)
    {
        int[][] dMatrix  =   new int[m.length][m[0].length + 2];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length + 2; j++)
                dMatrix[i][j] = m[i][j % m[0].length];
        
        
        return dMatrix;
    }
    
    public static int[][] getTranspose(int[][] m)
    {
        int[][] transposeMatrix =   new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                transposeMatrix[j][i] = m[i][j];
        
        return transposeMatrix;
    }   
    
    private static int getDeterminantCol(int[][] m, int col, boolean posDirection)
    {
        int result      =   1;
        int row         =   0;
        int i           =   col;
        boolean eval    =   posDirection? (i < col + 3) : (i > col - 3);
        
        while(eval)
        {
            int val     =   m[row][i];
            result      *=  val;
            i           =   (posDirection? i + 1 : i - 1);
            eval        =   posDirection? (i < col + 3) : (i > col - 3);
            row++;
        }
        
        return result;
    }
    
    public static void main(String[] args)
    {
        String a    =   "a";
        int code    =   a.charAt(0) - '0';
    }
    
}
