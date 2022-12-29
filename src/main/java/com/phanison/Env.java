package com.phanison;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    private Dotenv dotenv;

    public Env() {
        dotenv = Dotenv.configure().load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}
