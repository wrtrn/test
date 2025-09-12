package complexTasks.userDataValidator;

public class UserValidator extends User {
    private boolean validationEnabled;

    public UserValidator(String name, int age, String email) {
        super(name, age, email);
    }


    public boolean nameCheck() {
        if (validationEnabled) {
            if ((getName().isEmpty() || getName() == null || !Character.isUpperCase(getName().charAt(0)))) {
                throw new InvalidUserException("Name is invalid");
            }

        }
        return true;
    }

    public boolean ageCheck() {

        if (validationEnabled) {
            int minAge = 18;
            int maxAge = 100;

            if (getAge() < 18 || getAge() > 100) {
                throw new InvalidUserException("Age is invalid");
            }

        }
        return true;
    }

    public boolean emailCheck() {
        if (validationEnabled) {
            if (!getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new InvalidUserException("Email is invalid");
            }

        }
        return true;
    }

    public void changeValidationStatus(boolean validationEnabled) {
        this.validationEnabled = validationEnabled;
    }
}
