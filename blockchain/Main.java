package blockchain;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import blockchain.miners.Miner;
import blockchain.utils.SerializationUtils;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        Blockchain blockchain = new Blockchain();
//        blockchain = (Blockchain) SerializationUtils.deserialize("blockchain.txt");

        ExecutorService executor = Executors.newFixedThreadPool(5);


        while (blockchain.getCounter() < 5) {
            executor.execute(() -> {
                Miner miner = new Miner(blockchain);
                miner.sendToBlockchain(miner.mine());

            });
            Thread.sleep(50);
        }

        executor.shutdownNow();

//        SerializationUtils.serialize(blockchain, blockchain.txt");

    }
}
