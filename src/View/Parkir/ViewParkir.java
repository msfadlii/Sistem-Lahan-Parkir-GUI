package View.Parkir;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ViewParkir {
    static Scanner input = new Scanner(System.in);
    public void checkIn() throws ParseException {
        System.out.print("Masukkan Plat Nomor : ");
        String plat = input.nextLine();
        System.out.print("Masukkan Tanggal Parkir : ");
        String tanggal = input.nextLine();
        System.out.print("Msaukkan Jam Parkir : ");
        String jam = input.nextLine();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = tanggal+" "+jam;
        Date parsedDate = format.parse(date);

        System.out.println(parsedDate);

    }
}
