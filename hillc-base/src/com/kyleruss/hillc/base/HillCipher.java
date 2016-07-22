package com.kyleruss.hillc.base;


public class HillCipher 
{
    public static CStructure encrypt(CStructure plainText, CStructure key, int mod)
    {
        int size                =   key.getVectorSize();
        int[][] keyMatrix       =   key.getMatrix();
        int[][] plainTextMatrix =   plainText.getMatrix();
        int[][] cipherMatrix    =   new int[plainText.getNumRows()][size];
        
        for(int i = 0; i < plainTextMatrix.length; i++)
        {
            int[] entry     =   plainTextMatrix[i];
            cipherMatrix[i] =   MatrixUtils.multiplyMatrix(keyMatrix, entry, mod);
        } 
        
        return new CStructure(cipherMatrix, mod, plainText.getVectorSize());
    }
    
    public static CStructure invKeyDecrypt(CStructure cipher, CStructure invKey, int mod)
    {
        String plainText    =   "";
        int[][] keyMatrix   =   invKey.getMatrix();
        
        for(int i = 0; i < cipher.getNumRows(); i++)
        {
            int[] decVector =   MatrixUtils.multiplyMatrix(keyMatrix, cipher.getRow(i), mod);
            
            if(decVector != null)
                plainText += MatrixUtils.getVectorString(decVector, mod);
        } 
        
        return new CStructure(plainText, mod, cipher.getVectorSize());
    }
    
    public static CStructure decrypt(CStructure cipher, CStructure key, int mod)
    {
        CStructure invKey   =   new CStructure(MatrixUtils.getInverse(key.getMatrix(), mod), mod, cipher.getVectorSize());
        return invKeyDecrypt(cipher, invKey, mod);
    }
}
