package org.example;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static private String category_number, country_code,
            product_name, product_price, expire_date,
            expire_month, expire_year, explanation;

    public static void main(String[] args) throws IOException, WriterException {

        System.out.println("Category 1-4");
        category_number = input.nextLine();

        System.out.println("Code");
        country_code = input.nextLine();

        System.out.println("Name");
        product_name = input.nextLine();

        System.out.println("Price");
        product_price = input.nextLine();

        System.out.println("date");
        expire_date = input.nextLine();

        System.out.println("Month");
        expire_month = input.nextLine();

        System.out.println("Year");
        expire_year = input.nextLine();

        System.out.println("Explain");
        explanation = input.nextLine();


        String linekd = new StringBuilder().append(category_number).append("@").append(country_code).append("[")
                .append(product_name).append("]").append(product_price).
                append("*").append(expire_date).append("~").append(expire_month).append("!")
                .append(expire_year).append("{").append(explanation).append("}").toString();


        String encode = Base64.getEncoder().encodeToString(linekd.getBytes(StandardCharsets.UTF_8));

        System.out.println("Decoded values is ");
        System.out.println(encode);


        //path where we want to get QR Code
        String path = "E:/Spring project/BarCodeIncoder/src/main/resources.qcode.png";

        //Encoding charset to be used
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

        //generates QR code with Low level(L) error correction capability
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


        //invoking the user-defined method that creates the QR code
        generateQRcode(encode, path, charset, hashMap, 200, 200);


        //increase or decrease height and width accodingly
        //prints if the QR code is generated
        System.out.println("QR Code created successfully.");
    }

    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {

        //the BitMatrix class represents the 2D matrix of bits
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));

    }
}
