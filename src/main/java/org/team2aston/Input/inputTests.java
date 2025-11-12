import org.team2aston.Input.InputManager;

void main() {
    InputManager manager = new InputManager();

    List<?> list = manager.fillEmployeeList();

    System.out.println(list);

}