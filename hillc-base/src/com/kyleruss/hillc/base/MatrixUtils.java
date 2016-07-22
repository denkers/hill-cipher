//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.base;

import java.util.ArrayList;
import java.util.List;


public class MatrixUtils
{
    //A list holding all english alphabetical characters
    private static List<Character> alphaCharList;
    
    //initializes the alphaCharList
    static
    {
        initAlphaCharList();
    }
    
    //initializes the alphaCharList
    //Adds all english alphabetical characters to the list
    private static void initAlphaCharList()
    {
        alphaCharList   =   new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++)
            alphaCharList.add(c);
    }
    
    /**
     * Multiplies the input matrix a with the input vector b % mod
     * @param a The matrix to multiply with. a.length = b.length
     * @param b A vector to multiply with the matrix where b.length = a.length
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The vector product of the multiplication (a, b) % mod
     */
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
    
    /**
     * Multiplies the input matrix a with another input matrix b % mod
     * @param a The matrix to multiply with. a.length = b.length
     * @param b A matrix to multiply with the matrix where b.length = a.length
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The matrix product of the multiplication (a, b) % mod
     */
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
    
    /**
     * Creates and returns a formatted string representation of the input vector v
     * @param v An input vector to get its formatted string representation
     * @return A formatted string of the input vector: [a, b, c..] 
     */
    public static String vectorToString(int[] v)
    {
        String str  =   "[";
        
        for(int i = 0; i < v.length; i++)
            str +=  v[i] + ((i < v.length - 1)? ", " : "");
        
        return str + "]";
    }
    
    /**
     * Creates and returns a formatted string represenation of the input matrix m
     * @param m A matrix to get its formatted string representation
     * @return A formatted string of the input matrix: [[a, b, c], [d, e, f]]
     */
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
    
    /**
     * Performs the extended-euclidean and returns the inverse of a 
     * If an inverse exists then returns x where ax = 1 mod m
     * @param a The int input whose inverse you want to find
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The inverse x if ax = 1 mod m, otherwise -1
     */
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
    
    /**
     * Creates and returns a vector of size @param size for the input text
     * @param textSegment A string text to convert to vector array
     * @param size The size of the resulting vector
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return An int vector representation of the input text
     */
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
    
    /**
     * Creates and returns a matrix of size @param size for the input text
     * @param str A string text to convert to matrix
     * @param size The size of the resulting matrix
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return An int matrix representation of the input text
     */
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
    
    /**
     * Creates and returns a string representation of the input matrix m
     * @param m The matrix to get its string representation
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return A string representation of the matrix m
     */
    public static String getMatrixString(int[][] m, int mod)
    {
        String text =   "";
        for(int i = 0; i < m.length; i++)
            text += getVectorString(m[i], mod);
        
        return text;
    }
    
    /**
     * Creates and returns a string representation of the input vector v
     * @param v The vector to get its string representation
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return A string representation of the vector v
     */
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
    
    /**
     * Computes and returns the inverse matrix of the input matrix m
     * Works on both 2D and 3D matrices
     * First computes the inverse of the determinant of the matrix m
     * Then scalar multiplies the determinant inverse with the computed cofactor matrix
     * @param m A matrix to compute its inverse
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The inverse matrix of the input matrix m
     */
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
    
    /**
     * Finds the determinant from getDeterminantSmall or getDeterminantLarge
     * and performs mod operations on the determinant 
     * @param m The matrix to find the determinant
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The determinant of the input matrix m
     */
    public static int getDeterminant(int[][] m, int mod)
    {
        int det =   (m.length == 2? getDeterminantSmall(m) : getDeterminantLarge(m)) % mod;
        if(det < 0)
            det += mod;
        
        return det;
    }
    
    /**
     * Computes the determinant for a 3D matrix m
     * @see getDeterminantCol
     * @param m A 3D matrix whose determinant we want to find
     * @return The determinant for the input matrix m
     */
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
    
    /**
     * Computes the determinant for the 2D matrix m
     * @param m A 2D matrix 
     * @return The determinant for the input matrix m
     */
    public static int getDeterminantSmall(int[][] m)
    {
        int ad  =   m[0][0] * m[1][1];
        int bc  =   m[0][1] * m[1][0];
        return ad - bc;
    }
    
    /**
     * Computes and returns the adjugate matrix of the input matrix m
     * @param m A 2D matrix 
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return A 2D adjugate matrix of the input matrix m
     */
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
    
    /**
     * Checks if the input matrix m is invertible 
     * such that the determinant of m is not 0 and
     * the inverse of the determinant is not < 0
     * @param m A 2D/3D matrix 
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return true if the matrix m is invertible; false otherwise
     */
    public static boolean isInvertible(int[][] m, int mod)
    {
        int det     =   getDeterminant(m, mod);
        return det != 0 && euclInverse(det, mod) > 0;
    }
    
    /**
     * Creates and returns the cofactor matrix for the input matrix m
     * @see getCofactorEntry
     * @param m A 3D matrix
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The corresponding cofactor matrix 
     */
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
    
    /**
     * Computes and returns the ij'th entry of cofactor matrix for the input matrix m
     * @param m A 3D matrix
     * @param row The ith row of the matrix: i >= 0, i < m.length
     * @param col The jth row of the matrix: j >= 0, j < m[0].length
     * @param minor True if the entry is negative; false otherwise
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The ith row, jth col entry of the cofactor matrix
     */
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
    
    /**
     * Scalar multiplies the input matrix m with the scalar k
     * @param m A matrix to multiply by k
     * @param k The int scalar to multiply the matrix with
     * @param mod The modulus used. See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS for options
     * @return The resulting matrix after it has been multiplied with k
     */
    public static int[][] scalarMultiplyMatrix(int[][] m, int k, int mod)
    {
        int[][] transformedMatrix   =   new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                transformedMatrix[i][j] = (m[i][j] * k) % mod;
        
        return transformedMatrix;
    }
    
    /**
     * Creates and returns the determinant matrix used to find the det of a matrix
     * The resulting matrix has m.length rows and m[0].length + 2 columns
     * Copies the entire matrix into the resulting matrix 
     * and the first two columns into the last two columns again
     * @param m The matrix to get its determinant matrix
     * @return A determinant matrix with m.length rows and m[0].length + 2 columns
     */
    public static int[][] getDeterminantMatrix(int[][] m)
    {
        int[][] dMatrix  =   new int[m.length][m[0].length + 2];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length + 2; j++)
                dMatrix[i][j] = m[i][j % m[0].length];
        
        
        return dMatrix;
    }
    
    /**
     * Computes and returns the transpose of the input matrix m
     * Flips rows in the input matrix to columns in the resulting matrix
     * @param m A matrix whose transpose you want to find
     * @return The transpose matrix for the input matrix m
     */
    public static int[][] getTranspose(int[][] m)
    {
        int[][] transposeMatrix =   new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                transposeMatrix[j][i] = m[i][j];
        
        return transposeMatrix;
    }   
    
    /**
     * Computes and returns determinant column value for the input matrix
     * @param m A 3D matrix 
     * @param col The column to compute 
     * @param posDirection true if the direction is right (positive); false otherwise
     * @return The computes determinant column value
     */
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
}
