
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
        int numRounds   =   cipher.getText().length() - knownPlaintext.length() + 2;
        
        for(int i = 0; i < numRounds; i++)
        {
            Entry<CStructure, CStructure> attempt   =   getAttemptRound(cipher, knownPlaintext, i);
            if(attempt != null) attemptList.add(attempt);
        }
        
        return attemptList;
    }
    
    public static String testStr(String s)
    {
        return "[" + s.substring(0, 2) + " " + s.substring(2) + "]";
    }
    
    public static Entry<CStructure, CStructure> getAttemptRound(CStructure cipher, String knownPlaintext, int offset)
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
    
    public static CStructure getBigrams(String text, int offset)
    {
        CStructure bigramA      =   new CStructure(text.substring(offset, offset + 2), 26);
        CStructure bigramB      =   new CStructure(text.substring(offset + 2, offset + 4), 26);
        int[][] bigramMatrix    =   new int[2][2];
        
        bigramMatrix[0]         =   bigramA.getRow(0);
        bigramMatrix[1]         =   bigramB.getRow(0);
        
        return new CStructure(bigramMatrix, 26);
    }
    
    public static void main(String[] args)
    {
        CStructure key      =   new CStructure("alph", 26);
        CStructure text     =   new CStructure("defendthewestwallofthecastle", 26);
        CStructure cipher   =   new CStructure("fupcmtgzkyukbqfjhuktzkkixtta", 26);
        CStructure decr     =   HillCipher.decrypt(cipher, key, 26);
        String knownText    =   "ofthe";
        
        System.out.println(cipher.getText());
        List<Entry<CStructure, CStructure>> attempts    =   breakCipher(cipher, knownText);
        
        for(Entry<CStructure, CStructure> attempt : attempts)
            System.out.println(attempt.getValue().getText()); 
    }
}
