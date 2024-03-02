package com.bob_blockchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlocksMain {

    public static ArrayList<Blocks> BB = new ArrayList<Blocks>();
    private static final int TARGET_BLOCK_TIME = 10 * 60;
    private static final int BLOCKS_FOR_DIFFICULTY_ADJUSTMENT = 2;
    private static int difficulty = 1; // the initial difficulty           

    public static void main(String[] args){

        Blocks genesisBlock = new Blocks("yo 1st block", "0");
        genesisBlock.BlockMining(difficulty);
        BB.add(genesisBlock);

        for (int i = 1; i < 10; i++) {
            Blocks newBlock = new Blocks("yo " + (i + 1) + "th block", BB.get(BB.size() - 1).hash);
            newBlock.BlockMining(difficulty);
            BB.add(newBlock);
            if (BB.size() % BLOCKS_FOR_DIFFICULTY_ADJUSTMENT == 0) {
                adjustDifficulty();
            }
        }

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(BB));
    }

    public static boolean chainValidity(){
        Blocks currentBlock;
        Blocks previousBlock;
        for(int i = 1; i < BB.size(); i++){
            currentBlock = BB.get(i);
            previousBlock = BB.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.CalculatingHashes())){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring(0, 2).equals("00")){
                System.out.println("Hashes are not valid");
                return false;
            }
        }
        return true;
    }

    public static void Blockadding(Blocks newBlock) {
		newBlock.BlockMining(difficulty);
		BB.add(newBlock);
	}

    private static void adjustDifficulty() {
        long startTime = BB.get(BB.size() - BLOCKS_FOR_DIFFICULTY_ADJUSTMENT).timeStamp;
        long endTime = BB.get(BB.size() - 1).timeStamp;
        long elapsedTime = endTime - startTime;

        double ratio = (double) elapsedTime / (TARGET_BLOCK_TIME * BLOCKS_FOR_DIFFICULTY_ADJUSTMENT);

        if (ratio < 1) {
            difficulty++;
        } else if (ratio > 1) {
            difficulty--;
        }

    }
}