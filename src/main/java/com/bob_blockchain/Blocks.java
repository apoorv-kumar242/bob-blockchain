package com.bob_blockchain;

import java.util.Date;

public class Blocks {
    public String hash;
    public String previousHash;
    private String data;
    public long timeStamp;
    private int nonce;

    public Blocks(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = CalculatingHashes();
    }

    // Calculating the Hashes
    public String CalculatingHashes(){
        String calHashes = StringUtilities.applySha256(previousHash+Long.toString(timeStamp)
                            +Integer.toString(nonce)+data);
                            return calHashes;
    }
    // Mining the Block
    public void BlockMining(int difficulty_level){
        String T = new String(new char[difficulty_level]);
        String a  = T.replace('\0','0');
        while (!hash.substring(0,difficulty_level).equals(a)) {
            nonce++;
            hash = CalculatingHashes();
        }        
    }
    
    public int getNonce() {
        return nonce;
    }
}
