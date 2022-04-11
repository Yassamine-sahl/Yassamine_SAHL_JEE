package dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        //se connecter à la BD pour récupérer la température
        System.out.println("Version Base de donnee");
        double temp = Math.random() * 40;
        return temp;
    }
}



