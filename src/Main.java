
import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int scorePlayer1; //1. oyuncunun skoru
        int scorePlayer2; //2. oyuncunun skoru

        int winsPlayer1; //1. oyuncunun kazandığı tur sayısı
        int winsPlayer2; //2. oyuncunun kazandığı tur sayısı

        int turnCount; // oynanmak istenen tur sayısı

        boolean gameEnded = false; //oyunu tekrarlayabilmek için

        Scanner scanner = new Scanner(System.in);

        String playerNames[] = new String[2]; //Javada birden fazla string üst üste girdi almak sıkıntılı olduğu için böyle yapmak gerekiyormuş

        System.out.print("//////Taş Kağıt Makas//////\nHoşgeldiniz,sırasıyla 1. ve 2. oyuncunun isimlerini giriniz: ");

        for(int i=0;i<2;i++){
            playerNames[i]=scanner.nextLine(); //Oyuncuların isimlerinin alınması
        }

        while(!gameEnded){ // Ana oyun döngüsü

            System.out.println("Oynamak istediğiniz tur sayısını giriniz(1-10): ");
            turnCount = scanner.nextInt(); //Tur sayısı alma

            System.out.println("Oyun kuralları: Taş hamlesi yapmak için T,Kağıt hamlesi yapmak için K,\n" +
                    "Makas hamlesi yapmak için M harflerini giriniz(Büyük harf veya küçük harf girebilirsiniz)");

            scorePlayer1=0; //Burada her iki oyuncunun puanını sıfıra eşitliyorum ki eğer tekrar oynanmak istenirse sıfırlansın
            scorePlayer2=0;

            winsPlayer1=0;
            winsPlayer2=0;

            for(int i=0;i<turnCount;i++){ //tur sayısı kadar çalışacak

                System.out.printf("\n//%d. TUR//\n",i+1);

                System.out.print("1. oyuncu hamle: ");
                String choice = scanner.next().toLowerCase(); //büyük harf küçük harf sıkıntı çıkarmasın diye ne girilirse girilsin küçüklttüm
                int computerChoice = ComputerChoice(); //rastgele hamle yapan kod
                switch (checkWinner(choice,computerChoice)){ //1==Berabere 2==Kaybetme, 3==kazanma olarak kodladım
                    case 1:
                        System.out.println("2. oyuncu hamle: "+ ComputerChoiceString(computerChoice));
                        System.out.println("Berabere");
                        scorePlayer1+=1;
                        scorePlayer2+=1;
                        break;
                    case 2:
                        System.out.println("2. oyuncu hamle: "+ ComputerChoiceString(computerChoice));
                        System.out.println("Kaybettiniz");
                        scorePlayer2+=1;
                        winsPlayer2+=1;
                        break;
                    case 3:
                        System.out.println("2. oyuncu hamle: "+ ComputerChoiceString(computerChoice));
                        System.out.println("Kazandınız");
                        scorePlayer1+=1;
                        winsPlayer1+=1;
                        break;

                }


            }

            System.out.println("\n1. oyuncu skor: "+scorePlayer1);
            System.out.println("2. oyuncu skor: "+scorePlayer2 + "\n");

            if(scorePlayer1<scorePlayer2) //oyuncuların skorlarının karşılaştırılması
                System.out.println("2. oyuncu kazandı! Tebrikler "+playerNames[1] +"\nToplam "+winsPlayer2+" tur kazandın.");
            else
                System.out.println("1. oyuncu kazandı! Tebrikler "+playerNames[0] +"\nToplam "+winsPlayer1+" tur kazandın.");

            System.out.println("\nTekrar oynamak ister misiniz? Evet=1/Hayır=0: ");
            int playAgain = scanner.nextInt();//normalde string olarak evet hayır alacaktım ama isim almadaki gibi yine sıkıntı çıkardığı için int aldım

            if(playAgain==0)
                gameEnded=true; //tekrar oynamak istemezsek oyun bitti değişkenini false yapıp döngüden çıkıyor

        }

        System.out.println("Güle güle :)");
    }
    static int ComputerChoice(){ //rastgele bilgisayar hamlesi üreten method 1==Taş 2==Kağır 3==Makas
        Random rng = new Random();
        int computerChoice= rng.nextInt(3)+1;
        return computerChoice;
    }

    static String ComputerChoiceString(int computerChoice){ //Hamleyi string olarak kullanıcıya göstermek için hamle kodunu stringe çeviren method
        switch (computerChoice){
            case 1:
                return "Taş";
            case 2:
                return "Kağıt";
            case 3:
                return "Makas";
        }
        return "e";//buna gerek yok ama koymasam hata veriyordu
    }
    static int checkWinner (String playerChoice,int computerChoice){

        //sırayla tüm olasılıkları deneyip 1. oyuncu kazandı mı kaybetti mi onu belirliyor. Belki daha optimal bir yolu vardır ama
        //benim aklıma bu geldi.
        if(playerChoice.equals("t")){
            switch (computerChoice){
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
            }
        }
        else if (playerChoice.equals("k")) {
            switch (computerChoice){
                case 1:
                    return 3;
                case 2:
                    return 1;
                case 3:
                    return 2;
            }
        }
        else if (playerChoice.equals("m")) {
            switch (computerChoice){
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 1;
            }
        }
        return 4;//buna gerek yok ama koymasam hata veriyordu

    }

}