package HW_M13;

import java.util.Random;

public class RacingTeam implements Runnable {
    Thread team;//������ �� �����
    static int counter = 1;
     int position;

    RacingTeam(String name) {
        team = new Thread(this, name);
        team.start();//������ ������ ����� ����� ��������
    }

    final Random rand = new Random();

    int getStrength() {
        return rand.nextInt(300) + 100;
    }

    @Override
    public void run() {
        System.out.println("������� " + team.getName() + " ��������");
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(getStrength());// ��������� ��������� ������
                System.out.println("��������� ����� " + i + " ������� " + team.getName());
            }
        } catch (InterruptedException e) {
            System.out.println("������� " + team.getName() + " �������� �������");
        }
        System.out.println("������� " + team.getName() + " ������������");
        position = counter;
        counter++;
    }

    static class Main {
        public static void main(String[] args) {
            System.out.println("�������� ��������!");
            //new RacingTeam("�������");
            RacingTeam team1 = new RacingTeam("�������");
            RacingTeam team2 = new RacingTeam("�������");
            RacingTeam team3 = new RacingTeam("�����");

            try {
                team1.team.join();
                team2.team.join();
                team3.team.join();
            } catch (InterruptedException exc) {
                System.out.println("������������ �����������");
            }
            System.out.println("����� ���������");
            System.out.println("������� " + team1.team.getName() + " ������ " + team1.position + " �����");
            System.out.println("������� " + team2.team.getName() + " ������ " + team2.position + " �����");
            System.out.println("������� " + team3.team.getName() + " ������ " + team3.position + " �����");
        }
    }
}