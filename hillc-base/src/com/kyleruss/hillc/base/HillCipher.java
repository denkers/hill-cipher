//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.base;

public class HillCipher 
{
    //---------------------------------------------
    //  CHARACTER/STRUCTURE CONSTS
    //---------------------------------------------
    //Defines use of alphabetic only characters
    public static final int ALPHA_CHARS     =   26;
    
    //Defines use of all ASCII characters
    public static final int ALL_CHARS       =   255;
    
    //Represents a small vector size m = 2
    public static final int SMALL_V         =   2;
    
    //Represents a larger vector size m = 3
    public static final int LARGE_V         =   3;
    //---------------------------------------------
    
    /**
     * Encrypts the input plain text using the input key using the HillCipher algorithm
     * Multiplies each row of the plaintext with key % @param mod
     * Each ith row of the resulting cipher structure corresponds to the encrypted
     * ith row of the input plain text structure 
     * @param plainText A CStructure representing the plain text to encrypt
     * @param key A CStructure representing the key used to encrypt the plain text
     * @param mod The modulus used in operations. See ALPHA_CHARS, ALL_CHARS for options
     * @return The encrypted cipher CStructure
     */
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
    
    /**
     * Decryptes the input cipher with an existing input inverse key
     * Builds the plain text back up  by multiplying each row of the 
     * input cipher matrix with the inverse key % mod
     * @param cipher The encrypted cipher CStructure
     * @param invKey An inverted key CStructure (use decrypt if you don't have inv key)
     * @param mod The modulus used in operations. See ALPHA_CHARS, ALL_CHARS for options
     * @return The decrypted plain text CStructure 
     */
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
    
    /**
     * Decryptes the input cipher with an existing input inverse key
     * Builds the plain text back up  by multiplying each row of the 
     * input cipher matrix with the inverse key % mod
     * Finds the inverse key with MatrixUtils.getInverse and calls HillCipher.invKeyDecrpyt
     * @see HillCipher.invKeyDecrypt
     * @param cipher The encrypted cipher CStructure
     * @param key A key CStructure (NOT the inverse key, @see HillCipher.invKeyDecrypt if you have inv key)
     * @param mod The modulus used in operations. See ALPHA_CHARS, ALL_CHARS for options
     * @return The decrypted plain text CStructure 
     */
    public static CStructure decrypt(CStructure cipher, CStructure key, int mod)
    {
        CStructure invKey   =   new CStructure(MatrixUtils.getInverse(key.getMatrix(), mod), mod, cipher.getVectorSize());
        return invKeyDecrypt(cipher, invKey, mod);
    }
}
