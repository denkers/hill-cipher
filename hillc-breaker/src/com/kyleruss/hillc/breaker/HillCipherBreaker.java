
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
    public static List<Entry<CStructure, CStructure>> breakCipher(CStructure cipher, String knownPlaintext)
    {
        List<Entry<CStructure, CStructure>> attemptList =   new ArrayList<>();
        int numRounds   =   cipher.getText().length() - knownPlaintext.length();
        
        for(int i = 0 ; i< numRounds; i++)
            attemptList.add(getAttemptRound(cipher, knownPlaintext, i));
        
        return attemptList;
    }
    
    public static Entry<CStructure, CStructure> getAttemptRound(CStructure cipher, String knownPlaintext, int offset)
    {
        boolean startBigram         =   offset % 2 == 0;
        int startIndex              =   startBigram? offset : offset + 1;
        CStructure guessStruc       =   getBigrams(knownPlaintext, startIndex);
        CStructure cipherStruc      =   getBigrams(cipher.getText(), startIndex);
        CStructure invCipherStruc   =   new CStructure(MatrixUtils.getInverse(cipherStruc.getMatrix()));
        CStructure decKey           =   new CStructure(MatrixUtils.multiplyDimMatrix(guessStruc.getMatrix(), invCipherStruc.getMatrix(), 255));
        CStructure decryptedCipher  =   HillCipher.decrypt(cipher, decKey);
        
        return new SimpleEntry(decKey, decryptedCipher);
    }
    
    public static CStructure getBigrams(String text, int offset)
    {
        boolean startBigram     =   offset % 2 == 0;
        int startIndex          =   startBigram? offset : offset + 1;
        CStructure bigramA      =   new CStructure(text.substring(startIndex, startIndex + 2));
        CStructure bigramB      =   new CStructure(text.substring(startIndex + 2));
        int[][] bigramMatrix    =   new int[2][2];
        
        bigramMatrix[0]         =   bigramA.getRow(0);
        bigramMatrix[1]         =   bigramB.getRow(0);
        
        return new CStructure(bigramMatrix);
    }
    
    public static void main(String[] args)
    {
        String test =   "abcde";
        int start   =   1;
        String sub  =   test.substring(start, start + 2);
        String sub2 =   test.substring(start + 2);
        System.out.println("sub: " + sub + "\nsub2: " + sub2);
    }
}
