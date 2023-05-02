package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User user2 = new User("Petr", "Petrov", "petr@mail.ru");
      User user3 = new User("Olga", "Pavlova", "pavlova@mail.ru");
      User user4 = new User("Svetlana", "Sidorova", "sid@mail.ru");

      Car car1 = new Car("Audi", 5);
      Car car2 = new Car("BMW", 325);
      Car car3 = new Car("BMW", 520);
      Car car4 = new Car("Lada", 2106);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.getlistUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Auto: " + "Model: " + user.getCar().getModel() + " Series: " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("___________________________________________");
      System.out.println("User c Audi: " + userService.getUserByCar("Audi", 5));
      System.out.println("User c Lada: " + userService.getUserByCar("Lada", 2106));

      context.close();
   }
}
