/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class GetUserName {
     public static String gene(String ten) {
        String x = removeAccents(ten);
        String y = x.replaceAll("đ", "d");
        String result = getLastWordAbbreviation(y) + "nv" + randomNum();
        return result;
    }

    public static String getLastWordAbbreviation(String fullName) {
        String[] parts = fullName.split("\\s+"); // Tách chuỗi thành các từ
        StringBuilder abbreviation = new StringBuilder();
        // Xử lý từ cuối cùng không dấu
        String lastPart = parts[parts.length - 1];
        String normalizedLastPart = removeAccents(lastPart); // Loại bỏ dấu
        abbreviation.append(normalizedLastPart);
        // Lặp qua từng từ trong chuỗi
        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                char firstChar = Character.toLowerCase(part.charAt(0)); // Lấy ký tự đầu tiên và chuyển thành chữ thường
                abbreviation.append(firstChar);
            }
        }

        return abbreviation.toString();
    }

    public static String removeAccents(String input) {
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").toLowerCase();
    }

    public static String randomNum() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900) + 100);
    }
}
