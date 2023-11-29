package study.level.middle;

import java.util.*;

enum TaskStatus {
    TODO, // 오늘 해야할 일
    진행중,
    완료
}

class Task<T extends Enum<T>> {
    private String name;
    private TaskStatus status;

    public Task(String name) {
        this.name = name;
        this.status = TaskStatus.TODO;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = (TaskStatus) status;
    }
}

class TeamMember {
    private String name;
    private List<Task<?>> tasks;

    public TeamMember(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task<?> task) {
        tasks.add(task);
    }

    public List<Task<?>> getTasks() {
        return tasks;
    }
}

class Project<T extends Enum<T>> {
    private String projectName;
    private Map<TeamMember, List<Task<?>>> teamTasks;

    public Project(String projectName) {
        this.projectName = projectName;
        this.teamTasks = new HashMap<>();
    }

    public void addTeamMember(TeamMember teamMember) {
        teamTasks.put(teamMember, new ArrayList<>());
    }

    public void assignTask(TeamMember teamMember, Task<?> task) {
        teamTasks.get(teamMember).add(task);
    }

    public void printTeamTasks() {
        System.out.println("프로젝트: " + projectName);
        for (Map.Entry<TeamMember, List<Task<?>>> entry : teamTasks.entrySet()) {
            TeamMember teamMember = entry.getKey();
            List<Task<?>> tasks = entry.getValue();

            System.out.println("팀 멤버: " + teamMember.getName());
            for (Task<?> task : tasks) {
                System.out.println("  - 작업: " + task.getName() + " | 상태: " + task.getStatus());
            }
        }
    }
}

public class TeamManageExample {
    public static void main(String[] args) {
        TeamMember john = new TeamMember("John");
        TeamMember alice = new TeamMember("Alice");

        Task<TaskStatus> task1 = new Task<>("코드 리팩토링");
        Task<TaskStatus> task2 = new Task<>("단위 테스트 작성");
        Task<TaskStatus> task3 = new Task<>("UI 디자인");

        john.addTask(task1);
        john.addTask(task2);

        alice.addTask(task3);

        Project<TaskStatus> softwareProject = new Project<>("소프트웨어 프로젝트");
        softwareProject.addTeamMember(john);
        softwareProject.addTeamMember(alice);

        softwareProject.assignTask(john, task1);
        softwareProject.assignTask(john, task2);
        softwareProject.assignTask(alice, task3);

        task1.setStatus(TaskStatus.진행중);
        task2.setStatus(TaskStatus.완료);
        task3.setStatus(TaskStatus.진행중);

        softwareProject.printTeamTasks();
    }
}
