package com.company;

public class CRC32Calculator {
    private static int[] crc_table = new int[256];
    public static void main(String[] args) {
        InitTable();

        System.out.println(GetCRC32("This is example text ..."));
    }

    public static void InitTable(){
        for (int i = 0; i < 256; ++i) {
            int code = i;
            for (int j = 0; j < 8; ++j) {
                code = (code & 0x01) == 1 ? (0xEDB88320^ (code >>> 1)) : (code >>> 1);
            }
            crc_table[i] = code;
        }
    }

    public static int GetCRC32(String text){
        int crc = -1;
        for(int i = 0; i < text.length(); ++i){
            int code = Character.codePointAt(text, i);
            crc = crc_table[(code ^ crc) & 0xFF] ^ (crc >>> 8);
        }

        return ((-1 ^ crc) >>> 0);
    }
}
