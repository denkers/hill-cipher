
public class Key 
{
    private int[][] keyMatrix;
    private String keyStr;
    
    public Key(String keyStr)
    {
        this.keyStr  =   keyStr;
        initKeyMatrix();
    }
    
    public Key(int[][] keyMatrix)
    {
        this.keyMatrix  =   keyMatrix;
        initKeyStr();
    }
    
    private void initKeyMatrix()
    {
        int size    =   getKeyStrSize();
        keyMatrix   =   MatrixUtils.getStrMatrix(keyStr, size);
    }
    
    private void initKeyStr()
    {
        keyStr  =   MatrixUtils.matrixToString(keyMatrix);
    }
    
    public int getKeyStrSize()
    {
        if(keyStr.length() % 2 == 0) return 2;
        else return 3;
    }
    
    public Key getInverseKey()
    {
        int[][] invKeyMatrix    =   MatrixUtils.getInverse(keyMatrix);
        return new Key(invKeyMatrix);
    }

    public int[][] getKeyMatrix()
    {
        return keyMatrix;
    }

    public String getKeyStr() 
    {
        return keyStr;
    }
}
