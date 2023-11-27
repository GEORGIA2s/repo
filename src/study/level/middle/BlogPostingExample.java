package study.level.middle;

import java.util.*;

enum PostType {
    TEXT,
    IMAGE,
    LINK
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
        System.out.println("Blog Posts:");

        for (Map.Entry<User, List<Post<?>>> entry : userPosts.entrySet()) {
            User user = entry.getKey();
            List<Post<?>> posts = entry.getValue();

            System.out.println("User: " + user.getUsername());

            for (Post<?> post : posts) {
                System.out.println("  - Type: " + post.getType() + " | Content: " + post.getContent());
            }
        }
    }
}

public class BlogPostingExample {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        Post<PostType> textPost = new Post<>("Hello, World!", PostType.TEXT);
        Post<PostType> imagePost = new Post<>("Image: Sunset", PostType.IMAGE);
        Post<PostType> linkPost = new Post<>("Check this out: www.example.com", PostType.LINK);

        alice.addPost(textPost);
        alice.addPost(imagePost);
        bob.addPost(linkPost);

        Blog<PostType> myBlog = new Blog<>();
        myBlog.addUser(alice);
        myBlog.addUser(bob);

        myBlog.addPost(alice, textPost);
        myBlog.addPost(alice, imagePost);
        myBlog.addPost(bob, linkPost);

        myBlog.printBlog();

        // Example of using Optional and stream
        myBlog.getLatestPost(alice).ifPresent(post ->
                System.out.println("Latest Post by " + alice.getUsername() + ": " + post.getContent()));
    }
}
