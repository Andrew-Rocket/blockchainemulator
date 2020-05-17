package blockchain;

import blockchain.utils.StringUtil;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class Block implements Serializable {
    private Integer id;
    private Long timestamp;
    private String previousHash = "5";
    private int magicNumber;
    private String hash;
    private long creationTime;
    private int minerId;

    public Block(int id, String previousHash, String proveOfWork) {


        this.id = id;
        this.previousHash = previousHash;

        this.timestamp = new Date().getTime();
        this.hash = generateProvedHash(proveOfWork);


    }

    public String generateProvedHash(String proveOfWork) {
        Random rand = new Random();
        while(true) {
            if (generateHash().startsWith(proveOfWork)) {
                return generateHash();
            }
            magicNumber = rand.nextInt();
        }
    }


    public String generateHash() {
        return StringUtil.applySha256(id.toString() + timestamp.toString() + previousHash + magicNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block: \n");
        sb.append("Created by miner # ").append(minerId).append("\n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Timestamp: ").append(timestamp).append("\n");
        sb.append("Magic number: ").append(magicNumber).append("\n");
        sb.append("Hash of the previous block:\n").append(previousHash).append("\n");
        sb.append("Hash of the block:\n").append(hash).append("\n");
        sb.append("Block was generating for ").append(creationTime / 1000).append(" seconds");
        return sb.toString();
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public int getMinerId() {
        return minerId;
    }

    public void setMinerId(int minerId) {
        this.minerId = minerId;
    }
}
