//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.base;

public class CStructure 
{
    //A multidimensional array aux-ds to represent plaintext and keys
    private int[][] matrix;
    
    //The string representation of the plaintext/key
    private String text;
    
    //The fixed vector size m
    private int vecSize;
    
    //The character list size
    //See HillCipher.ALPHA_CHARS, HillCipher.ALL_CHARS
    int mod;
    
    //Create a structure with an existing matrix
    //Initializes the text based on the matrix
    public CStructure(int[][] matrix, int mod, int vecSize)
    {
        this.matrix     =   matrix;
        this.mod        =   mod;
        this.vecSize    =   vecSize;
        initText();
    }
    
    //Create a structure with existing text
    //Inititalizes the matrix based on the text
    public CStructure(String text, int mod, int vecSize)
    {
        this.text       =   text;
        this.mod        =   mod;
        this.vecSize    =   vecSize;
        initMatrix();
    }
    
    /**
    Creates the structure matrix based on the text
    */
    private void initMatrix()
    {
        int size    =   getVectorSize();
        matrix      =   MatrixUtils.getStrMatrix(text, size, mod);
    }
    
    /**
    Creates the text based on the structure matrix
    */
    private void initText()
    {
        text  =   MatrixUtils.getMatrixString(matrix, mod);
    }
    
    /**
     * Returns the M[i][j] entry of the structure matrix
     * @param i The ith row of the matrix: i >= 0, i < No. rows
     * @param j The jth column of the matrix: j >=, j < No. cols
     * @return The ith row, jth column entry of the structure matrix
     */
    public int getEntry(int i, int j)
    {
        if(i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length)
            return matrix[i][j];
        else return -1;
    }
    
    /**
     * Returns the ith row of the matrix
     * @param i The ith row of the matirx: i >=0, i < No. rows
     * @return A vector of the ith row in the matrix
     */
    public int[] getRow(int i)
    {
        if(i >= 0 && i < matrix.length)
            return matrix[i];
        else return null;
    }
    
    /**
     * @return A formatted string representation of the matrix
     */
    public String getMatrixString()
    {
        return MatrixUtils.matrixToString(matrix);
    }
    
    /**
     * Returns the formatted string of the ith row of the matrix
     * @param i The ith row of the matrix: i >= 0, i < No. rows
     * @return A formatted string of the vector at the ith row of the matrix
     */
    public String getVectorString(int i)
    {
        if(i >= 0 && i < matrix.length)
            return MatrixUtils.vectorToString(matrix[i]);
        else return "";
    }
    
    /**
     * @return The text String default hashcode
     */
    @Override
    public int hashCode()
    {
        return text.hashCode();
    }
    
    /**
     * Compares equivalence the text of two CStructure's
     * @param obj An Object to compare, should be com.kyleruss.hillc.base.CStructure 
     * @return true if the two objects have the same text; false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof CStructure)
            return ((CStructure) obj).getText().equals(text);
        else return false;
    }
    
    public int getVectorSize()
    {
        return vecSize;
    }
    
    public void setVectorSize(int vecSize)
    {
        this.vecSize    =   vecSize;
    }
    
    public int getNumRows()
    {
        return matrix.length;
    }
    
    
    public int getNumCols()
    {
        return matrix.length > 0? matrix[0].length : 0;
    }
    

    public int[][] getMatrix() 
    {
        return matrix;
    }

    public String getText()
    {
        return text;
    }
    
    public int getMod()
    {
        return mod;
    }
}
