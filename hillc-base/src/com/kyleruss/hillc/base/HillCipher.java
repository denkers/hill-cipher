package com.kyleruss.hillc.base;


public class HillCipher 
{
    public static CStructure encrypt(CStructure plainText, CStructure key)
    {
        int size                =   key.getVectorSize();
        int[][] keyMatrix       =   key.getMatrix();
        int[][] plainTextMatrix =   plainText.getMatrix();
        int[][] cipherMatrix    =   new int[plainText.getNumRows()][size];
        
        for(int i = 0; i < plainTextMatrix.length; i++)
        {
            int[] entry     =   plainTextMatrix[i];
            cipherMatrix[i] =   MatrixUtils.multiplyMatrix(keyMatrix, entry, 26);
        } 
        
        return new CStructure(cipherMatrix);
    }
    
    
    public static CStructure decrypt(CStructure cipher, CStructure key)
    {
        String plainText        =   "";
        int[][] keyMatrix       =   key.getMatrix();
     //   int[][] invKeyMatrix    =   MatrixUtils.getInverse(keyMatrix);
        
        for(int i = 0; i < cipher.getNumRows(); i++)
        {
            int[] decVector =   MatrixUtils.multiplyMatrix(keyMatrix, cipher.getRow(i), 26);
            
            if(decVector != null)
            {
                plainText += MatrixUtils.getVectorString(decVector);
                /*for(int j = 0; j < decVector.length; j++)
                    plainText += MatrixUtils.getVectorString(decVector[j]);//(decVector[j] == 0)? "" : (char) (decVector[j] + '0'); */
            }
        } 
        
        return new CStructure(plainText);
    }

    public static void main(String[] args)
    {
        CStructure key      =   new CStructure("alph");
        CStructure text     =   new CStructure("defendthewestwallofthecastle");
        CStructure cipher   =   encrypt(text, key);
        CStructure dec      =   decrypt(cipher, key);
        System.out.println(dec.getText());
    }
}
