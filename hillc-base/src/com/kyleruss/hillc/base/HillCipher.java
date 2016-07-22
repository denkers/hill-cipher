package com.kyleruss.hillc.base;

public class HillCipher 
{
    public static final int ALPHA_CHARS     =   26;
    public static final int ALL_CHARS       =   255;
    public static final int SMALL_V         =   2;
    public static final int LARGE_V         =   3;
    
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

    public static void main(String[] args)
    {
        CStructure key      =   new CStructure("alphabeta", ALL_CHARS, LARGE_V);
        CStructure text     =   new CStructure("wea booz", ALL_CHARS, LARGE_V);
        CStructure cipher   =   encrypt(text, key, ALL_CHARS);
        String cipherText   =   cipher.getText();
        CStructure cCipher  =   new CStructure(cipherText, ALL_CHARS, LARGE_V);
        CStructure dec      =   decrypt(cCipher, key, ALL_CHARS);
        System.out.println(dec.getText());
    }
}
