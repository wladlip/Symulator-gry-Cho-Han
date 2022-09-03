package pl.zobaczymy;

/* Cho-Han to tradycyjna japońska gra hazardowa, w której krupier za pomocą kubka
rzuca dwiema kostkami o sześciu ściankach. Kubek jest postawiony podstawą do góry,
aby kostki były zakryte. Gracze obstawiają, czy suma wyrzuconych oczek jest parzysta
(Cho), czy nieparzysta (Han). Zwycięzca (lub zwycięzcy) zgarniają całą stawkę. Jeśli nie
ma zwycięzcy, pieniądze trafiają do krupiera.
*/
import java.util.Scanner;

public class ChoHan
{
    public static void main(String[] args)
    {
        final int MAX_ROUNDS = 5;
        String player1Name;
        String player2Name;

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Podaj imię pierwszego gracza: ");
        player1Name = keyboard.nextLine();
        System.out.print("Podaj imię drugiego gracza: ");
        player2Name = keyboard.nextLine();

        Dealer dealer = new Dealer();
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        for (int round = 0; round < MAX_ROUNDS; round++)
        {
            System.out.println("-------------------------");
            System.out.printf("Runda numer %d.\n", round + 1);

            dealer.rollDice();

            player1.makeGuess();
            player2.makeGuess();

            roundResults(dealer, player1, player2);
        }
        displayGrandWinner(player1, player2);
    }

    public static void roundResults(Dealer dealer, Player player1,
                                    Player player2)
    {
        System.out.printf("Krupier wyrzucił %d i %d.\n",
                         dealer.getDie1Value(), dealer.getDie2Value());
        System.out.printf("Wynik %s\n", dealer.getChoOrHan());

        checkGuess(player1, dealer);
        checkGuess(player2, dealer);
    }

    public static void checkGuess(Player player, Dealer dealer)
    {
        final int POINTS_TO_ADD = 1;
        String guess = player.getGuess();
        String choHanResult = dealer.getChoOrHan();

        System.out.printf("Gracz %s wytypował %s.\n",
                         player.getName(), player.getGuess());

        if (guess.equalsIgnoreCase(choHanResult))
        {
            player.addPoints(POINTS_TO_ADD);
            System.out.printf("Punkty przyznane graczowi %s to %d.\n",
                             player.getName(), POINTS_TO_ADD);
        }
    }
    public static void displayGrandWinner(Player player1, Player player2)
    {
        System.out.println("---------------------------");
        System.out.println("Koniec gry. Oto Wyniki");
        System.out.printf("%s: liczba punktów - %d.\n", player1.getName(),
                         player1.getPoints());
        System.out.printf("%s: liczba punktów - %d.\n", player2.getName(),
                                         player2.getPoints());

        if (player1.getPoints() > player2.getPoints())
            System.out.println(player1.getName() + " wygrał całą grę!");
        else if (player2.getPoints() > player1.getPoints())
            System.out.println(player2.getName() + " wygrał całą grę!");
        else
            System.out.println("Remis!");
    }
}
