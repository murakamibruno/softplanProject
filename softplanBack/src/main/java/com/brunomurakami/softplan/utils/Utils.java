package com.brunomurakami.softplan.utils;

public class Utils {

    public String convertToNumber(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }
}
