package blockchain.miners;

import blockchain.Block;
import blockchain.Blockchain;

public class Miner {
    private static int counter = 1;
    private int id;

    Blockchain blockchain;

    public Miner(Blockchain blockchain) {
        this.blockchain = blockchain;
        id = counter;
        counter++;
    }

    public Block mine() {
        Block lastBlock = blockchain.getLastBlock();
        Block newBlock = new Block(
                lastBlock.getId() + 1,
                lastBlock.getHash(),
                blockchain.getProveString()
                );
        return newBlock;
    }


    public void sendToBlockchain(Block block) {
        blockchain.sendNewBlock(block, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
