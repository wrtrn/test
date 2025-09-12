package complexTasks;

import complexTasks.userDataValidator.InvalidUserException;
import complexTasks.userDataValidator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {

    @Test
    public void nameCheck() {
        UserValidator userValidator1 = new UserValidator("Ivan", 18, "a@a.com");
        UserValidator userValidator2 = new UserValidator("evan", 15, "aa.com");
        UserValidator userValidator3 = new UserValidator("", 2, "aa%@a.com");

        UserValidator.disableValidation();
        Assertions.assertTrue(userValidator1.nameCheck());
        Assertions.assertTrue(userValidator2.nameCheck());
        Assertions.assertTrue(userValidator3.nameCheck());
        UserValidator.enableValidation();

        Assertions.assertTrue(userValidator1.nameCheck());
        Assertions.assertThrows(InvalidUserException.class, userValidator2::nameCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator3::nameCheck);
    }

    @Test
    public void ageCheck() {
        UserValidator userValidator1 = new UserValidator("Ivan", 18, "a@a.com");
        UserValidator userValidator2 = new UserValidator("Ivan", 19, "a@a.com");
        UserValidator userValidator3 = new UserValidator("Ivan", 50, "a@a.com");
        UserValidator userValidator4 = new UserValidator("Ivan", 99, "a@a.com");
        UserValidator userValidator5 = new UserValidator("Ivan", 100, "a@a.com");
        UserValidator userValidator6 = new UserValidator("evan", 101, "aa.com");
        UserValidator userValidator7 = new UserValidator("evan", 17, "aa.com");
        UserValidator userValidator8 = new UserValidator("evan", 0, "aa.com");
        UserValidator userValidator9 = new UserValidator("evan", -50, "aa.com");
        UserValidator userValidator10 = new UserValidator("", 2, "aa%@a.com");

        UserValidator.disableValidation();

        Assertions.assertTrue(userValidator1.ageCheck());
        Assertions.assertTrue(userValidator2.ageCheck());
        Assertions.assertTrue(userValidator3.ageCheck());
        Assertions.assertTrue(userValidator4.ageCheck());
        Assertions.assertTrue(userValidator5.ageCheck());
        Assertions.assertTrue(userValidator6.ageCheck());
        Assertions.assertTrue(userValidator7.ageCheck());
        Assertions.assertTrue(userValidator8.ageCheck());
        Assertions.assertTrue(userValidator9.ageCheck());
        Assertions.assertTrue(userValidator10.ageCheck());

        UserValidator.enableValidation();

        Assertions.assertTrue(userValidator1.ageCheck());
        Assertions.assertTrue(userValidator2.ageCheck());
        Assertions.assertTrue(userValidator3.ageCheck());
        Assertions.assertTrue(userValidator4.ageCheck());
        Assertions.assertTrue(userValidator5.ageCheck());

        Assertions.assertThrows(InvalidUserException.class, userValidator6::ageCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator7::ageCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator8::ageCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator9::ageCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator10::ageCheck);
    }

    @Test
    public void emailCheck() {
        UserValidator userValidator0 = new UserValidator("Ivan", 18, "asx@yandex.com");
        UserValidator userValidator1 = new UserValidator("Ivan", 18, "a@y.com");
        UserValidator userValidator2 = new UserValidator("Ivan", 19, "1@a.com");
        UserValidator userValidator3 = new UserValidator("Ivan", 50, "@a.com");
        UserValidator userValidator4 = new UserValidator("Ivan", 99, "aa.com");
        UserValidator userValidator5 = new UserValidator("Ivan", 100, "a@acom");
        UserValidator userValidator6 = new UserValidator("evan", 101, "aa@a%x.com");
        UserValidator userValidator7 = new UserValidator("evan", 17, "привет@яндекс.com");
        UserValidator userValidator8 = new UserValidator("evan", 0, "a:a@ax.com");
        UserValidator userValidator9 = new UserValidator("evan", -50, "a@a@asd.com");
        UserValidator userValidator10 = new UserValidator("", 2, "aa%@a.com");

        UserValidator.disableValidation();

        Assertions.assertTrue(userValidator0.emailCheck());
        Assertions.assertTrue(userValidator1.emailCheck());
        Assertions.assertTrue(userValidator2.emailCheck());
        Assertions.assertTrue(userValidator3.emailCheck());
        Assertions.assertTrue(userValidator4.emailCheck());
        Assertions.assertTrue(userValidator5.emailCheck());
        Assertions.assertTrue(userValidator6.emailCheck());
        Assertions.assertTrue(userValidator7.emailCheck());
        Assertions.assertTrue(userValidator8.emailCheck());
        Assertions.assertTrue(userValidator9.emailCheck());
        Assertions.assertTrue(userValidator10.emailCheck());

        UserValidator.enableValidation();

        Assertions.assertTrue(userValidator0.emailCheck());
        Assertions.assertTrue(userValidator1.emailCheck());
        Assertions.assertTrue(userValidator2.emailCheck());
        Assertions.assertThrows(InvalidUserException.class, userValidator3::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator4::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator5::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator6::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator7::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator8::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator9::emailCheck);
        Assertions.assertThrows(InvalidUserException.class, userValidator10::emailCheck);
    }
}
