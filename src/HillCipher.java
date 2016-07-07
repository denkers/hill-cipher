
public class HillCipher 
{
    public static int[][] encrypt(String plainText, String key, int size)
    {
        int[][] keyMatrix       =   MatrixUtils.getStrMatrix(key, size);
        int[][] plainTextMatrix =   MatrixUtils.getStrMatrix(plainText, size);
        int[][] cipherMatrix    =   new int[plainTextMatrix.length][plainTextMatrix[0].length];
        
        for(int i = 0; i < plainTextMatrix.length; i++)
        {
            int[] entry     =   plainTextMatrix[i];
            cipherMatrix[i] =   MatrixUtils.multiplyMatrix(keyMatrix, entry, 255);
        } 
        
        return cipherMatrix;
    }
    
    public static String decrypt(int[][] cipherMatrix, String key)
    {
        String plainText    =   "";
        int[][] keyMatrix   =   MatrixUtils.getStrMatrix(key, cipherMatrix[0].length);
        keyMatrix           =   MatrixUtils.getInverse(keyMatrix);
        
        for(int i = 0; i < cipherMatrix.length; i++)
        {
            int[] decVector =   MatrixUtils.multiplyMatrix(keyMatrix, cipherMatrix[i], 255);
            
            for(int j = 0; j < decVector.length; j++)
                plainText += (decVector[j] == 0)? "" : (char) (decVector[j] + '0');
        } 
        
        return plainText;
    }

}
