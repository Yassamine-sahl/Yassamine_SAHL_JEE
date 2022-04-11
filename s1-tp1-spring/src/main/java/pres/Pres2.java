package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));

        //Injection des dependence par instantiation dynamic
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();


        String metierClaaName = scanner.nextLine();
        Class cMetier = Class.forName(metierClaaName);
        IMetier metier = (IMetier) cMetier.newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        //metier.setDao(dao);
        method.invoke(metier, dao);

        System.out.println("Resultat :" + metier.calcul());
    }
}

