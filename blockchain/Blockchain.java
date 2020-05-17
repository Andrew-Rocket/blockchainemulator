package blockchain;


import blockchain.miners.Miner;
import blockchain.utils.StringUtil;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Blockchain implements Serializable {
    final static long serialVersionUID = 1L;

    private int counter = 0;
    private String prevHash;
    private List<Block> blocks = new LinkedList<>();
    private int proveOfWorkCount = 0;


    public Blockchain() {
        blocks.add(new Block(0, "0", getProveString()));
        prevHash=getLastBlock().getHash();
    }


//    private void createBlock() {
//
//        Block block = new Block(counter, prevHash, proveOfWork);
//        counter++;
//        prevHash = block.getHash();
//        blocks.add(block);
//
//    }

    public synchronized void sendNewBlock(Block block, Miner miner) {
        if (block.getHash().startsWith(getProveString()) && block.getPreviousHash().equals(prevHash)) {
            prevHash = block.getHash();
            block.setCreationTime(block.getTimestamp() - getLastBlock().getTimestamp());
            block.setMinerId(miner.getId());
            blocks.add(block);

            System.out.println(block);

            if(block.getCreationTime()<2000) {
                proveOfWorkCount++;
                System.out.println("N was increased to " + proveOfWorkCount);
            } else

            if(block.getCreationTime()>30000) {
                proveOfWorkCount--;
                System.out.println("N was decreased to " + proveOfWorkCount);
            } else {
                System.out.println("N stays the same");
            }

            System.out.println("\n");
            counter++;
        }
    }

    public String getProveString() {
        StringBuilder proveOfWork = new StringBuilder();

        for (int i = 0; i < proveOfWorkCount; i++) {
            proveOfWork.append("0");
        }

        return proveOfWork.toString();

    }

    public Block get(int index) {
        return blocks.get(index);
    }

    public Block getLastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public boolean validateAll() {
        String proveOfWork = getProveString();
        for (Block cur : blocks) {
            if (!cur.getHash().equals(StringUtil.applySha256(cur.getId() + cur.getTimestamp() + cur.getPreviousHash()))) {
                return false;
            }
            if (!cur.getHash().startsWith(proveOfWork)) {
                return false;
            }
        }
        return true;
    }

    public int getCounter() {
        return counter;
    }
}
