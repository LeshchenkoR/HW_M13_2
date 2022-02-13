package HW_M13;

import java.util.Random;

public class RacingTeam implements Runnable {
    Thread team;//ссылка на поток
    static int counter = 1;
     int position;

    RacingTeam(String name) {
        team = new Thread(this, name);
        team.start();//запуск потока сразу после создания
    }

    final Random rand = new Random();

    int getStrength() {
        return rand.nextInt(300) + 100;
    }

    @Override
    public void run() {
        System.out.println("Команда " + team.getName() + " стартует");
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(getStrength());// случайные параметры игрока
                System.out.println("Стартовал игрок " + i + " команды " + team.getName());
            }
        } catch (InterruptedException e) {
            System.out.println("Команда " + team.getName() + " нарушила правила");
        }
        System.out.println("Команда " + team.getName() + " финишировала");
        position = counter;
        counter++;
    }

    static class Main {
        public static void main(String[] args) {
            System.out.println("Начинаем эстафету!");
            //new RacingTeam("Спартак");
            RacingTeam team1 = new RacingTeam("Зеленые");
            RacingTeam team2 = new RacingTeam("Красные");
            RacingTeam team3 = new RacingTeam("Синие");

            try {
                team1.team.join();
                team2.team.join();
                team3.team.join();
            } catch (InterruptedException exc) {
                System.out.println("Соревнования остановлены");
            }
            System.out.println("Гонка завершена");
            System.out.println("Команда " + team1.team.getName() + " заняла " + team1.position + " место");
            System.out.println("Команда " + team2.team.getName() + " заняла " + team2.position + " место");
            System.out.println("Команда " + team3.team.getName() + " заняла " + team3.position + " место");
        }
    }
}