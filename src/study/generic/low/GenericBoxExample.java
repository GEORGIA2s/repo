package study.generic.low;

class Box<T> {
    private T content;

    public Box(T content){
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

class EnhancedBox<T> extends Box<T> {
    private T tag;
    public EnhancedBox(T content, T tag) {
        super(content);
        this.tag = tag;
    }

    public T getTag() {
        return tag;
    }
}

public class GenericBoxExample {

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>(42);
        System.out.println("Box Content : " + integerBox.getContent());

        EnhancedBox<String> enhancedBox = new EnhancedBox<>("NIKE","BOOM");
        System.out.println("Enhanced Box Content : " + enhancedBox.getContent());
        System.out.println("Enhanced Box Content : " + enhancedBox.getTag());

    }
}
