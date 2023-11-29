package study.level.middle;

import java.util.*;

enum PostType {
    본문,
    이미지,
    링크
}

class Post<T extends Enum<T>> {
    private String content;
    private T type;

    public Post(String content, T type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public T getType() {
        return type;
    }
}

class User {
    private String username;
    private List<Post<?>> posts;

    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void addPost(Post<?> post) {
        posts.add(post);
    }

    public List<Post<?>> getPosts() {
        return posts;
    }
}

class Blog<T extends Enum<T>> {
    private Map<User, List<Post<?>>> userPosts;

    public Blog() {
        this.userPosts = new HashMap<>();
    }

    public void addUser(User user) {
        userPosts.put(user, new ArrayList<>());
    }

    public void addPost(User user, Post<?> post) {
        userPosts.get(user).add(post);
    }

    public Optional<Post<?>> getLatestPost(User user) {
        return userPosts.get(user).stream()
                .max(Comparator.comparing(Post::getContent));
    }

    public void printBlog() {
        System.out.println("블로그 포스팅:");

        for (Map.Entry<User, List<Post<?>>> entry : userPosts.entrySet()) {
            User user = entry.getKey();
            List<Post<?>> posts = entry.getValue();

            System.out.println("사용자: " + user.getUsername());

            for (Post<?> post : posts) {
                System.out.println("  - 타입: " + post.getType() + " | 내용: " + post.getContent());
            }
        }
    }
}

public class BlogPostingExample {
    public static void main(String[] args) {
        User ShimCheong = new User("심청이");
        User Hong = new User("홍길동");

        Post<PostType> textPost = new Post<>("안녕하세요", PostType.본문);
        Post<PostType> imagePost = new Post<>("이미지: 석양.jpg", PostType.이미지);
        Post<PostType> linkPost = new Post<>("여기 링크 들어가보셈 ㅋㅋ : www.example.com", PostType.링크);

        ShimCheong.addPost(textPost);
        ShimCheong.addPost(imagePost);
        Hong.addPost(linkPost);

        Blog<PostType> myBlog = new Blog<>();
        myBlog.addUser(ShimCheong);
        myBlog.addUser(Hong);

        myBlog.addPost(ShimCheong, textPost);
        myBlog.addPost(ShimCheong, imagePost);
        myBlog.addPost(Hong, linkPost);

        myBlog.printBlog();

        // Example of using Optional and stream
        myBlog.getLatestPost(ShimCheong).ifPresent(post ->
                System.out.println("[최신 게시물] " + ShimCheong.getUsername() + ": " + post.getContent()));
    }
}
