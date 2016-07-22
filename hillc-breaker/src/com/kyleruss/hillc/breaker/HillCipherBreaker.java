//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.breaker;

import com.kyleruss.hillc.base.CStructure;
import com.kyleruss.hillc.base.HillCipher;
import com.kyleruss.hillc.base.MatrixUtils;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class HillCipherBreaker 
{
    /**
     * Breaks an input Hill Cipher with some known plain text
     * Must only contain alphabetic characters and m = 2
     * @see getAttemptRound
     * @param cipher The CStructure cipher to break
     * @param knownPlaintext A length = 5 string of known plain text 
     * @return A list of all attempts where entry keys are the inverse key and
     * entry values are the decrypted texts
     */
    public static List<Entry<CStructure, CStructure>> breakCipher(CStructure cipher, String knownPlaintext)
    {
        List<Entry<CStructure, CStructure>> attemptList =   new ArrayList<>();
        int numRounds   =   cipher.getText().length() - knownPlaintext.length() + 2;
        
        for(int i = 0; i < numRounds; i++)
        {
            Entry<CStructure, CStructure> attempt   =   getAttemptRound(cipher, knownPlaintext, i);
            if(attempt != null) attemptList.add(attempt);
        }
        
        return attemptList;
    }
    
    /**
     * Attempts to break a hill cipher for a given bigram with some known plain text using cribing technique
     * Gets the bigrams of the cipher and and known plain text at the offset
     * Then computes their transpose matrices and computes the inverse of the cipher transpose
     * The inverted key can then be found by multiplying the plain text tranpose with the cipher inverse
     * Finally we multiply the inverse key with the input cipher to decrypt the cipher
     * @param cipher A cipher which you are attempting to break
     * @param knownPlaintext A length = 5 string of known plain text 
     * @param offset The starting index to get bigrams from @see getBigrams
     * @return An entry where key => the inverted key used to decrypt, value => the decrypted plain text
     */
    private static Entry<CStructure, CStructure> getAttemptRound(CStructure cipher, String knownPlaintext, int offset)
    {
        boolean startBigram         =   offset % 2 == 0;
        int startIndex              =   startBigram? 0 : 1;
        int startOffset             =   startBigram? offset : offset + 1;
        CStructure guessStruc       =   getBigrams(knownPlaintext, startIndex);
        CStructure cipherStruc      =   getBigrams(cipher.getText(), startOffset);
        
        int[][] guessTranspose      =   MatrixUtils.getTranspose(guessStruc.getMatrix());
        int[][] cipherTranspose     =   MatrixUtils.getTranspose(cipherStruc.getMatrix());
        
        if(!MatrixUtils.isInvertible(cipherStruc.getMatrix(), 26))
            return null;
        
        else
        {
            int[][] inverse             =   MatrixUtils.getInverse(cipherTranspose, 26);
            CStructure key              =   new CStructure(MatrixUtils.multiplyDimMatrix(guessTranspose, inverse, 26), 26);
            CStructure decryptedCipher  =   HillCipher.invKeyDecrypt(cipher, key, 26);

            return new SimpleEntry(key, decryptedCipher);
        }
    }
    
    /**
     * Fetches the bigram for the input text at the offset
     * @param text A plain text of length >= 2, containing alphabetic characters only
     * @param offset The starting index to get the bigrams
     * @return A CStructure containing the 2x2 bigram 
     */
    public static CStructure getBigrams(String text, int offset)
    {
        CStructure bigramA      =   new CStructure(text.substring(offset, offset + 2), 26);
        CStructure bigramB      =   new CStructure(text.substring(offset + 2, offset + 4), 26);
        int[][] bigramMatrix    =   new int[2][2];
        
        bigramMatrix[0]         =   bigramA.getRow(0);
        bigramMatrix[1]         =   bigramB.getRow(0);
        
        return new CStructure(bigramMatrix, 26);
    }
}
