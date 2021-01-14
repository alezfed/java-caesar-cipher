package main.java.com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.ProcessCryptoService;

public class CaesarCipherOnConsole {

    public static void main(String[] args) {
        (new ProcessCryptoService()).startCryptoService();
    }
}