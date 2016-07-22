package com.kyleruss.hillc.base;

public class CStructure 
{
    private int[][] matrix;
    private String text;
    private int vecSize;
    int mod;
    
    public CStructure(int[][] matrix, int mod, int vecSize)
    {
        this.matrix     =   matrix;
        this.mod        =   mod;
        this.vecSize    =   vecSize;
        initText();
    }
    
    public CStructure(String text, int mod, int vecSize)
    {
        this.text       =   text;
        this.mod        =   mod;
        this.vecSize    =   vecSize;
        initMatrix();
    }
    
    private void initMatrix()
    {
        int size    =   getVectorSize();
        matrix      =   MatrixUtils.getStrMatrix(text, size, mod);
    }
    
    private void initText()
    {
        text  =   MatrixUtils.getMatrixString(matrix, mod);
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
    
    public int getEntry(int i, int j)
    {
        if(i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length)
            return matrix[i][j];
        else return -1;
    }
    
    public int[] getRow(int i)
    {
        if(i >= 0 && i < matrix.length)
            return matrix[i];
        else return null;
    }
    
    public String getMatrixString()
    {
        return MatrixUtils.matrixToString(matrix);
    }
    
    public String getVectorString(int i)
    {
        if(i >= 0 && i < matrix.length)
            return MatrixUtils.vectorToString(matrix[i]);
        else return "";
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
    
    @Override
    public int hashCode()
    {
        return text.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof CStructure)
            return ((CStructure) obj).getText().equals(text);
        else return false;
    }
}
