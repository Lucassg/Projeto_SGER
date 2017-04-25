package teste;

import java.io.IOException;

public class teste {

    public static void main(String[] args) throws IOException {
        int k = 0;
        int qtd = 23; // quantidade de pedidos
        for (int i = qtd - 1; i > 0; i--) {
            k = k + i;
            System.out.println("i: " + i);
            System.out.println("k: " + k);
        }
        System.out.println(k);
        System.exit(0);
    }
}
