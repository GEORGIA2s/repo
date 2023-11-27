package study.level.low;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

// ToDo 상태를 정의하는 이넘
enum TodoStatus {
    TODO, IN_PROGRESS, DONE
}

// ToDo 클래스에 제네릭을 사용하여 임의의 데이터를 저장할 수 있도록 함
class Todo<T> {
    private T data;
    private TodoStatus status;

    public Todo(T data) {
        this.data = data;
        this.status = TodoStatus.TODO;
    }

    public T getData() {
        return data;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "data=" + data +
                ", status=" + status +
                '}';
    }
}

// ToDo 목록을 관리하는 클래스
class TodoManager<T> {
    private List<Todo<T>> todoList = new ArrayList<>();

    // ToDo를 추가하는 메서드
    public void addTodo(T data) {
        Todo<T> todo = new Todo<>(data);
        todoList.add(todo);
        System.out.println("ToDo 추가: " + todo);
    }

    // ToDo 목록을 반환하는 메서드
    public List<Todo<T>> getTodoList() {
        return todoList;
    }

    // 특정 상태의 ToDo 목록을 반환하는 메서드
    public List<Todo<T>> getTodosByStatus(TodoStatus status) {
        List<Todo<T>> filteredList = new ArrayList<>();
        for (Todo<T> todo : todoList) {
            if (todo.getStatus() == status) {
                filteredList.add(todo);
            }
        }
        return filteredList;
    }

    // ToDo 상태를 변경하는 메서드
    public void updateTodoStatus(int index, TodoStatus newStatus) {
        if (index >= 0 && index < todoList.size()) {
            Todo<T> todo = todoList.get(index);
            todo.setStatus(newStatus);
            System.out.println("ToDo 업데이트: " + todo);
        } else {
            System.out.println("유효하지 않은 인덱스");
        }
    }
}

public class CollectionAndGenericEnumExample {
    public static void main(String[] args) {
        // ToDo 관리자 생성 및 ToDo 추가
        TodoManager<String> todoManager = new TodoManager<>();
        todoManager.addTodo("자바 공부");
        todoManager.addTodo("알고리즘 연습");
        todoManager.addTodo("프로젝트 회의");

        // ToDo 목록 출력
        List<Todo<String>> todos = todoManager.getTodoList();
        System.out.println("전체 ToDo 목록: " + todos);

        // ToDo 상태 변경 및 출력
        todoManager.updateTodoStatus(0, TodoStatus.IN_PROGRESS);
        todoManager.updateTodoStatus(2, TodoStatus.DONE);
        System.out.println("변경된 ToDo 목록: " + todos);

        // 특정 상태의 ToDo 목록 출력
        List<Todo<String>> inProgressTodos = todoManager.getTodosByStatus(TodoStatus.IN_PROGRESS);
        System.out.println("진행 중인 ToDo 목록: " + inProgressTodos);
    }
}
